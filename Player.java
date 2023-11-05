/*
 * Activity 2.5.2
 * 
 * A Player class the PhraseSolverGame
 */

import java.util.Scanner;

public class Player {
  private String name;
  private int points;
  private int maxIncorrectGuesses;

  public Player(String name, int maxIncorrectGuesses) {
    this.name = name;
    this.maxIncorrectGuesses = maxIncorrectGuesses;
    points = 0;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {
    return points;
  }

  public void addPoints(int value) {
    points += value;
  }

  public void setName(String newName) {
    name = newName;
  }
}
