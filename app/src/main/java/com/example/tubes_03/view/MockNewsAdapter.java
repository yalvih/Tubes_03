package com.example.tubes_03.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tubes_03.R;
import com.example.tubes_03.databinding.NewsListDesignBinding;
import com.example.tubes_03.model.MockNews;
import com.example.tubes_03.model.News;
import com.example.tubes_03.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

public class MockNewsAdapter extends BaseAdapter {
    private List<News> listNews;
    private Activity activity;
    private NewsPresenter ui;
    private Context fragment;
    private News[] news;
    public MockNewsAdapter(Context fragment){
        this.fragment = fragment;
        this.listNews = new ArrayList<News>();
        this.news = MockNews.news;
    }
    @Override
    public int getCount() {
        return news.length;
    }

    @Override
    public News getItem(int position) {
        return news[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setUi(NewsPresenter ui) {
        this.ui = ui;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder temp;
        if(view == null){
            view = LayoutInflater.from(this.fragment).inflate(R.layout.news_list_design,viewGroup, false);
            temp = new ViewHolder(view);
            view.setTag(temp);
        }else{
            temp = (ViewHolder)view.getTag();
        }

        temp.updateView(this.getItem(i));
        return view;
    }
    private class ViewHolder{
        protected TextView title;
        protected TextView tanggal;
        protected TextView desc;

        public ViewHolder(View view){
            this.title = view.findViewById(R.id.news_title);
            this.tanggal = view.findViewById(R.id.news_date);
            this.desc = view.findViewById(R.id.news_description);
        }

        public void updateView(News news){
            this.title.setText(news.getTitle());
            this.tanggal.setText(news.getDate());
            this.desc.setText(news.getDesc());
            Log.d("viewHolder", "updateView: a");
        }
    }

    public void updateList(List<News> a){
        this.listNews = a;
    }
}
