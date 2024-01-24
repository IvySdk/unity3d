#if UNITY_EDITOR
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Text;
using UnityEditor;
using UnityEngine;
using UnityEngine.Networking;

namespace RiseSdk
{
    public class RiseSdkEditorAd : MonoBehaviour
    {
        private static RiseSdkEditorAd _editorAdInstance = null;
        public static bool hasInit = true;
        private Rect bannerPos;
        private bool bannerShow = false;
        private string bannerContent = "";
        private bool interstitialShow = false;
        private string interstitialContent = "";
        private bool rewardShow = false;
        private string rewardContent = "";
        private float scaleWidth = 1;
        private float scaleHeight = 1;
        private int originScreenWidth = 1;
        private int originScreenHeight = 1;
        private bool toastShow = false;
        private List<string> toastList = new List<string>();
        private GUIStyle toastStyle = null;
        private int rewardAdId = NONE_REWARD_ID;
        private string rewardAdTag = DEFAULT_REWARD_TAG;
        private float iconAdWidth = 56;
        private float iconAdXPercent = .2f;
        private float iconAdYPercent = .2f;
        private bool iconAdShow = false;
        private string iconAdContent = "Icon Ad";
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
        private EventSystem curEvent = null;
#endif

        private const int NONE_REWARD_ID = -10;
        private const string DEFAULT_REWARD_TAG = "DEFAULT";
        private const string BANNER_DEFAULT_TXT = "Banner AD";
        private const string INTERSTITIAL_DEFAULT_TXT = "\nInterstitial AD Test";
        private const string REWARD_DEFAULT_TXT = "Free Coin AD Test: ";
        private const int SCREEN_WIDTH = 854;
        private const int SCREEN_HEIGHT = 480;
        private const int GUI_DEPTH = -99;
        private const int BANNER_WIDTH = 320;
        private const int BANNER_HEIGHT = 50;

        void Awake()
        {
            if (_editorAdInstance == null)
            {
                _editorAdInstance = this;
            }
            DontDestroyOnLoad(gameObject);
            if (Screen.width > Screen.height)
            {
                originScreenWidth = SCREEN_WIDTH;
                originScreenHeight = SCREEN_HEIGHT;
            }
            else
            {
                originScreenWidth = SCREEN_HEIGHT;
                originScreenHeight = SCREEN_WIDTH;
            }
            scaleWidth = Screen.width * 1f / originScreenWidth;
            scaleHeight = Screen.height * 1f / originScreenHeight;
            toastStyle = new GUIStyle();
            toastStyle.fontStyle = FontStyle.Bold;
            toastStyle.alignment = TextAnchor.MiddleCenter;
            toastStyle.fontSize = 30;
        }

        public static RiseSdkEditorAd EditorAdInstance
        {
            get
            {
                if (_editorAdInstance == null)
                {
                    _editorAdInstance = FindObjectOfType<RiseSdkEditorAd>() == null ? new GameObject("RiseSdkEditorAd").AddComponent<RiseSdkEditorAd>() : _editorAdInstance;
                }
                if (!hasInit)
                {
                    Debug.LogError("Fatal Error: \nNeed Call RiseSdk.Instance.Init () First At Initialize Scene");
                }
                return _editorAdInstance;
            }
        }

#if UNITY_EDITOR
        void OnGUI()
        {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
            if (curEvent == null) {
                curEvent = EventSystem.current;
            }
#endif
            GUI.depth = GUI_DEPTH;
            if (bannerShow)
            {
                GUI.backgroundColor = Color.green;
                GUI.color = Color.red;
                if (GUI.Button(bannerPos, bannerContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                GUI.backgroundColor = Color.green;
                if (GUI.Button(bannerPos, bannerContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                GUI.backgroundColor = Color.green;
                if (GUI.Button(bannerPos, bannerContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                GUI.backgroundColor = Color.green;
                if (GUI.Button(bannerPos, bannerContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
            }
            if (interstitialShow)
            {
                GUI.backgroundColor = Color.black;
                GUI.color = Color.white;
                //GUI.backgroundColor = new Color (0, 0, 0, 1);
                //GUI.color = new Color (1, 0, 0, 1);
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    InterstitialAdCallBack();
                    interstitialShow = false;
                    //  Instance.OnResume();
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), interstitialContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), interstitialContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), interstitialContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), interstitialContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                GUI.backgroundColor = Color.red;
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    InterstitialAdCallBack();
                    interstitialShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    InterstitialAdCallBack();
                    interstitialShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    InterstitialAdCallBack();
                    interstitialShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    InterstitialAdCallBack();
                    interstitialShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
            }
            if (rewardShow)
            {
                GUI.backgroundColor = Color.black;
                GUI.color = Color.white;
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    rewardShow = false;
                    //  Instance.OnResume();
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                    RewardAdCallBack();
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), rewardContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), rewardContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), rewardContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                if (GUI.Button(new Rect(0, 0, Screen.width, Screen.height), rewardContent))
                {
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                }
                GUI.backgroundColor = Color.red;
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    rewardShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                    RewardAdCallBack();
                }
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    rewardShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                    RewardAdCallBack();
                }
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    rewardShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                    RewardAdCallBack();
                }
                if (GUI.Button(new Rect(Screen.width - 100 * scaleWidth, 0, 100 * scaleWidth, 50 * scaleHeight), "Close"))
                {
                    rewardShow = false;
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                    if (EventSystem.current != null) {
                        EventSystem.current.enabled = false;
                    }
#endif
                    RewardAdCallBack();
                }
            }
            if (iconAdShow)
            {
                GUI.backgroundColor = Color.black;
                GUI.color = Color.white;
                //GUI.backgroundColor = new Color (0, 0, 0, 1);
                //GUI.color = new Color (1, 0, 0, 1);
                if (GUI.Button(new Rect(Screen.width * iconAdXPercent, Screen.height * iconAdYPercent, iconAdWidth, iconAdWidth), iconAdContent))
                {
                }
                if (GUI.Button(new Rect(Screen.width * iconAdXPercent, Screen.height * iconAdYPercent, iconAdWidth, iconAdWidth), iconAdContent))
                {
                }
                if (GUI.Button(new Rect(Screen.width * iconAdXPercent, Screen.height * iconAdYPercent, iconAdWidth, iconAdWidth), iconAdContent))
                {
                }
                if (GUI.Button(new Rect(Screen.width * iconAdXPercent, Screen.height * iconAdYPercent, iconAdWidth, iconAdWidth), iconAdContent))
                {
                }
            }
            if (toastList.Count > 0)
            {
                GUI.backgroundColor = Color.black;
                GUI.color = Color.red;
                //GUI.contentColor = Color.red;
                GUI.Button(new Rect((Screen.width - 400 * scaleWidth) * .5f, Screen.height - 100 * scaleHeight, 400 * scaleWidth, 50 * scaleHeight), toastList[0]);
                GUI.Button(new Rect((Screen.width - 400 * scaleWidth) * .5f, Screen.height - 100 * scaleHeight, 400 * scaleWidth, 50 * scaleHeight), toastList[0]);
                //GUI.Label (new Rect ((Screen.width - 200 * scaleWidth) * .5f, Screen.height - 100 * scaleHeight, 200 * scaleWidth, 50 * scaleHeight), toastList [0], toastStyle);
            }
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
            if (EventSystem.current != null) {
                EventSystem.current.enabled = true;
            } else if (curEvent != null) {
                curEvent.enabled = true;
                EventSystem.current = curEvent;
            }
#endif
        }

        void Update()
        {
            if (Input.GetKeyDown(KeyCode.Escape))
            {
                if (interstitialShow)
                {
                    interstitialShow = false;
                    InterstitialAdCallBack();
                }
                else if (rewardShow)
                {
                    rewardShow = false;
                    RewardAdCallBack();
                }
#if UNITY_5 || UNITY_4_6 || UNITY_4_7 || UNITY_4_8 || UNITY_4_9
                if (EventSystem.current != null) {
                    EventSystem.current.enabled = true;
                } else if (curEvent != null) {
                    curEvent.enabled = true;
                    EventSystem.current = curEvent;
                }
#endif
            }
        }

        private void InterstitialAdCallBack()
        {
            if (interstitialShow)
            {
#if UNITY_IOS || PLATFORM_IOS
                RiseSdkListener.Instance.adDidClose("custom");
#elif UNITY_ANDROID
                RiseSdkListener.Instance.onFullAdClosed("EditorAd");
#endif
            }
        }

        private void RewardAdCallBack()
        {
            if (rewardAdId != NONE_REWARD_ID)
            {
                Toast("Show Reward Ad Success");
#if UNITY_ANDROID
                RiseSdkListener.Instance.onReceiveReward("0|" + rewardAdId + "|EditorVideo");
#elif UNITY_IOS || PLATFORM_IOS
                //RiseSdkListener.Instance.adReward(rewardAdTag + "|" + rewardAdId);
                Debug.LogWarning("[RewardAdCallBack]rewardAdId:" + rewardAdId + " rewardAdTag:" + rewardAdTag);
                RiseSdkListener.Instance.adReward("EditorVideo|" + rewardAdId);
                
#endif
            }
            rewardAdId = NONE_REWARD_ID;
            rewardAdTag = DEFAULT_REWARD_TAG;
        }
#endif

        public void ShowBanner(int pos)
        {
#if UNITY_EDITOR
            bannerContent = BANNER_DEFAULT_TXT + ", tag: default, pos: " + pos;
            bannerShow = true;
            SetBannerPos(pos);
#endif
        }

        public void ShowBanner(string tag, int pos)
        {
#if UNITY_EDITOR
            bannerContent = BANNER_DEFAULT_TXT + ", tag: " + tag + ", pos: " + pos;
            bannerShow = true;
            SetBannerPos(pos);
#endif
        }

        public void ShowBanner(string tag, int pos, int animate)
        {
#if UNITY_EDITOR
            bannerContent = BANNER_DEFAULT_TXT + ", tag: " + tag + ", pos: " + pos + ", animate: " + animate;
            bannerShow = true;
            SetBannerPos(pos);
#endif
        }

        public void CloseBanner()
        {
#if UNITY_EDITOR
            bannerShow = false;
#endif
        }

        private void SetBannerPos(int pos)
        {
#if UNITY_EDITOR
            switch (pos)
            {
                case RiseSdk.POS_BANNER_LEFT_BOTTOM:
                    bannerPos = new Rect(0, Screen.height - BANNER_HEIGHT * scaleHeight, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
                case RiseSdk.POS_BANNER_LEFT_TOP:
                    bannerPos = new Rect(0, 0, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
                case RiseSdk.POS_BANNER_MIDDLE_BOTTOM:
                    bannerPos = new Rect((Screen.width - BANNER_WIDTH * scaleWidth) * .5f, Screen.height - BANNER_HEIGHT * scaleHeight, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
                case RiseSdk.POS_BANNER_MIDDLE_MIDDLE:
                    bannerPos = new Rect((Screen.width - BANNER_WIDTH * scaleWidth) * .5f, (Screen.height - BANNER_HEIGHT * scaleHeight) * .5f, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
                case RiseSdk.POS_BANNER_MIDDLE_TOP:
                    bannerPos = new Rect((Screen.width - BANNER_WIDTH * scaleWidth) * .5f, 0, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
                case RiseSdk.POS_BANNER_RIGHT_BOTTOM:
                    bannerPos = new Rect(Screen.width - BANNER_WIDTH * scaleWidth, Screen.height - BANNER_HEIGHT * scaleHeight, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
                case RiseSdk.POS_BANNER_RIGHT_TOP:
                    bannerPos = new Rect(Screen.width - BANNER_WIDTH * scaleWidth, 0, BANNER_WIDTH * scaleWidth, BANNER_HEIGHT * scaleHeight);
                    break;
            }
#endif
        }

        public void ShowAd(string tag)
        {
#if UNITY_EDITOR
            interstitialShow = true;
            interstitialContent = tag + INTERSTITIAL_DEFAULT_TXT;
            RiseSdk.Instance.OnPause();
#endif
        }

        public void ShowRewardAd(int id)
        {
#if UNITY_EDITOR
            rewardShow = true;
            rewardAdId = id;
            rewardAdTag = DEFAULT_REWARD_TAG;
            rewardContent = REWARD_DEFAULT_TXT + rewardAdTag;
            // RiseSdkListener.Instance.onAdShow($"2|Default|{id}");
            // RiseSdkListener.Instance.onReceiveReward($"1|{id}|Default|0");
            // RiseSdkListener.Instance.onVideoAdClosed($"Default|{id}");
            RiseSdk.Instance.OnPause();
#endif
        }

        public void ShowRewardAd(string tag, int id)
        {
#if UNITY_EDITOR
            rewardShow = true;
            rewardAdId = id;
            rewardAdTag = tag;
            rewardContent = REWARD_DEFAULT_TXT + tag;
            // RiseSdkListener.Instance.onAdShow($"2|{tag}|{id}");
            // RiseSdkListener.Instance.onReceiveReward($"1|{id}|{tag}|0");
            // RiseSdkListener.Instance.onVideoAdClosed($"{tag}|{id}");
            RiseSdk.Instance.OnPause();
#endif
        }

        public void ShowIconAd(float width, float xPercent, float yPercent)
        {
            iconAdShow = true;
            iconAdWidth = width;
            iconAdXPercent = xPercent;
            iconAdYPercent = yPercent;
        }

        public void CloseIconAd()
        {
            iconAdShow = false;
        }

        public void Pay(int billingId)
        {
#if !Headline
#if UNITY_EDITOR
            //RiseSdkListener.Instance.onPaymentSuccess(billingId + "");
#if UNITY_ANDROID
            switch (EditorUtility.DisplayDialogComplex("Pay", "Pay: " + billingId, "TRY FAILURE", "NO", "YES"))
            {
                case 0://TRY FAILURE
                    Toast("pay " + billingId + " Failed");
                    RiseSdkListener.Instance.onPaymentFail(billingId + "");
                    break;
                case 1://NO
                    Toast("pay " + billingId + " Canceled");
                    RiseSdkListener.Instance.onPaymentCanceled(billingId + "");
                    break;
                case 2://YES
                    Toast("pay " + billingId + " Success");
                    RiseSdkListener.Instance.onPaymentSuccess(billingId + "");
                    break;
            }
#elif UNITY_IOS || PLATFORM_IOS
            switch (EditorUtility.DisplayDialogComplex("Pay", "Pay: " + billingId, "TRY FAILURE", "NO", "YES"))
            {
                case 0://TRY FAILURE
                    Toast("pay " + billingId + " Failed");
                    RiseSdkListener.Instance.onPaymentFailure(billingId + "");
                    break;
                case 1://NO
                    Toast("pay " + billingId + " Canceled");
                    RiseSdkListener.Instance.onPaymentFailure(billingId + "");
                    break;
                case 2://YES
                    Toast("pay " + billingId + " Success");
                    RiseSdkListener.Instance.onPaymentSuccess(billingId + "");
                    break;
            }
#endif
#endif
#endif
        }

        public void PayWithPayload(int billingId, string payloadData)
        {
#if !Headline
#if UNITY_EDITOR
            switch (EditorUtility.DisplayDialogComplex("Pay", "Pay: " + billingId, "TRY FAILURE", "NO", "YES"))
            {
                case 0://TRY FAILURE
                    Toast("pay " + billingId + " Failed");
#if UNITY_ANDROID
                    RiseSdkListener.Instance.onPaymentFail(billingId.ToString());
#elif UNITY_IOS || PLATFORM_IOS
                    RiseSdkListener.Instance.onPaymentFailure(billingId.ToString());
#endif
                    // RiseSdkListener.Instance.onPaymentFail (billingId + "|" + Convert.ToBase64String(paymentItems));
                    break;
                case 1://NO
                    Toast("pay " + billingId + " Canceled");
#if UNITY_ANDROID
                    RiseSdkListener.Instance.onPaymentCanceled(billingId.ToString());
#elif UNITY_IOS || PLATFORM_IOS
                    RiseSdkListener.Instance.onPaymentFailure(billingId.ToString());
#endif
                    // RiseSdkListener.Instance.onPaymentCanceled (billingId + "|" + Convert.ToBase64String(paymentItems));
                    break;
                case 2://YES

                    RiseSdkListener.Instance.onPaymentSuccessWithPayload(billingId + "|" + payloadData);

                    //                    Toast("pay " + billingId + " Request Sending");
                    //                    Dictionary<string, string> paramDic = new Dictionary<string, string>();
                    //                    paramDic["payId"] = billingId.ToString();
                    //                    paramDic["payload"] = payloadData;
                    //#if UNITY_ANDROID
                    //                    MakeRequest($"{payUrl_}/Order/verifyGoogleOrderLocal_test", (string data) =>
                    //                    {
                    //#elif UNITY_IOS || PLATFORM_IOS
                    //                    MakeRequest($"{payUrl_}/Order/verifyGoogleOrderLocal_test", (string data) =>
                    //                    {
                    //#else
                    //                    MakeRequest($"{payUrl_}/Order/verifyGoogleOrderLocal_test", (string data) => {
                    //#endif
                    //                        Debug.Log("PayWithPayload, callback data:::" + data);
                    //                        if (!string.IsNullOrEmpty(data))
                    //                        {
                    //                            Dictionary<string, object> obj = (Dictionary<string, object>)RiseJson.Deserialize(data);
                    //                            if (obj != null && obj.ContainsKey("status"))
                    //                            {
                    //                                if (obj["status"].ToString().Equals("1"))
                    //                                {
                    //                                    if (obj.ContainsKey("payload"))
                    //                                    {
                    //                                        RiseSdkListener.Instance.onPaymentSuccessWithPayload(billingId + "|" + obj["payload"].ToString());
                    //                                    }
                    //                                    else
                    //                                    {
                    //                                        RiseSdkListener.Instance.onPaymentSuccessWithPayload(billingId + "|");
                    //                                    }
                    //                                }
                    //                                else
                    //                                {
                    //#if UNITY_ANDROID
                    //                                    RiseSdkListener.Instance.onPaymentFail(billingId.ToString());
                    //#elif UNITY_IOS || PLATFORM_IOS
                    //                                    RiseSdkListener.Instance.onPaymentFailure(billingId.ToString());
                    //#endif
                    //                                }
                    //                            }
                    //                        }
                    //                    }, paramDic);
                    break;
            }
#endif
#endif
        }

        public void MakeRequest(string url, Action<string> callback, Dictionary<string, string> param, byte[] binaryData = null, int timeoutSecond = 10)
        {
            try
            {
                if (string.IsNullOrEmpty(url))
                {
                    if (callback != null)
                    {
                        callback(null);
                    }
                }
                else
                {
                    RiseSdkListener.Instance.StartCoroutine(PostRequest(url, param, callback, binaryData, timeoutSecond));
                }
                //StartCoroutine (GetRequest (url, param, callback));
            }
            catch (Exception e)
            {
                Debug.LogError(e.StackTrace);
            }
        }

        IEnumerator PostRequest(string url, Dictionary<string, string> post, Action<string> callback, byte[] binaryData, int timeoutSecond)
        {
            WWWForm form = null;

            if (post != null && post.Count > 0)
            {
                form = new WWWForm();
                var enumerator = post.GetEnumerator();
                while (enumerator.MoveNext())
                {
                    form.AddField(enumerator.Current.Key, enumerator.Current.Value);
                }
                if (binaryData != null)
                {
                    form.AddBinaryData("file", binaryData);
                }
            }
            Debug.Log("PostRequest, url: " + url + (form != null ? ", WWWForm: " + Encoding.Default.GetString(form.data) : ""));

            UnityWebRequest uwr = null;
            if (form == null)
            {
                uwr = UnityWebRequest.Get(url);
            }
            else
            {
                uwr = UnityWebRequest.Post(url, form);
            }
            uwr.timeout = timeoutSecond;
            yield return uwr.Send();
            yield return null;
            if (callback != null)
            {
                if (!uwr.isNetworkError && uwr.responseCode == 200L)
                {
                    callback(uwr.downloadHandler.text);
                }
                else
                {
                    callback(null);
                }
            }
        }

        private bool timeCounting = false;

        public void Toast(string msg)
        {
#if UNITY_EDITOR
            toastList.Add(msg);
            if (!timeCounting)
            {
                timeCounting = true;
                StartCoroutine(CheckToast());
            }
#endif
        }

        private IEnumerator CheckToast(float time = 2)
        {
            yield return new WaitForSeconds(time);
            if (toastList.Count > 0)
            {
                toastList.RemoveAt(0);
            }
            if (toastList.Count > 0)
            {
                StartCoroutine(CheckToast());
            }
            else
            {
                timeCounting = false;
            }
        }

        public void Alert(string title, string msg)
        {
#if UNITY_EDITOR
            EditorUtility.DisplayDialog(title, msg, "NO", "OK");
#endif
        }

        public void OnExit()
        {
#if UNITY_EDITOR
            if (EditorUtility.DisplayDialog("Exit", "Are you sure to exit?", "YES", "NO"))
            {
                EditorApplication.isPlaying = false;
            }
#endif
        }
    }

}
#endif