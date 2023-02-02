package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Jiayin Huang
 *
 *  * Assignment : 4
 *  * Name: Jiayin Huang
 *  * CWID: 10477088
 *  * Course: CS-570
 *
 **/
public class Maze implements GridColors {

  /** The maze */
  private TwoDimGrid maze;

  public Maze(TwoDimGrid m) {
    maze = m;
  }

  /** Wrapper method. */
  public boolean findMazePath() {
    return findMazePath(0, 0); // (0, 0) is the start point.
  }

  /**
   * Attempts to find a path through point (x, y).
   * @pre Possible path cells are in BACKGROUND color;
   *      barrier cells are in ABNORMAL color.
   * @post If a path is found, all cells on it are set to the
   *       PATH color; all cells that were visited but are
   *       not on the path are in the TEMPORARY color.
   * @param x The x-coordinate of current point
   * @param y The y-coordinate of current point
   * @return If a path through (x, y) is found, true;
   *         otherwise, false
   */
  public boolean findMazePath(int x, int y) {
    // COMPLETE HERE FOR PROBLEM 1
    //If the current cell being analyzed falls outside the grid , false is returned
    if(x < 0 || y <0 || x >= maze.getNCols() || y >= maze.getNRows()){
      return false;

    //if current point cannot be part of the path, return false
    }else if(!maze.getColor(x,y).equals(NON_BACKGROUND)){
      return false;

    //if current point equals to exit, return true
    }else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1){
      maze.recolor(x,y,GridColors.PATH);
      return true;

    //Otherwise, the current cell may be assumed to be part of the path and paint color PATH.
    }else{
      maze.recolor(x,y,GridColors.PATH);

      //if the neighbour points of current point is exit, return true
      if(findMazePath(x - 1,y) || findMazePath(x + 1, y) || findMazePath(x, y+1) || findMazePath(x, y -1)){
        return true;

      // else, recolor current point to TEMPORARY
      }else{
        maze.recolor(x,y,GridColors.TEMPORARY);
        return false;
      }
    }

  }

  // ADD METHOD FOR PROBLEM 2 HERE

  // helper method 2.1 for problem 2, return All the paths with an Arraylist.
  public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x , int y ){
    if(x < 0 || x >= maze.getNCols() || y < 0 || y >= maze.getNRows()){
      System.out.println("X or Y is Invalid, out of bound");
      return null;
    }
    ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    Stack<PairInt> trace = new Stack<>();
    findMazePathStackBased(0,0,result,trace);
    return result;
  }

  // helper method 2.2 for problem 2
  //param x The x-coordinate of current point
  //param y The y-coordinate of current point
  public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
    // if x and y are out of bounds or not equal to red, then  the find method terminates
    if(x < 0 || x >= maze.getNCols() || y < 0 || y >= maze.getNRows()){
      System.out.println("X or Y is Invalid, out of bound");
      return;
    }
    if(maze.getColor(x,y).equals(NON_BACKGROUND)){
      return;

    // if exit is found, then push the exit point to trace, and then add to result
    // After visiting this point, remove from trace in the recursive call
    // Recolor this point to Non_background for Re-visiting
    }else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1){
      ArrayList<PairInt> temp = new ArrayList<>();
      temp.add(0, new PairInt(x, y));
      temp.addAll(0, trace);
      result.add(temp);
      return;
    }else{
      //Recursive method is called for the neighbors.
      trace.push(new PairInt(x,y));// push this point to trace
      maze.recolor(x,y,GridColors.PATH);
      findMazePathStackBased(x,y - 1, result, trace);
      findMazePathStackBased(x, y + 1, result, trace);
      findMazePathStackBased(x - 1, y, result, trace);
      findMazePathStackBased(x + 1, y, result, trace);
      trace.pop();
      maze.recolor(x, y, GridColors.NON_BACKGROUND);
    }
  }

  // ADD METHOD FOR PROBLEM 3 HERE
  // Find the min path
  public ArrayList<PairInt> findMazePathMin (int x, int y) {
    if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
      System.out.println("X or Y is Invalid, out of bound");
      return null;
    }

    //arrayList minPath is the result from find all possible paths
    ArrayList<ArrayList<PairInt>> allPaths = findAllMazePaths(x, y);
    int index = 0;
    int minSize = allPaths.get(index).size();
    for (int i = 1; i < allPaths.size(); i++) {
      if (allPaths.get(i).size() < minSize) {
        index = i;
        minSize = allPaths.get(i).size();
      }
    }
    // return the path which has the smallest index
    return allPaths.get(index);
  }

  /*<exercise chapter="5" section="6" type="programming" number="2">*/
  public void resetTemp() {
    maze.recolor(TEMPORARY, BACKGROUND);
  }
  /*</exercise>*/

  /*<exercise chapter="5" section="6" type="programming" number="3">*/
  public void restore() {
    resetTemp();
    maze.recolor(PATH, BACKGROUND);
    maze.recolor(NON_BACKGROUND, BACKGROUND);
  }
  /*</exercise>*/
}
/*</listing>*/
