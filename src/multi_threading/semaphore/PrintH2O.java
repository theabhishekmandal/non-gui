package multi_threading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PrintH2O {
    public static void main(String[] args) {
        Runnable hydrogen = () -> System.out.print("h");
        Runnable oxygen = () -> System.out.print("o");

        try (ExecutorService executorService = Executors.newFixedThreadPool(100)) {
            String randomHydrogenAndOxygen = "HHHHHHHHOOOO";
            var h2o = new H2O();
            for (char c : randomHydrogenAndOxygen.toCharArray()) {
                if (c == 'H') {
                    executorService.execute(() -> {
                        try {
                            h2o.hydrogen(hydrogen);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

                if (c == 'O') {
                    executorService.execute(() -> {
                        try {
                            h2o.oxygen(oxygen);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
            executorService.shutdown();
        }
    }


    static class H2O {
        private final Semaphore hydrogen;
        private final Semaphore oxygen;
        H2O() {
            hydrogen = new Semaphore(2);
            oxygen = new Semaphore(0);
        }

        public void hydrogen(Runnable hydrogen) throws InterruptedException {
            this.hydrogen.acquire();
            hydrogen.run();
            this.oxygen.release();
        }
        public void oxygen(Runnable oxygen) throws InterruptedException {
            this.oxygen.acquire(2);
            oxygen.run();
            this.hydrogen.release(2);
        }
    }
}
