public class Coord {
    int x;
    int y;

    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coord obj) {
        return (this.x == obj.x) && (this.y == obj.y);
    }

}
