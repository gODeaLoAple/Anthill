package client.domain;

public class Resources {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void add(int count) {
        assert count >= 0;
        this.count += count;
    }

    public void remove(int count) {
        assert count <= 0;
        this.count -= count;
    }

}
