package design_patterns.singleton;

class Elvis {
    private static final Elvis elvis = new Elvis();
    private Elvis() {}
    public static Elvis getInstance() {
        return elvis;
    }
}
public class Singleton {
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        Elvis elvis2 = Elvis.getInstance();
        System.out.println(elvis == elvis2);
    }
}
