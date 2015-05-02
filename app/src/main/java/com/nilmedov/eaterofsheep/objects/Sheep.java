package com.nilmedov.eaterofsheep.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.nilmedov.eaterofsheep.views.GameView;

/**
 * NOT READY YET
 */

public class Sheep extends BaseActiveObject{

    public Sheep(GameView gameView, Bitmap picture, int x, int y, int speed) {
        super(gameView, picture, x, y, speed);
    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}
