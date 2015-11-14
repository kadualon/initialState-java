package com.alonkadury.initialState;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;

class EventTask implements Runnable {
  private final static String METHOD_TYPE = "POST";
  private final static String CONTENT_TYPE_KEY = "Content-Type";
  private final static String CONTENT_TYPE_VALUE = "application/json";
  private final static String X_ACCESS_KEY = "X-IS-AccessKey";
  private final static String ACCEPT_VERSION_KEY = "Accept-Version";
  private final static String ACCEPT_VERSION_VALUE = "~0";

  private String accessKey;
  private String endpoint;
  private HashMap<String, String> customHeaders;
  private String body;

  public EventTask(String accessKey, String endpoint, HashMap<String, String> customHeaders, String body) {
    this.accessKey = accessKey;
    this.endpoint = endpoint;
    this.customHeaders = customHeaders;
    this.body = body;
  }

  @Override
  public void run() {
    try {
      sendRequest();
    }
    catch (Exception ex) {
      Thread t = Thread.currentThread();
      t.getUncaughtExceptionHandler().uncaughtException(t, ex);
    }
  }

  private void sendRequest() throws Exception {
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
    con.disconnect();

    if (responseCode < 200 || responseCode >= 300)
      throw new Exception(String.format("InitialState call returned: %d", responseCode));

    //      InputStream is = con.getInputStream();
    //      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    //      String line;
    //      StringBuffer response = new StringBuffer();
    //      while((line = reader.readLine()) != null) {
    //        response.append(line);
    //        response.append('\r');
    //      }
    //      reader.close();
  }
}
