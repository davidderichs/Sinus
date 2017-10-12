package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioTrack;
import javafx.stage.Stage;

public class ToneGenerator extends Application {

	Task<Void> t;
	static SourceDataLine audioTrack;
	static FirstLineService service;
	
    static int sr = 44100;
    static boolean isRunning = false;
    
    
    static double baseFrequency = 440.f;
	static double volume = 32000;

	
    static double fr = 440.0f;

   
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/audio/ToneGeneratorView.fxml")); 
        primaryStage.setTitle("FXML-Beispiel");
        primaryStage.setScene(new Scene(root, 600, 400 ));
        primaryStage.show();
        
        service = new FirstLineService();
//        service.setUrl("http://google.com");
//        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//
//            @Override
//            public void handle(WorkerStateEvent t) {
//                System.out.println("done:" + t.getSource().getValue());
//            }
//        });
        service.start();
        
        onCreate();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
	static void generateTone() {
		if(isRunning)
            return;
        else
            isRunning = true;
		
		int buffsize = 1024;
        try { 
        	AudioFormat audioFormat = new AudioFormat(44100f, 16, 1, true, true);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			audioTrack = (SourceDataLine) AudioSystem.getLine(info);
			audioTrack.open(audioFormat);
			audioTrack.start();
	        
	        byte samples[] = new byte[buffsize];	      
	        double twopi = 8.*Math.atan(1.);	       
	        double ph = 0.0;
	      
	        // synthesis loop
	        while(isRunning){
	    	   for(int i=0; i < buffsize / 2; i++){
	    		   short y = (short) (service.getVolume()*Math.sin(ph));
	    		   samples[2 * i + 1] =  (byte)y; 
	    		   samples[2 * i ] = (byte)(y >> 8);
	    		   fr = baseFrequency * Math.pow(2., service.getNote()/12.);
	    		   ph += twopi*fr/sr;
	    		   
	    		   // UI updaten
	    	       Platform.runLater(new Runnable() {
	    	            @Override
	    	            public void run() {
	    	                // entsprechende UI Komponente updaten
	    	                
	    	            }
	    	        });
	    		   
	    	   }
	    	   audioTrack.write(samples, 0, buffsize);
	    	   //System.out.println("Audiopuffer erhält Stream");
	        }	
	        audioTrack.drain();
		    audioTrack.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
       
    }
 
    void stopPlaying() {    
        
    }
		
	
    
    void onCreate()  {       
        // start a new thread to synthesise audio
        t = new Task<Void>()  {
        	public Void call() {    
        		generateTone();
        		return null;
         	}
        };
   		new Thread(t).start();
   		//t.start();
    }
    
    protected void onStop() {
        isRunning = false;
    }

    public void Stop() {
        service.setVolume(0.);
    }

    public void PlayA3() {
    	System.out.println("A3");
    	service.setVolume(volume);
		service.setNote(-12);
	}
	
	public void PlayASharp3() {
		service.setVolume(volume);
        service.setNote(-11);
	}
	
	public void PlayB3() {
		System.out.println("B3");
		service.setVolume(volume);
        service.setNote(-10);
	}
	
	public void PlayC4() {
		service.setVolume(volume);
        service.setNote(-9);
	}
	
	public void PlayCSharp4() {
		service.setVolume(volume);
        service.setNote(-8);
	}
	
	public void PlayD4() {
		service.setVolume(volume);
        service.setNote(-7);
	}
	
	public void PlayDSharp4() {
		service.setVolume(volume);
        service.setNote(-6);
	}

    public void PlayE4() {
    	service.setVolume(volume);
        service.setNote(-5);
    }

    public void PlayF4() {
    	service.setVolume(volume);
        service.setNote(-4);
    }

    public void PlayFSharp4() {
    	service.setVolume(volume);
        service.setNote(-3);
    }

    public void PlayG4() {
    	service.setVolume(volume);
        service.setNote(-2);
    }

    public void PlayGSharp4() {
    	service.setVolume(volume);
        service.setNote(-1);
    }

    public void PlayA4() {
    	service.setVolume(volume);
        service.setNote(0);
    }


    public void KeyPressed(KeyEvent event) {
    	String key = event.getText();
    	switch(key) {
    		case "a":
    			PlayA3();
    			break;
    		case "s":
    			PlayB3();
    			break;
    		case "d":
    			PlayC4();
    			break;
    		case "f":
    			PlayD4();
    			break;
    		case "g":
    			PlayE4();
    			break;
    		case "h":
    			PlayF4();
    			break;
    		case "j":
    			PlayG4();
    			break;
    		case "k":
    			PlayA4();
    			break;
    	
    	}
    }


    private static class FirstLineService extends Service<String> {

    	private IntegerProperty note = new SimpleIntegerProperty();
    	private DoubleProperty volume = new SimpleDoubleProperty();
    	
    	public final void setNote(Integer value) {
    		note.set(value);
    	}
    	
    	public final Integer getNote(){
    		return note.get();
    	}
    	
    	public final void setVolume(Double value) {
    		volume.set(value);
    	}
    	
    	public final Double getVolume() {
    		return volume.get();
    	}


        @Override
        protected Task<String> createTask() {
        	return new Task<String>()  {
        		protected String call() {    
        		generateTone();
        		return null;
         	}
        };
//        protected Task<String> createTask() {
//            return new Task<String>() {
//                @Override
//                protected String call() {
//                     
//                    }
//                }
//        };
    
        }
    }
}