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

    public ArrayList<Card> getRouteCards() {
        return routeCards;
    }

    public ArrayList<Card> getTrainCards() {
        return trainCards;
    }

    public void setRouteCards(ArrayList<Card> routeCards) {
        this.routeCards = routeCards;
    }

    public void setTrainCards(ArrayList<Card> trainCards) {
        this.trainCards = trainCards;
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

    public void sort() {
        /**
         * TO-DO: Sort cards in arraylist
         */
        for(int i = 0; i < trainCards.size(); i++){
            int index = minIndex(trainCards, i);
            if(index != i)
                swap(trainCards, i, index);
        }
    }

    private int minIndex(ArrayList<Card> cards, int l){
        String min = cards.get(l).getName();
        int index = l;
        for(int i = l; i < cards.size(); i++){
            if(min.compareTo(cards.get(i).getName()) < 0){
                min = cards.get(i).getName();
                index = i;
            }
        }
        return index;
    }

    private void swap(ArrayList<Card> cards, int index1, int index2){
        Card c = cards.get(index1);
        cards.set(index1, cards.get(index2));
        cards.set(index2, c);
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
