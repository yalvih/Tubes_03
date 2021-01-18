package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.presenter.TermsAndConditionsPresenter;

//implements TermsAndConditionsPresenter.ITermsAndConditionsFragment
public class TermsAndConditionsFragment extends Fragment {
    private FragmentListener fragmentListener;
//    private TermsAndConditionsPresenter presenter;

    public static TermsAndConditionsFragment newInstance(String title) {
        TermsAndConditionsFragment fragment = new TermsAndConditionsFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.term_and_condition_fragment, container, false);
//        this.presenter = new TermsAndConditionsPresenter(this);

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
