import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

class Game {
    private int points;
    private String name;
    private String answer;
    private static final Random random = new Random();
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private static final List<String> words = new ArrayList<>(Arrays.asList("dog", "horse", "pony", "cat", "lion", "bear", "lioncub"));
    private static final Set<String> usedWords = new HashSet<>();
    protected int gameStatus = 0;
    private List<String> guesses = new ArrayList<>();

    public Game(String name) {
        this.name = name;
        this.points = 10;
        this.answer = getRandomWord();
    }

    private String getRandomWord() {
        if (usedWords.size() == words.size()) {
            usedWords.clear();
        }
        List<String> availableWords = words.stream().filter(w -> !usedWords.contains(w)).toList();
        String selectedWord = availableWords.get(random.nextInt(availableWords.size()));
        usedWords.add(selectedWord);
        return selectedWord;
    }

    private void setRandomWord() {
        List<String> availableWords = new ArrayList<>(words);
        availableWords.removeAll(usedWords);
        if (availableWords.isEmpty()) {
            usedWords.clear();
            availableWords.addAll(words);
        }
        this.answer = availableWords.get(random.nextInt(availableWords.size()));
        usedWords.add(this.answer);
    }

    public String getAnswer() {
        return this.answer.toLowerCase(Locale.ENGLISH);
    }

        /**
     * Checks if the guess made is correct, should ignore upper/lower case.
     * Should give points based on made guess. Method returns a double, number
     * of the double has different meanings 0 Correct guess 1.x Letter is in the
     * word, x represents the number of times the letter is in the word 2.0
     * Guess has correct length 2.1 Guess is too long (only if it was a word)
     * 2.2 Guess is too short (only if it was a word) 3.0 Guess is partially
     * included in the word (only if it was a word), given instead of 2.2 if it
     * is a partial word 4.0 This guess was already used 4.1 Guess includes
     * symbols, numbers (not just letters or one letter) 5. After 10 guesses the
     * game ends and is set to game over 5.1 If the player keeps guessing even
     * though the status is not InProgress
     *
     * The returned answer and the guess needs to be added to the respective
     * lists for tracking.
     *
     * If letter: Return 1.NumOfOccurrence, 1.0 for the letter not being in the
     * word, 1.1 for being in there once etc. Add points according to
     * NumOfOccurance
     *
     * If word (go by this order of checks): If word is correct return 0.0 and
     * add points based on the length of the word (e.g. dog - 3 points, horse -
     * 5 points), set game status to won If word is incorrect but has correct
     * length return 2.0 and add 1 point If word is incorrect and is partially
     * included in the word return 3.0 and add 2 points If word is too long or
     * too short return 2.1, 2.2 accordingly and reduce points based on how off
     * the word is (e.g. how many letters off)
     *
     * For either: Guess was already used, reduce points by 2 and return 4.0
     * (checked before 4.1 error). Guess still counts toward made guesses. Guess
     * includes numbers/symbols etc. (so more than just letters) reduce points
     * by 3 and return 4.1, the guess is still added to the list of guesses
     *
     *
     * Score can also be negative, that is no problem. When the player guessed
     * 10 times and did not guess the word set the game to game over (status)
     * and return 5.0 (no matter if there was another error). If the player
     * guesses again, even though game status is won or game over return 5.1.
     *
     * @param guess
     * @return double returns the appropriate number
     */

    public double makeGuess(String guess) {
        guess = guess.toLowerCase(Locale.ENGLISH);
        
        if (gameStatus == 2) {
            return 5.1;
        }
        if (guesses.size() >= 10) {
            this.gameStatus = 2;
            return 5.0;
        }
        if (guess.equals(this.answer)) {
            this.points += this.answer.length();
            this.gameStatus = 1;
            return 0.0;
        }
        
        if (!guess.matches("[a-zA-Z]+")) {
            this.points -= 3;
            guesses.add(guess);
            return 4.1;
        }
        if (guesses.contains(guess)) {
            this.points -= 2;
            return 4.0;
        }
        guesses.add(guess);
        if (guess.length() == 1) {
            int occurrences = countLetters(guess.charAt(0));
            if (occurrences > 0) {
                this.points += occurrences;
                return 1.0 + occurrences / 10.0;
            } else {
                return 1.0;
            }
        }
        return 2.0;
    }

    private int countLetters(char letter) {
        int count = 0;
        for (char c : answer.toCharArray()) {
            if (c == letter) count++;
        }
        return count;
    }

    public void saveScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE, true))) {
            writer.write(name + ": " + points + "\n");
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }

    public int getPoints() {
        return this.points; 
    }

    public boolean isGameOver() {
        if (gameStatus == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void displayLeaderboard() {
        System.out.println("Leaderboard:");
        try (BufferedReader reader = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard: " + e.getMessage());
        }
    }
}