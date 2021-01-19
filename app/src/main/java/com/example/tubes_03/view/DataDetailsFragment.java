package com.example.tubes_03.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.presenter.DataDetailsPresenter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class DataDetailsFragment extends Fragment implements DataDetailsPresenter.IDataDetailsFragment, View.OnClickListener {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private FragmentListener fragmentListener;
    private DataDetailsPresenter presenter;
    private TextView title, dataPicker_id, dataPicker_ww,
            text_confirmed_total_id, text_death_total_id, text_sick_total_id, text_recovered_total_id,
            text_confirmed_interval_id, text_death_interval_id, text_sick_interval_id, text_recovered_interval_id;
    private PieChart dataChart;
    private TypedValue valueTextColor;
    DecimalFormat thousandSeparatorFormat = new DecimalFormat("###,###,###,###");
//    0 = Indonesia, 1 = Worldwide
    private int fragmentCode;
    private static final int FRAGMENT_DATA_INDONESIA = 0;
    private static final int FRAGMENT_DATA_WORLDWIDE = 1;

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
        this.sp = this.getActivity().getPreferences(MODE_PRIVATE);
        this.spEditor = sp.edit();
        this.presenter = new DataDetailsPresenter(this);

        this.valueTextColor = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.textColor, this.valueTextColor, true);

        this.title = view.findViewById(R.id.data_title);
        this.dataPicker_id = view.findViewById(R.id.data_picker_id);
        this.dataPicker_ww = view.findViewById(R.id.data_picker_ww);
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
        this.fragmentCode = this.sp.getInt("DATA_FRAGMENT_PICKED", FRAGMENT_DATA_INDONESIA);

        if (fragmentCode == FRAGMENT_DATA_INDONESIA) {
            this.title.setText("DATA CORONA INDONESIA");
            dataModeSetUnpicked(this.dataPicker_ww);
            this.presenter.loadDataIndonesia();
        }
        else {
            this.title.setText("DATA CORONA DUNIA");
            dataModeSetUnpicked(this.dataPicker_id);
            this.presenter.loadDataWorldwide();
        }
        setPieChartSettings();

        this.dataPicker_id.setOnClickListener(this);
        this.dataPicker_ww.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (v == this.dataPicker_id && this.fragmentCode != FRAGMENT_DATA_INDONESIA) {
            this.spEditor.putInt("DATA_FRAGMENT_PICKED", FRAGMENT_DATA_INDONESIA);
            spEditor.apply();
            fragmentListener.changePage(1);
            fragmentListener.changePage(2);
        }
        else if (v == this.dataPicker_ww && this.fragmentCode != FRAGMENT_DATA_WORLDWIDE) {
            this.spEditor.putInt("DATA_FRAGMENT_PICKED", FRAGMENT_DATA_WORLDWIDE);
            spEditor.apply();
            fragmentListener.changePage(1);
            fragmentListener.changePage(2);
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

        this.text_confirmed_interval_id.setTextColor(intervalColorPicker(confirmedInterval));
        this.text_death_interval_id.setTextColor(intervalColorPicker(deathInterval));
        this.text_sick_interval_id.setTextColor(intervalColorPicker(sickInterval));
        this.text_recovered_interval_id.setTextColor(intervalColorPicker(recoveredInterval));

        setPieChartData(deathTotal, sickTotal, recoveredTotal);
    }

    public void updateTextViewsWorldwide(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval) {
        this.text_confirmed_total_id.setText(thousandSeparatorFormat.format(confirmedTotal));
        this.text_death_total_id.setText(thousandSeparatorFormat.format(deathTotal));
        this.text_sick_total_id.setText(thousandSeparatorFormat.format(sickTotal));
        this.text_recovered_total_id.setText(thousandSeparatorFormat.format(recoveredTotal));

        this.text_confirmed_interval_id.setText(intervalFormatter(confirmedInterval));
        this.text_death_interval_id.setText(intervalFormatter(deathInterval));
        this.text_sick_interval_id.setText(intervalFormatter(sickInterval));
        this.text_recovered_interval_id.setText(intervalFormatter(recoveredInterval));

        this.text_confirmed_interval_id.setTextColor(intervalColorPicker(confirmedInterval));
        this.text_death_interval_id.setTextColor(intervalColorPicker(deathInterval));
        this.text_sick_interval_id.setTextColor(intervalColorPicker(sickInterval));
        this.text_recovered_interval_id.setTextColor(intervalColorPicker(recoveredInterval));

        setPieChartData(deathTotal, sickTotal, recoveredTotal);
    }

    public String intervalFormatter(int interval) {
        if (interval > 0) {
            return "+" + thousandSeparatorFormat.format(interval);
        }
        else return thousandSeparatorFormat.format(interval);
    }

    public int intervalColorPicker(int interval) {
        TypedValue valueIntervalColor = new TypedValue();

        if (interval > 0) {
            getContext().getTheme().resolveAttribute(R.attr.intervalColorPositive, valueIntervalColor, true);
            return valueIntervalColor.data;
        }
        else if (interval == 0) {
            getContext().getTheme().resolveAttribute(R.attr.intervalColorNeutral, valueIntervalColor, true);
            return valueIntervalColor.data;
        }
        else {
            getContext().getTheme().resolveAttribute(R.attr.intervalColorNegative, valueIntervalColor, true);
            return valueIntervalColor.data;
        }
    }

    public void dataModeSetUnpicked(TextView dataPicker) {
        dataPicker.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        dataPicker.setTextColor(getResources().getColor(R.color.gray));
    }

    public void initializePieChart() {
        this.dataChart.setRotationEnabled(false);
        this.dataChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);

        this.dataChart.setCenterText("Jumlah Kasus");
        this.dataChart.setCenterTextTypeface(ResourcesCompat.getFont(this.getContext(), R.font.google_font_normal));
        this.dataChart.setCenterTextSize(25f);
        this.dataChart.setCenterTextColor(valueTextColor.data);
        this.dataChart.setNoDataTextTypeface(ResourcesCompat.getFont(this.getContext(), R.font.google_font_normal));

        this.dataChart.setHoleColor(Color.parseColor("#00000000"));
        this.dataChart.setHoleRadius(40f);
        this.dataChart.setTransparentCircleRadius(45f);
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
        chartDataSet.setValueTextSize(15f);
        chartDataSet.setColors(colors);
        PieData chartData = new PieData(chartDataSet);
        chartData.setDrawValues(true);

        dataChart.setData(chartData);
        dataChart.invalidate();
    }

    public void setPieChartSettings() {
        Legend legends = this.dataChart.getLegend();
        legends.setTypeface(ResourcesCompat.getFont(this.getContext(), R.font.google_font_normal));
        legends.setTextSize(13f);

        legends.setTextColor(valueTextColor.data);
        legends.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legends.setFormSize(13f);
        legends.setXEntrySpace(13f);

        Description description = this.dataChart.getDescription();
        description.setEnabled(false);
    }
}
