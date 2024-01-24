
#if UNITY_ANDROID
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


    public sealed class RiseSdkForAndroid : AbstractRiseSdk
    {

        private AndroidJavaClass _class = null;

        private AndroidJavaClass _javaClass
        {
            set
            {
                _class = value;
            }
            get
            {
                if (_class == null)
                {
                    _class = new AndroidJavaClass("com.android.client.Unity");
                }
                if (_class != null)
                {
                    string packageNameWithClassName = _class.GetRawClass().ToString();
                    if (packageNameWithClassName != null && "com.android.client.Unity".Equals(packageNameWithClassName))
                    {
                        return _class;
                    }
                    else
                    {
                        _class = null;
                        _class = new AndroidJavaClass("com.android.client.Unity");
                    }
                }
                return _class;
            }
        }

        public override void OnInit()
        {

            CallSafeOnMainThread(() =>
                       {
                           if (Application.platform == RuntimePlatform.Android)
                           {
                               // CallSafeOnMainThread2((activity) =>
                               // {
                               //     _javaClass?.CallStatic("onCreate", activity);
                               // });
                               using (AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
                               {
                                   using (AndroidJavaObject context = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity"))
                                   {
                                       Debug.Log($"RiseSdkForAndroid::OnInit:{_javaClass}");
                                       _javaClass?.CallStatic("onCreate", context);

                                       // CallSafeOnMainThread(() =>
                                       // {
                                       //     Debug.Log($"RiseSdkForAndroid::OnInit:{_javaClass}");
                                       //     _javaClass.CallStatic("onCreate", context);
                                       // });
                                   }
                               }
                           }
                       });
        }

        public override void OnPause()
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("onPause");
            });
        }

        public override void OnResume()
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("onResume");
            });
        }

        public override void OnStart()
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("onStart");
            });
        }

        public override void OnStop()
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("onStop");
            });
        }


        public override void FireBaseTrackEvent(string category, string keyValueData)
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("trackEventToFirebase", category, keyValueData);
            });
        }

        public override void recordException(string title, string err)
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("recordException", title, err);
            });
        }

        public override bool HasRewardAd()
        {
            Debug.Log("Java class call from android");
            if (_javaClass == null)
            {
                Debug.Log("Java class is null");
                return false;
            }
            return _javaClass.CallStatic<bool>("hasRewardAd");
        }

        public override void ShowRewardAd(int rewardId)
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("showRewardAd", rewardId);
            });
        }

        public override void ShowRewardAd(string tag, int rewardId)
        {
            CallSafeOnMainThread(() =>
            {
                _javaClass?.CallStatic("showRewardAd", tag, rewardId);
            });
        }

        public override bool GetAdsEnabledState()
        {
            return true;
        }

        public override void SetAdsEnabledState(bool state)
        {
            Debug.Log($"Android set ads enabled state: {state}");
        }

        public override void ToastMsg(string str)
        {
            CallSafeOnMainThread(() =>
          {
              _javaClass?.CallStatic("toast", str);
          });
        }
    }
}
#endif