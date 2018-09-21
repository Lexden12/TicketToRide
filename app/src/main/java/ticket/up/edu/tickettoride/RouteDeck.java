package ticket.up.edu.tickettoride;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.Arrays;

public class RouteDeck extends Deck {

    /**
     * Create a new Route Card Deck with all thirty destination cards.
     * @param context activity which gives us a context for our resources
     */
    public RouteDeck(Context context) {
        Card[] routeCards = { //need card images in resources to update names (30 routes total)
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest1), "Winnipeg - Little Rock"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest2), "Denver - Pittsburgh"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest3), "Portland - Phoenix"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest4), "Vancouver - Santa Fe"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest5), "Calgary - Phoenix"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest6), "Los Angeles - Miami"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest7), "Los Angeles - New York"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest8), "Boston - Miami"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest9), "San Francisco - Atlanta"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest10), "Portland - Nashville"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest11), "Vancouver - Montreal"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest12), "Seattle - New York"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest13), "Dallas - New York"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest14), "Los Angeles - Chicago"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest15), "Toronto - Miami"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest16), "Helena - Los Angeles"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest17), "Sault Ste. Marie - Nashville"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest18), "Duluth - Houston"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest19), "Seattle - Los Angeles"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest20), "Montreal - Atlanta"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest21), "Sault Ste. Marie - Oklahoma City"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest22), "Denver - El Paso"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest23), "New York - Atlanta"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest24), "Chicago - New Orleans"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest25), "Kansas City - Houston"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.dest26), "Calgary - Salt Lake City"),
            /*new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.new_york), "New York"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.new_york), "New York"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.new_york), "New York"),
            new Card(BitmapFactory.decodeResource(context.getResources(), R.drawable.new_york), "New York")*/
        };
        cards.addAll(Arrays.asList(routeCards));
        shuffle();
    }
}
