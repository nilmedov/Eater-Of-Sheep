package com.nilmedov.eaterofsheep.threads;

import android.graphics.Canvas;

import com.nilmedov.eaterofsheep.views.GameView;

public class GameLoopThread extends Thread {
    private static final long FPS = 10;
    private boolean isRunning = false;
    private GameView mGameView;

    public GameLoopThread(GameView GameView) {
        this.mGameView = GameView;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (isRunning) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();

            try {
                canvas = mGameView.getHolder().lockCanvas();
                synchronized (mGameView.getHolder()) {
                    mGameView.doDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    mGameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }

            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            } catch (Exception e) {

            }
        }
    }
}
