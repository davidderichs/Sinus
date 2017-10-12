package UE01A03FourierSynthese;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by David on 12.10.2017.
 */
public class Fouriersynthese extends Application {

    private VBox root;
    private Group graph;
    private Rectangle panel;
    private Line line;

    Label f_label_description;
    Label f_label_value;
    Label b_label_description;
    Label b_label_value;


    int cursorX = 0;
    int cursorY = 200;

    double f;
    double b;


    /**
     * y = 50. * Math.sin(4 * Math.PI * i /600);
     */

    double amplitude = 8;  // Amplitude
    double y = 0;
    int i = 0;

    BorderPane controlLayout;
    VBox showControls;
    Slider my_slider_f_val;
    Slider my_slider_b_val;


    public void wave(double f, double b) {
        amplitude = f;
        if(i > 600)
            return;
        y = amplitude * Math.sin(4 * Math.PI * i / 600);

        System.out.println(i + " " + (int)y );
        drawLine(i, (int)y) ;
        i++;
        wave(f, b);
    }

    public static void main(String[] args) {
        launch(args);
    }

    void drawLine(int x, int y) {
        int endX =  x;
        int endY =  200 - y;
        line = new Line(cursorX, cursorY, endX , endY);
        cursorX = endX;
        cursorY = endY;
        line.setStroke(Color.RED);
        panel.setFill(Color.WHITE);
        graph.getChildren().add(line);
    }

    private BorderPane createBorderPane(){
        BorderPane myborder = new BorderPane();
        Rectangle panel = new Rectangle(600, 400, Color.WHITESMOKE);
        myborder.setCenter(panel);
        return myborder;
    }

    private VBox addHbox(){
        VBox myvbox = new VBox();
        myvbox.setMinWidth(80);
        myvbox.setPadding(new Insets(0, 20, 0, 20));
        myvbox.setSpacing(0);
        myvbox.setStyle("-fx-background-color: #336699;");

        HBox myhboxSliders = new HBox();
        myhboxSliders.setPadding(new Insets(15, 15, 0, 12));
        myhboxSliders.setSpacing(10);
        myhboxSliders.setStyle("-fx-background-color: #336699;");

        HBox myhboxLabels = new HBox();
        myhboxLabels.setPadding(new Insets(15, 15, 15, 12));
        myhboxLabels.setSpacing(10);
        myhboxLabels.setStyle("-fx-background-color: #336699;");

        Slider my_slider_f_val = new Slider();
        my_slider_f_val.setMin(1.91);
        my_slider_f_val.setMax(1.99);
        my_slider_f_val.setValue(f);

        Slider my_slider_b_val = new Slider();
        my_slider_b_val.setMin(0.91);
        my_slider_b_val.setMax(1.0);
        my_slider_b_val.setValue(b);

        f_label_description = new Label("f:");
        f_label_description.setTextFill(Color.WHITE);
        f_label_description.setFont(new Font("Arial", 20));
        f_label_description.setPadding(new Insets(0, 0, 0, 5));

        f_label_value = new Label(Double.toString(my_slider_f_val.getValue()));
        f_label_value.setTextFill(Color.WHITE);
        f_label_value.setFont(new Font("Arial", 20));

        b_label_description = new Label("b:");
        b_label_description.setTextFill(Color.WHITE);
        b_label_description.setFont(new Font("Arial", 20));
        b_label_description.setPadding(new Insets(0, 0, 0, 80));

        b_label_value = new Label(Double.toString(my_slider_b_val.getValue()));
        b_label_value.setTextFill(Color.WHITE);
        b_label_value.setFont(new Font("Arial", 20));

        myhboxSliders.getChildren().addAll(my_slider_f_val, my_slider_b_val);
        myhboxLabels.getChildren().addAll(f_label_description, f_label_value, b_label_description, b_label_value);

        my_slider_f_val.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                cursorX = 0;
                cursorY = 200;
                amplitude = 8;  // Amplitude
                y = 0;
                i = 0;
                f = (double) new_val;
                graph.getChildren().clear();
                try {
                    f_label_value.setText(Double.toString(f).substring(0,4));
                } catch (StringIndexOutOfBoundsException e){
                    f_label_value.setText(Double.toString(f));
                }

                wave(f, 0.99);
            }
        });

        my_slider_b_val.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                cursorX = 0;
                cursorY = 200;
                amplitude = 8;  // Amplitude
                y = 0;
                i=0;
                b = (double) new_val;
                graph.getChildren().clear();


                try {
                    b_label_value.setText(Double.toString(b).substring(0,4));
                } catch (StringIndexOutOfBoundsException e){
                    b_label_value.setText(Double.toString(b));
                }

                wave(f, b);
            }
        });

        myvbox.getChildren().add(myhboxSliders);
        myvbox.getChildren().add(myhboxLabels);
        return myvbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        f = 1.99;
        b = 0.99;

        root = new VBox(10);
        graph = new Group();
        showControls = addHbox();
        controlLayout = new BorderPane();

        controlLayout.setPrefSize(600,400);
        controlLayout.setStyle("-fx-border-color: white;");

        panel = new Rectangle(600, 400, Color.WHITESMOKE);
        graph.getChildren().add(panel);

        controlLayout.setCenter(graph);

        root.getChildren().add(showControls);
        root.getChildren().add(controlLayout);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Scribble");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}