
package view;
import javax.swing.JFrame;
import java.awt.*;



import view.BoardView;
import java.awt.event.*;


public class GameView extends JFrame {
	
	
	
	
	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private BoardView boardView;
	
	private int score;
	
	
	
	int rows = 8, cols = 8;
	int width, height;
	

	ActionListener newGameListener;
	ActionListener tileTouchedListener;
	ActionListener levelSelected;
	
	
	
	
	public GameView(String title, int rows, int cols, MouseListener listener, ActionListener newGameListener, ActionListener tileTouched, ActionListener levelSelected) throws HeadlessException {
		super(title);
		setResizable(false);
		
		width = 400;
		height = 500;
		score = 0;
		this.rows = rows;
		this.cols = cols;
		
		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouched;
		this.levelSelected = levelSelected;
		
		
		
		setBounds(100,50,width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		scoreView = new ScoreView();
    	add(scoreView, BorderLayout.NORTH);
		
    	buttonView = new ButtonView(newGameListener,levelSelected);
    	add(buttonView, BorderLayout.SOUTH);
    	
    	boardView = new BoardView(rows,cols, tileTouchedListener);
		add(boardView, BorderLayout.CENTER);
		
		setVisible(true);
				
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
		
		System.out.println(boardView);  
	}
	
	public void processTouchedTile(TileView tv) {
		
		
		scoreView.updateScore(score += boardView.processTouchedTile(tv));
		System.out.println("GameView == processing tile touch");
		
		System.out.println(boardView); 
	}
	
	


}
