import java.awt.*;

public class Bridge {
    private int row, col;
    private final Color color = Color.BLACK; // Obstacle's color

    public Bridge(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    

    public void drawMe(Graphics g, int x, int y, int cellSize) {
        g.setColor(color);
        g.fillRect(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
    }
}
