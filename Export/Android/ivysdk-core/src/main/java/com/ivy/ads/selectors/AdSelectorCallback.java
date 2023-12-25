package com.ivy.ads.selectors;

import com.ivy.ads.adapters.BaseAdapter;

public interface AdSelectorCallback {
  void adLoadFailed(BaseAdapter adapter);

  void adLoadSuccess(BaseAdapter adapter);
}
