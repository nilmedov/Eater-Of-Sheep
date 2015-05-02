package com.nilmedov.eaterofsheep.navigation;

import com.nilmedov.eaterofsheep.objects.BaseActiveObject;
import com.nilmedov.eaterofsheep.objects.Map;
import com.nilmedov.eaterofsheep.views.GameView;

public abstract class BaseNavigationController {

    protected GameView gameView;
    protected Map map;

    public BaseNavigationController(GameView gameView, Map map) {
        this.gameView = gameView;
        this.map = map;
    }

    public abstract void move();

    protected void moveBottom(BaseActiveObject object) {
        object.setY(object.getY() + object.getSpeed());
    }

    protected void moveTop(BaseActiveObject object) {
        object.setY(object.getY() - object.getSpeed());
    }

    protected void moveLeft(BaseActiveObject object) {
        object.setX(object.getX() - object.getSpeed());
    }

    protected void moveRight(BaseActiveObject object) {
        object.setX(object.getX() + object.getSpeed());
    }

    protected void moveBottomLeft(BaseActiveObject object) {
        object.setY(object.getY() + object.getDiagonalSpeed());
        object.setX(object.getX() - object.getDiagonalSpeed());
    }

    protected void moveBottomRight(BaseActiveObject object) {
        object.setY(object.getY() + object.getDiagonalSpeed());
        object.setX(object.getX() + object.getDiagonalSpeed());
    }

    protected void moveTopLeft(BaseActiveObject object) {
        object.setY(object.getY() - object.getDiagonalSpeed());
        object.setX(object.getX() - object.getDiagonalSpeed());
    }

    protected void moveTopRight(BaseActiveObject object) {
        object.setY(object.getY() - object.getDiagonalSpeed());
        object.setX(object.getX() + object.getDiagonalSpeed());
    }
}
