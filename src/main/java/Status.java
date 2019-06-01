public enum Status {


    EMPTY ("Empty"),
    BLACK ("Black"),
    WHITE ("White");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Tile getTile() {
        if (title.equals("Black")) return Tile.B;
        if (title.equals("White")) return Tile.W;
        throw new IllegalArgumentException("Tile  doesn't exists");
    }


}
