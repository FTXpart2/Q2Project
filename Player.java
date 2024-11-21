import java.awt.*;

public class Player {
    private int row, col; // Current position of the player
    private final Color color = Color.RED; // Player's color

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int dRow, int dCol) {
        // Calculate the new position
        int newRow = row + dRow;
        int newCol = col + dCol;
    
        // Define Germany's borders
        int germanyTop = 10;
        int germanyBottom = 90;
        int germanyLeft = 10;
        int germanyRight = 90;
    
        // Check if the new position is within Germany's borders
        if (newRow >= germanyTop && newRow <= germanyBottom && newCol >= germanyLeft && newCol <= germanyRight) {
            row = newRow;
            col = newCol;
        }
    }
    
    
    

    public void drawMe(Graphics g, int x, int y, int cellSize) {
        g.setColor(color);
        g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
    }
}
