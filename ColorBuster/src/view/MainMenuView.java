package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame{
    private GameView gv;
    private StartGameButtonView startGame;
    private ScoreBoardButtonView scoreBoardBtn;

    public MainMenuView(GameView gv, int width, int height, ActionListener newGameListener, ActionListener scoreBoardButtonListener) {
        super();
        this.gv = gv;
        setTitle(gv.getTitle());

        setLocation(100,50);
        setPreferredSize(new Dimension(width,height));


        startGame = new StartGameButtonView(newGameListener);
        scoreBoardBtn = new ScoreBoardButtonView(scoreBoardButtonListener);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        panel.add(new JLabel("<html><h1>Color Buster</h1></html>"), gbc);

        panel.add(startGame, gbc);
        panel.add(scoreBoardBtn, gbc);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }

}
