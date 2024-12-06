import java.awt.Graphics;
import java.awt.Color;
import java.io.Serializable;
public class GridObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Color color;

    public GridObject(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void drawMe(Graphics g, int x, int y) {
        
        g.setColor(color);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.BLACK);
       
        if(name == "Berlin Wall"){
            //draw picture
        }
        if(name == "Cologne Cathederal"){
            //draw picture
        }
        if(name == "Brandenburg Gate"){
            //draw picture
        }
        if(name == "Neuschwanstein Castle"){
            //draw picture
        }
    }

    public String getName() {
        return name;
    }
}