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
import com.example.tubes_03.model.CovidData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class WebserviceTask {
    private final String BASE_URL = "https://api.covid19api.com/world/total";
    private Context context;
    private Gson gson;
    private UIThreadedWrapper uiThreadedWrapper;

    public interface IMainActivity {
        void changePage(int page);
        void closeApplication();
    }

    public WebserviceTask(Context context, UIThreadedWrapper uiThreadedWrapper) {
        this.context = context;
        this.gson = new Gson();
        this.uiThreadedWrapper = uiThreadedWrapper;
    }

    public void execute() {
        this.callVolley();
    }

    public void callVolley() {
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                processResult(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", error.getMessage());
            }
        });

        queue.add(request);
    }

    public void processResult(String response) {
        ArrayList<CovidData> data = gson.fromJson(response, new TypeToken<ArrayList<CovidData>>(){}.getType());

        DatabaseDefinition db = FlowManager.getDatabase(DBCovidStats.class);

        FlowManager.getDatabase(DBCovidStats.class).beginTransactionAsync(
                new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<CovidData>() {
                            @Override
                            public void processModel(CovidData individualData, DatabaseWrapper wrapper) {
                                individualData.save();
                            }
                        }).addAll(data).build()).error(new Transaction.Error() {
                            @Override
                            public void onError(Transaction transaction, Throwable error) {
                                Log.d("DatabaseProcessingError", "Data was unable to be stored.");
                            }
                        }).success(transaction -> {}).build().execute();
//        uiThreadedWrapper.sendResult(result);
    }
}
