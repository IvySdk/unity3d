#if UNITY_IOS
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

        public sealed class RiseSdkForIOS : AbstractRiseSdk
        {
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


                public override void OnInit()
                {
                        onCreate();
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
                        logFirebaseEventWithData(category, keyValueData);
                }

                public override void recordException(string title, string err)
                {
                        logErrorInFirebase(1, title, title, err, "");
                }

                public override bool HasRewardAd()
                {
                        return isVideoAvailable();
                }

                public override void ShowRewardAd(int rewardId)
                {
                        showRewardVideo(rewardId);
                }

                public override void ShowRewardAd(string tag, int rewardId)
                {
                        showRewardVideoWithTag(tag, rewardId);
                }


                public override bool GetAdsEnabledState()
                {
                        return isAdsEnabled();
                }

                public override void SetAdsEnabledState(bool state)
                {
                        setAdsEnable(state);
                        Debug.Log($"IOS set ads enabled state: {state}");
                }

                public override void ToastMsg(string str)
                {
                        toast(str);
                }
        }
}
#endif