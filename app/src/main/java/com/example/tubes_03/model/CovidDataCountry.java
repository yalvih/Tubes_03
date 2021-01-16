package com.example.tubes_03.model;

import org.json.JSONObject;

public class CovidDataCountry {
    private String ID;
    private String Country;
    private String CountryCode;
    private String Slug;
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;
    private String Date;
    private JSONObject Premium;

    public CovidDataCountry(String ID, String country, String countryCode, String slug, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered, String date, JSONObject premium) {
        this.ID = ID;
        this.Country = country;
        this.CountryCode = countryCode;
        this.Slug = slug;
        this.NewConfirmed = newConfirmed;
        this.TotalConfirmed = totalConfirmed;
        this.NewDeaths = newDeaths;
        this.TotalDeaths = totalDeaths;
        this.NewRecovered = newRecovered;
        this.TotalRecovered = totalRecovered;
        this.Date = date;
        this.Premium = premium;
    }

    public String getID() {
        return ID;
    }

    public String getCountry() {
        return Country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getSlug() {
        return Slug;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public int getNewDeaths() {
        return NewDeaths;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public String getDate() {
        return Date;
    }

    public JSONObject getPremium() {
        return Premium;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public void setCountryCode(String countryCode) {
        this.CountryCode = countryCode;
    }

    public void setSlug(String slug) {
        this.Slug = slug;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.NewConfirmed = newConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.TotalConfirmed = totalConfirmed;
    }

    public void setNewDeaths(int newDeaths) {
        this.NewDeaths = newDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.TotalDeaths = totalDeaths;
    }

    public void setNewRecovered(int newRecovered) {
        this.NewRecovered = newRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.TotalRecovered = totalRecovered;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public void setPremium(JSONObject premium) {
        this.Premium = premium;
    }
}