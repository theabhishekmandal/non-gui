package reflection.field_object.json_serlializer.data;

public class Movie {
    private final String name;
    private final float rating;
    private final String[] categories;

    private final Actor[] actors;

    public Movie(String name, float rating, String[] categories, Actor[] actors) {
        this.name = name;
        this.rating = rating;
        this.categories = categories;
        this.actors = actors;
    }

    public Movie(String name, float rating, String[] categories) {
        this(name, rating, categories, null);
    }
}
