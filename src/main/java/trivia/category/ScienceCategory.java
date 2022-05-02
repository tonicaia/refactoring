package trivia.category;

public class ScienceCategory extends Category {

    public ScienceCategory() {
        super("Science");
    }

    @Override
    public String createQuestion(int index) {
        return "Science Question " + index;
    }

    @Override
    public Type getCategoryType() {
        return Type.SCIENCE;
    }
}
