// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Landmark implements Serializable {
   private static final long serialVersionUID = 1L;
   private int row;
   private int col;
   private final Color color;
   private BufferedImage Castle, Gate, Wall, Church;
   private String name;

   public Landmark(int var1, int var2, String name) {
      this.color = Color.BLACK;
      this.row = var1;
      this.col = var2;
      this.name = name;
      try {
        
         this.Castle = ImageIO.read(new File("Castle.png"));
         this.Gate = ImageIO.read(new File("Gate.png"));
         this.Church = ImageIO.read(new File("Church.png"));
         this.Wall = ImageIO.read(new File("Wall.jpg"));
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public int getRow() {
      return this.row;
   }

   public int getCol() {
      return this.col;
   }

   public void drawMe(Graphics var1, int var2, int var3, int var4) {
      var1.setColor(this.color);
        if(name == "Berlin Wall"){
            //draw picture
             var1.drawImage(this.Wall, var2 + var4 / 4 - 14, var3 + var4 / 4 - 10, var4 + 8, var4 + 8, (ImageObserver)null);
        }
        if(name == "Cologne Cathederal"){
            //draw picture
             var1.drawImage(this.Church, var2 + var4 / 4 - 14, var3 + var4 / 4 - 10, var4 + 8, var4 + 8, (ImageObserver)null);
        }
        if(name == "Brandenburg Gate"){
            //draw picture
             var1.drawImage(this.Gate, var2 + var4 / 4 - 14, var3 + var4 / 4 - 10, var4 + 8, var4 + 8, (ImageObserver)null);
        }
        if(name == "Neuschwanstein Castle"){
            //draw picture
             var1.drawImage(this.Castle, var2 + var4 / 4 - 14, var3 + var4 / 4 - 10, var4 + 8, var4 + 8, (ImageObserver)null);
        }
     
   }
}
