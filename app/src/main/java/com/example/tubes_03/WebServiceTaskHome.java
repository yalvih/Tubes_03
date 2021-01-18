package com.example.tubes_03;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidSummaryResponse;
import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class WebServiceTaskHome {
    private final String URL = "https://api.covid19api.com/summary";
    private Context context;
    private GsonBuilder gson;
    private UIThreadedWrapperHome uiThreadedWrapperHome;
    private int fragmentCode;

    public WebServiceTaskHome(Context context, UIThreadedWrapperHome uiThreadedWrapperHome) {
        this.context = context;
        this.uiThreadedWrapperHome = uiThreadedWrapperHome;
        this.gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[] {new DBFlowGsonExclusion()});
    }

    public void executeWorldwide() {
        this.callVolley(0);
    }

    public void executeIndonesia() {
        this.callVolley(1);
    }

    public void callVolley(int processCode) {
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (processCode == 0) {
                    processResultWorldwide(response.toString());
                }
                else processResultIndonesia(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", error.getMessage());
            }
        });

        queue.add(request);
    }

    public void processResultWorldwide(String response) {
        CovidSummaryResponse data = gson.create().fromJson(response, CovidSummaryResponse.class);

        uiThreadedWrapperHome.sendResultWorldwide(data.getGlobal());
    }

    public void processResultIndonesia(String response) {
        CovidDataCountry dataIDN = null;
        CovidSummaryResponse data = gson.create().fromJson(response, CovidSummaryResponse.class);

        for (int i = 0; i < data.getCountries().length; i++) {
            if (data.getCountries()[i].getCountryCode().equals("ID")) {
                dataIDN = data.getCountries()[i];
                break;
            }
        }

        uiThreadedWrapperHome.sendResultIndonesia(dataIDN);
    }
}
