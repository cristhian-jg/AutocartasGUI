package com.crisgon.autocartasgui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by @cristhian-jg on 22/02/2020.
 */
public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();
    }
}