package com.example.tubes_03.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;

import static android.content.Context.MODE_PRIVATE;

//implements SettingsPresenter.ISettingFragment,
public class SettingsFragment extends Fragment implements View.OnClickListener {
    private FragmentListener fragmentListener;
//    private SettingsPresenter presenter;
    TextView tnc;
    Button changeTheme;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    int darkTheme;

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
        sp = this.getActivity().getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();
//        this.presenter = new SettingsPresenter(this);

        this.tnc = view.findViewById(R.id.tnc);
        this.changeTheme = view.findViewById(R.id.change_theme);

        this.darkTheme = this.sp.getInt("DARK_THEME", 0);
        if (this.darkTheme == 1) {
            this.changeTheme.setText("LIGHT");
        }
        else this.changeTheme.setText("DARK");

        this.changeTheme.setOnClickListener(this);
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
        if (v == this.changeTheme) {
            if (this.darkTheme == 1) {
                this.spEditor.putInt("DARK_THEME", 2);
                spEditor.apply();
                fragmentListener.changeTheme(2);
            }
            else {
                this.spEditor.putInt("DARK_THEME", 1);
                spEditor.apply();
                fragmentListener.changeTheme(2);
            }
        }
    }
}
