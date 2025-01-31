import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    
    // Parameterized test that tests --------------------------12
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void correctLetter(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("l");
        assertEquals(1.1, response,0.01);
        assertEquals(11, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests -----------------------13
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void incorrectLetter(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("z");
        assertEquals(1.0, response, 0.01);
        assertEquals(10, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests ----------------------14
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void correctGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.01);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }
    
    // Parameterized test that tests ---------------------15
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void partialGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lio");
        assertEquals(3.0, response, 0.01);
        assertEquals(12, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests --------------------16
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void oneCharacterTooLong(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("horse");
        assertEquals(2.1, response, 0.01);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests ---------------------17
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void oneCharacterTooShort(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("dog");
        assertEquals(2.2, response, 0.01);
        assertEquals(9, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests -----------------------18
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void extraLongWord(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("rhinocerous");
        assertEquals(2.1, response, 0.01);
        assertEquals(3, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests -------------------------19
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void symbolGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("!@");
        assertEquals(4.1, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests -------------------------20
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void numeralGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("123");
        assertEquals(4.1, response, 0.01);
        assertEquals(7, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests ------------------------21
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void duplicateGuess(Game game) {
        game.initGame("lion", "Dr. M");

        game.makeGuess("dog");
        double response = game.makeGuess("dog");
        assertEquals(4.0, response, 0.01);
        assertEquals(8, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }
    
    // Parameterized test that tests ------------------------22
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void gameOverGuess(Game game) {
        game.initGame("lion", "Dr. M");

        for (int i = 0; i < 9; i++) {
            game.makeGuess("dog"); 
        }
        double response = game.makeGuess("dog");
        assertEquals(5.0, response, 0.01);
        assertEquals(-9, game.getPoints());
        assertEquals(2, game.getGameStatus());
    }
    
    // Parameterized test that tests ------------------------21
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void afterGameOverGuess(Game game) {
        game.initGame("lion", "Dr. M");

        for (int i = 0; i < 10; i++) {
            game.makeGuess("dog");
        }
        double response = game.makeGuess("dog");
        assertEquals(5.1, response, 0.01);
        assertEquals(-9, game.getPoints());
        assertEquals(2, game.getGameStatus());
    }
}