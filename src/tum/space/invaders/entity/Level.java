package tum.space.invaders.entity;

import tum.space.invaders.service.Strategy;

/**
 *
 * @author Zarowar Thant
 */
public class Level {

	private String levelName;
	private Strategy strategy;
	private int row;
	private int col;

	public Level(String levelName, Strategy strategy, int row, int col) {
		super();
		this.levelName = levelName;
		this.strategy = strategy;
		this.row = row;
		this.col = col;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
