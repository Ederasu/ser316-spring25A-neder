
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuessTests { //SER316 TASK 2 SPOT-BUGS FIX

    // Tests when a single letter guesses is within the correct word
    @Test
    @DisplayName("Correct Letter Guess")
    void correctLetter() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("l");
        assertEquals(1.1, response, 0.01);
        assertEquals(11, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when a single letter guess is incorrect
    @Test
    @DisplayName("Incorrect Letter Guess")
    void incorrectLetter() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("z");
        assertEquals(1.0, response, 0.01);
        assertEquals(10, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when the guess is the correct word
    @Test
    @DisplayName("Correct Word Guess")
    void correctGuess() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.01);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    // Tests when the guess is a partial of the correct word
    @Test
    @DisplayName("Partially Correct Word Guess")
    void partialGuess() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("lio");
        assertEquals(3.0, response, 0.01);
        assertEquals(12, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when the guess is too long by one character
    @Test
    @DisplayName("Guess Too Long")
    void oneCharacterTooLong() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("horse");
        assertEquals(2.1, response, 0.01);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when a guess is too short by one character
    @Test
    @DisplayName("Guess Too Short")
    void oneCharacterTooShort() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("dog");
        assertEquals(2.2, response, 0.01);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when the guess is extraordinarily long
    @Test
    @DisplayName("Guess Extra Long")
    void extraLongWord() {
        Game game = new Game("lion", "Dr. M");
        double response = game.makeGuess("rhinocerous");
        assertEquals(2.1, response, 0.01);
        assertEquals(3, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when the guess is a symbol character
    @Test
    @DisplayName("Guess is a Symbol")
    void symbolGuess() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("!@");
        assertEquals(4.1, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when the guess is a numeral character
    @Test
    @DisplayName("Guess is a Numeral")
    void numeralGuess() {
        Game game = new Game("lion", "Player");
        double response = game.makeGuess("123");
        assertEquals(4.1, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    // Tests when the guess is a duplicate guess
    @Test
    @DisplayName("Guess is a Duplicate")
    void duplicateGuess() {
        Game game = new Game("lion", "Player");
        game.makeGuess("dog");
        double response = game.makeGuess("dog");
        assertEquals(4.0, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    /*
    // Tests when the game is over due to 10 incorrect guesses
    @Test
    @DisplayName("Guess Causes Game Over")
    void gameOverGuess() {
        Game game = new Game("lion", "Player");
        for (int i = 0; i < 9; i++) {
            game.makeGuess("dog");
        }
        double response = game.makeGuess("dog");
        assertEquals(5.0, response, 0.01);
        assertEquals(-7, game.getPoints());
        assertEquals(2, game.getGameStatus());
    }
    
     // Tests when the guess is made after the game is over
    @Test
    @DisplayName("Guess After Game Over")
    void afterGameOverGuess() {
        Game game = new Game("lion", "Player");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("dog");
        }
        double response = game.makeGuess("dog");
        assertEquals(5.1, response, 0.01);
        assertEquals(2, game.getGameStatus());
    }
     */
}
