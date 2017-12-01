package com.lmig.gfc.Blackjack.models;

public class NumberCard extends Card{
	
	private int value;
	
	public NumberCard(Suits suit, int value) {
		super(suit);
		this.value = value;
	}

	@Override
	public String getFace() {
		return String.valueOf(value);
	}
	
	@Override
	public int getValue() {
		return value;
	}
}
