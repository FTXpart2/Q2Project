import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map of Germany");
        Player player = new Player(50, 50); // Start the player near the center of the grid
        GridMap gridMap = new GridMap(player);
        

        frame.add(gridMap);
        frame.setSize(500, 500); // 20x20 cells visible, each 25px
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Key listener for player movement
        frame.addKeyListener(new KeyAdapter() {
            @Override
            
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                int dRow = 0, dCol = 0;

                // Update player position based on key press
                
                    try {
                        Thread.sleep(50); // Move every 500 milliseconds
                    } catch (InterruptedException c) {
                        c.printStackTrace();
                    }
                switch (key) {
                    case KeyEvent.VK_W -> dRow = -1; // Move up
                    case KeyEvent.VK_S -> dRow = 1;  // Move down
                    case KeyEvent.VK_A -> dCol = -1; // Move left
                    case KeyEvent.VK_D -> dCol = 1;  // Move right
                }

                // Update player position
                player.move(dRow, dCol);
                gridMap.repaint(); // Redraw the grid
            
        }
        });
        

        frame.setVisible(true);
    }
}
