package com.android.client;

import java.util.Map;

public interface IAppsflyerConversionListener {

    void onAfInitSuccess();

    void onAfInitFailed(int i, String s);

    void onAppOpenAttribution(Map<String, String> conversionData);

    void onConversionDataFail(String s);

    void onConversionDataSuccess(Map<String, Object> conversionData);

    void onAttributionFailure(String s);

}
