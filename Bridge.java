// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Bridge implements Serializable {
   private static final long serialVersionUID = 1L;
   private int row;
   private int col;
   private final Color color;
   private BufferedImage bridge;

   public Bridge(int var1, int var2) {
      this.color = Color.BLACK;
      this.row = var1;
      this.col = var2;

      try {
         this.bridge = ImageIO.read(new File("bridge.png"));
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
      var1.drawImage(this.bridge, var2 + var4 / 4 - 14, var3 + var4 / 4 - 10, var4 + 8, var4 + 8, (ImageObserver)null);
   }
}
