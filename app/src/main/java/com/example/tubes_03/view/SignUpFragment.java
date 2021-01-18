package com.example.tubes_03.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.Users;
import com.example.tubes_03.model.Users_Table;
import com.example.tubes_03.presenter.SignUpPresenter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import static android.content.Context.MODE_PRIVATE;

public class SignUpFragment extends Fragment implements SignUpPresenter.ISignUpFragment, View.OnClickListener {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private FragmentListener fragmentListener;
    private SignUpPresenter presenter;
    private TextView error, signup_username, signup_password, signup_retypepass;
    private Button signup_confirm;

    public static SignUpFragment newInstance(String title) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        sp = this.getActivity().getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();
        this.presenter = new SignUpPresenter(this);

        this.error = view.findViewById(R.id.error_message);
        this.signup_username = view.findViewById(R.id.username);
        this.signup_password = view.findViewById(R.id.password);
        this.signup_retypepass = view.findViewById(R.id.retype_password);
        this.signup_confirm = view.findViewById(R.id.signup_confirm);

        this.signup_confirm.setOnClickListener(this);

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
        if (v == this.signup_confirm) {
            String username = this.signup_username.getText().toString();
            String password = this.signup_password.getText().toString();
            String passwordConfirm = this.signup_retypepass.getText().toString();
            this.presenter.signup(username, password, passwordConfirm);
        }
    }

    public Activity getFragmentActivity() {
        return this.getActivity();
    }

    public void signupError(int errorCode) {
//        0 = empty username
//        1 = empty password
//        2 = duplicate username
//        3 = re-entered password does not match
        switch (errorCode) {
            case 0:
                this.error.setText(R.string.logsign_user_empty);
                break;
            case 1:
                this.error.setText(R.string.logsign_password_empty);
                break;
            case 2:
                this.error.setText(R.string.logsign_user_exists);
                break;
            case 3:
                this.error.setText(R.string.logsign_password_nomatch);
                break;
        }
    }

    public void clearInputFields() {
        this.signup_username.setText("");
        this.signup_password.setText("");
        this.signup_retypepass.setText("");
    }
}
