package edu.ics211.h09;

import org.junit.jupiter.api.BeforeEach;

/**
 * Represents a SudokuTest2.
 * @author Gavin Peng
 *        Partnered w/ Christine Nakano, Johnathon Means, Londy Tong-Lee
 *
 *        Brief Discussion: 
 *        The testing strategy that I used was taking the already created Sudoku's in the Sudoku 
 *        test.
 *        I then just changed one number to an incorrect number and checked if my methods work.
 *        If it dosen't pass it will print an error.
 */
class SudokuTest2 {

  /**
   * The testing methods.
   *
   */
  @BeforeEach
  void setUp() throws Exception {
  }
  
  /**
   * Test the two methods created.
   * @param args the args.
   */
  public static void main(String[] args) {
    int[][] solution = {
        { 7, 8, 0, 0, 9, 0, 0, 2, 0 }, { 1, 0, 0, 0, 0, 0, 9, 6, 4 }, { 0, 0, 0, 2, 5, 1, 0, 0, 0 },
        { 0, 0, 6, 1, 8, 5, 0, 0, 0 }, { 5, 0, 4, 0, 0, 0, 3, 0, 2 }, { 0, 0, 0, 3, 4, 2, 5, 0, 0 },
        { 0, 0, 0, 9, 6, 3, 0, 0, 0 }, { 6, 4, 1, 0, 0, 0, 0, 0, 3 }, 
        { 0, 9, 0, 0, 1, 0, 0, 5, 7 }  
    };
    
    // test if solveSudoku works 
    if (Sudoku.solveSudoku(solution)) {
      System.out.println("Solution is correct \n");
    }
    
    // copy of solution but changed first value of 7 to 6
    int[][] solution2 = {
        { 6, 6, 5, 9, 4, 8, 2, 1, 3 }, { 1, 2, 4, 5, 3, 6, 7, 8, 9 }, { 8, 9, 3, 7, 2, 1, 4, 5, 6 },
        { 2, 4, 7, 1, 6, 3, 5, 9, 8 }, { 9, 3, 6, 2, 8, 5, 1, 7, 4 }, { 5, 8, 1, 4, 7, 9, 3, 6, 2 },
        { 3, 7, 8, 6, 1, 4, 9, 2, 5 }, { 4, 5, 2, 8, 9, 7, 6, 3, 1 }, { 6, 1, 9, 3, 5, 2, 8, 4, 7 }
    };
    
    if (Sudoku.solveSudoku(solution2)) {
      //we don't want this
      System.out.println("Solve Sudoku incorrect, passed test when it should have failed \n");
    } else { 
      //we want this
      System.out.println("Solve Sudoku has passed for a bad case \n");
    }
    
    //copy of solution2 but changed the first value of 7 to 0
    int[][] solution3 = {
        { 0, 6, 5, 9, 4, 8, 2, 1, 3 }, { 1, 2, 4, 5, 3, 6, 7, 8, 9 }, { 8, 9, 3, 7, 2, 1, 4, 5, 6 },
        { 2, 4, 7, 1, 6, 3, 5, 9, 8 }, { 9, 3, 6, 2, 8, 5, 1, 7, 4 }, { 5, 8, 1, 4, 7, 9, 3, 6, 2 },
        { 3, 7, 8, 6, 1, 4, 9, 2, 5 }, { 4, 5, 2, 8, 9, 7, 6, 3, 1 }, 
        { 6, 1, 9, 3, 5, 2, 8, 4, 7 }  
    };
    
    //the value at position (0, 0) should be 7
    System.out.println("Checking legal values of position (0, 0)");
    //make sure that the value at 0 is 7 
    if (Sudoku.legalValues(solution3, 0, 0).get(0) == 7) { 
      System.out.println("The legal value given is 7, which is correct \n");
    } else { 
      System.out.println("The legal value given is incorrect should be 7 \n");
    }
    
    // the value at position (0, 1) should be null
    System.out.println("Checking legal values of position (0, 1)");
    // it should return null because there is already a value, no legal values needed 
    if (Sudoku.legalValues(solution3, 0, 1) == null) {
      System.out.println("There are no legal values and it returned null \n");
    } else { 
      System.out.println("It did not return null, which is incorrect \n");
    }
    
    //copied from sudoku test 
    int[][] example = { 
        { 7, 8, 0, 0, 9, 0, 0, 2, 0 }, { 1, 0, 0, 0, 0, 0, 9, 6, 4 }, { 0, 0, 0, 2, 5, 1, 0, 0, 0 },
        { 0, 0, 6, 1, 8, 5, 0, 0, 0 }, { 5, 0, 4, 0, 0, 0, 3, 0, 2 }, { 0, 0, 0, 3, 4, 2, 5, 0, 0 },
        { 0, 0, 0, 9, 6, 3, 0, 0, 0 }, { 6, 4, 1, 0, 0, 0, 0, 0, 3 }, { 0, 9, 0, 0, 1, 0, 0, 5, 7 }
    };
    
    // called solveSudoku to get rid of the zeros 
    if (Sudoku.solveSudoku(example)) { 
      for (int i = 0; i < example.length; i++) {
        for (int j = 0; j < example.length; j++) {
          if (example[i][j] == 0) {
            //should be no zeros left 
            System.out.println("Found a zero");
          }
        }
      }
      // print the solved sudoku
      System.out.println(Sudoku.toString(example, true));
    }
  }
}
