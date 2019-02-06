/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tum.space.invaders.entity;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Zayar Thant
 */
public class Invater {

	private boolean toggle;

	public Invater(ImageView evil, Pane area, double y, double x) {
		area.getChildren().add(evil);
		evil.setPreserveRatio(true);
		evil.setX(x * 100);
		evil.setY(y * 100);
		evil.setFitWidth(100);
		KeyFrame keyFrame = new KeyFrame(new Duration(100), e -> {
			double position = calculateMovePosition(evil.getX());
			evil.setX(position);
		});
		Timeline timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public double calculateMovePosition(double value) {
		if (!toggle && value < 770)
			value += 20;
		else
			toggle = true;
		if (toggle && value > 0)
			value -= 20;
		else
			toggle = false;
		return value;
	}

}
