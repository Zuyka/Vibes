
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



 
public class JavaFXApplication2 extends Application{
      //  private ExperimentLoader loader;
       // private Canvas canvas;
        
   /* public JavaFXApplication2(final String path) throws IOException{
        File sourceFile = new File(path);
        loader = new ExperimentLoader(sourceFile);
    }*/
     
    ArrayList<TileMap> TileM = new ArrayList<>();
    double W;//коэфициент разборчивости
    private Canvas canvas;
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Расчет с помощью виброизлучателей");
        
        BorderPane root = new BorderPane();  
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_RIGHT);
        Label label1 = new Label("Тип конструкции");
        Label label2 = new Label("Ширина");
        Label label3 = new Label("Высота");
        Label label4 = new Label("Разборчивость речи");
        TextField sizeX = new TextField();
        TextField sizeY = new TextField();
        TextField speech = new TextField();
        Label label5 = new Label("Легкие датчики");
        Button vib1 = new Button("VNG-012GL");
        Button vib2 = new Button("Соната-АВ");
        Label label6 = new Label("Тяжелые датчики");
        Button vib3 = new Button("VNG-012GL");
        Button vib4 = new Button("Соната-АВ");
        
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Button btn = new Button();
        btn.setText("Добавить");
        
        ChoiceBox<String> choicebox = new ChoiceBox<>();
        choicebox.getItems().addAll("Окно", "Стена", "Труба");
        choicebox.setValue("Окно");
        //canvas = createCanvasGrid(600, 500, choicebox.getValue());
        vib2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    int a = Integer.parseInt(sizeX.getText());
                    System.out.println(a);
                    int b = Integer.parseInt(sizeY.getText());
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    }
                
                
                //
                //String type = choicebox.getValue();
                //drawVib(gc, a, b, type);
                //repaint();
                gc.clearRect(0, 0, 400, 500);
                
            }
        });

        
        choicebox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               try{ 
                
                String type = choicebox.getValue();
                int a = Integer.parseInt(sizeX.getText());
                int b = Integer.parseInt(sizeY.getText());
                W = Integer.parseInt(speech.getText());
                Settings set = new Settings(TileM,W, a, b, type);
                switch(type){
                   case "Окно": {redrawCanvas(set);}
                   case "Стена" : {redrawCanvas(set);}
                   case "Труба" : {redrawCanvas(set); }
        }
               }
               catch(NumberFormatException e) {
                    System.out.println(e);
                    }
            }
        });
        ToolBar toolBar1 = new ToolBar();
        toolBar1.setOrientation(Orientation.VERTICAL);
        toolBar1.getItems().addAll(
                label1,
                choicebox,
                new Separator(),
                label2,sizeX,
                label3,sizeY,
                new Separator(),
                label4, speech,
                new Separator(),
                label5, vib1, vib2,
                label6, vib3, vib4
            );
        
        vBox.getChildren().add(toolBar1);
        root.setRight(vBox);
        //Scene scene = new Scene(pane, 600, 400);
        root.setLeft(canvas);
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
        
    }

        //перерисовка канвы еще не работает
    private void redrawCanvas(final Settings settings) {
            
            GraphicsContext gc = canvas.getGraphicsContext2D();
            
            String type = settings.getType();
            int width = settings.getWidth();
            int hight = settings.getHight();
            
            gc.clearRect(0, 0, settings.getWidth(), settings.getHight());
            gc.setStroke(Color.WHITE);
            
            gc.setLineWidth(1.0);
        int a = 20;
        switch(type){
            case "Окно" : a = 100;
            case "Труба" : a = 20;
            case "Стена" : a = 20;}
        for (int x = 0; x < width; x+=a) {
            double x1 ;
            x1 = x + 0.5 ;
            gc.moveTo(x1, 0);
            gc.lineTo(x1, hight);
            gc.stroke();
        }

        for (int y = 0; y < hight; y+=a) {
            double y1 ;
            y1 = y + 0.5 ;
            gc.moveTo(0, y1);
            gc.lineTo(width, y1);
            gc.stroke();
        }
    }
    
    private void drawVib(GraphicsContext gc, int a, int b, String type) {
        ArrayList<TileMap> TileM = new ArrayList<>();
        TileM.add(new TileMap(a,b));
        //TileM.Draw(gc);
        gc.fillOval(10, 60, 4, 4);
        switch(type){
            case "Окно": gc.setFill(Color.BLUE);
            case "Стена" : gc.setFill(Color.WHITE);
            case "Труба" : gc.setFill(Color.GRAY);
        }
        
    }
    
    
    
    private Canvas createCanvasGrid(int width, int height, String type) {
        
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        int a = 0;
        switch(type){
            case "Окно" : a = 100;
            case "Труба" : a = 20;
            case "Стена" : a = 20;}
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

final class Settings{
    private ArrayList<TileMap> tileM = new ArrayList<>();
    private double W;
    private int Width;
    private int Hight;
    private String Type;
    
    Settings(ArrayList<TileMap> tilem, double w, int width, int hight, String type) {
        tileM = tilem;
        W = w;
        Width = width;
        Hight = hight;
        Type = type;
    }
    
    public double getSpeech(){
        return W;
    }
    
    public int getWidth(){
        return Width;
    }
    
    public int getHight(){
        return Hight;
    }
    
    public String getType(){
        return Type;
    }
    
    public ArrayList<TileMap> getTileMap(){
        return tileM;
    }
    
    public void setSettings(ArrayList<TileMap> tilem, double w, int width, int hight, String type){
        tileM = tilem;
        W = w;
        Width = width;
        Hight = hight;
        Type = type;
    }

}
