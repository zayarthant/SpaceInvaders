/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tum.space.invaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tum.space.invaders.entity.Level;
import tum.space.invaders.service.CenterStrategy;
import tum.space.invaders.service.EasyStrategy;
import tum.space.invaders.service.PlayerAccountService;
import tum.space.invaders.service.Strategy;

/**
 *
 * @author Zayar Thant
 */
public class LevelView {
	// ActionController actionController;

	@FXML
	private Label one, two, three, four, five, six, seven, eight, nine;

	@FXML
	public void initialize() {
		one.setOnMouseClicked(e -> onClick(e));
		two.setOnMouseClicked(e -> onClick(e));
		three.setOnMouseClicked(e -> onClick(e));
		four.setOnMouseClicked(e -> onClick(e));
		five.setOnMouseClicked(e -> onClick(e));
		six.setOnMouseClicked(e -> onClick(e));
		seven.setOnMouseClicked(e -> onClick(e));
		eight.setOnMouseClicked(e -> onClick(e));
		nine.setOnMouseClicked(e -> onClick(e));
	}

	private void onClick(MouseEvent e) {
		String selectedLabel = ((Label) e.getSource()).getText();
		Strategy strategy;
		int row;
		int levelNumber = Integer.parseInt(selectedLabel);
		if (levelNumber < 5) {
			row = 2;
			strategy = new EasyStrategy();
		} else {
			row = 3;
			strategy = new CenterStrategy();
		}
		Level level = new Level(selectedLabel, strategy, row, 5 + levelNumber);
		PlayerAccountService.getPlayerAccount().setLevel(level);
		App.startGame();
	}

}
