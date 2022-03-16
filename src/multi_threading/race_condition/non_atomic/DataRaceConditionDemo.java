package multi_threading.race_condition.non_atomic;

/**
 * This is an example of data race condition.
 * -    Here one thread is updating value x before y. So x >= y should always be true.
 * -    But, when another thread checks for the value, it is found that y > x is happening which
 * leads to data race condition.
 * -    Also note that adding synchronized keyword in method is not helping in resolving this issue.
 */
public class DataRaceConditionDemo {
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
        private int x;
        private int y;

        public synchronized void increment() {
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
