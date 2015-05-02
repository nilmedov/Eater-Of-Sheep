package com.nilmedov.eaterofsheep.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.nilmedov.eaterofsheep.views.GameView;

/**
 * NOT READY YET
 */

public class Sheep extends BaseActiveObject{

    public Sheep(GameView gameView, Bitmap picture, int x, int y, int speed, int direction) {
        super(gameView, picture, x, y, speed, direction);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int srcX = currentFrame * getWidth();
        int srcY = getAnimationRow() *  getHeight();
        Rect src = new Rect(srcX, srcY, srcX + getWidth(), srcY + getHeight());
        Rect dst = new Rect(getX(), getY(), getX() + getWidth(), getY() + getHeight());

        canvas.drawBitmap(picture, src, dst, null);
    }
}
