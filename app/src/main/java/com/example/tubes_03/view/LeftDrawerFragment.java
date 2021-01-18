package com.example.tubes_03.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;

import static android.content.Context.MODE_PRIVATE;

public class LeftDrawerFragment extends Fragment implements View.OnClickListener {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private FragmentListener fragmentListener;
    private TextView home, data, news, faq, login, setting, exit;
    private ImageView homeIcon, dataIcon, newsIcon, faqIcon, loginIcon, settingIcon, exitIcon;
    boolean userLoggedIn;

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
        this.sp = this.getActivity().getPreferences(MODE_PRIVATE);
        this.userLoggedIn = this.sp.getBoolean("USER_LOGGED_IN", false);

        this.home = view.findViewById(R.id.drawer_home);
        this.data = view.findViewById(R.id.drawer_data);
        this.news = view.findViewById(R.id.drawer_news);
        this.faq = view.findViewById(R.id.drawer_faq);
        this.login = view.findViewById(R.id.drawer_login);
        this.setting = view.findViewById(R.id.drawer_settings);
        this.exit = view.findViewById(R.id.drawer_exit);
        this.homeIcon = view.findViewById(R.id.drawer_icon_home);
        this.dataIcon = view.findViewById(R.id.drawer_icon_data);
        this.newsIcon = view.findViewById(R.id.drawer_icon_news);
        this.faqIcon = view.findViewById(R.id.drawer_image_faq);
        this.loginIcon = view.findViewById(R.id.drawer_icon_login);
        this.settingIcon = view.findViewById(R.id.drawer_image_settings);
        this.exitIcon = view.findViewById(R.id.drawer_image_exit);

        this.home.setOnClickListener(this);
        this.data.setOnClickListener(this);
        this.news.setOnClickListener(this);
        this.faq.setOnClickListener(this);
        this.login.setOnClickListener(this);
        this.setting.setOnClickListener(this);
        this.exit.setOnClickListener(this);
        this.homeIcon.setOnClickListener(this);
        this.dataIcon.setOnClickListener(this);
        this.newsIcon.setOnClickListener(this);
        this.faqIcon.setOnClickListener(this);
        this.loginIcon.setOnClickListener(this);
        this.settingIcon.setOnClickListener(this);
        this.exitIcon.setOnClickListener(this);

        if (userLoggedIn) {
            this.login.setText("My Account");
            this.loginIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_my_account_24));
        }
        else {
            this.login.setText("Login");
            this.loginIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_login_24));
        }

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
        if (v == this.home || v == this.homeIcon) {
            fragmentListener.changePage(1);
        }
        else if (v == this.data || v == this.dataIcon) {
            fragmentListener.changePage(2);
        }
        else if (v == this.news || v == this.newsIcon) {
            fragmentListener.changePage(3);
        }
        else if (v == this.faq || v == this.faqIcon) {
            fragmentListener.changePage(4);
        }
        else if (v == this.login || v == this.loginIcon) {
            if (userLoggedIn) {
                fragmentListener.changePage(6);
            }
            else fragmentListener.changePage(5);
        }
        else if (v == this.setting || v == this.settingIcon) {
            fragmentListener.changePage(7);
        }
        else if(v == this.exit || v == this.exitIcon) {
            fragmentListener.closeApplication();
        }
    }
}
