using System;
using UnityEngine;

namespace RiseSdk
{
    public abstract class AbstractRiseSdk
    {

        public abstract void OnInit();

        public abstract void OnStart();

        public abstract void OnResume();

        public abstract void OnPause();

        public abstract void OnStop();

        public abstract void recordException(string title, string err);

        public abstract bool HasRewardAd();

        public abstract void ShowRewardAd(int rewardId);

        public abstract void ShowRewardAd(string tag, int rewardId);

        public abstract void FireBaseTrackEvent(string category, string keyValueData);

        public abstract bool GetAdsEnabledState();

        public abstract void SetAdsEnabledState(bool state);

        public abstract void ToastMsg(string str);




        // protected void CallSafeOnMainThread2(Action<AndroidJavaObject> action)
        // {
        //     try
        //     {
        //         // 确保在主线程上执行JNI调用
        //         if (Application.platform == RuntimePlatform.Android)
        //         {
        //             using (AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
        //             {
        //                 using (AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity"))
        //                 {
        //                     activity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
        //                     {
        //                         action?.Invoke(activity);
        //                     }));
        //                 }
        //             }
        //         }
        //     }
        //     catch (Exception e)
        //     {
        //         Debug.LogError("RiseSdk Init Error:::\n" + e.StackTrace + "\n" + e.Message);
        //     }
        // }


    }
}