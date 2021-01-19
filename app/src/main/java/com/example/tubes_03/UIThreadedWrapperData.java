package com.example.tubes_03;

import android.os.Handler;
import android.os.Message;

import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.presenter.DataDetailsPresenter;
import com.example.tubes_03.view.MainActivity;

public class UIThreadedWrapperData extends Handler {
    protected static final int CALLER_FRAGMENT_HOME = 0;
    protected static final int CALLER_FRAGMENT_DETAILS_IDN = 1;
    protected static final int CALLER_FRAGMENT_DETAILS_WORLD = 2;
    protected static final int PASS_RESULTS_WORLDWIDE = 10;
    protected static final int PASS_RESULTS_INDONESIA = 11;
    protected MainActivity mainActivity;
    protected DataDetailsPresenter data;
    protected int fragmentCode;

    public UIThreadedWrapperData(DataDetailsPresenter data) {
        this.data = data;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapperData.PASS_RESULTS_WORLDWIDE) {
            CovidDataWorldwide data = (CovidDataWorldwide) msg.obj;
            this.data.getDataWorldwide(data);
        }
        else if (msg.what == UIThreadedWrapperData.PASS_RESULTS_INDONESIA) {
            CovidDataCountry data = (CovidDataCountry) msg.obj;
            this.data.getDataIndonesia(data);
        }
    }

    public void sendResultIndonesia(CovidDataCountry data) {
        Message msg = new Message();
        msg.what = PASS_RESULTS_INDONESIA;
        msg.obj = data;

        this.sendMessage(msg);
    }

    public void sendResultWorldwide(CovidDataWorldwide data) {
        Message msg = new Message();
        msg.what = PASS_RESULTS_WORLDWIDE;
        msg.obj = data;

        this.sendMessage(msg);
    }
}
