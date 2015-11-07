package com.alonkadury.initialState;

public abstract class Data implements Events {
  private final static String DATA_API_URL = API_BASEURL + "events";
  private String key;
  private String iso8601Time;

  public Data(String key, String iso8601Time) {
    this.key = key;
    this.iso8601Time = iso8601Time;
  }

  public String getEndpoint() {
    return DATA_API_URL;
  }

  // TODO: move to genrics
  // TODO: add annotations
}
