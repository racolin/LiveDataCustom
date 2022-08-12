package com.banvie.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ImageButton ibt;
    ProgressBar pgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ibt = findViewById(R.id.ibt);
        pgb = findViewById(R.id.pgb);

        ibt.setOnClickListener((view) -> {
            onLoading();
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    runOnUiThread(() -> {
                        onLoadingSuccess();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    private void onLoading() {
        pgb.setVisibility(View.VISIBLE);
        ibt.setVisibility(View.INVISIBLE);
    }

    private void onLoadingSuccess() {
        pgb.setVisibility(View.INVISIBLE);
        ibt.setVisibility(View.VISIBLE);
    }
}