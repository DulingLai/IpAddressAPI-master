package com.example.android.ipaddress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static String apiUrl = "http://ip-api.com/json";

    TextView mIpDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIpDisplay = (TextView) findViewById(R.id.ip_display);

        ipHttpRequest(apiUrl);
    }

    private void ipHttpRequest(String url) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String ip = response.getString("query");
                            String country = response.getString("country");
                            String city = response.getString("city");
                            String zip = response.getString("zip");
                            String lat = response.getString("lat");
                            String lon = response.getString("lon");
                            String isp = response.getString("isp");
                            String org = response.getString("org");
                            mIpDisplay.setText("IP: " + ip + "\n" + "\n" +"Country: " + country + "\n" + "\n" + "City: " + city + "\n" + "\n" + "Zip Code:" + zip + "\n" + "\n" + "Location: " + lat +"," + lon + "\n" + "\n" + "ISP: " + isp + "\n" + "\n" + "Organization: " + org);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }
}