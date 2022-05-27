package reflection.field_object.configuration_parser.dto;

public class GameConfig {
    private int releaseYear;
    private String gameName;
    private double price;

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGameName() {
        return gameName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                '}';
    }
}
