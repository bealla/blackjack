package com.grandpad.blackjack.app;

import android.app.Activity;
import android.app.AlertDialog;
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


public class InstructionActivity extends FirstActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howto_screen);
        ImageView arrow = (ImageView) findViewById(R.id.arrow);
        ImageView arrow2 = (ImageView) findViewById(R.id.arrow2);
        ImageView arrow3 = (ImageView) findViewById(R.id.arrow3);

        arrow = (ImageView) findViewById(R.id.arrow);
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(450);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.ABSOLUTE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        arrow.startAnimation(mAnimation);

        arrow2 = (ImageView) findViewById(R.id.arrow2);
        arrow2.startAnimation(mAnimation);

        arrow3 = (ImageView) findViewById(R.id.arrow3);
        arrow3.startAnimation(mAnimation);


        Button btn_double = (Button) findViewById(R.id.btn_double);
        btn_double.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent myIntent = new Intent(view.getContext(), agones.class);
                //startActivityForResult(myIntent, 0);


                AlertDialog alertDialog = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog.setTitle("Double Down");
                alertDialog.setMessage("Before your first hit you can double your bet and receive one more card to complete your hand");

                alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });

                alertDialog.show();  //<-- See This!
            }
        });
        Button btn_split = (Button) findViewById(R.id.btn_split);
        btn_split.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                AlertDialog alertDialog = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog.setTitle("Split");
                alertDialog.setMessage("If you have two of the same valued card in your first hand you can split your hand in two");

                alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });
                alertDialog.show();  //<-- See This!

            }
        });


        Button btn_hit = (Button) findViewById(R.id.btn_hit);
        btn_hit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                AlertDialog alertDialog1 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog1.setTitle("Hit");
                alertDialog1.setMessage("Tap the hit button if you want to add another card to your hand");

                alertDialog1.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });

                alertDialog1.show();  //<-- See This!

            }
        });

        Button btn_stand = (Button) findViewById(R.id.btn_stand);
        btn_stand.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Stand");
                alertDialog2.setMessage("When you're satisfied with the value of your hand tap stand to reveal the dealer's hand");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });

                alertDialog2.show();  //<-- See This!


            }
        });
    }
}














