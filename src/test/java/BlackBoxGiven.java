import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackBoxGiven {

    // Method that supplies instances of different classes to the parameterized test
    static Stream<Arguments> provideGuessingGameInstances() {
        return Stream.of(
                Arguments.of(new Game0()),
                Arguments.of(new Game1()),
                Arguments.of(new Game2()),
                Arguments.of(new Game3()),
                Arguments.of(new Game4())
        );
    }
    
    // Parameterized test that tests when a single letter guesses is within the correct word
    @DisplayName("Correct Letter Guess")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void correctLetter(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("l");
        assertEquals(1.1, response,0.01);
        assertEquals(11, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when a single letter guess is incorrect
    @DisplayName("Incorrect Letter Guess")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void incorrectLetter(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("z");
        assertEquals(1.0, response, 0.01);
        assertEquals(10, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is the correct word
    @DisplayName("Correct Guess")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void correctGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.01);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is a partial of the correct word
    @DisplayName("Partially Correct Guess")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void partialGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lio");
        assertEquals(3.0, response, 0.01);
        assertEquals(12, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is too long by one character
    @DisplayName("Guess Too Long")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void oneCharacterTooLong(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("horse");
        assertEquals(2.1, response, 0.01);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when a guess is too short by one character
    @DisplayName("Guess Too Short")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void oneCharacterTooShort(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("dog");
        assertEquals(2.2, response, 0.01);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is extraordinarily long
    @DisplayName("Guess Extra Long")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void extraLongWord(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("rhinocerous");
        assertEquals(2.1, response, 0.01);
        assertEquals(3, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is a symbol character
    @DisplayName("Guess is a Symbol")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void symbolGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("!@");
        assertEquals(4.1, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is a numeral character
    @DisplayName("Guess is a Numeral")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void numeralGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("123");
        assertEquals(4.1, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is a duplicate guess
    @DisplayName("Guess is a Duplicate")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void duplicateGuess(Game game) {
        game.initGame("lion", "Dr. M");

        game.makeGuess("dog");
        double response = game.makeGuess("dog");
        assertEquals(4.0, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests when the game is over due to 10 incorrect guesses
    @DisplayName("Guess Causes Game Over")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void gameOverGuess(Game game) {
        game.initGame("lion", "Dr. M");

        for (int i = 0; i < 9; i++) {
            game.makeGuess("dog"); 
        }
        double response = game.makeGuess("dog");
        assertEquals(5.0, response, 0.01);
        assertEquals(-7, game.getPoints());
        assertEquals(2, game.getGameStatus());
    }
    
    // Parameterized test that tests when the guess is made after the game is over
    @DisplayName("Guess After Game Over")
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void afterGameOverGuess(Game game) {
        game.initGame("lion", "Dr. M");

        for (int i = 0; i < 10; i++) {
            game.makeGuess("dog");
        }
        double response = game.makeGuess("dog");
        assertEquals(5.1, response, 0.01);
        assertEquals(2, game.getGameStatus());
    }
}