package com.example.tubes_03;

import android.app.Application;
import android.util.Log;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class COVIDStats extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).openDatabasesOnInit(true).build());
//        FlowManager.getDatabase(DBCovidStats.class).reset(getBaseContext());
    }
}
