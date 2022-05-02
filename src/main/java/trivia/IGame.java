package trivia;

public interface IGame {

	void addPlayer(String playerName);

	void takeTurn(int roll);

	boolean handleCorrectAnswer();

	boolean handleWrongAnswer();

}