
import java.awt.Graphics2D;
import javafx.scene.canvas.GraphicsContext;

public class TileMap{
    //массив этих квадратиков
    public Tile[][] tileMap;
    
    
    //кол-во квадратиков определяется типом поверхности
    public TileMap(int r, int c) {
        tileMap = new Tile[r][c];
    }
    
    //отрисовка всех квадратиков
    public void Draw(GraphicsContext g2d) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                tileMap[i][j].draw(g2d);
            }
        }
    }
}
