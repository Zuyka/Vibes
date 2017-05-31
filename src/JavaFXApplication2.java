
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

 
public class JavaFXApplication2 extends Application implements MouseListener, MouseMotionListener{
    
 
    private ArrayList vibe;
    private Ellipse2D current;
    
  
    
    public void add(Point2D p){
        current = new Ellipse2D.Double(p.getX()-10, p.getY()-10, 20, 20);
        vibe.add(current);
        
    }
    //yjdsq rjvvtyn
    
    private class MyMouse extends MouseAdapter{
        public void MousePressed(MouseEvent e){
            add(e.getPoint());
        }
    }
    //*
    
    int mouseX = 0, mouseY = 0;
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        
        
        TextField radius = new TextField();
        Canvas canvas = new Canvas(3000, 2500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Button btn = new Button();
        btn.setText("Добавить");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                double r = Double.parseDouble(radius.getText());
                drawVib(gc, r);
                //repaint();
            }
        });
        
        root.getChildren().addAll(canvas, radius, btn);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawVib(GraphicsContext gc, double r) {
        gc.fillOval(10, 60, 4, 4);
        gc.fillOval(r, r, r, r);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // сохранить координаты
			mouseX = e.getX();
			mouseY = e.getY();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}