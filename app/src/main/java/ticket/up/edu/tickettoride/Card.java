package ticket.up.edu.tickettoride;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Card {
    private Bitmap img;
    private String name;
    private Rect src;
    private Rect dest;

    public Card(Bitmap bmp, String name){
        img = bmp;
        this.name = name;
        dest = new Rect();
        src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
    }

    public String getName() {
        return name;
    }

    public void setDest(Rect dest) {
        this.dest = dest;
    }

    public void draw(Canvas c){
        c.drawBitmap(img, src, dest, null);
    }
}
