package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.presenter.AccountPresenter;
// implements AccountPresenter.IAccountFragment
public class AccountFragment extends Fragment {
    private FragmentListener fragmentListener;
//    private AccountPresenter presenter;

    public static AccountFragment newInstance(String title) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        PLACEHOLDER (account_fragment does not exist yet!)
        View view = inflater.inflate(R.layout.login_fragment, container, false);
//        this.presenter = new AccountPresenter(this);
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
}
