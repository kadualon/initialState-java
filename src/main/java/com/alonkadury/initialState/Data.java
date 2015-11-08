package com.alonkadury.initialState;

public class Data<T> implements Events {
  private final static String DATA_API_URL = API_BASEURL + "events";
  private String key;
  private T value;
  private String iso8601;

  public Data(String key, T value, String iso8601Time) {
    this.key = key;
    this.value = value;
    this.iso8601 = iso8601Time;
  }

  public Data(String key, T value) {
    this(key, value, null);
  }

  public String getEndpoint() {
    return DATA_API_URL;
  }

  // TODO: add annotations
}
