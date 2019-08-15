
package view;
import javax.swing.*;
import java.awt.*;



import view.BoardView;
import java.awt.event.*;


public class GameView extends JFrame {
	
	
	
	
	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private BoardView boardView;
	private ScoreBoardButtonView scoreBoardButtonView;


	private int score;
	
	
	
	int rows = 8, cols = 8;
	int width, height;
	

	ActionListener newGameListener;
	ActionListener tileTouchedListener;
	ActionListener levelSelected;
	ActionListener scoreBoardButtonViewListener;
	
	
	
	
	public GameView(String title, int rows, int cols, int width, int height, MouseListener listener, ActionListener newGameListener, ActionListener tileTouched, ActionListener levelSelected, ActionListener scoreBoardButtonViewListener) throws HeadlessException {
		super(title);
		setResizable(false);
		
		this.width = width;
		this. height = height;
		score = 0;
		this.rows = rows;
		this.cols = cols;
		
		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouched;
		this.levelSelected = levelSelected;
		this.scoreBoardButtonViewListener = scoreBoardButtonViewListener;


		setBounds(100,50,width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		scoreView = new ScoreView();
    	add(scoreView, BorderLayout.NORTH);

    	JPanel panel = new JPanel();
    	buttonView = new ButtonView(newGameListener,levelSelected);

		scoreBoardButtonView = new ScoreBoardButtonView(scoreBoardButtonViewListener);
		buttonView.add(scoreBoardButtonView);

		panel.add(buttonView);
		add(panel,BorderLayout.SOUTH);
		boardView = new BoardView(rows,cols, tileTouchedListener);
		add(boardView, BorderLayout.CENTER);

				
	}
	
	
	public boolean isMoveAvailable() {
		if(boardView.isMoveAvailable())
			return true;
		else
			return false;
		
	}
	
	
	
	public void newGame() {
		

		Container c = getContentPane();
		c.remove(boardView);
		c.invalidate();
		pack();

		int matchSize = buttonView.getValue();
		
		boardView = null;
		score = 0;
		scoreView.updateScore(score);
		
		boardView = new BoardView(rows,cols, tileTouchedListener,matchSize);
		
		
		
		add(boardView, BorderLayout.CENTER);
		pack();
		revalidate();
		setBounds(100,50,width, height);
		setVisible(true);
		System.out.println(boardView);  
	}
	
	public void newGame(int matchSize) {
		
		
		Container c = getContentPane();
		c.remove(boardView);
		c.invalidate();
		pack();

		
		boardView = null;
		score = 0;
		scoreView.updateScore(score);
		
		boardView = new BoardView(rows,cols, tileTouchedListener,matchSize);
		
		
		
		add(boardView, BorderLayout.CENTER);
		pack();
		revalidate();
		setBounds(100,50,width, height);
		setVisible(true);
		System.out.println(boardView);  
	}
	
	public void processTouchedTile(TileView tv) {
		
		
		scoreView.updateScore(score += boardView.processTouchedTile(tv));
		System.out.println("GameView == processing tile touch");
		
		System.out.println(boardView); 
	}

	public int getScore() {
		return score;
	}
	


}
