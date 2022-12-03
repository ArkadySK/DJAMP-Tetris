import fri.shapesge.Square;

public class Block {
    private static final int BLOCK_SIZE = 30;
    private int x;
    private int y;

    private final Square square;

    public Block(int x, int y, String color) {
        this.x = x;
        this.y = y;

        this.square = new Square(x * BLOCK_SIZE, y * BLOCK_SIZE);
        this.square.changeSize(BLOCK_SIZE);
        this.square.changeColor(color);
        this.square.makeVisible();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        this.y++;
        this.square.moveVertical(BLOCK_SIZE);
    }

    public void moveRight() {
        this.x++;
        this.square.moveHorizontal(BLOCK_SIZE);
    }

    public void moveLeft() {
        this.x--;
        this.square.moveHorizontal(-BLOCK_SIZE);
    }

    public void hide() {
        this.square.makeInvisible();
    }
}