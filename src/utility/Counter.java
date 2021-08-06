package utility;

public class Counter {
    private int count;

    public Counter(int startCount)
    {
        this.count = startCount;
    }

    public Counter()
    {
        this(0);
    }

    public void increment()
    {
        this.count++;
    }

    public void decrement()
    {
        this.count--;
    }

    public void add(int value)
    {
        this.count += value;
    }

    public int getCount()
    {
        return this.count;
    }

    public void reset()
    {
        this.count = 0;
    }
}
