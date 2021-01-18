package com.example.tubes_03.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.News;
import com.example.tubes_03.presenter.NewsPresenter;

import java.util.List;

//implements NewsPresenter.INewsFragment
public class NewsFragment extends Fragment implements NewsPresenter.INewsFragment{
    ListView lstNews;
    private NewsPresenter present;
    private MockNewsAdapter adapter;
    private FragmentListener fragmentListener;
    private Activity activity;
    private NewsPresenter.INewsFragment newsFragment;


    public static NewsFragment newInstance(String title) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = this.getActivity();
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        lstNews = view.findViewById(R.id.list_news);
        this.present = new NewsPresenter(this);
        this.adapter = new MockNewsAdapter(this.getContext());
        this.adapter.setUi(present);
        this.lstNews.setAdapter(this.adapter);
        present.loadData();
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
    public void updateList(List<News> a) {
        this.adapter.updateList(a);
        this.adapter.notifyDataSetChanged();
    }
}
