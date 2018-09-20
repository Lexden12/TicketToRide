package ticket.up.edu.tickettoride;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BoardView extends SurfaceView{
    private Bitmap board;
    private Bitmap[] trainCards = {BitmapFactory.decodeResource(getResources(), R.drawable.black_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.blue_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.green_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.orange_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.purple_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.rainbow_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.red_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.white_card),
            BitmapFactory.decodeResource(getResources(), R.drawable.yellow_card)};
    Bitmap[] publicCards = new Bitmap[5];
    Rect boardSrc = new Rect();
    Rect boardDest = new Rect();
    Rect trainDraw = new Rect();
    Rect card1 = new Rect();
    Rect card2 = new Rect();
    Rect card3 = new Rect();
    Rect card4 = new Rect();
    Rect card5 = new Rect();
    Rect cardSrc = new Rect();
    Rect[] buttons = new Rect[2];
    Context context;
    Point screen;
    boolean init = false;
    Paint paint;
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        board = BitmapFactory.decodeResource(getResources(), R.drawable.board);

        this.context = context;
        paint = new Paint();
    }

    public void setScreen(Point point){
        screen = point;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!init) {
            init();
            init = true;
        }
        canvas.drawBitmap(board, boardSrc, boardDest, null);//draw the board

        canvas.drawBitmap(publicCards[0], cardSrc, card1, null);
        canvas.drawBitmap(publicCards[1], cardSrc, card2, null);
        canvas.drawBitmap(publicCards[2], cardSrc, card3, null);
        canvas.drawBitmap(publicCards[3], cardSrc, card4, null);
        canvas.drawBitmap(publicCards[4], cardSrc, card5, null);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(buttons[0], paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(buttons[1], paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Save & Exit", buttons[0].left, buttons[0].bottom*2/3, paint);
        canvas.drawText("Help!", buttons[1].left+(buttons[1].right-buttons[1].left)/4, buttons[1].bottom*2/3, paint);
    }

    private void init(){
        boardSrc.set(20, 20, board.getWidth()-20, board.getHeight()-20);//part of bitmap we want to use
        boardDest.set(0, 0, (int)(screen.x*0.8), (int)(screen.y*0.8));//part of the screen we want to project to
        cardSrc.set(0, 0, trainCards[0].getWidth(), trainCards[0].getHeight());//all cards are the same size
        for(int i=0; i<5; i++){
            publicCards[i] = trainCards[(int)(trainCards.length*Math.random())];
        }
        int left = 0;
        int top = (int)(screen.y*0.8);
        int bottom = screen.y;
        int right = (int)(left+(bottom-top)/1.5);//keep 2:1 aspect ratio
        int space = (int)((left-right)*1.1);
        //zoomBoard(2, boardDest.right, boardDest.bottom);

        trainDraw.set(left, top, right, bottom);
        card1.set(left-space, top, right-space, bottom);
        card2.set(left-space*2, top, right-space*2, bottom);
        card3.set(left-space*3, top, right-space*3, bottom);
        card4.set(left-space*4, top, right-space*4, bottom);
        card5.set(left-space*5, top, right-space*5, bottom);
        buttons[0] = new Rect();
        buttons[0].set(boardDest.left, boardDest.top, boardDest.right/8, (int)(boardDest.top+(boardDest.right/8-boardDest.left)/2));
        buttons[1] = new Rect();
        buttons[1].set(boardDest.right*7/8, boardDest.top, boardDest.right, (int)(boardDest.top+(boardDest.right-boardDest.right*7/8)/2));
    }

    public Bitmap getBoard(){
        return board;
    }

    public boolean setBoard(Bitmap newBoard){
        if(board.getWidth() != newBoard.getWidth() || board.getHeight() != newBoard.getHeight())
            return false;
        board = newBoard;
        return true;
    }

    public void zoomBoard(float scaleFactor, int x, int y){
        if(scaleFactor < 1)
            return;
        if(scaleFactor > 3)
            scaleFactor = 3;
        boardSrc = calcScale(scaleFactor, x, y, boardSrc, boardDest, board);
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
