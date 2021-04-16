package design_patterns.adapter;

/**
 * This is an example of adapter pattern in which non compatible classes are wrapped inside an adapter to make it compatible.
 *
 * Here FishingBoat is not a type of RowingBoat, so we make an adapter below, which wraps FishingBoat.
 */
interface RowingBoat {
    void row();
}

class FishingBoat {
    public void sail() {
        System.out.println("The fishing boat is sailing");
    }
}

class Captain {
    private final RowingBoat rowingBoat;
    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }
    public void row() {
        this.rowingBoat.row();
    }
}

// adapter class
class FishingBoatAdapter implements RowingBoat {
    private final FishingBoat fishingBoat;

    public FishingBoatAdapter() {
        this.fishingBoat = new FishingBoat();
    }

    @Override
    public void row() {
        fishingBoat.sail();
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        var captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
