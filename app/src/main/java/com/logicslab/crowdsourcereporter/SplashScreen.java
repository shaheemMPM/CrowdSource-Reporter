package com.logicslab.crowdsourcereporter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView splashText;
    private static int splashTimeOut = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashText = findViewById(R.id.tvSplashName);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/custom.ttf");
        splashText.setTypeface(customFont);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomePage = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(HomePage);
                finish();
            }
        }, splashTimeOut);

    }
}
