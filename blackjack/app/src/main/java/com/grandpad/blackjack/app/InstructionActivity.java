package com.grandpad.blackjack.app;

import android.app.Activity;
//import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;


public class InstructionActivity extends FirstActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howto_screen);
    }



    public void howtoDouble (View view) {
        new AlertDialog("Tap double down to double your bet and add one more card to your hand", "Double Down",  R.drawable.alert, false, this).show();

    }


    public void howtoHit(View view) {
        new AlertDialog("Tap the hit button if you want to add another card to your hand", "Hit", R.drawable.alert, false, this).show();

    }

    public void howtoStand(View view) {
        new AlertDialog("Tap Stand to complete your round and reveal the dealer's hand", "Stand", R.drawable.alert, false, this).show();

    }

    public void howtoBet(View view) {
        new AlertDialog("Add and subtract from the amount you want to bet and tap submit bet when you are happy with your choice", "Bet", R.drawable.alert, false, this).show();
    }

    public void howtoCancelBet(View view) {
        new AlertDialog("Cancel your bet and place a new one", "Cancel Bet", R.drawable.alert, false, this).show();
    }


    public void howtoDealersHand(View view) {
        new AlertDialog("This is the dealer's hand", "Dealer's Hand", R.drawable.alert, false, this).show();
    }

    public void howtoYourHand(View view) {
        new AlertDialog("This is your hand", "Your Hand", R.drawable.alert, false, this).show();
    }

    public void howtoSideBar(View view) {
        new AlertDialog("These are each of your playing options;Tap each one to learn when to use them", "Play Options", R.drawable.alert, false, this).show();
    }




}
















