#region Using
using System;
using System.Collections;
using UnityEngine;
using System.Collections.Generic;
#endregion


#if Headline
public class RiseSdkListener : MonoBehaviour
{

#if UNITY_ANDROID
    /// <summary>
    /// ��������Ƶ���Ļص��¼�
    /// 1.RiseSdk.AdEventType
    /// 2.rewardId
    /// 3.ad tag
    /// 4.RiseSdk.ADTYPE_
    /// 5.video skipped  //max 4 param limited.
    /// </summary>
    public static event Action<RiseSdk.AdEventType, int, string, int> OnAdEvent;

    /// <summary>
    /// ���������Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.DouYinShareResult> OnShareEvent;

    /// <summary>
    /// ¼���Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.ScreenRecordResult, string> OnRecordEvent;

    /// <summary>
    ///���ܵĽ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.AIVideoEditorShareResult, string> OnAIVideoEvnet;

    /// <summary>
    ///��ȡ��ƬȨ�޵Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.RequestPhotoWritepermitResult> OnRequestPhotoWritepermitEvnet;

    private static RiseSdkListener _instance;

    public static RiseSdkListener Instance
    {
        get
        {
            if (!_instance)
            {
                // check if there is a IceTimer instance already available in the scene graph
                _instance = FindObjectOfType(typeof(RiseSdkListener)) as RiseSdkListener;
                // nope, create a new one
                if (!_instance)
                {
                    var obj = new GameObject("RiseSdkListener");
                    _instance = obj.AddComponent<RiseSdkListener>();
                    DontDestroyOnLoad(obj);
                }
            }
            return _instance;
        }
    }

    /// <summary>
    /// ��ʾ��Ƶ���Ľ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���صĽ������</param>
    public void onReceiveReward(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            bool success = false;
            int id = -1;
            string tag = "Default";
            bool skippedVideo = false;
            if (!string.IsNullOrEmpty(data))
            {
                string[] results = data.Split('|');
                if (results != null && results.Length > 1)
                {
                    success = int.Parse(results[0]) == 0;
                    id = int.Parse(results[1]);
                    if (results.Length > 2)
                    {
                        tag = results[2];
                        if (results.Length > 3)
                        {
                            skippedVideo = int.Parse(results[3]) == 0 ? true : false;
                        }
                    }
                }
            }
            if (success)
            {
                OnAdEvent(RiseSdk.AdEventType.RewardAdShowFinished, id, tag, RiseSdk.ADTYPE_VIDEO);
            }
            else
            {
                OnAdEvent(RiseSdk.AdEventType.RewardAdLoadFailed, id, tag, RiseSdk.ADTYPE_VIDEO);
            }
        }
    }

    /// <summary>
    /// ������汻�رյĻص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onFullAdClosed(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 0)
                {
                    tag = msg[0];
                }
            }
            OnAdEvent(RiseSdk.AdEventType.FullAdClosed, -1, tag, RiseSdk.ADTYPE_INTERTITIAL);
        }
    }

    /// <summary>
    /// ������汻����Ļص�������SDK�Զ����á�    
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onFullAdClicked(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 0)
                {
                    tag = msg[0];
                }
            }
            OnAdEvent(RiseSdk.AdEventType.FullAdClicked, -1, tag, RiseSdk.ADTYPE_INTERTITIAL);
        }
    }

    /// <summary>
    /// �������չʾ�ɹ��Ļص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onAdShow(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int type = RiseSdk.ADTYPE_INTERTITIAL;
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 1)
                {
                    int.TryParse(msg[0], out type);
                    tag = msg[1];
                }
            }
            RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
            switch (type)
            {
                case RiseSdk.ADTYPE_INTERTITIAL:
                    eventType = RiseSdk.AdEventType.FullAdShown;
                    break;
                case RiseSdk.ADTYPE_VIDEO:
                    eventType = RiseSdk.AdEventType.RewardAdShown;
                    break;
                case RiseSdk.ADTYPE_BANNER:
                case RiseSdk.ADTYPE_ICON:
                case RiseSdk.ADTYPE_NATIVE:
                    eventType = RiseSdk.AdEventType.AdShown;
                    break;
            }
            OnAdEvent(eventType, -1, tag, type);
        }
    }

    /// <summary>
    /// ������汻����Ļص�������SDK�Զ����á�    
    /// </summary
    /// <param name="data">���ص�����</param>
    public void onAdClicked(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int adType = RiseSdk.ADTYPE_INTERTITIAL;
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 1)
                {
                    int.TryParse(msg[0], out adType);
                    tag = msg[1];
                }
            }
            RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
            switch (adType)
            {
                case RiseSdk.ADTYPE_INTERTITIAL:
                    eventType = RiseSdk.AdEventType.FullAdClicked;
                    break;
                case RiseSdk.ADTYPE_VIDEO:
                    eventType = RiseSdk.AdEventType.RewardAdClicked;
                    break;
                case RiseSdk.ADTYPE_BANNER:
                    eventType = RiseSdk.AdEventType.BannerAdClicked;
                    break;
                case RiseSdk.ADTYPE_ICON:
                    eventType = RiseSdk.AdEventType.IconAdClicked;
                    break;
                case RiseSdk.ADTYPE_NATIVE:
                    eventType = RiseSdk.AdEventType.NativeAdClicked;
                    break;
            }
            //OnAdEvent (RiseSdk.AdEventType.AdClicked, -1, tag, adType);
            OnAdEvent(eventType, -1, tag, adType);
        }
    }

    /// <summary>
    /// ��Ƶ��汻�رյĻص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onVideoAdClosed(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 0)
                {
                    tag = msg[0];
                }
            }
            OnAdEvent(RiseSdk.AdEventType.RewardAdClosed, -1, tag, RiseSdk.ADTYPE_VIDEO);
        }
    }

    /// <summary>
    /// banner��汻����Ļص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onBannerAdClicked(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 0)
                {
                    tag = msg[0];
                }
            }
            OnAdEvent(RiseSdk.AdEventType.BannerAdClicked, -1, tag, RiseSdk.ADTYPE_BANNER);
        }
    }

    /// <summary>
    /// �����ƹ��汻����Ļص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onCrossAdClicked(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            if (!string.IsNullOrEmpty(data))
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 0)
                {
                    tag = msg[0];
                }
            }
            OnAdEvent(RiseSdk.AdEventType.CrossAdClicked, -1, tag, RiseSdk.ADTYPE_OTHER);
        }
    }

    public void onRecordingStoped(string data)
    {
        if (OnRecordEvent != null && OnRecordEvent.GetInvocationList().Length > 0)
        {
            if (!string.IsNullOrEmpty(data))
                OnRecordEvent(RiseSdk.ScreenRecordResult.Success, data);
            else
                OnRecordEvent(RiseSdk.ScreenRecordResult.Fail, data);
        }
    }

    //public void onRecordingProgress(string data)
    //{
    //    if (OnAIVideoEvnet != null && OnAIVideoEvnet.GetInvocationList().Length > 0)
    //    {
    //        Debug.LogError("onRecordingProgress:" + Time.frameCount);
    //        OnRecordEvent(RiseSdk.ScreenRecordResult.EditingProgress, data);
    //    }
    //}

    public void onEditVideoComplete(string data)
    {
        if (OnAIVideoEvnet != null && OnAIVideoEvnet.GetInvocationList().Length > 0)
        {
            OnAIVideoEvnet(RiseSdk.AIVideoEditorShareResult.Success, data);
        }
    }

    public void onSharePhotosSuccess(string data)
    {
        if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
        {
            OnShareEvent(RiseSdk.DouYinShareResult.Success);
        }
    }

    public void onSharePhotosFailure(string data)
    {
        if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
        {
            OnShareEvent(RiseSdk.DouYinShareResult.Fail);
        }
    }

    public void onShareVideosSuccess(string data)
    {
        if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
        {
            OnShareEvent(RiseSdk.DouYinShareResult.Success);
        }
    }

    public void onShareVideosFailure(string data)
    {
        if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
        {
            OnShareEvent(RiseSdk.DouYinShareResult.Fail);
        }
    }

    public void onRecordVideoEditComplete(string data)
    {
        if (OnAIVideoEvnet != null && OnAIVideoEvnet.GetInvocationList().Length > 0)
        {
            OnAIVideoEvnet(RiseSdk.AIVideoEditorShareResult.RecordVideoEditSuccess, data);
        }
    }

    public void requestPhotoWritePermissionResult(string data)
    {
        Debug.LogError("requestPhotoWritepermitResult====" + data);
        if (OnRequestPhotoWritepermitEvnet != null && OnRequestPhotoWritepermitEvnet.GetInvocationList().Length > 0)
        {
            if (!string.IsNullOrEmpty(data))
            {
                if (data.Equals("0"))
                {
                    OnRequestPhotoWritepermitEvnet(RiseSdk.RequestPhotoWritepermitResult.Fail);
                }
                else if (data.Equals("1"))
                {
                    OnRequestPhotoWritepermitEvnet(RiseSdk.RequestPhotoWritepermitResult.Success);
                }
            }
        }
    }
#endif

#if UNITY_IOS
    /// <summary>
    /// ��������Ƶ���Ļص��¼�
    /// 1.RiseSdk.AdEventType
    /// 2.rewardId
    /// 3.ad tag
    /// 4.RiseSdk.ADTYPE_
    /// 5.video skipped  //max 4 param limited.
    /// </summary>
    public static event Action<RiseSdk.AdEventType, int, string, int> OnAdEvent;
 
    /// <summary>
    /// ���������Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.DouYinShareResult> OnShareEvent;

    /// <summary>
    /// ¼���Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.ScreenRecordResult, string> OnRecordEvent;

    /// <summary>
    ///���ܵĽ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.AIVideoEditorShareResult, string> OnAIVideoEvnet;

	/// <summary>
	///��ȡ��ƬȨ�޵Ľ���ص��¼�
	/// </summary>
	public static event Action<RiseSdk.RequestPhotoWritepermitResult> OnRequestPhotoWritepermitEvnet;

    private static RiseSdkListener _instance;
 
    public static RiseSdkListener Instance
    {
        get
        {
            if (!_instance)
            {
                // check if there is a IceTimer instance already available in the scene graph
                _instance = FindObjectOfType(typeof(RiseSdkListener)) as RiseSdkListener;
                // nope, create a new one
                if (!_instance)
                {
                    var obj = new GameObject("RiseSdkListener");
                    _instance = obj.AddComponent<RiseSdkListener>();
                    DontDestroyOnLoad(obj);
                }
            }
            return _instance;
        }
    }

    public void adReward(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int placementId = -1;
            if (!string.IsNullOrEmpty(data))
            {
                string[] str = data.Split('|');
                if (str.Length == 1)
                {
                    tag = str[0];
                }
                else if (str.Length >= 2)
                {
                    tag = str[0];
                    int.TryParse(str[1], out placementId);
                }
            }
            OnAdEvent(RiseSdk.AdEventType.RewardAdShowFinished, placementId, tag, RiseSdk.ADTYPE_VIDEO);
        }
    }

    public void adLoaded(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty(data))
            {
                string[] str = data.Split('|');
                if (str.Length == 1)
                {
                    tag = str[0];
                }
                else if (str.Length >= 2)
                {
                    tag = str[0];
                    int.TryParse(str[1], out adType);
                }
            }
            OnAdEvent(RiseSdk.AdEventType.AdLoadCompleted, -1, tag, adType);
        }
    }
		

    public void adShowFailed(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty(data))
            {
                string[] str = data.Split('|');
                if (str.Length == 1)
                {
                    tag = str[0];
                }
                else if (str.Length >= 2)
                {
                    tag = str[0];
                    int.TryParse(str[1], out adType);
                }
            }
            OnAdEvent(RiseSdk.AdEventType.AdLoadFailed, -1, tag, adType);
        }
    }

    public void adDidShown(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty(data))
            {
                string[] str = data.Split('|');
                if (str.Length == 1)
                {
                    tag = str[0];
                }
                else if (str.Length >= 2)
                {
                    tag = str[0];
                    int.TryParse(str[1], out adType);
                }
            }
            OnAdEvent(RiseSdk.AdEventType.AdShown, -1, tag, adType);
        }
    }

    public void adDidClose(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty(data))
            {
                string[] str = data.Split('|');
                if (str.Length == 1)
                {
                    tag = str[0];
                }
                else if (str.Length >= 2)
                {
                    tag = str[0];
                    int.TryParse(str[1], out adType);
                }
            }
            OnAdEvent(RiseSdk.AdEventType.AdClosed, -1, tag, adType);
        }
    }

    public void adDidClick(string data)
    {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
        {
            string tag = "Default";
            int adType = RiseSdk.ADTYPE_INTERTITIAL;
            if (!string.IsNullOrEmpty(data))
            {
                string[] str = data.Split('|');
                if (str.Length == 1)
                {
                    tag = str[0];
                }
                else if (str.Length >= 2)
                {
                    tag = str[0];
                    int.TryParse(str[1], out adType);
                }
            }
            RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
            switch (adType)
            {
                case RiseSdk.ADTYPE_INTERTITIAL:
                    eventType = RiseSdk.AdEventType.FullAdClicked;
                    break;
                case RiseSdk.ADTYPE_VIDEO:
                    eventType = RiseSdk.AdEventType.RewardAdClick;
                    break;
                case RiseSdk.ADTYPE_BANNER:
                    eventType = RiseSdk.AdEventType.BannerAdClicked;
                    break;
                case RiseSdk.ADTYPE_ICON:
                    eventType = RiseSdk.AdEventType.IconAdClicked;
                    break;
                case RiseSdk.ADTYPE_NATIVE:
                    eventType = RiseSdk.AdEventType.NativeAdClicked;
                    break;
            }
            OnAdEvent(eventType, -1, tag, adType);
        }
    }

    public void onRecordingStoped(string data)
    {
        if (OnRecordEvent != null && OnRecordEvent.GetInvocationList().Length > 0)
        {
            if (!string.IsNullOrEmpty(data))
                OnRecordEvent(RiseSdk.ScreenRecordResult.Success, data);
            else
                OnRecordEvent(RiseSdk.ScreenRecordResult.Fail, data);
        }
	}
        

        public void onEditVideoComplete(string data)
        {
            if (OnAIVideoEvnet != null && OnAIVideoEvnet.GetInvocationList().Length > 0)
            {
			OnAIVideoEvnet(RiseSdk.AIVideoEditorShareResult.Success, data);
            }
        }

        public void onSharePhotosSuccess(string data)
        {
            if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
            {
			OnShareEvent(RiseSdk.DouYinShareResult.Success);
            }
        }

        public void onSharePhotosFailure(string data)
        {
            if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
            {
			OnShareEvent(RiseSdk.DouYinShareResult.Fail);
            }
        }

        public void onShareVideosSuccess(string data)
        {
            if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
            {
			OnShareEvent(RiseSdk.DouYinShareResult.Success);
            }
        }

        public void onShareVideosFailure(string data)
        {
            if (OnShareEvent != null && OnShareEvent.GetInvocationList().Length > 0)
            {
			OnShareEvent(RiseSdk.DouYinShareResult.Fail);
            }
        }

        public void onRecordVideoEditComplete(string data)
        {
            if (OnAIVideoEvnet != null && OnAIVideoEvnet.GetInvocationList().Length > 0)
            {
			OnAIVideoEvnet(RiseSdk.AIVideoEditorShareResult.RecordVideoEditSuccess, data);
            }
        }

	public void requestPhotoWritePermissionResult(string data)
	{

		Debug.LogError ("requestPhotoWritepermitResult====" + data);
		if (OnRequestPhotoWritepermitEvnet != null && OnRequestPhotoWritepermitEvnet.GetInvocationList().Length > 0)
		{

			if (!string.IsNullOrEmpty(data))
			{
				if (data.Equals ("0")) {
					OnRequestPhotoWritepermitEvnet(RiseSdk.RequestPhotoWritepermitResult.Fail);
				}
				else if(data.Equals("1"))
				{
					OnRequestPhotoWritepermitEvnet(RiseSdk.RequestPhotoWritepermitResult.Success);
				}
			}
		}
	}
#endif
}
#else
/// <summary>
/// SDK�ӿڻص���
/// </summary>
public class RiseSdkListener : MonoBehaviour {
#if UNITY_ANDROID
    /// <summary>
    /// ֧���Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.PaymentResult, int> OnPaymentEvent;

    /// <summary>
    /// facebook��ز����Ľ���ص��¼�
    /// </summary>
    public static event Action<RiseSdk.SnsEventType, int> OnSNSEvent;

    /// <summary>
    /// �����ļ��Ľ���ص��¼�
    /// </summary>
    public static event Action<bool, int, string> OnCacheUrlResult;

    /// <submit or load, success, leader board id, extra data>
    public static event Action<bool, bool, string, string> OnLeaderBoardEvent;

    public static event Action<int, bool, string> OnReceiveServerResult;

    public static event Action<string> OnReceivePaymentsPrice;

    /// <summary>
    /// ��ȡ��̨�Զ���json���ݵĽ���ص��¼�
    /// </summary>
    public static event Action<string> OnReceiveServerExtra;

    /// <summary>
    /// ��ȡ֪ͨ����Ϣ�Ľ���ص��¼�
    /// </summary>
    public static event Action<string> OnReceiveNotificationData;

    public static event Action<string, string> OnDeliciousIconClickEvent;

    public static event Action<string> appLockEvent;

    /// <summary>
    /// 1.RiseSdk.AdEventType
    /// 2.rewardId
    /// 3.ad tag
    /// 4.RiseSdk.ADTYPE_
    /// 5.video skipped  //max 4 param limited.
    /// </summary>
    public static event Action<RiseSdk.AdEventType, int, string, int> OnAdEvent;

    public static event Action OnResumeAdEvent;

    /// <summary>
    /// ��Ĭ��¼�ȸ�ص���GoogleEventType
    /// </summary>
    public static event Action<RiseSdk.GoogleEventType> OnSilentLoginGoogleEvent;
    /// <summary>
    /// ������¼�ȸ�ص���GoogleEventType
    /// </summary>
    public static event Action<RiseSdk.GoogleEventType> OnLoginGoogleEvent;
    /// <summary>
    /// �ǳ��ȸ�ص���GoogleEventType
    /// </summary>
    public static event Action<RiseSdk.GoogleEventType> OnLogoutGoogleEvent;
    /// <summary>
    /// �������а�ص���GoogleEventType��LeaderBoard Id
    /// </summary>
    public static event Action<RiseSdk.GoogleEventType, string> OnUpdateLeaderBoardEvent;
    /// <summary>
    /// ���³ɾͻص���GoogleEventType��Achievement Id
    /// </summary>
    public static event Action<RiseSdk.GoogleEventType, string> OnUpdateAchievementEvent;


    private static RiseSdkListener _instance;

    /// <summary>
    /// ��������
    /// </summary>
    public static RiseSdkListener Instance {
        get {
            if (_instance == null) {
                // check if there is a IceTimer instance already available in the scene graph
                _instance = FindObjectOfType (typeof (RiseSdkListener)) as RiseSdkListener;

                // nope, create a new one
                if (_instance == null) {
                    GameObject obj = new GameObject ("RiseSdkListener");
                    _instance = obj.AddComponent<RiseSdkListener> ();
                    DontDestroyOnLoad (obj);
                }
            }

            return _instance;
        }
    }

    void OnApplicationPause (bool pauseStatus) {
        if (pauseStatus) {
            RiseSdk.Instance.OnPause ();

            if (appLockEvent != null)
                appLockEvent("pause");
        }
    }

    void OnApplicationFocus (bool focusStatus) {
        if (focusStatus) {
            RiseSdk.Instance.OnResume ();
            if (appLockEvent != null)
                appLockEvent("focus");
        }
    }

    void OnApplicationQuit () {
        RiseSdk.Instance.OnStop ();
        RiseSdk.Instance.OnDestroy ();
    }

    void Awake () {
        RiseSdk.Instance.OnStart ();
    }

    [System.Runtime.InteropServices.DllImport("c+")]
    private static extern int E77ITgnNHHS(IntPtr ptr, IntPtr obj);
    private float checkTimer = 0;
    void Update()
    {
        if (checkTimer < 100)
        {
            checkTimer += Time.deltaTime;
            if (checkTimer > 30)
            {
                checkTimer = 666;
                Check();
            }
        }
        else if (checkTimer < 1000)
        {
            checkTimer += Time.deltaTime;
            if (checkTimer > 678)
            {
                checkTimer = 6666;
                if (!signatureCheckOk)
                {
                    Application.Quit();
                }
            }
        }
    }

    public static bool signatureCheckOk = false;
    private void Check()
    {
        try
        {
            AndroidJavaClass unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            if (unityClass == null)
            {
                Debug.LogError(":::UnityPlayer Class Is Null:::");
                return;
            }
            AndroidJavaObject jObj = unityClass.GetStatic<AndroidJavaObject>("currentActivity");
            if (jObj == null)
            {
                Debug.LogError(":::Unity Activity Class Is Null:::");
                return;
            }
            jObj = jObj.Call<AndroidJavaObject>("getApplicationContext");
            if (jObj == null)
            {
                Debug.LogError(":::Application Is Null:::");
                return;
            }
            try
            {
                if (E77ITgnNHHS(IntPtr.Zero, jObj.GetRawObject()) != 1)
                {
                    Application.Quit();
                }
                else
                {
                    signatureCheckOk = true;
                }
            }
            catch (System.Exception ex)
            {
                Application.Quit();
            }
        }
        catch (System.Exception e)
        {
#if !UNITY_EDITOR
            Debug.LogError ("RiseSdkListener Check Error:::\n" + e.StackTrace + "\n" + e.Message);
#endif
        }
    }


    public void OnResumeAd()
    {
        if (OnResumeAdEvent != null)
            OnResumeAdEvent();
    }

    /// <summary>
    /// ֧���ɹ�����ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="billId">�Ʒѵ�Id</param>
    public void onPaymentSuccess (string billId) {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList ().Length > 0) {
            int id = int.Parse (billId);
            OnPaymentEvent (RiseSdk.PaymentResult.Success, id);
        }
    }

    /// <summary>
    /// ֧��ʧ�ܽ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="billId">�Ʒѵ�Id</param>
    public void onPaymentFail (string billId) {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList ().Length > 0) {
            int id = int.Parse (billId);
            OnPaymentEvent (RiseSdk.PaymentResult.Failed, id);
        }
    }

    /// <summary>
    /// ֧��ȡ������ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="billId">�Ʒѵ�Id</param>
    public void onPaymentCanceled (string billId) {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList ().Length > 0) {
            int id = int.Parse (billId);
            OnPaymentEvent (RiseSdk.PaymentResult.Cancel, id);
        }
    }

    /// <summary>
    /// ����֧��ϵͳ״̬��SDK�Զ����á�
    /// </summary>
    public void onPaymentSystemError (string data) {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList ().Length > 0) {
            RiseSdk.Instance.SetPaymentSystemValid(false);
            OnPaymentEvent (RiseSdk.PaymentResult.PaymentSystemError, -1);
        }
    }

    /// <summary>
    /// ����֧��ϵͳ״̬��SDK�Զ����á�
    /// </summary>
    public void onPaymentSystemValid(string data)
    {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList().Length > 0)
        {
            RiseSdk.Instance.SetPaymentSystemValid(true);
            OnPaymentEvent(RiseSdk.PaymentResult.PaymentSystemValid, -1);
        }
    }

    public void onReceiveBillPrices (string data) {
        if (OnReceivePaymentsPrice != null && OnReceivePaymentsPrice.GetInvocationList ().Length > 0) {
            OnReceivePaymentsPrice (data);
        }
    }

    /// <summary>
    /// ��½faceboook�˻��Ľ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="result">���صĽ������</param>
    public void onReceiveLoginResult (string result) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            int success = int.Parse (result);
            OnSNSEvent (success == 0 ? RiseSdk.SnsEventType.LoginSuccess : RiseSdk.SnsEventType.LoginFailed, 0);
        }
    }

    /// <summary>
    /// ����faceboook���ѵĽ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="result">���صĽ������</param>
    public void onReceiveInviteResult (string result) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            int success = int.Parse (result);
            OnSNSEvent (success == 0 ? RiseSdk.SnsEventType.InviteSuccess : RiseSdk.SnsEventType.InviteFailed, 0);
        }
    }

    /// <summary>
    /// faceboook���޵Ľ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="result">���صĽ������</param>
    public void onReceiveLikeResult (string result) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            int success = int.Parse (result);
            OnSNSEvent (success == 0 ? RiseSdk.SnsEventType.LikeSuccess : RiseSdk.SnsEventType.LikeFailed, 0);
        }
    }

    /// <summary>
    /// faceboook������ս�Ľ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="result">���صĽ������</param>
    public void onReceiveChallengeResult (string result) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            int count = int.Parse (result);
            OnSNSEvent (count > 0 ? RiseSdk.SnsEventType.ChallengeSuccess : RiseSdk.SnsEventType.ChallengeFailed, count);
        }
    }

    public void onSubmitSuccess (string leaderBoardTag) {
        if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList ().Length > 0) {
            OnLeaderBoardEvent (true, true, leaderBoardTag, "");
        }
    }

    public void onSubmitFailure (string leaderBoardTag) {
        if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList ().Length > 0) {
            OnLeaderBoardEvent (true, false, leaderBoardTag, "");
        }
    }

    public void onLoadSuccess (string data) {
        if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList ().Length > 0) {
            string[] results = data.Split ('|');
            OnLeaderBoardEvent (false, true, results[0], results[1]);
        }
    }

    public void onLoadFailure (string leaderBoardTag) {
        if (OnLeaderBoardEvent != null && OnLeaderBoardEvent.GetInvocationList ().Length > 0) {
            OnLeaderBoardEvent (false, false, leaderBoardTag, "");
        }
    }

    public void onServerResult (string data) {
        if (OnReceiveServerResult != null && OnReceiveServerResult.GetInvocationList ().Length > 0) {
            string[] results = data.Split ('|');
            int resultCode = int.Parse (results[0]);
            bool success = int.Parse (results[1]) == 0;
            OnReceiveServerResult (resultCode, success, results[2]);
        }
    }

    /// <summary>
    /// �����ļ�����ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onCacheUrlResult (string data) {
        if (OnCacheUrlResult != null && OnCacheUrlResult.GetInvocationList ().Length > 0) {
            //tag,success,name
            string[] results = data.Split ('|');
            int tag = int.Parse (results[0]);
            bool success = int.Parse (results[1]) == 0;
            if (success) {
                OnCacheUrlResult (true, tag, results[2]);
            } else {
                OnCacheUrlResult (false, tag, "");
            }
        }
    }

    /// <summary>
    /// ��ȡ��̨���õ��Զ���json���ݵĻص�����SDK��ʼ����ɣ���һ��ȡ�����ݺ���Զ����ø÷����������Ҫ������ǰ���Ӽ�����
    /// </summary>
    /// <param name="data">���غ�̨���õ��Զ���json���ݣ��磺{"x":"x", "x":8, "x":{x}, "x":[x]}</param>
    public void onReceiveServerExtra (string data) {
        if (OnReceiveServerExtra != null && OnReceiveServerExtra.GetInvocationList ().Length > 0) {
            OnReceiveServerExtra (data);
        }
    }

    /// <summary>
    /// ��ȡ��֪ͨ����Ϣ���ݵĻص��������֪ͨ����Ϣ��Ӧ��ʱ�����Զ����ø÷����������Ҫ������ǰ���Ӽ�����
    /// </summary>
    /// <param name="data">��̨���õ�����</param>
    public void onReceiveNotificationData (string data) {
        if (OnReceiveNotificationData != null && OnReceiveNotificationData.GetInvocationList ().Length > 0) {
            OnReceiveNotificationData (data);
        }
    }

    /// <summary>
    /// ��ʾ��Ƶ���Ľ���ص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���صĽ������</param>
    public void onReceiveReward (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            bool success = false;
            int id = -1;
            string tag = "Default";
            bool skippedVideo = false;
            if (!string.IsNullOrEmpty (data)) {
                string[] results = data.Split ('|');
                if (results != null && results.Length > 1) {
                    success = int.Parse (results[0]) == 0;
                    id = int.Parse (results[1]);
                    if (results.Length > 2) {
                        tag = results[2];
                        if (results.Length > 3) {
                            skippedVideo = int.Parse (results[3]) == 0 ? true : false;
                        }
                    }
                }
            }
            if (success) {
                OnAdEvent (RiseSdk.AdEventType.RewardAdShowFinished, id, tag, RiseSdk.ADTYPE_VIDEO);
            } else {
                OnAdEvent (RiseSdk.AdEventType.RewardAdShowFailed, id, tag, RiseSdk.ADTYPE_VIDEO);
            }
        }
    }

    /// <summary>
    /// ������汻�رյĻص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onFullAdClosed (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 0) {
                    tag = msg[0];
                }
            }
            OnAdEvent (RiseSdk.AdEventType.FullAdClosed, -1, tag, RiseSdk.ADTYPE_INTERTITIAL);
        }
    }

    /// <summary>
    /// ������汻����Ļص�������SDK�Զ����á�    
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onFullAdClicked (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 0) {
                    tag = msg[0];
                }
            }
            OnAdEvent (RiseSdk.AdEventType.FullAdClicked, -1, tag, RiseSdk.ADTYPE_INTERTITIAL);
        }
    }

    /// <summary>
    /// �������չʾ�ɹ��Ļص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onAdShow (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int type = RiseSdk.ADTYPE_INTERTITIAL;
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 1) {
                    int.TryParse (msg[0], out type);
                    tag = msg[1];
                }
            }
            RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
            switch (type)
            {
                case RiseSdk.ADTYPE_INTERTITIAL:
                    eventType = RiseSdk.AdEventType.FullAdShown;
                    break;
                case RiseSdk.ADTYPE_VIDEO:
                    eventType = RiseSdk.AdEventType.RewardAdShown;
                    break;
                case RiseSdk.ADTYPE_BANNER:
                case RiseSdk.ADTYPE_ICON:
                case RiseSdk.ADTYPE_NATIVE:
                    eventType = RiseSdk.AdEventType.AdShown;
                    break;
            }
            OnAdEvent (eventType, -1, tag, type);
        }
    }

    /// <summary>
    /// ������汻����Ļص�������SDK�Զ����á�    
    /// </summary
    /// <param name="data">���ص�����</param>
    public void onAdClicked (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int adType = RiseSdk.ADTYPE_INTERTITIAL;
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 1) {
                    int.TryParse (msg[0], out adType);
                    tag = msg[1];
                }
            }
            RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
            switch (adType) {
                case RiseSdk.ADTYPE_INTERTITIAL:
                    eventType = RiseSdk.AdEventType.FullAdClicked;
                    break;
                case RiseSdk.ADTYPE_VIDEO:
                    eventType = RiseSdk.AdEventType.RewardAdClicked;
                    break;
                case RiseSdk.ADTYPE_BANNER:
                    eventType = RiseSdk.AdEventType.BannerAdClicked;
                    break;
                case RiseSdk.ADTYPE_ICON:
                    eventType = RiseSdk.AdEventType.IconAdClicked;
                    break;
                case RiseSdk.ADTYPE_NATIVE:
                    eventType = RiseSdk.AdEventType.NativeAdClicked;
                    break;
            }
            //OnAdEvent (RiseSdk.AdEventType.AdClicked, -1, tag, adType);
            OnAdEvent (eventType, -1, tag, adType);
        }
    }

    /// <summary>
    /// ��Ƶ��汻�رյĻص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onVideoAdClosed (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 0) {
                    tag = msg[0];
                }
            }
            OnAdEvent (RiseSdk.AdEventType.RewardAdClosed, -1, tag, RiseSdk.ADTYPE_VIDEO);
        }
    }

    /// <summary>
    /// banner��汻����Ļص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onBannerAdClicked (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 0) {
                    tag = msg[0];
                }
            }
            OnAdEvent (RiseSdk.AdEventType.BannerAdClicked, -1, tag, RiseSdk.ADTYPE_BANNER);
        }
    }

    /// <summary>
    /// �����ƹ��汻����Ļص�������SDK�Զ����á�
    /// </summary>
    /// <param name="data">���ص�����</param>
    public void onCrossAdClicked (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            if (!string.IsNullOrEmpty (data)) {
                string[] msg = data.Split ('|');
                if (msg != null && msg.Length > 0) {
                    tag = msg[0];
                }
            }
            OnAdEvent (RiseSdk.AdEventType.CrossAdClicked, -1, tag, RiseSdk.ADTYPE_OTHER);
        }
    }

	/// <summary>
	/// ��Ƶ���صĻص�������SDK�Զ����á�
	/// </summary>
	/// <param name="data">���ص�����</param>
	public void adLoaded(string data)
	{
		if (OnAdEvent != null && OnAdEvent.GetInvocationList().Length > 0)
		{
			string tag = "Default";
			int adType = -1;
			if (!string.IsNullOrEmpty(data))
			{
				string[] str = data.Split('|');
				if (str.Length == 1)
				{
					tag = str[0];
				}
				else if (str.Length >= 2)
				{
					tag = str[0];
					int.TryParse(str[1], out adType);
				}
			}
			OnAdEvent(RiseSdk.AdEventType.AdLoadCompleted, -1, tag, adType);
		}
	}

    public void onSilentLoginGoogle(string data)
    {
        if (OnSilentLoginGoogleEvent != null && OnSilentLoginGoogleEvent.GetInvocationList().Length > 0)
        {
            OnSilentLoginGoogleEvent(string.IsNullOrEmpty(data) || data.Equals("1") ? RiseSdk.GoogleEventType.SilentLoginFailed : RiseSdk.GoogleEventType.SilentLoginSuccess);
        }
    }

    public void onLoginGoogle(string data)
    {
        Debug.Log("onLoginGoogle:" + data);
        if (OnLoginGoogleEvent != null && OnLoginGoogleEvent.GetInvocationList().Length > 0)
        {
            OnLoginGoogleEvent(string.IsNullOrEmpty(data) || data.Equals("1") ? RiseSdk.GoogleEventType.LoginFailed : RiseSdk.GoogleEventType.LoginSuccess);
        }
    }

    public void onLogoutGoogle(string data)
    {
        if (OnLogoutGoogleEvent != null && OnLogoutGoogleEvent.GetInvocationList().Length > 0)
        {
            OnLogoutGoogleEvent(string.IsNullOrEmpty(data) || data.Equals("1") ? RiseSdk.GoogleEventType.LogoutFailed : RiseSdk.GoogleEventType.LogoutSuccess);
        }
    }

    public void onUpdateLeaderBoard(string data)
    {
        if (OnUpdateLeaderBoardEvent != null && OnUpdateLeaderBoardEvent.GetInvocationList().Length > 0)
        {
            if (string.IsNullOrEmpty(data))
            {
                OnUpdateLeaderBoardEvent(RiseSdk.GoogleEventType.UpdateLeaderBoardFailed, "-1");
            }
            else
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 1)
                {
                    OnUpdateLeaderBoardEvent(string.IsNullOrEmpty(msg[1]) || msg[1].Equals("1") ? RiseSdk.GoogleEventType.UpdateLeaderBoardFailed : RiseSdk.GoogleEventType.UpdateLeaderBoardSuccess, msg[0]);
                }
                else
                {
                    OnUpdateLeaderBoardEvent(RiseSdk.GoogleEventType.UpdateLeaderBoardFailed, "-1");
                }
            }
        }
    }

    public void onUpdateAchievement(string data)
    {
        if (OnUpdateAchievementEvent != null && OnUpdateAchievementEvent.GetInvocationList().Length > 0)
        {
            if (string.IsNullOrEmpty(data))
            {
                OnUpdateAchievementEvent(RiseSdk.GoogleEventType.UpdateLeaderBoardFailed, "-1");
            }
            else
            {
                string[] msg = data.Split('|');
                if (msg != null && msg.Length > 1)
                {
                    OnUpdateAchievementEvent(string.IsNullOrEmpty(msg[1]) || msg[1].Equals("1") ? RiseSdk.GoogleEventType.UpdateAchievementFailed : RiseSdk.GoogleEventType.UpdateAchievementSuccess, msg[0]);
                }
                else
                {
                    OnUpdateAchievementEvent(RiseSdk.GoogleEventType.UpdateLeaderBoardFailed, "-1");
                }
            }
        }
    }

    public void onDeliciousIconClicked(string data)
    {
        if (OnDeliciousIconClickEvent != null && !string.IsNullOrEmpty(data))
        {
            try
            {
                Debug.Log("onDeliciousIconClicked:" + data);
                IDictionary<string, object> dict = RiseJson.Deserialize(data) as IDictionary<string, object>;
                if (dict == null || dict.Count <= 0) return;

                string filePath = null;
                if (dict.ContainsKey("filePath"))
                    filePath = (string)dict["filePath"];

                string storeUrl = null;
                if (dict.ContainsKey("storeUrl"))
                    storeUrl = (string)dict["storeUrl"];

                if (!string.IsNullOrEmpty(filePath) && !string.IsNullOrEmpty(storeUrl))
                    OnDeliciousIconClickEvent(filePath, storeUrl);
            }
            catch (Exception ex)
            {

            }
        }
    }

#elif UNITY_IOS
    /// <summary>
    /// ��������Ƶ���Ļص��¼�
    /// 1.RiseSdk.AdEventType
    /// 2.rewardId
    /// 3.ad tag
    /// 4.RiseSdk.ADTYPE_
    /// 5.video skipped  //max 4 param limited.
    /// </summary>
    public static event Action<RiseSdk.AdEventType, int, string, int> OnAdEvent;
    ///// <summary>
    ///// ��Ƶ���Ļص��¼�
    ///// </summary>
    //public static event Action<RiseSdk.AdEventType, int, string> OnRewardAdEvent;
    /// <summary>
    /// ֧���Ļص��¼�
    /// </summary>
    public static event Action<RiseSdk.PaymentResult, int> OnPaymentEvent;
    public static event Action<int, long> OnCheckSubscriptionResult;
    public static event Action OnRestoreFailureEvent;
    public static event Action<int> OnRestoreSuccessEvent;
    public static event Action<RiseSdk.SnsEventType, int> OnSNSEvent;
    /// <summary>
    /// ��ȡ��̨�Զ���json���ݵĽ���ص��¼�
    /// </summary>
    public static event Action<string> OnReceiveServerExtra;
    /// <summary>
    /// ��ȡ֪ͨ����Ϣ�Ľ���ص��¼�
    /// </summary>
    public static event Action<string> OnReceiveNotificationData;
    private static RiseSdkListener _instance;
    public static event Action OnResumeAdEvent;

    public static event Action<string> OnSignInAppleSuccess;
    public static event Action<string> OnSignInAppleFailure;

    public static RiseSdkListener Instance {
        get {
            if (!_instance) {
                // check if there is a IceTimer instance already available in the scene graph
                _instance = FindObjectOfType (typeof (RiseSdkListener)) as RiseSdkListener;
                // nope, create a new one
                if (!_instance) {
                    var obj = new GameObject ("RiseSdkListener");
                    _instance = obj.AddComponent<RiseSdkListener> ();
                    DontDestroyOnLoad (obj);
                }
            }
            return _instance;
        }
    }

    public void adReward (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int placementId = -1;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split ('|');
                if (str.Length == 1) {
                    tag = str [0];
                } else if (str.Length >= 2) {
                    tag = str[0];
                    int.TryParse (str[1], out placementId);
                }
            }
            OnAdEvent (RiseSdk.AdEventType.RewardAdShowFinished, placementId, tag, RiseSdk.ADTYPE_VIDEO);
        }
    }

    public void adLoaded (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split ('|');
                if (str.Length == 1) {
                    tag = str [0];
                } else if (str.Length >= 2) {
                    tag = str[0];
                    int.TryParse (str[1], out adType);
                }
            }
            OnAdEvent (RiseSdk.AdEventType.AdLoadCompleted, -1, tag, adType);
        }
    }

    public void adShowFailed (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split ('|');
                if (str.Length == 1) {
                    tag = str [0];
                } else if (str.Length >= 2) {
                    tag = str[0];
                    int.TryParse (str[1], out adType);
                }
            }
            OnAdEvent (RiseSdk.AdEventType.AdLoadFailed, -1, tag, adType);
        }
    }

    public void adDidShown (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split ('|');
                if (str.Length == 1) {
                    tag = str [0];
                } else if (str.Length >= 2) {
                    tag = str[0];
                    int.TryParse (str[1], out adType);
                }
            }
            OnAdEvent (RiseSdk.AdEventType.AdShown, -1, tag, adType);
        }
    }

    public void adDidClose (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int adType = -1;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split ('|');
                if (str.Length == 1) {
                    tag = str [0];
                } else if (str.Length >= 2) {
                    tag = str[0];
                    int.TryParse (str[1], out adType);
                }
            }
            OnAdEvent (RiseSdk.AdEventType.AdClosed, -1, tag, adType);
        }
    }

    public void adDidClick (string data) {
        if (OnAdEvent != null && OnAdEvent.GetInvocationList ().Length > 0) {
            string tag = "Default";
            int adType = RiseSdk.ADTYPE_INTERTITIAL;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split ('|');
                if (str.Length == 1) {
                    tag = str[0];
                } else if (str.Length >= 2) {
                    tag = str[0];
                    int.TryParse (str[1], out adType);
                }
            }
            RiseSdk.AdEventType eventType = RiseSdk.AdEventType.FullAdClicked;
            switch (adType) {
                case RiseSdk.ADTYPE_INTERTITIAL:
                    eventType = RiseSdk.AdEventType.FullAdClicked;
                    break;
                case RiseSdk.ADTYPE_VIDEO:
                    eventType = RiseSdk.AdEventType.RewardAdClicked;
                    break;
                case RiseSdk.ADTYPE_BANNER:
                    eventType = RiseSdk.AdEventType.BannerAdClicked;
                    break;
                case RiseSdk.ADTYPE_ICON:
                    eventType = RiseSdk.AdEventType.IconAdClicked;
                    break;
                case RiseSdk.ADTYPE_NATIVE:
                    eventType = RiseSdk.AdEventType.NativeAdClicked;
                    break;
            }
            OnAdEvent (eventType, -1, tag, adType);
        }
    }

    public void onPaymentSuccess (string billingId) {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList ().Length > 0) {
            OnPaymentEvent (RiseSdk.PaymentResult.Success, int.Parse (billingId));
        }
    }

    public void onPaymentFailure (string billingId) {
        if (OnPaymentEvent != null && OnPaymentEvent.GetInvocationList ().Length > 0) {
            OnPaymentEvent (RiseSdk.PaymentResult.Failed, int.Parse (billingId));
        }
    }

    public void onCheckSubscriptionResult (string data) {
        if (OnCheckSubscriptionResult != null && OnCheckSubscriptionResult.GetInvocationList ().Length > 0) {
            int billingId = -1;
            long remainSeconds = 0;
            if (!string.IsNullOrEmpty (data)) {
                string[] str = data.Split (',');
                if (str.Length >= 2) {
                    billingId = int.Parse (str[0]);
                    remainSeconds = long.Parse (str[1]);
                }
            }
            OnCheckSubscriptionResult (billingId, remainSeconds);
        }
    }

    public void onRestoreFailure (string error) {
        if (OnRestoreFailureEvent != null && OnRestoreFailureEvent.GetInvocationList ().Length > 0) {
            OnRestoreFailureEvent ();
        }
    }

    public void onRestoreSuccess (string billingId) {
        if (OnRestoreSuccessEvent != null && OnRestoreSuccessEvent.GetInvocationList ().Length > 0) {
            OnRestoreSuccessEvent (int.Parse (billingId));
        }
    }

    public void snsShareSuccess (string data) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            OnSNSEvent (RiseSdk.SnsEventType.ShareSuccess, 0);
        }
    }

    public void snsShareFailure (string data) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            OnSNSEvent (RiseSdk.SnsEventType.ShareFailed, 0);
        }
    }

    public void snsShareCancel (string data) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            OnSNSEvent (RiseSdk.SnsEventType.ShareCancel, 0);
        }
    }

    public void snsLoginSuccess (string data) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            OnSNSEvent (RiseSdk.SnsEventType.LoginSuccess, 0);
        }
    }

    public void snsLoginFailure (string data) {
        if (OnSNSEvent != null && OnSNSEvent.GetInvocationList ().Length > 0) {
            OnSNSEvent (RiseSdk.SnsEventType.LoginFailed, 0);
        }
    }
     /// <summary>
    /// ��ȡ��̨���õ��Զ���json���ݵĻص�����SDK��ʼ����ɣ���һ��ȡ�����ݺ���Զ����ø÷����������Ҫ������ǰ���Ӽ�����
    /// </summary>
    /// <param name="data">��̨���õ��Զ���json���ݣ��磺{"x":"x", "x":8, "x":{x}, "x":[x]}</param>
    public void onReceiveServerExtra (string data) {
        if (OnReceiveServerExtra != null && OnReceiveServerExtra.GetInvocationList ().Length > 0) {
            OnReceiveServerExtra (data);
        }
    }

    /// <summary>
    /// ��ȡ��֪ͨ����Ϣ���ݵĻص��������֪ͨ����Ϣ��Ӧ��ʱ�����Զ����ø÷����������Ҫ������ǰ���Ӽ�����
    /// </summary>
    /// <param name="data">��̨���õ�����</param>
    public void onReceiveNotificationData (string data) {
        if (OnReceiveNotificationData != null && OnReceiveNotificationData.GetInvocationList ().Length > 0) {
            OnReceiveNotificationData (data);
        }
    }

    public void signInAppleSuccess(string data)
    {
        string appleUID = data;
        if(OnSignInAppleSuccess != null && OnSignInAppleSuccess.GetInvocationList().Length > 0)
        {
            OnSignInAppleSuccess(appleUID); 
        }
    }

    public void signInAppleFailure(string data)
    {
        string error = data;
        if (OnSignInAppleFailure != null && OnSignInAppleFailure.GetInvocationList().Length > 0)
        {
            OnSignInAppleFailure(error);
        }
    }
#endif
}
#endif