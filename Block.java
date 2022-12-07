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
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.square.moveHorizontal(-this.x * BLOCK_SIZE + x * BLOCK_SIZE);
        this.x = x;
    }

    public void setY(int y) {
        this.square.moveVertical(-this.y * BLOCK_SIZE + y * BLOCK_SIZE);
        this.y = y;
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