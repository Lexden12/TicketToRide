package ticket.up.edu.tickettoride;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Card {
    private Bitmap img;
    private String name;
    private Rect src;
    private Rect dest;
    private boolean isDrawn;

    public Card(Bitmap bmp, String name){
        img = bmp;
        this.name = name;
        dest.set(0, 0, 0, 0);
        src.set(0, 0, bmp.getWidth(), bmp.getHeight());
        isDrawn = false;
    }

    public String getName() {
        return name;
    }

    public void setDest(Rect dest) {
        this.dest = dest;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void draw(Canvas c){
        if(isDrawn)
            c.drawBitmap(img, src, dest, null);
    }
}
