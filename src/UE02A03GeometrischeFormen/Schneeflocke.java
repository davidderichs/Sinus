package UE02A03GeometrischeFormen;

        import javafx.application.Application;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.canvas.GraphicsContext;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;
        import javafx.scene.canvas.Canvas;

public class Schneeflocke extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc, 400, 300, 0, 100, 0);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc, double xAlt, double yAlt, double a, double laenge, int i) {
        System.out.println(i);
        if (i >= 6) return;
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        double xNeu;
        double yNeu;

        xNeu = (laenge * Math.cos(a * Math.PI / 180));
        yNeu = (laenge * Math.sin(a * Math.PI / 180));
        gc.strokeLine(xAlt, 600 - yAlt, xAlt + xNeu, 600 - (yAlt + yNeu));

        drawShapes(gc, xAlt, yAlt, a+60, laenge, i+1);
        drawShapes(gc, xAlt, yAlt, a+120, laenge, i+1);
        drawShapes(gc, xAlt, yAlt, a+180, laenge, i+1);
        drawShapes(gc, xAlt, yAlt, a+240, laenge, i+1);
        drawShapes(gc, xAlt, yAlt, a+300, laenge, i+1);
        drawShapes(gc, xAlt, yAlt, a+360, laenge, i+1);

    }
}
