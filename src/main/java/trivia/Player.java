package trivia;

import java.util.Objects;

public class Player {

    private final String name;
    private int balance;
    private int location;
    private boolean inPenaltyBox;
    private int streak;

    public Player(String name) {
        this.name = name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public int getBalance() {
        return balance;
    }

    public int getLocation() {
        return location;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void addCoins(int nrCoins) {
        balance += nrCoins;
    }

    public void addToCurrentLocation(int roll) {
        location += roll;
        resetPlayerLocationIfCompletedCircle();
    }

    public int getStreak() {
        return streak;
    }

    public void increaseStreak() {
        streak++;
    }

    public void resetStreak() {
        streak = 0;
    }

    private void resetPlayerLocationIfCompletedCircle() {
        if (this.location > 11) {
            this.location -= 12;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
