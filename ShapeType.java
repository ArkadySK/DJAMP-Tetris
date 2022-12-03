public enum ShapeType {
    O(new int[][] {{0, 0}, {1, 0}, {0, 1}, {1, 1}}),
    L(new int[][] {{0, 1}, {1, 1}, {2, 1}, {2, 0}}),
    J(new int[][] {{0, 0}, {1, 0}, {2, 0}, {2, 1}}),
    I(new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}}),
    Z(new int[][] {{0, 0}, {1, 0}, {1, 1}, {2, 1}}),
    S(new int[][] {{0, 1}, {1, 1}, {1, 0}, {2, 0}}),
    T(new int[][] {{1, 0}, {0, 1}, {1, 1}, {2, 1}});

    private final int[][] positions;
    ShapeType(int[][] positions) {
        this.positions = positions;
    }

    public int[][] getPositions() {
        return this.positions;
    }
}
