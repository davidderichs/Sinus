package multithreading;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TrafficLightTask extends Application {
	Circle redlight, yellowlight, greenlight;
	Thread thread;

	void weiter(int state) {	
		System.out.println("weiter state: "+state);
				
		switch(state){
			case 0:
				redlight.setFill(Color.RED);
				yellowlight.setFill(Color.GREY);
				greenlight.setFill(Color.GREY);
				break;
			case 1:
				redlight.setFill(Color.RED);
				yellowlight.setFill(Color.YELLOW);
				greenlight.setFill(Color.GREY);
				break;
			case 2:
				redlight.setFill(Color.GREY);
				yellowlight.setFill(Color.GREY);
				greenlight.setFill(Color.LAWNGREEN);
				break;
			case 3:
				redlight.setFill(Color.GREY);
				yellowlight.setFill(Color.YELLOW);
				greenlight.setFill(Color.GREY);
				break;
		}		 
	    
	}
	
	void update(int state) {		
		//System.out.println("update");
		final int fState = state;
		// UI updaten
        Platform.runLater(new Runnable() {        	
        	@Override
            public void run() {
                // entsprechende UI Komponente updaten
                weiter(fState);                
            }
        	
        });
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        state++;
	    if(state > 3)
			state = 0;       
		update(state);
	}
	
	public void start(Stage primaryStage) {
		redlight = new Circle(35, Color.RED);
		yellowlight = new Circle(35, Color.YELLOW);
		greenlight = new Circle(35, Color.GREEN);
		
		yellowlight.setFill(Color.GREY);
		greenlight.setFill(Color.GREY);
		VBox lightbox = new VBox(10, redlight, yellowlight, greenlight);
		lightbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(lightbox, 50, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Task<Void> t = new Task<Void>()  {			
			public Void call() {    
	        	 update(0);	        	 
	        	 return null;
	         }
		};
		thread = new Thread(t);
		thread.start();
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		System.out.println("Exitus");
		thread.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
