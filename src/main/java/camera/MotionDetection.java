package camera;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MotionDetection extends Application implements WebcamMotionListener {

	public MotionDetection() {
		WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
		detector.setInertia(500);
		detector.addMotionListener(this);
		detector.start();
	}

	public static void main(String[] args) throws IOException {
		new MotionDetection();
		System.in.read();
	}

	boolean isPLaying = false;

	String bip = "brains.mp3";
	Media hit = new Media(new File(bip).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	
	public void motionDetected(WebcamMotionEvent wme) {
		System.out.println("Detected motions");
		double motionArea = wme.getArea();
		System.out.println(motionArea);
		if (motionArea > 25.00) {
			
				mediaPlayer.play();
		}
	}

	public void takeAPicture() {
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		try {
			String fileName = UUID.randomUUID().toString().replaceAll("-", "");
			ImageIO.write(webcam.getImage(), "PNG", new File("captures/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
