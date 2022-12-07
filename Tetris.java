import fri.shapesge.FontStyle;
import fri.shapesge.Manager;
import fri.shapesge.Text;

import java.util.ArrayList;
import java.util.Random;

public class Tetris {
    private final String[] colors = new String[]{"red", "blue", "green"};
    private Shape currentShape;
    private final Manager manager;
    private final ArrayList<Shape> shapes;
    
    private GameUI gameUI;
    private boolean isGameOver;
    private int gameDuration;

    public static final int SCREEN_WIDTH = 10;
    public static final int SCREEN_HEIGHT = 20;

    public Tetris() {
        this.isGameOver = false;
        this.shapes = new ArrayList<>();
        this.manager = new Manager();
        this.currentShape = new Shape(ShapeType.T, Tetris.SCREEN_WIDTH / 2 - 1, 1, this.colors[new Random().nextInt(this.colors.length)]);
        manager.manageObject(this);
        this.gameUI = new GameUI();
    }

    public void tick() {
        if(this.isGameOver)
            return;
        this.moveDown();
    }

    public void secondTick() {
        this.gameDuration++;
    }

    public void moveDown() {
        if(this.isGameOver)
            return;
        
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
                this.moveLineDownFromIndex(line - 1);
            }
        }

        this.currentShape.moveDown();
    }

    public void moveLeft() {
        if(this.isGameOver)
            return;
        
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
        if(this.isGameOver)
            return;
        
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
        // pridaj aktualny tvar do zoznamu tvarov a pripocitaj skore
        this.gameUI.setScore(this.gameUI.getScore() + 1);
        this.shapes.add(this.currentShape);
        
        var shapeToSpawn = new Shape(ShapeType.values()[new Random().nextInt(7)], Tetris.SCREEN_WIDTH / 2 - 1, 1, this.colors[new Random().nextInt(this.colors.length)]);
        this.currentShape = shapeToSpawn;

        for (Block block : shapeToSpawn.getParts()) {
            if (this.isPositionFilled(block.getX(), block.getY()))  {
                this.endGame();
            }
        }
    }

    public static void main(String[] args) {
        new Tetris();
    }

    private void endGame() {
        this.isGameOver = true;
        
        // Skry vsetko
        for (Shape shape : this.shapes) {
            shape.hide();
        }
        this.currentShape.hide();
        this.gameUI.clear();

        // UI po konci hry
        Text headerText = new Text("Koniec hry!", 60, 50);
        headerText.changeFont("Arial", FontStyle.BOLD, 32);
        headerText.makeVisible();
        Text text = new Text("Vaše skóre: " + this.gameUI.getScore(), 20, 100);
        text.changeFont("Arial", FontStyle.PLAIN, 22);
        text.makeVisible();
        String totalTime = String.format("%d:%d", this.gameDuration / 60, this.gameDuration % 60);
        Text text2 = new Text("Trvanie hry: " + totalTime, 20, 140);
        text2.changeFont("Arial", FontStyle.PLAIN, 22);
        text2.makeVisible();
    }

    private void moveLineDownFromIndex(int firstLineToMove)  {
        for (Shape shape : this.shapes) {
            ArrayList<Block> parts = shape.getParts();

            for (Block block : parts) {
                if (block.getY() <= firstLineToMove)
                    block.moveDown();
            }
        }
    }

    private void clearLine(int line) {
        for (Shape shape : this.shapes) {
            ArrayList<Block> parts = shape.getParts();
            ArrayList<Block> partsToRemove = new ArrayList<Block>();
            
            for (Block block : parts) {
                if (block.getY() == line)
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
}