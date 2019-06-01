class Board
{
    private final int SIZE = Game.SIZE;
    static final int WIN = 5;
    Cell[][] board;
    Status turn = Status.WHITE;

    Board()
    {
        this.board = new Cell[SIZE][SIZE];
        for(int x = 0; x < SIZE; x++)
        {
            for(int y = 0; y < SIZE; y++)
            {
                this.board[x][y] = new Cell();
                this.board[x][y].setCoord(new Coord(x, y));
            }
        }
        this.board[SIZE/2][SIZE/2].setStatus(Status.BLACK);
    }

    void switchTurn()
    {
        if(this.turn == Status.WHITE)
            this.turn = Status.BLACK;
        else
            this.turn = Status.WHITE;
    }


    boolean tieCondition()
    {
        for(int x = 0; x < SIZE; x++)
        {
            for (int y = 0; y < SIZE; y++)
            {
                if (this.board[x][y].getStatus() == Status.EMPTY)
                {
                    return false;
                }
            }
        }
        return true;
    }


    boolean winCondition(Coord coord)
    {
        int temp = 0;
        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.x + i < 0) || (coord.x + i >= SIZE))
                continue;
            if(this.board[coord.x + i][coord.y].getStatus() == this.turn)
            {
                temp++;
            }
            else
            {
                temp = 0;
            }
            if(temp == WIN)
            {
                return true;
            }
        }

        temp = 0;

        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.y + i < 0) || (coord.y + i >= SIZE))
                continue;
            if(this.board[coord.x][coord.y + i].getStatus() == this.turn)
            {
                temp++;
            }
            else
            {
                temp = 0;
            }
            if(temp == WIN)
            {
                return true;
            }
        }

        temp = 0;

        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.x + i < 0) || (coord.x + i >= SIZE) || (coord.y + i < 0) || (coord.y + i >= SIZE))
                continue;
            if(this.board[coord.x + i][coord.y + i].getStatus() == this.turn)
            {
                temp++;
            }
            else
            {
                temp = 0;
            }
            if(temp == WIN)
            {
                return true;
            }
        }

        temp = 0;

        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.x - i < 0) || (coord.x - i >= SIZE) || (coord.y + i < 0) || (coord.y+i >= SIZE))
                continue;
            if(this.board[coord.x-i][coord.y+i].getStatus() == this.turn)
            {
                temp++;
            }
            else
            {
                temp = 0;
            }
            if(temp == WIN)
            {
                return true;
            }
        }
        return false;
    }
}


