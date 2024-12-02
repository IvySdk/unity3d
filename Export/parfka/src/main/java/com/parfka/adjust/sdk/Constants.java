//
//  Constants.java
//  Adjust
//
//  Created by keyboardsurfer on 2013-11-08.
//  Copyright (c) 2012-2014 adjust GmbH. All rights reserved.
//  See the file MIT-LICENSE for copying permission.
//

package com.parfka.adjust.sdk;

/**
 * @author keyboardsurfer
 * @since 8.11.13
 */
public interface Constants {
    int ONE_SECOND = 1000;
    int ONE_MINUTE = 60 * ONE_SECOND;
    int THIRTY_MINUTES = 30 * ONE_MINUTE;
    int ONE_HOUR = 2 * THIRTY_MINUTES;

    int CONNECTION_TIMEOUT = Constants.ONE_MINUTE;
    int SOCKET_TIMEOUT = Constants.ONE_MINUTE;
    int MAX_WAIT_INTERVAL = Constants.ONE_MINUTE;

    String BASE_URL = "http://gateway.adsfall.com:8080";
//    String BASE_URL = "http://192.168.0.102:8080";
    String GDPR_URL = "https://gdpr.adsfall.com";
    String SCHEME = "http";
    String AUTHORITY = "gateway.adsfall.com";
    String CLIENT_SDK = "a0.9";
    String LOGTAG = "Parf";
    String REFTAG = "reftag";
    String INSTALL_REFERRER = "install_referrer";
    String DEEPLINK = "deeplink";
    String PUSH = "push";
    String THREAD_PREFIX = "Parf-";

    String ACTIVITY_STATE_FILENAME = "AdjustIoActivityState";
    String ATTRIBUTION_FILENAME = "AdjustAttribution";
    String SESSION_CALLBACK_PARAMETERS_FILENAME = "AdjustSessionCallbackParameters";
    String SESSION_PARTNER_PARAMETERS_FILENAME = "AdjustSessionPartnerParameters";

    String MALFORMED = "malformed";
    String SMALL = "small";
    String NORMAL = "normal";
    String LONG = "long";
    String LARGE = "large";
    String XLARGE = "xlarge";
    String LOW = "low";
    String MEDIUM = "medium";
    String HIGH = "high";
    String REFERRER = "referrer";

    String ENCODING = "UTF-8";
    String MD5 = "MD5";
    String SHA1 = "SHA-1";
    String SHA256 = "SHA-256";

    String CALLBACK_PARAMETERS = "callback_params";
    String PARTNER_PARAMETERS = "partner_params";

    int MAX_INSTALL_REFERRER_RETRIES = 2;

    String FB_AUTH_REGEX = "^(fb|vk)[0-9]{5,}[^:]*://authorize.*access_token=.*";
}
