package Hangman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    /**
     * initialize the text box for the player's guesses
     */
    public static List<String> dashes = new ArrayList<>();

    /**
     * basic ascii graphic of the hangman game
     * @param num number of guesses
     * @return
     */
    public static String graphic(int num) {

        String last = """
                    +----+
                    0    |
                   \\ /   |
                    |    |
                   / \\   |
                ===========""";

        String fourth = """
                    +----+
                    0    |
                   \\ /   |
                    |    |
                         |
                ===========""";

        String third = """
                    +----+
                    0    |
                   \\ /   |
                         |
                         |
                ===========""";

        String second = """
                    +----+
                    0    |
                         |
                         |
                         |
                ===========""";

        String first = """
                    +----+
                         |
                         |
                         |
                         |
                ===========""";

        String[] hangMans = {first, second, third, fourth, last};

        return hangMans[num];
    }

    /**
     * uses a HTTP GET request to a Heroku app that generates a random word - thus not requiring memory to store a large array of strings
     * @return the word the player must find
     * @throws Exception connection timeout
     */
    public static String wordBank() throws Exception {
        StringBuilder word = new StringBuilder();
        URL url = new URL("https://random-word-api.herokuapp.com/word?number=1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                word.append(line);
            }
        }
        return word.toString();
    }

    /**
     * logic to allow the program to iterate through the player's guesses and fill the text box accordingly
     * @param word the word of interest
     * @param guess the player's current guess
     * @return hacky and non-ideal way to return the text box each time
     */
    public static String wordBuilder(String word, String guess) {

        ArrayList<String> wordList = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            dashes.add("_ ");
            wordList.add(String.valueOf(word.charAt(i)));
        }

        dashes = dashes.subList(0, word.length());

        int i = 0;

        while (wordList.contains(guess) && i < dashes.size()) {
            if (wordList.get(i).equals(guess)) {
                dashes.set(i, guess);
            }
            i++;
        }

        return dashes.toString().replaceAll(",", "");
    }

    /**
     * takes user's input for gameplay and calls wordBuilder method
     * @param getWord accepts auto-generated word
     * @param wrongs number of user's wrong attempts
     */
    public static void game(String getWord, int wrongs) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nGuess a letter: ");
            String guess = sc.next();
            String block = wordBuilder(getWord, guess);

            if (!getWord.contains(String.valueOf(guess))) {
                wrongs++;
            }
            System.out.print(graphic(wrongs) + "     " + block);

            if (!block.contains("_")) {
                System.out.println("\nYou win!");
                dashes.clear();
                break;
            } else if (wrongs == 4) {
                System.out.println("\nYou lose!");
                dashes.clear();
                break;
            }
        }
    }

    /**
     * gets word from the HTTP GET request method and adds another layer of exception handling
     * @return removes array formatting "["...."]" and returns only the word of interest
     */
    public static String getWord() {
        String getWord = null;

        try {
            getWord = wordBank();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert getWord != null;
        return getWord.replaceAll("[^a-z]", "");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String word = getWord();

        int wrongs = 0;

        game(word, wrongs);

        System.out.println("Play again? y / n");
        char playAgain = sc.next().charAt(0);

        if (playAgain == 'y') game(getWord(), wrongs);
    }
}