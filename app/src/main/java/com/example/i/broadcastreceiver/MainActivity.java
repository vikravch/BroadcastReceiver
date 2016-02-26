package com.example.i.broadcastreceiver;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_NAME ="LogTag" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Log.d(LOG_NAME," intent request "+intent.getIntExtra("request",-1));
        ((Button) findViewById(R.id.btnIntent)).setOnClickListener(this);
    }

    public static final int MAKE_PHOTO = 12;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIntent:
                try {

                    Intent intent = new Intent();
                    intent.setAction("com.example.i.secondapplication.RUN");
                    startActivityForResult(intent, MAKE_PHOTO);

                } catch (ActivityNotFoundException ex){
                    Toast.makeText(this,"Not found activity",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ((TextView) findViewById(R.id.tvFirstApps))
                .setText(
                        String.format("Request code - %d, ResultCode - %d, Data from intent - %d "
                                , requestCode, resultCode, data.getIntExtra("var", 0))
                );
        switch (requestCode){
            case MAKE_PHOTO:

                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}