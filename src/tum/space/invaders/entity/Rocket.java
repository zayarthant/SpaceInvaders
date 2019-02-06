package tum.space.invaders.entity;

import java.io.File;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import resources.R;
import tum.space.invaders.service.PlayerAccountService;
import tum.space.invaders.service.Strategy;

public class Rocket {

	private ImageView rocket;
	private Pane area;
	private int position;
	private List<ImageView> invaters;
	private Strategy strategy;

	private Timeline timeline = null;

	public Rocket(ImageView rocket, Pane area, int position, List<ImageView> invaters, Strategy strategy) {
		if (null == rocket || null == area)
			return;
		this.rocket = rocket;
		this.area = area;
		this.position = position;
		this.invaters = invaters;
		this.strategy = strategy;
		postConstruct();
	}

	private void postConstruct() {
		area.getChildren().add(rocket);
		rocket.setFitWidth(32);
		rocket.setFitHeight(75);
		rocket.setX(position + 35);
		rocket.setY(300);
		Duration duration = new Duration(10);
		KeyFrame keyFrame = new KeyFrame(duration, e -> {
			rocket.setY(rocket.getY() - 5);
			onRocketPath();
		});
		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		playSound(R.SoundEffect.fire, 0.2);
	}

	private void onRocketPath() {
		if (rocket.getY() < -10 && null != timeline)
			rocketExplore();
		else
			crushInvaters();
	}

	private void rocketExplore() {
		area.getChildren().remove(rocket);
		timeline.stop();
	}

	private void crushInvaters() {
		for (ImageView e : invaters) {
			if (e.getY() > rocket.getY())
				if (strategy.collision(e, rocket)) {
					area.getChildren().remove(e);
					invaters.remove(e);
					rocketExplore();
					playSound(R.SoundEffect.explore, 1);
					int score = PlayerAccountService.getPlayerAccount().getCurrentScore() + 100;
					PlayerAccountService.getPlayerAccount().setCurrentScore(score);
					return;
				}
		}
	}

	private void playSound(String clip, double volume) {
		AudioClip plonkSound = new AudioClip(new File(clip).toURI().toString());
		plonkSound.setVolume(volume);
		plonkSound.play();
	}

	public boolean isComplete() {
		if (invaters.size() == 0)
			return true;
		else
			return false;
	}

}
