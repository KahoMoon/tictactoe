package com.example.tictactoe;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Stack;

/*Class that handles the visual part of the game*/
public class VisualBoard extends AppCompatActivity {

    /*the current turn*/
    private Game.TURN turn;

    /*current context of the game*/
    Context context;

    /*the main activity view*/
    View view;
    ConstraintLayout layout;

    /*holds the ids of the game piece views*/
    Stack<Integer> undoStack = new Stack<>();

    /*constructor*/
    public VisualBoard(Context context, View view) {
        this.context = context;
        this.view = view;
        this.layout = (ConstraintLayout) view;
    }

    /*removes the most recently placed piece from the board*/
    public Integer undo() {
        int removedId = undoStack.pop();
        layout.removeView(findViewById(removedId));
        return removedId;
    }

    /*adds an X piece*/
    public void addX(int x, int y) {
        ImageView gamePiece = new ImageView(context);
        gamePiece.setImageResource(R.drawable.x_piece);
        int sizeForScreen = (int) getGamePieceSize();
        scaleImage(gamePiece, sizeForScreen, sizeForScreen);

        gamePiece.setX(x);
        gamePiece.setY(y);
        gamePiece.setId(undoStack.push(View.generateViewId()));
        layout.addView(gamePiece);
    }

    /*adds an O piece*/
    private void addO(int x, int y) {
        ImageView gamePiece = new ImageView(context);
        gamePiece.setImageResource(R.drawable.o_piece);
        int sizeForScreen = (int) getGamePieceSize();
        scaleImage(gamePiece, sizeForScreen, sizeForScreen);

        gamePiece.setX(x);
        gamePiece.setY(y);
        gamePiece.setId(undoStack.push(View.generateViewId()));
        layout.addView(gamePiece);
    }

    /*adds the specified game piece*/
    public void addGamePiece(int x, int y, Game.TURN turn) {
        if (turn == Game.TURN.X) {
            addX(x, y);
        }
        else {
            addO(x, y);
        }
    }

    /*returns the ideal game piece size for the current screen resolution*/
    public static double getGamePieceSize() {
        return Math.floor(((getScreenWidth() - (getScreenWidth() * 0.05)) / 3));
    }

    /*returns the current screen width*/
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /*returns the current screen height*/
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /*scales the image to the specified width and height*/
    private void scaleImage(ImageView iv, int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        iv.setLayoutParams(params);
    }

}
