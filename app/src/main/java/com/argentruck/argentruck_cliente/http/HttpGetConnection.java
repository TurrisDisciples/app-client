package com.argentruck.argentruck_cliente.http;

import java.net.HttpURLConnection;
import java.net.ProtocolException;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpGetConnection extends HttpConnection {

    public HttpGetConnection(HttpResponseListener listener) {
        super(listener);
    }

    @Override
    public HttpURLConnection buildRequestStructure(String baseURL) {
        try {
            HttpURLConnection httpURLConnection = createConnection(baseURL);
            httpURLConnection.setRequestMethod("GET");
            return httpURLConnection;
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return null;
    }
}
