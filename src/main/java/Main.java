import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Word Guessing Game!");
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            
            Game game = new Game(playerName);
            
            while (true) {
                System.out.println("\nCurrent Points: " + game.getPoints());
                System.out.print("Enter your guess (or type 'exit' to quit): ");
                String guess = scanner.nextLine().trim();
                
                if (guess.equalsIgnoreCase("exit")) {
                    System.out.println("Thanks for playing!");
                    break;
                }

                double result = game.makeGuess(guess);
                handleResult(result, game);
                
                if (game.isGameOver()) {
                    System.out.println("Game over! The word was: " + game.getAnswer());
                    game.saveScore();
                    if (!restartGame(scanner)) {
                        break;
                    }
                    game = new Game(playerName);
                }
            }
        }
    }
    
    private static void handleResult(double result, Game game) {
        if (result == 0.0) {
            System.out.println("Congratulations! You guessed the word correctly!");
        } else if (result == 5.0) {
            System.out.println("You reached the maximum number of guesses. Game Over!");
        } else if (result == 5.1) {
            System.out.println("The game has ended. Start a new game to continue playing.");
        } else {
            System.out.println("Your guess was: " + interpretResult(result));
        }
    }

    private static String interpretResult(double result) {
        if (result == 1.0) return "Incorrect letter";
        if (result > 1.0 && result < 2.0) return "Letter appears " + (int) ((result - 1.0) * 10) + " times";
        if (result == 2.0) return "Incorrect word with correct length";
        if (result == 2.1) return "Guess is too long";
        if (result == 2.2) return "Guess is too short";
        if (result == 3.0) return "Partially correct word";
        if (result == 4.0) return "Repeated guess";
        if (result == 4.1) return "Invalid guess (symbols/numbers included)";
        return "Unknown result";
    }

    private static boolean restartGame(Scanner scanner) {
        System.out.print("Would you like to play again? (yes/no): ");
        return scanner.nextLine().trim().equalsIgnoreCase("yes");
    }
}
