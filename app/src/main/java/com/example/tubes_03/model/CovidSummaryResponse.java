package com.example.tubes_03.model;

import com.example.tubes_03.DBFlowGsonExclusion;
import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

public class CovidSummaryResponse {
    private GsonBuilder gson;
    private String ID;
    private String Message;
    private CovidDataWorldwide Global;
    private CovidDataCountry[] Countries;

    public CovidSummaryResponse(String ID, String message, CovidDataWorldwide global, CovidDataCountry[] countries) {
        this.gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[] {new DBFlowGsonExclusion()});
        this.ID = ID;
        this.Message = message;
        this.Global = gson.create().fromJson(global.toString(), CovidDataWorldwide.class);
        this.Countries = gson.create().fromJson(countries.toString(), CovidDataCountry[].class);
    }

    public String getID() {
        return ID;
    }

    public String getMessage() {
        return Message;
    }

    public CovidDataWorldwide getGlobal() {
        return Global;
    }

    public CovidDataCountry[] getCountries() {
        return Countries;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public void setGlobal(CovidDataWorldwide global) {
        this.Global = global;
    }

    public void setCountries(CovidDataCountry[] countries) {
        this.Countries = countries;
    }
}
