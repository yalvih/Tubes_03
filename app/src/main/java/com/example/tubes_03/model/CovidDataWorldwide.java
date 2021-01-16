package com.example.tubes_03.model;

public class CovidDataWorldwide {
    private String ID;
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;

    public CovidDataWorldwide(String ID, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered) {
        this.ID = ID;
        this.NewConfirmed = newConfirmed;
        this.TotalConfirmed = totalConfirmed;
        this.NewDeaths = newDeaths;
        this.TotalDeaths = totalDeaths;
        this.NewRecovered = newRecovered;
        this.TotalRecovered = totalRecovered;
    }

    public String getID() {
        return ID;
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

    public void setID(String ID) {
        this.ID = ID;
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
}