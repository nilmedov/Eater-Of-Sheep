package com.nilmedov.eaterofsheep.navigation;


import com.nilmedov.eaterofsheep.objects.Map;
import com.nilmedov.eaterofsheep.objects.Sheep;
import com.nilmedov.eaterofsheep.views.GameView;

public class SheepNavigationController extends BaseNavigationController {

    private Sheep sheep;

    public SheepNavigationController(GameView gameView, Sheep sheep, Map map) {
        super(gameView, map);
        this.sheep = sheep;
    }

    @Override
    public void move() {
        switch (sheep.getDirection()) {
            case JoystickView.BOTTOM:
                if (sheep.getY() <= gameView.getHeight() - sheep.getHeight() - sheep.getSpeed()) {
                    moveBottom(sheep);
                } else {
                    sheep.setDirection(JoystickView.TOP);
                    moveTop(sheep);
                }
                break;
            case JoystickView.TOP:
                if (sheep.getY() - sheep.getSpeed() >= 0) {
                    moveTop(sheep);
                } else {
                    sheep.setDirection(JoystickView.BOTTOM);
                    moveBottom(sheep);
                }
                break;
            case JoystickView.LEFT:
                if (sheep.getX() - sheep.getSpeed() >= 0) {
                    moveLeft(sheep);
                } else {
                    sheep.setDirection(JoystickView.RIGHT);
                    moveRight(sheep);
                }
                break;
            case JoystickView.RIGHT:
                if (sheep.getX() <= gameView.getWidth() - sheep.getWidth() - sheep.getSpeed()) {
                    moveRight(sheep);
                } else {
                    sheep.setDirection(JoystickView.LEFT);
                    moveLeft(sheep);
                }
                break;
            case JoystickView.BOTTOM_LEFT:
                if (sheep.getY() <= gameView.getHeight() - sheep.getHeight() - sheep.getDiagonalSpeed()
                        && sheep.getX() - sheep.getDiagonalSpeed() >= 0) {
                    moveBottomLeft(sheep);
                } else if(!(sheep.getX() - sheep.getDiagonalSpeed() >= 0)) {
                    sheep.setDirection(JoystickView.BOTTOM_RIGHT);
                    moveBottomRight(sheep);
                } else {
                    sheep.setDirection(JoystickView.TOP_LEFT);
                    moveTopLeft(sheep);
                }
                break;
            case JoystickView.BOTTOM_RIGHT:
                if (sheep.getY() <= gameView.getHeight() - sheep.getHeight() - sheep.getDiagonalSpeed()
                        && sheep.getX() <= gameView.getWidth() - sheep.getWidth() - sheep.getDiagonalSpeed()) {
                    moveBottomRight(sheep);
                } else if(!(sheep.getX() <= gameView.getWidth() - sheep.getWidth() - sheep.getDiagonalSpeed())) {
                    sheep.setDirection(JoystickView.BOTTOM_LEFT);
                    moveBottomLeft(sheep);
                } else {
                    sheep.setDirection(JoystickView.TOP_RIGHT);
                    moveTopRight(sheep);
                }
                break;
            case JoystickView.TOP_LEFT:
                if (sheep.getY() - sheep.getDiagonalSpeed() >= 0 && sheep.getX() - sheep.getDiagonalSpeed() >= 0) {
                    moveTopLeft(sheep);
                } else if(!(sheep.getX() - sheep.getDiagonalSpeed() >= 0)) {
                    sheep.setDirection(JoystickView.TOP_RIGHT);
                    moveTopRight(sheep);
                } else {
                    sheep.setDirection(JoystickView.BOTTOM_LEFT);
                    moveBottomLeft(sheep);
                }
                break;
            case JoystickView.TOP_RIGHT:
                if (sheep.getY() - sheep.getDiagonalSpeed() >= 0
                        && sheep.getX() <= gameView.getWidth() - sheep.getWidth() - sheep.getDiagonalSpeed()) {
                    moveTopRight(sheep);
                } else if(!(sheep.getX() <= gameView.getWidth() - sheep.getWidth() - sheep.getDiagonalSpeed())) {
                    sheep.setDirection(JoystickView.TOP_LEFT);
                    moveTopLeft(sheep);
                } else {
                    sheep.setDirection(JoystickView.BOTTOM_RIGHT);
                    moveBottomRight(sheep);
                }
                break;
        }
        sheep.nextAnimation();
    }
}
