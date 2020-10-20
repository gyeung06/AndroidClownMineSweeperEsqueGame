package c.example.myapplication.models;

/*
Singleton class to carry options throughout the app
 */

public class OptionsData {
    private int rows = 4;
    private int cols = 6;
    private int mines = 6;

    private static  OptionsData instance;

    //Overriding the default constructor
    private OptionsData(){}

    public void setRowsAndCols(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public static OptionsData getInstance() {
        if (instance == null) {
            instance = new OptionsData();
        }
        return instance;
    }
}
