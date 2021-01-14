package com.example.tubes_03.model;

public class CovidDataWorldwide {
    private int TotalConfirmed;
    private int TotalDeaths;
    private int TotalRecovered;

    public CovidDataWorldwide(int TotalConfirmed, int TotalDeaths, int TotalRecovered) {
        this.TotalConfirmed = TotalConfirmed;
        this.TotalDeaths = TotalDeaths;
        this.TotalRecovered = TotalRecovered;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalConfirmed(int TotalConfirmed) {
        this.TotalConfirmed = TotalConfirmed;
    }

    public void setTotalDeaths(int TotalDeaths) {
        this.TotalDeaths = TotalDeaths;
    }

    public void setTotalRecovered(int TotalRecovered) {
        this.TotalRecovered = TotalRecovered;
    }
}
