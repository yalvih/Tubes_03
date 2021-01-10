package com.example.tubes_03.model;

import com.example.tubes_03.DBCovidStats;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = DBCovidStats.class)
public class CovidDataCountry extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String country;
    @Column
    String countryCode;
    @Column
    String province;
    @Column
    String city;
    @Column
    String cityCode;
    @Column
    String lat;
    @Column
    String lon;
    @Column
    int confirmed;
    @Column
    int deaths;
    @Column
    int recovered;
    @Column
    String active;
    @Column
    String date;

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getDate() {
        return date;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
