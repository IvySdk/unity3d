
using System;
using UnityEngine;

namespace RiseSdk
{

    public sealed class RiseSdk : AbstractRiseSdk
    {
        //其他广告类型
        public const int ADTYPE_OTHER = -1;
        //大屏广告类型
        public const int ADTYPE_INTERTITIAL = 1;
        //视频广告类型
        public const int ADTYPE_VIDEO = 2;
        //横幅广告类型
        public const int ADTYPE_BANNER = 3;
        //icon广告类型  ios has
        public const int ADTYPE_ICON = 4;
        //native广告类型
        public const int ADTYPE_NATIVE = 5;


        private static readonly Lazy<RiseSdk> _instance = new Lazy<RiseSdk>(() => new RiseSdk());

        private RiseSdk()
        {
#if UNITY_ANDROID
            RiseSdkListener.Instance.enabled = true;
            _riseSdk = new RiseSdkForAndroid();
#elif UNITY_IOS
            RiseSdkListener.Instance.enabled = true;
            _riseSdk = new RiseSdkForIOS();
#elif UNITY_EDITOR
            RiseSdkListener.Instance.enabled = true;
            _riseSdk = new RiseSdkForEditor();
#endif
        }

        public static RiseSdk Instance => _instance.Value;

        private AbstractRiseSdk _riseSdk = null;


        public override void OnInit()
        {
            _riseSdk?.OnInit();
        }

        public override void OnStart()
        {
            _riseSdk?.OnStart();
        }

        public override void OnResume()
        {
            _riseSdk?.OnResume();
        }

        public override void OnPause()
        {
            _riseSdk?.OnPause();
        }

        public override void OnStop()
        {
            _riseSdk?.OnStop();
        }

        public override void recordException(string title, string err)
        {
            _riseSdk?.recordException(title, err);
        }

        public override bool HasRewardAd()
        {
            if (_riseSdk == null) return false;
            return _riseSdk.HasRewardAd();
        }

        public override void ShowRewardAd(int rewardId)
        {
            Debug.Log("ShowRewardAd " + rewardId);
            _riseSdk?.ShowRewardAd(rewardId);
        }

        public override void ShowRewardAd(string tag, int rewardId)
        {
            _riseSdk?.ShowRewardAd(tag, rewardId);
        }

        public override void FireBaseTrackEvent(string category, string keyValueData)
        {
            _riseSdk?.FireBaseTrackEvent(category, keyValueData);
        }

        public override bool GetAdsEnabledState()
        {
            if (_riseSdk == null) return false;
            return _riseSdk.GetAdsEnabledState();
        }

        public override void SetAdsEnabledState(bool state)
        {
            _riseSdk?.SetAdsEnabledState(state);
        }

        public override void ToastMsg(string str)
        {
            _riseSdk?.ToastMsg(str);
        }



        /// <summary>
        /// 在左上角显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_LEFT_TOP = 1;
        /// <summary>
        /// 在顶部居中显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_MIDDLE_TOP = 3;
        /// <summary>
        /// 在右上角显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_RIGHT_TOP = 6;
        /// <summary>
        /// 在中间居中显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_MIDDLE_MIDDLE = 5;
        /// <summary>
        /// 在左下角显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_LEFT_BOTTOM = 2;
        /// <summary>
        /// 在底部居中显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_MIDDLE_BOTTOM = 4;
        /// <summary>
        /// 在右下角显示banner广告参数常量
        /// </summary>
        public const int POS_BANNER_RIGHT_BOTTOM = 7;
        public const int POS_BANNER_LEFT_MIDDLE = 8;
        public const int POS_BANNER_RIGHT_MIDDLE = 9;

        public enum AdEventType : int
        {
            FullAdLoadCompleted = 1,
            FullAdLoadFailed,
            RewardAdLoadFailed,
            RewardAdLoadCompleted,
            RewardAdShown,
            RewardAdShowFinished,
            RewardAdShowFailed,
            RewardAdClosed,
            RewardAdClicked,
            FullAdClosed,// 大屏广告被关闭
            FullAdShown,// 大屏广告展示成功
            FullAdClicked,// 大屏广告被点击
            BannerAdClicked,// bannner广告被点击
            CrossAdClicked,// 交叉推广广告被点击
            AdLoadCompleted,//广告加载成功(only ios)
            AdLoadFailed,//广告加载失败(only ios)
            AdShown,// 广告展示成功(大屏或bannner)(only ios)
            AdClosed,//广告被关闭(only ios)
            AdClicked,//广告被点击(only ios)
            IconAdClicked,
            NativeAdClicked
        }

        public enum AFEventType : int
        {
            InitSuccess = 1,
            InitFail,
            AppOpenAttribution,
            AttributionFailure,
            ConversionDataSuccess,
            ConversionDataFail
        }

        public enum GMSEventType : int
        {
            GMSPaid = 1
        }

        public enum PaymentResult : int
        {
            Success = 1,
            Failed,
            Cancel,
            PaymentSystemError,
            PaymentSystemValid
        }

        /// <summary>
        /// faceboook事件回调类型
        /// </summary>
        public enum SnsEventType : int
        {
            /// <summary>
            /// faceboook登陆成功
            /// </summary>
            LoginSuccess = 1,
            LoginFailed,
            /// <summary>
            /// faceboook邀请好友成功
            /// </summary>
            InviteSuccess,
            InviteFailed,
            /// <summary>
            /// faceboook挑战好友成功
            /// </summary>
            ChallengeSuccess,
            ChallengeFailed,
            /// <summary>
            /// faceboook给好友点赞成功
            /// </summary>
            LikeSuccess,
            LikeFailed,
            ShareSuccess,
            ShareFailed,
            ShareCancel
        }

        public enum LocalPushType : int
        {
            NoCycle = 0,
            YearCycle = 1 << 2,
            MonthCycle = 1 << 3,
            DayCycle = 1 << 4,
            HourCycle = 1 << 5,
            MinuteCycle = 1 << 6,
            SecondCycle = 1 << 7,
            WeekDayCycle = 1 << 9,
            WeekDayOrDinalCycle = 1 << 10
        }

        public enum FullAdType
        {
            once, //20
            back, //锁屏
            flash, //切屏
            settle, //结束
            none
        }

    }

}