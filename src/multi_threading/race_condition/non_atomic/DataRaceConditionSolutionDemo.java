package multi_threading.race_condition.non_atomic;

/**
 * This example is a solution for the previous example in which we were facing data race condition.
 * Instead of using synchronized keyword on method, we are using volatile keyword on variables.
 * This is done so that compiler executes them in order and not in Out of Order format. This guarantees
 * that x >= y.
 */
public class DataRaceConditionSolutionDemo {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread one = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });


        Thread two = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRaceConditions();
            }
        });

        one.start();
        two.start();
    }

    public static class SharedClass {
        private volatile int x;
        private volatile int y;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRaceConditions() {
            if (y > x) {
                System.out.println("y > x Found data race condition");
            }
        }
    }
}
