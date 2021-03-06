package com.nilmedov.eaterofsheep.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nilmedov.eaterofsheep.navigation.JoystickView;
import com.nilmedov.eaterofsheep.navigation.PlayerNavigationController;
import com.nilmedov.eaterofsheep.R;
import com.nilmedov.eaterofsheep.navigation.SheepNavigationController;
import com.nilmedov.eaterofsheep.objects.Map;
import com.nilmedov.eaterofsheep.objects.Sheep;
import com.nilmedov.eaterofsheep.objects.Sprite;
import com.nilmedov.eaterofsheep.threads.GameLoopThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String LOG_TAG = GameView.class.getSimpleName();

    private GameLoopThread mGameLoopThread;

    private PlayerNavigationController mPlayerNavigationController;
    private SheepNavigationController mSheepNavigationController;

    private Map mMap;
    private Sprite mSprite;
    private Sheep mSheep;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGameLoopThread = new GameLoopThread(this);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMap = new Map(BitmapFactory.decodeResource(getResources(), R.mipmap.background));
        mSprite = new Sprite(this,
                BitmapFactory.decodeResource(getResources(), R.mipmap.sprite),
                0,
                0,
                10,
                JoystickView.RIGHT
        );
        mSheep = new Sheep(this,
                BitmapFactory.decodeResource(getResources(), R.mipmap.sprite2),
                100,
                100,
                10,
                JoystickView.BOTTOM_RIGHT
        );

        mPlayerNavigationController = new PlayerNavigationController(this, mSprite, mMap);
        mSheepNavigationController = new SheepNavigationController(this, mSheep, mMap);

        mGameLoopThread.setRunning(true);
        mGameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mGameLoopThread.setPause(false);
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
        mSheepNavigationController.move();

        mMap.onDraw(canvas);
        mSprite.onDraw(canvas);
        mSheep.onDraw(canvas);
    }

    public void onPause() {
        mGameLoopThread.setPause(true);
    }

    public void onResume() {
        mGameLoopThread.setPause(false);
    }

    public void setJoystick(JoystickView joystick) {
        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction) {
                mSprite.setDirection(direction);
                mPlayerNavigationController.move();
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }
}
