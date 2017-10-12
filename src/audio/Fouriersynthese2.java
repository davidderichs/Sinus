package audio;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Fouriersynthese2 extends Application {

	private Group root;	
	int cursorX = 0;
	int cursorY = 200;
	
	
	void playSound(byte[] sData, int size ) {		
	    AudioFormat audioFormat;
	    SourceDataLine sourceLine;	    
	    
	    try {	    	
	    	audioFormat = new AudioFormat(44100f, 16, 1, true, true);
		    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		    sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);			
		    sourceLine.start();
		    for(int i = 0; i < 500; i++)
		    	sourceLine.write(sData, 0, size);		    
		    sourceLine.drain();
	        sourceLine.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	
	
	public void wave() {
		double y;
		byte[] abData = new byte[1200];
		
		for (int i = 0; i < 600; i++) {
			// Rechteck:
			double amp = 16000;
			String waveform = "Dreieck";
//			String waveform = "Rechteck";
			y = 0;
			if(waveform.equals("Rechteck")){
				for(int j = 0; j < 80; j++) {
					y += (amp/(double)(2 *j + 1)) * Math.sin((2 *j + 1) * 4 * Math.PI * i / 600);
					
				}
			}
			// Dreieck:
			if(waveform.equals("Dreieck")){
				for(int j = 0; j < 4; j++) {
					double a = (amp/(double)((2 *j + 1) * (2 *j + 1))) * Math.pow(-1, j) ; 
					y += a * Math.sin((2 *j + 1) * 4 * Math.PI * i / 600);
					
				}
			}
			short sy = (short)y;
			drawLine(i, sy * 100 / 32768);
			// Sichern der Daten in einem Bytepuffer:
			abData[2 * i + 1] =  (byte)sy; 
			abData[2 * i ] = (byte)(sy >> 8);
			
		}
		
		playSound(abData, 1200);
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

	

	void drawLine(int x, int y) {
		int endX =  x;
		int endY =  200 - y;
		Line line = new Line(cursorX, cursorY, endX , endY);
		cursorX = endX;
		cursorY = endY;
		line.setStroke(Color.RED);
		root.getChildren().add(line);	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		root = new Group();
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scribble");
		
		Rectangle panel = new Rectangle(600, 400, Color.WHITESMOKE);
		root.getChildren().add(panel);
		primaryStage.show();		
	
		wave();
	}
}
