package tircket.up.edu.tickettoride;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BoardView extends SurfaceView{
    private Bitmap board, blackTrain, blueTrain, greenTrain, orangeTrain, purpleTrain, rainbowTrain, redTrain, whiteTrain, yellowTrain;
    Rect src = new Rect();
    Rect dest = new Rect();
    Context context;
    Point screen;
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        board = BitmapFactory.decodeResource(getResources(), R.drawable.board);
        this.context = context;

        src.top = 20;
        src.left = 20;
        src.bottom = board.getHeight();
        src.right = board.getWidth()-20;//part of the bitmap we want to use
        dest.top = 0;
        dest.left = 0;
        dest.bottom = screen.y;
        dest.right = screen.x;//part of the screen we want to project onto
    }

    public void setScreen(Point point){
        screen = point;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBoard(canvas);
    }

    private void drawBoard(Canvas canvas){

        canvas.drawBitmap(board, src, dest, null);
    }

    private Rect calcScale(float scaleFactor, int x, int y, Rect src, Rect dest){
        double percentX = (double)x/(double)dest.width();//percent of way across screen
        double percentY = (double)y/(double)dest.height();
        double imageX = percentX*(double)src.width() + (double)src.left;//find equivalent point on bitmap
        double imageY = percentY*(double)src.height() + (double)src.top;
        double newWidth = ((double)board.getWidth()/(double)scaleFactor);//find the new width and height after scaling
        double newHeight = ((double)board.getHeight()/(double)scaleFactor);
        Rect subRect = new Rect();
        subRect.set((int) (imageX-(newWidth*percentX)),(int) (imageY-(newHeight*percentY)),(int) (imageX+(newWidth*(1-percentX))),(int) (imageY+(newHeight*(1-percentY))));//add or subtract the newWidth and Height from the new center location to calculate the new rectangle.
        return subRect;
    }
}
