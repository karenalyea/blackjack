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
	boolean showDealerCard = true;
	
	boolean dealerHasBlackjack = false;
	boolean playerHasBlackjack = false;
	boolean outOfMoney = false;
	boolean needNewBetAmount = false;
	private int currentBet;
	boolean outOfCards = false;
	public int dealerUpValue;
	public Suits dealerUpSuit;

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
		dealerHasBlackjack = false;
		playerHasBlackjack = false;
				
		Card card = deck.draw();
		playerHand.accept(card);
		playerTotal = playerHand.getTotal();
		
		Card card2 = deck.draw();
		dealerHand.accept(card2);
		dealerTotal = dealerHand.getTotal();
		showDealerCard = true;
				
		Card card3 = deck.draw();
		playerHand.accept(card3);
		playerTotal = playerHand.getTotal();
		
		Card card4 = deck.draw();
		dealerHand.accept(card4);
		dealerTotal = dealerHand.getTotal();
		
		if (dealerTotal == blackjack) {
			dealerHasBlackjack = true;
			gameOver = true;
		}
		if (playerTotal == blackjack) {
			playerHasBlackjack = true;
			gameOver = true;
		}
	}
	

	public void ddown() {
		//initial bet has already had bet taken out, so need to take
		//out again to fulfill the double down
		cashOnHand = cashOnHand - currentBet;
		currentBet = currentBet * 2;
		Card card = deck.draw();
		playerHand.accept(card);
		playerTotal = playerHand.getTotal();
		showDealerCards = true;
		showPlayerButtons = false;
		gameOver = true;

		if (playerTotal > blackjack) {
			playerBust = true;
		} else {
			playerBust = false;
		}
	}

	public void hitPlayer() {
		Card card = deck.draw();
		playerHand.accept(card);
		playerTotal = playerHand.getTotal();

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
		showDealerCard = false;
		showDealerCards = true;
		while (dealerTotal < dealerHits) {
			Card card = deck.draw();
			dealerHand.accept(card);
			dealerTotal = dealerHand.getTotal();
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
		if (!deck.deckStillHasCards) {
			gameOver = true;
		}
		ThisHandOver calculator = new ThisHandOver(playerBust, dealerBust, playerHasBlackjack, dealerHasBlackjack, playerTotal,
				dealerTotal);
		double winningAmount = calculator.figureItOut(currentBet);
		cashOnHand = cashOnHand + winningAmount;
		if ((cashOnHand == 0.0) || (cashOnHand < 0)) {
			gameOver = true;
			outOfMoney = true;
		}
	}
	
	public boolean playerLost() {
		return playerTotal < dealerTotal && !playerBust;
	}

	public void setCurrentBet(int bet) {
		currentBet = bet;
		cashOnHand = cashOnHand - bet;
		
	}


	public boolean deckStillHasCards() {
		return deck.deckStillHasCards;
	}
}
