
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image; //картинка виброизлучателя
    private int Id;
    private int x, y;
    public Tile (BufferedImage image, int Id, int x, int y) {
        this.image = image;
        this.Id = Id;
        this.x = x;
        this.y = y;
    }
    
    //в этом методе будет отрисовка виброизлучателя в квадратике канвы
    public void draw(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId() {
        return Id;
    }
}