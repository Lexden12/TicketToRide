package ticket.up.edu.tickettoride;

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
    Rect boardSrc = new Rect();
    Rect boardDest = new Rect();
    Rect card1 = new Rect();
    Rect card2 = new Rect();
    Rect card3 = new Rect();
    Rect card4 = new Rect();
    Rect card5 = new Rect();
    Rect cardSrc = new Rect();
    Context context;
    Point screen;
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        board = BitmapFactory.decodeResource(getResources(), R.drawable.board);
        blackTrain = BitmapFactory.decodeResource(getResources(), R.drawable.black_card);
        blueTrain = BitmapFactory.decodeResource(getResources(), R.drawable.blue_card);
        greenTrain = BitmapFactory.decodeResource(getResources(), R.drawable.green_card);
        orangeTrain = BitmapFactory.decodeResource(getResources(), R.drawable.orange_card);
        purpleTrain = BitmapFactory.decodeResource(getResources(), R.drawable.purple_card);
        rainbowTrain = BitmapFactory.decodeResource(getResources(), R.drawable.rainbow_card);
        redTrain = BitmapFactory.decodeResource(getResources(), R.drawable.red_card);
        whiteTrain = BitmapFactory.decodeResource(getResources(), R.drawable.white_card);
        yellowTrain = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_card);
        this.context = context;

    }

    public void setScreen(Point point){
        screen = point;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boardSrc.set(20, 20, board.getWidth()-20, board.getHeight());//part of bitmap we want to use
        boardDest.set(0, 0, screen.x, screen.y);//part of the screen we want to project to
        cardSrc.set(0, 0, blackTrain.getWidth(), blackTrain.getHeight());//all cards are the same size
        int left = 0;
        int top = screen.y/2-150;
        int bottom = screen.y/2+150;
        int right = (int)(left+(bottom-top)*1.5);//keep 2:1 aspect ratio
        int space = (int)((left-right)*1.1);

        card1.set(left, top, right, bottom);
        card2.set(left-space, top, right-space, bottom);
        card3.set(left-space*2, top, right-space*2, bottom);
        card4.set(left-space*3, top, right-space*3, bottom);
        card5.set(left-space*4, top, right-space*4, bottom);
        canvas.drawBitmap(board, boardSrc, boardDest, null);//draw the board
        canvas.drawBitmap(blackTrain, cardSrc, card1, null);
        canvas.drawBitmap(blueTrain, cardSrc, card2, null);
        canvas.drawBitmap(redTrain, cardSrc, card3, null);
        canvas.drawBitmap(rainbowTrain, cardSrc, card4, null);
        canvas.drawBitmap(yellowTrain, cardSrc, card5, null);
    }

    public void zoomBoard(float scaleFactor, int x, int y){
        boardDest = calcScale(scaleFactor, x, y, boardSrc, boardDest, board);
        invalidate();
    }

    private Rect calcScale(float scaleFactor, int x, int y, Rect src, Rect dest, Bitmap bmp){
        double percentX = (double)x/(double)dest.width();//percent of way across screen
        double percentY = (double)y/(double)dest.height();
        double imageX = percentX*(double)src.width() + (double)src.left;//find equivalent point on bitmap
        double imageY = percentY*(double)src.height() + (double)src.top;
        double newWidth = ((double)bmp.getWidth()/(double)scaleFactor);//find the new width and height after scaling
        double newHeight = ((double)bmp.getHeight()/(double)scaleFactor);
        Rect subRect = new Rect();
        subRect.set((int) (imageX-(newWidth*percentX)),(int) (imageY-(newHeight*percentY)),(int) (imageX+(newWidth*(1-percentX))),(int) (imageY+(newHeight*(1-percentY))));//add or subtract the newWidth and Height from the new center location to calculate the new rectangle.
        if(subRect.left<0) {//handle edge cases (we can't have the subrect move outside of the bounds of the bitmap
            subRect.offsetTo(0,subRect.top);
        }
        if(subRect.top<0){
            subRect.offsetTo(subRect.left,0);
        }
        if(subRect.right>bmp.getWidth()){
            subRect.offsetTo((int) (bmp.getWidth()-newWidth),subRect.top);
        }
        if(subRect.bottom>bmp.getHeight()){
            subRect.offsetTo(subRect.left,(int) (bmp.getHeight()-newHeight));
        }
        return subRect;
    }
}
