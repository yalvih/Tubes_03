package com.example.tubes_03.model;

import com.example.tubes_03.DBCovidStats;
import com.raizlabs.android.dbflow.annotation.Collate;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = DBCovidStats.class)
public class Users extends BaseModel {
    @Column (collate = Collate.NOCASE)
    @PrimaryKey
    String username;
    @Column
    String password;
//    0 = no data, 1 = negative, 2 = positive
    @Column
    int swabTestStatus;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSwabTestStatus() {
        return swabTestStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSwabTestStatus(int swabTestStatus) {
        this.swabTestStatus = swabTestStatus;
    }
}
