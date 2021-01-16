package com.example.tubes_03;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.model.CovidSummaryResponse;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WebserviceTask {
    private final String URL = "https://api.covid19api.com/summary";
    private Context context;
    private GsonBuilder gson;
    private UIThreadedWrapper uiThreadedWrapper;
    private int fragmentCode;

    public interface IMainActivity {
        void changePage(int page);
        void closeApplication();
    }

    public WebserviceTask(Context context, UIThreadedWrapper uiThreadedWrapper) {
        this.context = context;
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[] {new DBFlowGsonExclusion()});
    }

    public void executeWorldwide(int fragmentCode) {
        this.fragmentCode = fragmentCode;
        this.callVolley(0);
    }

    public void executeIndonesia(int fragmentCode) {
        this.fragmentCode = fragmentCode;
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

        uiThreadedWrapper.sendResultWorldwide(data.getGlobal(), fragmentCode);
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

        uiThreadedWrapper.sendResultIndonesia(dataIDN, fragmentCode);
    }
}
