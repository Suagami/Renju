class Cell
{
    private Status status;
    private Coord coord;

    Cell()
    {
        this.status = Status.EMPTY;
        this.coord = new Coord(0, 0);
    }

    void setCoord(Coord coord)
    {
        this.coord = coord;
    }

    void setStatus(Status status)
    {
        this.status = status;
    }

    Status getStatus()
    {
        return this.status;
    }

    Coord getCoord()
    {
        return this.coord;
    }

    boolean equals(Cell obj)
    {
        return (this.coord.equals(obj.coord)) && (this.status.equals(obj.status));
    }
}

