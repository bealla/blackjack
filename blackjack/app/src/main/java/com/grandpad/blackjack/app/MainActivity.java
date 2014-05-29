package com.grandpad.blackjack.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    Button btn_split;
    Button btn_double;
    Button btn_hit;
    Button btn_stand;

    Button btn_minus_one;
    Button btn_minus_five;
    Button btn_plus_one;
    Button btn_plus_five;

    Button btn_bet;
    Button btn_cancel_bet;
//    TextView tv_text_lefthand; TODO: add splitting

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_split = (Button) findViewById(R.id.btn_split);
        btn_double = (Button) findViewById(R.id.btn_double);
        btn_hit = (Button) findViewById(R.id.btn_hit);
        btn_stand = (Button) findViewById(R.id.btn_stand);

        btn_minus_one = (Button) findViewById(R.id.btn_split);
        btn_minus_five = (Button) findViewById(R.id.btn_double);
        btn_plus_one = (Button) findViewById(R.id.btn_hit);
        btn_plus_five = (Button) findViewById(R.id.btn_stand);

        btn_bet = (Button) findViewById(R.id.btn_bet);
        btn_cancel_bet = (Button) findViewById(R.id.btn_cancel_bet);

        //TODO: create set bet screen
        setBetScreen();
        //TODO: create set first choice screen
        //TODO: create set hit/stand screen

    }

    public void setBetScreen() {

    }

    public void hitCommands(View view) {
        // If hit is clicked hide all buttons besides hit and stand
        //TODO: add another card
        btn_double.setVisibility(View.GONE);
        btn_split.setVisibility(View.GONE);

    }

    public void doubledownCommands(View view) {
        // If doubleDown is clicked hide all buttons besides next round (not made yet)
        // TODO: double the bet and add last card
        btn_double.setVisibility(View.GONE);
        btn_split.setVisibility(View.GONE);
        btn_bet.setVisibility(View.GONE);

    }

//    public void splitCommands(View view) {
//        // If split is clicked hide all buttons besides hit and stand
//        //and display which hand they're playing on
//        // TO-DO double the bet and add another card
//        btn_double.setVisibility(View.GONE);
//        btn_split.setVisibility(View.GONE);
//        btn_bet.setVisibility(View.GONE);
//        tv_text_lefthand.setVisibility(View.VISIBLE);
//    }

}
