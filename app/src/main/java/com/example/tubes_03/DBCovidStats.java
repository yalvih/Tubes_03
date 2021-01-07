package com.example.tubes_03;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DBCovidStats.NAME, version = DBCovidStats.VERSION)
public class DBCovidStats {
    public static final String NAME = "CovidStats";

    public static final int VERSION = 1;
}
