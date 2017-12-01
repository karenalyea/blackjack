package com.lmig.gfc.Blackjack.models;

public class Game {

	private Deck deck = new Deck();
	private Hand playerHand = new Hand();
	private Hand dealerHand = new Hand();
	private double cashOnHand = 200d;
	public int dealerTotal = 0;
	public int playerTotal = 0;
	private int dealerHits = 17;
	private int blackjack = 21;
	boolean showDealerCards = false;
	boolean playerBust = false;
	boolean dealerBust = false;
	boolean showPlayerButtons = true;
	boolean gameOver = false;
	boolean dealerFaceCard = false;
	boolean playerFaceCard = false;
	boolean dealerHasBlackjack = false;
	boolean playerHasBlackjack = false;
	boolean outOfMoney = false;
	boolean needNewBetAmount = false;
	private int currentBet;
	boolean outOfCards = false;

	public void deal() {
		playerHand = new Hand();
		dealerHand = new Hand();
		playerTotal = 0;
		dealerTotal = 0;
		showDealerCards = false;
		playerBust = false;
		dealerBust = false;
		showPlayerButtons = true;
		gameOver = false;
		dealerFaceCard = false;
		playerFaceCard = false;
		dealerHasBlackjack = false;
		playerHasBlackjack = false;
	
		
		
		
		Card card = deck.draw();
		playerHand.accept(card);
		playerTotal = playerTotal + card.getValue();
		if ((card.getFace() == "JACK") || (card.getFace() == "QUEEN") || (card.getFace() == "KING")) {
			playerFaceCard = true;
		}

		Card card2 = deck.draw();
		dealerHand.accept(card2);
		dealerTotal = dealerTotal + card2.getValue();
		if ((card2.getFace() == "JACK") || (card2.getFace() == "QUEEN") || (card2.getFace() == "KING")) {
			playerFaceCard = true;
		}

		Card card3 = deck.draw();
		playerHand.accept(card3);
		playerTotal = playerTotal + card3.getValue();
		if ((card3.getFace() == "JACK") || (card3.getFace() == "QUEEN") || (card3.getFace() == "KING")) {
			dealerFaceCard = true;
		}

		Card card4 = deck.draw();
		dealerHand.accept(card4);
		dealerTotal = dealerTotal + card4.getValue();
		if ((card4.getFace() == "JACK") || (card4.getFace() == "QUEEN") || (card4.getFace() == "KING")) {
			dealerFaceCard = true;
		}

		if ((dealerFaceCard = true) & (dealerTotal == blackjack)) {
			dealerHasBlackjack = true;
			gameOver = true;
		}
		if ((playerFaceCard = true) & (playerTotal == blackjack)) {
			playerHasBlackjack = true;
			gameOver = true;
		}
	}


	public void hitPlayer() {
		Card card = deck.draw();
		playerHand.accept(card);
		playerTotal = playerTotal + card.getValue();

		if (playerTotal > blackjack) {
			playerBust = true;
			showDealerCards = true;
			showPlayerButtons = false;
			gameOver = true;

		} else {
			playerBust = false;
			showDealerCards = false;
			showPlayerButtons = true;
		}
	}

	public void hitDealer() {
		showPlayerButtons = false;
		showDealerCards = true;
		while (dealerTotal < dealerHits) {
			Card card = deck.draw();
			dealerHand.accept(card);
			dealerTotal = dealerTotal + card.getValue();
			if (dealerTotal > blackjack) {
				dealerBust = true;
				gameOver = true;
			} else {
				dealerBust = false;
			}
		}

	}

	public boolean isPlayerBust() {
		return playerBust;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void calculatePayout() {
		ThisHandOver calculator = new ThisHandOver(playerBust, dealerBust, playerHasBlackjack, dealerHasBlackjack, playerTotal,
				dealerTotal);
		double winningAmount = calculator.figureItOut(currentBet);
		cashOnHand = cashOnHand + winningAmount;
			
	}
	
	public boolean playerLost() {
		return playerTotal < dealerTotal && !playerBust;
	}

	public void setCurrentBet(int bet) {
		currentBet = bet;
//		if (bet > cashOnHand) {
//			needNewBetAmount = true;
//		}
		cashOnHand = cashOnHand - bet;
		if ((cashOnHand == 0.0) || (cashOnHand < 0)) {
			gameOver = true;
			outOfMoney = true;
		}
	}
}
