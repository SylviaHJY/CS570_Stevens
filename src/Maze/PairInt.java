package Maze;

import java.util.Objects;

/**
 * Assignment : 4
 * Name: Jiayin Huang
 * CWID: 10477088
 * Course: CS-570
 */

public class PairInt {
  private int x;
  private int y;

  public PairInt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX(){
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }


  @Override
  public boolean equals(Object p) {
    if (this == p) return true;
    if (p == null || getClass() != p.getClass()) return false;
    PairInt pairInt = (PairInt) p;
    return x == pairInt.x && y == pairInt.y;
  }

  @Override
  public String toString() {
    return "PairInt{" +
            "x=" + x +
            ", y=" + y +
            '}';
  }

  public PairInt copy(){
    PairInt pairInt = new PairInt(this.x,this.y);
    return pairInt;
  }
}
