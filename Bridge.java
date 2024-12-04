import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Bridge {
    private int row, col;
    private final Color color = Color.BLACK; // Obstacle's color
    private BufferedImage bridge;

    public Bridge(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
        try {
     
      
            bridge = ImageIO.read(new File("bridge.png"));
           
            
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    

    public void drawMe(Graphics g, int x, int y, int cellSize) {
        g.setColor(color);
       
        g.drawImage(bridge, x+cellSize/4-14,y+cellSize/4-10,cellSize+8,cellSize+8,null);
    }
}
