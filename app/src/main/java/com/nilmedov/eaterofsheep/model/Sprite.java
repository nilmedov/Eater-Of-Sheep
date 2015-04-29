package com.nilmedov.eaterofsheep.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.nilmedov.eaterofsheep.utils.Const;
import com.nilmedov.eaterofsheep.views.GameView;

import java.util.Random;

public class Sprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;

    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 up, 1 left, 0 down, 2 right
    private static final int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private int mapIndex = 3;

    private GameView gameView;

    private Bitmap picture;

    private int x = 0;
    private int y = 0;

    int direction;

    private int speed;
    private int diagonalSpeed;

    private int currentFrame = 0;

    private int width;
    private int height;

    public Sprite(GameView gameView, Bitmap picture) {
        this.gameView = gameView;
        this.picture = picture;
        this.width = picture.getWidth() / BMP_COLUMNS;
        this.height = picture.getHeight() / BMP_ROWS;

        setSpeed(10);
    }

    public void setMovementDirection(int direction) {
        this.direction = direction;

        switch (direction) {
            case Const.Navigation.BOTTOM:
                if(y <= gameView.getHeight() - height - speed) {
                    y = y + speed;
                }
                break;
            case Const.Navigation.TOP:
                if(y - speed >= 0) {
                    y = y - speed;
                }
                break;
            case Const.Navigation.LEFT:
                if(x <= gameView.getWidth() - height - speed) {
                    x = x + speed;
                }
                break;
            case Const.Navigation.RIGHT:
                if(x - speed >= 0) {
                    x = x - speed;
                }
                break;
            case Const.Navigation.BOTTOM_LEFT:
                if(y <= gameView.getHeight() - height - speed && x <= gameView.getWidth() - height - speed) {
                    y = y + diagonalSpeed;
                    x = x + diagonalSpeed;
                }
                break;
            case Const.Navigation.RIGHT_BOTTOM:
                if(y <= gameView.getHeight() - height - speed && x - speed >= 0) {
                    y = y + diagonalSpeed;
                    x = x - diagonalSpeed;
                }
                break;
            case Const.Navigation.LEFT_TOP:
                if(y - speed >= 0 && x <= gameView.getWidth() - height - speed) {
                    y = y - diagonalSpeed;
                    x = x + diagonalSpeed;
                }
                break;
            case Const.Navigation.TOP_RIGHT:
                if(y - speed >= 0 && x - speed >= 0) {
                    y = y - diagonalSpeed;
                    x = x - diagonalSpeed;
                }
                break;
        }
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        diagonalSpeed = (int) Math.round(speed * 0.586);
    }

    public void onDraw(Canvas canvas) {
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(picture, src, dst, null);
    }

    private int getAnimationRow() {
        if(direction == Const.Navigation.BOTTOM) {
            mapIndex = 2;
        } else if(direction == Const.Navigation.TOP) {
            mapIndex = 0;
        } else if(direction == Const.Navigation.RIGHT || direction == Const.Navigation.RIGHT_BOTTOM
                || direction == Const.Navigation.TOP_RIGHT) {
            mapIndex = 1;
        } else if(direction == Const.Navigation.LEFT || direction == Const.Navigation.BOTTOM_LEFT
                || direction == Const.Navigation.LEFT_TOP) {
            mapIndex = 3;
        }
        return DIRECTION_TO_ANIMATION_MAP[mapIndex];
    }
}
