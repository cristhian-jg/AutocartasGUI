package com.crisgon.autocartasgui;

/**
 * Created by @cristhian-jg on 22/02/2020.
 */
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Created by @cristhian-jg on 12/01/2020.
 */
public class SettingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}