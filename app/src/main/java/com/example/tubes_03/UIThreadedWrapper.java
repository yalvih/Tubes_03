package com.example.tubes_03;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataCountry_Table;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.view.HomeFragment;
import com.example.tubes_03.view.MainActivity;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class UIThreadedWrapper extends Handler {
    protected static final int PASS_RESULTS_WORLDWIDE = 0;
    protected static final int PASS_RESULTS_INDONESIA = 1;
    protected MainActivity mainActivity;
    protected HomeFragment home;

    public UIThreadedWrapper(HomeFragment home) {
        this.home = home;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapper.PASS_RESULTS_WORLDWIDE) {
            CovidDataWorldwide data = (CovidDataWorldwide) msg.obj;
            this.home.updateTextViewsWorldwide(data);
        }
        else if (msg.what == UIThreadedWrapper.PASS_RESULTS_INDONESIA) {
            CovidDataCountry data = (CovidDataCountry) msg.obj;
            this.home.updateTextViewsIndonesia(data);
        }
    }

    public void sendResultIndonesia() {
        Message msg = new Message();
        CovidDataCountry data = SQLite.select().
                from(CovidDataCountry.class).
                orderBy(CovidDataCountry_Table.Date, false).
                limit(1)
                .querySingle();

        msg.what = PASS_RESULTS_INDONESIA;
        msg.obj = data;
//        Log.d("aeugh", data.getDate());

        this.sendMessage(msg);
    }

    public void sendResultWorldwide(CovidDataWorldwide data) {
        Message msg = new Message();
        msg.what = PASS_RESULTS_WORLDWIDE;
        msg.obj = data;
        this.sendMessage(msg);
    }
}
