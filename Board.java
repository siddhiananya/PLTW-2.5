import java.util.Scanner;
import java.io.File;

public class Board {
  private String solvedPhrase;
  private String phrase;
  private int currentLetterValue;
  private int incorrectGuesses; // Add a field to keep track of incorrect guesses
  private boolean phraseDisplayed; // Add a field to track whether the phrase has been displayed
  private int maxIncorrectGuesses;

  /* Constructor */
  public Board() {
    solvedPhrase = "";
    phrase = loadPhrase(); // Load a phrase from a file
    setLetterValue(); // Set a random letter value
    phraseDisplayed = false; // Initialize to false
    incorrectGuesses = 0; // Initialize to 0
    maxIncorrectGuesses = 6; // Set the maximum number of incorrect guesses
  }

  /* Accessor(s) */
  public String getPhrase() {
    return phrase;
  }

  public int getLetterValue() {
    return currentLetterValue;
  }

  public String getSolvedPhrase() {
    return solvedPhrase;
  }

  public int getIncorrectGuesses() { // Add a getter method for incorrect guesses
    return incorrectGuesses;
  }

  public int getMaxIncorrectGuesses() {
    return maxIncorrectGuesses;
  }

  /* Mutator(s) */
  public void setLetterValue() {
    // Generate a random letter value
    int randomInt = (int) ((Math.random() * 10) + 1) * 100;
    currentLetterValue = randomInt;
  }

  public boolean isSolved(String guess) {
    if (phrase.equals(guess)) {
      return true; // The guessed phrase matched the actual phrase
    }
    return false; // The guessed phrase does not match
  }

  private String loadPhrase() {
    String tempPhrase = "";

    int numOfLines = 0;
    try {
      Scanner sc = new Scanner(new File("ReviewRunner/phrases.txt")); // Read phrases from file
      while (sc.hasNextLine()) {
        tempPhrase = sc.nextLine().trim();
        numOfLines++;
      }
    } catch (Exception e) {
      System.out.println("Error reading or parsing phrases.txt");
    }

    int randomInt = (int) ((Math.random() * numOfLines) + 1);

    try {
      int count = 0;
      Scanner sc = new Scanner(new File("ReviewRunner/phrases.txt"));
      while (sc.hasNextLine()) {
        count++;
        String temp = sc.nextLine().trim();
        if (count == randomInt) {
          tempPhrase = temp;
        }
      }
    } catch (Exception e) {
      System.out.println("Error reading or parsing phrases.txt");
    }

    for (int i = 0; i < tempPhrase.length(); i++) {
      if (tempPhrase.substring(i, i + 1).equals(" ")) {
        solvedPhrase += "  ";
      } else {
        solvedPhrase += "_ ";
      }
    }

    return tempPhrase;
  }

  /*
   * Checks to see if the letter is in the phrase
   * Precondition:
   * the user entered a valid letter as a guess
   * 
   * Postcondition:
   * the boolean is populated
   * Solved phrase should be populated with a new phrase if the guess
   * is correct
   */
  public boolean guessLetter(String guess) {
    // Check to see if the guess is the final phrase
    boolean foundLetter = false;
    StringBuilder newSolvedPhrase = new StringBuilder(solvedPhrase);

    for (int i = 0; i < phrase.length(); i++) {
      // if the letter is in the phrase, add it to the new phrase
      if (phrase.substring(i, i + 1).equals(guess)) {
        // if the letter is found in the word then it's added to newSolvedPhrase
        newSolvedPhrase.setCharAt(i * 2, guess.charAt(0));
        foundLetter = true;
      }
    }

    // updates solvedPhrase and returns foundLetter
    solvedPhrase = newSolvedPhrase.toString();

    // Check if the guess was incorrect and increment the incorrect guess count
    if (!foundLetter) {
      incorrectGuesses++;
      if (incorrectGuesses >= maxIncorrectGuesses && !phraseDisplayed) {
        phraseDisplayed = true;
      }
    }

    return foundLetter;
  }
}