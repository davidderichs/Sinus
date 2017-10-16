package UE01A03FourierSynthese;

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
public class Fouriersynthese extends Application {

    private VBox root;
    private Group graph;
    private Rectangle panel;
    private Line line;

    Label aufgabe_4_3;
    ToggleGroup radioGroup;
    RadioButton radioDreieck;
    RadioButton radioRechteck;

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
    double frequenz = 4.0;
    double y = 0;
    int i = 0;

    BorderPane controlLayout;
    VBox showControls;

    /**
     * Aufgabe 3.3 - Synthese - RechteckFunktion
     */
    public void waveRechteck() {
        if(i > 600)
            return;
        y = (amplitude) * Math.sin(frequenz * Math.PI * i / 600);
        y += (amplitude / 3.) * Math.sin(frequenz * 3. * Math.PI * i / 600);
        y += (amplitude / 5.) * Math.sin(frequenz * 5. * Math.PI * i / 600);
        y += (amplitude / 7.) * Math.sin(frequenz * 7. * Math.PI * i / 600);
        drawLine(i, (int)y) ;
        i++;
        waveRechteck();
    }

    /**
     * Aufgabe 3.4 - Synthese - Dreieckschwingung
     */
    public void waveDreieck() {
        if(i > 600)
            return;
        y = (amplitude) * Math.sin(frequenz * Math.PI * i / 600);
        y += (amplitude / 9.) * Math.sin(frequenz * -3. * Math.PI * i / 600);
        y += (amplitude / 25.) * Math.sin(frequenz * 5. * Math.PI * i / 600);
        y += (amplitude / 49.) * Math.sin(frequenz * -7. * Math.PI * i / 600);
        drawLine(i, (int) y) ;
        i++;
        waveDreieck();
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
            aufgabe_4_3.setText("Dreieck-Folge: 1, 9, 25, 49, 81, 121, ... Formel: f(n)=(n+2)^2");
            waveDreieck();
        }
        if (radioRechteck.isSelected()){
            aufgabe_4_3.setText("Rechteck-Folge: 1, 3, 5, 7, 9, 11, 13, ... Formel: f(n)=n+2");
            waveRechteck();
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
                redraw();
            }
        });

        slider_frequenz.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                frequenz = (double) new_val;
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

        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                redraw();
            }
        });

        aufgabe_4_3 = new Label ("Rechteck-Folge: 1, 3, 5, 7, 9, 11, 13, ... Formel: f(n)=n+2");
        aufgabe_4_3.setTextFill(Color.WHITE);
        aufgabe_4_3.setFont(new Font("Arial", 20));
        aufgabe_4_3.setPadding(new Insets(0, 0, 0, 5));

        myvbox.getChildren().add(aufgabe_4_3);
        myvbox.getChildren().add(hboxLabelsAmplitudeFrequenz);
        myvbox.getChildren().add(hboxAmplitudeFrequenzSliders);
        myvbox.getChildren().add(hboxLabelsFaktoren);
        myvbox.getChildren().add(hboxFaktorenSliders);
        myvbox.getChildren().add(radioDreieck);
        myvbox.getChildren().add(radioRechteck);
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