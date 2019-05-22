package com.utbm.reversi.model;

public class Cells {
	private int value;
	private Pawn pawn;
	private boolean hasTrap;
	private boolean hasBomb;
	private boolean isObstacle;
	
	public Cells() {
		this.value = 0;
		this.pawn = null;
		this.hasBomb = false;
		this.hasTrap = false;
		this.isObstacle = false;
	}
	
	/**
	 * @return
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the hasBomb
	 */
	public boolean hasBomb() {
		return hasBomb;
	}

	/**
	 * @param the hasBomb
	 */
	public void setBomb(boolean hasBomb) {
		this.hasBomb = hasBomb;
	}

	/**
	 * @return the hasTrap
	 */
	public boolean hasTrap() {
		return hasTrap;
	}

	/**
	 * @param the hasTrap
	 */
	public void setTrap(boolean hasTrap) {
		this.hasTrap = hasTrap;
	}
	
	/**
	 * @return
	 */
	public boolean isObstacle() {
		return isObstacle;
	}

	/**
	 * @param isObstacle
	 */
	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}
	
	/**
	 * @return
	 */
	public Pawn getPawn() {
		return this.pawn;
	}
	/**
	 * @param pawn
	 */
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
}
