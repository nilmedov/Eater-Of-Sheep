package com.nilmedov.eaterofsheep.navigation;

import android.util.Log;

import com.nilmedov.eaterofsheep.model.Map;
import com.nilmedov.eaterofsheep.model.Sprite;
import com.nilmedov.eaterofsheep.views.GameView;

public class NavigationController {

    private GameView mGameView;
    private Sprite mSprite;
    private Map mMap;

    private int widthDifference;
    private int heightDifference;

    public NavigationController(GameView gameView, Sprite sprite, Map map) {
        mGameView = gameView;
        mSprite = sprite;
        mMap = map;

        widthDifference = map.getWidth() - gameView.getWidth();
        heightDifference = map.getHeight() - gameView.getHeight();
    }

    public void movePlayer(int direction) {
        switch (direction) {
            case JoystickView.BOTTOM:
                if(mSprite.getY() <= mGameView.getHeight() - mSprite.getHeight() - mSprite.getSpeed()) {

                    if(Math.abs(mMap.getY()) <= heightDifference - mSprite.getSpeed()) {
                        mMap.setY(mMap.getY() - mSprite.getSpeed());
                    }
                    mSprite.setY(mSprite.getY() + mSprite.getSpeed());
                }
                break;
            case JoystickView.TOP:
                if(mSprite.getY() - mSprite.getSpeed() >= 0) {

                    if(mMap.getY() + mSprite.getSpeed() <= 0) {
                        mMap.setY(mMap.getY() + mSprite.getSpeed());
                    }
                    mSprite.setY(mSprite.getY() - mSprite.getSpeed());
                }
                break;
            case JoystickView.LEFT:
                if(mSprite.getX() - mSprite.getSpeed() >= 0) {

                    if(mMap.getX() + mSprite.getSpeed() <= 0) {
                        mMap.setX(mMap.getX() + mSprite.getSpeed());
                    }
                    mSprite.setX(mSprite.getX() - mSprite.getSpeed());
                }
                break;
            case JoystickView.RIGHT:
                if(mSprite.getX() <= mGameView.getWidth() - mSprite.getWidth() - mSprite.getSpeed()) {

                    if(Math.abs(mMap.getX()) <= widthDifference - mSprite.getSpeed()) {
                        mMap.setX(mMap.getX() - mSprite.getSpeed());
                    }
                    mSprite.setX(mSprite.getX() + mSprite.getSpeed());
                }
                break;
            case JoystickView.BOTTOM_LEFT:
                if(mSprite.getY() <= mGameView.getHeight() - mSprite.getHeight() - mSprite.getDiagonalSpeed()
                        && mSprite.getX() - mSprite.getDiagonalSpeed() >= 0) {

                    if(Math.abs(mMap.getY()) <= heightDifference - mSprite.getDiagonalSpeed()
                            && mMap.getX() + mSprite.getDiagonalSpeed() <= 0) {
                        mMap.setY(mMap.getY() - mSprite.getDiagonalSpeed());
                        mMap.setX(mMap.getX() + mSprite.getDiagonalSpeed());
                    }
                    mSprite.setY(mSprite.getY() + mSprite.getDiagonalSpeed());
                    mSprite.setX(mSprite.getX() - mSprite.getDiagonalSpeed());
                }
                break;
            case JoystickView.BOTTOM_RIGHT:
                if(mSprite.getY() <= mGameView.getHeight() - mSprite.getHeight() - mSprite.getDiagonalSpeed()
                        && mSprite.getX() <= mGameView.getWidth() - mSprite.getWidth() - mSprite.getDiagonalSpeed()) {

                    if(Math.abs(mMap.getY()) <= heightDifference - mSprite.getDiagonalSpeed()
                            && Math.abs(mMap.getX()) <= widthDifference - mSprite.getDiagonalSpeed()) {
                        mMap.setY(mMap.getY() - mSprite.getDiagonalSpeed());
                        mMap.setX(mMap.getX() - mSprite.getDiagonalSpeed());
                    }
                    mSprite.setY(mSprite.getY() + mSprite.getDiagonalSpeed());
                    mSprite.setX(mSprite.getX() + mSprite.getDiagonalSpeed());
                }
                break;
            case JoystickView.TOP_LEFT:
                if(mSprite.getY() - mSprite.getDiagonalSpeed() >= 0 && mSprite.getX() - mSprite.getDiagonalSpeed() >= 0) {

                    if(mMap.getY() + mSprite.getDiagonalSpeed() <= 0 && mMap.getX() + mSprite.getDiagonalSpeed() <= 0) {
                        mMap.setY(mMap.getY() + mSprite.getDiagonalSpeed());
                        mMap.setX(mMap.getX() + mSprite.getDiagonalSpeed());
                    }
                    mSprite.setY(mSprite.getY() - mSprite.getDiagonalSpeed());
                    mSprite.setX(mSprite.getX() - mSprite.getDiagonalSpeed());
                }
                break;
            case JoystickView.TOP_RIGHT:
                if(mSprite.getY() - mSprite.getDiagonalSpeed() >= 0
                        && mSprite.getX() <= mGameView.getWidth() - mSprite.getWidth() - mSprite.getDiagonalSpeed()) {

                    if(mMap.getY() + mSprite.getDiagonalSpeed() <= 0
                            && Math.abs(mMap.getX()) <= widthDifference - mSprite.getDiagonalSpeed()) {
                        mMap.setY(mMap.getY() + mSprite.getDiagonalSpeed());
                        mMap.setX(mMap.getX() - mSprite.getDiagonalSpeed());
                    }
                    mSprite.setY(mSprite.getY() - mSprite.getDiagonalSpeed());
                    mSprite.setX(mSprite.getX() + mSprite.getDiagonalSpeed());
                }
                break;
        }
        mSprite.nextAnimation(direction);

//        Log.d("GameView:", mGameView.getWidth() + " " + mGameView.getHeight());
//        Log.d("Player:", mSprite.getX() + " " + mSprite.getY());
//        Log.d("Map:", mMap.getX() + " " + mMap.getY());
    }
}
