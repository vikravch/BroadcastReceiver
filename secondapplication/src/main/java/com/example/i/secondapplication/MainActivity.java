package com.example.i.secondapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String actionVal = intent.getAction();
        ((TextView) findViewById(R.id.tvSecond)).setText(actionVal);

        Intent intentResult = new Intent();

        intentResult.putExtra("var",12345);
        setResult(12, intentResult);
    }
}
