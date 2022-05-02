package trivia.category;

import java.util.LinkedList;

public abstract class Category {

    private static final int NR_QUESTIONS = 50;

    public enum Type {
        POP,
        SCIENCE,
        SPORTS,
        ROCK,
        GEOGRAPHY
    }

    private final String name;
    private final LinkedList<String> questions;

    public Category(String name) {
        this.name = name;
        this.questions = new LinkedList<>();

        initQuestion();
    }

    public abstract Type getCategoryType();

    public abstract String createQuestion(int index);

    public void initQuestion() {
        for (int i = 0; i < NR_QUESTIONS; i++) {
            this.getQuestions().addLast(createQuestion(i));
        }
    }

    public String getName() {
        return name;
    }

    public LinkedList<String> getQuestions() {
        return questions;
    }

}
