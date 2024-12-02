package com.parfka.adjust.sdk;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by uerceg on 03/04/2017.
 */

public class UtilNetworking {
  private static String userAgent;
  private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
  private static final MediaType BINARY = MediaType.get("application/octet-stream");
  public static final OkHttpClient okHttpClient =
    new OkHttpClient.Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .build();

  private static ILogger getLogger() {
    return AdjustFactory.getLogger();
  }

  public static void setUserAgent(String userAgent) {
    UtilNetworking.userAgent = userAgent;
  }


  public static ResponseData createPOSTHttpsURLConnection(String urlString, ActivityPackage activityPackage, int queueSize) throws Exception {
    try {
      Map<String, Object> parameters = new HashMap<String, Object>(activityPackage.getParameters());

      URL org_url = new URL(urlString);

      Uri.Builder builder = new Uri.Builder();
      String scheme = org_url.getProtocol();
      String authority = org_url.getAuthority();
      String path = org_url.getPath();

      builder.scheme(scheme);
      builder.encodedAuthority(authority);
      builder.path(path);

      URL url = new URL(builder.build().toString());

      getLogger().debug("post URL: %s", url.toString());

      String appSecret = extractAppSecret(parameters);
      String secretId = extractSecretId(parameters);
      extractEventCallbackId(parameters);

      String authorizationHeader = buildAuthorizationHeader(parameters, appSecret, secretId, activityPackage.getActivityKind().toString());


      RequestBody body = RequestBody.create(JSON, getPostDataString(parameters, queueSize));

      Request.Builder requestBuilder = new Request.Builder();
      if (authorizationHeader != null) {
        requestBuilder.header("Authorization", authorizationHeader);
      }

      String clientSdk = activityPackage.getClientSdk();
      if (clientSdk != null) {
        requestBuilder.header("Client-SDK", clientSdk);
      }
      if (userAgent != null) {
        requestBuilder.header("User-Agent", userAgent);
      }

      Request request = requestBuilder.url(url).post(body).build();
      Response response = okHttpClient.newCall(request).execute();
      try {
        return readHttpResponse(response, activityPackage);
      } finally {
        try {
          response.close();
        } catch (Exception ex) {
          getLogger().error("close response body failed");
        }
      }

    } catch (Exception e) {
      throw e;
    }
  }

  public static ResponseData createGETHttpsURLConnection(ActivityPackage activityPackage, String basePath) throws Exception {
    try {
      Map<String, Object> parameters = new HashMap<String, Object>(activityPackage.getParameters());

      String appSecret = extractAppSecret(parameters);
      String secretId = extractSecretId(parameters);
      extractEventCallbackId(parameters);

      Uri uri = buildUri(activityPackage.getPath(), parameters, basePath);

      URL url = new URL(uri.toString());

      Request.Builder requestBuilder = new Request.Builder();
      String authorizationHeader = buildAuthorizationHeader(parameters, appSecret, secretId, activityPackage.getActivityKind().toString());
      if (authorizationHeader != null) {
        requestBuilder.header("Authorization", authorizationHeader);
      }

      String clientSdk = activityPackage.getClientSdk();
      if (clientSdk != null) {
        requestBuilder.header("Client-SDK", clientSdk);
      }
      if (userAgent != null) {
        requestBuilder.header("User-Agent", userAgent);
      }
      Request request = requestBuilder.url(url).get().build();
      Response response = okHttpClient.newCall(request).execute();
      try {
        return readHttpResponse(response, activityPackage);
      } finally {
        try {
          response.close();
        } catch (Exception ex) {
          getLogger().error("close response body failed");
        }
      }

    } catch (Exception e) {
      throw e;
    }
  }

  private static ResponseData readHttpResponse(Response response, ActivityPackage activityPackage) throws Exception {
    ILogger logger = getLogger();
    Integer responseCode = null;

    ResponseData responseData = ResponseData.buildResponseData(activityPackage);

    responseCode = response.code();
    ResponseBody responseBody = response.body();
    if (responseBody == null) {
      logger.error("Response body is empty");
      return responseData;
    }

    String stringResponse = responseBody.string();

    logger.verbose("Response: %s", stringResponse);

    if (responseCode == 429) {
      logger.error("Too frequent requests to the endpoint (429)");
      return responseData;
    }

    if (stringResponse == null || stringResponse.length() == 0) {
      return responseData;
    }

    JSONObject jsonResponse = null;

    try {
      jsonResponse = new JSONObject(stringResponse);
    } catch (JSONException e) {
      String message = Util.formatString("Failed to parse json response. (%s)", e.getMessage());
      logger.error(message);
      responseData.message = message;
    }

    if (jsonResponse == null) {
      return responseData;
    }

    responseData.jsonResponse = jsonResponse;

    String message = jsonResponse.optString("message", null);
    responseData.message = message;
    responseData.timestamp = jsonResponse.optString("timestamp", null);
    responseData.adid = jsonResponse.optString("adid", null);
    responseData.deviceId = jsonResponse.optString("deviceId", null);
    String trackingState = jsonResponse.optString("tracking_state", null);
    if (trackingState != null && trackingState.equals("opted_out")) {
      responseData.trackingState = TrackingState.OPTED_OUT;
    }

    if (message == null) {
      message = "No message found";
    }

    if (responseCode == HttpsURLConnection.HTTP_OK) {
      logger.info("%s", message);
      responseData.success = true;
    } else {
      logger.error("%s", message);
    }

    return responseData;
  }

  private static void buildPostData(Map<String, Object> body, int queueSize, FormBody.Builder builder) {
    for (Map.Entry<String, Object> entry : body.entrySet()) {
      builder.add(entry.getKey(), String.valueOf(entry.getValue()));
    }
    long now = System.currentTimeMillis();

    builder.add("st", String.valueOf(Long.valueOf(now / 1000)));
    if (queueSize > 0) {
      builder.add("queue_size", String.valueOf(queueSize));
    }
  }

  private static String getPostDataString(Map<String, Object> body, int queueSize) throws JSONException {

    JSONObject result = new JSONObject();

    for (Map.Entry<String, Object> entry : body.entrySet()) {
      result.put(entry.getKey(), entry.getValue());
    }

    long now = System.currentTimeMillis();

    result.put("st", Long.valueOf(now / 1000));

    if (queueSize > 0) {
      result.put("queue_size", queueSize);
    }

    return result.toString();
  }

  private static Uri buildUri(String path, Map<String, Object> parameters, String basePath) {
    Uri.Builder uriBuilder = new Uri.Builder();

    String scheme = Constants.SCHEME;
    String authority = Constants.AUTHORITY;
    String initialPath = "";

    try {
      String url = AdjustFactory.getBaseUrl();
      if (basePath != null) {
        url += basePath;
      }
      URL baseUrl = new URL(url);
      scheme = baseUrl.getProtocol();
      authority = baseUrl.getAuthority();
      initialPath = baseUrl.getPath();
    } catch (MalformedURLException e) {
      getLogger().error("Unable to parse endpoint (%s)", e.getMessage());
    }

    uriBuilder.scheme(scheme);
    uriBuilder.encodedAuthority(authority);
    uriBuilder.path(initialPath);
    uriBuilder.appendPath(path);

    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
      uriBuilder.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
    }

    long now = System.currentTimeMillis();
    uriBuilder.appendQueryParameter("st", String.valueOf(Long.valueOf(now / 1000)));

    Uri result = uriBuilder.build();

    getLogger().info("Result URI: %s", result.toString());
    return result;
  }

  private static String extractAppSecret(Map<String, Object> parameters) {
    return String.valueOf(parameters.remove("app_secret"));
  }

  private static String extractSecretId(Map<String, Object> parameters) {
    return String.valueOf(parameters.remove("secret_id"));
  }

  private static void extractEventCallbackId(Map<String, Object> parameters) {
    parameters.remove("event_callback_id");
  }

  private static String buildAuthorizationHeader(Map<String, Object> parameters,
                                                 String appSecret,
                                                 String secretId,
                                                 String activityKind) {
    // check if the secret exists and it's not empty
    if (appSecret == null || appSecret.length() == 0) {
      return null;
    }
    String appSecretName = "app_secret";

    Map<String, String> signatureDetails = getSignature(parameters, activityKind, appSecret);

    String signature = Util.sha256(String.valueOf(signatureDetails.get("clear_signature")));

    String signatureHeader = Util.formatString("signature=\"%s\"", signature);

    String authorizationHeader = Util.formatString("Signature %s", signatureHeader);
    getLogger().verbose("authorizationHeader: %s", authorizationHeader);

    return authorizationHeader;
  }

  private static Map<String, String> getSignature(
    final Map<String, Object> parameters,
    final String activityKind,
    final String appSecret) {
    String activityKindName = "activity_kind";
    String activityKindValue = activityKind;

    String createdAtName = "created_at";
    String createdAt = String.valueOf(parameters.get(createdAtName));

    String deviceIdentifierName = getValidIdentifier(parameters);
    String deviceIdentifier = String.valueOf(parameters.get(deviceIdentifierName));

    Map<String, String> signatureParams = new HashMap<String, String>();

    signatureParams.put("app_secret", appSecret);
    signatureParams.put(createdAtName, createdAt);
    signatureParams.put(activityKindName, activityKindValue);
    signatureParams.put(deviceIdentifierName, deviceIdentifier);

    String fields = "";
    String clearSignature = "";

    for (Map.Entry<String, String> entry : signatureParams.entrySet()) {
      if (entry.getValue() != null) {
        fields += entry.getKey() + " ";
        clearSignature += entry.getValue();
      }
    }

    // Remove last empty space.
    fields = fields.substring(0, fields.length() - 1);

    HashMap<String, String> signature = new HashMap<String, String>();

    signature.put("clear_signature", clearSignature);
    signature.put("fields", fields);

    return signature;
  }

  private static String getValidIdentifier(final Map<String, Object> parameters) {
    String googleAdIdName = "gps_adid";
    String fireAdIdName = "fire_adid";
    String androidIdName = "android_id";
    String macSha1Name = "mac_sha1";
    String macMd5Name = "mac_md5";
    String androidUUIDName = "android_uuid";

    if (parameters.get(googleAdIdName) != null) {
      return googleAdIdName;
    }
    if (parameters.get(fireAdIdName) != null) {
      return fireAdIdName;
    }
    if (parameters.get(androidIdName) != null) {
      return androidIdName;
    }
    if (parameters.get(macSha1Name) != null) {
      return macSha1Name;
    }
    if (parameters.get(macMd5Name) != null) {
      return macMd5Name;
    }
    if (parameters.get(androidUUIDName) != null) {
      return androidUUIDName;
    }

    return null;
  }
}