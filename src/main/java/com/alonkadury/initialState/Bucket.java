package com.alonkadury.initialState;

public class Bucket implements Events {
  private final static String BUCKET_API_URL = API_BASEURL + "buckets";
  private String key;
  private String friendlyName;

  public Bucket(String key) {
    this(key, null);
  }

  public Bucket(String key, String friendlyName) {
    this.key = key;
    this.friendlyName = friendlyName;
  }

  public String getKey() { return key; }
  //public String getFriendlyName() { return friendlyName; }

  public String getEndpoint() {
    return BUCKET_API_URL;
  }
}
