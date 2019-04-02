/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2_to_5;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Snap game where players take turn putting cards in the pile, anyone who runs out of cards, loses. 
 * Players can call snap if the two card's values are the same to keep their card count up higher than the opponent.
 *
 * @author lyleb
 */
public class Snap 
{
    private int playerTurn;
    public int PLAYER_1 = 1;
    public int PLAYER_2 = 2;
    private Deck player1Deck;
    private Deck player2Deck;
    private ArrayList<Card> pile;
    
    /**
     * Makes player 1 start the turn, and setups the initialized empty decks.
     */
    public Snap()
    {
        this.playerTurn = this.PLAYER_1;
        this.player1Deck = new Deck(false);
        this.player2Deck = new Deck(false);
        pile = new ArrayList<>();
        setupPlayerDecks();
    }
    
    /**
     * Splits a shuffled deck and draws 1 card to be put on PLAYER 1's deck,
     * and then the draws another 1 card to be put on PLAYER 2's deck.
     */
    private void setupPlayerDecks()
    {
        Deck gameDeck = new Deck();
        gameDeck.shuffle();
        // Add the cards in both Decks
        for (int counter = 0; counter < 26; counter++)
        {
            this.player1Deck.placeCard(gameDeck.drawCard());
            this.player2Deck.placeCard(gameDeck.drawCard());
        }
    }
    
    /**
     * Specified player picks up the pile that's accumulated through drawing.
     * 
     * @param player Which player should pickup the pile (1 or 2)
     */
    private void pickupPile(int player)
    {
        int pileSize = this.pile.size();
        if (player == this.PLAYER_1) // If player 1 picks up the pile
        {
            for (int pickupCounter = 0; pickupCounter < pileSize; pickupCounter++)
            {
                this.player1Deck.placeCard(this.pile.get(pickupCounter)); 
            } 
            this.player1Deck.shuffle();
            this.pile.removeAll(this.pile);
            this.pile.trimToSize();
        }
        else if (player == this.PLAYER_2) // If player 2 picks up the pile
        {
            for (int pickupCounter2 = 0; pickupCounter2 < pileSize; pickupCounter2++)
            {
                this.player2Deck.placeCard(this.pile.get(pickupCounter2)); 
            } 
            this.player2Deck.shuffle();
            this.pile.removeAll(this.pile);
            this.pile.trimToSize();
        }
    }
    
    /**
     * Check if the 2 final cards in the pile have the same value.
     * 
     * @return A boolean indicating whether it's the same card value or not.
     */
    private boolean checkSnap()
    {
        Card previousTempCard = this.pile.get(this.pile.size() - 1);
        Card currentTempCard = this.pile.get(this.pile.size() - 2);
            
        return previousTempCard.getValue() == currentTempCard.getValue();
    }
    
    /**
     * The player that calls the snap has 2 possibilities:
     * <b>1)</b> - If the two card's values are the same, the player who calls gets the pile.<br> 
     * <b>2)</b> - If the two card's values are not the same, the other player gets the pile.<br> 
     * 
     * @param player Which player called the snap (1 or 2).
     * @return whether the snap was correct or not.
     */
    public boolean snap (int player)
    {
        if (player == this.PLAYER_1 && this.pile.size() >= 2)
        {
            // Player 1 calls for a Snap
           if (checkSnap())
            {
                this.playerTurn = this.PLAYER_1;
                pickupPile(this.PLAYER_1);
                return true;
            }
            else
            {
                this.playerTurn = this.PLAYER_2;
                pickupPile(this.PLAYER_2);
                return false;
            }
        }
        else if (player == this.PLAYER_2 && this.pile.size() >= 2)
        {
            // Player 2 calls for a Snap
            if (checkSnap())
            {
                
                this.playerTurn = this.PLAYER_2;
                pickupPile(this.PLAYER_2);
                return true;
            }
            else
            {

                this.playerTurn = this.PLAYER_1;
                pickupPile(this.PLAYER_1);
                return false;
            }
        }
        else
        {
            System.out.printf("\nNEEDS AT LEAST 2 CARDS IN THE PILE!!\n");
            return false;
        } 
    }
    
    /**
     * Draws a card, putting it in the pile, change the turn, and returning the drawn card.
     * 
     * @param player Which player draws a card (1 or 2).
     * @return the card drawn in the specified player's deck.
     */
    public Card drawCard(int player) 
    {
        Card tempCard = null;
        if (player == this.PLAYER_1)
        {
            tempCard = this.player1Deck.drawCard();
            this.playerTurn = this.PLAYER_2;
            this.pile.add(tempCard);
            return tempCard;
        }
        else if (player == this.PLAYER_2)
        {
            tempCard = this.player2Deck.drawCard();
            this.playerTurn = this.PLAYER_1;
            this.pile.add(tempCard);
            return tempCard;
        }
        else
        {
             return tempCard;
        } 
    }
    
    /**
     * Checks whether anyone has won yet, through running out of cards.
     * 
     * @return true if either one has won, and false if both of the haven't won yet.
     */
    public boolean hasGameFinished()
    {
        return isWinner(PLAYER_1) || isWinner(PLAYER_2);
    }
    
    /**
     * Checks whether a player has won or not.
     * 
     * @param player Which player is the winner.
     * @return Did someone win, draw, or lose.
     */
    public boolean isWinner(int player)
    {
       if (this.player1Deck.getDeckSize() < 1 && this.player2Deck.getDeckSize() < 1) // Did both of them lose
       {
           System.out.printf("\n🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🃁🃂🃃🃄🃅🃆🃇🃈🃉🃊🃋🃌🃍🃎🂡🂢🂣\n");
           System.out.println("|♠♣♦♥          IT'S A DRAW !!         ♥♦♣♠|");
           System.out.println("🂱🂲🂳🂴🂵🂶🂷🂸🂹🂺🂻🂼🂽🂾🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🂱🂲🂳");
           return true;
       }
       else if (player == this.PLAYER_1 && this.player2Deck.getDeckSize() < 1) // Did player 1 win checker
       {
           System.out.printf("\n🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🃁🃂🃃🃄🃅🃆🃇🃈🃉🃊🃋🃌🃍🃎🂡🂢🂣\n");
           System.out.println("|♠♣♦♥         PLAYER 1 WINS !!        ♥♦♣♠|");
           System.out.println("🂱🂲🂳🂴🂵🂶🂷🂸🂹🂺🂻🂼🂽🂾🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🂱🂲🂳");
           return true;
       }
       else if(player == this.PLAYER_2 && this.player1Deck.getDeckSize() < 1) // Did player 2 win checker
       {
           System.out.printf("\n🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🃁🃂🃃🃄🃅🃆🃇🃈🃉🃊🃋🃌🃍🃎🂡🂢🂣\n");
           System.out.println("|♠♣♦♥         PLAYER 2 WINS !!        ♥♦♣♠|");
           System.out.println("🂱🂲🂳🂴🂵🂶🂷🂸🂹🂺🂻🂼🂽🂾🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🂱🂲🂳");
           return true;
       }
       else
       {
           return false;
       }
    }
    
    /**
     * Returns the player turn when called.
     * 
     * @return current player turn (1 or 2).
     */
    public int getPlayerTurn()
    {
        return this.playerTurn;
    }
    
    /**
     * Returns the overall amount of cards in the specified player's deck.
     * 
     * @param player Which player to return the deck size.
     * @return Deck size of the specified player.
     */
    public int getPlayerCardsRemaining(int player)
    {
        if (player == this.PLAYER_1)
        {
            return this.player1Deck.getDeckSize();
        }
        else if (player == this.PLAYER_2)
        {
            return this.player2Deck.getDeckSize();
        }
        else
        {
            return 0;   
        }
    }
    
    public static void main(String[] args)
    {
        Snap mySnap = new Snap();
        Scanner scan = new Scanner(System.in);
        Card drawedCard = null;
        Card previousCard = null;
        
        char key = 'Q';
        // Print Welcome Message
        System.out.println("🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🃁🃂🃃🃄🃅🃆🃇🃈🃉🃊🃋🃌🃍🃎🂡🂢🂣");
        System.out.println("|♠♣♦♥    Welcome to the Snap Game!!   ♥♦♣♠|");
        System.out.println("🂱🂲🂳🂴🂵🂶🂷🂸🂹🂺🂻🂼🂽🂾🂡🂢🂣🂤🂥🂦🂧🂨🂩🂪🂫🂬🂭🂮🃑🃒🃓🃔🃕🃖🃗🃘🃙🃚🃛🃜🃝🃞🂱🂲🂳");
        
        do 
        {
            // Puts 2 cards mandatory cards
            if (mySnap.pile.isEmpty())
            {
                previousCard = mySnap.drawCard(mySnap.PLAYER_1);   
                drawedCard = mySnap.drawCard(mySnap.PLAYER_2);
            }
            // Print controls
            System.out.printf("\n><><><><><><><><><><><><><><><><><><><><><><>\n");
            System.out.println("|  D - PLAYER 1 DRAW  |  K - PLAYER 2 DRAW  |");
            System.out.println("|  S - PLAYER 1 SNAP  |  L - PLAYER 2 SNAP  |");
            System.out.println("><><><><><><><><><><><><><><><><><><><><><><>");
            
            // Print player turn and how man cards are in the pile
            System.out.println("| CARD PILE (PILE CARDS: " + mySnap.pile.size() + "): " + previousCard + " " + drawedCard);
            System.out.printf("|IT'S PLAYER " + mySnap.getPlayerTurn() + "'S TURN (PLAYER CARDS: " + mySnap.getPlayerCardsRemaining(mySnap.getPlayerTurn()) + "): ");
            key = scan.nextLine().charAt(0);

            switch(key)
            {
                // PLAYER 1 DRAW CARD   
                case 'D':
                case 'd':
                    // Checks if it's PLAYER 1's turn or PLAYER 2
                    if (mySnap.getPlayerTurn() == mySnap.PLAYER_1) 
                    {
                        previousCard = drawedCard;
                        drawedCard = mySnap.drawCard(mySnap.PLAYER_1);
                    }
                    else
                    {
                        System.out.printf("ERROR: IT'S PLAYER 2'S TURN !!\n\n");
                    }
                    break;
                
                // PLAYER 1 SNAP CALL
                case 'S':
                case 's':
                    System.out.println("PLAYER 1 calls for a snap...");
                    if (mySnap.snap(mySnap.PLAYER_1))
                    {
                        System.out.println("\nPLAYER 1 WON !! PLAYER 1 picks up the pile...");
                    }
                    else
                    {
                        System.out.println("\nPLAYER 1 LOSES !! PLAYER 2 picks up the pile...");
                    }
                    break;
                    
               // PLAYER 2 DRAW CARD 
               case 'K':
               case 'k':
                    // Checks if it's PLAYER 2's turn or PLAYER 1
                    if (mySnap.getPlayerTurn() == mySnap.PLAYER_2) 
                    {
                        previousCard = drawedCard;
                        drawedCard = mySnap.drawCard(mySnap.PLAYER_2);
                    }
                    else
                    {
                        System.out.println("ERROR: IT'S PLAYER 1'S TURN !!\n\n");
                    }
                    break;
                    
                // PLAYER 2 SNAP CALL
                case 'L':
                case 'l':
                    System.out.println("PLAYER 2 calls for a snap...");
                    if (mySnap.snap(mySnap.PLAYER_2))
                    {
                        System.out.println("\nPLAYER 2 WON !! PLAYER 2 picks up the pile...");
                    }
                    else
                    {
                        System.out.printf("\nPLAYER 2 LOSES !! PLAYER 1 picks up the pile...");
                    }
                    break;
                    
                default:
                    System.out.printf("UNKNOWN ACTION\n\n");             
            }
        }while(!mySnap.hasGameFinished());
    }
}
