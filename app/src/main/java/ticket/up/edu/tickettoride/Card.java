package ticket.up.edu.tickettoride;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Card {
    private Bitmap img;
    private String name;
    private Rect src;
    private Rect dest;

    /**
     * Construct a new Card from scratch
     * @param bmp the bitmap to be used to draw the card on the board
     * @param name the name of the card to be used when comparing cards
     */
    public Card(Bitmap bmp, String name){
        img = bmp;
        this.name = name;
        dest = new Rect();
        src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
    }

    /**
     * Construct a new card by using an existing card
     * @param c the card whose instance variables we will copy from
     */
    public Card(Card c){
        this.img = c.img;
        this.name = c.name;
        this.dest = c.dest;
        src = new Rect(0, 0, img.getWidth(), img.getHeight());
    }

    public String getName() {
        return name;
    }

    public void setDest(Rect dest) {
        this.dest = dest;
    }

    /**
     * draw this card on the given canvas
     * @param c the canvas on which to draw this card
     */
    public void draw(Canvas c){
        c.drawBitmap(img, src, dest, null);
    }
}
