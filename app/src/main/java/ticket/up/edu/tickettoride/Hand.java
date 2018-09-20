package ticket.up.edu.tickettoride;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Hand {
    private ArrayList<TrainCard> trainCards;
    private ArrayList<RouteCard> routeCards;
    public Hand(){
        trainCards = new ArrayList<>();
    }

    public void addTrainCards(ArrayList<TrainCard> cards){
        trainCards.addAll(cards);
    }

    public void addRouteCards(ArrayList<RouteCard> cards){
        routeCards.addAll(cards);
    }

    public ArrayList<TrainCard> discard(String name, int count) {
        ArrayList<TrainCard> discards = new ArrayList<>();
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
