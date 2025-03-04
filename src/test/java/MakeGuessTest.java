import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MakeGuessTest {
    
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
}