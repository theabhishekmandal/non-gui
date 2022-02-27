package multi_threading.interrupting_threads;

/**
 * This is an example showing how to interrupt a thread using the interrupt method
 */
public class InterruptDemo {
    public static void main(String[] args) {
        var thread = new Thread(new BlockingTask());
        thread.start();

        thread.interrupt();
    }


    static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                System.out.println("Exiting Blocking Task");
            }
        }
    }
}
