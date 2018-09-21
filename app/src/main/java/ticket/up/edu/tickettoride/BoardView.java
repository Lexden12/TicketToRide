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

import java.util.ArrayList;

public class BoardView extends SurfaceView{
    private Bitmap board;
    Rect boardSrc = new Rect();
    Rect boardDest = new Rect();
    Rect[] buttons = new Rect[2];
    Player player;
    ArrayList<Card> publicCards = new ArrayList<>();
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

    /**
     * the player on this tablet (used to pass in the player's cards to be drawn)
     * @param p player received from the activity
     */
    public void setPlayer(Player p){
        player = p;
        invalidate();
    }

    /**
     * set the five cards that are face up that can be drawn by the player
     * @param cards the cards that are face up
     */
    public void setPublicCards(ArrayList<Card> cards){
        publicCards = cards;
        invalidate();
    }

    /**
     * used to pass in the screen dimensions of the device
     * @param point point containing the coordinates of the bottom right corner of the screen
     */
    public void setScreen(Point point){
        screen = point;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //method to initialize the rects used for the board and buttons
        if(!init) {
            init();
            init = true;
        }

        //draw the board
        canvas.drawBitmap(board, boardSrc, boardDest, null);//draw the board

        //draw the player's hand
        player.draw(canvas);

        //draw the face up cards
        for (Card card:publicCards) {
            card.draw(canvas);
        }

        //draw the buttons
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(buttons[0], paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(buttons[1], paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Save & Exit", buttons[0].left, buttons[0].bottom*2/3, paint);
        canvas.drawText("Help!", buttons[1].left+(buttons[1].right-buttons[1].left)/4, buttons[1].bottom*2/3, paint);
        paint.setTextSize(75);

        //write whose turn it is
        canvas.drawText("Turn: "+player.getUsername(), boardDest.right/2-100, 75, paint);
    }

    /**
     * prepares the board and buttons to be drawn
     */
    private void init(){
        boardSrc.set(20, 20, board.getWidth()-20, board.getHeight()-20);//part of bitmap we want to use
        boardDest.set(0, 0, (int)(screen.x*0.8), (int)(screen.y*0.8));//part of the screen we want to project to

        //zoomBoard(2, boardDest.right, boardDest.bottom);
        buttons[0] = new Rect();
        buttons[0].set(boardDest.left, boardDest.top, boardDest.right/8, (int)(boardDest.top+(boardDest.right/8-boardDest.left)/2));
        buttons[1] = new Rect();
        buttons[1].set(boardDest.right*7/8, boardDest.top, boardDest.right, (int)(boardDest.top+(boardDest.right-boardDest.right*7/8)/2));
    }

    public Bitmap getBoard(){
        return board;
    }

    /**
     * set the board to the given bitmap (planned for use with updating the board when routes are claimed)
     * @param newBoard the new bitmap of the board to display
     * @return whether the board was successfully overwritten (will return false if the new board
     *          is not the same dimensions as the old board
     */
    public boolean setBoard(Bitmap newBoard){
        if(board.getWidth() != newBoard.getWidth() || board.getHeight() != newBoard.getHeight())
            return false;
        board = newBoard;
        invalidate();
        return true;
    }

    /**
     * publically used to zoom in on the board
     * @param scaleFactor the amount to zoom in by
     * @param x the x coordinate of the center of the zoom
     * @param y the y coordinate of the center of the zoom
     */
    public void zoomBoard(float scaleFactor, int x, int y){
        if(scaleFactor < 1)
            return;
        if(scaleFactor > 3)
            scaleFactor = 3;
        boardSrc = calcScale(scaleFactor, x, y, boardSrc, boardDest, board);
        invalidate();
    }

    /**
     * used to calculate the new "source rect" for the board in order to zoom properly
     * @param scaleFactor the amount to zoom in by
     * @param x the x coordinate of the center of the zoom
     * @param y the y coordinate of the center of the zoom
     * @param src the rect representing the area of the bitmap to be drawn
     * @param dest the rect representing the area on the screen on which the bitmap will be drawn
     * @param bmp the bitmap which is being zoomed in on
     * @return the new source rect to be used which is zoomed
     */
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
