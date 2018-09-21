package ticket.up.edu.tickettoride;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> trainCards;
    private ArrayList<Card> routeCards;
    public Hand(){
        trainCards = new ArrayList<>();
        routeCards = new ArrayList<>();
    }

    public void addTrainCards(ArrayList<Card> cards){
        trainCards.addAll(cards);
    }

    public void addRouteCards(ArrayList<Card> cards){
        routeCards.addAll(cards);
    }

    public ArrayList<Card> discard(String name, int count) {
        ArrayList<Card> discards = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            boolean hasCard = false;
            for (int j = 0; j < trainCards.size(); j++) {
                if (trainCards.get(j).getName().equals(name)) {
                    discards.add(trainCards.remove(j));
                    hasCard = true;
                    break;
                }
            }
            if(!hasCard) {
                trainCards.addAll(discards);
                sort();
                return null;
            }
        }
        return discards;
    }

    public void sort(){
        /**
         * TO-DO: Sort cards in arraylist
         */
    }

    public void draw(Canvas c){
        for (Card card:trainCards) {
            card.draw(c);
        }
        for (Card card:routeCards) {
            card.draw(c);
        }
    }
}
