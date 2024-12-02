# Required for Pafka
-keepattributes *Annotation*
-keepattributes Signature
# https://github.com/square/okio#proguard
-dontwarn okio.**

-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
    com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
    java.lang.String getId();
    boolean isLimitAdTrackingEnabled();
}
-keep public class com.android.installreferrer.** { *; }

-keep class com.bun.miitmdid.core.** {*;}

-keep class com.parfka.** {*;}