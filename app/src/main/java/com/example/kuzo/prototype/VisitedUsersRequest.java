package com.example.kuzo.prototype;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class VisitedUsersRequest extends StringRequest{
    private static final String USERS_REQUEST_URL = "http://192.168.0.111/VisitedUsers.php";
    private Map<String, String> params;

    public VisitedUsersRequest(String title, Response.Listener<String> listener) {
        super(Method.POST, USERS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("title", title);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
