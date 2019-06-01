public enum Tile {
    B("B"),
    W("W"),
    UL("UL"),
    DL("DL"),
    UR("UR"),
    DR("DR"),
    U("U"),
    D("D"),
    L("L"),
    R("R"),
    S("S"),
    C("C"),
    ICON("ICON");

    private String title;

    Tile(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
