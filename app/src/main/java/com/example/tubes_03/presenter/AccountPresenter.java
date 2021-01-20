package com.example.tubes_03.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class AccountPresenter {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private IAccountFragment view;

    public AccountPresenter(IAccountFragment view) {
        this.view = view;
        sp = this.view.getFragmentActivity().getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public interface IAccountFragment {
        Activity getFragmentActivity();
        void deployUserDatas();
    }

    public void logout() {
        this.spEditor.remove("USER_USERNAME");
        this.spEditor.remove("USER_STATUS");
        this.spEditor.putBoolean("USER_LOGGED_IN", false);
        this.spEditor.putBoolean("USER_JUST_LOGGED_OUT", true);
        spEditor.apply();

        Activity activity = view.getFragmentActivity();
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
}
