package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
//implements SettingsPresenter.ISettingFragment,
public class SettingsFragment extends Fragment implements View.OnClickListener {
    private FragmentListener fragmentListener;
//    private SettingsPresenter presenter;
    TextView tnc;

    public static SettingsFragment newInstance(String title) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
//        this.presenter = new SettingsPresenter(this);

        this.tnc = view.findViewById(R.id.tnc);
        this.tnc.setOnClickListener(this);

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
        if (v==tnc){
            this.fragmentListener.changePage(9);
        }
    }
}
