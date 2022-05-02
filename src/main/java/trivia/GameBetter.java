package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {  //bad name-> GameBetter + no need fo IGame we already know is an interface

    ArrayList<Player> players = new ArrayList<>();
    Map<Integer, Category> categories;
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    GameBetter(LinkedList<Player> players, List<Category> categories) {
        this.categories = categories.stream()
                .collect(Collectors.toMap(value -> value.getCategoryType().ordinal(), value -> value));
        System.out.println("\n\n--Starting game--");

    }

    public void addCategory(Category category) {
        categories.put(category.getCategoryType().ordinal(), category);
    }

    public void addPlayer(final String playerName) {
        Player player = new Player(playerName);
        players.add(player);

        System.out.println(player + " was added");
        System.out.println("They are player number " + numberOfPlayers());
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public void takeTurn(int roll) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (getCurrentPlayer().isInPenaltyBox()) {
            if (isOdd(roll)) {
                getPlayerOutOfPenaltyBox();
                movePlayerBy(roll);
                askQuestion();
            } else {
                System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            movePlayerBy(roll);

            askQuestion();
        }

    }

    private void movePlayerBy(int roll) {
        getCurrentPlayer().addToCurrentLocation(roll);
        System.out.println(getCurrentPlayer() + "'s new location is " + getCurrentPlayer().getLocation());

    }

    private void getPlayerOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
    }

    private static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private void askQuestion() {
        System.out.println("The category is " + currentCategory().getName());
        System.out.println(currentCategory().getQuestions().removeFirst());
    }

    private Category currentCategory() {
        final int locationIdx = getCurrentPlayer().getLocation();
        final int nrCategories = Category.Type.values().length;
        return categories.get(locationIdx % nrCategories);
    }

    public boolean handleCorrectAnswer() {
        if (getCurrentPlayer().isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            moveToNextPlayer();
            return true;
        }

        awardPlayerWithCoin();
        boolean winner = didPlayerWin();
        moveToNextPlayer();

        return winner;

    }

    public boolean handleWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer() + " was sent to the penalty box");
        getCurrentPlayer().setInPenaltyBox(true);

        moveToNextPlayer();
        return true;
    }

    private void moveToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == numberOfPlayers()) {
            currentPlayer = 0;
        }

    }

    private void awardPlayerWithCoin() {
        System.out.println("Answer was correct!!!!");
        getCurrentPlayer().addACoin();
        System.out.println(getCurrentPlayer() + " now has " + getCurrentPlayer().getBalance() + " Gold Coins.");
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    private boolean didPlayerWin() {
        return !(getCurrentPlayer().getBalance() == 6);
    }
}
