package ru.plesser.geekbrainsui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "UI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initList();
    }

    private void initList() {
        String[] versions = getResources().getStringArray(R.array.version_name);
        TypedArray imgs = getResources().obtainTypedArray(R.array.version_logos);

        LinearLayout layoutList = findViewById(R.id.layoutList);
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < versions.length; i++){
            String version = versions[i];
            Log.d(TAG, version);
            View item = inflater.inflate(R.layout.android_item, layoutList, false);
            TextView tv = item.findViewById(R.id.textAndroid);
            tv.setText(version);
            AppCompatImageView imgLogo = item.findViewById(R.id.imageAndroid);
            imgLogo.setImageResource(imgs.getResourceId(i, -1));
            layoutList.addView(item);
        }
    }

    private void initViews() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/19659.ttf");
        TextView tvDescLang = findViewById(R.id.textVLang);
        tvDescLang.setTypeface(tf);
        tvDescLang.setText(getText(R.string.descriptionLanguage));
        AppCompatImageView image = findViewById(R.id.imageView);
        loadImageFromAsset(image, "android.png");

        Button stylesButton = findViewById(R.id.button_styles);
        stylesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StylesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadImageFromAsset(ImageView image, String fileName){
        try {
            InputStream ims = getAssets().open(fileName);
            Drawable d = Drawable.createFromStream(ims, null);
            image.setImageDrawable(d);
        } catch (IOException e) {
            return;
        }
    }
}