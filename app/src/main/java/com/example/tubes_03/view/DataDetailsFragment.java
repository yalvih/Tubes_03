package com.example.tubes_03.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataDetailsFragment extends Fragment {
    protected static final int FRAGMENT_CODE = 1;
    private FragmentListener fragmentListener;
    private TextView text_confirmed_id, text_death_id, text_sick_id, text_recovered_id, text_confirmed_ww, text_death_ww, text_sick_ww, text_recovered_ww;
    private PieChart dataChart;

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
        this.dataChart = view.findViewById(R.id.details_chart);

        this.fragmentListener.loadData(FRAGMENT_CODE);
        initializePieChart();

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
        int totalConfirmed = data.getTotalConfirmed();
        int totalDeaths = data.getTotalDeaths();
        int totalRecovered = data.getTotalRecovered();
        int totalSick = totalConfirmed - totalDeaths - totalRecovered;

        this.text_confirmed_id.setText(thousandSeparatorFormat.format(totalConfirmed));
        this.text_death_id.setText(thousandSeparatorFormat.format(totalDeaths));
        this.text_sick_id.setText(thousandSeparatorFormat.format(totalSick));
        this.text_recovered_id.setText(thousandSeparatorFormat.format(totalRecovered));

        setPieChartData(totalDeaths, totalSick, totalRecovered);
    }

    public void updateTextViewsWorldwide(CovidDataWorldwide data) {
        DecimalFormat thousandSeparatorFormat = new DecimalFormat("###,###,###,###");
        int totalConfirmed = data.getTotalConfirmed();
        int totalDeaths = data.getTotalDeaths();
        int totalRecovered = data.getTotalRecovered();
        int totalSick = totalConfirmed - totalDeaths - totalRecovered;

        this.text_confirmed_ww.setText(thousandSeparatorFormat.format(totalConfirmed));
        this.text_death_ww.setText(thousandSeparatorFormat.format(totalDeaths));
        this.text_sick_ww.setText(thousandSeparatorFormat.format(totalSick));
        this.text_recovered_ww.setText(thousandSeparatorFormat.format(totalRecovered));

        setPieChartData(totalDeaths, totalSick, totalRecovered);
    }

    public void initializePieChart() {
        this.dataChart.getDescription().setEnabled(false);
        this.dataChart.setRotationEnabled(false);
        this.dataChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        this.dataChart.setHoleColor(Color.parseColor("#00000000"));
        this.dataChart.setHoleRadius(60f);
        this.dataChart.setTransparentCircleRadius(65f);
    }

    public void setPieChartData(int death, int sick, int recovered) {
        ArrayList<PieEntry> chartEntries = new ArrayList<>();
        String label = "Jumlah Kasus";

        Map<String, Integer> dataPerCases = new HashMap<>();
        dataPerCases.put("Meninggal", death);
        dataPerCases.put("Dirawat", sick);
        dataPerCases.put("Sembuh", recovered);

//        int[] chartColors = new int[] {R.color.teal_200, R.color.red, R.color.purple_700};

        for (String type: dataPerCases.keySet()){
            chartEntries.add(new PieEntry(dataPerCases.get(type), type));
        }

        PieDataSet chartDataSet = new PieDataSet(chartEntries, label);
        //setting text size of the value
        chartDataSet.setValueTextSize(15f);
        //providing color list for coloring different entries
        chartDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //grouping the data set from entry to chart
        PieData chartData = new PieData(chartDataSet);
        //showing the value of the entries, default true if not set
        chartData.setDrawValues(true);

        dataChart.setData(chartData);
        dataChart.invalidate();
    }
}
