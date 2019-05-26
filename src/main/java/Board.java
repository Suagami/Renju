class Board
{
    static Cell[][] board;
    static String turn = "W";
    private static final int SIZE = Game.SIZE;
    private static final int WIN = 5;

    Board()
    {
        board = new Cell[SIZE][SIZE];
        for(int x = 0; x < SIZE; x++)
        {
            for(int y = 0; y < SIZE; y++)
            {
                board[x][y] = new Cell();
                board[x][y].setCoord(new Coord(x, y));
            }
        }
        board[7][7].setStatus("B");
    }

    static void switchTurn()
    {
        if(turn.equals("W"))
            turn = "B";
        else
            turn = "W";
    }


    static boolean winCondition(Coord coord)
    {
        int t = 0;
        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.x + i < 0) || (coord.x + i >= SIZE))
                continue;
            if(board[coord.x + i][coord.y].getStatus().equals(turn))
            {
                t++;
            }
            else
            {
                t = 0;
            }
            if(t == WIN)
            {
                return true;
            }
        }

        t = 0;

        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.y + i < 0) || (coord.y + i >= SIZE))
                continue;
            if(board[coord.x][coord.y + i].getStatus().equals(turn))
            {
                t++;
            }
            else
            {
                t = 0;
            }
            if(t == WIN)
            {
                return true;
            }
        }

        t = 0;

        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.x + i < 0) || (coord.x + i >= SIZE) || (coord.y + i < 0) || (coord.y + i >= SIZE))
                continue;
            if(board[coord.x + i][coord.y + i].getStatus().equals(turn))
            {
                t++;
            }
            else
            {
                t = 0;
            }
            if(t == WIN)
            {
                return true;
            }
        }

        t = 0;

        for(int i = -1 * (WIN - 1); i <= WIN - 1; i++)
        {
            if((coord.x - i < 0) || (coord.x - i >= SIZE) || (coord.y + i < 0) || (coord.y+i >= SIZE))
                continue;
            if(board[coord.x-i][coord.y+i].getStatus().equals(turn))
            {
                t++;
            }
            else
            {
                t=0;
            }
            if(t == WIN)
            {
                return true;
            }
        }
        return false;
    }

}


