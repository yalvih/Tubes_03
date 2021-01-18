package com.example.tubes_03.presenter;

import com.example.tubes_03.model.MockNews;
import com.example.tubes_03.model.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsPresenter {
    private INewsFragment view;
    private List<News> news;

    public NewsPresenter(INewsFragment view) {
        this.news = new ArrayList<News>();
        this.view = view;
    }

    public interface INewsFragment {
        void updateList(List<News> a);
    }
    public void loadData(){
        this.news = new ArrayList<News>(Arrays.asList(MockNews.news));
        this.view.updateList(this.news);
    }
}
