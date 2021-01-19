package com.example.tubes_03.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.presenter.AccountPresenter;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends Fragment implements AccountPresenter.IAccountFragment, View.OnClickListener {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private FragmentListener fragmentListener;
    private AccountPresenter presenter;
    private TextView username, status, editName, signout;
    Button test_covid;

    public static AccountFragment newInstance(String title) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_details_fragment, container, false);
        sp = this.getActivity().getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();
        Log.d("aeugh", Boolean.toString(sp.getBoolean("USER_LOGGED_IN", false)));
        this.presenter = new AccountPresenter(this);

        this.username = view.findViewById(R.id.account_username);
        this.status = view.findViewById(R.id.account_status);
        this.editName = view.findViewById(R.id.account_edit_name);
        this.signout = view.findViewById(R.id.account_sign_out);

        this.signout.setOnClickListener(this);

        Button button = view.findViewById(R.id.test_covid);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://tesmasif.pikobar.jabarprov.go.id/"));
                startActivity(viewIntent);
            }
        });

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
        if (v == this.signout) {
            this.presenter.logout();
        }
    }

    public Activity getFragmentActivity() {
        return this.getActivity();
    }
}
