package com.ivy.firestore;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.android.client.AndroidSdk;
import com.android.client.DatabaseChangedListener;
import com.android.client.DatabaseConnectListener;
import com.android.client.DatabaseConnectListener2;
import com.android.client.DatabaseListener;
import com.android.client.FirebaseAuthError;
import com.android.client.OnPasswordChangedListener;
import com.android.client.OnResultListener;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.ivy.IvySdk;
import com.ivy.util.Logger;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FirestoreAdapter {
    private static final String TAG = "Firestore";
    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = null;
    public static FirestoreAdapter INSTANCE = null;
    public static FirestoreAdapter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirestoreAdapter();
        }
        return INSTANCE;
    }
    private String getFirebaseUserId() {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        return currentUser != null ? currentUser.getUid() : null;
    }
    private FirestoreAdapter() {
        this.db = FirebaseFirestore.getInstance();
    }
    public void snapshot(String collection, String documentId, final DatabaseChangedListener listener) {
        try {
            final DocumentReference docRef = db.collection(collection).document(documentId);
            docRef.addSnapshotListener((snapshot, e) -> {
                if (e != null) {
                    Logger.warning(TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Logger.debug(TAG, "Current data: " + snapshot.getData());
                    if (listener != null) {
                        Map<String, Object> resultData = snapshot.getData();
                        if (resultData != null && resultData.size() > 0) {
                            listener.onData(collection, JSON.toJSONString(resultData));
                        }
                    }
                } else {
                    Logger.debug(TAG, "Current data: null");
                }
            });
        } catch (Throwable t) {
            // ignore
            Logger.error(TAG, "snap exception", t);
        }
    }
    public void snapshot(String collection, final DatabaseChangedListener listener) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null || this.db == null) {
            return;
        }
        this.snapshot(collection, firebaseUserId, listener);
    }
    public void query(String collection, DatabaseListener listener) {
        try {
            this.db.collection(collection).get().addOnSuccessListener(queryDocumentSnapshots -> {
                if (queryDocumentSnapshots == null) {
                    if (listener != null) {
                        listener.onData(collection, "{}");
                    }
                    return;
                }
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                List<Map<String, Object>> datas = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : documentSnapshotList) {
                    datas.add(documentSnapshot.getData());
                }
                String resultString = JSON.toJSONString(datas);
                if (listener != null) {
                    listener.onData(collection, resultString);
                }
            }).addOnFailureListener(e -> {
                if (listener != null) {
                    listener.onFail(collection);
                }
            });
        } catch (Throwable t) {
            // ignore
        }
    }
    public void read(String collection, final DatabaseListener listener) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null || this.db == null) {
            if (listener != null) {
                listener.onFail(collection);
            }
            return;
        }
        this.read(collection, firebaseUserId, listener);
    }
    public void read(String collection, String documentId, final DatabaseListener listener) {
        Logger.debug(TAG, "Firestore read " + collection + ", document: " + documentId);
        try {
            DocumentReference documentReference = this.db.collection(collection).document(documentId);
            documentReference.get().addOnSuccessListener(documentSnapshot -> {
                Logger.debug(TAG, "Firestore read success");
                if (documentSnapshot == null || !documentSnapshot.exists()) {
                    if (listener != null) {
                        listener.onData(collection, "{}");
                    }
                    return;
                }
                Map<String, Object> result = documentSnapshot.getData();
                if (result != null) {
                    String resultString = JSON.toJSONString(result);
                    if (listener != null) {
                        listener.onData(collection, resultString);
                    }
                } else {
                    if (listener != null) {
                        listener.onData(collection, "{}");
                    }
                }
            }).addOnFailureListener(e -> {
                Logger.error(TAG, "Firestore read exception", e);
                if (listener != null) {
                    listener.onFail(collection);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public void merge(String collection, String jsonString, final DatabaseListener listener) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null || this.db == null) {
            if (listener != null) {
                listener.onFail(collection);
            }
            return;
        }
        try {
            Logger.debug(TAG, "Firestore merge " + collection + ", document: " + firebaseUserId);
            int disableBigDecimal = JSON.DEFAULT_PARSER_FEATURE & ~Feature.UseBigDecimal.mask;
            JSONObject jsonObject = JSON.parseObject(jsonString, JSONObject.class, disableBigDecimal);
            Map<String, Object> data = (Map<String, Object>) jsonObject;
            this.db.collection(collection).document(firebaseUserId).set(data, SetOptions.merge()).addOnSuccessListener(aVoid -> {
                if (listener != null) {
                    listener.onSuccess(collection);
                }
            }).addOnFailureListener(e -> {
                if (listener != null) {
                    listener.onFail(collection);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void set(String collection, String jsonString, final DatabaseListener listener) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null || this.db == null) {
            if (listener != null) {
                listener.onFail(collection);
            }
            return;
        }
        try {
            Logger.debug(TAG, "Firestore set " + collection + ", document: " + firebaseUserId);
            int disableBigDecimal = JSON.DEFAULT_PARSER_FEATURE & ~Feature.UseBigDecimal.mask;
            JSONObject jsonObject = JSON.parseObject(jsonString, JSONObject.class, disableBigDecimal);
            Map<String, Object> data = (Map<String, Object>) jsonObject;
            this.db.collection(collection).document(firebaseUserId).set(data).addOnSuccessListener(aVoid -> {
                if (listener != null) {
                    listener.onSuccess(collection);
                }
            }).addOnFailureListener(e -> {
                if (listener != null) {
                    listener.onFail(collection);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public void update(String collection, String jsonString, final DatabaseListener listener) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null || this.db == null) {
            if (listener != null) {
                listener.onFail(collection);
            }
            return;
        }
        try {
            Logger.debug(TAG, "Firestore update " + collection + ", document: " + firebaseUserId);
            int disableBigDecimal = JSON.DEFAULT_PARSER_FEATURE & ~Feature.UseBigDecimal.mask;
            JSONObject jsonObject = JSON.parseObject(jsonString, JSONObject.class, disableBigDecimal);
            Map<String, Object> data = (Map<String, Object>) jsonObject;
            this.db.collection(collection).document(firebaseUserId).update(data).addOnSuccessListener(aVoid -> {
                Logger.debug(TAG, "Firestore update success");
                if (listener != null) {
                    listener.onSuccess(collection);
                }
            }).addOnFailureListener(e -> {
                Logger.error(TAG, "Firestore update exception", e);
                if (listener != null) {
                    listener.onFail(collection);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public void delete(String collection, final DatabaseListener listener) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null || this.db == null) {
            if (listener != null) {
                listener.onFail(collection);
            }
            return;
        }
        try {
            Logger.debug(TAG, "Firestore delete " + collection + ", document: " + firebaseUserId);
            this.db.collection(collection).document(firebaseUserId).delete().addOnSuccessListener(aVoid -> {
                Logger.debug(TAG, "Firestore delete success");
                if (listener != null) {
                    listener.onSuccess(collection);
                }
            }).addOnFailureListener(e -> {
                Logger.error(TAG, "Firestore delete exception", e);
                if (listener != null) {
                    listener.onFail(collection);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void initializeAfterSignIn(String provider, DatabaseConnectListener databaseConnectListener) {
        Log.e("ADSFALL", "start initializeAfterSignIn::" + provider);
        if (provider == null || "".equals(provider) || "play".equals(provider)) {
            initializeAfterSignInPlay(databaseConnectListener);
        } else if ("anonymously".equals(provider)) {
            initializeAfterSignInAnonymously(databaseConnectListener);
        } else if ("facebook".equals(provider)) {
            initializeAfterSignInFacebook(databaseConnectListener);
        } else if ("email".equals(provider)) {
            initializeAfterSignInEmail(databaseConnectListener);
        }
    }

    public void initializeAfterSignInPlayGames(DatabaseConnectListener2 databaseConnectListener) {
        try {
            Log.e("ADSFALL", "start initializeAfterSignInPlay");
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(IvySdk.getActivity());
            if (acct == null) {
                Log.e("ADSFALL", "GoogleSignIn account null");
                if (databaseConnectListener != null) {
                    databaseConnectListener.onFail(FirebaseAuthError.ERROR_INVALID_CREDENTIAL.name(), FirebaseAuthError.ERROR_INVALID_CREDENTIAL.getDescription());
                }
                return;
            }
            Logger.debug(TAG, "firebaseAuthWithPlayGames:" + acct.getServerAuthCode());
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null && currentUser.isAnonymous()) {
                auth.signOut();
                Log.e("ADSFALL", "pre log FirebaseUser logout");
            }
            String serverAuthCode = acct.getServerAuthCode();
            if (serverAuthCode == null || "".equals(serverAuthCode)) {
                Logger.warning(TAG, "Not got server auth Code");
                Log.e("ADSFALL", "Not got server auth Code");
                if (databaseConnectListener != null) {
                    databaseConnectListener.onFail(FirebaseAuthError.ERROR_INVALID_CREDENTIAL.name(), FirebaseAuthError.ERROR_INVALID_CREDENTIAL.getDescription());
                }
                return;
            }
            AuthCredential credential = PlayGamesAuthProvider.getCredential(serverAuthCode);
            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "signInWithCredential:success");
                    Log.e("ADSFALL", "signInWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                    Log.e("ADSFALL", "signInWithCredential:failure::" + task.getException());
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail(FirebaseAuthError.ERROR_INVALID_CREDENTIAL.name(), FirebaseAuthError.ERROR_INVALID_CREDENTIAL.getDescription());
                    }
                }
            });
        } catch (Throwable t) {
            Log.e("ADSFALL", " initializeAfterSignInPlay error::" + t.getMessage());
            t.printStackTrace();
        }
    }

    public void initializeAfterSignInFacebook2(@NonNull DatabaseConnectListener2 databaseConnectListener) {
        try {
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken == null) {
                if (databaseConnectListener != null) {
                    databaseConnectListener.onFail(FirebaseAuthError.ERROR_INVALID_CREDENTIAL.name(), FirebaseAuthError.ERROR_INVALID_CREDENTIAL.getDescription());
                }
                return;
            }
            if (currentUser != null && currentUser.isAnonymous()) {
                auth.signOut();
            }
            AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "signInWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Exception exception = task.getException();
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                    if (exception != null) {
                        IvySdk.showToast(exception.getMessage());
                    }
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail(FirebaseAuthError.ERROR_INVALID_CREDENTIAL.name(), FirebaseAuthError.ERROR_INVALID_CREDENTIAL.getDescription());
                    }
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public void initializeAndLinkGoogleAfterSignIn(DatabaseConnectListener databaseConnectListener) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(IvySdk.getActivity());
        String serverAuthCode = acct.getServerAuthCode();
        if (serverAuthCode == null || "".equals(serverAuthCode)) {
            Logger.warning(TAG, "Not got server auth Code");
            if (databaseConnectListener != null) {
                databaseConnectListener.onFail();
            }
            return;
        }
        AuthCredential credential = PlayGamesAuthProvider.getCredential(serverAuthCode);
        Log.e("Firestore", "google-credential provider:" + credential.getProvider());
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Logger.debug(TAG, "signInWithCredential:success");
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onSuccess();
                            }
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Exception exception = task.getException();
                        Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                        if (exception != null) {
                            IvySdk.showToast(exception.getMessage());
                        }
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onFail();
                        }
                    }
                }
            });
        } else {
            // check the
            List<? extends UserInfo> providerData = currentUser.getProviderData();
            for (UserInfo userInfo : providerData) {
                if (userInfo != null) {
                    Log.e("Firestore", String.format("google--providerId:%s", userInfo.getProviderId()));
                    if ("playgames.google.com".equals(userInfo.getProviderId())) {
                        // already linked, just signed in
                        String uid = userInfo.getUid();
                        String googleId = acct.getId();
                        if (googleId != null && googleId.equals(uid)) {
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onSuccess();
                            }
                            return;
                        }
                    }
                }
            }
            currentUser.linkWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "linkWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    Exception exception = task.getException();
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "linkWithCredential:failure", exception);
                    if (exception != null) {
                        Log.e("Firestore", "google-linkWithCredential:failure:" + exception.getMessage());
                        String message = exception.getMessage();
                        if (message != null && message.contains("already been linked")) {
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onAccountLinkFail();
                            }
                            return;
                        } else {
                            IvySdk.showToast(exception.getMessage());
                        }
                    }
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        }
    }
    /**
     * 玩家已经登入facebook账号，将账号链接到google
     *
     * @param databaseConnectListener
     */
    public void initializeAndLinkGoogleAfterSignInFacebook(DatabaseConnectListener databaseConnectListener) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(IvySdk.getActivity());
        String serverAuthCode = acct.getServerAuthCode();
        if (serverAuthCode == null || "".equals(serverAuthCode)) {
            Logger.warning(TAG, "Not got server auth Code");
            if (databaseConnectListener != null) {
                databaseConnectListener.onFail();
            }
            return;
        }
        AuthCredential credential = PlayGamesAuthProvider.getCredential(serverAuthCode);
        Log.e("Firestore", "google-credential provider:" + credential.getProvider());
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Logger.debug(TAG, "signInWithCredential:success");
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onSuccess();
                            }
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Exception exception = task.getException();
                        Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                        if (exception != null) {
                            IvySdk.showToast(exception.getMessage());
                        }
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onFail();
                        }
                    }
                }
            });
        } else {
            // check the
            List<? extends UserInfo> providerData = currentUser.getProviderData();
            for (UserInfo userInfo : providerData) {
                if (userInfo != null) {
                    Log.e("Firestore", String.format("google--providerId:%s", userInfo.getProviderId()));
                    if ("playgames.google.com".equals(userInfo.getProviderId())) {
                        String uid = userInfo.getUid();
                        String facebookId = AndroidSdk.getFacebookUserId();
                        Log.e("Firestore", String.format("google--providerId:%s;uid:%s;fbId:%s", userInfo.getProviderId(), uid, facebookId));
                        if (facebookId != null && facebookId.equals(uid)) {
                            // already linked, just signed in
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onSuccess();
                            }
                            return;
                        }
                    }
                }
            }
            currentUser.linkWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "linkWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    Exception exception = task.getException();
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "linkWithCredential:failure", exception);
                    if (exception != null) {
                        Log.e("Firestore", "google-linkWithCredential:failure:" + exception.getMessage());
                        String message = exception.getMessage();
                        if (message != null && message.contains("already been linked")) {
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onAccountLinkFail();
                            }
                            return;
                        } else {
                            IvySdk.showToast(exception.getMessage());
                        }
                    }
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        }
    }
    /**
     * 初始化并关联到facebook
     * 1. 如果当前有用户，将当前用户直接link到此facebook账号
     */
    public void initializeAndLinkFacebookAfterSignIn(DatabaseConnectListener databaseConnectListener) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            Logger.debug(TAG, "accessToken is empty");
            if (databaseConnectListener != null) {
                databaseConnectListener.onFail();
            }
            return;
        }
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        if (currentUser == null) {
            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "signInWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Exception exception = task.getException();
                    Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                    if (exception != null) {
                        IvySdk.showToast(exception.getMessage());
                    }
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        } else {
            // check the
            List<? extends UserInfo> providerData = currentUser.getProviderData();
            for (UserInfo userInfo : providerData) {
                if (userInfo != null) {
                    if ("facebook.com".equals(userInfo.getProviderId())) {
                        String uid = userInfo.getUid();
                        String facebookId = AndroidSdk.getFacebookUserId();
                        if (facebookId != null && facebookId.equals(uid)) {
                            // already linked, just signed in
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onSuccess();
                            }
                            return;
                        }
                    }
                }
            }
            currentUser.linkWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "linkWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    Exception exception = task.getException();
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "linkWithCredential:failure", exception);
                    if (exception != null) {
                        String message = exception.getMessage();
                        if (message != null && message.contains("already associated")) {
                            if (databaseConnectListener != null) {
                                databaseConnectListener.onAccountLinkFail();
                            }
                            return;
                        } else {
                            IvySdk.showToast(exception.getMessage());
                        }
                    }
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        }
    }
    public void initializeAfterSignInFacebook(DatabaseConnectListener databaseConnectListener) {
        try {
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken == null) {
                if (databaseConnectListener != null) {
                    databaseConnectListener.onFail();
                }
                return;
            }
            if (currentUser != null && currentUser.isAnonymous()) {
                auth.signOut();
            }
            AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "signInWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Exception exception = task.getException();
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                    if (exception != null) {
                        IvySdk.showToast(exception.getMessage());
                    }
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public void signOutFirestore() {
        try {
//      try {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = auth.getCurrentUser();
//        List<? extends UserInfo> data = currentUser.getProviderData();
//        for (UserInfo userInfo : data) {
//          try {
//            currentUser.unlink(userInfo.getProviderId());
//          } catch (Exception e) {
//
//          }
//        }
//        currentUser.sendEmailVerification();
//      } catch (Exception e){
//
//      }
            FirebaseAuth.getInstance().signOut();
        } catch (Throwable t) {
            Logger.error(TAG, "SignOut exception", t);
        }
    }

    private void initializeAfterSignInEmail(DatabaseConnectListener databaseConnectListener) {
        try {
            Log.e("ADSFALL", "start initializeAfterSignInEmail" );
            Logger.debug(TAG, "firebaseAuthWithEmail");
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null) {
                Log.e("ADSFALL", "firebaseAuthWithEmail current user is already present, ignore" );
                Logger.warning(TAG, "firebaseAuthWithEmail current user is already present, ignore");
                if (databaseConnectListener != null) {
                    databaseConnectListener.onSuccess();
                }
                return;
            }
            auth.createUserWithEmailAndPassword(IvySdk.getUUID() + "@XX.com", IvySdk.getUUID())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.e("ADSFALL", "log with email::success" );
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                Log.e("ADSFALL", "log with email::success::user::" + user.getUid());
                                if (databaseConnectListener != null) {
                                    databaseConnectListener.onSuccess();
                                }
                            } else {
                                Log.e("ADSFALL", "log with email::success::user::null");
                            }
                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                Log.e("ADSFALL", "log with email::fail::" + e.getMessage());
                            } else {
                                Log.e("ADSFALL", "log with email::fail::null");
                            }
                            try {
                                auth.signInWithEmailAndPassword(IvySdk.getUUID() + "@XX.com", IvySdk.getUUID())
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Log.e("ADSFALL", "sign with email::success::" );
                                                FirebaseUser user = auth.getCurrentUser();
                                                if (user != null) {
                                                    Log.e("ADSFALL", "sign with email::success::user::" + user.getUid() );
                                                    if (databaseConnectListener != null) {
                                                        databaseConnectListener.onSuccess();
                                                    }
                                                }
                                            } else {
                                                Exception e1 = task1.getException();
                                                if (e1 != null) {
                                                    Log.e("ADSFALL", "sign with email::fail::" + e1.getMessage());
                                                } else {
                                                    Log.e("ADSFALL", "sign with email::fail::null");
                                                }
                                                if (databaseConnectListener != null) {
                                                    databaseConnectListener.onFail();
                                                }
                                            }
                                        });
                            } catch (Exception e2) {
                                if (databaseConnectListener != null) {
                                    databaseConnectListener.onFail();
                                }
                                Log.e("ADSFALL", "sign with email error::" + e2.getMessage());
                            }
                        }
                    });
        } catch (Throwable t) {
            Log.e("ADSFALL", "email log error::" + t.getMessage());
            t.printStackTrace();
        }
    }
    /**
     * 匿名登陆
     *
     * @param databaseConnectListener
     */
    private void initializeAfterSignInAnonymously(DatabaseConnectListener databaseConnectListener) {
        try {
            Logger.debug(TAG, "firebaseAuthWithPlayGames Anonymously");
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null) {
                Logger.warning(TAG, "initializeAfterSignInAnonymously current user is already present, ignore");
                if (databaseConnectListener != null) {
                    databaseConnectListener.onSuccess();
                }
                return;
            }
            auth.signInAnonymously().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "signInAnonymously:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "signInAnonymously:failure", task.getException());
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    // Call this both in the silent sign-in task's OnCompleteListener and in the
    // Activity's onActivityResult handler.
    private void initializeAfterSignInPlay(DatabaseConnectListener databaseConnectListener) {
        try {
            Log.e("ADSFALL", "start initializeAfterSignInPlay");
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(IvySdk.getActivity());
            if (acct == null) {
                Log.e("ADSFALL", "GoogleSignIn account null");
                if (databaseConnectListener != null) {
                    databaseConnectListener.onFail();
                }
                return;
            }
            Logger.debug(TAG, "firebaseAuthWithPlayGames:" + acct.getServerAuthCode());
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null && currentUser.isAnonymous()) {
                auth.signOut();
                Log.e("ADSFALL", "pre log FirebaseUser logout");
            }
            String serverAuthCode = acct.getServerAuthCode();
            if (serverAuthCode == null || "".equals(serverAuthCode)) {
                Logger.warning(TAG, "Not got server auth Code");
                Log.e("ADSFALL", "Not got server auth Code");
                if (databaseConnectListener != null) {
                    databaseConnectListener.onFail();
                }
                return;
            }
            AuthCredential credential = PlayGamesAuthProvider.getCredential(serverAuthCode);
            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.debug(TAG, "signInWithCredential:success");
                    Log.e("ADSFALL", "signInWithCredential:success");
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        if (databaseConnectListener != null) {
                            databaseConnectListener.onSuccess();
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                    Log.e("ADSFALL", "signInWithCredential:failure::" + task.getException());
                    if (databaseConnectListener != null) {
                        databaseConnectListener.onFail();
                    }
                }
            });
        } catch (Throwable t) {
            Log.e("ADSFALL", " initializeAfterSignInPlay error::" + t.getMessage());
            t.printStackTrace();
        }
    }

    public void signInWithEmailAndPassword(@NonNull final String email, @NonNull final String password, @NonNull final DatabaseConnectListener2 databaseConnectListener) {
        signInWithEmailAndPassword(email, password, databaseConnectListener, true);
    }

    private void signInWithEmailAndPassword(@NonNull final String email, @NonNull final String password, @NonNull final DatabaseConnectListener2 databaseConnectListener, final boolean reauthentication) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Logger.debug("Firestore", "signInWithEmail and password: " + email + ", password");
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        databaseConnectListener.onSuccess();
                    } else {
                        FirebaseAuthError firebaseAuthError = FirebaseAuthError.fromException(task.getException());
                        if (reauthentication && FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.equals(firebaseAuthError)) {
                            reauthentication(new OnResultListener() {
                                @Override
                                public void onSuccess() {
                                    signInWithEmailAndPassword(email, password, databaseConnectListener, false);
                                }

                                @Override
                                public void onError() {
                                    databaseConnectListener.onFail(FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.name(), FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.getDescription());
                                }
                            });
                        } else {
                            if (FirebaseAuthError.ERROR_USER_NOT_FOUND.equals(firebaseAuthError)) {
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        databaseConnectListener.onSuccess();
                                                    } else {
                                                        FirebaseAuthError err = FirebaseAuthError.fromException(task.getException());
                                                        databaseConnectListener.onFail(err.name(), err.getDescription());
                                                    }
                                                }
                                            });
                                        } else {
                                            FirebaseAuthError err = FirebaseAuthError.fromException(task.getException());
                                            databaseConnectListener.onFail(err.name(), err.getDescription());
                                        }
                                    }
                                });
                            } else {
                                databaseConnectListener.onFail(firebaseAuthError.name(), firebaseAuthError.getDescription());
                            }
                        }
                    }
                }
            });
        } else {
            Logger.debug("Firestore", "Already have signed user, try to link the data into that account");
            currentUser.linkWithCredential(EmailAuthProvider.getCredential(email, password)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            databaseConnectListener.onSuccess();
                        }
                    } else {
                        try {
                            FirebaseAuthError firebaseAuthError = FirebaseAuthError.fromException(task.getException());
                            if (reauthentication && FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.equals(firebaseAuthError)) {
                                reauthentication(new OnResultListener() {
                                    @Override
                                    public void onSuccess() {
                                        signInWithEmailAndPassword(email, password, databaseConnectListener, false);
                                    }

                                    @Override
                                    public void onError() {
                                        String str = FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.name();
                                        databaseConnectListener.onFail(str, FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.getDescription());
                                    }
                                });
                            } else {
                                if (FirebaseAuthError.ERROR_USER_NOT_FOUND.equals(firebaseAuthError)) {
                                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                AuthCredential credential = EmailAuthProvider.getCredential(email, password);
                                                currentUser.linkWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            databaseConnectListener.onSuccess();
                                                        } else {
                                                            FirebaseAuthError err = FirebaseAuthError.fromException(task.getException());
                                                            databaseConnectListener.onFail(err.name(), err.getDescription());
                                                        }
                                                    }
                                                });
                                            } else {
                                                FirebaseAuthError error = FirebaseAuthError.fromException(task.getException());
                                                databaseConnectListener.onFail(error.name(), error.getDescription());
                                            }
                                        }
                                    });
                                } else {
                                    databaseConnectListener.onFail(firebaseAuthError.name(), firebaseAuthError.getDescription());
                                }
                            }
                        } catch (Exception e) {
                            databaseConnectListener.onFail(FirebaseAuthError.ERROR_UNKNOWN.name(), FirebaseAuthError.ERROR_UNKNOWN.getDescription());
                        }
                    }
                }
            });
        }
    }

    private void reauthentication(@NonNull OnResultListener onResultListener) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            onResultListener.onError();
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        List<? extends UserInfo> userInfos = firebaseUser.getProviderData();
        for (UserInfo userInfo : userInfos) {
            if (userInfo != null && !TextUtils.isEmpty(userInfo.getProviderId())) {
                arrayList.add(userInfo.getProviderId());
            }
        }
        if (arrayList.contains("playgames.google.com")) {
            String serverAuthCode = null;
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(IvySdk.getActivity());
            if (acct != null) {
                serverAuthCode = acct.getServerAuthCode();
            }
            if (TextUtils.isEmpty(serverAuthCode)) {
                onResultListener.onError();
                return;
            }
            firebaseUser.reauthenticate(PlayGamesAuthProvider.getCredential(serverAuthCode)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        onResultListener.onSuccess();
                    } else {
                        onResultListener.onError();
                    }
                }
            });
        } else if (arrayList.contains("facebook.com")) {
            try {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                firebaseUser.reauthenticate(FacebookAuthProvider.getCredential(accessToken.getToken())).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            onResultListener.onSuccess();
                        } else {
                            onResultListener.onError();
                        }
                    }
                });
            } catch (Exception e) {
                onResultListener.onError();
            }
        } else if (arrayList.contains("password")) {
            String str1 = IvySdk.mmGetStringValue("__saved_email", "");
            String str2 = IvySdk.mmGetStringValue("__saved_password", "");
            if ("".equals(str1) || "".equals(str2)) {
                onResultListener.onError();
                return;
            }
            firebaseUser.reauthenticate(EmailAuthProvider.getCredential(str1, str2)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        onResultListener.onSuccess();
                    } else {
                        onResultListener.onError();
                    }
                }
            });
        }
    }

    public void updatePassword(@NonNull String paramString, @NonNull OnPasswordChangedListener paramOnPasswordChangedListener) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            paramOnPasswordChangedListener.onError("ERROR_NOT_SIGNED_IN", "Not signed Inn");
            return;
        }
        firebaseUser.updatePassword(paramString).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    paramOnPasswordChangedListener.onSuccess();
                } else {
                    FirebaseAuthError firebaseAuthError = FirebaseAuthError.fromException(task.getException());
                    if (FirebaseAuthError.ERROR_REQUIRES_RECENT_LOGIN.equals(firebaseAuthError)) {
                        reauthentication(new OnResultListener() {
                            @Override
                            public void onSuccess() {
                                updatePassword(paramString, paramOnPasswordChangedListener);
                            }

                            @Override
                            public void onError() {
                                paramOnPasswordChangedListener.onError(FirebaseAuthError.ERROR_INVALID_CREDENTIAL.name(), FirebaseAuthError.ERROR_INVALID_CREDENTIAL.getDescription());
                            }
                        });
                    } else {
                        paramOnPasswordChangedListener.onError(firebaseAuthError.name(), firebaseAuthError.getDescription());
                    }
                }
            }
        });
    }
}