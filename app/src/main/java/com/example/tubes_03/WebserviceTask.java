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
import com.example.tubes_03.model.CovidDataWorldwide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONObject;

import java.util.ArrayList;

public class WebserviceTask {
    private final String URL_WORLD = "https://api.covid19api.com/world/total";
    private final String URL_INDONESIA = "https://api.covid19api.com/total/country/indonesia";
    private Context context;
    private Gson gson;
    private UIThreadedWrapper uiThreadedWrapper;

    public interface IMainActivity {
        void changePage(int page);
        void closeApplication();
    }

    public WebserviceTask(Context context, UIThreadedWrapper uiThreadedWrapper) {
        this.context = context;
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.gson = new Gson();
    }

    public void executeWorldwide() {
        this.callVolleyWorldwide();
    }

    public void callVolleyWorldwide() {
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_WORLD, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processResultWorldwide(response.toString());
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
        CovidDataWorldwide dataWorldwide = gson.fromJson(response, CovidDataWorldwide.class);

        uiThreadedWrapper.sendResultWorldwide(dataWorldwide);
    }

    public void processResultIndonesia(String response) {
        ArrayList<CovidDataCountry> data = gson.fromJson(response, new TypeToken<ArrayList<CovidDataCountry>>(){}.getType());
//        if (data == null) {
//            Log.d("aeugh", "isEmptyBlyat");
//        }
//        else Log.d("aeugh", "hooray");

        FlowManager.getDatabase(DBCovidStats.class).beginTransactionAsync(
                new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<CovidDataCountry>() {
                            @Override
                            public void processModel(CovidDataCountry individualData, DatabaseWrapper wrapper) {
                                individualData.save();
                            }
                        }).addAll(data).build()).error(new Transaction.Error() {
                            @Override
                            public void onError(Transaction transaction, Throwable error) {
                                Log.d("DatabaseProcessingError", "Data was unable to be stored.");
                            }
                        }).success(transaction -> {}).build().execute();
//        uiThreadedWrapper.sendResult(data);
    }
}
