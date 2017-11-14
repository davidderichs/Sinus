package UE02A02TuermeVonHanoi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Untenstehend finden Sie den Quelltext für das Spiel „Die Türme von Hanoi“. 
 * Die einzelnen Spielzüge werden noch über eine Konsolenausgabe angezeigt. 
 * Erstellen Sie eine JavaFXAnwendung, welche die Spielzüge für 6 Scheiben grafisch darstellen kann. 
 * Schreiben Sie für die Eigenschaften und Zustände der Scheibe eine eigene Klasse.
 * 
 * 
 * Die Bedienung erfolgt zunächst nur manuell. 
 * Über eine Combobox wird eine Scheibe ausgewählt und über eine zweite Combobox festgelegt auf welchem Stapel sie abgelegt wird.
 * Nach Betätigung eines Buttons wird die Scheibe verschoben. Später soll dann in einer
 * Erweiterung der Algorithmus eingefügt werden * 
 * */


public class GuiTuermeVonHanoi extends Application{
	private static VBox root;
	private static  HBox navigationHBox;
	private static  HBox stapelHBox;
	static BorderPane controlLayout;
	static VBox showControls;
	static ComboBox<Integer> comboScheibe;
	static ComboBox<String> comboStapel;
	static Button buttonSubmit;
	
	static private HBox hBoxPos4;
	static private Button aPos4;
	static private Button bPos4;
	static private Button cPos4;
	
	static private HBox hBoxPos3;
	static private Button aPos3;
	static private Button bPos3;
	static private Button cPos3;
	
	static private HBox hBoxPos2;
	static private Button aPos2;
	static private Button bPos2;
	static private Button cPos2;
	
	static private HBox hBoxPos1;
	static private Button aPos1;
	static private Button bPos1;
	static private Button cPos1;	
	
	static private Button bezeichnerStapel1;
	static private Button bezeichnerStapel2;
	static private Button bezeichnerStapel3;
	
	static private HBox hBoxMagicButton;
	static private Button magicButton;
	static private Button resetButton;
	
	static ArrayList<HanoiScheibe> hanoiScheiben;
	static ArrayList<HanoiStapel> hanoiStapel;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		root = new VBox(10);			
		
		legeDatenFest();		
		konfiguriereComboBoxen();
		zeichnePositionsLayout();
		faerbePositionenEin();
		bezeichneStapel();
		fuegeMagicUndResetButtonDazu();

		Scene scene = new Scene(root, 600, 400);
		primaryStage.setTitle("Tuerme von Hanoi");
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	private void fuegeMagicUndResetButtonDazu() {
		hBoxMagicButton = new HBox();
		hBoxMagicButton.setSpacing(10.0);
		magicButton = new Button();
		magicButton.setText("let the magic happen");
		magicButton.setMinWidth(190.0);			
		magicButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					hanoi(4, "A", "B", "C");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});				
		resetButton = new Button();
		resetButton.setText("RESET");
		resetButton.setMinWidth(190.0);
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				legeDatenFest();
				entferneFarbenUndBezeichner();
				faerbePositionenEin();
			}			
		});			
		hBoxMagicButton.getChildren().addAll(magicButton, resetButton);		
		root.getChildren().add(hBoxMagicButton);
	}

	private static void zeichnePositionsLayout() {
		
		hBoxPos4 = new HBox();
		hBoxPos4.setSpacing(10.0);
		aPos4 = new Button();
		aPos4.setMinWidth(190.0);
		aPos4.setStyle("-fx-background-color: "+ "white" +";");
		bPos4 = new Button();
		bPos4.setMinWidth(190.0);
		bPos4.setStyle("-fx-background-color: "+ "white" +";");
		cPos4 = new Button();
		cPos4.setMinWidth(190.0);
		cPos4.setStyle("-fx-background-color: "+ "white" +";");
		hBoxPos4.getChildren().addAll(aPos4, bPos4, cPos4);
		
		hBoxPos3 = new HBox();
		hBoxPos3.setSpacing(10.0);
		aPos3 = new Button();
		aPos3.setMinWidth(190.0);
		aPos3.setStyle("-fx-background-color: "+ "white" +";");
		bPos3 = new Button();
		bPos3.setMinWidth(190.0);
		bPos3.setStyle("-fx-background-color: "+ "white" +";");
		cPos3 = new Button();
		cPos3.setMinWidth(190.0);
		cPos3.setStyle("-fx-background-color: "+ "white" +";");
		hBoxPos3.getChildren().addAll(aPos3, bPos3, cPos3);
		
		hBoxPos2 = new HBox();
		hBoxPos2.setSpacing(10.0);
		aPos2 = new Button();
		aPos2.setMinWidth(190.0);
		aPos2.setStyle("-fx-background-color: "+ "white" +";");
		bPos2 = new Button();
		bPos2.setMinWidth(190.0);
		bPos2.setStyle("-fx-background-color: "+ "white" +";");
		cPos2 = new Button();
		cPos2.setMinWidth(190.0);
		cPos2.setStyle("-fx-background-color: "+ "white" +";");
		hBoxPos2.getChildren().addAll(aPos2, bPos2, cPos2);
		
		hBoxPos1 = new HBox();
		hBoxPos1.setSpacing(10.0);
		aPos1 = new Button();
		aPos1.setMinWidth(190.0);
		aPos1.setStyle("-fx-background-color: "+ "white" +";");
		bPos1 = new Button();
		bPos1.setMinWidth(190.0);
		bPos1.setStyle("-fx-background-color: "+ "white" +";");
		cPos1 = new Button();
		cPos1.setMinWidth(190.0);
		cPos1.setStyle("-fx-background-color: "+ "white" +";");
		hBoxPos1.getChildren().addAll(aPos1, bPos1, cPos1);
		
		root.getChildren().addAll(hBoxPos4, hBoxPos3, hBoxPos2, hBoxPos1);
	}
	
	private static void entferneFarbenUndBezeichner() {
		aPos4.setStyle("-fx-background-color: "+ "white" +";");
		bPos4.setStyle("-fx-background-color: "+ "white" +";");
		cPos4.setStyle("-fx-background-color: "+ "white" +";");
		aPos3.setStyle("-fx-background-color: "+ "white" +";");
		bPos3.setStyle("-fx-background-color: "+ "white" +";");
		cPos3.setStyle("-fx-background-color: "+ "white" +";");
		aPos2.setStyle("-fx-background-color: "+ "white" +";");
		bPos2.setStyle("-fx-background-color: "+ "white" +";");
		cPos2.setStyle("-fx-background-color: "+ "white" +";");
		aPos1.setStyle("-fx-background-color: "+ "white" +";");
		bPos1.setStyle("-fx-background-color: "+ "white" +";");
		cPos1.setStyle("-fx-background-color: "+ "white" +";");
		
		aPos4.setText("");
		bPos4.setText("");
		cPos4.setText("");
		aPos3.setText("");
		bPos3.setText("");
		cPos3.setText("");
		aPos2.setText("");
		bPos2.setText("");
		cPos2.setText("");
		aPos1.setText("");
		bPos1.setText("");
		cPos1.setText("");
	}

	
	private static void faerbePositionenEin() {	
		for (HanoiScheibe scheibe : hanoiScheiben) {
			if (scheibe.stapel=="A") {
				if (scheibe.position==1) {
					aPos1.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					aPos1.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==2) {
					aPos2.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					aPos2.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==3) {
					aPos3.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					aPos3.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==4) {
					aPos4.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					aPos4.setText(Integer.toString(scheibe.scheibenNr));
				}
			} else if (scheibe.stapel=="B") {
				if (scheibe.position==1) {
					bPos1.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					bPos1.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==2) {
					bPos2.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					bPos2.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==3) {
					bPos3.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					bPos3.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==4) {
					bPos4.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					bPos4.setText(Integer.toString(scheibe.scheibenNr));
				}
			} else if (scheibe.stapel=="C") {
				if (scheibe.position==1) {
					cPos1.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					cPos1.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==2) {
					cPos2.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					cPos2.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==3) {
					cPos3.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					cPos3.setText(Integer.toString(scheibe.scheibenNr));
				}
				if (scheibe.position==4) {
					cPos4.setStyle("-fx-background-color: "+ scheibe.hexColor +";");
					cPos4.setText(Integer.toString(scheibe.scheibenNr));
				}
			}
		}
	}
	
	private void bezeichneStapel() {	
		
		stapelHBox = new HBox();
		stapelHBox.setSpacing(10.0);
		bezeichnerStapel1 = new Button();
		bezeichnerStapel1.setMinWidth(190.0);
		bezeichnerStapel1.setText("Platz 1");
		bezeichnerStapel2 = new Button();
		bezeichnerStapel2.setMinWidth(190.0);
		bezeichnerStapel2.setText("Hilfsplatz");
		bezeichnerStapel3 = new Button();
		bezeichnerStapel3.setMinWidth(190.0);
		bezeichnerStapel3.setText("Platz 2");
		stapelHBox.getChildren().addAll(bezeichnerStapel1, bezeichnerStapel2, bezeichnerStapel3);
		
		
		
		stapelHBox = new HBox(1);
		stapelHBox.setPrefWidth(600);
		stapelHBox.setSpacing(10.0);
		for (HanoiStapel stapel : hanoiStapel) {
			Label newLabel = new Label(stapel.stapelName);
			newLabel.setMinWidth(190.0);
			newLabel.setPadding(new Insets (0.0, 0.0, 0.0, 80.0));
			stapelHBox.getChildren().add(newLabel);
		}
		root.getChildren().add(stapelHBox);
		
	}
	

	private void konfiguriereComboBoxen() {
		
		navigationHBox = new HBox(2);	
		navigationHBox.setSpacing(10.0);
		
		comboScheibe = new ComboBox<Integer>();
		for (HanoiScheibe scheibe : hanoiScheiben){
			comboScheibe.getItems().add(scheibe.scheibenNr);
		}
		comboScheibe.getSelectionModel().selectFirst();
		comboScheibe.setMinWidth(190.0);
		
		comboStapel = new ComboBox<String>();
		for (HanoiStapel stapel : hanoiStapel) {
			comboStapel.getItems().add(stapel.stapelName);
		}
		comboStapel.getSelectionModel().selectFirst();	
		comboStapel.setMinWidth(190.0);
		
		buttonSubmit = new Button();
		buttonSubmit.setText("Fuehre Zug aus");
		buttonSubmit.setMinWidth(190.0);
		
		buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				verschiebe(comboScheibe.getValue(), comboStapel.getValue());
			}
			
		});
		
		
		navigationHBox.getChildren().addAll(comboScheibe, comboStapel, buttonSubmit);	
		root.getChildren().add(navigationHBox);
	}

	private void legeDatenFest() {
		hanoiScheiben = new ArrayList<HanoiScheibe>();
		hanoiScheiben.add(new HanoiScheibe(1, "A", 1, "red"));
		hanoiScheiben.add(new HanoiScheibe(2, "A", 2, "green"));
		hanoiScheiben.add(new HanoiScheibe(3, "A", 3, "blue"));
		hanoiScheiben.add(new HanoiScheibe(4, "A", 4, "yellow"));
		
		hanoiStapel = new ArrayList<HanoiStapel>();
		hanoiStapel.add(new HanoiStapel("A", 4));
		hanoiStapel.add(new HanoiStapel("B", 4));
		hanoiStapel.add(new HanoiStapel("C", 4));	
	}
	
	private static HanoiScheibe findeScheibe(int nummer) {
		Iterator<HanoiScheibe> it = GuiTuermeVonHanoi.hanoiScheiben.iterator();
		while (it.hasNext()) {
			HanoiScheibe scheibe = it.next();
			if (scheibe.scheibenNr==nummer) return scheibe;
		}
		return null;
	}
	
	private static void verschiebe(int nummer, String nach){
		if (zugMoeglich(nummer, nach)) {
			HanoiScheibe scheibe = findeScheibe(nummer);		
			if (scheibe != null) {
				scheibe.stapel=nach;			
				scheibe.position=0;
				scheibe.position=findeFreiePosition(nach);
			}
			entferneFarbenUndBezeichner();
			faerbePositionenEin();			
		}

	}
	
	private static boolean zugMoeglich(int nummer, String nach) {
		HanoiScheibe scheibe = findeScheibe(nummer);
		int position = scheibe.position;
		String stapel = scheibe.stapel;
		
		Iterator<HanoiScheibe> it = GuiTuermeVonHanoi.hanoiScheiben.iterator();
		while (it.hasNext()) {
			scheibe = it.next();
			if (scheibe.scheibenNr!=nummer && scheibe.stapel==stapel && scheibe.position>position) return false;
		}
		return true;
	}
	
	public static int findeFreiePosition(String stapel) {
		int position = 0;
		for (HanoiScheibe scheibe : hanoiScheiben) {
			if (scheibe.stapel==stapel && scheibe.position>position){
				position = scheibe.position;
			}
		}
		position++;
		return position;
	}
	
	
	private static void ziehe_scheibe(int nummer, String von, String nach){
        System.out.println("Scheibe " + nummer + " wird von " + von +
                " nach " + nach + " verschoben ");
		verschiebe(nummer, nach);
    }
	
    private static void hanoi(int N, String platz1, String hilfsplatz, String platz2) throws InterruptedException{    	
        if (N == 1) {
            ziehe_scheibe(N, platz1, platz2);
        } else {
            hanoi(N-1, platz1, platz2, hilfsplatz);
            ziehe_scheibe(N, platz1, platz2);
            hanoi(N-1, hilfsplatz, platz1, platz2);
        }
    }	
}
