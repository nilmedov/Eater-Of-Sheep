package com.nilmedov.eaterofsheep.objects;

import android.graphics.Bitmap;

import com.nilmedov.eaterofsheep.navigation.JoystickView;
import com.nilmedov.eaterofsheep.views.GameView;

public abstract class BaseActiveObject extends BaseObject{

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;

    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 up, 1 left, 0 down, 2 right
    private static final int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    protected int mapIndex;

    private int direction;

    private int speed;
    private int diagonalSpeed;

    protected int currentFrame = 0;

    public BaseActiveObject(GameView gameView, Bitmap picture, int x, int y, int speed, int direction) {
        super(gameView, picture, x, y);
        this.direction = direction;

        setWidth(getWidth() / BMP_COLUMNS);
        setHeight(getHeight() / BMP_ROWS);
        setSpeed(speed);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        diagonalSpeed = (int) Math.round(speed * 0.586);
    }

    public int getDiagonalSpeed() {
        return diagonalSpeed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void nextAnimation() {
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    protected int getAnimationRow() {
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
