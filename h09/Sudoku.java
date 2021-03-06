package edu.ics211.h09;

import java.util.ArrayList;

/**
 * Class for recursively finding a solution to a Sudoku problem.
 *
 * @author Biagioni, Edoardo, Cam Moore
 * @date October 23, 2013
 * @missing solveSudoku and legalValues, to be implemented by the students in ICS 211
 */
public class Sudoku {

  /**
   * Find an assignment of values to sudoku cells that makes the sudoku valid.
   * @author Gavin Peng, 
   *        Partnered w/ Christine Nakano, Johnathon Means
   * 
   * @param sudoku to be solved
   * @return whether a solution was found if a solution was found, the sudoku is filled in with the solution if no
   * solution was found, restores the sudoku to its original value
   */
  public static boolean solveSudoku(int[][] sudoku) {
    //check if all cells are filled
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        //check if every cell is equal to 0
        if (sudoku[i][j] == 0) { 
          //this contains the list of valid integers
          ArrayList<Integer> solution = legalValues(sudoku, i, j);
          //loop through all the possible legal values
          for (int z = 0; z < solution.size(); z++) {             
            //set the thing in the cell to a legal value
            sudoku[i][j] = solution.get(z);
            //if the sudoku has been solved 
            if (solveSudoku(sudoku)) {
              return true;
            }
          }
          //if no solution was found set the cell back to 0
          sudoku[i][j] = 0; 
          return false;
        }
      }
    }
    //this will check the sudoku
    return checkSudoku(sudoku, true);
  }


  /**
   * Find the legal values for the given sudoku and cell.
   * @author Gavin Peng
   *        Partnered w/ Christine Nakano, Johnathon Means
   * @param sudoku the sudoku being solved.
   * @param row the row of the cell to get values for.
   * @param column the column of the cell.
   * @return an ArrayList of the valid values.
   */
  public static ArrayList<Integer> legalValues(int[][] sudoku, int row, int column) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    boolean legal;
    //if the cell is not empty
    if (sudoku[row][column] != 0) {
      return null;
    }
    //loop the entire sudoku
    for (int i = 0; i < sudoku.length; i++) {
      //go through every number from 1 - 9
      int numbers = i + 1;
      //check's if the legal value is true or not
      legal = true;
      //check every column
      for (int j = 0; j < sudoku.length; j++) {
        if (sudoku[j][column] == numbers) { 
          legal = false; 
        }
      }
      //check every row 
      for (int z = 0; z < sudoku.length; z++) {
        if (sudoku[row][z] == numbers) {
          legal = false;
        }
      }
      /* does it match any other value in the 3x3? */
      for (int k = 0; k < 3; k++) {
        for (int m = 0; m < 3; m++) {
          int testRow = (row / 3 * 3) + k; /* test this row */
          int testCol = (column / 3 * 3) + m; /* test this col */
          //chekc the 3 * 3's 
          if (sudoku[testRow][testCol] == numbers) {
            legal = false;
          }
        }
      }
      //if the values are valid 
      if (legal) {
        list.add(numbers);
      }
    }
    return list;
  }
  
  /**
   * checks that the sudoku rules hold in this sudoku puzzle. cells that contain 0 are not checked.
   *
   * @param the sudoku to be checked
   * @param whether to print the error found, if any
   * @return true if this sudoku obeys all of the sudoku rules, otherwise false
   */
  public static boolean checkSudoku(int[][] sudoku, boolean printErrors) {
    if (sudoku.length != 9) {
      if (printErrors) {
        System.out.println("sudoku has " + sudoku.length + " rows, should have 9");
      }
      return false;
    }
    for (int i = 0; i < sudoku.length; i++) {
      if (sudoku[i].length != 9) {
        if (printErrors) {
          System.out.println("sudoku row " + i + " has " + sudoku[i].length + " cells, should have 9");
        }
        return false;
      }
    }
    /* check each cell for conflicts */
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        int cell = sudoku[i][j];
        if (cell == 0) {
          continue; /* blanks are always OK */
        }
        if ((cell < 1) || (cell > 9)) {
          if (printErrors) {
            System.out.println("sudoku row " + i + " column " + j + " has illegal value " + cell);
          }
          return false;
        }
        /* does it match any other value in the same row? */
        for (int m = 0; m < sudoku.length; m++) {
          if ((j != m) && (cell == sudoku[i][m])) {
            if (printErrors) {
              System.out.println("sudoku row " + i + " has " + cell + " at both positions " + j + " and " + m);
            }
            return false;
          }
        }
        /* does it match any other value it in the same column? */
        for (int k = 0; k < sudoku.length; k++) {
          if ((i != k) && (cell == sudoku[k][j])) {
            if (printErrors) {
              System.out.println("sudoku column " + j + " has " + cell + " at both positions " + i + " and " + k);
            }
            return false;
          }
        }
        /* does it match any other value in the 3x3? */
        for (int k = 0; k < 3; k++) {
          for (int m = 0; m < 3; m++) {
            int testRow = (i / 3 * 3) + k; /* test this row */
            int testCol = (j / 3 * 3) + m; /* test this col */
            if ((i != testRow) && (j != testCol) && (cell == sudoku[testRow][testCol])) {
              if (printErrors) {
                System.out.println("sudoku character " + cell + " at row " + i + ", column " + j
                    + " matches character at row " + testRow + ", column " + testCol);
              }
              return false;
            }
          }
        }
      }
    }
    return true;
  }


  /**
   * Converts the sudoku to a printable string
   *
   * @param the sudoku to be converted
   * @param whether to check for errors
   * @return the printable version of the sudoku
   */
  public static String toString(int[][] sudoku, boolean debug) {
    if ((!debug) || (checkSudoku(sudoku, true))) {
      String result = "";
      for (int i = 0; i < sudoku.length; i++) {
        if (i % 3 == 0) {
          result = result + "+-------+-------+-------+\n";
        }
        for (int j = 0; j < sudoku.length; j++) {
          if (j % 3 == 0) {
            result = result + "| ";
          }
          if (sudoku[i][j] == 0) {
            result = result + "  ";
          } else {
            result = result + sudoku[i][j] + " ";
          }
        }
        result = result + "|\n";
      }
      result = result + "+-------+-------+-------+\n";
      return result;
    }
    return "illegal sudoku";
  }
}