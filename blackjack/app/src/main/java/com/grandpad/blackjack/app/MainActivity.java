package com.grandpad.blackjack.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    int chips;
    int currentBet;
    screens currentScreen;
    Random nextCard = new Random();
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

        tv_text_bank = (TextView) findViewById(R.id.tv_text_bank);
        tv_text_bet = (TextView) findViewById(R.id.tv_text_bet);

        tv_text_bank.setText(String.valueOf(chips));
        currentBet = 0;
        tv_text_bet.setText(String.valueOf(currentBet));

        setBetScreen();
        //setFirstChoice();
        //setHitScreen();
    }

    //TODO: stop bet from exceeding current chips
    public void split(View v) {
        switch (currentScreen) {
            case BET:
                if (currentBet >= 1)
                    currentBet--;
                updateBet();
                break;//subtract 1
            case FIRSTCHOICE:

                break;//splits
        }
    }

    public void doubleHand(View v) {
        switch (currentScreen) {
            case BET:
                if (currentBet > 5)
                    currentBet -= 5;
                else
                    currentBet = 0;
                updateBet();
                break;//minus 5
            case FIRSTCHOICE://set double down and goto game over
                currentBet = currentBet * 2;
                tv_text_bet.setText(String.valueOf(currentBet));
                player_hand[2] = getNextCard();
                setResultScreen();
                break;
        }

    }

    public void hit(View v) {
        switch (currentScreen) {
            case BET:
                currentBet++;
                updateBet();
                break;//plus 1
            case FIRSTCHOICE://hits then set screen to hit
                break;
        }
    }

    public void stand(View v) {
        switch (currentScreen) {
            case BET:
                currentBet += 5;
                updateBet();
                break;//plus 5
            case FIRSTCHOICE://stop and set screen to game over
                break;
        }
    }

    public void bet(View v) {
        switch (currentScreen) {
            case BET://sets bet /goto firstchoice
                chips -= currentBet;
                tv_text_bank.setText(String.valueOf(chips));
                setFirstChoice();
                break;
        }
    }

    public void cancelBet(View v) {
        switch (currentScreen) {
            case BET://setbet to 0 and continue
                currentBet = 0;
                updateBet();
                break;
        }
    }

    public void setBetScreen() {
        currentScreen = screens.BET;
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
        currentScreen = screens.FIRSTCHOICE;
        btn_minus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_minus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);

        btn_minus_one.setTextColor(Color.DKGRAY);
        btn_plus_one.setTextColor(Color.DKGRAY);
        btn_minus_five.setTextColor(Color.DKGRAY);
        btn_plus_five.setTextColor(Color.DKGRAY);

        btn_bet.setVisibility(View.INVISIBLE);
        btn_cancel_bet.setVisibility(View.INVISIBLE);

        //reset hand
        dealer_hand = new int[10];
        player_hand = new int[10];

        //create starting hands
        player_hand[0] = getNextCard();
        dealer_hand[0] = getNextCard();
        player_hand[1] = getNextCard();
        dealer_hand[1] = getNextCard();

        //TODO: display hands
        showPlayerHand();
        showDealerHand();
    }

    private int getNextCard() {
        int i = -1;
        i = nextCard.nextInt(53);
        if (checkAlreadyUsed(i)) {
            i = getNextCard();
        }
        return i;
    }

    private boolean checkAlreadyUsed(int i) {
        boolean used = false;
        for (int k : dealer_hand) {
            if (k == i)
                used = true;
        }
        for (int k : player_hand) {
            if (k == i)
                used = true;
        }
        return used;
    }

    private void showPlayerHand() {
        //TODO: make this do stuff
    }

    private void showDealerHand() {
        //TODO: make this do stuff
    }


    /*
    enum suit
    select case 1-4
    enum rank
    mod?
    select case
     */

    private void setHitScreen() {
        //TODO: fill in
        currentScreen = screens.HIT;
        btn_minus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_one.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_minus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_plus_five.setBackgroundResource(R.drawable.abc_item_background_holo_light);
        btn_bet.setVisibility(View.INVISIBLE);
        btn_cancel_bet.setVisibility(View.INVISIBLE);
    }

    public void updateBet() {
        tv_text_bet.setText(String.valueOf(currentBet));
    }

    //divides by 13 then returns remainder
    public int calculateNum(int cardNum) {
        int num = cardNum % 13;
        if (num == 0)
            num = 13;
        return num;
    }

    public int calculateValue(int cardNum) {

        //todo: add case for Ace = 1
        int value = -1;
        if (cardNum > 9)
            value = 10;
        else if (cardNum > 1)
            value = cardNum;
        else if (cardNum == 1)
            value = 13;
        return value;
    }

    public enum screens {BET, FIRSTCHOICE, HIT, DOUBLEDOWN, GAMEOVER}

    private void setResultScreen() {
        btn_split.setVisibility(View.INVISIBLE);
        btn_double.setVisibility(View.INVISIBLE);
        btn_hit.setVisibility(View.INVISIBLE);
        btn_stand.setVisibility(View.INVISIBLE);
        btn_hit.setVisibility(View.INVISIBLE);
        btn_cancel_bet.setVisibility(View.INVISIBLE);

        //TODO: show who wins
    }

}
