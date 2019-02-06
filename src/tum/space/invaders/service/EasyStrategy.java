package tum.space.invaders.service;

import javafx.scene.image.ImageView;

public class EasyStrategy implements Strategy {
	@Override
	public boolean collision(ImageView e, ImageView rocket) {
		return e.getX() - 50 < rocket.getX() && e.getX() + 50 > rocket.getX();
	}
}
