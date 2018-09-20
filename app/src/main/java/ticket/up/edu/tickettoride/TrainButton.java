package ticket.up.edu.tickettoride;

import android.graphics.Canvas;
import android.graphics.Color;

public class TrainButton {

    int topLeft;
    int topRight;
    int bottomLeft;
    int bottomRight;
    Color color;
    boolean highlight;

    public TrainButton(int topLeft, int topRight, int bottomLeft, int bottomRight, Color trainColor){
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.color = trainColor;
    }

    public void drawTrain(Canvas canvas){

    }

    public boolean isClicked(int c1, int c2, int c3, int c4){
        return false;
    }

    public void setHighlight(boolean highlight){
        this.highlight = highlight;
    }
}
