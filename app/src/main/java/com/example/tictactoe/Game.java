package com.example.tictactoe;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.*;

/*Class that handles the logical part of the game*/
public class Game extends AppCompatActivity {

    /*enum for the possible turns*/
    public enum TURN {
        X,
        O,
        NONE
    }

    private final TURN[][] board;   //game board
    private TURN turn;  //turn

    VisualBoard vb;
    View view;
    ConstraintLayout layout;
    Context context;

    /*holds the ids of the game piece views*/
    Stack<Integer> undoStack = new Stack<>();


    /*default Game constructor*/
    public Game(Context context, View view) {
        this.context = context;
        this.view = view;
        this.layout = (ConstraintLayout) view;
        vb = new VisualBoard(context, view);

        Button test = layout.findViewById(R.id.undo_button);
        test.setOnClickListener(view1 -> {
            undo();
            Log.d("testing undo button", "testing the undo button");
        });

        board = new TURN[3][3];
        for (int column = 0; column <= 2; column++){
            for (int row = 0; row <= 2; row++) {
                board[column][row] = TURN.NONE;
            }
        }
        whoGoesFirst();
    }

    /*removes the most recently placed piece from the board*/
    public void undo() {
        if (!undoStack.isEmpty()) {
            int removedYCoordinate = undoStack.pop();
            int removedXCoordinate = undoStack.pop();
            board[removedXCoordinate][removedYCoordinate] = TURN.NONE;
        }

        vb.undo();
    }

    /*returns X or O depending on which player's turn it is*/
    public TURN getTurn() {
        return turn;
    }

    /*switches turn to other player*/
    public void switchTurns() {
        if (turn == TURN.X) {
            turn = TURN.O;
        } else {
            turn = TURN.X;
        }
    }

    /*adds an X piece at (x, y)*/
    public void addX(int x, int y) {
        if (x < 3 && y < 3) {
            board[x][y] = TURN.X;
        }
    }

    /*adds a O piece at (x, y)*/
    public void addO(int x, int y) {
        if (x < 3 && y < 3) {
            board[x][y] = TURN.O;
        }
    }

    public void addGamePiece(int boardXCoordinate, int boardYCoordinate, int vbXCoordinate, int vbYCoordinate, TURN turn) {
        if (turn == TURN.X) {
            addX(boardXCoordinate, boardYCoordinate);
            vb.addGamePiece(vbXCoordinate, vbYCoordinate, turn);
        }
        else {
            addO(boardXCoordinate, boardYCoordinate);
            vb.addGamePiece(vbXCoordinate, vbYCoordinate, turn);
        }
    }

    /*clears the game board of X and O pieces*/
    public void clearBoard() {
        for (int column = 0; column <= 2; column++){
            for (int row = 0; row <= 2; row++) {
                board[column][row] = TURN.NONE;
            }
        }
        vb.clearBoard();
    }

    /*determines who goes first*/
    public TURN whoGoesFirst() {
        if (randomOneOrZero() == 0) {
            turn = TURN.X;
        }
        else {
            turn = TURN.O;
        }
        return turn;
    }

    /*manually sets the player who goes first*/
    public void whoGoesFirst(TURN person) {
        turn = person;
    }

    /*returns a random 1 or 0*/
    private int randomOneOrZero() {
        if (Math.random() < 0.5) {
            return 0;
        }
        return 1;
    }

}
