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
    int ID;
    @Column
    String Country;
    @Column
    String CountryCode;
    @Column
    String Province;
    @Column
    String City;
    @Column
    String CityCode;
    @Column
    String Lat;
    @Column
    String Lon;
    @Column
    int Confirmed;
    @Column
    int Deaths;
    @Column
    int Recovered;
    @Column
    String Active;
    @Column
    String Date;

    public int getId() {
        return ID;
    }

    public String getCountry() {
        return Country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getProvince() {
        return Province;
    }

    public String getCity() {
        return City;
    }

    public String getCityCode() {
        return CityCode;
    }

    public String getLat() {
        return Lat;
    }

    public String getLon() {
        return Lon;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public int getDeaths() {
        return Deaths;
    }

    public int getRecovered() {
        return Recovered;
    }

    public String getActive() {
        return Active;
    }

    public String getDate() {
        return Date;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    public void setLat(String Lat) {
        this.Lat = Lat;
    }

    public void setLon(String Lon) {
        this.Lon = Lon;
    }

    public void setConfirmed(int Confirmed) {
        this.Confirmed = Confirmed;
    }

    public void setDeaths(int Deaths) {
        this.Deaths = Deaths;
    }

    public void setRecovered(int Recovered) {
        this.Recovered = Recovered;
    }

    public void setActive(String Active) {
        this.Active = Active;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
}
