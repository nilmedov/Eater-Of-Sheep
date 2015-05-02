package com.nilmedov.eaterofsheep.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.nilmedov.eaterofsheep.views.GameView;

public abstract class BaseObject {

    protected GameView gameView;

    protected Bitmap picture;

    private int x;
    private int y;

    private int width;
    private int height;

    public BaseObject(GameView gameView, Bitmap picture, int x, int y) {
        this.gameView = gameView;
        this.picture = picture;
        this.x = x;
        this.y = y;
        this.width = picture.getWidth();
        this.height = picture.getHeight();
    }
    
    public abstract void onDraw(Canvas canvas);

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
