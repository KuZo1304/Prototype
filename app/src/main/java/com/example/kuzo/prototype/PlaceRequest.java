package com.example.kuzo.prototype;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PlaceRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.0.111/Places.php";
    private Map<String, String> params;

    public PlaceRequest(int lid, int tag, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("lid",""+lid);
        params.put("tag",""+tag);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
