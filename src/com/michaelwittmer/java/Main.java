package com.michaelwittmer.java;

import java.util.Scanner;

public class Main {
    static String[] cardArray = new String[52];
    //static String[] deck = new String[52];
    static int cardCounter;
    static boolean testing = false;
/**The <b>BlackJack</b> application main method
 * 
 * @param args - a string array named args
 */
    public static void main(String[] args){
    	cardCounter = 3;
        Scanner sc = new Scanner(System.in);                //create a scanner object to read KB input
    	
    	//create a deck of cards
    	Deck myDeck = new Deck();
    	
    	//shuffle the deck of cards
    	myDeck.shuffle();
    	         
        //****overwrite cardArray values for testing
    	if (testing) {
    		myDeck.cardArray[0] = "♥7";
    		myDeck.cardArray[1] = "♦10"; 
    		myDeck.cardArray[2] = "♣1"; 
    		myDeck.cardArray[3] = "♣11"; 
    		myDeck.cardArray[4] = "♦8"; 
    		myDeck.cardArray[5] = "♥8";
    		myDeck.cardArray[6] = "♠3";
    		myDeck.cardArray[7] = "♥10";
    		myDeck.cardArray[8] = "♥9"; 
    		myDeck.cardArray[9] = "♦2"; 
    	
    		//display the first 10 cards
            for (int i = 0; i < 10; i++) {
            	System.out.print(myDeck.cardArray[i] + " ");
            }
            System.out.println("\n");
      	}
    	
    	
    	Player p = new Player();         //create a Player object for the human player
        Player d = new Player();         //create a Player object for the dealer/computer

        p.name = "Mike";        
        
        if( p.name == null ) {
	        System.out.print("Player, enter your name: ");      //prompt user to enter name
	        p.name  = sc.next();                                //apply the user-provided name to the player object
        }
        
        d.name = "Dealer";                                  //give the dealer a name
        d.isDealer = true;									//set the isDealer flag to true
        
        
        //start game
        p.hit(myDeck.cardArray[0]);
        p.listCards();                                      //show what the player has
        d.hit(myDeck.cardArray[1]);
        d.listCards();
        p.hit(myDeck.cardArray[2]);                         //deal the 3rd card in the deck to Player
        p.listCards();                                      //show what the player has
        
        //Player's turn
        int x = 1;
        while(x == 1) {
	        //System.out.println(p.name + " has " + p.calcHandValue());
	        System.out.print("Press 1 to hit, 2 to stay: ");
	        x = sc.nextInt();
	        System.out.println();
	        
	        if (x == 1) {
	        	p.hit(myDeck.cardArray[cardCounter]);
	        	p.listCards();
	        	cardCounter++;
	        }else {
	        	p.stay();
	        	if(dealersTurn(myDeck, d, p.currentHandValue)) {
	        		System.out.println("Dealer Wins with " + d.currentHandValue + ".");
	        		}else{
	        			System.out.println("Dealer Loses. " + p.name.toUpperCase() + " WINS!!!");
	        	}
	        }
	        //check for win/loss
	        if (p.currentHandValue == 21) {
	        	System.out.println("You have 21!  YOU WIN!!!");
	        	x = 2;
	        }if (p.currentHandValue > 21) {
	        	System.out.println("BUSTED!  You lose.");
	        	x = 2;
	        }
        }
        sc.close();
    } // end of main method
	
    public static void runGame() {
		
	}
	
	public static boolean dealersTurn(Deck myDeck, Player d, int playerValue) {
		boolean dealerWins;

		while (d.currentHandValue < playerValue) {
			d.hit(myDeck.cardArray[cardCounter]);
			cardCounter++;
			d.listCards();
		}
		
		if (d.currentHandValue > 21) {
			//dealer busts
			dealerWins = false;
		}else {
			//dealer wins
			dealerWins = true;
		}
		return dealerWins;
	} // end of dealersTurn method
}