package lowleveldesign.foodOrderingSystem.design.dto;

public class Rating {
    private int sum;
    private int count;
    private double averageRating;

    public void addRating(int n) {
        this.sum += n;
        this.count++;
        this.averageRating = ((double) this.sum / (double) this.count);
    }

    public double getRating() {
        return this.averageRating;
    }
}
