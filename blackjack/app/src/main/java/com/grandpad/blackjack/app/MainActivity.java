package com.grandpad.blackjack.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    int chips;
    int currentBet;
    screens currentScreen;

    public enum screens {BET, FIRSTCHOICE, HIT, DOUBLEDOWN, GAMEOVER}

    ;
    //buttons on the right
    Button btn_split;
    Button btn_double;
    Button btn_hit;
    Button btn_stand;
    //alt names for the buttons listed above
    Button btn_minus_one;
    Button btn_minus_five;
    Button btn_plus_one;
    Button btn_plus_five;
    //standalone buttons
    Button btn_bet;
    Button btn_cancel_bet;
    ImageView img_deck;
//    TextView tv_text_lefthand; TODO: add splitting

    //cards
    ImageView img_dealer_revealed;
    ImageView img_dealer_hidden;
    ImageView img_player_1;
    ImageView img_player_2;
    TextView tv_text_bank;
    TextView tv_text_bet;
    //card arrays
    int[] deck = new int[53];
    int[] dealer_hand = new int[10];
    int[] player_hand = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        chips = settings.getInt("chips", 1000); //get saved chips. if non-existent set to 1000

        for (int i : deck) {
            deck[i] = i;
        }

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

        img_deck = (ImageView) findViewById(R.id.img_deck);
        img_dealer_revealed = (ImageView) findViewById(R.id.img_dealer_revealed);
        img_dealer_hidden = (ImageView) findViewById(R.id.img_dealer_hidden);
        img_player_1 = (ImageView) findViewById(R.id.img_player_1);
        img_player_2 = (ImageView) findViewById(R.id.img_player_2);

        tv_text_bank = (TextView) findViewById(R.id.tv_text_bank);
        tv_text_bet = (TextView) findViewById(R.id.tv_text_bet);

        tv_text_bank.setText(chips);
        currentBet = 0;
        tv_text_bet.setText(currentBet);

        setBetScreen();
        //setFirstChoice();
        //setHitScreen();
    }

    //TODO: button on_click listeners. Will check for current state. Logic will be based off current state
    public void split(View v) {
        switch (currentScreen) {
            case BET:
                break;//subtract 1
            case FIRSTCHOICE:
                break;//splits
        }
    }

    public void doubleHand(View v) {
        switch (currentScreen) {
            case BET:
                break;//minus 5
            case FIRSTCHOICE:
                break; //set double down and goto game over
        }

    }

    public void hit(View v) {
        switch (currentScreen) {
            case BET:
                break;//plus 1
            case FIRSTCHOICE:
                break; //hits then set screen to hit
        }
    }

    public void stand(View v) {
        switch (currentScreen) {
            case BET:
                break;//plus 5
            case FIRSTCHOICE:
                break; //stop and set screen to game over
        }
    }

    public void bet(View v) {
        switch (currentScreen) {
            case BET:
                break;//sets bet /goto firstchoice
        }
    }

    public void cancelBet(View v) {
        switch (currentScreen) {
            case BET:
                break;//setbet to 0 and continue
        }
    }

    public void setBetScreen() {
        currentScreen = screens.BET;
        img_deck.setVisibility(View.INVISIBLE);
        img_dealer_revealed.setVisibility(View.INVISIBLE);
        img_dealer_hidden.setVisibility(View.INVISIBLE);
        img_player_1.setVisibility(View.INVISIBLE);
        img_player_2.setVisibility(View.INVISIBLE);
        btn_minus_one.setBackgroundResource(R.drawable.penny);
        btn_plus_one.setBackgroundResource(R.drawable.penny);
        btn_minus_five.setBackgroundResource(R.drawable.nickel);
        btn_plus_five.setBackgroundResource(R.drawable.nickel);
        btn_minus_one.setTextColor(Color.TRANSPARENT);
        btn_plus_one.setTextColor(Color.TRANSPARENT);
        btn_minus_five.setTextColor(Color.TRANSPARENT);
        btn_plus_five.setTextColor(Color.TRANSPARENT);

    }

    private void setFirstChoice() {
        //TODO: fill in
        btn_minus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_minus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);

        btn_minus_one.setTextColor(Color.DKGRAY);
        btn_plus_one.setTextColor(Color.DKGRAY);
        btn_minus_five.setTextColor(Color.DKGRAY);
        btn_plus_five.setTextColor(Color.DKGRAY);

    }

    private void setHitScreen() {
        //TODO: fill in
        btn_minus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_minus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);

    }

    public void hitCommands(View view) {
        // If hit is clicked hide all buttons besides hit and stand
        //TODO: add another card

    }

    public void doubledownCommands(View view) {
        // If doubleDown is clicked hide all buttons besides next round (not made yet)
        // TODO: double the bet and add last card

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
