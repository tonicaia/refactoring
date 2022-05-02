package trivia.category;

public class PopCategory extends Category {

    public PopCategory() {
        super("Pop");
    }

    @Override
    public String createQuestion(int index) {
        return "Pop Question " + index;
    }

    @Override
    public Type getCategoryType() {
        return Type.POP;
    }
}
