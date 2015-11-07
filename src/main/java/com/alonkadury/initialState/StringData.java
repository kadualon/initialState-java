package com.alonkadury.initialState;

public class StringData extends Data {
  private String val;

  public StringData(String key, String val) {
    this(key, val, null);
  }

  public StringData(String key, String val, String iso8601Time) {
    super(key, iso8601Time);
    this.val = val;
  }
}
