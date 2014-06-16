package com.grandpad.blackjack.app;

//import android.app.AlertDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    Button btn_double;
    Button btn_hit;
    Button btn_stand;
    //alt names for the buttons listed above
    Button btn_minus_one;
    Button btn_plus_one;
    //standalone buttons
    Button btn_bet;
    Button btn_cancel_bet;
    ImageView img_deck;

    MediaPlayer mp_win;
    MediaPlayer mp_lose;


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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        chips = settings.getInt("chips", 1000); //get saved chips. if non-existent set to 1000


        if (chips == 0) {
            chips = 1000;
            //haveChips();
        }


       // btn_split = (Button) findViewById(R.id.btn_split);
        btn_double = (Button) findViewById(R.id.btn_double);
        btn_hit = (Button) findViewById(R.id.btn_hit);
        btn_stand = (Button) findViewById(R.id.btn_stand);


        //TODO fix the betting buttons
        btn_minus_one = (Button) findViewById(R.id.btn_hit);
        //btn_minus_five = (Button) findViewById(R.id.btn_double);
        btn_plus_one = (Button) findViewById(R.id.btn_stand);
        //btn_plus_five = (Button) findViewById(R.id.btn_stand);

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
                // make sure doesn't exceed chips available
                if (chips >= currentBet * 2) {
                    chips = chips - currentBet;
                    tv_text_bank.setText(String.valueOf(chips));
                    currentBet = currentBet * 2;
                    updateBet();
                    playerHand.add(getNextCard());
                    showPlayerHand();
                    setResultScreen();
                } else
                    notEnoughChips();
                break;
        }

    }

    // stop bet from exceeding current chips
    public void hit(View v) {
        switch (currentScreen) {
            case BET:
                if (chips > currentBet) {
                    currentBet++;
                    updateBet();
                } else
                    notEnoughChips();
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
                if (chips >= currentBet + 5) {
                    currentBet += 5;
                    updateBet();
                } else
                    notEnoughChips();
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


    public void notEnoughChips()
    {
        new AlertDialog("You do not have enough chips to complete your bet", "Chips",  R.drawable.alert, false, this).show();
    }


    public void setBetScreen() {
        currentScreen = screens.BET;


        btn_minus_one.setBackgroundResource(R.drawable.btn_red);
        btn_plus_one.setBackgroundResource(R.drawable.btn_green);

        btn_minus_one.setText("-1");
        btn_plus_one.setText("+1");
        btn_minus_one.setTextColor(Color.WHITE);
        btn_plus_one.setTextColor(Color.WHITE);
        btn_double.setVisibility(View.INVISIBLE);
    }

    private void setFirstChoice() {


        currentScreen = screens.FIRSTCHOICE;
        btn_minus_one.setBackgroundResource(android.R.drawable.btn_default);
        btn_plus_one.setBackgroundResource(android.R.drawable.btn_default);

        btn_minus_one.setTextColor(Color.DKGRAY);
        btn_plus_one.setTextColor(Color.DKGRAY);
        btn_double.setTextColor(Color.DKGRAY);

        btn_minus_one.setText("Hit");
        btn_plus_one.setText("Stand");

        btn_bet.setVisibility(View.INVISIBLE);
        btn_cancel_bet.setVisibility(View.INVISIBLE);
        btn_double.setVisibility(View.VISIBLE);

        //reset hand
        playerHand = new ArrayList<Integer>();
        dealerHand = new ArrayList<Integer>();

        //create starting hands
        playerHand.add(getNextCard());
        dealerHand.add(getNextCard());
        playerHand.add(getNextCard());
        dealerHand.add(getNextCard());

        //if (calculateValue(playerHand.get(0)) == calculateValue(playerHand.get(1)))
        //  btn_split.setActivated(true);
        //else
        //  btn_split.setActivated(false);
        //display hands
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
                player_row.addView(view, 136, 190);
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
                    dealer_row.addView(view, 136, 190);
                }
            }
        } else {
            int k = dealerHand.get(0); //should always have a card. be careful of calling this method if it doesn't
            ImageView view = new ImageView(MainActivity.this);
            view.setImageResource(getImageID(getSuit(k), calculateNum(k)));
            dealer_row.addView(view, 136, 190);
            ImageView back = new ImageView(MainActivity.this);
            back.setImageResource(R.drawable.card_back);
            dealer_row.addView(back, 136, 190);
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
                        imageID = R.drawable.card_heart_02;
                        break;
                    case 3:
                        imageID = R.drawable.card_heart_03;
                        break;
                    case 4:
                        imageID = R.drawable.card_heart_04;
                        break;
                    case 5:
                        imageID = R.drawable.card_heart_05;
                        break;
                    case 6:
                        imageID = R.drawable.card_heart_06;
                        break;
                    case 7:
                        imageID = R.drawable.card_heart_07;
                        break;
                    case 8:
                        imageID = R.drawable.card_heart_08;
                        break;
                    case 9:
                        imageID = R.drawable.card_heart_09;
                        break;
                    case 10:
                        imageID = R.drawable.card_heart_10;
                        break;
                    case 11:
                        imageID = R.drawable.card_heart_11;
                        break;
                    case 12:
                        imageID = R.drawable.card_heart_12;
                        break;
                    case 13:
                        imageID = R.drawable.card_heart_13;
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
        currentScreen = screens.HIT;
        //btn_split.setVisibility(View.INVISIBLE);
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


        int value = -1;
        if (cardNum > 9)
            value = 10 ;
        else if (cardNum > 1)
            value = cardNum;
        else if (cardNum == 1)
            value = 11;
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

    private int calculateTotalValue(ArrayList<Integer> cardList) {
        int totalValue = 0;
        int aceValue = 0;

        for (int card : cardList) {
            if (totalValue >= 11 && calculateValue(calculateNum(card)) == 11) {

                totalValue += 1;
            } else {
                if(calculateValue(calculateNum(card)) == 11){
                    totalValue += 11;
                    aceValue +=1;
                }
                else {


                    totalValue += calculateValue(calculateNum(card));
                }
            }
        }
        while (totalValue >21 && aceValue > 0) {
            totalValue -= 10;
            aceValue -= 1;
        }


        return totalValue;

    }



    private boolean checkBust(ArrayList<Integer> cardList, boolean dealer) {
        boolean bust = false;
        int totalValue = calculateTotalValue(cardList);
        if (dealer) {
            if (totalValue > 16)
                bust = true;
        } else {
            if (totalValue > 21)
                bust = true;
        }
        return bust;
    }

    private void setResultScreen() {

        //todo: slowly flip dealer cards
        //show win/lose
        while (!checkBust(dealerHand, true)) {
            dealerHand.add(getNextCard());
        }
        showDealerHand(false);
       // btn_split.setVisibility(View.GONE);
        btn_double.setVisibility(View.GONE);
        btn_hit.setVisibility(View.GONE);
        btn_stand.setVisibility(View.GONE);
        btn_hit.setVisibility(View.GONE);
        btn_cancel_bet.setVisibility(View.GONE);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        //check who wins
        int playerValue = -1;
        int dealerValue = -1;
        int winnings = currentBet;
        boolean playerWins = false;
        if (!checkBust(playerHand, false))
            playerValue = calculateTotalValue(playerHand);
        if (!checkBust(dealerHand, false))
            dealerValue = calculateTotalValue(dealerHand);

        if (playerValue > dealerValue) {
            winnings = currentBet * 2;
            playerWins = true;
        }


        Button myButton = new Button(this);
        //TextView winnerText = new TextView(this);
        if (playerWins) {
            //winnerText.setText("Congratulations, You've won: " + winnings + " chips!");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    //TODO make the last parameter and winnings work when wrapped up in the run funciton, add delay before displaying dialog for all results
                    //new AlertDialog("Congratulations, You've won: " + winnings + " chips!", "Chips",  R.drawable.alert, false, this).show();


                }
            }, 5000);

            //TODO add sounds into raw folder and see if this media player works
            mp_win = MediaPlayer.create(this, R.raw.tadasoundeffect);
            mp_win.start();

            chips += winnings;

            myButton.setText("Next Round");

        } else if (chips == 0){
            //does not actually go to the bet screen, still gives option for next round but with reset chips
            new AlertDialog("Oh no, you lost, here are 1000 chips to start a new game", "New Game",  R.drawable.alert, false, this).show();
            mp_lose = MediaPlayer.create(this, R.raw.sadtrombonesound);
            mp_lose.start();


            myButton.setText("New Game");

            //setBetScreen();//this needs to be fixed
        } else if (!playerWins) {
            new AlertDialog("Oh no, you lost: " + winnings + " chips.", "Chips", R.drawable.alert, false, this).show();
            mp_lose = MediaPlayer.create(this, R.raw.sadtrombonesound);
            mp_lose.start();

            myButton.setText("Next Round");


        }



        //winnerText.setWidth(175);
        //winnerText.setHeight(100);
        //myButton.setText("Next Round");
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout ll_rightColumn = (LinearLayout) findViewById(R.id.ll_rightColumn);

        //ll_rightColumn.addView(winnerText);
        ll_rightColumn.addView(myButton, 175, 100);

        settings.edit().putInt("chips", chips).commit();
    }

    public enum screens {BET, FIRSTCHOICE, HIT, DOUBLEDOWN, GAMEOVER}

    public enum suit {Spade, Heart, Diamond, Club}

}
