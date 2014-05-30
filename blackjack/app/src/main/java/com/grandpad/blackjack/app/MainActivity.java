package com.grandpad.blackjack.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
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

    TableRow player_row;
    TableRow dealer_row;
    //card arrays
    ArrayList<Integer> dealerHand = new ArrayList<Integer>();
    ArrayList<Integer> playerHand = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        chips = settings.getInt("chips", 1000); //get saved chips. if non-existent set to 1000

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

        player_row = (TableRow) findViewById(R.id.player_row);
        dealer_row = (TableRow) findViewById(R.id.dealer_row);

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
                playerHand.add(getNextCard());
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
                playerHand.add(getNextCard());
                setHitScreen();
                showPlayerHand();
                if (checkBust(playerHand, false))
                    setResultScreen();
                break;
            case HIT:
                playerHand.add(getNextCard()); //gets a card
                showPlayerHand();
                if (checkBust(playerHand, false))
                    setResultScreen();
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
                setResultScreen();
                break;
            case HIT:
                setResultScreen();
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

        //todo: check for splitting as an option. only show if possible
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
        playerHand = new ArrayList<Integer>();
        dealerHand = new ArrayList<Integer>();

        //create starting hands
        playerHand.add(getNextCard());
        dealerHand.add(getNextCard());
        playerHand.add(getNextCard());
        dealerHand.add(getNextCard());

        //TODO: display hands
        showPlayerHand();
        showDealerHand(true);
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
        for (int k : dealerHand) {
            if (k == i)
                used = true;
        }
        for (int k : playerHand) {
            if (k == i)
                used = true;
        }
        return used;
    }

    private void showPlayerHand() {
        player_row.removeAllViews();
        for (int k : playerHand) {
            if (k != 0) //make sure its an initialized variable. no card will ever be 0
            {
                ImageView view = new ImageView(MainActivity.this);
                view.setImageResource(getImageID(getSuit(k), calculateNum(k)));
                player_row.addView(view, 100, 175);
            }
        }
    }
    private void showDealerHand(boolean hidden) { //checks to see if one card should be hidden
        dealer_row.removeAllViews();
        if (!hidden) {
            for (int k : dealerHand) {
                if (k != 0) //make sure its an initialized variable. no card will ever be 0
                {
                    ImageView view = new ImageView(MainActivity.this);
                    view.setImageResource(getImageID(getSuit(k), calculateNum(k)));
                    dealer_row.addView(view, 100, 175);
                }
            }
        } else {
            int k = dealerHand.get(0); //should always have a card. be careful of calling this method if it doesn't
            ImageView view = new ImageView(MainActivity.this);
            view.setImageResource(getImageID(getSuit(k), calculateNum(k)));
            dealer_row.addView(view, 100, 150);
            ImageView back = new ImageView(MainActivity.this);
            back.setImageResource(R.drawable.card_back);
            dealer_row.addView(back, 100, 150);
        }
    }

    private int getImageID(suit cardSuit, int cardNum) {
        int imageID = -1;
        switch (cardSuit) {
            case Spade:
                switch (cardNum) {
                    case 1:
                        imageID = R.drawable.card_spade_01;
                        break;
                    case 2:
                        imageID = R.drawable.card_spade_02;
                        break;
                    case 3:
                        imageID = R.drawable.card_spade_03;
                        break;
                    case 4:
                        imageID = R.drawable.card_spade_04;
                        break;
                    case 5:
                        imageID = R.drawable.card_spade_05;
                        break;
                    case 6:
                        imageID = R.drawable.card_spade_06;
                        break;
                    case 7:
                        imageID = R.drawable.card_spade_07;
                        break;
                    case 8:
                        imageID = R.drawable.card_spade_08;
                        break;
                    case 9:
                        imageID = R.drawable.card_spade_09;
                        break;
                    case 10:
                        imageID = R.drawable.card_spade_10;
                        break;
                    case 11:
                        imageID = R.drawable.card_spade_11;
                        break;
                    case 12:
                        imageID = R.drawable.card_spade_12;
                        break;
                    case 13:
                        imageID = R.drawable.card_spade_13;
                        break;
                }
                break;
            case Heart:
                switch (cardNum) {
                    case 1:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 2:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 3:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 4:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 5:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 6:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 7:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 8:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 9:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 10:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 11:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 12:
                        imageID = R.drawable.card_heart_01;
                        break;
                    case 13:
                        imageID = R.drawable.card_heart_01;
                        break;
                }
                break;

            case Diamond:
                switch (cardNum) {
                    case 1:
                        imageID = R.drawable.card_diamond_01;
                        break;
                    case 2:
                        imageID = R.drawable.card_diamond_02;
                        break;
                    case 3:
                        imageID = R.drawable.card_diamond_03;
                        break;
                    case 4:
                        imageID = R.drawable.card_diamond_04;
                        break;
                    case 5:
                        imageID = R.drawable.card_diamond_05;
                        break;
                    case 6:
                        imageID = R.drawable.card_diamond_06;
                        break;
                    case 7:
                        imageID = R.drawable.card_diamond_07;
                        break;
                    case 8:
                        imageID = R.drawable.card_diamond_08;
                        break;
                    case 9:
                        imageID = R.drawable.card_diamond_09;
                        break;
                    case 10:
                        imageID = R.drawable.card_diamond_10;
                        break;
                    case 11:
                        imageID = R.drawable.card_diamond_11;
                        break;
                    case 12:
                        imageID = R.drawable.card_diamond_12;
                        break;
                    case 13:
                        imageID = R.drawable.card_diamond_13;
                        break;
                }
                break;

            case Club:
                switch (cardNum) {
                    case 1:
                        imageID = R.drawable.card_club_01;
                        break;
                    case 2:
                        imageID = R.drawable.card_club_02;
                        break;
                    case 3:
                        imageID = R.drawable.card_club_03;
                        break;
                    case 4:
                        imageID = R.drawable.card_club_04;
                        break;
                    case 5:
                        imageID = R.drawable.card_club_05;
                        break;
                    case 6:
                        imageID = R.drawable.card_club_06;
                        break;
                    case 7:
                        imageID = R.drawable.card_club_07;
                        break;
                    case 8:
                        imageID = R.drawable.card_club_08;
                        break;
                    case 9:
                        imageID = R.drawable.card_club_09;
                        break;
                    case 10:
                        imageID = R.drawable.card_club_10;
                        break;
                    case 11:
                        imageID = R.drawable.card_club_11;
                        break;
                    case 12:
                        imageID = R.drawable.card_club_12;
                        break;
                    case 13:
                        imageID = R.drawable.card_club_13;
                        break;
                }
                break;
        }
        return imageID;
    }

    private void setHitScreen() {
        //TODO: fill in
        currentScreen = screens.HIT;
        btn_split.setVisibility(View.INVISIBLE);
        btn_double.setVisibility(View.INVISIBLE);
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

    public int calculateValue(int cardNum) { //make sure you calculateNum first

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

    public suit getSuit(int cardNum) {
        suit cardSuit;
        if (cardNum <= 13)
            cardSuit = suit.Spade;
        else if (cardNum <= 26)
            cardSuit = suit.Heart;
        else if (cardNum <= 39)
            cardSuit = suit.Diamond;
        else
            cardSuit = suit.Club;
        return cardSuit;
    }

    private boolean checkBust(ArrayList<Integer> cardList, boolean dealer) {
        boolean bust = false;
        int totalValue = 0;
        if (dealer) {
            for (int card : cardList) {
                totalValue += calculateValue(calculateNum(card));
            }
            if (totalValue > 16)
                bust = true;
        } else {
            for (int card : cardList) {
                totalValue += calculateValue(calculateNum(card));
            }
            if (totalValue > 21)
                bust = true;
        }
        return bust;
    }

    private void setResultScreen() {

        //todo: slowly flip dealer cards
        //todo: show win/lose and offer next game
        while (checkBust(dealerHand, true)) {

        }
        btn_split.setVisibility(View.INVISIBLE);
        btn_double.setVisibility(View.INVISIBLE);
        btn_hit.setVisibility(View.INVISIBLE);
        btn_stand.setVisibility(View.INVISIBLE);
        btn_hit.setVisibility(View.INVISIBLE);
        btn_cancel_bet.setVisibility(View.INVISIBLE);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putInt("chips", chips);
    }

    public enum screens {BET, FIRSTCHOICE, HIT, DOUBLEDOWN, GAMEOVER}

    public enum suit {Spade, Heart, Diamond, Club}

}
