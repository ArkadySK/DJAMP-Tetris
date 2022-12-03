import fri.shapesge.Manager;

import java.util.ArrayList;
import java.util.Random;

public class Tetris {
    private final String[] colors = new String[]{"red", "blue", "green"};
    private Shape currentShape;
    private final Manager manager;
    private final ArrayList<Shape> shapes;
    
    public static final int SCREEN_WIDTH = 10;
    public static final int SCREEN_HEIGHT = 20;

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

        for (int line = 0; line < Tetris.SCREEN_HEIGHT; line++) {
            if(this.isLineFull(line))   {
                this.clearLine(line);
            }
        }

        this.currentShape.moveDown();
    }

    private void clearLine(int line) {
        for (Shape shape : shapes) {
            ArrayList<Block> parts = shape.getParts();
            ArrayList<Block> partsToRemove = new ArrayList<Block>();
            
            for (Block block : parts) {
                if(block.getY() == line)
                    partsToRemove.add(block);
            }

            for (Block block : partsToRemove) {
                parts.remove(block);
                block.hide();
            }
        }
    }


    private boolean isLineFull(int line)    {
        boolean isFull = true;
            for (int i = 0; i < Tetris.SCREEN_WIDTH; i++)  
                if(!this.isPositionFilled(i, line))
                    isFull = false;
        return isFull;
    }

    private boolean isPositionFilled(int x, int y)   {
        for (Shape shape : shapes) {
            ArrayList<Block> parts = shape.getParts();
            for (Block block : parts) {
                if(block.getX() == x && block.getY() == y)
                    return true;
            }
        }
        return false;
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