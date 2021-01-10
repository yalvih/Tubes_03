package com.example.tubes_03.model;

public class CovidDataWorldwide {
    private String TotalConfirmed;
    private String TotalDeaths;
    private String TotalRecovered;

    public CovidDataWorldwide(String TotalConfirmed, String TotalDeaths, String TotalRecovered) {
        this.TotalConfirmed = TotalConfirmed;
        this.TotalDeaths = TotalDeaths;
        this.TotalRecovered = TotalRecovered;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalConfirmed(String TotalConfirmed) {
        this.TotalConfirmed = TotalConfirmed;
    }

    public void setTotalDeaths(String TotalDeaths) {
        this.TotalDeaths = TotalDeaths;
    }

    public void setTotalRecovered(String TotalRecovered) {
        this.TotalRecovered = TotalRecovered;
    }
}
