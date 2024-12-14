// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class StandObstacle implements Serializable {
   private static final long serialVersionUID = 1L;
   private int row;
   private int col;
   private final Color color;
   private static BufferedImage Tree, Rock, Mountain;
   private String name;
    static{
        try {
        
         
         Tree = ImageIO.read(new File("Tree.png"));
         Rock = ImageIO.read(new File("Boulder.png"));
         Mountain = ImageIO.read(new File("Mountain.png"));
      } catch (Exception var4) {
         var4.printStackTrace();
      }
    }
   public StandObstacle(int var1, int var2, String name) {
      this.color = Color.BLACK;
      this.row = var1;
      this.col = var2;
      this.name = name;
      

   }

   public int getRow() {
      return this.row;
   }

   public int getCol() {
      return this.col;
   }

   public void drawMe(Graphics var1, int var2, int var3, int var4) {
      var1.setColor(this.color);
        if(name == "Tree"){
            //draw picture
             var1.drawImage(Tree, var2 + var4 / 4 - 12, var3 + var4 / 4 -10, var4, var4, (ImageObserver)null);
        }
        if(name == "Rock"){
            //draw picture
             var1.drawImage(Rock, var2 + var4 / 4 - 12, var3 + var4 / 4 - 13, var4, var4, (ImageObserver)null);
        }
        if(name == "Mountain"){
            //draw picture
             var1.drawImage(Mountain, var2 + var4 / 4 - 12, var3 + var4 / 4 - 14, var4, var4, (ImageObserver)null);
        }

     
   }
}
