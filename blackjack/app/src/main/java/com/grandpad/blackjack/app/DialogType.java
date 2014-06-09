package com.grandpad.blackjack.app;

public enum DialogType {
	ALERT(0),
	CONFIRM(1);
	
	private final int	value;
	
	private DialogType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
