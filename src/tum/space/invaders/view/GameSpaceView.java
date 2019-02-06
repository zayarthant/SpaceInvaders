/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tum.space.invaders.view;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.R;
import tum.space.invaders.entity.Invater;
import tum.space.invaders.entity.Level;
import tum.space.invaders.entity.PlayerAccount;
import tum.space.invaders.entity.Rocket;
import tum.space.invaders.service.PlayerAccountService;

/**
 *
 * @author Zayar Thant
 */
public class GameSpaceView {

	private Pane area;
	private Label timer;
	private Label score;
	private boolean isGameAlive = true;

	private ImageView spaceShip;
	private List<ImageView> invaters = new LinkedList<>();
	private int position = 420;

	private int second;

	private PlayerAccount playerAccount = PlayerAccountService.getPlayerAccount();

	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("./gameSpace.fxml"));
		Scene scene = new Scene(root, R.Dimans.screenWidth, R.Dimans.screenHeight);
		area = (Pane) scene.lookup("#Pane");
		timer = (Label) scene.lookup("#timer");
		score = (Label) scene.lookup("#score");
		addInvater(R.Drawable.evil);
		addSpaceShip(R.Drawable.spaceship);
		scene.setOnKeyPressed(e -> onKeyPressed(e));
		stage.setScene(scene);
		stage.setOnCloseRequest(e -> System.exit(0));
		runTimer();
	}

	private void runTimer() {
		new Thread(new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				while (isGameAlive) {
					second++;
					Thread.sleep(1000);
					Platform.runLater(() -> {
						timer.setText("Time - " + timeFormatter(second));
						timer.toFront();
						score.setText("Score - " + playerAccount.getCurrentScore());
						score.toFront();
						if (invaters.size() <= 0) {
							isGameAlive = false;
							App.show(getClass().getResource("./gameEnd.fxml"));
							playerAccount.setSecond(second);
						}
					});
				}
				return null;
			}
		}).start();
	}

	private String timeFormatter(int seconds) {
		int minute = seconds / 60;
		int second = seconds % 60;
		return String.format("%02d:%02d", minute, second);
	}

	private void addSpaceShip(String url) {
		spaceShip = new ImageView(url);
		spaceShip.setFitWidth(100);
		spaceShip.setFitHeight(120);
		spaceShip.setX(420);
		spaceShip.setY(350);
		area.getChildren().add(spaceShip);
	}

	public void addInvater(String url) {
		Level level = PlayerAccountService.getPlayerAccount().getLevel();
		for (int row = 0; row < level.getRow(); row++)
			for (int col = 0; col < level.getCol(); col++) {
				ImageView invater = new ImageView(url);
				new Invater(invater, area, row, col);
				invaters.add(invater);
			}
	}

	private void onKeyPressed(KeyEvent e) {
		switch (e.getCode()) {
		case D:
			moveRight();
			break;
		case A:
			moveLeft();
			break;
		case SPACE:
			fireRocket();
			break;
		default:
			break;
		}
	}

	private void moveLeft() {
		if (position > -40)
			position -= 20;
		spaceShip.setX(position);
	}

	private void moveRight() {
		if (position < 800)
			position += 20;
		spaceShip.setX(position);
	}

	private void fireRocket() {
		new Rocket(new ImageView(R.Drawable.rocket), area, position, invaters, playerAccount.getLevel().getStrategy());
	}
}
