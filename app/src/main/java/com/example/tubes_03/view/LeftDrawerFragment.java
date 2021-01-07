package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;

public class LeftDrawerFragment extends Fragment implements View.OnClickListener {
    private FragmentListener fragmentListener;
    private TextView home, data, news, faq, login, setting, exit;

    public static LeftDrawerFragment newInstance(String title) {
        LeftDrawerFragment fragment = new LeftDrawerFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);

        this.home = view.findViewById(R.id.drawer_home);
        this.data = view.findViewById(R.id.drawer_data);
        this.news = view.findViewById(R.id.drawer_news);
        this.faq = view.findViewById(R.id.drawer_faq);
        this.login = view.findViewById(R.id.drawer_login);
        this.setting = view.findViewById(R.id.drawer_settings);
        this.exit = view.findViewById(R.id.drawer_exit);

        this.home.setOnClickListener(this);
        this.data.setOnClickListener(this);
        this.news.setOnClickListener(this);
        this.faq.setOnClickListener(this);
        this.login.setOnClickListener(this);
        this.setting.setOnClickListener(this);
        this.exit.setOnClickListener(this);

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
        if (v == this.home) {
            fragmentListener.changePage(1);
        }
        else if (v == this.data) {
            fragmentListener.changePage(2);
        }
        else if ( v == this.news) {
            fragmentListener.changePage(3);
        }
        else if (v == this.faq) {
            fragmentListener.changePage(4);
        }
        else if (v == this.login) {
            fragmentListener.changePage(5);
        }
        else if (v == this.setting) {
            fragmentListener.changePage(7);
        }
        else if(v == this.exit) {
            fragmentListener.closeApplication();
        }
    }
}
