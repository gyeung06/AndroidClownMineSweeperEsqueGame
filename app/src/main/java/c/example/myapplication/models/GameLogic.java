package c.example.myapplication.models;

import java.util.ArrayList;
import java.util.Collections;

//Code inspired from https://stackoverflow.com/questions/8115722/generating-unique-random-numbers-in-java for the mine generator
/*
Game logic class for the UI
 */

public class GameLogic {

    //states of button
    private final int HIDDEN_EMPTY = 0;
    private final int HIDDEN_BOMB = 1;
    private final int SHOW_EMPTY = 2;
    private final int SHOW_BOMB = 3;

    private int rows;
    private int cols;
    private int mines;

    private int[][] board;

    public int[][] getBoardState() {
        return board;
    }

    //Disable access to general constructor
    private GameLogic(){}

    public GameLogic(int rows, int cols, int mines, boolean freshStart) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        if (freshStart) {
            initialize();
        }
    }

    private void initialize() {
        int[][] board = new int[rows][cols];
        //Create and place mines
        ArrayList<Integer> minesList = new ArrayList<Integer>();
        for (int i = 0; i < cols*rows; i++) {
            minesList.add(i);
        }
        Collections.shuffle(minesList);
        for (int i = 0; i < mines; i++){
            int place = minesList.get(i);
            int placeRow = place/rows;
            int placeCols = place%cols;
            board[placeRow][placeCols] = HIDDEN_BOMB;
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] != HIDDEN_BOMB) {
                    board[i][j] = HIDDEN_EMPTY;
                }
            }
        }

    }
}
