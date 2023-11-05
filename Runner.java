
/*
 * Activity 2.5.2
 *
 * The runner for the PhraseSolverGame
 */
import java.util.Scanner;

public class Runner {
  public static void main(String[] args) {
    // Prompt the user to enter the names of the first and second players
    System.out.println("Enter first player name:");
    Scanner sc = new Scanner(System.in);
    String nameOne = sc.nextLine();

    System.out.println("Enter second player name");
    String nameTwo = sc.nextLine();

    // Create a PhraseSolver game with the provided player names and start the game
    PhraseSolver p = new PhraseSolver(nameOne, nameTwo);
    p.play();
  }

}
