package com.example.foryoudicodingsubmissionfour.view.fragment.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.SwitchPreference;
import androidx.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.notification.ReleaseNotification;
import com.example.foryoudicodingsubmissionfour.notification.ReminderNotification;

public class SetttingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener  {

    private String Reminder = "reminder";
    private String Release = "release";
    private SwitchPreference prefReminder,prefRelease;
    ReminderNotification reminderNotification;
    ReleaseNotification releaseNotification;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.notification_refference);
        initPref();

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }

    private void initPref(){
        prefReminder = (SwitchPreference) findPreference(Reminder);
        prefRelease = (SwitchPreference) findPreference(Release);
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        prefReminder.setChecked(sharedPreferences.getBoolean(Reminder, false));
        prefRelease.setChecked(sharedPreferences.getBoolean(Release, false));
        reminderNotification = new ReminderNotification();
        releaseNotification = new ReleaseNotification();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equalsIgnoreCase(Reminder))   {
            boolean setReminder = sharedPreferences.getBoolean(Reminder, false);
            prefReminder.setChecked(setReminder);

            if (!setReminder) {
                reminderNotification.cancelReminder(getActivity());
                Toast.makeText(getActivity(), "Notifikasi Reminder di matikan", Toast.LENGTH_LONG).show();

            } else {
                reminderNotification.setReminder(getActivity());
                Log.d("reminder", "reminder berhasil");
                Toast.makeText(getActivity(), "Notifikasi Reminder Hidup", Toast.LENGTH_LONG).show();

            }
        }


        if (key.equalsIgnoreCase(Release))   {

            boolean setRelease = sharedPreferences.getBoolean(Release, false);
            prefRelease.setChecked(setRelease);

            if (!setRelease) {
                releaseNotification.cancelReminder(getActivity());
                Toast.makeText(getActivity(), "Notifikasi Release di matikan", Toast.LENGTH_LONG).show();

            } else {
                releaseNotification.setReminder(getActivity());
                Log.d("reminder", "reminder berhasil");
                Toast.makeText(getActivity(), "Notifikasi Release Hidup", Toast.LENGTH_LONG).show();

            }
        }
    }
}
