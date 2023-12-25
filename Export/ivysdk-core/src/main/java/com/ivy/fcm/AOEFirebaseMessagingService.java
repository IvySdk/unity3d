package com.ivy.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ivy.IvySdk;
import com.ivy.util.Logger;

import java.util.Map;


/**
 * NOTE: There can only be one service in each app that receives FCM messages. If multiple
 * are declared in the Manifest then the first one will be chosen.
 * <p>
 * In order to make this Java sample functional, you must remove the following from the Kotlin messaging
 * service in the AndroidManifest.xml:
 * <p>
 * <intent-filter>
 * <action android:name="com.google.firebase.MESSAGING_EVENT" />
 * </intent-filter>
 */
public class AOEFirebaseMessagingService extends FirebaseMessagingService {

  private static final String TAG = "MyFirebaseMsgService";

  /**
   * Called when message is received.
   *
   * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
   */
  // [START receive_message]
  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    Log.w(TAG, "onMessageReceived >>>>> ");
    super.onMessageReceived(remoteMessage);
    // [START_EXCLUDE]
    // There are two types of messages data messages and notification messages. Data messages
    // are handled
    // here in onMessageReceived whether the app is in the foreground or background. Data
    // messages are the type
    // traditionally used with GCM. Notification messages are only received here in
    // onMessageReceived when the app
    // is in the foreground. When the app is in the background an automatically generated
    // notification is displayed.
    // When the user taps on the notification they are returned to the app. Messages
    // containing both notification
    // and data payloads are treated as notification messages. The Firebase console always
    // sends notification
    // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
    // [END_EXCLUDE]
    if (remoteMessage == null) {
      return;
    }

    try {
      // TODO(developer): Handle FCM messages here.
      // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
      Log.d(TAG, "From: " + remoteMessage.getFrom());

      // Check if message contains a data payload.
      Map<String, String> data = remoteMessage.getData();
      if (data != null && data.size() > 0) {
        Logger.debug(TAG, "Message data payload: " + remoteMessage.getData());
      } else if (remoteMessage.getNotification() != null) {
        Logger.debug(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
      }
    } catch (Throwable t) {
      Logger.debug(TAG, "Push data exception", t);
    }

    // Also if you intend on generating your own notifications as a result of a received FCM
    // message, here is where that should be initiated. See sendNotification method below.
  }
  // [END receive_message]


  // [START on_new_token]

  /**
   * Called if InstanceID token is updated. This may occur if the security of
   * the previous token had been compromised. Note that this is called when the InstanceID token
   * is initially generated so this is where you would retrieve the token.
   */
  @Override
  public void onNewToken(String token) {
    Logger.debug(TAG, "Refreshed token: " + token);

    // If you want to send messages to this application instance or
    // manage this apps subscriptions on the server side, send the
    // Instance ID token to your app server.
    sendRegistrationToServer(token);
  }
  // [END on_new_token]

  /**
   * Schedule async work using WorkManager.
   */
  private void scheduleJob() {
    // [START dispatch_job]
//    OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(AOEFirebaseWorker.class)
//      .build();
//    WorkManager.getInstance().beginWith(work).enqueue();
    // [END dispatch_job]
  }

  /**
   * Handle time allotted to BroadcastReceivers.
   */
  private void handleNow() {
    Log.d(TAG, "Short lived task is done.");
  }

  /**
   * Persist token to third-party servers.
   * <p>
   * Modify this method to associate the user's FCM InstanceID token with any server-side account
   * maintained by your application.
   *
   * @param token The new token.
   */
  private void sendRegistrationToServer(String token) {
    // TODO: Implement this method to send token to your app server.
    try {
      if (token != null && IvySdk.getActivity() != null) {

      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


}