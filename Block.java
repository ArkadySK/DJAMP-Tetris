import fri.shapesge.Square;

public class Block {
    private static final int BLOCK_SIZE = 30;
    private String color;
    private int x;
    private int y;

    private final Square square;

    public Block(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}