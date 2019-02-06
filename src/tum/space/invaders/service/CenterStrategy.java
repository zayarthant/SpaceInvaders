package tum.space.invaders.service;

import javafx.scene.image.ImageView;

public class CenterStrategy implements Strategy {
	@Override
	public boolean collision(ImageView e, ImageView rocket) {
		return e.getX() - 10 < rocket.getX() && e.getX() + 10 > rocket.getX();
	}
}
