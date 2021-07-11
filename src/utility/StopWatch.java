package utility;

public class StopWatch {
    private long startTime;
    private long stopTime;

    public void startTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void stopTime() {
        this.stopTime = System.currentTimeMillis();
    }

    public long getTimeInSeconds() {
        return getTimeInMillis() / 1000;
    }

    public long getTimeInMillis() {
        if (this.stopTime == 0) {
            this.stopTime = System.currentTimeMillis();
        }
        return this.stopTime - this.startTime;
    }

    public void reset() {
        this.startTime = 0;
        this.stopTime = 0;
    }
}
