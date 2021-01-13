package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;

public class DataDetailsFragment extends Fragment {
    protected static final int FRAGMENT_CODE = 1;
    private FragmentListener fragmentListener;
    private TextView text_confirmed_id, text_death_id, text_sick_id, text_recovered_id, text_confirmed_ww, text_death_ww, text_sick_ww, text_recovered_ww;

    public static DataDetailsFragment newInstance(String title) {
        DataDetailsFragment fragment = new DataDetailsFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_details_fragment, container, false);

        this.text_confirmed_id = view.findViewById(R.id.indonesia_confirmed_number);
        this.text_death_id = view.findViewById(R.id.indonesia_death_number);
        this.text_sick_id = view.findViewById(R.id.indonesia_sick_number);
        this.text_recovered_id = view.findViewById(R.id.indonesia_recovered_number);
        this.text_confirmed_ww = view.findViewById(R.id.worldwide_confirmed_number);
        this.text_death_ww = view.findViewById(R.id.worldwide_death_number);
        this.text_sick_ww = view.findViewById(R.id.worldwide_sick_number);
        this.text_recovered_ww = view.findViewById(R.id.worldwide_recovered_number);

        this.fragmentListener.loadData(FRAGMENT_CODE);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.fragmentListener = (FragmentListener)context;
        }
        else {
            throw new ClassCastException(context.toString() + " must implement FragmentListener!");
        }
    }

    public void updateTextViewsIndonesia(CovidDataCountry data) {
        DecimalFormat thousandSeparatorFormat = new DecimalFormat("###,###,###,###");
        int totalSick = data.getConfirmed() - data.getDeaths() - data.getRecovered();

        this.text_confirmed_id.setText(thousandSeparatorFormat.format(data.getConfirmed()));
        this.text_death_id.setText(thousandSeparatorFormat.format(data.getDeaths()));
        this.text_sick_id.setText(thousandSeparatorFormat.format(totalSick));
        this.text_recovered_id.setText(thousandSeparatorFormat.format(data.getRecovered()));
    }

    public void updateTextViewsWorldwide(CovidDataWorldwide data) {
        DecimalFormat thousandSeparatorFormat = new DecimalFormat("###,###,###,###");
        int totalSick = Integer.parseInt(data.getTotalConfirmed()) - Integer.parseInt(data.getTotalDeaths()) - Integer.parseInt(data.getTotalRecovered());

        this.text_confirmed_ww.setText(thousandSeparatorFormat.format(Integer.parseInt(data.getTotalConfirmed())));
        this.text_death_ww.setText(thousandSeparatorFormat.format(Integer.parseInt(data.getTotalDeaths())));
        this.text_sick_ww.setText(thousandSeparatorFormat.format(totalSick));
        this.text_recovered_ww.setText(thousandSeparatorFormat.format(Integer.parseInt(data.getTotalRecovered())));
    }
}
