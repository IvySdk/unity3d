using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class Dem : MonoBehaviour
{


    void Awake()
    {
        RiseSdk.RiseSdk.Instance.OnInit();
        // #if UNITY_ANDROID
        RiseSdk.RiseSdkListener.OnGMSEvent += (eventType, str) =>
        {
            RiseSdk.RiseSdk.Instance.ToastMsg($"{eventType.ToString()} + {str}");
        };

        RiseSdk.RiseSdkListener.OnAdEvent += (eventType, id, str, h) =>
        {
            RiseSdk.RiseSdk.Instance.ToastMsg($"{eventType.ToString()} + {id} + {str} + {h}");
        };

        // #endif
    }

    // Start is called before the first frame update
    void Start()
    {

        transform.Find("rewarded").GetComponent<Button>().onClick.AddListener(() =>
        {
            Debug.Log("call show Reward Ad");
            RiseSdk.RiseSdk.Instance.ToastMsg("call show Reward Ad");
            // #if UNITY_ANDROID
            if (RiseSdk.RiseSdk.Instance.HasRewardAd())
            {
                RiseSdk.RiseSdk.Instance.ShowRewardAd(1);
            }
            else
            {
                RiseSdk.RiseSdk.Instance.ToastMsg("No Reward Ad");
            }
            // #elif UNITY_EDITOR
            //     RiseSdk.RiseSdk.Instance.ShowRewardAd(1);
            // #endif
            //  RiseSdk.Instance.callNoMethod();
        });
    }

    // Update is called once per frame
    void Update()
    {

    }
}
