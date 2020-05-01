package com.fanaye.sorantravel.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.MainActivity;
import com.fanaye.sorantravel.R;

import java.util.Locale;

public class SettingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private boolean firstTime = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        if(getResources().getConfiguration().getLocales().get(0).getLanguage().equalsIgnoreCase("ku")){
            root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        Spinner lang_spinner = root.findViewById(R.id.lang_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.languages, R.layout.lang_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang_spinner.setAdapter(adapter);
        lang_spinner.setSelection(getCurrentLang());
        lang_spinner.setOnItemSelectedListener(this);
        return root;
    }

    int getCurrentLang() {
        int LANG = 0;
        switch (getResources().getConfiguration().getLocales().get(0).getLanguage()) {
            case "en":
                LANG = 0;
                break;
            case "ku":
                LANG = 1;
                break;
            case "ar":
                LANG = 2;
                break;
        }
        return LANG;
    }

    void setLocale(String lang) {
        Locale newLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(newLocale);
        res.updateConfiguration(conf, dm);
        startActivity(new Intent(getContext(), MainActivity.class));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if (firstTime) {
            firstTime = false;
            return;
        }
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String LANG = Locale.getDefault().getLanguage();
        switch (pos) {
            case 0:
                LANG = "en";
                break;
            case 1:
                LANG = "ku";
                break;
            case 2:
                LANG = "ar";
                break;
        }
        setLocale(LANG);
        sharedPreferences.edit().putString("lang", LANG).apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}