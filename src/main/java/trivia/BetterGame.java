package trivia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import trivia.category.Category;

// REFACTOR ME
public class BetterGame implements IGame {

    //Used to pass the tests
    private static final boolean USE_STREAKS = false;
    private static final int ADD_NR_COINS_WITH_NO_STREAK = 1;
    private static final int ADD_NR_COINS_WITH_STREAK = 2;
    private static final int NR_MIN_WINS_FOR_STREAK = USE_STREAKS ? 3 : 100;
    private static final int POINTS_TO_WIN = USE_STREAKS ? 12 : 6;
    private final List<Player> players;
    private final Map<Integer, Category> categories;
    private int currentPlayer = 0;

    private BetterGame(final Set<Player> players, final List<Category> categories) {
        this.categories = categories.stream().collect(Collectors.toMap(value -> value.getCategoryType().ordinal(), value -> value));
        this.players = Collections.unmodifiableList(new ArrayList<>(players));

        System.out.println("\n\n--Starting game--");
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public void takeTurn(int roll) {
        final Player currentPlayer = getCurrentPlayer();
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (!isOdd(roll)) {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                return;
            }

            takePlayerOutOfPenaltyBox();
        }

        movePlayerBy(roll);
        askQuestion();
    }

    private void movePlayerBy(int roll) {
        final Player currentPlayer = getCurrentPlayer();

        currentPlayer.addToCurrentLocation(roll);
        System.out.println(currentPlayer + "'s new location is " + currentPlayer.getLocation());
    }

    private void takePlayerOutOfPenaltyBox() {
        getCurrentPlayer().setInPenaltyBox(false);
        System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
    }

    private static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private void askQuestion() {
        System.out.println("The category is " + currentCategory().getName());
        System.out.println(currentCategory().getNextQuestion());
    }

    private Category currentCategory() {
        final int locationIdx = getCurrentPlayer().getLocation();
        return categories.get(locationIdx % categories.size());
    }

    /**
     * @return Boolean - true if player is not a winner, otherwise false
     */
    public boolean handleCorrectAnswer() {
        final Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.isInPenaltyBox()) {
            moveToNextPlayer();
            return true;
        }

        System.out.println("Answer was correct!!!!");
        currentPlayer.increaseStreak();
        awardPlayerCoins();
        boolean winner = didPlayerWin();
        moveToNextPlayer();

        return !winner;
    }

    /**
     * @return True - Player is not a winner
     */
    public boolean handleWrongAnswer() {
        System.out.println("Question was incorrectly answered");

        final Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.getStreak() < NR_MIN_WINS_FOR_STREAK) {
            currentPlayer.setInPenaltyBox(true);
        }
        currentPlayer.resetStreak();

        System.out.println(currentPlayer + " was sent to the penalty box");

        moveToNextPlayer();
        return true;
    }

    private void moveToNextPlayer() {
        currentPlayer = (currentPlayer + 1) % numberOfPlayers();
    }

    private void awardPlayerCoins() {
        final Player currentPlayer = getCurrentPlayer();

        int coinsToAdd = ADD_NR_COINS_WITH_NO_STREAK;
        if (currentPlayer.getStreak() >= NR_MIN_WINS_FOR_STREAK) {
            coinsToAdd = ADD_NR_COINS_WITH_STREAK;
        }
        currentPlayer.addCoins(coinsToAdd);

        System.out.println(currentPlayer + " now has " + currentPlayer.getBalance() + " Gold Coins.");
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    private boolean didPlayerWin() {
        return getCurrentPlayer().getBalance() == POINTS_TO_WIN;
    }

    public static Initializer initializer() {
        return new Initializer();
    }

    public static class Initializer {

        private static final int MIN_PLAYERS = 2;
        private static final int MAX_PLAYERS = 6;
        private final Set<Player> players = new HashSet<>();
        private final List<Category> categories = new ArrayList<>();

        private Initializer() {
        }

        public Initializer addCategory(Category category) {
            categories.add(category);
            return this;
        }

        public Initializer addPlayer(final String playerName) {
            final Player player = new Player(playerName);
            boolean newPlayer = players.add(player);
            if (!newPlayer) {
                System.out.printf("Player with name %s already exists.%n", player);
                return this;
            }

            System.out.println(player + " was added");
            System.out.println("They are player number " + players.size());
            return this;
        }

        public IGame createGame() {
            final int nrPlayers = players.size();
            if (nrPlayers < MIN_PLAYERS || nrPlayers > MAX_PLAYERS) {
                throw new IllegalArgumentException("Number of players must be between: 2-6");
            }

            return new BetterGame(players, categories);
        }
    }
}
