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

    public static void main(String[] args)
    {
        new Board();
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
                        String tile = getTile(x, y);
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
        setIconImage(getImage("icon"));
    }

    private Image getImage(String name)
    {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void makeTurn(Coord coord)
    {
        if(Board.board[coord.x][coord.y].getStatus().equals("E"))
        {
            Board.board[coord.x][coord.y].setStatus(Board.turn);
            panel.repaint();

            if(Board.winCondition(coord))
            {
                endGame();
            }
            else
            {
                Board.switchTurn();
                turnNumber++;
                String current;

                if(Board.turn.equals("B"))
                    current = "Black";
                else
                    current = "White";

                this.setTitle(current+ " player's turn "+turnNumber/2);
            }
        }
    }

    private void endGame()
    {
        String winner;

        if(Board.turn.equals("B"))
            winner = "Black";
        else
            winner = "White";

        ImageIcon icon = new ImageIcon(getClass().getResource("img/"+Board.turn+".png"));
        JOptionPane.showMessageDialog(this, winner+" player wins the game!",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE, icon);
        this.dispose();
    }

    private String getTile(int x, int y)
    {
        if(Board.board[x][y].getStatus().equals("E"))
        {
            switch (y) {
                case (0):
                    switch (x) {
                        case (0):
                            return "UL";
                        case (SIZE - 1):
                            return "UR";
                        default:
                            return "U";
                    }
                case (SIZE - 1):
                    switch (x) {
                        case (0):
                            return "DL";
                        case (SIZE - 1):
                            return "DR";
                        default:
                            return "D";
                    }
                case (5):
                    switch (x) {
                        case (0):
                            return "L";
                        case (SIZE - 1):
                            return "R";
                        case (5):
                            return "S";
                        case (9):
                            return "S";
                        default:
                            return "C";
                    }
                case (9):
                    switch (x) {
                        case (0):
                            return "L";
                        case (SIZE - 1):
                            return "R";
                        case (5):
                            return "S";
                        case (9):
                            return "S";
                        default:
                            return "C";
                    }
                case (7):
                    switch (x) {
                        case (0):
                            return "L";
                        case (SIZE - 1):
                            return "R";
                        case (7):
                            return "B";
                        default:
                            return "C";
                    }
                default:
                    switch (x) {
                        case (0):
                            return "L";
                        case (SIZE - 1):
                            return "R";
                        default:
                            return "C";
                    }
            }
        }
        else return Board.board[x][y].getStatus();
    }
}
