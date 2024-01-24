#if UNITY_EDITOR
using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.Security.Cryptography;
using System.IO;
using UnityEditor;


namespace RiseSdk
{
    [UnityEditor.InitializeOnLoad]
    public sealed class RiseSdkForEditor : AbstractRiseSdk
    {

        private bool _isAdsEnabled = true;

        public override void OnInit()
        {
            Debug.Log($"Editor call Init");
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
            Debug.Log($"track event to firebase:{category}:{keyValueData}");
        }

        public override void recordException(string title, string err)
        {
            Debug.Log($"log exception to firebase:{title}:{err}");
        }

        public override bool HasRewardAd()
        {
            return true;//Random.Range(0, 100) % 2 == 0;
        }

        public override void ShowRewardAd(int rewardId)
        {
            Debug.Log($"Editor show reward ad with reward id:{rewardId}");
            RiseSdkEditorAd.EditorAdInstance.ShowRewardAd(rewardId);
        }

        public override void ShowRewardAd(string tag, int rewardId)
        {
            Debug.Log($"Editor show reward ad with tag:{tag} and reward id:{rewardId}");
            RiseSdkEditorAd.EditorAdInstance.ShowRewardAd(tag, rewardId);
        }

        public override bool GetAdsEnabledState()
        {
            return _isAdsEnabled;
        }

        public override void SetAdsEnabledState(bool state)
        {
            _isAdsEnabled = state;
            Debug.Log($"Editor set ads enabled state to {state}");
        }

        public override void ToastMsg(string str)
        {
            Debug.Log($"Editor show toast msg:{str}");
        }
    }
}

#endif