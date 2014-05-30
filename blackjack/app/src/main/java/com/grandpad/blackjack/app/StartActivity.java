package com.grandpad.blackjack.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;


public class StartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);


    }

    public void toMainActivity(View view) {
        //on start new button click go to main activity

        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        StartActivity(intent);
    }
}
