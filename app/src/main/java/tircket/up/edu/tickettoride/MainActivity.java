package tircket.up.edu.tickettoride;

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
        boardView = findViewById(R.id.boardView);
        boardView.setSize(point);
    }
}
