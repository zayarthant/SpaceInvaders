package tum.space.invaders.service;

import javafx.scene.image.ImageView;

public interface Strategy {
	public boolean collision(ImageView e, ImageView rocket);
}
