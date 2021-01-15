package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.CovidDataCountry;
import com.example.tubes_03.model.CovidDataCountry_Table;
import com.example.tubes_03.model.Users;
import com.example.tubes_03.model.Users_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class LoginFragment extends Fragment implements View.OnClickListener{
    private FragmentListener fragmentListener;
    private TextView login_username, login_password, sign_up;
    private Button login_confirm;

    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        this.login_username = view.findViewById(R.id.username);
        this.login_password = view.findViewById(R.id.password);
        this.login_confirm = view.findViewById(R.id.login_confirm);
        this.sign_up = view.findViewById(R.id.sign_up);

        this.login_confirm.setOnClickListener(this);
        this.sign_up.setOnClickListener(this);

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
        if (v == login_confirm) {
            String username = this.login_username.getText().toString();
            String password = this.login_password.getText().toString();

            if (username.length() == 0) {
                Log.d("aeugh", "Username is empty!");
            }
            else if (password.length() == 0) {
                Log.d("aeugh", "Password is empty!");
            }
            else {
                Users currentUser = SQLite.select().
                        from(Users.class).
                        where(Users_Table.username.eq(username)).
                        querySingle();

                if (currentUser == null) {
                    Log.d("aeugh", "User does not exist!");
                }
                else {
                    if (!currentUser.getPassword().equals(password)) {
                        Log.d("aeugh", "Password does not match!");
                    }
                    else {
                        Log.d("aeugh", "Login successful!");
                    }
                }
            }
        }
        else if (v == sign_up) {
            this.fragmentListener.changePage(8);
        }
    }
}
