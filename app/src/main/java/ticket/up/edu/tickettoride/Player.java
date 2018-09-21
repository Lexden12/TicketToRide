package ticket.up.edu.tickettoride;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Player {
    private Hand hand;
    private String username;
    public Player(String name){
        username = name;
        hand = new Hand();
    }

    public void giveTrainCards(ArrayList<Card> cards){
        hand.addTrainCards(cards);
    }

    public void draw(Canvas c){
        hand.draw(c);
    }
}
