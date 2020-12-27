package com.example.tubes_03.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tubes_03.R;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private LeftDrawerFragment leftDrawerFragment;
    private DataDetailsFragment dataDetailsFragment;
    private NewsFragment newsFragment;
    private FAQFragment faqFragment;
    private LoginFragment loginFragment;
    private AccountFragment accountFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.drawer = findViewById(R.id.drawer_layout);

        this.fragmentManager = this.getSupportFragmentManager();

        this.homeFragment = HomeFragment.newInstance("COVID Stats");
        this.dataDetailsFragment = DataDetailsFragment.newInstance("COVID Stats");
        this.newsFragment = NewsFragment.newInstance("COVID Stats");
        this.faqFragment = FAQFragment.newInstance("COVID Stats");
        this.loginFragment = LoginFragment.newInstance("COVID Stats");
        this.accountFragment = AccountFragment.newInstance("COVID Stats");
        this.settingFragment = SettingFragment.newInstance("COVID Stats");

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        changePage(1);
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        switch (page) {
            case 1:
                ft.replace(R.id.fragment_container, this.homeFragment).addToBackStack(null);
                break;
            case 2:
                ft.replace(R.id.fragment_container, this.dataDetailsFragment).addToBackStack(null);
                break;
            case 3:
                ft.replace(R.id.fragment_container, this.newsFragment).addToBackStack(null);
                break;
            case 4:
                ft.replace(R.id.fragment_container, this.faqFragment).addToBackStack(null);
                break;
            case 5:
                ft.replace(R.id.fragment_container, this.loginFragment).addToBackStack(null);
                break;
            case 6:
                ft.replace(R.id.fragment_container, this.accountFragment).addToBackStack(null);
                break;
            case 7:
                ft.replace(R.id.fragment_container, this.settingFragment).addToBackStack(null);
                break;
        }
        ft.commit();
    }
}