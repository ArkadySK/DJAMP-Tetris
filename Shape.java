import java.util.ArrayList;

public class Shape {
    private int x;
    private int y;
    private final ArrayList<Block> parts;

    public Shape(ShapeType type, int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.parts = new ArrayList<Block>();
        for (int i = 0; i < 4; i++) {
            this.parts.add(new Block(type.getPositions()[i][0] + x, type.getPositions()[i][1] + y, color));
        }

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ArrayList<Block> getParts() {
        return parts;
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

    public void rotateLeft() {
        for (Block block : this.parts) {
            int dX = this.x + 1 - block.getX();
            int dY = this.y - block.getY();

            block.setY(dX + this.y);
            block.setX(dY*-1 + this.x + 1);
        }
    }

    public void rotateRight() {
        for (Block block : this.parts) {
            int dX = this.x + 1 - block.getX();
            int dY = this.y - block.getY();

            block.setY(dX*-1 + this.y);
            block.setX(dY + this.x + 1);
        }
    }

    public boolean collideWithGround() {
        for (Block current : this.parts) {
            if (current.getY() + 1 >= Tetris.SCREEN_HEIGHT) {
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
            if (current.getX() + 1 >= Tetris.SCREEN_WIDTH) {
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
                    return CollisionSide.DOWN;
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

    public void hide() {
        for (Block block : this.getParts()) {
            block.hide();
        }
    }
}