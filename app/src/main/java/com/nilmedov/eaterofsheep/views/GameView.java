package com.nilmedov.eaterofsheep.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nilmedov.eaterofsheep.navigation.JoystickView;
import com.nilmedov.eaterofsheep.navigation.NavigationController;
import com.nilmedov.eaterofsheep.R;
import com.nilmedov.eaterofsheep.model.Map;
import com.nilmedov.eaterofsheep.model.Sprite;
import com.nilmedov.eaterofsheep.threads.GameLoopThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String LOG_TAG = GameView.class.getSimpleName();

    private GameLoopThread mGameLoopThread;

    private NavigationController mNavigationController;

    private Map mMap;
    private Sprite mSprite;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGameLoopThread = new GameLoopThread(this);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMap = new Map(BitmapFactory.decodeResource(getResources(), R.mipmap.background));
        mSprite = new Sprite(this, BitmapFactory.decodeResource(getResources(), R.mipmap.sprite));
        mNavigationController = new NavigationController(this, mSprite, mMap);
        mGameLoopThread.setRunning(true);
        mGameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mGameLoopThread.setRunning(false);
        while (retry) {
            try {
                mGameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, "Interrupted Exception");
            }
        }
    }

    public void doDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        mMap.onDraw(canvas);
        mSprite.onDraw(canvas);
    }

    public void setJoystick(JoystickView joystick) {
        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction) {
                mNavigationController.movePlayer(direction);
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }
}
