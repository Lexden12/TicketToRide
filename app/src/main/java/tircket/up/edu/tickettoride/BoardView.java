package tircket.up.edu.tickettoride;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BoardView extends SurfaceView{
    private Bitmap bmp;
    Rect src = new Rect();
    Rect dest = new Rect();
    Context context;
    Point size;
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.board);
        this.context = context;
    }

    public void setSize(Point point){
        size = point;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        src.top = 20;
        src.left = 20;
        src.bottom = bmp.getHeight();
        src.right = bmp.getWidth()-20;
        dest.top = 0;
        dest.left = 0;
        dest.bottom = size.y;
        dest.right = size.x;
        canvas.drawBitmap(bmp, src, dest, null);
    }
}
