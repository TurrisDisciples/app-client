package com.argentruck.argentruck_cliente.http;

import org.json.JSONObject;

/**
 * Created by Demian on 10/04/2016.
 */
public interface HttpResponseListener {
    void handleHttpResponse(JSONObject responseBody);
    void handleHttpError(String error);
}
