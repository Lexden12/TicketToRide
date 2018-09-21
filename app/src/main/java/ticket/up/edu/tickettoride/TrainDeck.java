package ticket.up.edu.tickettoride;

import android.content.Context;
import android.graphics.BitmapFactory;

public class TrainDeck extends Deck {

    public TrainDeck(Context context){
        Card[] trainCards = {new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.black_card), "Black Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_card), "Blue Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.green_card), "Green Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.orange_card), "Orange Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.purple_card), "Purple Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.red_card), "Red Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.white_card), "White Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow_card), "Yellow Train"),
                new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.rainbow_card), "Rainbow Train")};
        for(int i = 0; i < 8; i++)
            for (int j = 0; j < 12; j++)
                cards.add(trainCards[i]);//add the regular cards
        for (int i = 0; i < 14; i++)
            cards.add(trainCards[8]);//add the rainbow cards
        shuffle();
    }
}
