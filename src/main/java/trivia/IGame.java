package trivia;

public interface IGame {

	default void addPlayer(String playerName) {
		throw new UnsupportedOperationException();
	}

	void takeTurn(int roll);

	boolean handleCorrectAnswer();

	boolean handleWrongAnswer();

}