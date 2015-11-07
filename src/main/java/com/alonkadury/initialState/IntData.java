package com.alonkadury.initialState;

public class IntData extends Data {
  private int val;

  public IntData(String key, int val) {
    this(key, val, null);
  }
  public IntData(String key, int val, String iso8601Time) {
    super(key, iso8601Time);
    this.val = val;
  }
}
