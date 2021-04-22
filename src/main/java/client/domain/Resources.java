package client.domain;

public class Resources {
    private int count = 0;

    public Resources(int count) {
        this.count = count;
    }

    public Resources() {
        this(0);
    }

    public int getCount() {
        return count;
    }

    public void add(int count) {
        this.count += count;
    }

    public void apply(int count) {
        this.count -= count;
    }

}
