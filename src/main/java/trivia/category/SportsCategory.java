package trivia.category;

public class SportsCategory extends Category {

    public SportsCategory() {
        super("Sports");
    }

    @Override
    public String createQuestion(int index) {
        return "Sports Question " + index;
    }

    @Override
    public Type getCategoryType() {
        return Type.SPORTS;
    }
}
