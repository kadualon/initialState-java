package com.alonkadury.initialState;

public class BooleanData extends Data {
  private Boolean val;

  public BooleanData(String key, Boolean val) {
    this(key, val, null);
  }

  public BooleanData(String key, Boolean val, String iso8601Time) {
    super(key, iso8601Time);
    this.val = val;
  }
}
