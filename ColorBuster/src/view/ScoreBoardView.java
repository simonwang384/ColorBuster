package view;

import database.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;


public class ScoreBoardView extends JFrame {

    private String scores[][];
    private JTable scoreBoard;

    public ScoreBoardView(String title, int width, int height, WindowListener w) {
        super();
        setTitle(title);
        setLocation(100,50);
        setPreferredSize(new Dimension(width,height));
        addWindowListener(w);



        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("<html><h1>Score Board</i></strong></h1></html>"));
        add(panel1, BorderLayout.PAGE_START);

        JScrollPane panel2 = new JScrollPane();
        updateScoresFromDatabase();


        panel2.setViewportView(scoreBoard);
        add(panel2,BorderLayout.CENTER);

        pack();
        setResizable(false);

    }

    public void updateScoresFromDatabase() {
        this.scores = Driver.getTop10Scores();
        String[] columnNames = {"Names", "Scores"};
        scoreBoard = new JTable(scores, columnNames);
        scoreBoard.setEnabled(false);

    }


}
