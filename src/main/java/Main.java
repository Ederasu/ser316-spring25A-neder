public class Main {
    public static void main(String[] args) {
        System.out.println("Getting started");
        Game game = new Game("Student");
        System.out.println("Current word: " + game.getAnswer());
        System.out.println(game.makeGuess("a"));
        System.out.println("Automatic guess a");
        
        game.saveScore();
        game.displayLeaderboard();
    }
}