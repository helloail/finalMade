package com.example.foryoudicodingsubmissionfour.view.activity.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;


import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.view.activity.setting.SettingActivity;
import com.example.foryoudicodingsubmissionfour.view.fragment.favorites.FavoritFragment;
import com.example.foryoudicodingsubmissionfour.view.fragment.main.MainFragment;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.content_main) RelativeLayout contentmain;
    @BindView(R.id.search_recycler) RecyclerView search_recycler;
    @BindView(R.id.bn_main) BottomNavigationView bottomnav;

    private ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Tools.initstetho(this);
        initToolbar();
        initcomponent(savedInstanceState);
    }

    private void initcomponent(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            loadFragment(new MainFragment());
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new MainFragment();

                break;
            case R.id.navigation_favorites:
                fragment = new FavoritFragment();

                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_5));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_language){
            final Intent mintent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(mintent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        Tools.setSystemBarColor(this, R.color.grey_900);
        final String ltitle = String.format(getResources().getString(R.string.title));
        actionBar.setTitle(ltitle);
    }

}
