package com.argentruck.argentruck_cliente.http;

import android.os.AsyncTask;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Demian on 10/04/2016.
 */

public abstract class HttpConnection extends AsyncTask<Void, Void, String> {
    private HttpResponseListener listener;
    protected String calledHttp = "";
    private String uri;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpConnection(HttpResponseListener listener){
        this.listener = listener;
    }

    protected HttpURLConnection createConnection(String url){
        try {
            URL PhyscUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) PhyscUrl.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);

            return httpURLConnection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract HttpURLConnection buildRequestStructure(String baseURL);

    @Override
    protected String doInBackground(Void... params) {
        String returnedBody = null;

        try {

            HttpURLConnection httpURLConnection = buildRequestStructure(uri);

            if(httpURLConnection == null){
                return null;
            }

            httpURLConnection.connect();

            InputStream result = httpURLConnection.getInputStream();
            ByteArrayOutputStream b = new ByteArrayOutputStream();

            int val = result.read();
            while (val >= 0){
                byte readedByte = (byte) val;
                b.write(readedByte);
                val = result.read();
            }

            returnedBody = new String(b.toByteArray());

            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedBody;
    }

    @Override
    protected void onPostExecute(String result) {

        if(result == null){
            listener.handleHttpError("Client side error in \"doInBackground\" on HttpConnection.java");
            return;
        }

        try {
            String a = "{\"data\":" + result + "}";
            JSONObject responseObject = new JSONObject(a);
            listener.handleHttpResponse(responseObject);
        } catch (JSONException e){
            listener.handleHttpError("Error in \"OnPostExecute\" trying to handle the response error in " + calledHttp);
        }
    }
}
