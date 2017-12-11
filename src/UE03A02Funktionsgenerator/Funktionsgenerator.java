package UE03A02Funktionsgenerator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
public class Funktionsgenerator extends Application {

    private VBox root;
    private Group graph;
    private Rectangle panel;
    private Line line;

    Label aufgabe_4_3;
    ToggleGroup radioGroup;
    RadioButton radioDreieck;
    RadioButton radioRechteck;
    RadioButton radioSinus;

    Label amplitude_label_description;
    Label amplitude_label_value;
    Label amplitude_faktor_label_desccriptor;
    Label amplitude_faktor_label_value;
    Label frequenz_label_description;
    Label frequenz_label_value;
    Label frequenz_faktor_label_descriptor;
    Label frequenz_faktor_label_value;


    int cursorX = 0;
    int cursorY = 200;

    /**
     * y = 50. * Math.sin(4 * Math.PI * i /600);
     */

    double amplitude = 50.0;  // Amplitude
    double frequenz = 1.0;
    double y = 0;
    int i = 0;
    int lastX = 0;
    int lastY = 0;
    int periodendauer = 300/((int)frequenz*4);
    int dreieckInkrement = periodendauer;

    BorderPane controlLayout;
    VBox showControls;


    public void waveSinus() {
        if(i > 600)
            return;
        double y = amplitude * Math.sin(frequenz * Math.toRadians((double)i));
        drawPixel(i, (int) y) ;
        i++;
        waveSinus();
    }
    /**
     * Aufgabe 3.3 - Synthese - RechteckFunktion
     */
    public void waveRechteck(int vorZ) {
        if(i > 600)
            return;
        int scale = 600/(int)frequenz;
        drawLine(i, 0, i, vorZ*(int)amplitude) ;
        drawLine(i, vorZ*(int)amplitude, i+scale, vorZ*(int)amplitude);
        drawLine(i + scale, 0, i + scale, vorZ * (int) amplitude) ;
        i+=scale;
        waveRechteck(-vorZ);
    }

    /**
     * Aufgabe 3.4 - Synthese - Dreieckschwingung
     */
    public void waveDreieck() {
        if(i > 600)
            return;

        /**
         * Zusatzaufgabe: Eine Schleife, die 16-Mal die Faktoren errechnet.
         * Nach der Formel, die ich mir ausgedacht hab. Abhaengig von n.
         *
         * Hier ist es ja nur drei-mal gemacht worden, wird dadurch einfach genauer.
         *
         */
        y = (amplitude) * Math.sin(frequenz * Math.PI * i / 600);
        int m = -1;
        for (int n=3; n<=33; n+=2){
            y += (amplitude / Math.pow(n,2)) * Math.sin(frequenz * ((double) (n*m) ) * Math.PI * i / 600);
            m=-m;
        }
        if(i==dreieckInkrement){
            drawLine(lastX, 200 - lastY, i, 200 - (int) y);
            lastX = i;
            lastY = (int)y;
            dreieckInkrement+=periodendauer;
            System.out.println("t neu: " + dreieckInkrement);
        }
        i++;
        waveDreieck();
    }

    public static void main(String[] args) {
        launch(args);
    }

    void drawPixel(int x, int y) {
        int endX =  x;
        int endY =  200 - y;
        line = new Line(cursorX, cursorY, endX , endY);
        cursorX = endX;
        cursorY = endY;
        line.setStroke(Color.RED);
        panel.setFill(Color.WHITE);
        graph.getChildren().add(line);
    }

    void drawLine(int x, int y, int endX, int endY) {
        line = new Line(x, y, endX , endY);
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

    private void redraw(){
        cursorX = 0;
        cursorY = 200;
        y = 0;
        i = 0;
        graph.getChildren().clear();
        try {
            amplitude_label_value.setText(Double.toString(amplitude).substring(0, 4));
            frequenz_label_value.setText(Double.toString(frequenz).substring(0,4));
        } catch (StringIndexOutOfBoundsException e) {
            amplitude_label_value.setText(Double.toString(amplitude));
            frequenz_label_value.setText(Double.toString(frequenz));
        }
        i = 0;
        if (radioDreieck.isSelected()){
            waveDreieck();
        }
        if (radioRechteck.isSelected()){
            waveRechteck(-1);
        }
        if (radioSinus.isSelected()){
            waveSinus();
        }
    }

    private VBox addHbox() {
        VBox myvbox = new VBox();
        myvbox.setMinWidth(80);
        myvbox.setPadding(new Insets(0, 20, 0, 20));
        myvbox.setSpacing(0);
        myvbox.setStyle("-fx-background-color: #336699;");

        HBox hboxAmplitudeFrequenzSliders = new HBox();
        hboxAmplitudeFrequenzSliders.setPadding(new Insets(0, 15, 15, 12));
        hboxAmplitudeFrequenzSliders.setSpacing(130);
        hboxAmplitudeFrequenzSliders.setStyle("-fx-background-color: #336699;");

        HBox hboxLabelsAmplitudeFrequenz = new HBox();
        hboxLabelsAmplitudeFrequenz.setPadding(new Insets(15, 0, 15, 12));
        hboxLabelsAmplitudeFrequenz.setSpacing(10);
        hboxLabelsAmplitudeFrequenz.setStyle("-fx-background-color: #336699;");

        HBox hboxFaktorenSliders = new HBox();
        hboxFaktorenSliders.setPadding(new Insets(0, 15, 15, 12));
        hboxFaktorenSliders.setSpacing(130);
        hboxFaktorenSliders.setStyle("-fx-background-color: #336699;");

        HBox hboxLabelsFaktoren = new HBox();
        hboxLabelsFaktoren.setPadding(new Insets(15, 0, 15, 12));
        hboxLabelsFaktoren.setSpacing(10);
        hboxLabelsFaktoren.setStyle("-fx-background-color: #336699;");

        Slider slider_amplitude = new Slider();
        slider_amplitude.setMin(0.0);
        slider_amplitude.setMax(100.0);
        slider_amplitude.setValue(amplitude);

        Slider slider_frequenz = new Slider();
        slider_frequenz.setMin(0.0);
        slider_frequenz.setMax(100.0);
        slider_frequenz.setValue(frequenz);


        amplitude_label_description = new Label("Amplitude: a=");
        amplitude_label_description.setTextFill(Color.WHITE);
        amplitude_label_description.setFont(new Font("Arial", 20));
        amplitude_label_description.setPadding(new Insets(0, 0, 0, 5));

        amplitude_label_value = new Label(Double.toString(slider_amplitude.getValue()));
        amplitude_label_value.setTextFill(Color.WHITE);
        amplitude_label_value.setFont(new Font("Arial", 20));

        frequenz_label_description = new Label("Frequenz: f=");
        frequenz_label_description.setTextFill(Color.WHITE);
        frequenz_label_description.setFont(new Font("Arial", 20));
        frequenz_label_description.setPadding(new Insets(0, 0, 0, 80));

        frequenz_label_value = new Label(Double.toString(slider_frequenz.getValue()));
        frequenz_label_value.setTextFill(Color.WHITE);
        frequenz_label_value.setFont(new Font("Arial", 20));

        frequenz_faktor_label_descriptor = new Label("Faktor F.: f_faktor=");
        frequenz_faktor_label_descriptor.setTextFill(Color.WHITE);
        frequenz_faktor_label_descriptor.setFont(new Font("Arial", 20));
        frequenz_faktor_label_descriptor.setPadding(new Insets(0, 0, 0, 5));

        hboxAmplitudeFrequenzSliders.getChildren().addAll(slider_amplitude, slider_frequenz);
        hboxLabelsAmplitudeFrequenz.getChildren().addAll(amplitude_label_description, amplitude_label_value,
                frequenz_label_description, frequenz_label_value);

        slider_amplitude.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                amplitude = (double) new_val;
                lastX = 0;
                lastY = 0;
                periodendauer = 300/((int)frequenz*4);
                dreieckInkrement = periodendauer;
                redraw();
            }
        });

        slider_frequenz.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                frequenz = (double) new_val;
                lastX = 0;
                lastY = 0;
                periodendauer = 300/((int)frequenz*4);
                dreieckInkrement = periodendauer;
                redraw();
            }
        });

        radioGroup = new ToggleGroup();

        radioDreieck = new RadioButton ("Dreieck");
        radioDreieck.setTextFill(Color.WHITE);
        radioDreieck.setSelected(true);
        radioDreieck.setToggleGroup(radioGroup);

        radioRechteck = new RadioButton("Rechteck");
        radioRechteck.setTextFill(Color.WHITE);
        radioRechteck.setToggleGroup(radioGroup);

        radioSinus = new RadioButton("Sinus");
        radioSinus.setTextFill(Color.WHITE);
        radioSinus.setToggleGroup(radioGroup);

        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                redraw();
            }
        });


        myvbox.getChildren().add(hboxLabelsAmplitudeFrequenz);
        myvbox.getChildren().add(hboxAmplitudeFrequenzSliders);
        myvbox.getChildren().add(hboxLabelsFaktoren);
        myvbox.getChildren().add(hboxFaktorenSliders);
        myvbox.getChildren().add(radioDreieck);
        myvbox.getChildren().add(radioRechteck);
        myvbox.getChildren().add(radioSinus);
        return myvbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        root = new VBox(10);
        graph = new Group();
        showControls = addHbox();
        controlLayout = new BorderPane();

        controlLayout.setPrefSize(600, 400);
        controlLayout.setStyle("-fx-border-color: white;");

        panel = new Rectangle(600, 400, Color.WHITESMOKE);
        graph.getChildren().add(panel);

        controlLayout.setCenter(graph);

        root.getChildren().add(showControls);
        root.getChildren().add(controlLayout);

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Scribble");
        primaryStage.setScene(scene);

        primaryStage.show();

        amplitude = 50.0;
        frequenz = 4.0;
        i=0;
        redraw();
    }
}