package com.utbm.reversi.model;

public class Cells {
	private int value;
	private Pawn pawn;
	
	public Cells() {
		this.value = 0;
		this.pawn = null;
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
