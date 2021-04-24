package client.Entities;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector multiply(int d) {
        return new Vector(x * d, y * d);
    }

    public Vector plus(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    public Vector minus(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }

    public Vector normalized() {
        var l = getLength();
        return new Vector((int) (x / l), (int) (y / l));
    }

    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}
