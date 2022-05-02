package trivia.category;

public class GeographyCategory extends Category {

    public GeographyCategory() {
        super("Geography");
    }

    @Override
    public String createQuestion(int index) {
        return "Geography Question " + index;
    }

    @Override
    public Type getCategoryType() {
        return Type.GEOGRAPHY;
    }
}
