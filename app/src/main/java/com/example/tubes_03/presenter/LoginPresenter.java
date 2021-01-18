package com.example.tubes_03.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tubes_03.R;
import com.example.tubes_03.model.Users;
import com.example.tubes_03.model.Users_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenter {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private ILoginFragment view;

    public LoginPresenter(ILoginFragment view) {
        this.view = view;
        sp = this.view.getFragmentActivity().getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public interface ILoginFragment {
        Activity getFragmentActivity();
        void loginError(int errorCode);
        void clearInputFields();
    }

    public void login(String username, String password) {
        if (username.length() == 0) {
            this.view.loginError(0);
        }
        else if (password.length() == 0) {
            this.view.loginError(1);
        }
        else {
            Users currentUser = SQLite.select().
                    from(Users.class).
                    where(Users_Table.username.eq(username)).
                    querySingle();

            if (currentUser == null) {
                this.view.loginError(2);
            }
            else {
                if (!currentUser.getPassword().equals(password)) {
                    this.view.loginError(3);
                }
                else {
                    this.spEditor.putString("USER_USERNAME", currentUser.getUsername());
                    this.spEditor.putInt("USER_STATUS", currentUser.getSwabTestStatus());
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
