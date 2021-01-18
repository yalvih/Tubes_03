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
        void updateTextViewsIndonesia(int confirmed, int death, int sick, int recovered);
        void updateTextViewsWorldwide(int confirmed, int death, int sick, int recovered);
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
        int totalSick = data.getTotalConfirmed() - data.getTotalDeaths() - data.getTotalRecovered();
        this.view.updateTextViewsIndonesia(data.getTotalConfirmed(), data.getTotalDeaths(), totalSick, data.getTotalConfirmed());
    }

    public void getDataWorldwide(CovidDataWorldwide data) {
        int totalSick = data.getTotalConfirmed() - data.getTotalDeaths() - data.getTotalRecovered();
        this.view.updateTextViewsWorldwide(data.getTotalConfirmed(), data.getTotalDeaths(), totalSick, data.getTotalConfirmed());
    }
}
