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

    public void moveDown() {
        this.y++;
        for (Block part : parts) {
            part.moveDown();
        }
    }

    public void moveLeft() {
        this.x--;
        for (Block part : parts) {
            part.moveLeft();
        }
    }

    public void moveRight() {
        this.x++;
        for (Block part : parts) {
            part.moveRight();
        }
    }

    public boolean collideWithGround() {
        for (Block current : this.parts) {
            if (current.getY() + 1 >= 20) {
                return true;
            }
        }
        return false;
    }

    public CollisionSide collideWithSide() {
        for (Block current : this.parts) {
            if (current.getX() - 1 < 0) {
                return CollisionSide.LEFT;
            }
            if (current.getX() + 1 >= 10) {
                return CollisionSide.RIGHT;
            }
        }
        return CollisionSide.NONE;
    }

    public CollisionSide collideWith(Shape other) {
        CollisionSide side = CollisionSide.NONE;
        for (Block current : this.parts) {
            for (Block square : other.parts) {
                if (square.getX() == current.getX() && square.getY() == current.getY() + 1) {
                    side = CollisionSide.DOWN;
                }
                if (square.getX() == current.getX() + 1 && square.getY() == current.getY()) {
                    side = CollisionSide.RIGHT;
                }
                if (square.getX() == current.getX() - 1 && square.getY() == current.getY()) {
                    side =  CollisionSide.LEFT;
                }
            }
        }
        return side;
    }
}