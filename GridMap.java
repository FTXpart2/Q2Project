import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class GridMap extends JPanel {
    private final int GRID_SIZE = 100;
    private final int VIEWPORT_SIZE = 10;
    private final int CELL_SIZE = 50;
    private final HashMap<Location, LinkedList<GridObject>> gridMapTable = new HashMap<>();
    private Player player;
    private Obstacle obstacle; // Obstacle instance

    public GridMap(Player player) {
        this.player = player;
        this.obstacle = new Obstacle(50, 50); // Starting position for the obstacle
        generateGermanyMap();

        // Start the obstacle movement thread
        Thread obstacleThread = new Thread(() -> {
            while (true) {
                obstacle.move();
                repaint();
                try {
                    Thread.sleep(500); // Move the obstacle every 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        obstacleThread.start();
    }

    private void generateGermanyMap() {
        // Generate the map as before (with terrain, river, etc.)
        int germanyTop = 10, germanyBottom = 90;
        int germanyLeft = 10, germanyRight = 90;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Fill the map with terrain, water, and edges (same logic as before)
                if (row >= germanyTop && row <= germanyBottom && col >= germanyLeft && col <= germanyRight) {
                    if (row < germanyTop + 10 && Math.random() < 0.6) {
                        addObject(row, col, new GridObject("", Color.YELLOW));
                    } else {
                        double random = Math.random();
                        if (random < 0.4) {
                            addObject(row, col, new GridObject("", Color.GREEN));
                        } else if (random < 0.7) {
                            addObject(row, col, new GridObject("", new Color(139, 69, 19)));
                        } else if (random < 0.85) {
                            addObject(row, col, new GridObject("", new Color(0, 153, 0)));
                        } else {
                            addObject(row, col, new GridObject("", Color.GRAY));
                        }
                    }
                } else if (row < germanyTop) {
                    // Northern edge (Water - North Sea/Baltic Sea)
                    addObject(row, col, new GridObject("", Color.BLUE));
                } 
                else if (row >= germanyBottom && col >= germanyLeft && col <= germanyRight) {
                    // Bottom edge (Austria)
                    addObject(row, col, new GridObject("", Color.CYAN));
                } 
                else if (col < germanyLeft) {
                    // Left edge (France)
                    addObject(row, col, new GridObject("", Color.CYAN));
                } 
                else if (col >= germanyRight) {
                    // Right edge (Poland)
                    addObject(row, col, new GridObject("", Color.CYAN));
                } 
                else {
                    // Remaining areas filled with water
                    addObject(row, col, new GridObject("Water", Color.BLUE));
                }
            }
        }
        drawRhineRiver();
    }

    private void drawRhineRiver() {
        // Add the river to the map as before
         // Define the path of the Rhine River
        int[][] rhinePath = {
            {85,10},{85,14},{85,13},{85,12},{85,11},{85, 15}, {84, 16}, {83, 16}, {82, 17}, {81, 18}, // Starting from the southwest
            {80, 18}, {79, 19}, {78, 19}, {77, 20}, {76, 21},
            {75, 22}, {74, 23}, {73, 23}, {72, 24}, {71, 25},
            {70, 25}, {69, 26}, {68, 27}, {67, 27}, {66, 28},
            {65, 29}, {64, 30}, {63, 30}, {62, 31}, {61, 32},
            {60, 33}, {59, 33}, {58, 34}, {57, 35}, {56, 35},
            {55, 36}, {54, 37}, {53, 38}, {52, 38}, {51, 39},
            {50, 40}, {49, 41}, {48, 41}, {47, 42}, {46, 43},
            {45, 44}, {44, 45}, {43, 45}, {42, 46}, {41, 47},
            {40, 48}, {39, 48}, {38, 49}, {37, 50}, {36, 51},
            {35, 52}, {34, 52}, {33, 53}, {32, 54}, {31, 55},
            {30, 55}, {29, 56}, {28, 57}, {27, 58}, {26, 58},
            {25, 59}, {24, 60}, {23, 60}, {22, 61}, {21, 62},
            {20, 63}, {19, 63}, {18, 64}, {17, 65}, {16, 66},
            {15, 66}, {14, 67}, {13, 68}, {12, 69}, {11, 69},{10,69} // Ending in the northwest
        };
    
        for (int[] location : rhinePath) {
            int row = location[0];
            int col = location[1];
            addObject(row, col, new GridObject("Water", Color.BLUE)); // Cyan color for river
        }
    }

    public void addObject(int row, int col, GridObject obj) {
        Location loc = new Location(row, col);
        gridMapTable.computeIfAbsent(loc, k -> new LinkedList<>()).add(obj);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the top-left corner of the viewport based on the player's position
        int viewportStartRow = Math.max(0, player.getRow() - VIEWPORT_SIZE / 2);
        int viewportStartCol = Math.max(0, player.getCol() - VIEWPORT_SIZE / 2);

        // Ensure the viewport does not exceed the map boundaries
        viewportStartRow = Math.min(viewportStartRow, GRID_SIZE - VIEWPORT_SIZE);
        viewportStartCol = Math.min(viewportStartCol, GRID_SIZE - VIEWPORT_SIZE);

        // Draw the 10x10 viewport as before
        for (int r = 0; r < VIEWPORT_SIZE; r++) {
            for (int c = 0; c < VIEWPORT_SIZE; c++) {
                int gridRow = viewportStartRow + r;
                int gridCol = viewportStartCol + c;

                Location loc = new Location(gridRow, gridCol);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                if (gridMapTable.containsKey(loc)) {
                    for (GridObject obj : gridMapTable.get(loc)) {
                        obj.drawMe(g, c * CELL_SIZE, r * CELL_SIZE);
                    }
                }
            }
        }

        // Draw the player at their position within the viewport
        int playerViewportX = (player.getCol() - viewportStartCol) * CELL_SIZE;
        int playerViewportY = (player.getRow() - viewportStartRow) * CELL_SIZE;
        player.drawMe(g, playerViewportX, playerViewportY, CELL_SIZE);

        // Draw the obstacle
        int obstacleViewportX = (obstacle.getCol() - viewportStartCol) * CELL_SIZE;
        int obstacleViewportY = (obstacle.getRow() - viewportStartRow) * CELL_SIZE;
        obstacle.drawMe(g, obstacleViewportX, obstacleViewportY, CELL_SIZE);

        drawBorders(g, viewportStartRow, viewportStartCol);
    }

    private void drawBorders(Graphics g, int viewportStartRow, int viewportStartCol) {
        // Draw borders as before
          g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        // Check if the viewport includes the labeled borders
        if (viewportStartRow < 10) {
            g.drawString("North Sea/Baltic Sea", getWidth() / 2 - 60, 15); // Top
        }
        if (viewportStartCol <= 10) {
            g.drawString("France", 10, getHeight() / 2); // Left side
        }
        if (viewportStartCol + VIEWPORT_SIZE > 90) {
            g.drawString("Poland", getWidth() - 60, getHeight() / 2); // Right side
        }
        if (viewportStartRow + VIEWPORT_SIZE > 90) {
            g.drawString("Austria", getWidth() / 2 - 30, getHeight() - 10); // Bottom
        }
    }
}
