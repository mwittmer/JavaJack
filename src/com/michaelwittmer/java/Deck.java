package com.michaelwittmer.java;

import java.util.Random;

public class Deck {
	public String[] cardDeck = new String[52];
	public String[] cardArray = new String[52];
	public String[] temporaryDeck = new String[52];  //look into streamlining - this shouldn't be needed
	public String topCard;
	public int cardCounter;
	
	//Constructor
	public Deck() {
		// create a fresh unshuffled deck of cards
        for(int i = 0; i < 13 ; i++ ){
            int cardVal = i+1;
            cardArray[i]= "♦" + Integer.toString(cardVal);
        }
        for(int i = 13; i < 26 ; i++ ){
            int cardVal = i-12;
            cardArray[i] = "♥" + Integer.toString(cardVal);
        }
        for(int i = 26; i < 39 ; i++ ){
            int cardVal = i-25;
            cardArray[i] = "♣" + Integer.toString(cardVal);
        }
        for(int i = 39; i < 52 ; i++ ){
            int cardVal = i-38;
            cardArray[i] = "♠" + Integer.toString(cardVal);
        }
        refreshTopCard();
	}
	
	//** updates the topCard field with the value of the next card
	public void refreshTopCard() {
		this.topCard = cardArray[cardCounter];
	}
	
	//** shuffles the deck
	public void shuffle() {
	
        // ****shuffle the deck*****
        Random rand = new Random();
        //int r = rand.nextInt(cardArray.length);

        //populate a shuffled deck
        int idx;
        cardCounter = 0;
        int maxCtr = cardArray.length;

        do {
            idx = rand.nextInt(maxCtr);                             //a randomized index variable
            if (cardArray[idx] != null) {                           //if there is a value in the index element
            	temporaryDeck[cardCounter] = cardArray[idx];              //add the value to the temporaryDeck array
                cardArray[idx] = null;                              //set cardArray[idx] to null so it is not used again
                cardCounter++;                                      //increment cardCounter
            }
        } while(cardCounter < maxCtr);
        
        // **** refresh cardArray (deck) with shuffled cards ****
        for(int i = 0; i < cardArray.length; i++) {
        	cardArray[i] = temporaryDeck[i];
        	temporaryDeck[i] = null;
        }
        cardCounter = 0;
        refreshTopCard();
	}
	
	public String getCardDisplayName(String s){
        String output = s;
        if(s.contains("13")){
            output = "KING of " + s.substring(0,1);
        }
        if(s.contains("12")){
            output = "QUEEN of " + s.substring(0,1);
        }
        if(s.contains("11")){
            output = "JACK of " + s.substring(0,1);
        }
        if(s.equals("♣1")) {
            output = "ACE of ♣";
        }
        if (s.equals("♠1")) {
            output = "ACE of ♠";
        }
        if (s.equals("♥1")) {
            output = "ACE of ♥";
        }
        if (s.equals("♦1")) {
            output = "ACE of ♦";
        }
        return output;
    }
	public void trashCard() {
		cardCounter++;
	}
}
