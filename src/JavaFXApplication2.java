import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sun.plugin.dom.css.Rect;
/*
import State.ExperimentSettings;
import State.ExperimentState;
import State.ExperimentLoader;
import State.Particle;
import java.io.File;
import java.io.IOException;
*/

 
public class JavaFXApplication2 extends Application{
      //  private ExperimentLoader loader;
       // private Canvas canvas;
        
   /* public JavaFXApplication2(final String path) throws IOException{
        File sourceFile = new File(path);
        loader = new ExperimentLoader(sourceFile);
    }*/
     
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();    
        TextField sizeX = new TextField();
        TextField sizeY = new TextField();
        Canvas canvas = new Canvas(300, 400);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Button btn = new Button();
        btn.setText("Добавить");
        
        ChoiceBox<String> choicebox = new ChoiceBox<>();
        choicebox.getItems().addAll("Window", "Wall", "Pipe");
        choicebox.setValue("Window");
        canvas = createCanvasGrid(Integer.parseInt(sizeX.getText()), Integer.parseInt(sizeY.getText()), choicebox.getValue());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                int a = Integer.parseInt(sizeX.getText());
                int b = Integer.parseInt(sizeY.getText());
                String type = choicebox.getValue();
                drawVib(gc, a, b, type);
                //repaint();
            }
        });
        ToolBar toolBar1 = new ToolBar();
        toolBar1.getItems().addAll(
                new Separator(),
                btn,
                sizeX,
                sizeY,
                choicebox,
                new Separator()
            );
        //Scene scene = new Scene(pane, 600, 400);
        root.getChildren().addAll(canvas, toolBar1);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }

        //перерисовка канвы еще не работает
     /*   private void redraw(final ExperimentState state) {
        ExperimentSettings settings = loader.getExperimentSettings();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double barrierWidth = settings.getBarrierWidth();
        double barrierXMin = settings.getBarrierPosX() - barrierWidth / 2;
        double holeHeight = settings.getHoleHeight();
        double upperBarrierHeight = settings.getBoxHeight() - settings.getHolePosY() - holeHeight / 2;
        double lowerBarrierHeight = settings.getHolePosY() - holeHeight / 2;
        double particleR = settings.getParticleRadius();

        gc.clearRect(0, 0, settings.getBoxWidth(), settings.getBoxHeight());
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(0.02);
        gc.strokeRect(
                barrierXMin + 0.01,
                -0.01,
                barrierWidth,
                lowerBarrierHeight);
        gc.strokeRect(
                barrierXMin + 0.01,
                settings.getBoxHeight() - upperBarrierHeight + 0.01,
                barrierWidth,
                upperBarrierHeight);

        for (Particle p : state.getParticles()) {
            if ((p.getId() & 1) == 0) {
                gc.setFill(Color.RED);
            } else {
                gc.setFill(Color.SKYBLUE);
            }

            gc.fillOval(p.getPosX(), p.getPosY(), particleR, particleR);
        }
    }
    */
    private void drawVib(GraphicsContext gc, int a, int b, String type) {
        TileMap TileM = new TileMap(a, b);
        gc.fillOval(10, 60, 4, 4);
        switch(type){
            case "Window": gc.setFill(Color.BLUE);
            case "Wall" : gc.setFill(Color.WHITE);
            case "Pipe" : gc.setFill(Color.GRAY);
        }
    }
    
    private Canvas createCanvasGrid(int width, int height, String type) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        int a = 0;
        switch(type){
            case "Window" : a = 100;
            case "Pipe" : a = 20;
            case "Wall" : a = 20;}
        for (int x = 0; x < width; x+=a) {
            double x1 ;
            x1 = x + 0.5 ;
            gc.moveTo(x1, 0);
            gc.lineTo(x1, height);
            gc.stroke();
        }

        for (int y = 0; y < height; y+=a) {
            double y1 ;
            y1 = y + 0.5 ;
            gc.moveTo(0, y1);
            gc.lineTo(width, y1);
            gc.stroke();
        }
        return canvas ;
    }
    

}