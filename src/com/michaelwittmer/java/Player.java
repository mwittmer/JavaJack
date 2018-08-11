package com.michaelwittmer.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**The <b>Player</b> class 
 * */
public class Player {
    String name;
    int numCards=0;
    List<String> cards = new ArrayList<String>();
    boolean isDealer;
    int aceBonus;

    
    public int currentHandValue;

    /**The <b>Player</b> class constructor 
    */
    public Player(){      
    }

    /**The <b>hit</b> instance method 
     *  @param addCard - String value representing card to be added
     */
    public void hit(String addCard){
        this.cards.add(addCard);
        this.calcHandValue();
        
        //System.out.println(this.name + " has drawn a " + addCard);
    }
    /**The <b>stay</b> instance method 
     *  
     */
    public void stay(){
    	System.out.println(this.name + " has chosen to stay.");
    }
    /**The <b>fold</b> instance method 
     *  
     */
    public void fold(){

    }
    /**The <b>listCards</b> instance method
     *  output a listing of cards stored in the cards array list for the player instance
     */
    public void listCards(){
        
    	String output;
        System.out.println(this.name + " has: " + this.currentHandValue);
        for (String card:cards) {
            if(card.contains("13")){
                output = "KING " + card.substring(0,1);
                System.out.println(output);
            }else if(card.contains("12")){
                output = "QUEEN " + card.substring(0,1);
                System.out.println(output);
            }else if(card.contains("11")){
                output = "JACK " + card.substring(0,1);
                System.out.println(output);
            }else if(card.equals("♣101") || card.equals("♣111")) {
                output = "ACE of ♣";
                System.out.println(output);
            }else if (card.equals("♠101") || card.equals("♠111")) {
                output = "ACE ♠";
                System.out.println(output);
            }else if (card.equals("♥101") || card.equals("♥111")) {
                output = "ACE ♥";
                System.out.println(output);
            }else if (card.equals("♦101") || card.equals("♦111")) {
                output = "ACE of ♦";
                System.out.println(output);
            }else {
                System.out.println(card);
            }
        }
        System.out.println("");
    }
    /**The <b>calcHandValue</b> instance method
     *  @return currentHandValue - returns the value of the current hand as an integer
     */
    public int calcHandValue(){
    
    	//determine the value of the player's hand
    	
    	this.currentHandValue = 0;  //reset the variable
    	int cardNum = 0;
    	//int dispCardVal;
    	for (String card:this.cards) {
    		
    		//HOW TO REPLACE AN ITEM IN AN ARRAYLIST ****
    		//need to replace the card item when the card item is an ace in order
    		//to indicate value
    		
        	String parsedCardValue = card.substring(1);
        	String suit = card.substring(0, 1);
            int cardValue = Integer.parseInt(parsedCardValue);
            
            /*
            if (cardValue == 101 || cardValue == 111) {
            	dispCardVal = 1;
            }else {
            	dispCardVal = cardValue;
            }
            
            System.out.println("Processing: " + suit + dispCardVal);
            */
            
            String newCardValue;
            
            switch (cardValue){
            
            	case 1:
                	if ( processAce() == 1 ) {
                		cardValue = 101;
                		newCardValue = suit + cardValue;
                		this.cards.set(cardNum, newCardValue);
                		//System.out.println("DEBUG: cardNum now equals " + newCardValue);
                	} else {
                		System.out.println(card);
                		cardValue = 111;
                		newCardValue = suit + cardValue;
                		this.cards.set(cardNum, newCardValue);
                		//System.out.println("DEBUG: cardNum now equals " + newCardValue);
                	}
                    break;

                case 11:
                    cardValue = 10;
                    break;

                case 12:
                    cardValue = 10;
                    break;

                case 13:
                    cardValue = 10;
                    break;
                
            
                case 110:
                	cardValue = 101;
                	break;
                
                case 111:
                	cardValue = 111;
                	break;

                default:
                    break;
            }
            this.currentHandValue = this.currentHandValue + cardValue;
            if (this.currentHandValue > 200 ){
            	this.currentHandValue -= 200;
            }else if (this.currentHandValue > 100 && this.currentHandValue < 200) {
            	this.currentHandValue -= 100;
            }
            cardNum++;
        }
    	return currentHandValue;
    }
    /**The <b>processAce</b> instance method
     *  @return Returns an integer value for the specified Ace
     */
    public int processAce(){
    	if (this.isDealer) {
    		//System.out.println(this.name + " drew an ACE.");
    		if (this.currentHandValue <= 10) { 
    			System.out.println(this.name + " drew an ACE and is playing as 11");
    			return 11;
    		} else {
    			System.out.println(this.name + " drew an ACE and is playing as 1");
    			return 1;
    		}
    	}else {
    	    Scanner scan = new Scanner(System.in);                //create a scanner object to read KB input
            
    		System.out.println("You have an ace.");
            System.out.println("Press 1 to make it worth 1, Press 2 to make it worth 11.");
            int choice  = scan.nextInt();
            if (choice == 2) {
	            System.out.println("You selected 11.");
	            return 11;
            } else {
            	System.out.println("You selected 1.");
                return 1;
            }	
    	}
    }
}
