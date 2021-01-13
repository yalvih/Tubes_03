package com.example.tubes_03;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataCountry_Table;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.view.DataDetailsFragment;
import com.example.tubes_03.view.HomeFragment;
import com.example.tubes_03.view.MainActivity;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class UIThreadedWrapper extends Handler {
    protected static final int CALLER_FRAGMENT_HOME = 0;
    protected static final int CALLER_FRAGMENT_DETAILS_IDN = 1;
    protected static final int CALLER_FRAGMENT_DETAILS_WORLD = 2;
    protected static final int PASS_RESULTS_WORLDWIDE = 10;
    protected static final int PASS_RESULTS_INDONESIA = 11;
    protected MainActivity mainActivity;
    protected HomeFragment home;
    protected DataDetailsFragment data;
    protected int fragmentCode;

    public UIThreadedWrapper(HomeFragment home, DataDetailsFragment data) {
        this.home = home;
        this.data = data;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapper.PASS_RESULTS_WORLDWIDE) {
            CovidDataWorldwide data = (CovidDataWorldwide) msg.obj;

            if (fragmentCode == CALLER_FRAGMENT_HOME) {
                this.home.updateTextViewsWorldwide(data);
            }
            else if (fragmentCode == CALLER_FRAGMENT_DETAILS_WORLD) {
                this.data.updateTextViewsWorldwide(data);
            }
        }
        else if (msg.what == UIThreadedWrapper.PASS_RESULTS_INDONESIA) {
            CovidDataCountry data = (CovidDataCountry) msg.obj;

            if (fragmentCode == CALLER_FRAGMENT_HOME) {
                this.home.updateTextViewsIndonesia(data);
            }
            else if (fragmentCode == CALLER_FRAGMENT_DETAILS_IDN) {
                this.data.updateTextViewsIndonesia(data);
            }
        }
    }

    public void sendResultIndonesia(int fragmentCode) {
        this.fragmentCode = fragmentCode;
        Message msg = new Message();
        CovidDataCountry data = SQLite.select().
                from(CovidDataCountry.class).
                orderBy(CovidDataCountry_Table.Date, false).
                limit(1).
                querySingle();

        msg.what = PASS_RESULTS_INDONESIA;
        msg.obj = data;

        this.sendMessage(msg);
    }

    public void sendResultWorldwide(CovidDataWorldwide data, int fragmentCode) {
        this.fragmentCode = fragmentCode;
        Message msg = new Message();
        msg.what = PASS_RESULTS_WORLDWIDE;
        msg.obj = data;

        this.sendMessage(msg);
    }
}
