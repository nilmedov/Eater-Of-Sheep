package com.nilmedov.eaterofsheep.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.nilmedov.eaterofsheep.navigation.JoystickView;
import com.nilmedov.eaterofsheep.views.GameView;

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

        x = gameView.getWidth() / 2;
        y = gameView.getHeight() / 2;
        setSpeed(10);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiagonalSpeed() {
        return diagonalSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        diagonalSpeed = (int) Math.round(speed * 0.586);
    }

    public void nextAnimation(int direction) {
        this.direction = direction;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(picture, src, dst, null);
    }

    private int getAnimationRow() {
        if(direction == JoystickView.BOTTOM) {
            mapIndex = 2;
        } else if(direction == JoystickView.TOP) {
            mapIndex = 0;
        } else if(direction == JoystickView.RIGHT || direction == JoystickView.BOTTOM_RIGHT
                || direction == JoystickView.TOP_RIGHT) {
            mapIndex = 3;
        } else if(direction == JoystickView.LEFT || direction == JoystickView.BOTTOM_LEFT
                || direction == JoystickView.TOP_LEFT) {
            mapIndex = 1;
        }
        return DIRECTION_TO_ANIMATION_MAP[mapIndex];
    }
}
