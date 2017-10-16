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

    double amplitude = 8.0;  // Amplitude
    double amplitude_faktor = 1.0; // Faktor Synthese Amplitude
    double frequenz = 4.0;
    double frequenz_faktor = 1.0; // Faktor Synthese Frequenz
    double synthese = 0;
    double y = 0;
    int i = 0;

    BorderPane controlLayout;
    VBox showControls;


    public void wave() {
        if(i > 600)
            return;
        y = (amplitude / amplitude_faktor) * Math.sin(frequenz * frequenz_faktor * Math.PI * i / 600);
        drawLine(i, (int)y) ;
        i++;
        wave();
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

        HBox hboxAmplitudeFrequenzSliders = new HBox();
        hboxAmplitudeFrequenzSliders.setPadding(new Insets(15, 15, 15, 12));
        hboxAmplitudeFrequenzSliders.setSpacing(80);
        hboxAmplitudeFrequenzSliders.setStyle("-fx-background-color: #336699;");

        HBox hboxLabelsAmplitudeFrequenz = new HBox();
        hboxLabelsAmplitudeFrequenz.setPadding(new Insets(15, 15, 15, 12));
        hboxLabelsAmplitudeFrequenz.setSpacing(10);
        hboxLabelsAmplitudeFrequenz.setStyle("-fx-background-color: #336699;");

        HBox hboxFaktorenSliders = new HBox();
        hboxFaktorenSliders.setPadding(new Insets(15, 15, 15, 12));
        hboxFaktorenSliders.setSpacing(80);
        hboxFaktorenSliders.setStyle("-fx-background-color: #336699;");

        HBox hboxLabelsFaktoren = new HBox();
        hboxLabelsFaktoren.setPadding(new Insets(15, 15, 15, 12));
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

        Slider slider_amplitude_faktor = new Slider();
        slider_amplitude_faktor.setMin(1.0);
        slider_amplitude_faktor.setMax(8.0);
        slider_amplitude_faktor.setBlockIncrement(1.0);
        slider_amplitude_faktor.setValue(amplitude_faktor);

        Slider slider_frequenz_faktor = new Slider();
        slider_frequenz_faktor.setMin(1.0);
        slider_frequenz_faktor.setMax(8.0);
        slider_frequenz_faktor.setBlockIncrement(1.0);
        slider_frequenz_faktor.setValue(frequenz_faktor);


        amplitude_label_description = new Label("Amplitude");
        amplitude_label_description.setTextFill(Color.WHITE);
        amplitude_label_description.setFont(new Font("Arial", 20));
        amplitude_label_description.setPadding(new Insets(0, 0, 0, 5));

        amplitude_label_value = new Label(Double.toString(slider_amplitude.getValue()));
        amplitude_label_value.setTextFill(Color.WHITE);
        amplitude_label_value.setFont(new Font("Arial", 20));

        amplitude_faktor_label_desccriptor = new Label("Faktor Amplitude");
        amplitude_faktor_label_desccriptor.setTextFill(Color.WHITE);
        amplitude_faktor_label_desccriptor.setFont(new Font("Arial", 20));
        amplitude_faktor_label_desccriptor.setPadding(new Insets(0, 0, 0, 5));

        amplitude_faktor_label_value = new Label("1.0/" + Double.toString(slider_amplitude_faktor.getValue()));
        amplitude_faktor_label_value.setTextFill(Color.WHITE);
        amplitude_faktor_label_value.setFont(new Font("Arial", 20));

        frequenz_label_description = new Label("Frequenz");
        frequenz_label_description.setTextFill(Color.WHITE);
        frequenz_label_description.setFont(new Font("Arial", 20));
        frequenz_label_description.setPadding(new Insets(0, 0, 0, 80));

        frequenz_label_value = new Label(Double.toString(slider_frequenz.getValue()));
        frequenz_label_value.setTextFill(Color.WHITE);
        frequenz_label_value.setFont(new Font("Arial", 20));

        frequenz_faktor_label_descriptor = new Label("Faktor Frequenz");
        frequenz_faktor_label_descriptor.setTextFill(Color.WHITE);
        frequenz_faktor_label_descriptor.setFont(new Font("Arial", 20));
        frequenz_faktor_label_descriptor.setPadding(new Insets(0, 0, 0, 5));

        frequenz_faktor_label_value = new Label(Double.toString(slider_frequenz_faktor.getValue()));
        frequenz_faktor_label_value.setTextFill(Color.WHITE);
        frequenz_faktor_label_value.setFont(new Font("Arial", 20));

        hboxAmplitudeFrequenzSliders.getChildren().addAll(slider_amplitude, slider_frequenz);
        hboxLabelsAmplitudeFrequenz.getChildren().addAll(amplitude_label_description, amplitude_label_value,
                frequenz_label_description, frequenz_label_value);

        hboxFaktorenSliders.getChildren().addAll(slider_amplitude_faktor, slider_frequenz_faktor);
        hboxLabelsFaktoren.getChildren().addAll(amplitude_faktor_label_desccriptor, amplitude_faktor_label_value,
                frequenz_faktor_label_descriptor, frequenz_faktor_label_value);

        slider_amplitude_faktor.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                cursorX = 0;
                cursorY = 200;
                y = 0;
                i = 0;
                graph.getChildren().clear();
                amplitude_faktor = (double) newValue;
                try {
                    amplitude_faktor_label_value.setText("1.0/" + Double.toString(amplitude_faktor).substring(0, 4));
                } catch (StringIndexOutOfBoundsException e) {
                    amplitude_faktor_label_value.setText("1.0/" + Double.toString(amplitude_faktor));
                }
                i = 0;
                wave();
            }
        });

        slider_frequenz_faktor.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                cursorX = 0;
                cursorY = 200;
                y = 0;
                i = 0;
                graph.getChildren().clear();
                frequenz_faktor = (double) newValue;
                try {
                    frequenz_faktor_label_value.setText(Double.toString(frequenz_faktor).substring(0, 4));
                } catch (StringIndexOutOfBoundsException e) {
                    frequenz_faktor_label_value.setText(Double.toString(frequenz_faktor));
                }
                i = 0;
                wave();
            }
        });

        slider_amplitude.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                cursorX = 0;
                cursorY = 200;
                y = 0;
                i = 0;
                amplitude = (double) new_val;
                graph.getChildren().clear();
                try {
                    amplitude_label_value.setText(Double.toString(amplitude).substring(0, 4));
                } catch (StringIndexOutOfBoundsException e) {
                    amplitude_label_value.setText(Double.toString(amplitude));
                }
                i = 0;
                wave();
            }
        });

        slider_frequenz.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                cursorX = 0;
                cursorY = 200;
                y = 0;
                i=0;
                frequenz = (double) new_val;
                graph.getChildren().clear();


                try {
                    frequenz_label_value.setText(Double.toString(frequenz).substring(0, 4));
                } catch (StringIndexOutOfBoundsException e){
                    frequenz_label_value.setText(Double.toString(frequenz));
                }
                i=0;
                wave();
            }
        });

        myvbox.getChildren().add(hboxAmplitudeFrequenzSliders);
        myvbox.getChildren().add(hboxLabelsAmplitudeFrequenz);
        myvbox.getChildren().add(hboxFaktorenSliders);
        myvbox.getChildren().add(hboxLabelsFaktoren);
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

        amplitude = 8.0;
        frequenz = 4.0;
        frequenz_faktor = 1.0;
        amplitude_faktor = 1.0;
        i=0;
        wave();
    }
}