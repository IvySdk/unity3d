#!/bin/sh
set -e
set -u
set -o pipefail

function on_error {
  echo "$(realpath -mq "${0}"):$1: error: Unexpected failure"
}
trap 'on_error $LINENO' ERR


# This protects against multiple targets copying the same framework dependency at the same time. The solution
# was originally proposed here: https://lists.samba.org/archive/rsync/2008-February/020158.html
RSYNC_PROTECT_TMP_FILES=(--filter "P .*.??????")


variant_for_slice()
{
  case "$1" in
  "SmaatoSDKBanner.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKBanner.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKCore.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKCore.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKInterstitial.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKInterstitial.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKNative.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKNative.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKOpenMeasurement.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKOpenMeasurement.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "OMSDK_Smaato.xcframework/ios-arm64_armv7")
    echo ""
    ;;
  "OMSDK_Smaato.xcframework/ios-arm64_i386_x86_64-simulator")
    echo "simulator"
    ;;
  "OMSDK_Smaato.xcframework/tvos-arm64")
    echo ""
    ;;
  "OMSDK_Smaato.xcframework/tvos-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKOutstream.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKOutstream.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKRewardedAds.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKRewardedAds.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKRichMedia.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKRichMedia.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  "SmaatoSDKVideo.xcframework/ios-arm64")
    echo ""
    ;;
  "SmaatoSDKVideo.xcframework/ios-arm64_x86_64-simulator")
    echo "simulator"
    ;;
  esac
}

archs_for_slice()
{
  case "$1" in
  "SmaatoSDKBanner.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKBanner.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKCore.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKCore.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKInterstitial.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKInterstitial.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKNative.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKNative.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKOpenMeasurement.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKOpenMeasurement.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "OMSDK_Smaato.xcframework/ios-arm64_armv7")
    echo "arm64 armv7"
    ;;
  "OMSDK_Smaato.xcframework/ios-arm64_i386_x86_64-simulator")
    echo "arm64 i386 x86_64"
    ;;
  "OMSDK_Smaato.xcframework/tvos-arm64")
    echo "arm64"
    ;;
  "OMSDK_Smaato.xcframework/tvos-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKOutstream.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKOutstream.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKRewardedAds.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKRewardedAds.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKRichMedia.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKRichMedia.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  "SmaatoSDKVideo.xcframework/ios-arm64")
    echo "arm64"
    ;;
  "SmaatoSDKVideo.xcframework/ios-arm64_x86_64-simulator")
    echo "arm64 x86_64"
    ;;
  esac
}

copy_dir()
{
  local source="$1"
  local destination="$2"

  # Use filter instead of exclude so missing patterns don't throw errors.
  echo "rsync --delete -av "${RSYNC_PROTECT_TMP_FILES[@]}" --links --filter \"- CVS/\" --filter \"- .svn/\" --filter \"- .git/\" --filter \"- .hg/\" \"${source}*\" \"${destination}\""
  rsync --delete -av "${RSYNC_PROTECT_TMP_FILES[@]}" --links --filter "- CVS/" --filter "- .svn/" --filter "- .git/" --filter "- .hg/" "${source}"/* "${destination}"
}

SELECT_SLICE_RETVAL=""

select_slice() {
  local xcframework_name="$1"
  xcframework_name="${xcframework_name##*/}"
  local paths=("${@:2}")
  # Locate the correct slice of the .xcframework for the current architectures
  local target_path=""

  # Split archs on space so we can find a slice that has all the needed archs
  local target_archs=$(echo $ARCHS | tr " " "\n")

  local target_variant=""
  if [[ "$PLATFORM_NAME" == *"simulator" ]]; then
    target_variant="simulator"
  fi
  if [[ ! -z ${EFFECTIVE_PLATFORM_NAME+x} && "$EFFECTIVE_PLATFORM_NAME" == *"maccatalyst" ]]; then
    target_variant="maccatalyst"
  fi
  for i in ${!paths[@]}; do
    local matched_all_archs="1"
    local slice_archs="$(archs_for_slice "${xcframework_name}/${paths[$i]}")"
    local slice_variant="$(variant_for_slice "${xcframework_name}/${paths[$i]}")"
    for target_arch in $target_archs; do
      if ! [[ "${slice_variant}" == "$target_variant" ]]; then
        matched_all_archs="0"
        break
      fi

      if ! echo "${slice_archs}" | tr " " "\n" | grep -F -q -x "$target_arch"; then
        matched_all_archs="0"
        break
      fi
    done

    if [[ "$matched_all_archs" == "1" ]]; then
      # Found a matching slice
      echo "Selected xcframework slice ${paths[$i]}"
      SELECT_SLICE_RETVAL=${paths[$i]}
      break
    fi
  done
}

install_xcframework() {
  local basepath="$1"
  local name="$2"
  local package_type="$3"
  local paths=("${@:4}")

  # Locate the correct slice of the .xcframework for the current architectures
  select_slice "${basepath}" "${paths[@]}"
  local target_path="$SELECT_SLICE_RETVAL"
  if [[ -z "$target_path" ]]; then
    echo "warning: [CP] $(basename ${basepath}): Unable to find matching slice in '${paths[@]}' for the current build architectures ($ARCHS) and platform (${EFFECTIVE_PLATFORM_NAME-${PLATFORM_NAME}})."
    return
  fi
  local source="$basepath/$target_path"

  local destination="${PODS_XCFRAMEWORKS_BUILD_DIR}/${name}"

  if [ ! -d "$destination" ]; then
    mkdir -p "$destination"
  fi

  copy_dir "$source/" "$destination"
  echo "Copied $source to $destination"
}

install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKBanner.xcframework" "smaato-ios-sdk/Modules/Banner" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKCore.xcframework" "smaato-ios-sdk/Modules/Core" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKInterstitial.xcframework" "smaato-ios-sdk/Modules/Interstitial" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKNative.xcframework" "smaato-ios-sdk/Modules/Native" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKOpenMeasurement.xcframework" "smaato-ios-sdk/Modules/OpenMeasurement" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/vendor/OMSDK_Smaato.xcframework" "smaato-ios-sdk/Modules/OpenMeasurement" "framework" "ios-arm64_armv7" "ios-arm64_i386_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKOutstream.xcframework" "smaato-ios-sdk/Modules/Outstream" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKRewardedAds.xcframework" "smaato-ios-sdk/Modules/RewardedAds" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKRichMedia.xcframework" "smaato-ios-sdk/Modules/RichMedia" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"
install_xcframework "${PODS_ROOT}/smaato-ios-sdk/SmaatoSDKVideo.xcframework" "smaato-ios-sdk/Modules/Video" "framework" "ios-arm64" "ios-arm64_x86_64-simulator"

