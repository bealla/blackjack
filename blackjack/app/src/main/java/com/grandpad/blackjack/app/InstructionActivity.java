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
import android.os.Handler;


public class InstructionActivity extends FirstActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howto_screen);
    }
      /*  ImageView arrow = (ImageView) findViewById(R.id.arrow);
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
        arrow3.startAnimation(mAnimation);*/


        // Button btn_double = (Button) findViewById(R.id.btn_double);
         //btn_double.setOnClickListener(new View.OnClickListener() {
        //public void onClick(View view)


            public void howtoDouble () {
                new com.grandpad.blackjack.app.AlertDialog("Before your first hit you can double your bet and receive one more card to complete your hand", "Double Down", R.drawable.alert, false, this).show();

            }


    public void howtoHit() {
        new com.grandpad.blackjack.app.AlertDialog("Tap the hit button if you want to add another card to your hand", "Hit", R.drawable.alert, false, this).show();

    }

    public void howtoStand() {
        new com.grandpad.blackjack.app.AlertDialog("Tap Stand to complete your round and reveal the dealer's hand", "Stand", R.drawable.alert, false, this).show();

    }

    public void howtoBet() {
        new com.grandpad.blackjack.app.AlertDialog("Add and subtract from the amount you want to bet and tap submit bet when you are happy with your choice", "Bet", R.drawable.alert, false, this).show();
    }


    public void howtoDealersHand() {
        new com.grandpad.blackjack.app.AlertDialog("This is the dealer's hand", "Dealer's Hand", R.drawable.alert, false, this).show();
    }

    public void howtoYourHand() {
        new com.grandpad.blackjack.app.AlertDialog("This is your hand", "Your Hand", R.drawable.alert, false, this).show();
    }

    public void howtoSideBar() {
        new com.grandpad.blackjack.app.AlertDialog("These are each of your playing options;Tap each one to learn when to use them", "Play Options", R.drawable.alert, false, this).show();
    }


    //AlertDialog alertDialog = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
    //alertDialog.setTitle("Double Down");
    //alertDialog.setMessage("Before your first hit you can double your bet and receive one more card to complete your hand");

    //alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
    //  public void onClick(DialogInterface dialog, int which) {
    //
    //}
    // });

    //alertDialog.show();
    // }
    //});
/*        Button btn_split = (Button) findViewById(R.id.btn_split);
        btn_split.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                AlertDialog alertDialog = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog.setTitle("Split");
                alertDialog.setMessage("If you have two of the same valued card in your first hand you can split your hand in two");

                alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                alertDialog.show();

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
                        //
                    }
                });

                alertDialog1.show();

            }
        });

        Button btn_stand = (Button) findViewById(R.id.btn_stand);
        btn_stand.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Stand");
                alertDialog2.setMessage("Tap Stand to complete your round and reveal the dealer's hand");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                alertDialog2.show();


            }
        });

        Button btn_bet = (Button) findViewById(R.id.btn_bet);
        btn_bet.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Bet");
                alertDialog2.setMessage("Submit the amount of money you want to bet ");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                alertDialog2.show();


            }
        });

        Button btn_cancel_bet = (Button) findViewById(R.id.btn_cancel_bet);
        btn_cancel_bet.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Cancel Bet");
                alertDialog2.setMessage("Cancel the current amount of money you want to bet ");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                alertDialog2.show();


            }
        });

        arrow.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Dealer's Hand");
                alertDialog2.setMessage("This is the dealer's hand and game deck ");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                alertDialog2.show();


            }
        });

        arrow2.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Your hand");
                alertDialog2.setMessage("This is your hand ");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                alertDialog2.show();


            }
        });

        arrow3.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {


                AlertDialog alertDialog2 = new AlertDialog.Builder(InstructionActivity.this).create(); //Read Update
                alertDialog2.setTitle("Play Options");
                alertDialog2.setMessage("These are each of your playing options;Tap each one to learn when to use them");

                alertDialog2.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       //
                    }
                });

                alertDialog2.show();


            }
        });*/
}
















