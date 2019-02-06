/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tum.space.invaders.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 *
 * @author Zayar Thant
 */
public class MenuView {
	// ActionController actionController;
	@FXML
	private Button start;
	@FXML
	private Button highScore;
	@FXML
	private Button quit;

	@FXML
	public void initialize() {
		start.setOnMouseClicked(e -> onStartClick(e));
		highScore.setOnMouseClicked(e -> onHighScoreClick(e));
		quit.setOnMouseClicked(e -> onQuitClick(e));
	}

	private void onStartClick(MouseEvent e) {
		App.show(getClass().getResource("./level.fxml"));
	}

	private void onHighScoreClick(MouseEvent e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("HighScore");
		alert.setHeaderText("Comming Soon...");
		alert.show();
	}

	private void onQuitClick(MouseEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Are you sure?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
			System.exit(0);
		else
			return;
	}
}
