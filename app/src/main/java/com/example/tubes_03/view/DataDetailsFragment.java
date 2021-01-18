package com.example.tubes_03.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataWorldwide;
import com.example.tubes_03.presenter.DataDetailsPresenter;
import com.example.tubes_03.presenter.HomePresenter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.synnapps.carouselview.ImageListener;

import org.intellij.lang.annotations.JdkConstants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataDetailsFragment extends Fragment implements DataDetailsPresenter.IDataDetailsFragment {
    protected static final int FRAGMENT_CODE = 1;
    private FragmentListener fragmentListener;
    private DataDetailsPresenter presenter;
    private TextView text_confirmed_total_id, text_death_total_id, text_sick_total_id, text_recovered_total_id,
            text_confirmed_interval_id, text_death_interval_id, text_sick_interval_id, text_recovered_interval_id;
    private PieChart dataChart;
    private TypedValue textColor;
    DecimalFormat thousandSeparatorFormat = new DecimalFormat("###,###,###,###");

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
        this.presenter = new DataDetailsPresenter(this);

        this.textColor = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.colorAccent, this.textColor, true);

        this.text_confirmed_total_id = view.findViewById(R.id.indonesia_confirmed_number);
        this.text_confirmed_interval_id = view.findViewById(R.id.indonesia_confirmed_interval);
        this.text_death_total_id = view.findViewById(R.id.indonesia_death_number);
        this.text_death_interval_id = view.findViewById(R.id.indonesia_death_interval);
        this.text_sick_total_id = view.findViewById(R.id.indonesia_sick_number);
        this.text_sick_interval_id = view.findViewById(R.id.indonesia_sick_interval);
        this.text_recovered_total_id = view.findViewById(R.id.indonesia_recovered_number);
        this.text_recovered_interval_id = view.findViewById(R.id.indonesia_recovered_interval);
        this.dataChart = view.findViewById(R.id.details_chart);

        initializePieChart();
        this.presenter.loadDataIndonesia();
        setPieChartSettings();

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

    public Context getFragmentContext() {
        return this.getContext();
    }

    public void updateTextViewsIndonesia(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval) {
        this.text_confirmed_total_id.setText(thousandSeparatorFormat.format(confirmedTotal));
        this.text_death_total_id.setText(thousandSeparatorFormat.format(deathTotal));
        this.text_sick_total_id.setText(thousandSeparatorFormat.format(sickTotal));
        this.text_recovered_total_id.setText(thousandSeparatorFormat.format(recoveredTotal));

        this.text_confirmed_interval_id.setText(intervalFormatter(confirmedInterval));
        this.text_death_interval_id.setText(intervalFormatter(deathInterval));
        this.text_sick_interval_id.setText(intervalFormatter(sickInterval));
        this.text_recovered_interval_id.setText(intervalFormatter(recoveredInterval));

        setPieChartData(deathTotal, sickTotal, recoveredTotal);
    }

//    public void updateTextViewsWorldwide(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval) {
//        this.text_confirmed_total_ww.setText(thousandSeparatorFormat.format(confirmedTotal));
//        this.text_death_total_ww.setText(thousandSeparatorFormat.format(deathTotal));
//        this.text_sick_total_ww.setText(thousandSeparatorFormat.format(sickTotal));
//        this.text_recovered_total_ww.setText(thousandSeparatorFormat.format(recoveredTotal));
//
//        this.text_confirmed_interval_ww.setText(intervalFormatter(confirmedInterval));
//        this.text_death_interval_ww.setText(intervalFormatter(deathInterval));
//        this.text_sick_interval_ww.setText(intervalFormatter(sickInterval));
//        this.text_recovered_interval_ww.setText(intervalFormatter(recoveredInterval));
//
//        setPieChartData(deathTotal, sickTotal, recoveredTotal);
//    }

    public String intervalFormatter(int interval) {
        if (interval > 0) {
            return "+" + thousandSeparatorFormat.format(interval);
        }
        else return thousandSeparatorFormat.format(interval);
    }

    public void initializePieChart() {
        this.dataChart.setRotationEnabled(false);
        this.dataChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);

        this.dataChart.setCenterText("Jumlah Kasus");
        this.dataChart.setCenterTextTypeface(ResourcesCompat.getFont(this.getContext(), R.font.google_font_normal));
        this.dataChart.setCenterTextSize(25f);
        this.dataChart.setCenterTextColor(textColor.data);
        this.dataChart.setNoDataTextTypeface(ResourcesCompat.getFont(this.getContext(), R.font.google_font_normal));

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

        ArrayList<Integer> colors = new ArrayList<>();
        final int[] chartColors = new int[] {Color.rgb(227, 35, 14), Color.rgb(217, 190, 17), Color.rgb(26, 161, 62)};
        for (int chartColor : chartColors) {
            colors.add(chartColor);
        }

        for (String type: dataPerCases.keySet()){
            chartEntries.add(new PieEntry(dataPerCases.get(type), type));
        }

        PieDataSet chartDataSet = new PieDataSet(chartEntries, label);
        //setting text size of the value
        chartDataSet.setValueTextSize(15f);
        //providing color list for coloring different entries
        chartDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData chartData = new PieData(chartDataSet);
        //showing the value of the entries, default true if not set
        chartData.setDrawValues(true);

        dataChart.setData(chartData);
        dataChart.invalidate();
    }

    public void setPieChartSettings() {
        Legend legends = this.dataChart.getLegend();
        legends.setTypeface(ResourcesCompat.getFont(this.getContext(), R.font.google_font_normal));
        legends.setTextSize(13f);

        legends.setTextColor(textColor.data);
        legends.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legends.setFormSize(13f);
        legends.setXEntrySpace(13f);

        Description description = this.dataChart.getDescription();
        description.setEnabled(false);
    }
}
