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
import com.example.tubes_03.presenter.HomePresenter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment implements View.OnClickListener, HomePresenter.IHomeFragment {
    protected static final int FRAGMENT_CODE = 0;
    private FragmentListener fragmentListener;
    private HomePresenter presenter;
    private TextView text_confirmed_id, text_death_id, text_sick_id, text_recovered_id, text_confirmed_ww, text_death_ww, text_sick_ww, text_recovered_ww;
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

        this.text_confirmed_id = view.findViewById(R.id.indonesia_confirmed_number);
        this.text_death_id = view.findViewById(R.id.indonesia_death_number);
        this.text_sick_id = view.findViewById(R.id.indonesia_sick_number);
        this.text_recovered_id = view.findViewById(R.id.indonesia_recovered_number);
        this.text_confirmed_ww = view.findViewById(R.id.worldwide_confirmed_number);
        this.text_death_ww = view.findViewById(R.id.worldwide_death_number);
        this.text_sick_ww = view.findViewById(R.id.worldwide_sick_number);
        this.text_recovered_ww = view.findViewById(R.id.worldwide_recovered_number);

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

    public void updateTextViewsIndonesia(int confirmed, int death, int sick, int recovered) {
        this.text_confirmed_id.setText(thousandSeparatorFormat.format(confirmed));
        this.text_death_id.setText(thousandSeparatorFormat.format(death));
        this.text_sick_id.setText(thousandSeparatorFormat.format(sick));
        this.text_recovered_id.setText(thousandSeparatorFormat.format(recovered));
    }

    public void updateTextViewsWorldwide(int confirmed, int death, int sick, int recovered) {
        this.text_confirmed_ww.setText(thousandSeparatorFormat.format(confirmed));
        this.text_death_ww.setText(thousandSeparatorFormat.format(death));
        this.text_sick_ww.setText(thousandSeparatorFormat.format(sick));
        this.text_recovered_ww.setText(thousandSeparatorFormat.format(recovered));
    }
}
