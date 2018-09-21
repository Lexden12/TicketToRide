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
        boardView = findViewById(R.id.boardView);
        boardView.setScreen(screen);
        setBoard();
    }

    public void setBoard(){
        trainDeck = new TrainDeck(this);
        routeDeck = new RouteDeck();//RouteDeck is a stub
        player = new Player("Lexden");
        publicCards.addAll(trainDeck.draw(5));
        player.giveTrainCards(trainDeck.draw(5));
        int left = 0;
        int top = (int)(screen.y*0.8);
        int bottom = screen.y;
        int right = (int)(left+(bottom-top)/1.5);//keep 2:1 aspect ratio
        int space = (int)((left+right)*1.1);
        for(int i = 0; i < 5; i++){
            publicCards.get(i).setDest(new Rect(left + space*(i+1), top, right + space*(i+1), bottom));
        }
        boardView.setPublicCards(publicCards);
        boardView.setPlayer(player);
        boardView.setPublicCards(publicCards);
    }
}
