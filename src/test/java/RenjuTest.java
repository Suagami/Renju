import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class RenjuTest {
    @Test
    void cellTest() {
        Cell firstTestCell = new Cell();
        Cell secondTestCell = new Cell();
        Cell thirdTestCell = new Cell();
        Cell newCell = new Cell();

        firstTestCell.setStatus(Status.EMPTY);
        secondTestCell.setStatus(Status.BLACK);
        thirdTestCell.setStatus(Status.WHITE);

        Coord coord = new Coord(0,0);
        Coord coord1 = new Coord(0,1);
        Coord coord2 = new Coord(0,2);

        firstTestCell.setCoord(coord);
        secondTestCell.setCoord(coord1);
        thirdTestCell.setCoord(coord2);


        assertEquals(coord, firstTestCell.getCoord());
        assertEquals(coord1, secondTestCell.getCoord());
        assertEquals(coord2, thirdTestCell.getCoord());

        assertEquals(Status.EMPTY, firstTestCell.getStatus());
        assertEquals(Status.BLACK, secondTestCell.getStatus());
        assertEquals(Status.WHITE, thirdTestCell.getStatus());

        assertTrue(newCell.equals(firstTestCell));
    }

    @Test
    void boardTest() {
        Board firstTestBoard = new Board();

        for(int i=0;i<Board.WIN;i++)
        {
            firstTestBoard.board[Game.SIZE/2][Game.SIZE/2+i].setStatus(Status.BLACK);
        }

        assertEquals(Status.WHITE, firstTestBoard.turn);

        firstTestBoard.switchTurn();

        assertEquals(Status.BLACK, firstTestBoard.turn);


        assertTrue(firstTestBoard.winCondition(new Coord(Game.SIZE/2, Game.SIZE/2)));
        assertTrue(firstTestBoard.winCondition(new Coord(Game.SIZE/2, Game.SIZE/2 + Board.WIN - 1)));

        Board secondTestBoard = new Board();

        for(int i=0;i<Game.SIZE;i++)
        {
            for(int j=0;j<Game.SIZE;j++)
            {
                if((j + (i % 2 * 3)) / 3 % 2 == 1)
                {
                    secondTestBoard.board[i][j].setStatus(Status.BLACK);
                }
                else secondTestBoard.board[i][j].setStatus(Status.WHITE);

            }
        }

        assertTrue(secondTestBoard.tieCondition());
    }

}
