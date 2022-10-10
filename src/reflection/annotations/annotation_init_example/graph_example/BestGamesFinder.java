package reflection.annotations.annotation_init_example.graph_example;

import reflection.annotations.annotation_init_example.annotations.Annotations;

import java.util.*;

public class BestGamesFinder {
    private final Database database = new Database();

    @Annotations.Operation("all-games")
    public Set<String> getAllGames() {
        return database.readAllGames();
    }

    @Annotations.Operation("games-price")
    public Map<String, Float> getGameToPrice(@Annotations.DependsOn("all-games") Set<String> allGames) {
        return database.readGameToPrice(allGames);
    }

    @Annotations.Operation("games-rating")
    public Map<String, Float> getGameToRating(@Annotations.DependsOn("all-games") Set<String> allGames) {
        return database.readGameToRating(allGames);
    }

    @Annotations.Operation("score-Games")
    public SortedMap<Double, String> scoreGames(@Annotations.DependsOn("games-price") Map<String, Float> gameToPrice,
                                                @Annotations.DependsOn("games-rating") Map<String, Float> gameToRating) {
        SortedMap<Double, String> gameToScore = new TreeMap<>(Collections.reverseOrder());
        for (String gameName : gameToPrice.keySet()) {
            double score = (double) gameToRating.get(gameName) / (double) gameToPrice.get(gameName);
            gameToScore.put(score, gameName);
        }
        return gameToScore;
    }

    @Annotations.FinalResult
    public List<String> getTopGames(@Annotations.DependsOn("score-Games") SortedMap<Double, String> scoreToGame) {
        return new ArrayList<>(scoreToGame.values());
    }
}
