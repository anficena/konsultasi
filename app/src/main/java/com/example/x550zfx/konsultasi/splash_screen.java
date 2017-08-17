package com.example.x550zfx.konsultasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    private TextView txtTitle, txtDesc;
    private ImageView imgLogo;
    private Button btnGetStarted;
    private Animation slideUp;
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        setContentView(R.layout.activity_splash_screen);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        imgLogo = (ImageView) findViewById(R.id.imageLogo);
        btnGetStarted = (Button) findViewById(R.id.btnGetStarted);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slideup_anim);
        btnGetStarted.setVisibility(View.VISIBLE);
        btnGetStarted.startAnimation(slideUp);
        imgLogo.setVisibility(View.VISIBLE);
        imgLogo.startAnimation(slideUp);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.startAnimation(slideUp);
        txtDesc.setVisibility(View.VISIBLE);
        txtDesc.startAnimation(slideUp);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(splash_screen.this,slider_intro.class));
                finish();
            }
        });
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}