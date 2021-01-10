package com.example.tubes_03;

import androidx.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DBCovidStats.NAME, version = DBCovidStats.VERSION, insertConflict = ConflictAction.IGNORE, updateConflict= ConflictAction.REPLACE)
public class DBCovidStats {
    public static final String NAME = "CovidStats";

    public static final int VERSION = 4;
}
