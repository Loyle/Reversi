package com.utbm.reversi.view;

import javax.swing.JButton;

import com.utbm.reversi.model.powers.Power;

@SuppressWarnings("serial")
public class PowerButton extends JButton {
	private Power power;
	public PowerButton() {
		this.power = null;
	}
	/**
	 * @return the power
	 */
	public Power getPower() {
		return power;
	}
	/**
	 * @param power the power to set
	 */
	public void setPower(Power power) {
		this.power = power;
	}
}
