package com.example.tarde.sharedpreferences;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferenciasFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);
    }
}
