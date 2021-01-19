package com.example.tubes_03.view;

import com.example.tubes_03.model.News;

import java.util.List;

public interface FragmentListener {
    void changePage(int page);
    void closeApplication();
    void changeTheme(int theme);
}
