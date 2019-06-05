package com.utbm.reversi.model.cells;

public class Cells {
	private int value;
	private Pawn pawn;
	
	public Cells() {
		this.value = 0;
		this.pawn = null;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Pawn getPawn() {
		return this.pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
}
