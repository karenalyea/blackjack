package com.lmig.gfc.Blackjack.models;

import java.util.LinkedList;
import java.util.List;

public class Hand {
	
	//can be arraylist or linklist
	private LinkedList<Card> cards;
	
	public Hand() {
		cards = new LinkedList<Card>() ;
	}
		

		//has ability to hold cards, but doesn't currently have any
		
				
		public void accept(Card card) {
			cards.add(card);
		}
		// list method guarantees that cards will be returned
		
		
		//can use generic 'list' rather than linkedlist or arraylist
		public List<Card> getCards() {
			return cards;
		}
		
		public int getTotal() {
			int sum = 0;
			for (Card card : cards) {
				sum += card.getValue();
			}
			return sum;
		}
	
	
	//hand should know bust, blackjack, etc.  need to store cards inside hand

}
