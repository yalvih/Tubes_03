package com.example.tubes_03.presenter;

import android.content.Context;

import com.example.tubes_03.UIThreadedWrapperHome;
import com.example.tubes_03.WebServiceTaskHome;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;

public class HomePresenter {
    private IHomeFragment view;
    private UIThreadedWrapperHome threadHandlerHome;
    private WebServiceTaskHome dataInitializer;

    public HomePresenter(IHomeFragment view) {
        this.view = view;
        this.threadHandlerHome = new UIThreadedWrapperHome(this);
    }

    public interface IHomeFragment {
        Context getFragmentContext();
        void updateTextViewsIndonesia(int confirmed, int death, int sick, int recovered);
        void updateTextViewsWorldwide(int confirmed, int death, int sick, int recovered);
    }

    public void loadData() {
        this.dataInitializer = new WebServiceTaskHome(this.view.getFragmentContext(), this.threadHandlerHome);
        this.dataInitializer.executeIndonesia();
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
