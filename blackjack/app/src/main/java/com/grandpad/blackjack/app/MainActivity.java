package com.grandpad.blackjack.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button hit = (Button) findViewById(R.id.btn_hit);
        Button doubledown = (Button) findViewById(R.id.btn_double);
        Button split = (Button) findViewById(R.id.btn_split);


    }

    public void hitCommands(View view) {
        // If hit is clicked hide all buttons besides hit and stand
        //TO-DO add another card
        btn_double.setVisibility(View.GONE);
        btn_split.setVisibility(View.GONE);

    }

    public void doubledownCommands(View view) {
        // If doubleDown is clicked hide all buttons besides next round (not made yet)
        // TO-DO double the bet and add last card
        btn_double.setVisibility(View.GONE);
        btn_split.setVisibility(View.GONE);
        btn_bet.setVisibility(View.GONE);

    }

    public void splitCommands(View view) {
        // If split is clicked hide all buttons besides hit and stand
        //and display which hand they're playing on
        // TO-DO double the bet and add another card
        btn_double.setVisibility(View.GONE);
        btn_split.setVisibility(View.GONE);
        btn_bet.setVisibility(View.GONE);
        tv_text_lefthand.setVisibility(View.VISIBLE);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
