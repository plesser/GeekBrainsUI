package ru.plesser.geekbrainsui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class BaseActivity extends AppCompatActivity {

    static final String NameSharedPreference = "LOGIN";
    static final String appTheme = "APP_THEME";
    static final int MyCoolCodeStyle = 0;
    static final int AppThemeLightCodeStyle = 1;
    static final int AppThemeCodeStyle = 2;
    static final int AppThemeDarkCodeStyle = 3;

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle){
            case AppThemeCodeStyle:
                return R.style.AppTheme;
            case AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.MyCoolStyle;
        }
    }

    int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);

        return sharedPref.getInt(appTheme, codeStyle);
    }

    void setAppTheme(int codeStyle){
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyCoolStyle)); // перед setContentView!!!

    }
}
