package tum.space.invaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tum.space.invaders.service.PlayerAccountService;

public class WelcomeView {

	@FXML
	private Button submit;
	@FXML
	private TextField name;
	@FXML
	private Label error;

	@FXML
	public void initialize() {
		submit.setOnMouseClicked(e -> onSubmitClick(e));
	}

	private void onSubmitClick(MouseEvent e) {
		if (!validate())
			return;
		PlayerAccountService.getPlayerAccount().setName(name.getText());
		App.show(getClass().getResource("./menu.fxml"));
	}

	private boolean validate() {
		if (name.getText().isEmpty()) {
			name.getStyleClass().add("error");
			error.setText("Name is require!");
			return false;
		}
		name.getStyleClass().remove("error");
		error.setText("");
		return true;
	}

}
