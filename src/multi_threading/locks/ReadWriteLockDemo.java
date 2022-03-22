package multi_threading.locks;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is an example on how better is to use ReadWriteLock over ReentrantLock where there are
 * many reads and few writes.
 * Time consumed in execution with ReentrantLock is 6000 ms whereas with ReadWriteLock it is
 * 530 ms.
 * But note that it is not necessary that ReadWriteLock will outperform Reentrant lock. Since, it
 * is complexer than Reentrant lock, it may have performance issues.
 */
public class ReadWriteLockDemo {
    public static final Random random = new Random();
    private static final int HIGHEST_PRICE = 1000;

    public static void main(String[] args) throws InterruptedException {
        LockFacade lockFacade = new LockFacade();
        InventoryDatabase inventoryDatabase = new InventoryDatabase(lockFacade);

        lockFacade.setEnableReadWriteLock(false);
        execute(inventoryDatabase);

        lockFacade.setEnableReadWriteLock(true);
        execute(inventoryDatabase);
    }

    private static void execute(InventoryDatabase inventoryDatabase) throws InterruptedException {
        random.ints(10000, 0, HIGHEST_PRICE)
                .forEach(inventoryDatabase::addItem);

        Thread writer = new Thread(() -> {
            while (true) {
                inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
                inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));
                sleep(10);
            }
        });

        writer.setDaemon(true);
        writer.start();


        int numberOfReadThreads = 7;
        List<Thread> readers = new ArrayList<>();
        for (int readerIndex = 0; readerIndex < numberOfReadThreads; readerIndex++) {
            Thread reader = new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
                    inventoryDatabase.getNumberOfItemsInPriceRange(lowerBoundPrice, upperBoundPrice);
                }
            });
            reader.setDaemon(true);
            readers.add(reader);
        }

        long startReadingTime = System.currentTimeMillis();
        for (Thread reader : readers) {
            reader.start();
        }

        for (Thread reader : readers) {
            reader.join();
        }
        long endReadTime = System.currentTimeMillis();
        System.out.printf("Reading took %d ms%n", endReadTime - startReadingTime);
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    static class LockFacade {
        private final ReentrantLock reentrantLock = new ReentrantLock();
        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();
        private boolean enableReadWriteLock;

        public Lock getReadLock() {
            if (enableReadWriteLock) {
                return this.readLock;
            }
            return this.reentrantLock;
        }

        public Lock getWriteLock() {
            if (enableReadWriteLock) {
                return this.writeLock;
            }
            return this.reentrantLock;
        }

        public void setEnableReadWriteLock(boolean enableReadWriteLock) {
            this.enableReadWriteLock = enableReadWriteLock;
        }
    }

    static class InventoryDatabase {
        private final TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();
        private final LockFacade lockFacade;

        public InventoryDatabase(LockFacade lockFacade) {
            this.lockFacade = lockFacade;
        }

        public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
            Lock lock = lockFacade.getReadLock();
            lock.lock();
            try {
                Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
                Integer toKey = priceToCountMap.floorKey(upperBound);

                if (fromKey == null || toKey == null) {
                    return 0;
                }

                NavigableMap<Integer, Integer> rangeOfPrices = priceToCountMap
                        .subMap(fromKey, true, toKey, true);

                return rangeOfPrices.values().stream().mapToInt(x -> x).sum();
            } finally {
                lock.unlock();
            }
        }


        public void addItem(int price) {
            Lock lock = lockFacade.getWriteLock();
            lock.lock();
            try {
                priceToCountMap.merge(price, 1, Integer::sum);
            } finally {
                lock.unlock();
            }
        }

        public void removeItem(int price) {
            Lock lock = lockFacade.getWriteLock();
            lock.lock();
            try {
                priceToCountMap.merge(price, -1, Integer::sum);
                if (priceToCountMap.get(price) < 0) {
                    priceToCountMap.remove(price);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
