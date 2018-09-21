package ticket.up.edu.tickettoride;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BoardView boardView;
    TrainDeck trainDeck;
    RouteDeck routeDeck;
    ArrayList<Card> publicCards = new ArrayList<>();
    Player player;
    Point screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = new Point();
        getWindowManager().getDefaultDisplay().getSize(screen);
        /**
         * External Citation
         Date:     14 September 2015
         Problem:  Could not get screen dimensions
         Resource: https://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels

         Solution: I used the example code from this
         post.
         */
        screen.y -= 50;
        boardView = findViewById(R.id.boardView);
        boardView.setScreen(screen);
        setBoard();
    }

    /**
     * Initializes the boardview
     */
    public void setBoard(){
        trainDeck = new TrainDeck(this);
        routeDeck = new RouteDeck(this);//RouteDeck is a stub
        player = new Player("Lexden");
        publicCards.addAll(trainDeck.draw(5));//the public cards always have five cards
        player.giveTrainCards(trainDeck.draw(10));//give the player 10 cards for this example
        player.giveRouteCards(routeDeck.draw(3));
        player.getHand().sort();
        int left = 0;
        int top = (int)(screen.y*0.8);
        int bottom = screen.y;
        int right = (int)(left+(bottom-top)/1.5);//keep 2:1 aspect ratio
        int space = (int)((left+right)*1.1);//give a 10% width padding between the public cards
        for(int i = 0; i < 5; i++){
            publicCards.get(i).setDest(new Rect(left + space*(i+1), top,
                    right + space*(i+1), bottom));
        }
        boardView.setPublicCards(publicCards);
        arrangeHand(player.getHand().getTrainCards().size());//call method to place the player's cards
        arrangeRoutes(player.getHand().getRouteCards().size());
        boardView.setPlayer(player);
    }

    /**
     * Calculates the corners of the rectangles for each of the cards in the player's hand
     * @param numCards the number of cards currently in the player's hand
     */
    public void arrangeHand(int numCards){
        int left = (int)(screen.x*0.8);//point at which the board ends
        int top = 0;
        int bottom = (int)(screen.y*0.2);//match the size of the cards defined above
        int right = (int)(left+(bottom-top)/1.5);//keep 2:1 aspect ratio
        int space = (screen.y-(bottom - top)*2/3)/numCards;//divide the available screen space evenly
        ArrayList<Card> cards = player.getHand().getTrainCards();//the cards to be placed
        for(int i = 0; i < numCards; i++){
            cards.get(i).setDest(new Rect(left, top + space*i,
                    right, bottom + space*i));
        }
        player.getHand().setTrainCards(cards);
    }

    public void arrangeRoutes(int numCards){
        int left = (int)(screen.x*0.885);//point at which the board ends
        int top = 0;
        int bottom = (int)(screen.y*0.12);//match the size of the cards defined above
        int right = (int)(left+(bottom-top)*1.5);//keep 2:1 aspect ratio
        int space = (screen.y-(bottom - top)*2/3)/numCards;//divide the available screen space evenly
        ArrayList<Card> cards = player.getHand().getRouteCards();//the cards to be placed
        for(int i = 0; i < numCards; i++){
            cards.get(i).setDest(new Rect(left, top + space*i,
                    right, bottom + space*i));
        }
        player.getHand().setRouteCards(cards);
    }
}
