package com.nilmedov.eaterofsheep.navigation;

import com.nilmedov.eaterofsheep.objects.Map;
import com.nilmedov.eaterofsheep.objects.Sprite;
import com.nilmedov.eaterofsheep.views.GameView;

public class PlayerNavigationController extends BaseNavigationController{

    private Sprite sprite;

    private int widthDifference;
    private int heightDifference;

    public PlayerNavigationController(GameView gameView, Sprite sprite, Map map) {
        super(gameView, map);
        this.sprite = sprite;
        widthDifference = map.getWidth() - gameView.getWidth();
        heightDifference = map.getHeight() - gameView.getHeight();
    }

    @Override
    public void move() {
        switch (sprite.getDirection()) {
            case JoystickView.BOTTOM:
                if(sprite.getY() <= gameView.getHeight() - sprite.getHeight() - sprite.getSpeed()) {

                    if(Math.abs(map.getY()) <= heightDifference - sprite.getSpeed()) {
                        map.setY(map.getY() - sprite.getSpeed());
                    } else {
                        moveBottom(sprite);
                    }
                }
                break;
            case JoystickView.TOP:
                if(sprite.getY() - sprite.getSpeed() >= 0) {

                    if(map.getY() + sprite.getSpeed() <= 0) {
                        map.setY(map.getY() + sprite.getSpeed());
                    } else {
                        moveTop(sprite);
                    }
                }
                break;
            case JoystickView.LEFT:
                if(sprite.getX() - sprite.getSpeed() >= 0) {

                    if(map.getX() + sprite.getSpeed() <= 0) {
                        map.setX(map.getX() + sprite.getSpeed());
                    } else {
                        moveLeft(sprite);
                    }
                }
                break;
            case JoystickView.RIGHT:
                if(sprite.getX() <= gameView.getWidth() - sprite.getWidth() - sprite.getSpeed()) {

                    if(Math.abs(map.getX()) <= widthDifference - sprite.getSpeed()) {
                        map.setX(map.getX() - sprite.getSpeed());
                    } else {
                        moveRight(sprite);
                    }
                }
                break;
            case JoystickView.BOTTOM_LEFT:
                if(sprite.getY() <= gameView.getHeight() - sprite.getHeight() - sprite.getDiagonalSpeed()
                        && sprite.getX() - sprite.getDiagonalSpeed() >= 0) {

                    if(Math.abs(map.getY()) <= heightDifference - sprite.getDiagonalSpeed()
                            && map.getX() + sprite.getDiagonalSpeed() <= 0) {
                        map.setY(map.getY() - sprite.getDiagonalSpeed());
                        map.setX(map.getX() + sprite.getDiagonalSpeed());
                    } else {
                        moveBottomLeft(sprite);
                    }
                }
                break;
            case JoystickView.BOTTOM_RIGHT:
                if(sprite.getY() <= gameView.getHeight() - sprite.getHeight() - sprite.getDiagonalSpeed()
                        && sprite.getX() <= gameView.getWidth() - sprite.getWidth() - sprite.getDiagonalSpeed()) {

                    if(Math.abs(map.getY()) <= heightDifference - sprite.getDiagonalSpeed()
                            && Math.abs(map.getX()) <= widthDifference - sprite.getDiagonalSpeed()) {
                        map.setY(map.getY() - sprite.getDiagonalSpeed());
                        map.setX(map.getX() - sprite.getDiagonalSpeed());
                    } else {
                        moveBottomRight(sprite);
                    }
                }
                break;
            case JoystickView.TOP_LEFT:
                if(sprite.getY() - sprite.getDiagonalSpeed() >= 0 && sprite.getX() - sprite.getDiagonalSpeed() >= 0) {

                    if(map.getY() + sprite.getDiagonalSpeed() <= 0 && map.getX() + sprite.getDiagonalSpeed() <= 0) {
                        map.setY(map.getY() + sprite.getDiagonalSpeed());
                        map.setX(map.getX() + sprite.getDiagonalSpeed());
                    } else {
                        moveTopLeft(sprite);
                    }
                }
                break;
            case JoystickView.TOP_RIGHT:
                if(sprite.getY() - sprite.getDiagonalSpeed() >= 0
                        && sprite.getX() <= gameView.getWidth() - sprite.getWidth() - sprite.getDiagonalSpeed()) {

                    if(map.getY() + sprite.getDiagonalSpeed() <= 0
                            && Math.abs(map.getX()) <= widthDifference - sprite.getDiagonalSpeed()) {
                        map.setY(map.getY() + sprite.getDiagonalSpeed());
                        map.setX(map.getX() - sprite.getDiagonalSpeed());
                    } else {
                        moveTopRight(sprite);
                    }
                }
                break;
        }
        sprite.nextAnimation();

//        Log.d("GameView:", gameView.getWidth() + " " + gameView.getHeight());
//        Log.d("Player:", sprite.getX() + " " + sprite.getY());
//        Log.d("Map:", map.getX() + " " + map.getY());
    }
}
