package UE02A03GeometrischeFormen;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class BinaerenBaumZeichnen extends Application {

	
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc, 400, 600, 0);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc,int x, int y, int i) {
        if (i==7) return;
    	gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        drawPixelsNormal(gc, x, y, 0);
        drawPixelsDiagonalLinks(gc, x, y-100, 0);
        drawPixelsDiagonalRechts(gc, x, y-100, 0);
        i++;
        
    }     
    
    private void drawPixelsNormal(GraphicsContext gc, int x, int y, int n) {
    	if (n==99)return;
        gc.strokeLine(x, y, x, y);
        drawPixelsNormal(gc, x, --y, ++n);
    }
    
    private void drawPixelsDiagonalLinks(GraphicsContext gc, int x, int y, int n) {
    	if (n==99)return;
        gc.strokeLine(x, y, x, y);
        n++;
        drawPixelsDiagonalLinks(gc, --x, --y, n);
    }
    
    private void drawPixelsDiagonalRechts(GraphicsContext gc, int x, int y, int n) {
    	if (n==99)return;
        gc.strokeLine(x, y, x, y);
        n++;
        drawPixelsDiagonalRechts(gc, ++x, --y, n);
    }
    
}
