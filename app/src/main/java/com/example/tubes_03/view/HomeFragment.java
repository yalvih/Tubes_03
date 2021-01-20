package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.presenter.HomePresenter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment implements HomePresenter.IHomeFragment, View.OnClickListener {
    protected static final int FRAGMENT_CODE = 0;
    private FragmentListener fragmentListener;
    private HomePresenter presenter;
    private TextView text_confirmed_total_id, text_death_total_id, text_sick_total_id, text_recovered_total_id,
            text_confirmed_interval_id, text_death_interval_id, text_sick_interval_id, text_recovered_interval_id,
            text_confirmed_total_ww, text_death_total_ww, text_sick_total_ww, text_recovered_total_ww,
            text_confirmed_interval_ww, text_death_interval_ww, text_sick_interval_ww, text_recovered_interval_ww;
    DecimalFormat thousandSeparatorFormat = new DecimalFormat("###,###,###,###");
    CarouselView carouselView;
    int[] sampleImages = {
            R.drawable.carousel_1,
            R.drawable.carousel_2,
            R.drawable.carousel_3,
            R.drawable.carousel_4,
            R.drawable.carousel_5
    };
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        this.presenter = new HomePresenter(this);

        this.text_confirmed_total_id = view.findViewById(R.id.indonesia_confirmed_number);
        this.text_confirmed_interval_id = view.findViewById(R.id.indonesia_confirmed_interval);
        this.text_death_total_id = view.findViewById(R.id.indonesia_death_number);
        this.text_death_interval_id = view.findViewById(R.id.indonesia_death_interval);
        this.text_sick_total_id = view.findViewById(R.id.indonesia_sick_number);
        this.text_sick_interval_id = view.findViewById(R.id.indonesia_sick_interval);
        this.text_recovered_total_id = view.findViewById(R.id.indonesia_recovered_number);
        this.text_recovered_interval_id = view.findViewById(R.id.indonesia_recovered_interval);
        this.text_confirmed_total_ww = view.findViewById(R.id.worldwide_confirmed_number);
        this.text_confirmed_interval_ww = view.findViewById(R.id.worldwide_confirmed_interval);
        this.text_death_total_ww = view.findViewById(R.id.worldwide_death_number);
        this.text_death_interval_ww = view.findViewById(R.id.worldwide_death_interval);
        this.text_sick_total_ww = view.findViewById(R.id.worldwide_sick_number);
        this.text_sick_interval_ww = view.findViewById(R.id.worldwide_sick_interval);
        this.text_recovered_total_ww = view.findViewById(R.id.worldwide_recovered_number);
        this.text_recovered_interval_ww = view.findViewById(R.id.worldwide_recovered_interval);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        this.presenter.loadData();

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
        this.text_recovered_interval_id.setTextColor(intervalRecoveredColorPicker(recoveredInterval));
    }

    public void updateTextViewsWorldwide(int confirmedTotal, int confirmedInterval, int deathTotal, int deathInterval, int sickTotal, int sickInterval, int recoveredTotal, int recoveredInterval) {
        this.text_confirmed_total_ww.setText(thousandSeparatorFormat.format(confirmedTotal));
        this.text_death_total_ww.setText(thousandSeparatorFormat.format(deathTotal));
        this.text_sick_total_ww.setText(thousandSeparatorFormat.format(sickTotal));
        this.text_recovered_total_ww.setText(thousandSeparatorFormat.format(recoveredTotal));

        this.text_confirmed_interval_ww.setText(intervalFormatter(confirmedInterval));
        this.text_death_interval_ww.setText(intervalFormatter(deathInterval));
        this.text_sick_interval_ww.setText(intervalFormatter(sickInterval));
        this.text_recovered_interval_ww.setText(intervalFormatter(recoveredInterval));

        this.text_confirmed_interval_ww.setTextColor(intervalColorPicker(confirmedInterval));
        this.text_death_interval_ww.setTextColor(intervalColorPicker(deathInterval));
        this.text_sick_interval_ww.setTextColor(intervalColorPicker(sickInterval));
        this.text_recovered_interval_ww.setTextColor(intervalRecoveredColorPicker(recoveredInterval));
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

    public int intervalRecoveredColorPicker(int interval) {
        TypedValue valueIntervalColor = new TypedValue();

        if (interval > 0) {
            getContext().getTheme().resolveAttribute(R.attr.intervalColorNegative, valueIntervalColor, true);
            return valueIntervalColor.data;
        }
        else if (interval == 0) {
            getContext().getTheme().resolveAttribute(R.attr.intervalColorNeutral, valueIntervalColor, true);
            return valueIntervalColor.data;
        }
        else {
            getContext().getTheme().resolveAttribute(R.attr.intervalColorPositive, valueIntervalColor, true);
            return valueIntervalColor.data;
        }
    }
}
