package ticket.up.edu.tickettoride;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Player {
    private Hand hand;
    private String username;

    /**
     * create a new player with a given username
     * @param name the username of the player
     */
    public Player(String name){
        username = name;
        hand = new Hand();
    }

    /**
     * adds the given train cards to the player's hand
     * @param cards cards to add to the player's hand
     */
    public void giveTrainCards(ArrayList<Card> cards){
        hand.addTrainCards(cards);
    }

    /**
     * adds the given route cards to the player's hand
     * @param cards cards to add to the player's hand
     */
    public void giveRouteCards(ArrayList<Card> cards){
        hand.addRouteCards(cards);
    }

    public Hand getHand() {
        return hand;
    }

    public String getUsername() {
        return username;
    }

    /**
     * draw the player's hand on the given canvas (follows down to the draw method of each card)
     * @param c canvas on which to draw the hand
     */
    public void draw(Canvas c){
        hand.draw(c);
    }
}
