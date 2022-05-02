package trivia.category;

public class CategoryFactory {

    private CategoryFactory() {}

    public static Category createCategory(Category.Type type) {
        switch (type) {
            case GEOGRAPHY:
                return new GeographyCategory();
            case POP:
                return new PopCategory();
            case ROCK:
                return new RockCategory();
            case SCIENCE:
                return new ScienceCategory();
            case SPORTS:
                return new SportsCategory();
            default:
                throw new UnsupportedOperationException("Category type not found");
        }
    }
}