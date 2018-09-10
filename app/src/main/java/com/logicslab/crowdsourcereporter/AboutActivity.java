package com.logicslab.crowdsourcereporter;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        t1 = findViewById(R.id.tvAbout1);
        t2 = findViewById(R.id.tvAbout2);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/custom.ttf");
        t1.setTypeface(customFont);
        t2.setTypeface(customFont);

    }
}
