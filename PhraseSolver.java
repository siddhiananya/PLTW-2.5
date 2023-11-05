
/*
 * Activity 2.5.2
 *
 *  The PhraseSolver class for the PhraseSolverGame
 */
import java.util.Scanner;

public class PhraseSolver {
  private Player player1; // Assuming Player is a class.
  private Player player2;
  private Board board; // Assuming Board is a class.
  private boolean solved;

  public PhraseSolver(String player1Name, String player2Name) {
    player1 = new Player(player1Name, 6);
    player2 = new Player(player2Name, 6);
    board = new Board();
    solved = false;

    System.out.println("Hello and welcome to the game, " + player1.getName() + " and " + player2.getName());
    System.out.println("You each have a maximum of 6 guesses to correctly guess the phrase.");
  }

  public void play() {
    int currentPlayer = 1;
    String currentName = "";
    String phrase = board.getPhrase();

    Scanner input = new Scanner(System.in);

    boolean correct = false;
    while (!correct) {
      String temp = board.getSolvedPhrase();
      System.out.println("Current State: " + temp);

      // Prompt the player to enter a letter or the final phrase
      System.out.println("Enter a letter or the final phrase:");
      String guess = input.nextLine();

      if (board.guessLetter(guess)) {
        System.out.println("Correct!");
      } else {
        System.out.println("Incorrect! " + guess + " is not in the phrase.");
        if (currentPlayer == 1) {
          currentPlayer = 2;
          currentName = player2.getName();
        } else {
          currentPlayer = 1;
          currentName = player1.getName();
        }
      }

      // Check if all letters are guessed correctly
      if (board.getSolvedPhrase().equals(phrase)) {
        correct = true;
        System.out.println("Congrats Player " + currentName + "! You won!");
        System.out.println("Phrase: " + phrase);
      }

      // Check if the maximum incorrect guesses have been reached
      if (board.getIncorrectGuesses() >= board.getMaxIncorrectGuesses()) {
        correct = true;
        System.out.println("Max incorrect guesses reached. The game is over.");
        System.out.println("The correct phrase was: " + phrase);
      }
    }
  }
}
