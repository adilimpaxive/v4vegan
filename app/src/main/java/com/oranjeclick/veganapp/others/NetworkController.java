package com.oranjeclick.veganapp.others;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NetworkController {
    private static String BASE_URL = "http://ratedv4vegan.com/admin/index.php/api";

    public static void getMenuItems(Context context, final String rest_id, Response.Listener<String> callback, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/getMenuItems";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, callback, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("rest_id", rest_id);
                return param;
            }
        };
        RequestCache.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void getReview(Context context, final String rest_id, Response.Listener<String> callback, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/getReview";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, callback, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("rest_id", rest_id);
                return param;
            }
        };
        RequestCache.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void getRestaurants(Context context, Response.Listener<String> callback, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/getRestaurants";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, callback, errorListener) {

        };
        RequestCache.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void addReview(Context context, final String rest_id, final String name,final String review,
                                 final String rating, Response.Listener<String> callback,
                                 Response.ErrorListener errorListener) {
        String url = BASE_URL + "/addReview";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, callback, errorListener) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("rest_id", rest_id);
                MyData.put("name", name);
                //MyData.put("location", location);
                MyData.put("review", review);
                MyData.put("rating", rating);//Add the data you'd like to send to the server.
                return MyData;
            }
        };
        RequestCache.getInstance(context).addToRequestQueue(stringRequest);
    }
}
