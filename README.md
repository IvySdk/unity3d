# RiseSDK for CocosCreator

# 前言
[**default.json: sdk配置文件, 广告、计费、打点等功能均需要通过此文件配置；对各部分配置详细说明见文档最底部**](#default.json)

## 1, 添加引用
1. 添加default.json到native/engine/android/app/src/main/assets目录
2. 修改native/engine/android/app/build.gradle,添加一下配置

```js
        defaultConfig {
    //...
        manifestPlaceholders = [
                ivy_debug: true,
                din: false,
                gms_games_app_id  : "933591439403",
                google_admob_application_id: 'ca-app-pub-1914768831611213~5856809174',
                facebook_appId             : '419434508669986',
                facebook_clientToken: '003925c73e83fe389581f4700b43f16c'
        ]
    }
```
**Notice**
* ivy_debug                   : 是否是debug模式 
* din                          : 是否主动时配刘海屏 
* gms_games_app_id*            : google play games id 
* google_admob_application_id  : admob app id 
* facebook_appId              : facebook app id 
* facebook_clientToken         : facebook 客户端token 

* 如果您有使用proguard来混淆Java代码，需要添加以下规则：
* if you use proguard to obfuscate your java source code, you should add these rules to your proguard rules file:

**重要！！！**
在打包时务必将`AndroidManifest.xml`中的`UnityPlayerActivity`指向`com.android.client.UnityPlayerActivity `

```js
-dontwarn com.unity3d.**
-keep class com.android.** {
    <methods>;
}

-keep class android.support.** {
    *;
}

-keep class com.core.async.** {
    public *;
}

-keep class com.core.common.** {
    public *;
}

-keep class com.core.network.** {
    public *;
}

-keep class com.core.view.** {
    public *;
}
```
## 2, 添加firebase
1. 在Firebase控制台创建项目
2. 在Firebase项目中点击Android机器人图标，开始设置Android应用，添加正确的包名及签名文件的SHA-1
3. 点击注册应用
4. 在对应Android应用目录下下载配置文件(google-services.json),并放置在应用的模块(应用级)根目录中
5. 在根级build.gradle中添加
    
```js
   dependencies {
       //...
        classpath 'com.google.gms:google-services:4.3.0'
    }
```
6. 在模块(应用级)的build.gradle文件末尾添加插件

```js
apply plugin: 'com.google.gms.google-services'
```

## 3, 配置 Firebase crashlytics
1. 引入 Firebase crashlytics 插件
在项目Build.gradle中增加以下配置:
```js
        dependencies {
           //...
            classpath 'com.google.firebase:firebase-crashlytics-gradle:2.5.1'
        }
```
2. 引用Firebase crashlytics 插件
在项目主module的Build.gradle文件末尾增加以下配置
```js
apply plugin: 'com.google.firebase.crashlytics'
```
3. 主动记录异常信息
```js
RiseSdk.Instance.recordException(string err);//err 为异常信息
```


## 4, Initialize 初始化SDK
在第一个场景中的一个脚本中的Awake方法中调用RiseSdk.Instance.Init()方法 Call the Init function in a gameObject's Awake function in your initialize scene
```js
void Awake() {
  RiseSdk.Instance.Init();
  // when you want to use IAP or reward ad
  // then you should define this function
  // InitListeners();
}
```

## 5, ADs 广告
### 1，激励视频
* 如果你想使用视频奖励广告，你需要添加以下方法
    
```js
    RiseSdkListener.OnRewardAdEvent += 
    (
            RiseSdk.AdEventType result,//是否成功显示视频广告，
            //成功返回
            RiseSdk.AdEventType.RewardAdShowFinished，
            //失败返回
            RiseSdk.AdEventType.RewardAdShowFailed
        int rewardId//视频广告调用时机
    ) => {to do something};


    //判断视频广告是否加载完成
    bool yes = RiseSdk.Instance.HasRewardAd();
    if (yes) {
      setRewardButtonEnable();
    } else {
      setRewardButtonDisable();
    }

    显示视频广告
    RiseSdk.Instance.ShowRewardAd(rewardId);
```

* 如果有需要可以添加视频广告被关闭的回调：

```js
RiseSdkListener.OnAdEvent += 
(
    RiseSdk.AdEventType type//广告事件类型，需要判断是否为RiseSdk.AdEventType.VideoAdClosed
)  => {to do something};
```
    
### 2，插屏
* 如果你想使用插屏广告，你需要添加以下方法

```js
//当前是否存在插屏广告
RiseSdk.Instance.HasInterstitial(string tag)

//展示插屏广告
RiseSdk.Instance.ShowAd(string tag)
```
* 如果有需要可以添加大屏广告被点击和被关闭的回调：

```js
   RiseSdkListener.OnAdEvent += 
   (
     //RiseSdk.AdEventType type//广告事件类型，需要判断是否为
     RiseSdk.AdEventType.FullAdClosed（大屏广告被关闭）
     或RiseSdk.AdEventType.FullAdClicked（大屏广告被点击）
   ) => {to do something};
```
### 3，Banner

```js
RiseSdk.Instance.ShowBanner(RiseSdk.POS_BANNER_MIDDLE_BOTTOM)
RiseSdk.Instance.CloseBanner()
```
    

## 6, Firebase events 谷歌后台统计分析
* 事件打点

```js
//常规打点方式
/// <param name="category">需要统计的数据分类名称</param>
/// <param name="action">需要统计的数据属性名称</param>
/// <param name="label">数据的属性值</param>
/// <param name="value">一般传0</param>
RiseSdk.Instance.trackEvent(string:category, string action, string label, int value);

/**
*  @param event 事件名称
*  @param data  事件内容，用逗号分隔，ex: key1,value1,key2,value2...
*/
RiseSdk.Instance.trackEvent(string event, string data);
```
* 设置用户属性
`RiseSdk.Instance.setUserProperty(key: string, value: string); `


## 7, Firebase Remote Config 读取
* 获取对应类型值
```js
RiseSdk.getRemoteConfigInt(string key);

RiseSdk.getRemoteConfigLong(string key);

RiseSdk.getRemoteConfigDouble(string key);

RiseSdk.getRemoteConfigBoolean(string key);

RiseSdk.getRemoteConfigString(string key);
```

## 8, In-App billing 应用中内付费
* 如果你想使用google內付，你需要添加以下方法
```js
void InitListeners() {
  RiseSdkListener.OnPaymentEvent -= OnPaymentResult;
  RiseSdkListener.OnPaymentEvent += OnPaymentResult;
}

void OnPaymentResult(int resultCode, int billId) {
		switch (resultCode) {
		case RiseSdk.PAYMENT_RESULT_SUCCESS:
			switch (billId) {
			case 1:// the first billing Id success
				break;
			case 2:// the second billing Id success
				break;
			case 3:
				break;
			}
			Debug.LogError("On billing success : " + billId);
			break;

		case RiseSdk.PAYMENT_RESULT_FAILS:
			switch (billId) {
			case 1:
				break;
			}
			Debug.LogError("On billing failure : " + billId);
			break;

		case RiseSdk.PAYMENT_RESULT_CANCEL:
			break;
		}
}
```
* 调用付费接口
`RiseSdk.Instance.Pay(string billId);`
`RiseSdk.Instance.Pay(string billId, string payload);`

* 其它
1. 获取计费点信息

```js
- 获取单个计费点信息
RiseSdk.Instance.getPaymentData(int billId);
返回值示例：
{
    "id": "com.ivy.galaxyshooting.sky25",
    "type": "inapp",
    "price": "HK$78.00",
    "price_amount": 78.0,
    "currency": "HKD",
    "title": "unlock Hades (Galaxy sky shooting)",
    "desc": "unlock Hades"
}


- 获取所有计费点信息
RiseSdk.Instance.getPaymentDatas();
返回值示例：
    /// 22 23 等是计费点
    /// price_amount 一般用于计算折扣，当前的价格是打折后的价格，通过折扣来计算原价，展示给用户
{
     "22": {
         "id": "com.ivy.galaxyshooting.sky22",
         "type": "inapp",
         "price": "HK$15.00",
         "price_amount": 15.0,
         "currency": "HKD",
         "title": " remove Ads +2000 coins (Galaxy sky shooting)",
         "desc": " remove Ads +2000 coins"
     },
     "23": {
         "id": "com.ivy.galaxyshooting.sky23",
         "type": "subs",
         "price": "HK$15.00",
         "price_amount": 15.0,
         "currency": "HKD",
         "title": "VIP Privilege (Galaxy sky shooting)",
         "desc": "VIP Privilege"
     }
 }

```
2. 检测计费系统是否可用
`RiseSdk.Instance.IsPayEnabled();`
3. 查询计费点状态
`RiseSdk.Instance.HasPaid(int billId);`
4. 支付结果校验
服务端配置请参考
[GPP支付校验](GP支付验证接口.docx)
配置部分请参考
[计费](#计费)



## 9, Google 相关操作

* 是否已登录
`RiseSdk.Instance.IsGoogleLogin();`

* 登录
`RiseSdk.Instance.LoginGoogle();`
`RiseSdk.Instance.SilentLoginGoogle();`

* 退出登录
`RiseSdk.LogoutGoogle();`
如果需要监听退出登陆结果，请添加监听回调：
`RiseSdkListener.onLogoutGoogle`

* 更新排行榜
`RiseSdk.Instance.UpdateGoogleLeaderBoard(string leaderBoardId, long score);`
如果需要监听更新结果，请添加监听回调:
`RiseSdkListener.OnUpdateLeaderBoardEvent`

* 展示指定排行榜
`RiseSdk.Instance.ShowGoogleLeaderBoard(string leaderBoardId);`

* 展示所有排行榜(无回调)
`RiseSdk.Instance.ShowGoogleLeaderBoards();`

* 更新成就进度
`RiseSdk.Instance.UpdateGoogleAchievement(string achievementId, int step)`
如果需要监听更新结果，请添加监听回调：
`RiseSdkListener.OnUpdateAchievementEvent`

* 展示成就列表(无回调)
`RiseSdk.Instance.ShowGoogleAchievements()`



## 10, SNS facebook相关操作接口
如果你想使用facebook相关功能，需要添加以下方法

```js
void InitListeners() {
  RiseSdkListener.OnSNSEvent -= OnSNSEvent;
  RiseSdkListener.OnSNSEvent += OnSNSEvent;
}

void OnSNSEvent(bool success, int eventType, int extra) {
		switch (eventType) {
		case RiseSdk.SNS_EVENT_LOGIN:
			Debug.LogError ("login: " + success);
			break;

		case RiseSdk.SNS_EVENT_INVITE:
			Debug.LogError ("invite: " + success);
			break;

		case RiseSdk.SNS_EVENT_LIKE:
			Debug.LogError ("like success? " + success);
			break;

		case RiseSdk.SNS_EVENT_CHALLENGE:
			int friendsCount = extra;
			Debug.LogError ("challenge: " + friendsCount);
			break;
		}
	}
```

```js
//登陆facebook
RiseSdk.Instance.Login();

//登出facebook
RiseSdk.Instance.Logout();

//检测facebook是否登陆
RiseSdk.Instance.IsLogin();

//邀请facebook好友玩游戏
RiseSdk.Instance.Invite ();

// facebook点赞界面
RiseSdk.Instance.Like ();

// g获取我的faceook个人信息
string mestring = RiseSdk.Instance.Me ();
// friends is a Hashtable, {"id":"xxx", "name":"xxx", "picture":"/sdcard/.cache/xxxxx"}
object me = MiniJSON.jsonDecode (mestring);
//返回的json格式如下：
 {
 "id":"0000000000000000",//我的facebook账户id
 "name":"Me is me",//我的facebook账户名称
 "picture":"/data/empty_not_exists"//我的facebook账户个人图片本地保存的绝对路径
 }

//获取faceook朋友信息列表
string friendstring = RiseSdk.Instance.GetFriends ();
// friends is an ArrayList, [{"id":"xxx", "name":"xxxx", "picture":"/sdcard/.cache/xxxxx"}, ...]
object friends = MiniJSON.jsonDecode (friendstring);
 //返回的json格式如下：
 [
 {
 "id":"0000000000000001",//我的facebook好友1的账户id
 "name":"Friend 1",//我的facebook好友1的账户名称
 "picture":"/data/empty_not_exists1"//我的facebook好友1个人头像本地保存的绝对路径
 },
 {
  "id":"0000000000000002",//我的facebook好友2的账户id
 "name":"Friend 2",//我的facebook好友2的账户名称
 "picture":"/data/empty_not_exists2"//我的facebook好友2个人头像本地保存的绝对路径
 },
 {
 "id":"0000000000000003",//我的facebook好友3的账户id
 "name":"Friend 3",//我的facebook好友3的账户名称
 "picture":"/data/empty_not_exists3"//我的facebook好友3个人头像本地保存的绝对路径
 }
 ]

```

## 11, Firestore 云存档

### 登陆云存档
// provider 可选方式
//1. anonymously 匿名登陆
//2. play        需要在firebase后台开启play games 登陆
//3. facebook    需要在firebase后台开启Facebook 登陆
`RiseSDK.Instance.InitFirestoreAfterSignIn(string provider);`
如果需要监听登陆结果，请添加监听回调：
`RiseSdkListener.OnFirestoreConnected`
`RiseSdkListener.OnFirestoreConnectError`
`RiseSdkListener.OnFirestoreLinkError`

### 登出存档
`RiseSdk.Instance.Instance.SignOutFirestore();`

### 上传存档
`RiseSdk.Instance.SetFirestore(string collectionName, string jsonData);`
如果需要监听上传结果，请添加监听回调：
`RiseSdkListener.OnFirestoreSetSuccess`
`RiseSdkListener.OnFirestoreSetFail`


### 读取存档
`RiseSdk.Instance.ReadFirestore(string collectionName);`
`RiseSdk.Instance.ReadFirestore(string collectionName, string documentId);`
如果需要监听读取结果，请添加监听回调：
`RiseSdkListener.OnFirestoreReadData`
`RiseSdkListener.OnFirestoreReadFail`


### 更新存档
`RiseSdk.Instance.UpdateFirestore(string collectionName, string translationId, string jsonData);`
如果需要监听更新结果，请添加监听回调：
`RiseSdkListener.OnFirestoreUpdateSuccess`
`RiseSdkListener.OnFirestoreUpdateFail`


### 合并存档
`RiseSdk.Instance.MergeFirestore(string collectionName, string jsonData);`
如果需要监听登陆结果，请添加监听回调：
`RiseSdkListener.OnFirestoreMergeSuccess`
`RiseSdkListener.OnFirestoreMergeFail`

### 复制存档
`RiseSdk.Instance.SnapshotFirestore(string collectionName)`
如果需要监听复制结果，请添加监听回调：
`RiseSdkListener.OnFirestoreSnapshot`

### 删除存档
`RiseSdk.Instance.DeleteFirestore(string collectionName)`

## 12, Xsolla
1. [xsolla 后台配置部分请参考文档](https://developers.xsolla.com/zh/sdk/android/store/)
需要配置部分
* 商店 虚拟物品
* 登陆管理器，创建登陆管理器后，请选择**社交登陆并创建oauth2.0**
* 支付中心选择支持的支付方式，**在设置中设置重定向策略**
* 如有订阅付费需要，在订阅项中配置订阅计划
2. SDK 接口

```js
//查询是否支持xsolla
RiseSdk.Instance.IsXsollaSupport();
//查询登陆状态
RiseSdk.Instance.IsXsollaLoggedIn();
//登陆xsolla， 目前仅支持vk登陆
RiseSdk.Instance.LoginXsolla();
//退出登陆
RiseSdk.Instance.LogoutXsolla();
```
3. 监听登陆结果
**在支持xsolla的环境下，开启游戏时sdk会主动回调xsolla的登陆状态**
```js
  RiseSdkListener.onXsollaLoginState -= onXsollaLoginState;
  RiseSdkListener.onXsollaLoginState += onXsollaLoginState;
```
4. 计费配置
    
```js
   //default.json 中增加
    "xsolla": {
    "projectId": "252538",
    "oauthId": "8375"
      }
   //计费点配置
    "payment": {
    "checkout": {
      "billId": {
        "feename": "com.ivymerge.20gems",
        "repeat": 1,
        "usd": 1.99
      },
      "2": {
        "feename": "70coins",
        "repeat": 1,
        "usd": 4.99
      }
    }
  } 
  TIP:
  1. billId: 开发人员自定义的计费点编号，在pay接口中使用，用以付费对应计费点
  2. feename: 虚拟物品的sku、订阅计划的名称
  3. repeat: 1 为订阅商品，  0 为虚拟物品
  4. usd : 计费点的配置价格
```


## 13, Misc 其他
* 判断网络是否连接
`boolean isNetworkConnected = RiseSdk.Instance.isNetworkConnected();`
* 退出游戏（无回调）
`RiseSdk.Instance.exitGame();`
* 缓存 url（无回调）
`RiseSdk.Instance.cacheUrl(url: string);`
* 跳转评价
`RiseSdk.Instance.rate();`
* 原生 toast
`RiseSdk.Instance.toast(messageContent);`


## default.json
sdk配置文件,放置在`native/engine/android/app/src/main/assets`目录下，广告、计费、打点等功能均需要通过此文件配置
* 完整default.json 示例

```js
{
  "appflyers.devkey": "J6ejjnUP9fMkv29PqBuYzR",
  "requireFriends": true,
  "debug": false,
  "enableAfAdPing": false,
  "mixAdEvents": false,
  "api.top_user_advalue": "https://hda2k62cp0.execute-api.us-west-1.amazonaws.com/top_user_advalue",
  "ad.ecpm.url": "https://k3kp5yrim4.execute-api.us-west-1.amazonaws.com/default/ivy-ecpm-api",
  "providers": [],
  "data": {
    "push": [
      {
        "api": "firebase",
        "manual": 0,
        "topic": "",
        "push-server-url": "http://push.papermobi.com:7778/fcmpush"
      }
    ]
  },
  "sns": {
    "api": "facebook",
    "invite_url": "",
    "invite_preview_url": "",
    "like_url": "",
    "friends": true,
    "leader_board_url": "http://match3games1.iibingo.com/api"
  },
  "share": "https://play.google.com/store/apps/details?id=com.bubbleshooter.popbubbles.shootbubblesgame",
  "remoteconfig": {
    "PAM_ad_unit_android_banner": "ca-app-pub-1914768831611213/4106575741",
    "PAM_ad_unit_android_interstitial": "ca-app-pub-1914768831611213/2402557932",
    "PAM_ad_unit_android_rewarded": "ca-app-pub-1914768831611213/5419657410",
    "is_pam_banner": false,
    "is_pam_inter": false,
    "is_pam_video": false
  },
  "banner": [
    {
      "provider": "admob",
      "p": {
        "placement": "ca-app-pub-1914768831611213/4106575741"
      }
    }
  ],
  "full": [
    {
      "provider": "admob",
      "p": {
        "placement": "ca-app-pub-1914768831611213/2402557932"
      }
    }
  ],
  "video": [
    {
      "provider": "admob",
      "p": {
        "placement": "ca-app-pub-1914768831611213/5419657410"
      }
    }
  ],
  "adLoadTimeout": 10,
  "adRefreshInterval": 1800,
  "bannerLoadTimeoutSeconds": 5,
  "gen_events": {
    "interstitial_shown_2_in1day": [
      {
        "e1": "interstitial_shown",
        "v": 2,
        "op": ">=",
        "d": 1,
        "r": false
      }
    ],
    "S3_1D": [
      {
        "e1": "interstitial_shown",
        "v": 3,
        "op": ">=",
        "d": 1,
        "r": false
      }
    ],
    "S4_1D": [
      {
        "e1": "interstitial_shown",
        "v": 4,
        "op": ">=",
        "d": 1,
        "r": false
      }
    ],
    "S5_1D": [
      {
        "e1": "interstitial_shown",
        "v": 5,
        "op": ">=",
        "d": 1,
        "r": false
      }
    ],
    "interstitial_shown_2_in3day": [
      {
        "e1": "interstitial_shown",
        "v": 2,
        "op": ">=",
        "d": 3,
        "r": false
      }
    ],
    "video_shown_2_in1day": [
      {
        "e1": "video_shown",
        "v": 2,
        "op": ">=",
        "d": 1,
        "r": false
      }
    ],
    "video_shown_2_in3day": [
      {
        "e1": "video_shown",
        "v": 2,
        "op": ">=",
        "d": 3,
        "r": false
      }
    ]
  },
  "summary_events": {
    "op": [
      2,
      3
    ],
    "retention": [
      1,
      2,
      3,
      4,
      5,
      6,
      7
    ],
    "cv": {
      "count": [
        1,
        2,
        3,
        5,
        10,
        20
      ],
      "e": "video_completed"
    }
  },
  "adParallelRequests": 1,
  "adParallelWaitTime": 5,
  "dontShowFullPageAdsOnSlowConnection": false,
  "adFullScreenTimespan": 120,
  "ad": {
    "adNextLoadInterval": 15,
    "timeToWaitForAdToShowSeconds": 5,
    "updateBanner": [],
    "adDelayFirstInterstitialCallSec": 30,
    "adProvidersRefreshInMinutes": 60,
    "useBannerFingerPrinting": true,
    "useVideoClipPreloading": true,
    "rewardedClipsCaps": {
      "*": {
        "intervalHours": 24,
        "maxImpressions": 4
      }
    },
    "iLTS": 10,
    "bLTS": 10,
    "aC": {
      "iTs": [
        20,
        120
      ],
      "iPTs": [
        0,
        60
      ],
      "fIPT": 5,
      "fIPSS": 2,
      "iSTs": [
        {
          "f": "*",
          "t": "*"
        }
      ]
    }
  },
  "payment": {
    "checkout": {
      "1": {
        "feename": "25coins",
        "repeat": 1,
        "usd": 1.99
      },
      "2": {
        "feename": "70coins",
        "repeat": 1,
        "usd": 4.99
      },
      "key": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtpVDxkfuv2T6jkErdllxnHeMr4JqFucU+gontr0yYlvM4qt3PHlg1VY/1CQeLskBs2K420cQDaAIfCvAqil3VB6NWSci0SbMGkCidPQmlQRn90MCFAX3t+dRwgAlFwvlj4yVP53giV9GKfWVQ4EehWTg9h8fgRtILEEGgNFJMFz3MUS3erBBSOpuc9UDDtnvEo4NRaH6yUI4zUn46nrgskchYlUTeYEQo1y4lS3uPHpgriBk8XLxFIXSyKjHnnNwSeRSqeX6gEKUrzVpxzp122qv4ebdqPfyzxLUySqhkKkr3dkt45OP+/7xn3jYT5V8ClUA9GgoMwZv80bASfh9wwIDAQAB"
    }
  },
  "gts": 1692666353000,
  "gv": 1,
  "ver": 2,
  "appid": 2712,
  "v_api": 0,
  "token": "174f3dc7c2a0d2f56c9dba7946965fb6"
}
```
### 普通属性
* appid: 应用id
* v_api: 此配置版本号
* appflyers.devkey: af app id
* requireFriends:是否在facebook登陆是拉取朋友列表
* enableAfAdPing:是否开启自定义af广告收入统计事件
* mixAdEvents：交叉推广事件是否与正式广告事件合并
* api.top_user_advalue：用户分层价值事件，不需要修改
* push: 自定义应用内消息推送
* remoteconfig：key-value格式的预配置字段，在firebase remote config 获取失败时可使用
### 广告
广告类型包括 banner、full(大屏广告)、video(激励视频广告)
内部可配置属性：
- provider：广告平台，此sdk仅包含Admob
- p: 广告单元
- placement: 广告id

**tip:每种广告类型可配置多个广告单元:**
注：provider值中的 1x、2x 作为广告单元区分，在调用广告时区分广告对象

```js
  "video": [
    {
      "provider": "admob_1x",
      "p": {
        "placement": "ca-app-pub-1914768831611213/5419657410"
      }
    },
    {
      "provider": "admob_2x",
      "p": {
        "placement": "ca-app-pub-1914768831611213/5419657410"
      }
    }
  ]
```
* adLoadTimeout：广告加载自定义超时时间
* adRefreshInterval：banner广告自动刷新间隔，单位ms
* bannerLoadTimeoutSeconds： banner自定义加载超时时间
### 计费
所有计费点信息配置在 payment 字段结构中
* key：用于在计费点购买后的校验支付结果，若留空，则购买后不会校验购买结果，以google billing 返回状态为最终购买结果
* verify-url: 校验支付结果路径
* force-check: 开启强制检验， 可选值： 1-强制开启服务端校验；0-不校验支付结果(必须配置verify-url)；2-由客户端自行校验
* checkout：计费点信息列表，示例：

```js
//说明：
// billId：即每个计费点对应的序号，如 1，2；在程序中传递此序号用以支付对应计费点
// feename：google billing 后台创建的计费点名称
// repeat： 若值为 1 则为消耗型计费点；0 则为订阅型计费点
// usd：计费点价格
 "payment": {
    "checkout": {
      "1": {
        "feename": "25coins",
        "repeat": 1,
        "usd": 1.99
      },
      "2": {
        "feename": "70coins",
        "repeat": 1,
        "usd": 4.99
      },
      "key": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtpVDxkfuv2T6jkErdllxnHeMr4JqFucU+gontr0yYlvM4qt3PHlg1VY/1CQeLskBs2K420cQDaAIfCvAqil3VB6NWSci0SbMGkCidPQmlQRn90MCFAX3t+dRwgAlFwvlj4yVP53giV9GKfWVQ4EehWTg9h8fgRtILEEGgNFJMFz3MUS3erBBSOpuc9UDDtnvEo4NRaH6yUI4zUn46nrgskchYlUTeYEQo1y4lS3uPHpgriBk8XLxFIXSyKjHnnNwSeRSqeX6gEKUrzVpxzp122qv4ebdqPfyzxLUySqhkKkr3dkt45OP+/7xn3jYT5V8ClUA9GgoMwZv80bASfh9wwIDAQAB",
      "verify-url": "http://mergegamesone.iibingo.com/api/order/verifyGoogleOrderLocal",
      "force-check": 1,
    }
  }
```

### 转化事件
gen_events、summery_events 内配置用作app转化标记事件，可不修改


### 通过firebase remote config 下发配置
1. 整体下发
    * 将default.json内容作为字段 config_grid_data_android 的值配置
2. 仅下发Banner配置
    * 将banner配置作为字段 ad_config_banner 的值配置
    示例：
    
```js
{
    "ads":[
        {
          "provider": "admob",
          "p": {
            "placement": "ca-app-pub-1914768831611213/4106575741"
          }
        }
    ],
    "adRefreshInterval": 1800
}
```
3. 仅下发大屏广告配置
    * 将大屏广告配置作为字段 ad_config_full 的值配置
    示例：
    
```js
{
    "ads":[
        {
          "provider": "admob",
          "p": {
            "placement": "ca-app-pub-1914768831611213/2402557932"
          }
        }
    ]
}
```
4. 仅下发激励视频广告配置
    * 将激励视频广告配置作为字段 ad_config_video 的值配置
    
```js
{
    "ads":[
         {
          "provider": "admob",
          "p": {
            "placement": "ca-app-pub-1914768831611213/5419657410"
          }
        }
    ]
}
```
