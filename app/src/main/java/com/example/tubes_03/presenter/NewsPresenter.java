package com.example.tubes_03.presenter;

public class NewsPresenter {
    private INewsFragment view;

    public NewsPresenter(INewsFragment view) {
        this.view = view;
    }

    public interface INewsFragment {
//        Methods go here...
    }
}
