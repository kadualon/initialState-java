package com.alonkadury.initialState;

import com.google.gson.Gson;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class Account {
  private String accessKey;
  private final static String X_ACCESS_KEY = "X-IS-AccessKey";
  private final static String METHOD_TYPE = "POST";
  private final static String CONTENT_TYPE_KEY = "Content-Type";
  private final static String CONTENT_TYPE_VALUE = "application/json";
  private final static String ACCEPT_VERSION_KEY = "Accept-Version";
  private final static String ACCEPT_VERSION_VALUE = "~0";
  private final static String BUCKET_KEY = "X-IS-BucketKey";
  private Gson gson; // thread safe according to docs

  public Account(String accessKey) {
    this.accessKey = accessKey;
    gson = new Gson();
  }

  public void createBucket(Bucket bucket) {
    sendRequest(bucket.getEndpoint(), null, gson.toJson(bucket));
  }

  public void createData(Bucket bucket, Data data) {

    HashMap<String, String> hash = new HashMap<String, String>();
    hash.put(BUCKET_KEY, bucket.getKey());
    sendRequest(data.getEndpoint(), hash, gson.toJson(data));
  }

  public void createBulkData(Bucket bucket, Data[] bulkData) {
    HashMap<String, String> hash = new HashMap<String, String>();
    hash.put(BUCKET_KEY, bucket.getKey());
    sendRequest(bulkData[0].getEndpoint(), hash, gson.toJson(bulkData));
  }

  private boolean sendRequest(String endpoint, HashMap<String, String> customHeaders, String body) {
    try {
      URL url = new URL(endpoint);
      HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

      //add reuqest headers
      con.setRequestMethod(METHOD_TYPE);
      con.setRequestProperty(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
      con.setRequestProperty(X_ACCESS_KEY, accessKey);
      con.setRequestProperty(ACCEPT_VERSION_KEY, ACCEPT_VERSION_VALUE);
      if (customHeaders != null)
        customHeaders.forEach((k, v) -> con.setRequestProperty(k, v));

      con.setDoOutput(true);
      con.setDoInput(true);

      DataOutputStream writer = new DataOutputStream(con.getOutputStream());

      writer.writeBytes(body);
      writer.flush();
      writer.close();

      int responseCode = con.getResponseCode();
      //System.out.println("Response Code : " + responseCode);

      //InputStream is = con.getInputStream();
      //BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      //String line;
      //StringBuffer response = new StringBuffer();
      //while((line = reader.readLine()) != null) {
      //  response.append(line);
      //  response.append('\r');
      //}
      //reader.close();
      //System.err.println(response.toString());
      con.disconnect();

      if (responseCode >= 200 && responseCode < 300)
        return true;
      else
        return false;
    }
    catch (IOException ex) {
      System.err.println(ex.toString());
      return false;
    }
  }
}
