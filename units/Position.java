package units;

public class Position {
    public static final byte MAXPOSITION = 10;
    public static final byte MINPOSITION = 1;
    public static final float MAXDISTANCE = (float) Math.sqrt(2 * (MAXPOSITION - MINPOSITION) * (MAXPOSITION - MINPOSITION));
    public byte x, y;

    public Position(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEqual(Position position) {
        return (position.x == this.x && position.y == this.y);
    }

    public float getDistance(Position position) {
        int dx = position.x - this.x;
        int dy = position.y - this.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}
