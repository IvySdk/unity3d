package com.ivy.ads.selectors;

public @interface AdapterSkipReason {
  String DEBUG_FAIL = "debug";
  String PASSED = "passed";
  String PROVIDER_AGE_GATE_FAIL = "specific_age_gate_limit";
  String CONSENT_FAILED = "no_consent";
  String AGE_GATE_FAILED_IN_OTHER_APPS = "failed_age_gate_other_app";
  String AGE_GATE_FAILED = "age_gate_failed";
  String IBA_OPT_OUT_SETTINGS = "iba_opt_out_settings";
  String IBA_OPT_OUT_PHONE = "iba_opt_out_phone";
  String SKIP_CYCLE = "skip_cycle";
  String SKIP_LOAD_FAILED = "skip_load_failed";
  String SKIP_LOAD_FAILED_MANYTIMES = "skip_load_failed_manytimes";
  String SKIP_LOAD_TIMEOUT_MANYTIMES = "skip_load_timeout_manytimes";
  String SKIP_SHOW_FAILE_MANYTIMES = "skip_show_fail_manytimes";
  String SKIP_ZERO_WEIGHT = "skip_zero_weight";
  String SKIP_BY_COUNTRY = "skip_by_country";
  String SKIP_SLEEPING= "skip_sleeping";

  String SKIP_ZERO_DISPLAY = "skip_no_display";

  String NO_PLACEMENT_FAIL = "placement_missing";
}
