import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame
{
    private static JPanel panel;
    private static int turnNumber = 3;
    final static int SIZE = 15;
    private final static int IMAGE_SIZE = 48;
    private Board gameBoard = new Board();


    public static void main(String[] args)
    {
        new Game();
    }

    private Game()
    {
        initPanel();
        initFrame();
    }

    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(int x = 0; x < SIZE; x++)
                {
                    for(int y = 0; y < SIZE; y++)
                    {
                        Tile tile = getTile(x, y);
                        g.drawImage(getImage(tile), x*IMAGE_SIZE, y*IMAGE_SIZE, this);
                    }
                }
            }
        };

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {

                int x = e.getX()/IMAGE_SIZE;
                int y = e.getY()/IMAGE_SIZE;
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                   makeTurn(new Coord(x, y));
                }

            }
        });

        panel.setPreferredSize(new Dimension(SIZE*IMAGE_SIZE, SIZE*IMAGE_SIZE));
        add(panel);
    }

    private void initFrame()
    {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("White player's turn 1");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage(Tile.ICON));
    }

    private Image getImage(Tile tile)
    {
        String filename = "img/" + tile.getTitle().toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void makeTurn(Coord coord)
    {
        if(gameBoard.board[coord.x][coord.y].getStatus() == Status.EMPTY)
        {
            gameBoard.board[coord.x][coord.y].setStatus(gameBoard.turn);
            panel.repaint();

            if(gameBoard.winCondition(coord))
            {
                endGame();
            }
            else
            {
                if (gameBoard.tieCondition())
                {
                    tieGame();
                }
                else
                {
                    gameBoard.switchTurn();
                    turnNumber++;
                    String current = gameBoard.turn.getTitle();
                    this.setTitle(current + " player's turn " + turnNumber / 2);
                }
            }
        }
    }

    private void endGame()
    {
        String winner = gameBoard.turn.getTitle();
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("img/" + gameBoard.turn.getTile().getTitle() + ".png"));
        JOptionPane.showMessageDialog(this, winner + " player wins the game!",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE, icon);
        this.dispose();
    }


    private void tieGame()
    {
        JOptionPane.showMessageDialog(this, "It's a tie!",
                "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }


    private Tile getTile(int x, int y)
    {
        if(gameBoard.board[x][y].getStatus() == Status.EMPTY)
        {
            switch (y) {
                case (0):
                    switch (x) {
                        case (0):
                            return Tile.UL;
                        case (SIZE - 1):
                            return Tile.UR;
                        default:
                            return Tile.U;
                    }
                case (SIZE - 1):
                    switch (x) {
                        case (0):
                            return Tile.DL;
                        case (SIZE - 1):
                            return Tile.DR;
                        default:
                            return Tile.D;
                    }
                case (SIZE / 2 - 2):
                    switch (x) {
                        case (0):
                            return Tile.L;
                        case (SIZE - 1):
                            return Tile.R;
                        case (SIZE / 2 - 2):
                            return Tile.S;
                        case (SIZE / 2 + 2):
                            return Tile.S;
                        default:
                            return Tile.C;
                    }
                case (SIZE / 2 + 2):
                    switch (x) {
                        case (0):
                            return Tile.L;
                        case (SIZE - 1):
                            return Tile.R;
                        case (SIZE / 2 - 2):
                            return Tile.S;
                        case (SIZE / 2 + 2):
                            return Tile.S;
                        default:
                            return Tile.C;
                    }
                case (SIZE/2):
                    switch (x) {
                        case (0):
                            return Tile.L;
                        case (SIZE - 1):
                            return Tile.R;
                        case (SIZE/2):
                            return Tile.B;
                        default:
                            return Tile.C;
                    }
                default:
                    switch (x) {
                        case (0):
                            return Tile.L;
                        case (SIZE - 1):
                            return Tile.R;
                        default:
                            return Tile.C;
                    }
            }
        }
        else return gameBoard.board[x][y].getStatus().getTile();
    }
}
