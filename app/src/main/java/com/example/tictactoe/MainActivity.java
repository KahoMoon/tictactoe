package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    VisualBoard test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View main = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(main);
        test = new VisualBoard(MainActivity.this, main);
        //test.addX((int) ((VisualBoard.getScreenWidth() / 2) - (VisualBoard.getGamePieceSize() / 2)), 515);
        Log.d("width", String.valueOf((int) (VisualBoard.getScreenWidth() - (VisualBoard.getGamePieceSize() / 2))));
    }

    private void addXImage(int x, int y) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                test.addX(x - 170, y - 340);
        }

        return false;
    }

}