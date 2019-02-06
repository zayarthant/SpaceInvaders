/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tum.space.invaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tum.space.invaders.entity.PlayerAccount;
import tum.space.invaders.service.PlayerAccountService;

/**
 *
 * @author Zayar Thant
 */
public class GameEndView {

	@FXML
	private Label score;

	@FXML
	public void initialize() {
		PlayerAccount playerAccount = PlayerAccountService.getPlayerAccount();
		score.setText(playerAccount.getCurrentScore() + "");
	}

	public void onButtonClick() {
		PlayerAccountService.getPlayerAccount().setCurrentScore(0);
		App.show(getClass().getResource("./menu.fxml"));
	}

}
