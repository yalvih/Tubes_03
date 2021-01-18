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
        void updateTextViewsIndonesia(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval);
        void updateTextViewsWorldwide(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval);
        String intervalFormatter(int interval);
    }

    public void loadData() {
        this.dataInitializer = new WebServiceTaskHome(this.view.getFragmentContext(), this.threadHandlerHome);
        this.dataInitializer.executeIndonesia();
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
        this.view.updateTextViewsWorldwide(data.getTotalConfirmed(), data.getNewConfirmed(), data.getTotalDeaths(), data.getNewDeaths(), sickTotal, sickInterval, data.getTotalRecovered(), data.getNewRecovered());
    }
}
