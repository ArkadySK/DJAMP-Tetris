public class Shape {
    private String color;
    private ShapeType type;
    private int x;
    private int y;
    private final Block[] parts;

    public Shape(ShapeType type, int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.parts = new Block[4];
        for (int i = 0; i < 4; i++) {
            this.parts[i] = new Block(type.getPositions()[i][0] + x, type.getPositions()[i][1] + y, color);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}