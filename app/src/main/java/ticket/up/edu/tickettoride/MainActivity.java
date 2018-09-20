package ticket.up.edu.tickettoride;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BoardView boardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        /**
         * External Citation
         Date:     14 September 2015
         Problem:  Could not get screen dimensions
         Resource: https://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels

         Solution: I used the example code from this
         post.
         */
        boardView = findViewById(R.id.boardView);
        boardView.setScreen(point);
    }
}
