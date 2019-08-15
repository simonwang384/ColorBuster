package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ScoreBoardButtonView extends JButton {
    private static final long serialVersionUID = 1L;


    public ScoreBoardButtonView(ActionListener scoreBoardButtonListener) {
        setText("Score Board");
        addActionListener(scoreBoardButtonListener);
    }
}
