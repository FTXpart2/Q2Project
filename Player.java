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
        int[][] nogoareas = {
            {85,10},{85,14},{85,13},{85,12},{85,11},{85, 15}, {83, 16}, {82, 17}, {81, 18}, // Starting from the southwest
            {80, 18}, {79, 19}, {78, 19}, {77, 20}, {76, 21},
            {75, 22}, {74, 23}, {73, 23}, {72, 24}, {71, 25},
            {70, 25}, {69, 26}, {68, 27}, {67, 27}, {66, 28},
            {65, 29}, {64, 30}, {63, 30}, {62, 31}, {61, 32},
            {60, 33}, {59, 33}, {58, 34}, {57, 35}, {56, 35},
            {55, 36}, {54, 37}, {53, 38}, {52, 38}, {51, 39},
            {50, 40}, {49, 41}, {47, 42}, {46, 43},
            {45, 44}, {44, 45}, {43, 45}, {42, 46}, {41, 47},
            {40, 48}, {39, 48}, {38, 49}, {37, 50}, {36, 51},
            {35, 52}, {34, 52}, {33, 53}, {32, 54}, {31, 55},
            {30, 55}, {29, 56}, {28, 57}, {26, 58},
            {25, 59}, {24, 60}, {23, 60}, {22, 61}, {21, 62},
            {20, 63}, {19, 63}, {18, 64}, {17, 65}, {16, 66},
            {15, 66}, {14, 67}, {13, 68}, {12, 69}, {11, 69},{10,69},/*cologne cathederal*/{82,15},/* Berlin wall*/{40,86}, /*brandenburg gate */ {41, 89}, /* Neuschwanstein Castle */{88,55} // Ending in the northwest
        };

        // Check if the new position is within Germany's borders
        
    
        boolean action = false;

        

        
            for (int[] location : nogoareas) {
                
                if(newRow == location[0] && newCol == location[1] ){
                    System.out.println("ur  on water");
                    action = true;
                    break;
                    
                }
                
            }
            if (newRow >= germanyTop && newRow <= germanyBottom && newCol >= germanyLeft && newCol <= germanyRight && !action) {
       
                row = newRow;
                col = newCol;
                
            
        }
    }
    
    
    

    public void drawMe(Graphics g, int x, int y, int cellSize) {
        g.setColor(color);
        g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
    }
}
