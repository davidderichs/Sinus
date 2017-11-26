package UE02A03GeometrischeFormen;

        import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

/***
 * Implementation of a snowflake-shaped figure being drawn on a JavaFX-Canvas.
 * Thus being four crossed lines drawn from the same origin x-y-coordinates.
 * Then being drawn again on each end-point of either of these lines with a length of 0.33px
 */
public class Schneeflocke extends Application {

    /***
     * Starts the Application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /***
     * Sets up the JavaFX-Elements and calls sequence to draw a snlowflake-shaped figure.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        drawShapes(gc, 400, 300, 50, 0, 0);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /***
     * Recursive Method, which draws a snowflake-shaped figure until recursive depth is reached.
     * @param gc Graphics-Element to be drawn on.
     * @param posX X-Coordinate of the current recursive stack.
     * @param posY Y-Coordinate of the current recursive stack.
     * @param laenge length of the lines to be drawn.
     * @param a Starting angle to be used to rotate the current shape.
     * @param n Recursive depth counter.
     */
    private void drawShapes(GraphicsContext gc, double posX, double posY, double laenge, double a, int n) {
        if(n>2) return;
        n=n+1;

        double xNeu = laenge * Math.cos(a+45 * Math.PI / 180);
        double yNeu = laenge * Math.sin(a+45 * Math.PI / 180);
        gc.strokeLine(posX, posY, posX + xNeu, posY - yNeu);
        drawShapes(gc, posX + xNeu, posY - yNeu, laenge*0.33, a + 315, n);

        xNeu = laenge * Math.cos(a + 135 * Math.PI / 180);
        yNeu = laenge * Math.sin(a+135 * Math.PI / 180);
        gc.strokeLine(posX, posY, posX + xNeu, posY - yNeu);
        drawShapes(gc, posX + xNeu, posY - yNeu, laenge*0.33, a + 315, n);

        xNeu = laenge * Math.cos(a + 225 * Math.PI / 180);
        yNeu = laenge * Math.sin(a+225 * Math.PI / 180);
        gc.strokeLine(posX, posY, posX + xNeu, posY - yNeu);
        drawShapes(gc, posX+xNeu, posY-yNeu, laenge*0.33, a+315, n);

        xNeu = laenge * Math.cos(a + 315 * Math.PI / 180);
        yNeu = laenge * Math.sin(a+315 * Math.PI / 180);
        gc.strokeLine(posX, posY, posX+xNeu, posY-yNeu);
        drawShapes(gc, posX+xNeu, posY-yNeu, laenge*0.33, a+315, n);

    }
}
