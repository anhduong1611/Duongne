package com.example.scanqrcode.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.example.scanqrcode.MainActivity;
import com.example.scanqrcode.Main_introduction;
import com.example.scanqrcode.R;

import java.util.Locale;

public class setting extends PreferenceFragmentCompat {
    static Locale language;
    SwitchPreferenceCompat sound;
    SwitchPreference sw_ligth, sw_sound, sw_vibrate, sw_copy;
    Preference vlaunguae, introcduct;
    Switch theme;
    SharedPreferences sharedPreferences,sharedPreferences_copy,preference_language,prefernce_beep,preference_vibrate;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view =inflater.inflate(R.layout.test, container, false);
//        sharedPreferences = getActivity().getSharedPreferences("nigth",0);
//        theme = view.findViewById(R.id.theme);
//        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b==true){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("nigth",true);
//                    editor.commit();
//                    editor.apply();
//                }
//                else{
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("nigth",false);
//                    editor.commit();
//                    editor.apply();
//                }
//            }
//        });
//
//        return view;
//    }

        @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setPreferencesFromResource(R.xml.root_preference, rootKey);
        inutUI();
        //Sound();
        Ligth();
        Languageitem();
        Beep();
        Vibrate();
        Introduction();
        CopyintoClipboard();
    }
    void inutUI() {
            //sound = findPreference("sound");
       sw_ligth = findPreference("v");
        sw_sound = findPreference("sound");
        sw_copy = findPreference("copy");
        sw_vibrate = findPreference("vi");
        vlaunguae = findPreference("language");
        introcduct = findPreference("introduc");
    }
    public void Sound(){
            sharedPreferences = getActivity().getSharedPreferences("nigth",0);
            sw_ligth.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                        boolean test = sw_ligth.isChecked();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                        if(test==true){

                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            editor.putInt("nigth",1);
                        }
                        else{
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            editor.putInt("nigth",0);
                        }
                    editor.commit();
                    return true;
                }
            });
            sw_ligth.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    boolean check = ((SwitchPreferenceCompat) preference).isChecked();
                    if (check==true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("nigth",true);
                    editor.commit();
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("nigth",false);
                    editor.commit();
                    editor.apply();
                }
                    return true;
                }
            });
    }

    private void Introduction() {
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        introcduct.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                Intent intent = new Intent(getContext(), Main_introduction.class);
                startActivity(intent);
                return false;
            }
        });
    }
//
    public void CopyintoClipboard(){
        sharedPreferences_copy = getActivity().getSharedPreferences("copyintoclipboard",0);
        sw_copy.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
               if(sw_copy.isChecked()){
                   SharedPreferences.Editor editor = sharedPreferences_copy.edit();
                   editor.putBoolean("copyintoclipboard",false);
                   editor.commit();
               }else{
                   SharedPreferences.Editor editor = sharedPreferences_copy.edit();
                   editor.putBoolean("copyintoclipboard",true);
                   editor.commit();
               }
                return true;
            }
        });
    }
    //Beep
    public  void Beep(){
        prefernce_beep = getActivity().getSharedPreferences("beep",0);
        sw_sound.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
           @Override
           public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
               if (sw_sound.isChecked()){
                   SharedPreferences.Editor editor = prefernce_beep.edit();
                   editor.putBoolean("beep",false);
                   editor.commit();
               } else {
                   SharedPreferences.Editor editor = prefernce_beep.edit();
                   editor.putBoolean("beep",true);
                   editor.commit();
               }
               return true;
           }
       });
    }
//    //Vibrate
    public void Vibrate(){
        preference_vibrate = getActivity().getSharedPreferences("vibrate",0);
        sw_vibrate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                if(sw_vibrate.isChecked()){
                    SharedPreferences.Editor editor = preference_vibrate.edit();
                    editor.putBoolean("vibrate",false);
                    editor.commit();

                }else
                {
                    SharedPreferences.Editor editor = preference_vibrate.edit();
                    editor.putBoolean("vibrate",true);
                    editor.commit();

                }
                return true;
            }
        });
    }

    void Ligth() {
        sharedPreferences = getActivity().getSharedPreferences("nigth",0);
//        boolean booleancheck = sharedPreferences.getBoolean("nigth",true);
//            if(booleancheck==true){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            }else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }

        sw_ligth.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (sw_ligth.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("nigth",false);
                    editor.commit();
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("nigth",true);
                    editor.commit();
                    editor.apply();
                }
                return true;
            }
        });
    }

    public void Languageitem(){
        preference_language = getActivity().getSharedPreferences("language1",0);
       // int check  = preference_language.getInt("language1",1);

        vlaunguae.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Language");
                final String [] listlanguage={"English","VietNam"};
                int check  = preference_language.getInt("language1",1);
                builder.setSingleChoiceItems(listlanguage, check, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==1)
                        {
                            language = new Locale("vi","VN");
                            SharedPreferences.Editor editor = preference_language.edit();
                            editor.putInt("language1",1);
                            editor.commit();
                        }
                        else{

                            language = new Locale("en","US");
                            SharedPreferences.Editor editor = preference_language.edit();
                            editor.putInt("language1",0);
                            editor.commit();
                        }
                       ChangeLanguage(language);
                    }
                });

                AlertDialog alert = builder.create();
                builder.show();
                return false;
            }
        });

    }


    public void ChangeLanguage(Locale locale) {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            configuration.setLocale(locale);
        else
            configuration.setLocale(locale);

        resources.updateConfiguration(configuration, displayMetrics);
        Intent refresh = new Intent(getActivity(), getActivity().getClass());
        startActivity(refresh);
        getActivity().finish();
    }

}
