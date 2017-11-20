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
        drawShapes(gc, 400, 0, 90, 100, 0);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc, double xAlt, double yAlt, double a, double laenge, int i) {
        System.out.println(i);
        if (i >= 7) return;
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        double xNeu;
        double yNeu;

        xNeu = (laenge * Math.cos(a * Math.PI / 180));
        yNeu = (laenge * Math.sin(a*Math.PI/180));
        gc.strokeLine(xAlt, 600 - yAlt, xAlt + xNeu, 600 - (yAlt + yNeu));
        drawShapes(gc, xAlt + xNeu, yAlt + yNeu, a+45, laenge/2, i+1);
        drawShapes(gc, xAlt + xNeu, yAlt + yNeu, a-45, laenge/2, i+1);
    }
}
