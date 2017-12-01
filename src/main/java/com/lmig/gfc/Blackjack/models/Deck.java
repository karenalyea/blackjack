package com.lmig.gfc.Blackjack.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	public boolean deckStillHasCards = true;

	private ArrayList<Card> deckOfCards;

	// private variable that holds a collection of Card objects

	public Deck() {

		// initialize that collection of Card object
		deckOfCards = new ArrayList<Card>();

		// Generate deck - first aces, then number cards, then face cards

		deckOfCards.add(new AceCard(Suits.CLUB));
		deckOfCards.add(new AceCard(Suits.HEART));
		deckOfCards.add(new AceCard(Suits.SPADE));
		deckOfCards.add(new AceCard(Suits.DIAMOND));

		int[] cardNumbers = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i : cardNumbers) {
			deckOfCards.add(new NumberCard(Suits.CLUB, i));
			deckOfCards.add(new NumberCard(Suits.HEART, i));
			deckOfCards.add(new NumberCard(Suits.SPADE, i));
			deckOfCards.add(new NumberCard(Suits.DIAMOND, i));
		}

		deckOfCards.add(new FaceCard(Suits.CLUB, Faces.JACK));
		deckOfCards.add(new FaceCard(Suits.CLUB, Faces.QUEEN));
		deckOfCards.add(new FaceCard(Suits.CLUB, Faces.KING));
		deckOfCards.add(new FaceCard(Suits.HEART, Faces.JACK));
		deckOfCards.add(new FaceCard(Suits.HEART, Faces.QUEEN));
		deckOfCards.add(new FaceCard(Suits.HEART, Faces.KING));
		deckOfCards.add(new FaceCard(Suits.SPADE, Faces.JACK));
		deckOfCards.add(new FaceCard(Suits.SPADE, Faces.QUEEN));
		deckOfCards.add(new FaceCard(Suits.SPADE, Faces.KING));
		deckOfCards.add(new FaceCard(Suits.DIAMOND, Faces.JACK));
		deckOfCards.add(new FaceCard(Suits.DIAMOND, Faces.QUEEN));
		deckOfCards.add(new FaceCard(Suits.DIAMOND, Faces.KING));

		shuffle();
	}


	public ArrayList<Card> getDeckOfCards() {
		return deckOfCards;
	}

	public void shuffle() {
		Collections.shuffle(deckOfCards);
	}

	public int size() {
		return deckOfCards.size();
	
	}

	public Card draw() {
		if (deckOfCards.size() == 0) {
			Card card = null;
			deckStillHasCards = false;
			return card;
			
		}
		Card card = deckOfCards.remove(0);
		return card;
	}

}