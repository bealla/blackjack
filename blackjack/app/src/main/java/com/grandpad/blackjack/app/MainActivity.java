    package com.grandpad.blackjack.app;

    import android.os.Bundle;
    import android.support.v7.app.ActionBarActivity;


    public class MainActivity extends ActionBarActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.start_screen);
        }
        Intent myIntent = new Intent(MainActivity.this, NextActivity.class);

        CurrentActivity.this.startActivity(myIntent);

    }
