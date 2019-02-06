package tum.space.invaders.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.R;

/**
 * FXML Controller class
 *
 * @author Zayar Thant
 */
public class App extends Application {

	private static Stage stage;
	private static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage rootStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("./welcome.fxml"));
		scene = new Scene(root, R.Dimans.screenWidth, R.Dimans.screenHeight);
		rootStage.setScene(scene);
		rootStage.setResizable(false);
		rootStage.show();
		stage = rootStage;
		playBackgroundMusic();
	}

	private void playBackgroundMusic() {
		Media sound = new Media(new File(R.SoundEffect.background).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
	}

	public static void startGame() {
		try {
			new GameSpaceView().start(stage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void show(URL file) {
		Parent root;
		try {
			root = FXMLLoader.load(file);
			scene = new Scene(root, R.Dimans.screenWidth, R.Dimans.screenHeight);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Scene getScene() {
		return scene;
	}

}
