package reflection.field_object.json_serlializer.data;

public class Actor {
    private final String name;
    private final String[] knownForMovies;

    public Actor(String name, String[] knownForMovies) {
        this.name = name;
        this.knownForMovies = knownForMovies;
    }
}
