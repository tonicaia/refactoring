package trivia.category;

public class RockCategory extends Category {

    public RockCategory() {
        super("Rock");
    }

    @Override
    public String createQuestion(int index) {
        return "Rock Question " + index;
    }

    @Override
    public Type getCategoryType() {
        return Type.ROCK;
    }
}
