package com.nilmedov.eaterofsheep.threads;

import android.graphics.Canvas;

import com.nilmedov.eaterofsheep.views.GameView;

public class GameLoopThread extends Thread {
    private static final long FPS = 10;
    private boolean running = false;
    private boolean pause = false;
    private GameView mGameView;

    public GameLoopThread(GameView GameView) {
        this.mGameView = GameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
                while (pause) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

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
                    e.printStackTrace();
                }

        }
    }
}
