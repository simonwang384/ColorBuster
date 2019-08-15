package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StartGameButtonView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton newGameButton;

    public StartGameButtonView(ActionListener startGameButtonListener) {
        newGameButton = new JButton();
        newGameButton.setText("New Game");
        newGameButton.addActionListener(startGameButtonListener);
        add(newGameButton);


    }
}
