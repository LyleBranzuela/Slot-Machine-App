/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2_to_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A deck class, simulating the properties and things you can do in a real deck
 * (Shuffle, Draw, Place, and keep track of the amount of cards).
 * The deck class uses the Card.java to create the cards themselves.
 * 
 * @author lyleb
 */
public class Deck {
    private Card[] deck;
    private int deckSize;
    public int MAX_SIZE = 52;
    
    /**
     * Default constructor, initializing the full deck, and its cards.
     */
    public Deck()
    { 
        this.deck = new Card[this.MAX_SIZE];
        intialiseFullDeck();
        this.deckSize = this.deck.length - 1;
    }
    
    /**
     * Overloads the default constructor in case the user wants to initialize an empty deck.
     * 
     * @param fullDeck 
     */
    public Deck(boolean fullDeck)
    {
        if (fullDeck)
        {
            this.deck = new Card[this.MAX_SIZE];
            intialiseFullDeck();
            this.deckSize = this.deck.length - 1;
        }
        else
        {
            this.deck = new Card[0];
            //deckSize = -1; so that when the cards are added through placing, it starts in the array index of 0.
            deckSize = -1; 
        }
    }
    
    /**
     * Initializes the full 52 cards in the deck, with its proper suits and values.
     */
    private void intialiseFullDeck()
    {
        int deckCounter = 0;
        for (int suitCounter = 0; suitCounter < 4; suitCounter++)
        {
            for (int valueCounter = 0; valueCounter < 13; valueCounter++)
            {
                    this.deck[deckCounter] =    new Card(valueCounter, suitCounter);
                    deckCounter++; 
            }
        }
    }
    
    /**
     * Draws a card from the deck, while simultaneously reducing the size of the deck.
     * 
     * @return the card drawn.
     */
    public Card drawCard()
    {
        ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(this.deck));
        Card temp = (Card) deckList.get(this.deckSize);
        deckList.remove(this.deckSize);
        this.deckSize--;
        deckList.removeAll(Collections.singleton(null));
        this.deck = deckList.toArray(this.deck);
        
        return temp;
    }
    
    /**
     * Changes the deck into an array list, where a new card can be added.
     * This is changed again into the original array of cards object and removes all null characters.
     * 
     * @param card Which card would be placed to the deck.
     */
    public void placeCard(Card card)
    {
        ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(this.deck));
        deckList.add(card);
        this.deckSize++;
        deckList.removeAll(Collections.singleton(null));
        
        this.deck = deckList.toArray(this.deck);
    }
    
    /**
     * Shuffles the cards inside the deck randomly.
     */
    public void shuffle()
    {
        List deckList = Arrays.asList(this.deck);
        Collections.shuffle(deckList);
        deckList.toArray(this.deck);
    }
    
    /**
     * Returns the deck size of the deck / how many cards are in the deck.
     * 
     * @return amount of cards inside the deck.
     */
    public int getDeckSize()
    {
        int temp = this.deckSize + 1;
        return temp;
    }
    
    /**
     * Checks if the deck still has cards remaining (deck size greater than 0).
     * 
     * @return true if it still has cards, false if it's empty.
     */
    public boolean hasCardsRemaining()
    {
        List deckList = Arrays.asList(this.deck);
        return !deckList.isEmpty();
    }
    
    /**
     * Turns the deck into a readable string in the output.
     * 
     * @return the string built for an output.
     */
    @Override
    public String toString()
    {
        StringBuilder userCard = new StringBuilder();
        
        userCard.append("[ ");
        
        for (int counter = 0; counter <= this.deckSize; counter++) 
        {
            userCard.append(this.deck[counter]).append(" ");
        }
        userCard.append("]");

        return userCard.toString();
    }
}
