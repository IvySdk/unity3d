package com.ivy.facebook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.android.client.ShareResultListener;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.ivy.IvySdk;
import com.ivy.ads.events.EventID;
import com.ivy.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class FacebookUserManager {
  private static final String TAG = "Facebook";
  private FacebookLoginListener listener;
  private boolean init = true;
  private CallbackManager callbackManager;
  private String friends;
  private String me;

  public static final String PERMISSION_GET_FRIENDS = "user_friends";
  public static final String PERMISSION_GET_PROFILE = "public_profile";
  public static final String PERMISSION_GET_EMAIL = "email";

  private boolean requireFriends;

  private ShareDialog shareDialog = null;


  public FacebookUserManager() {
    this.requireFriends = IvySdk.getGridConfigBoolean("requireFriends", false);
    callbackManager = CallbackManager.Factory.create();
  }

  public String getUserId() {
    Profile profile = Profile.getCurrentProfile();

    if (profile != null) {
      return profile.getId();
    }
    return "";
  }
  private void updateMe() {
    Profile profile = Profile.getCurrentProfile();

    if (profile != null) {
      String me = "{\"id\":\"%s\", \"name\":\"%s\", \"picture\":\"%s\"}";
      Uri profilePictureUri = profile.getProfilePictureUri(128, 128);
      this.me = String.format(me, profile.getId(), profile.getName(), profilePictureUri.toString());
    } else {
      Logger.error(TAG, "Facebook profile is null");
      this.me = null;
    }

    Logger.debug(TAG, "Update facebook me to " + (this.me != null ? this.me.toString() : " null"));
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (callbackManager != null) {
      callbackManager.onActivityResult(requestCode, resultCode, data);
    }
  }

  public void logout(Activity activity) {
    LoginManager.getInstance().logOut();
  }

  private boolean checkAlreadyLogin() {
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    if (accessToken != null && !accessToken.isExpired()) {
      return true;
    }
    return false;
  }
  public void login(Activity context, FacebookLoginListener loginListener) {
    this.listener = loginListener;

    if (checkAlreadyLogin()) {
     if (listener != null) {
       listener.onReceiveLoginResult(true);
     }
     return;
    }

    LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override
        public void onSuccess(LoginResult loginResult) {
        if (loginResult == null) {
          Log.e(TAG, "Facebook login success, but loginResult null");
          return;
        }
        Logger.debug(TAG, "Facebook login success" + loginResult.toString());
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (Profile.getCurrentProfile() == null) {
          Logger.debug(TAG, "Get facebook info");
          Utility.getGraphMeRequestWithCacheAsync(accessToken.getToken(),
            new Utility.GraphMeRequestWithCacheCallback() {
              @Override
              public void onSuccess(JSONObject userInfo) {
                Logger.debug(TAG, "Facebook userinfo: " + userInfo.toString());
                String id = userInfo.optString("id", "");
                if ("".equals(id)) {
                  Logger.error(TAG, "facebook id is null");
                  return;
                }
                String link = userInfo.optString("link");
                Profile profile = new Profile(
                  id,
                  userInfo.optString("first_name"),
                  userInfo.optString("middle_name"),
                  userInfo.optString("last_name"),
                  userInfo.optString("name"),
                  link != null ? Uri.parse(link) : null
                );
                Profile.setCurrentProfile(profile);
                friends = null;
                updateMe();
                if (listener != null) {
                  listener.onReceiveLoginResult(true);
                }
                if (!requestFriends()) {
                  if (listener != null) {
                    listener.onReceiveFriends("[]");
                  }
                }
              }

              @Override
              public void onFailure(FacebookException error) {
                friends = null;
                if (listener != null) {
                  listener.onReceiveLoginResult(false);
                }
              }
            });
        } else {
          Logger.debug(TAG, "Already signed in");
          updateMe();
          if (listener != null) {
            listener.onReceiveLoginResult(true);
          }
          if (!requestFriends()) {
            if (listener != null) {
              listener.onReceiveFriends("[]");
            }
          }
        }
      }

      @Override
      public void onCancel() {
        AccessToken.setCurrentAccessToken(null);
        listener.onReceiveLoginResult(false);
      }

      @Override
      public void onError(FacebookException error) {
        if (error != null) {
          IvySdk.showToast(error.getMessage());
        }
        AccessToken.setCurrentAccessToken(null);
        listener.onReceiveLoginResult(false);
      }
    });

    List<String> permissions = requireFriends ? Arrays.asList("public_profile", "email", "user_friends") : Arrays.asList("public_profile", "email");
    LoginManager.getInstance().logInWithReadPermissions(context, permissions);
  }

  public boolean isLogin() {
    try {
      boolean isInitialized = FacebookSdk.isInitialized();
      if (isInitialized) {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        return currentAccessToken != null && !currentAccessToken.isExpired();
      }
    } catch(Throwable t) {
    }
    return false;
  }

  public void logout() {
    LoginManager instance = LoginManager.getInstance();
    try {
      instance.logOut();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void share(Activity activity, String shareUrl, String quote, String hashtag, ShareResultListener shareResultListener) {
    ShareLinkContent.Builder builder = new ShareLinkContent.Builder().setContentUrl(Uri.parse(shareUrl));
    if (quote != null && !"".equals(quote)) {
      builder.setQuote(quote);
    }
    if (hashtag != null && !"".equals(hashtag)) {
      builder.setShareHashtag(new ShareHashtag.Builder().setHashtag(hashtag).build());
    }

    ShareLinkContent content = builder.build();
    if (shareDialog == null) {
      shareDialog = new ShareDialog(activity);
    }
    // this part is optional
    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
      @Override
      public void onSuccess(Sharer.Result result) {
//        Bundle bundle = new Bundle();
        if (shareResultListener != null && result != null) {
          String postId = result.getPostId();
//          if (postId != null) {
//            bundle.putString("label", postId);
//          }
          shareResultListener.onSuccess(postId);
        }
//        IvySdk.logEvent(EventID.FB_SHARE, bundle);
      }

      @Override
      public void onCancel() {
        if (shareResultListener != null) {
          shareResultListener.onCancel();
        }
      }

      @Override
      public void onError(FacebookException error) {
        if (shareResultListener != null) {
          shareResultListener.onError(error != null ? error.getMessage() : "empty");
        }
      }
    });
    shareDialog.show(content);
  }

  public String me() {
    if (me == null) {
      Logger.debug(TAG, "Facebook me() is null, will update");
      updateMe();
    }
    return me == null ? "{}" : me;
  }

  private boolean requestFriends() {
    try {
      if (friends == null && requireFriends) {
        Logger.debug(TAG, "request Friends");
        String[] requiredFields = new String[]{
          "id",
          "name",
          "picture.height(128).width(128)"
        };
        try {
          GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), "me/friends");

          Bundle parameters = request.getParameters();
          parameters.putString("fields", TextUtils.join(",", requiredFields));
          request.setParameters(parameters);
          request.setCallback(new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
              Logger.debug(TAG, "Request Friends completed");
              onReceiveFriends(response);
            }
          });
          request.executeAsync();
          return true;
        } catch (Exception | Error e) {
          e.printStackTrace();
          if (listener != null) {
            listener.onReceiveFriends("[]");
          }
          return true;
        }
      } else {
        return false;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

  private void onReceiveFriends(GraphResponse response) {
    try {
      FacebookRequestError requestError = response.getError();
      FacebookException exception = (requestError == null) ? null : requestError.getException();
      if (response.getJSONObject() == null && exception == null) {
        exception = new FacebookException("GraphObjectPagingLoader received neither a result nor an error.");
      }

      if (exception == null) {
        JSONArray data = response.getJSONObject().optJSONArray("data");
        boolean haveData = data.length() > 0;

        if (haveData) {
          for (int i = data.length() - 1; i >= 0; --i) {
            try {
              JSONObject friend = data.getJSONObject(i);
              JSONObject url = friend.getJSONObject("picture");
              String url_ = url.getJSONObject("data").getString("url");
//                        String cacheName = downloadProfilePicture(url);
              friend.put("picture", url_);
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
          friends = data.toString();
          if (listener != null) {
            listener.onReceiveFriends(friends);
          }
          Logger.debug(TAG, "ufb#friends " + friends);
        } else {
          Logger.debug(TAG, "ufb#friends 0");
          friends = "[]";
          if (listener != null) {
            listener.onReceiveFriends("[]");
          }
        }
      } else {
        exception.printStackTrace();
        if (listener != null) {
          listener.onReceiveFriends("[]");
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String friends(FacebookLoginListener loginListener) {
    this.listener = loginListener;
    if (friends == null) {
      requestFriends();
      return "[]";
    } else {
      return friends;
    }
  }


  public void playerFinder(final Activity activity) {
  }
}
