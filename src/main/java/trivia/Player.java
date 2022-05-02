package trivia;

public class Player {

    String name;
    int balance;
    int location;
    boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getLocation() {
        return this.location;
    }

    public void setLocation(int newLocation){
        this.location = newLocation;
    }

    public void addACoin() {
        this.balance ++;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void addToCurrentLocation(int roll){
        this.location += roll;
        resetPlayerLocationIfCompletedCircle();
    }

    private void resetPlayerLocationIfCompletedCircle(){
        if(this.location > 11) {
            this.location -= 12;
        }
    }
    @Override
    public String toString() {
        return this.name;
    }
}
