package com.nilmedov.eaterofsheep.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Map {

    private Bitmap mBackground;

    private int x = 0;
    private int y = 0;

    public Map(Bitmap background) {
        mBackground = background;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackground, x, y, null);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return mBackground.getWidth();
    }

    public int getHeight() {
        return mBackground.getHeight();
    }
}
