package com.example.tubes_03;

import android.os.Handler;
import android.os.Message;

import com.example.tubes_03.model.CovidData;
import com.example.tubes_03.view.MainActivity;

public class UIThreadedWrapper extends Handler {
    protected static final int PASS_RESULTS = 0;
    protected MainActivity mainActivity;

    public UIThreadedWrapper(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapper.PASS_RESULTS) {
            CovidData[] data = (CovidData[]) msg.obj;
//            this.mainActivity.passResultToTextViews(data);
        }
    }

    public void sendResult(CovidData[] data) {
        Message msg = new Message();
        msg.what = PASS_RESULTS;
        msg.obj = data;
        this.sendMessage(msg);
    }
}
