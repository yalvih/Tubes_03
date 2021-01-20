package com.example.tubes_03.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.tubes_03.R;
import com.example.tubes_03.model.Users;
import com.example.tubes_03.model.Users_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import static android.content.Context.MODE_PRIVATE;

public class SignUpPresenter {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private ISignUpFragment view;

    public SignUpPresenter(ISignUpFragment view) {
        this.view = view;
        sp = this.view.getFragmentActivity().getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public interface ISignUpFragment {
        Activity getFragmentActivity();
        void signupError(int errorCode);
        void clearInputFields();
    }

    public void signup(String username, String password, String passwordConfirm) {
        if (username.length() == 0) {
            this.view.signupError(0);
        }
        else {
            if (password.length() == 0) {
                this.view.signupError(1);
            }
            else if (!passwordConfirm.equals(password)) {
                this.view.signupError(3);
            }
            else {
                Users currentUser = SQLite.select().
                        from(Users.class).
                        where(Users_Table.username.eq(username)).
                        querySingle();

                if (currentUser != null) {
                    this.view.signupError(2);
                }
                else {
                    Users user = new Users();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.save();

                    this.spEditor.putString("USER_USERNAME", user.getUsername());
                    this.spEditor.putInt("USER_STATUS", user.getSwabTestStatus());
                    this.spEditor.putBoolean("USER_LOGGED_IN", true);
                    this.spEditor.putBoolean("USER_JUST_LOGGED_IN", true);
                    spEditor.apply();

                    Activity activity = view.getFragmentActivity();
                    activity.finish();
                    activity.startActivity(new Intent(activity, activity.getClass()));

                    this.view.clearInputFields();
                }
            }
        }
    }
}
