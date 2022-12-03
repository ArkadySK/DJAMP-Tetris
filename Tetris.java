import fri.shapesge.Manager;

import java.util.Random;

public class Tetris {
    private final String[] colors = new String[]{"red", "blue", "green"};
    private Shape currentShape;
    private final Manager manager;

    public Tetris() {
        this.manager = new Manager();
        this.currentShape = new Shape(ShapeType.T, 2, 2, this.colors[new Random().nextInt(this.colors.length)]);
        manager.manageObject(this);
    }

    public void tick() {
        this.moveDown();
    }

    public void moveDown() {
        this.currentShape.moveDown();
    }

    public void moveLeft() {
        this.currentShape.moveLeft();
    }

    public void moveRight() {
        this.currentShape.moveRight();
    }

    public static void main(String[] args) {
        new Tetris();
    }
}