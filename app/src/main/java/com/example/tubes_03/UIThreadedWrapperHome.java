package com.example.tubes_03;

import android.os.Handler;
import android.os.Message;

import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.presenter.HomePresenter;
import com.example.tubes_03.view.DataDetailsFragment;
import com.example.tubes_03.view.MainActivity;

public class UIThreadedWrapperHome extends Handler {
    protected static final int CALLER_FRAGMENT_HOME = 0;
    protected static final int CALLER_FRAGMENT_DETAILS_IDN = 1;
    protected static final int CALLER_FRAGMENT_DETAILS_WORLD = 2;
    protected static final int PASS_RESULTS_WORLDWIDE = 10;
    protected static final int PASS_RESULTS_INDONESIA = 11;
    protected HomePresenter home;
    protected int fragmentCode;

    public UIThreadedWrapperHome(HomePresenter home) {
        this.home = home;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapperHome.PASS_RESULTS_WORLDWIDE) {
            CovidDataWorldwide data = (CovidDataWorldwide) msg.obj;
            this.home.getDataWorldwide(data);
        }
        else if (msg.what == UIThreadedWrapperHome.PASS_RESULTS_INDONESIA) {
            CovidDataCountry data = (CovidDataCountry) msg.obj;
            this.home.getDataIndonesia(data);
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
