package com.example.foryoudicodingsubmissionfour.view.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.view.fragment.setting.SetttingFragment;
import com.example.foryoudicodingsubmissionfour.helper.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.txtnotif) TextView txtnotif;
    @BindView(R.id.txtchangelanguage) TextView txtlanguage;
    String notif,langauge;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initLocale();
        initToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new SetttingFragment()).commit();
    }

    @OnClick({R.id.setting})
    public void imageView(LinearLayout linearLayout){
         Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(mIntent);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.format(getResources().getString(R.string.pengaturan)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_900);
    }

    private void initLocale(){
        notif = String.format(getResources().getString(R.string.Notifikasi));
        txtnotif.setText(notif);
        langauge = String.format(getResources().getString(R.string.language));
        txtlanguage.setText(langauge);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
