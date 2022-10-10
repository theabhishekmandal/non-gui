package reflection.annotations.annotation_init_example.graph_example;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Database {

    private final Random random = new Random();
    public Set<String> readAllGames() {
        return Set.of("mario", "gta", "cod");
    }

    public Map<String, Float> readGameToPrice(Set<String> allGames) {
        return allGames.stream().collect(Collectors.toMap(x -> x, y -> random.nextInt(10) + 1.0f));
    }

    public Map<String, Float> readGameToRating(Set<String> allGames) {
        return allGames.stream().collect(Collectors.toMap(x -> x, y -> random.nextInt(5) + 1.0f));
    }
}
