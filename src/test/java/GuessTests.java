import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuessTests {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("TestPlayer");
    }

    @Test
    void testCorrectWordGuess() {
        String correctWord = game.getAnswer();
        assertEquals(0.0, game.makeGuess(correctWord), "Correct word guess should return 0.0");
        assertEquals(1, game.gameStatus, "Game should be marked as won");
    }

    @Test
    void testCorrectLetterGuess() {
        String correctWord = game.getAnswer();
        char letter = correctWord.charAt(0);
        double result = game.makeGuess(String.valueOf(letter));
        assertTrue(result >= 1.0, "Correct letter guess should return 1.x");
    }

    @Test
    void testIncorrectGuess() {
        assertEquals(1.0, game.makeGuess("z"), "Incorrect letter guess should return 1.0");
    }

    @Test
    void testRepeatedGuess() {
        game.makeGuess("a");
        assertEquals(4.0, game.makeGuess("a"), "Repeated guess should return 4.0 and deduct points");
    }

    @Test
    void testInvalidCharacterGuess() {
        assertEquals(4.1, game.makeGuess("123"), "Invalid input should return 4.1 and deduct points");
    }

    @Test
    void testGameOverAfterTenGuesses() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x" + i);
        }
        assertEquals(5.0, game.makeGuess("y"), "After 10 guesses, game should return 5.0");
        assertEquals(2, game.gameStatus, "Game status should be game over");
    }

    @Test
    void testGuessAfterGameOver() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x" + i);
        }
        game.makeGuess("y");
        assertEquals(5.1, game.makeGuess("z"), "Guess after game over should return 5.1");
    }
}
