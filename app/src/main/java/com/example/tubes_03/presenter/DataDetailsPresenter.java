package com.example.tubes_03.presenter;

import android.content.Context;

import com.example.tubes_03.UIThreadedWrapperData;
import com.example.tubes_03.WebServiceTaskData;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;

public class DataDetailsPresenter {
    private IDataDetailsFragment view;
    private UIThreadedWrapperData threadHandlerData;
    private WebServiceTaskData dataInitializer;

    public DataDetailsPresenter(IDataDetailsFragment view) {
        this.view = view;
        this.threadHandlerData = new UIThreadedWrapperData(this);
    }

    public interface IDataDetailsFragment {
        Context getFragmentContext();
        void updateTextViewsIndonesia(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval);
//        void updateTextViewsWorldwide(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval);
        void initializePieChart();
        void setPieChartData(int death, int sick, int recovered);
        void setPieChartSettings();
    }

    public void loadDataIndonesia() {
        this.dataInitializer = new WebServiceTaskData(this.view.getFragmentContext(), this.threadHandlerData);
        this.dataInitializer.executeIndonesia();
    }

    public void loadDataWorldwide() {
        this.dataInitializer = new WebServiceTaskData(this.view.getFragmentContext(), this.threadHandlerData);
        this.dataInitializer.executeWorldwide();
    }

    public void getDataIndonesia(CovidDataCountry data) {
        int sickTotal = data.getTotalConfirmed() - data.getTotalDeaths() - data.getTotalRecovered();
        int sickInterval = data.getNewConfirmed() - data.getNewDeaths() - data.getNewRecovered();
        this.view.updateTextViewsIndonesia(data.getTotalConfirmed(), data.getNewConfirmed(), data.getTotalDeaths(), data.getNewDeaths(), sickTotal, sickInterval, data.getTotalRecovered(), data.getNewRecovered());
    }

    public void getDataWorldwide(CovidDataWorldwide data) {
        int sickTotal = data.getTotalConfirmed() - data.getTotalDeaths() - data.getTotalRecovered();
        int sickInterval = data.getNewConfirmed() - data.getNewDeaths() - data.getNewRecovered();
//        this.view.updateTextViewsWorldwide(data.getTotalConfirmed(), data.getNewConfirmed(), data.getTotalDeaths(), data.getNewDeaths(), sickTotal, sickInterval, data.getTotalRecovered(), data.getNewRecovered());
    }
}
