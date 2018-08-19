package com.example.kuzo.prototype;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DescriptionRequest extends StringRequest {
    private static final String DESCRIPTION_REQUEST_URL = "http://192.168.0.111/Description.php";
    private Map<String, String> params;

    public DescriptionRequest( String title, Response.Listener<String> listener) {
        super(Method.POST, DESCRIPTION_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("title", title);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}