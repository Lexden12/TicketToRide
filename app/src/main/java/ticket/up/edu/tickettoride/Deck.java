package ticket.up.edu.tickettoride;

import android.graphics.Canvas;

import java.util.ArrayList;

public abstract class Deck {
    protected ArrayList<Card> cards = new ArrayList<>();
    protected ArrayList<Card> discard = new ArrayList<>();

    /**
     * shuffles the deck randomly
     */
    public void shuffle(){
        for(int i=0; i<cards.size()-1; i++){
            swap(i, (int)(Math.random()*(cards.size()-i)+i), cards);
        }
    }

    /**
     * swaps the positions of two cards (used for shuffling)
     * @param i index of the first card
     * @param j index of the card to switch with the first card
     * @param list list containing the cards we want to swap
     */
    public void swap(int i, int j, ArrayList<Card> list){
        Card c = list.get(i);
        list.set(i, list.get(j));
        list.set(j, c);
    }

    /**
     * draw cards from the deck (removes cards from the deck)
     * @param i the number of cards to draw
     * @return the list of the cards that were drawn
     */
    public ArrayList<Card> draw(int i){
        ArrayList<Card> drawn = new ArrayList<>();
        for (int j = 0; j < i; j++){
            drawn.add(cards.remove(cards.size()-1));
        }
        return drawn;
    }

    /**
     * add the given cards to the discard pile (when a player claims a route)
     * @param cards the cards which will be discarded
     */
    public void discard(ArrayList<Card> cards){
        discard.addAll(cards);
    }
}
