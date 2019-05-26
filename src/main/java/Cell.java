class Cell
{
    private String status;
    private Coord coord;

    Cell()
    {
        this.status = "E";
        this.coord = new Coord(0, 0);
    }

    void setCoord(Coord coord)
    {
        this.coord = coord;
    }

    void setStatus(String status)
    {
        this.status = status;
    }

    String getStatus()
    {
        return this.status;
    }
}

