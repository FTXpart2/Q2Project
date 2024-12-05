import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.io.Serializable;
public class Runner implements Serializable {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) {

        JFrame frame = new JFrame("Germany");
        Player player = new Player(50, 50); // Start the player near the center of the grid
        
        GridMap c = GridMap.loadData("data.ser");
        if (c == null) {
            c = new GridMap(player); // Create a new instance if loading fails
        }
        final GridMap gridMap = c;
        frame.add(gridMap);
        frame.setSize(500, 500); // 20x20 cells visible, each 25px
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the GridMap focusable and request focus
        gridMap.setFocusable(true);
        gridMap.requestFocusInWindow(); // Request focus for key events

        // Key listener for player movement
        gridMap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                int dRow = 0, dCol = 0;
                
                System.out.println("Key Pressed: " + key); // Debugging key press

                switch (key) {
                    case KeyEvent.VK_W -> dRow = -1; // Move up
                    case KeyEvent.VK_S -> dRow = 1;  // Move down
                    case KeyEvent.VK_A -> dCol = -1; // Move left
                    case KeyEvent.VK_D -> dCol = 1;  // Move right
                }

                // Update player position
                player.move(dRow, dCol);
                gridMap.repaint(); // Redraw the grid
                if (gridMap.displayImage()) {
                    JPanel imagePanel = new JPanel();
                    imagePanel.setLayout(new GridLayout(0, 1)); // One column for images

                    // Limit images to a maximum of 2
                    String url = "https://mcscoring.com/AndroidClass/spartans.png";
                    switch (gridMap.landmarktype()) {
                        case "Cologne Cathederal":
                            url = "https://www.aesu.com/wp-content/uploads/2020/03/europe-4272297_640.jpg";
                            break;
                        case "Brandenburg Gate":
                            url = "https://img.freepik.com/premium-photo/nighttime-winter-view-brandenburg-gate-berlin-germany-ai_431161-541.jpg";
                            break;
                        case "Berlin Wall":
                            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Berlinermauer.jpg/640px-Berlinermauer.jpg";
                            break;
                        case "Neuschwanstein Castle":
                            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Schloss_Neuschwanstein_2013.jpg/500px-Schloss_Neuschwanstein_2013.jpg";
                            break;
                    }
                    try {
                        ImageIcon icon = new ImageIcon(new URL(url));
                        if (icon.getIconWidth() > 0) { // Check if image loaded
                            JLabel label = new JLabel(icon);
                            imagePanel.add(label);
                        } else {
                            System.err.println("Image not found or invalid URL: " + url);
                        }
                    } catch (Exception x) {
                        System.err.println("Failed to load image from URL: " + url);
                        x.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(frame, imagePanel, "Landmark Picture", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
