import fri.shapesge.Manager;

import java.util.ArrayList;
import java.util.Random;

public class Tetris {
    private final String[] colors = new String[]{"red", "blue", "green"};
    private Shape currentShape;
    private final Manager manager;
    private final ArrayList<Shape> shapes;

    public Tetris() {
        this.shapes = new ArrayList<>();
        this.manager = new Manager();
        this.currentShape = new Shape(ShapeType.T, 2, 2, this.colors[new Random().nextInt(this.colors.length)]);
        manager.manageObject(this);
    }

    public void tick() {
        this.moveDown();
    }

    public void moveDown() {
        if (currentShape.collideWithGround()) {
            this.addNewShape();
            return;
        }

        for (Shape other : this.shapes) {
            if (currentShape.collideWith(other) == CollisionSide.DOWN) {
                this.addNewShape();
                return;
            }
        }

        this.currentShape.moveDown();
    }

    public void moveLeft() {
        if (currentShape.collideWithSide() == CollisionSide.LEFT) {
            return;
        }
        for (Shape other : this.shapes) {
            if (currentShape.collideWith(other) == CollisionSide.LEFT) {
                return;
            }
        }
        this.currentShape.moveLeft();
    }

    public void moveRight() {
        if (currentShape.collideWithSide() == CollisionSide.RIGHT) {
            return;
        }
        for (Shape other : this.shapes) {
            if (currentShape.collideWith(other) == CollisionSide.RIGHT) {
                return;
            }
        }
        this.currentShape.moveRight();
    }

    private void addNewShape() {
        this.shapes.add(this.currentShape);
        this.currentShape = new Shape(ShapeType.values()[new Random().nextInt(7)], 2, 2, this.colors[new Random().nextInt(this.colors.length)]);
    }

    public static void main(String[] args) {
        new Tetris();
    }
}