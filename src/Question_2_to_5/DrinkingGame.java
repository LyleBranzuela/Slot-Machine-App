/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2_to_5;

import java.util.Scanner;

/**
 * A drinking game where two card's value and suit is compared.
 * Whoever loses, the loser needs to drink a shot of alcohol.
 * The program uses both the Card.java and the Deck.java classes.
 * 
 * @author lyleb
 */
public class DrinkingGame {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int PLAYER_1 = 0;
        int PLAYER_2 = 0;
        Card PLAYER_1_CARD; 
        Card PLAYER_2_CARD;
        
        Deck gameDeck = new Deck();
        gameDeck.shuffle();
        
        // Print rules, rank, and a welcome message
        System.out.println("============================================================================");
        System.out.println("|                       WELCOME TO THE DRINKING GAME !!                    |");
        System.out.println("============================================================================");
        System.out.println("Suit Rank (In Order - Left to Right): ♥, ♦, ♣, ♠");
        System.out.println("Value Rank (In Order - Left to Right): K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2, A");
        do
        {
            System.out.println("============================================================================");
            System.out.printf("PRESS ENTER TO DRAW(CARDS IN THE DECK: " + (gameDeck.getDeckSize() + 1) + "): ");
            scan.nextLine();
            
            // Drawing and Printing the Cards
            System.out.println("============================================================================");
            PLAYER_1_CARD = gameDeck.drawCard();
            PLAYER_2_CARD = gameDeck.drawCard();
            System.out.println("Player 1 Card: " + PLAYER_1_CARD);
            System.out.println("Player 2 Card: " + PLAYER_2_CARD);
            
            // Checking the Result
            if (PLAYER_1_CARD.compareTo(PLAYER_2_CARD) > 0)
            {
                System.out.printf(PLAYER_1_CARD + " beats " + PLAYER_2_CARD + "... Player 1 Wins!! Player 2 has to drink a shot.\n\n");
                PLAYER_1++;
            }
            else if(PLAYER_1_CARD.compareTo(PLAYER_2_CARD) < 0)
            {
                System.out.printf(PLAYER_1_CARD + " gets beaten by " + PLAYER_2_CARD + "... Player 2 Wins!! Player 1 has to drink a shot.\n\n");
                PLAYER_2++;
            }
        }while(gameDeck.getDeckSize() > 0);
        
        // Total Score Print
        System.out.printf("\n============================================================================\n");
        System.out.println("YOU USED UP ALL THE CARDS IN THE DECK!! HERE'S THE SCORE: ");
        System.out.println("============================================================================");
        System.out.println("PLAYER 1 SHOTS DRANK: " + PLAYER_1);
        System.out.println("PLAYER 2 SHOTS DRANK: " + PLAYER_2);
        System.out.println("============================================================================");
    }
}
