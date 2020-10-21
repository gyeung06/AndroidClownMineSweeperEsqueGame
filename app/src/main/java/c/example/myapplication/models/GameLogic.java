package c.example.myapplication.models;

import java.util.ArrayList;
import java.util.Collections;

//Code inspired from https://stackoverflow.com/questions/8115722/generating-unique-random-numbers-in-java for the mine generator
/*
Game logic class for the UI
 */

public class GameLogic {

    //states of button
    public static final int HIDDEN_SCAN = 0;
    public static final int HIDDEN_BOMB = 1;
    public static final int SHOW_SCAN = 2;
    public static final int SHOW_BOMB = 3;
    public static final int SHOW_BOMB_SCAN = 4;

    private int rows;
    private int cols;
    private int mines;

    private int[][] board;

    //Disable access to general constructor
    private GameLogic(){}

    public GameLogic(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;

        board = new int[rows][cols];
    }

    public void initialize() {
        //Create and place mines
        ArrayList<Integer> minesList = new ArrayList<Integer>();
        for (int i = 0; i < cols*rows; i++) {
            minesList.add(i);
        }
        Collections.shuffle(minesList);
        for (int i = 0; i < mines; i++){
            int place = minesList.get(i);
            int placeRow = place/cols;
            int placeCols = place%cols;
            board[placeRow][placeCols] = HIDDEN_BOMB;
        }
    }

    public int getState (int curRow, int curCol){
        return board[curRow][curCol];
    }

    public void changeState (int curRow, int curCol, int state) {
        board[curRow][curCol] = state;
    }

    public int minesLeft(){
        mines--;
        return mines;
    }

    //Will count itself in the scan value if the state is still hidden bomb for any reason
    public int scanValue (int curRow, int curCol) {
        int count = 0;
        for (int i = 0; i < cols; i++){
            if (board[curRow][i] == HIDDEN_BOMB){
                count++;
            }
        }
        for (int j = 0; j < rows; j++){
            if (board[j][curCol] == HIDDEN_BOMB){
                count++;
            }
        }
        return count;
    }
}
