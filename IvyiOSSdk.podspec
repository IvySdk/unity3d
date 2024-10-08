#
# Be sure to run `pod lib lint IvyiOSSdk.podspec' to ensure this is a
# valid spec before submitting.
#
# Any lines starting with a # are optional, but their use is encouraged
# To learn more about a Podspec see https://guides.cocoapods.org/syntax/podspec.html
#

Pod::Spec.new do |s|
  s.name             = 'IvyiOSSdk'
  s.version          = '1.0.6'
  s.summary          = 'A short description of IvyiOSSdk.'

# This description is used to generate tags and improve search results.
#   * Think: What does it do? Why did you write it? What is the focus?
#   * Try to keep it short, snappy and to the point.
#   * Write the description between the DESC delimiters below.
#   * Finally, don't worry about the indent, CocoaPods strips it!

  s.description      = <<-DESC
TODO: Add long description of the pod here.
                       DESC

  s.homepage         = 'https://github.com/siweiqi/IvyiOSSdk'
  # s.screenshots     = 'www.example.com/screenshots_1', 'www.example.com/screenshots_2'
  s.license          = { :type => 'MIT', :file => 'LICENSE' }
  s.author           = { 'siweiqi' => 'siweiqi@ivymobile.com' }
  s.source           = { :git => 'https://github.com/siweiqi/IvyiOSSdk.git', :tag => s.version.to_s }
  # s.social_media_url = 'https://twitter.com/<TWITTER_USERNAME>'

  s.ios.deployment_target = '12.0'
  s.static_framework = true  #  如果设置 use_frameworks!，需要改为静态库
  s.source_files = 'IvyiOSSdk/Classes/**/*'
#  s.frameworks = ['UIKit', 'CoreFoundation', 'QuartzCore', 'AVKit', 'AdServices', 'CoreTelephony', 'GameController', 'MediaPlayer', 'GameKit', 'iAd', 'Security', 'AdSupport']
#  s.xcconfig = { "FRAMEWORK_SEARCH_PATHS" => '$(inherited)', "LIBRARY_SEARCH_PATHS" => '$(inherited)', "HEADER_SEARCH_PATHS" => '$(inherited)', "OTHER_CFLAGS" => '$(inherited)', "OTHER_LDFLAGS" => '$(inherited)', "GCC_PREPROCESSOR_DEFINITIONS" => '$(inherited)', "VALID_ARCHS" => '$(inherited)' }
#  s.user_target_xcconfig = { "FRAMEWORK_SEARCH_PATHS" => '$(inherited)', "LIBRARY_SEARCH_PATHS" => '$(inherited)', "HEADER_SEARCH_PATHS" => '$(inherited)', "OTHER_CFLAGS" => '$(inherited)', "OTHER_LDFLAGS" => '$(inherited)', "GCC_PREPROCESSOR_DEFINITIONS" => '$(inherited)' }
  
  
  s.frameworks = 'UIKit', 'CoreFoundation', 'QuartzCore', 'AVKit', 'AdServices', 'StoreKit', 'SystemConfiguration', 'Security'
  s.xcconfig = { "OTHER_LDFLAGS" => '-ObjC' }
  s.libraries = 'sqlite3'
  
  
  s.dependency 'Google-Mobile-Ads-SDK', '10.10.0' #'10.6.0'
  #google.dependency 'PersonalizedAdConsent', '1.0.5'
  s.dependency 'GoogleMobileAdsMediationFyber', '8.2.8.0'
  s.dependency 'GoogleMobileAdsMediationIronSource', '7.6.0.0'
  #google.dependency 'GoogleMobileAdsMediationInMobi', '10.7.2.0'
  s.dependency 'GoogleMobileAdsMediationVungle', '7.3.1.0'
  s.dependency 'GoogleMobileAdsMediationFacebook', '6.14.0.0'
  s.dependency 'GoogleMobileAdsMediationUnity', '4.9.2.0'
  #s.dependency 'GoogleMobileAdsMediationChartboost', '9.7.0.0'
  s.dependency 'GoogleMobileAdsMediationAppLovin', '11.10.1.0'
  google.dependency 'GoogleMobileAdsMediationPangle', '5.1.0.6.0'
  #s.dependency 'GoogleMobileAdsMediationAdColony', '4.9.0.2'
  s.dependency 'GoogleMobileAdsMediationMintegral', '7.6.2.0'
  #google.dependency 'OpenWrapSDK', '3.2.0' #'2.8.0'
 # s.dependency 'AdMobPubMaticAdapter', '2.2.0'#, '2.1.1'
 # s.dependency 'smaato-ios-sdk', '22.6.0'
 # s.dependency 'smaato-ios-sdk-mediation-admob', '10.10.0.0'
  s.dependency 'GoogleUserMessagingPlatform', '2.5.0'

  #s.dependency 'YandexMobileAds', '5.9.1'
  
  s.dependency 'Firebase/Core', '10.24.0'
  s.dependency 'Firebase/Analytics', '10.24.0'
  
  s.dependency 'FBSDKCoreKit', '16.3.1'
  
  s.dependency 'AppsFlyerFramework', '6.13.2'
  
  s.xcconfig = { "GCC_PREPROCESSOR_DEFINITIONS" => 'GOOGLE=1' }
  
  
#  s.subspec 'Common' do |common|
#    common.libraries = 'sqlite3'
#    common.source_files = 'IvyiOSSdk/Classes/Common/**/*'
#    common.public_header_files = 'IvyiOSSdk/Classes/Common/**/*.h'
#    common.frameworks = 'StoreKit', 'SystemConfiguration', 'Security'
##    common.dependency 'SocketRocket', '0.7.0'
#  end
#  
#  s.subspec 'Core' do |core|
#      core.source_files = 'IvyiOSSdk/Classes/Core/**/*'
#      core.public_header_files = 'IvyiOSSdk/Classes/Core/**/*.h*'
#      core.dependency 'IvyiOSSdk/Common'
##      core.resource_bundles = {
##          'IvyiOSSdk-Core' => ['IvyiOSSdk/Assets/Core/*.png', 'IvyiOSSdk/Assets/Core/*.xib', 'IvyiOSSdk/Assets/Core/LocalizedStrings/*.lproj/*']
##      }
#      core.frameworks = 'CoreTelephony', 'GameController', 'MediaPlayer', 'GameKit', 'MessageUI', 'iAd', 'Security'
##      core.prefix_header_contents = '#import <IvyiOSSdk/NSString+Base64.h>'
##      core.xcconfig = { "GCC_PREPROCESSOR_DEFINITIONS" => 'MODULES=1' }
##      core.xcconfig = { "FRAMEWORK_SEARCH_PATHS" => '$(inherited)', "LIBRARY_SEARCH_PATHS" => '$(inherited)', "HEADER_SEARCH_PATHS" => '$(inherited)', "OTHER_CFLAGS" => '$(inherited)', "OTHER_LDFLAGS" => '$(inherited)', "GCC_PREPROCESSOR_DEFINITIONS" => '$(inherited)' }
##      core.user_target_xcconfig = { "FRAMEWORK_SEARCH_PATHS" => '$(inherited)', "LIBRARY_SEARCH_PATHS" => '$(inherited)', "HEADER_SEARCH_PATHS" => '$(inherited)', "OTHER_CFLAGS" => '$(inherited)', "OTHER_LDFLAGS" => '$(inherited)', "GCC_PREPROCESSOR_DEFINITIONS" => '$(inherited)' }
#  end
#  
#  s.subspec 'Google' do |google|
#      google.source_files = 'IvyiOSSdk/Classes/Google/**/*'
#      google.public_header_files= 'IvyiOSSdk/Classes/Google/**/*.h'
#     
#      google.dependency 'Google-Mobile-Ads-SDK', '10.10.0' #'10.6.0'
#      #google.dependency 'PersonalizedAdConsent', '1.0.5'
#      google.dependency 'GoogleMobileAdsMediationFyber', '8.2.4.0'
#      google.dependency 'GoogleMobileAdsMediationIronSource', '7.6.0.0'
#      #google.dependency 'GoogleMobileAdsMediationInMobi', '10.5.4.0'
#      google.dependency 'GoogleMobileAdsMediationVungle', '7.2.0.0'
#      google.dependency 'GoogleMobileAdsMediationFacebook', '6.14.0.0'
#      google.dependency 'GoogleMobileAdsMediationUnity', '4.9.2.0'
#      google.dependency 'GoogleMobileAdsMediationChartboost', '9.5.1.0'
#      google.dependency 'GoogleMobileAdsMediationAppLovin', '11.10.1.0'
#      #google.dependency 'GoogleMobileAdsMediationPangle', '5.1.0.6.0'
#      google.dependency 'GoogleMobileAdsMediationAdColony', '4.9.0.2'
#      google.dependency 'GoogleMobileAdsMediationMintegral', '7.4.8.0'
#      #google.dependency 'OpenWrapSDK', '3.2.0' #'2.8.0'
#      google.dependency 'AdMobPubMaticAdapter', '2.2.0'#, '2.1.1'
#      google.dependency 'smaato-ios-sdk', '22.6.0'
#      google.dependency 'smaato-ios-sdk-mediation-admob', '10.10.0.0'
#      google.dependency 'GoogleUserMessagingPlatform', '2.1.0'
#      google.dependency 'IvyiOSSdk/Core'
#      google.xcconfig = { "GCC_PREPROCESSOR_DEFINITIONS" => 'GOOGLE=1' }
#  end
#  
#  s.subspec 'Firebase' do |firebase|
#      firebase.source_files = 'IvyiOSSdk/Classes/Firebase/**/*'
#      firebase.public_header_files = 'IvyiOSSdk/Classes/Firebase/**/*.h'
##      firebase.dependency 'BoringSSL-GRPC', '0.0.24'
#      firebase.dependency 'Firebase/Core', '10.19.0'
#      firebase.dependency 'Firebase/Analytics', '10.19.0'
#      firebase.dependency 'Firebase/RemoteConfig', '10.19.0'
#      firebase.dependency 'IvyiOSSdk/Core'
#      firebase.xcconfig = { "GCC_PREPROCESSOR_DEFINITIONS" => 'FIREBASE=1' }
#  end
#  
#  s.subspec 'Facebook' do |facebook|
#      facebook.source_files = 'IvyiOSSdk/Classes/Facebook/**/*'
#      facebook.public_header_files = 'IvyiOSSdk/Classes/Facebook/**/*.h'
#      facebook.dependency 'FBSDKCoreKit', '16.3.1'
#      facebook.dependency 'IvyiOSSdk/Core'
#      facebook.xcconfig = { "GCC_PREPROCESSOR_DEFINITIONS" => 'FACEBOOK=1' }
##      facebook.user_target_xcconfig = { "LIBRARY_SEARCH_PATHS" => ['${PODS_CONFIGURATION_BUILD_DIR}/FBAEMKit', '${PODS_CONFIGURATION_BUILD_DIR}/FBSDKCoreKit'], "HEADER_SEARCH_PATHS" => ['${PODS_ROOT}/Headers/Public/FBAEMKit', '${PODS_ROOT}/Headers/Public/FBSDKCoreKit']}
##      facebook.libraries = 'swiftCore'
#  end
#  
#  s.subspec 'Yandex' do |yandex|
#      yandex.source_files = 'IvyiOSSdk/Classes/Yandex/**/*'
#      yandex.public_header_files = 'IvyiOSSdk/Classes/Yandex/**/*.h'
#      yandex.dependency 'YandexMobileAds', '5.9.1'
#      yandex.dependency 'IvyiOSSdk/Core'
#  end
#  
#  s.subspec 'Appsflyer' do |appsflyer|
#      appsflyer.source_files = 'IvyiOSSdk/Classes/Appsflyer/**/*'
#      appsflyer.public_header_files = 'IvyiOSSdk/Classes/Appsflyer/**/*.h'
#      appsflyer.dependency 'AppsFlyerFramework', '6.13.2'
#      # appsflyer.dependency 'AppsFlyer-AdRevenue', '6.9.1'#'5.4.4'#, '5.2.0'
#      appsflyer.dependency 'IvyiOSSdk/Core'
#      appsflyer.frameworks = 'AdSupport', 'iAd'
#      appsflyer.xcconfig = { "GCC_PREPROCESSOR_DEFINITIONS" => 'APPSFLYER=1' }
#  end


  
  # s.resource_bundles = {
  #   'IvyiOSSdk' => ['IvyiOSSdk/Assets/*.png']
  # }

  # s.public_header_files = 'Pod/Classes/**/*.h'
  # s.frameworks = 'UIKit', 'MapKit'
  # s.dependency 'AFNetworking', '~> 2.3'
end
