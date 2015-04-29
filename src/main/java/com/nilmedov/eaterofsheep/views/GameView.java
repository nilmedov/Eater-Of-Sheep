package com.nilmedov.eaterofsheep.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nilmedov.eaterofsheep.R;
import com.nilmedov.eaterofsheep.model.Sprite;
import com.nilmedov.eaterofsheep.threads.GameLoopThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoopThread mGameLoopThread;
    private Sprite mSprite;
    private JoystickView mJoystick;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGameLoopThread = new GameLoopThread(this);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSprite = new Sprite(this, BitmapFactory.decodeResource(getResources(), R.mipmap.sprite));
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

            }
        }
    }

    public void doDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        mSprite.onDraw(canvas);
    }

    public void setJoystick(JoystickView joystick) {
        mJoystick = joystick;
        mJoystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction) {
                mSprite.setMovementDirection(direction);
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }
}
