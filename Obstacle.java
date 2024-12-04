import java.awt.*;

public class Obstacle {
    private int row, col;
    private final Color color = Color.BLACK; // Obstacle's color

    public Obstacle(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move() {
        // Random movement for the obstacle
        int direction = (int) (Math.random() * 4); // 0=up, 1=down, 2=left, 3=right
        switch (direction) {
            case 0 -> row = Math.max(0, row - 1); // Move up
            case 1 -> row = Math.min(99, row + 1); // Move down
            case 2 -> col = Math.max(0, col - 1); // Move left
            case 3 -> col = Math.min(99, col + 1); // Move right
        }
    }

    public void drawMe(Graphics g, int x, int y, int cellSize, String name) {
        g.setColor(color);
        g.fillRect(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);

        
    }
}
