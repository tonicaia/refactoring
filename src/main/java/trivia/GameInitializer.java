package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import trivia.category.Category;
import trivia.category.GeographyCategory;
import trivia.category.PopCategory;
import trivia.category.RockCategory;
import trivia.category.ScienceCategory;
import trivia.category.SportsCategory;

public class GameInitializer {

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private static final Scanner scanner = new Scanner(System.in);

    public static GameBetter initBetterGame() {
        System.out.println("*** Welcome to Trivia Game ***\n");

        return new GameBetter(initPlayers(), initCategories());

    }

    private static LinkedList<Player> initPlayers() {
        return readPlayers(getPlayerCount());
    }

    private static List<Category> initCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new PopCategory());
        categories.add(new ScienceCategory());
        categories.add(new SportsCategory());
        categories.add(new RockCategory());
        categories.add(new GeographyCategory());
        return categories;
    }

    private static int getPlayerCount() {
        System.out.println("Enter number of players: 2-6");

        int playerCount;
        do {
            playerCount = Integer.parseInt(scanner.nextLine());
        } while (!isValidPlayerNumber(playerCount));
        return playerCount;
    }

    private static boolean isValidPlayerNumber(int playerCount) {
        if (playerCount < MIN_PLAYERS || playerCount > MAX_PLAYERS) {
            System.out.println("Number of players must be be: 2-6");
            return false;
        }
        return true;
    }

    private static LinkedList<Player> readPlayers(int playerCount) {
        System.out.println("Reading names for " + playerCount + " players:");

        LinkedList<Player> players = new LinkedList<>();

        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Player " + i + " name: ");
            String playerName;
            do {
                playerName = scanner.nextLine();
            } while (!isValidPlayerName(playerName, players));
            players.add(new Player(playerName));
        }
        return players;
    }

    private static boolean isValidPlayerName(String playerName, List<Player> players) {
        //TODO: verify if list contains already a player with that name

        return true;
    }
}
