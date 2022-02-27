package multi_threading.problems;

import java.util.List;
import java.util.Random;

public class HackerThreadCaseStudy {
    public static void main(String[] args) {
        Vault vault = new Vault(new Random().nextInt(1000));
        List<Thread> threadList = List.of(
                new AscendingThread(vault),
                new DescendingThread(vault),
                new PoliceThread()
        );

        threadList.forEach(Thread::start);
    }

    static class Vault {
        private final int passWord;

        public Vault(int passWord) {
            this.passWord = passWord;
        }

        public boolean isCorrectPassword(int guessedPassword) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return passWord == guessedPassword;
        }
    }

    abstract static class HackerThread extends Thread {
        protected Vault vault;

        protected HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    static class AscendingThread extends HackerThread {

        protected AscendingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                if (super.vault.isCorrectPassword(i)) {
                    System.out.println("guessed password is " + i);
                    System.exit(0);
                }
            }
        }
    }

    static class DescendingThread extends HackerThread {

        protected DescendingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = Integer.MAX_VALUE; i >= 0; i--) {
                if (super.vault.isCorrectPassword(i)) {
                    System.out.println("guessed password is " + i);
                    System.exit(0);
                }
            }
        }

    }

    static class PoliceThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Game over hackers");
            System.exit(0);
        }
    }

}






