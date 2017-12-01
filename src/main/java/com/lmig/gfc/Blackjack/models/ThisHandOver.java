package com.lmig.gfc.Blackjack.models;

public class ThisHandOver {
	private boolean playerBust;
	private boolean dealerBust;
	private boolean playerHasBlackjack;
	private boolean dealerHasBlackjack;
	private int playerTotal;
	private int dealerTotal;

	public ThisHandOver(boolean playerBust, boolean dealerBust, boolean playerHasBlackjack, boolean dealerHasBlackjack,
			int playerTotal, int dealerTotal) {
		this.playerBust = playerBust;
		this.dealerBust = dealerBust;
		this.playerHasBlackjack = playerHasBlackjack;
		this.dealerHasBlackjack = dealerHasBlackjack;
		this.playerTotal = playerTotal;
		this.dealerTotal = dealerTotal;

	}

	public double figureItOut(int currentBet) {

		if (playerBust) {
			return 0;
		}

		if (dealerBust) {
			return 2 * currentBet;
		}

		if ((playerHasBlackjack) && (dealerHasBlackjack)) {
			return currentBet;
		}

		if (playerHasBlackjack) {
			return 2.5 * currentBet;
		}

		if ((playerTotal <= 21) && (playerTotal > dealerTotal)) {
			return 2 * currentBet;
		}

		return 0;
	}
}
