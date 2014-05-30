package com.grandpad.blackjack.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


public class InstructionActivity extends FirstActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howto_screen);
        ImageView arrow = (ImageView) findViewById(R.id.arrow);

        arrow=(ImageView)findViewById(R.id.arrow);
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(300);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        arrow.startAnimation(mAnimation);
        arrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.clearAnimation();


            }
        });

    }
}








