package client.domain.entities.anthill;

import java.io.Serializable;

public class Resources implements Serializable {
    private int count;
    public Resources(int count) {
        this.count = count;
    }

    public Resources() {
        this(0);
    }

    public int getCount() {
        return count;
    }

    public void change(int difference) {
        this.count += difference;
    }

}
