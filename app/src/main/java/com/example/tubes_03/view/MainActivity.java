package com.example.tubes_03.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.example.tubes_03.R;
import com.example.tubes_03.databinding.ActivityMainBinding;
import com.example.tubes_03.model.News;
import com.example.tubes_03.presenter.NewsPresenter;

import java.util.List;

// implements MainActivityPresenter.IMainActivity,
public class MainActivity extends AppCompatActivity implements FragmentListener {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    protected final int CALLER_FRAGMENT_HOME = 0;
    protected final int CALLER_FRAGMENT_DETAILS_IDN = 1;
    protected final int CALLER_FRAGMENT_DETAILS_WORLD = 2;
//    private MainActivityPresenter presenter;
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
    private SettingsFragment settingsFragment;
    private SignUpFragment signUpFragment;
    private TermsAndConditionsFragment termsAndConditionsFragment;
    private ActivityMainBinding bind;
    private NewsPresenter present;
    private ListView lstNews;
    private MockNewsAdapter adapter;
    private NewsPresenter.INewsFragment InewsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = this.getPreferences(MODE_PRIVATE);
        spEditor = sp.edit();


//        this.presenter = new MainActivityPresenter(this);

//        this.setSupportActionBar(this.bind.toolbar);

        this.toolbar = findViewById(R.id.toolbar);
        this.drawer = findViewById(R.id.drawer_layout);

        this.fragmentManager = this.getSupportFragmentManager();

        this.homeFragment = HomeFragment.newInstance("COVID Stats");
        this.dataDetailsFragment = DataDetailsFragment.newInstance("COVID Stats");
        this.newsFragment = NewsFragment.newInstance("COVID Stats");
        this.faqFragment = FAQFragment.newInstance("COVID Stats");
        this.loginFragment = LoginFragment.newInstance("COVID Stats");
        this.accountFragment = AccountFragment.newInstance("COVID Stats");
        this.settingsFragment = SettingsFragment.newInstance("COVID Stats");
        this.signUpFragment = SignUpFragment.newInstance("COVID Stats");
        this.termsAndConditionsFragment = TermsAndConditionsFragment.newInstance("COVID Stats");



        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        if (sp.getBoolean("USER_JUST_LOGGED_IN", false)) {
            changePage(6);
            this.spEditor.putBoolean("USER_JUST_LOGGED_IN", false);
            spEditor.apply();
        }
        else if (sp.getBoolean("USER_JUST_LOGGED_OUT", false)) {
            changePage(5);
            this.spEditor.putBoolean("USER_JUST_LOGGED_OUT", false);
            spEditor.apply();
        }
        else changePage(1);
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
                ft.replace(R.id.fragment_container, this.settingsFragment).addToBackStack(null);
                break;
            case 8:
                ft.replace(R.id.fragment_container, this.signUpFragment).addToBackStack(this.loginFragment.getClass().getName());
                break;
            case 9:
                ft.replace(R.id.fragment_container, this.termsAndConditionsFragment).addToBackStack(null);
                break;
        }
        ft.commit();

        this.drawer.closeDrawers();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.drawer.getWindowToken(), 0);
    }

    @Override
    public void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
        this.drawer.closeDrawers();
    }

}