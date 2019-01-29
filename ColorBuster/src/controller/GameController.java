
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import view.GameView;
import view.TileView;

public class GameController {
	
	private int score;
	private int gameStatus;
	private int rows;
	private int cols;
	
	private int moveNumber = 0;
	
	private GameView gameView;
	
	

	
	public GameController() {
		super();		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				gameView = new GameView("Color Buster",6,6,null,new NewGameListener(), new TileTouchedListener(), new LevelListener());
				gameView.setVisible(true);	
				
			}
		});
		
	}

	
	
	class TileTouchedListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());
			
			gameView.processTouchedTile(tv);
			
			
			if (!gameView.isMoveAvailable()) {
				JOptionPane.showMessageDialog(gameView,
					    "No more moves...");
			}
		}
		
	}
	
	
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
			gameView.newGame();
			
		}
		
	}
	
	class LevelListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			System.out.println("New min match selected. Starting new game");
			JComboBox combo = (JComboBox)event.getSource();
			String level = (String)combo.getSelectedItem();
			gameView.newGame(Integer.parseInt(level));
			
		}
		
	}
}
