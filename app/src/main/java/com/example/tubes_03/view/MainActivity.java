package com.example.tubes_03.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tubes_03.R;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private LeftDrawerFragment leftDrawerFragment;
    private DataDetailsFragment dataDetailsFragment;
    private NewsFragment newsFragment;
//    private FAQFragment faqFragment;
    private LoginFragment loginFragment;
//    private AccountFragment accountFragment;
    private SettingFragment settingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        this.homeFragment = HomeFragment.newInstance("COVID Stats");
//        this.dataDetailsFragment = LeftDrawerFragment.newInstance("COVID Stats");
//        this.newsFragment = LeftDrawerFragment.newInstance("COVID Stats");
//        this.faqFragment = LeftDrawerFragment.newInstance("COVID Stats");
//        this.loginFragment = LeftDrawerFragment.newInstance("COVID Stats");
//        this.accountFragment = LeftDrawerFragment.newInstance("COVID Stats");
//        this.settingFragment = LeftDrawerFragment.newInstance("COVID Stats");
    }

//    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        switch (page) {
            case 1:
//                ft.replace(R.id.fragment_container, this.homeFragment).addToBackStack(null);
                break;
            case 2:
//                ft.replace(R.id.fragment_container, this.dataDetailsFragment).addToBackStack(null);
                break;
            case 3:
//                ft.replace(R.id.fragment_container, this.newsFragment).addToBackStack(null);
                break;
            case 4:
//                ft.replace(R.id.fragment_container, this.faqFragment).addToBackStack(null);
                break;
            case 5:
//                ft.replace(R.id.fragment_container, this.loginFragment).addToBackStack(null);
                break;
            case 6:
//                ft.replace(R.id.fragment_container, this.accountFragment).addToBackStack(null);
                break;
            case 7:
//                ft.replace(R.id.fragment_container, this.settingFragment).addToBackStack(null);
                break;
        }
    }
}