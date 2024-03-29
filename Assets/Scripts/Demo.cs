using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Demo : MonoBehaviour
{


    void Awake()
    {
        RiseSdk.Instance.Init();
bool valid =RiseSdk.Instance.IsPaymentValid();
        Debug.Log($"{valid}");
    }

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }
}
