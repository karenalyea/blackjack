package com.lmig.gfc.Blackjack.models;

import java.util.LinkedList;
import java.util.List;

public class Hand {

	// can be arraylist or linklist
	private LinkedList<Card> cards;

	public Hand() {
		cards = new LinkedList<Card>();
	}

	// has ability to hold cards, but doesn't currently have any

	public void accept(Card card) {
		cards.add(card);
	}
	// list method guarantees that cards will be returned

	// can use generic 'list' rather than linkedlist or arraylist
	public List<Card> getCards() {
		return cards;
	}

	public int getTotal() {
		int sum = 0;
		int aces = 0;
		for (Card card : cards) {
			if (card.getValue() == 11) {
				aces += 1;
			}
			sum += card.getValue();
		}
		if (sum > 21) {
			while (aces > 0 && sum > 21) {
				sum -= 10;
				aces -= 1;
			}
		}

		return sum;
	}
	
	
	
}
