package com.example.tubes_03;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.view.MainActivity;

public class UIThreadedWrapper extends Handler {
    protected static final int PASS_RESULTS_WORLDWIDE = 0;
    protected static final int PASS_RESULTS_INDONESIA = 1;
    protected MainActivity mainActivity;

    public UIThreadedWrapper(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapper.PASS_RESULTS_WORLDWIDE) {
            CovidDataWorldwide data = (CovidDataWorldwide) msg.obj;
//            this.mainActivity.passResultToTextViews(data);
//            Log.d("dataTestCon", data.getTotalConfirmed());
//            Log.d("dataTestDed", data.getTotalDeaths());
//            Log.d("dataTestRec", data.getTotalRecovered());
        }
    }

    public void sendResultWorldwide(CovidDataWorldwide data) {
        Message msg = new Message();
        msg.what = PASS_RESULTS_WORLDWIDE;
        msg.obj = data;
        this.sendMessage(msg);
    }
}
