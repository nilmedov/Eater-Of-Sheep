package com.nilmedov.eaterofsheep.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.nilmedov.eaterofsheep.R;
import com.nilmedov.eaterofsheep.views.GameView;
import com.nilmedov.eaterofsheep.navigation.JoystickView;

public class MainActivity extends Activity {
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mGameView =(GameView) findViewById(R.id.gameview);
        mGameView.setJoystick((JoystickView) findViewById(R.id.joystick));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameView.onResume();
    }
}