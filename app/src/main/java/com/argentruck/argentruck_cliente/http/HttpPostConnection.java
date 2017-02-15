package com.argentruck.argentruck_cliente.http;


import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpPostConnection extends HttpConnection {
    private JSONObject body = new JSONObject();

    public HttpPostConnection(HttpResponseListener listener) {
        super(listener);
    }

    @Override
    public HttpURLConnection buildRequestStructure(String baseURL) {
        try {
            HttpURLConnection httpURLConnection = createConnection(baseURL);
            httpURLConnection.setRequestMethod("POST");

            byte[] array = body.toString().getBytes();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(array.length));
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.getOutputStream().write(array);
            httpURLConnection.getOutputStream().close();

            return httpURLConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBody(JSONObject body){
        this.body = body;
    }
}
