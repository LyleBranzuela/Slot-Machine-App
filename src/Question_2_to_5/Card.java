/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2_to_5;

/**
 *  A card class, simulating the properties of a real card.
 *  The card class, uses the implementation of Comparable.java to compare two cards.
 * 
 * @author lyleb
 */

public class Card implements Comparable<Card> {
    private int suit;
    private int value;
    
    /**
     * Initializes the parameter variables into the field variables.
     * 
     * @param value A card's assigned value (A-10).
     * @param suit A card's assigned suit (S/C/D/H).
     */
    public Card(int value, int suit)
    {
        this.value = value;
        this.suit = suit;
    }
    
    /**
     * A method that returns the card's value, if it's within the parameters (0-12).
     * 
     * @return Returns the suit's integer, a -1 if the suit is invalid.
     */
    public int getValue()
    {
        if (this.value >= 0 && this.value < 13)
        {
            return this.value;
        }
        else
        {
            assert this.value >= 0 && this.value < 13;
            return -1;
        }
    }
    
    /**
     * A method that returns the card's suit, if it's within the parameters (0-3).
     * 
     * @return Returns the suit's integer, a -1 if the suit is invalid.
     */
    public int getSuit()
    {
        if (this.suit >= 0 && this.suit < 4)
        {
            return this.suit;
        }
        else
        {
            assert this.suit >= 0 && this.suit < 4;
            return -1;
        }
    }
    
    /**
     * A method that turns the value and suit's integers into a string.
     * 
     * @return Combination of both card suit and value as a string.
     */
    @Override
    public String toString()
    {
        StringBuilder cardName = new StringBuilder(2);
        char cardSuit[] = new char[] {'♠', '♣', '♦', '♥'};
        char cardValue[] = new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

        cardName.append(cardSuit[getSuit()]);
        cardName.append(cardValue[this.value]);
        
        return cardName.toString();
    }
    
    /**
     * Compares the current card's value, and suit if the value is the same. 
     * Negative means that the other card is bigger.
     * Zero/0 means that both cards are equal.
     * Positive means that the current card is bigger than the other.
     * 
     * @param other Other card that would be compared with the current card.
     * @return An integer that defines whether which card is higher.
     */
    @Override
    public int compareTo(Card other)
    {
        if (this.value == other.value)
        {
            return this.suit - other.suit;
        }
        else
        {
            return this.value - other.value;
        }
    }
}
