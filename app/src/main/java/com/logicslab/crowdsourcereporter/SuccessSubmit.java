package com.logicslab.crowdsourcereporter;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessSubmit extends AppCompatActivity {

    TextView ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_submit);

        ok = findViewById(R.id.tvOk);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/custom.ttf");
        ok.setTypeface(customFont);

    }
}
