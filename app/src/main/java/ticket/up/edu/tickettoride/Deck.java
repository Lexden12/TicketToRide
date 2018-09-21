package ticket.up.edu.tickettoride;

import android.graphics.Canvas;

import java.util.ArrayList;

public abstract class Deck {
    protected ArrayList<Card> cards = new ArrayList<>();
    protected ArrayList<Card> discard = new ArrayList<>();

    public void shuffle(){
        for(int i=0; i<cards.size()-1; i++){
            swap(i, (int)(Math.random()*(cards.size()-i)+i), cards);
        }
    }

    public void swap(int i, int j, ArrayList<Card> list){
        Card c = list.get(i);
        list.set(i, list.get(j));
        list.set(j, c);
    }

    public ArrayList<Card> draw(int i){
        ArrayList<Card> drawn = new ArrayList<>();
        for (int j = 0; j < i; j++){
            drawn.add(cards.remove(cards.size()-1));
        }
        return drawn;
    }

    public void discard(Card c){
        discard.add(c);
    }
}
