package com.lmig.gfc.Blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.Blackjack.models.Game;

@Controller
public class BlackjackController {
	private Game game;

	public BlackjackController() {
		game = new Game();
	} 

	@RequestMapping("/")
	public ModelAndView showBetScreen() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bet");
		mv.addObject("game", game);
		return mv;
	} 

	@PostMapping("/bet")
	public ModelAndView handleBet(int bet) {
		game.setCurrentBet(bet);
		try {
			game.deal();
		} catch(Exception e) {}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/play");
		return mv; 
	}     
 
	@GetMapping("/play")
	public ModelAndView dealCards() {
		
		if (game.deckStillHasCards() == false) {
			game.calculatePayout();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("game", game);
		mv.setViewName("play");
    
		return mv; 
	}  
	  
	@PostMapping("/hit")
	public ModelAndView hitPlayer() {
		try {
			game.hitPlayer();
		} catch(Exception e) {}
		
		if (game.isPlayerBust() || game.isGameOver()) {
	
			game.calculatePayout();
		}
		 
		if (game.deckStillHasCards() == false) {
			game.calculatePayout();
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("game", game);
		mv.setViewName("play");
		return mv;
	}
	  
	@PostMapping("/stand")
	public ModelAndView hitDealer() {
		try {
			game.hitDealer();
		} catch(Exception e) {}
		game.calculatePayout();
		
		if (game.deckStillHasCards() == false) {
			game.calculatePayout();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("game", game);
		mv.setViewName("play");
		return mv;
    
	}

}
