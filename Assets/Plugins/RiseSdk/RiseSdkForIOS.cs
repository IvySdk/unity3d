
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.Security.Cryptography;
using System.IO;
using System.Runtime.InteropServices;
using UnityEngine;


namespace RiseSdk
{
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
using UnityEngine.Networking;
#endif


#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
using UnityEngine.EventSystems;
#endif

    public sealed class RiseSdkForIOS : IRiseSdk
    {
#if UNITY_IOS
        [DllImport("__Internal")]
        private static extern void onCreate();
        [DllImport("__Internal")]
        private static extern bool isVideoAvailable();
        [DllImport("__Internal")]
        private static extern void showRewardVideo(int placementId);
        [DllImport("__Internal")]
        private static extern void showRewardVideoWithTag(string tag, int placementId);

        [DllImport("__Internal")]
        private static extern bool isAdsEnabled();
        [DllImport("__Internal")]
        private static extern void setAdsEnable(bool enable);

        [DllImport("__Internal")]
        private static extern void logFirebaseEventWithData(string eventId, string data);
        [DllImport("__Internal")]
        private static extern void logErrorInFirebase(int errorCode, string domain, string reason, string desc, string suggest);

        [DllImport("__Internal")]
        private static extern void toast(string info);
#endif


        public override void OnInit()
        {
#if UNITY_IOS
            onCreate();
#endif
        }

        public override void OnPause()
        {

        }

        public override void OnResume()
        {

        }

        public override void OnStart()
        {

        }

        public override void OnStop()
        {

        }

        public override void FireBaseTrackEvent(string category, string keyValueData)
        {
#if UNITY_IOS
            logFirebaseEventWithData(category, keyValueData);
#endif
        }

        public override void recordException(string title, string err)
        {
#if UNITY_IOS
            logErrorInFirebase(1, title, title, err, "");
#endif
        }

        public override bool HasRewardAd()
        {
#if UNITY_IOS
            return isVideoAvailable();
#endif
            return false;
        }

        public override void ShowRewardAd(int rewardId)
        {
#if UNITY_IOS
            showRewardVideo(rewardId);
#endif
        }

        public override void ShowRewardAd(string tag, int rewardId)
        {
#if UNITY_IOS
            showRewardVideoWithTag(tag, rewardId);
#endif
        }


        public override bool GetAdsEnabledState()
        {
#if UNITY_IOS
            return isAdsEnabled();
#endif
            return false;
        }

        public override void SetAdsEnabledState(bool state)
        {
#if UNITY_IOS
            setAdsEnable(state);
#endif
            Debug.Log($"IOS set ads enabled state: {state}");
        }

        public override void ToastMsg(string str)
        {
#if UNITY_IOS
            toast(str);
#endif
        }
    }
}