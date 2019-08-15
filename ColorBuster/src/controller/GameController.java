
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import database.Driver;
import view.GameView;
import view.MainMenuView;
import view.ScoreBoardView;
import view.TileView;

public class GameController {
	
	private int score;
	private int gameStatus;
	private int rows;
	private int cols;
	
	private int moveNumber = 0;
	
	private GameView gameView;
	private MainMenuView mainMenuView;
	private ScoreBoardView scoreBoardView;
	private int width;
	private int height;

	public GameController() {
		super();		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				width = 500;
				height = 600;
				gameView = new GameView("Color Buster",7,7,width,height,null,new NewGameListener(), new TileTouchedListener(), new LevelListener(),  new ScoreBoardListener());
				scoreBoardView = new ScoreBoardView("Color Buster",width,height, new ScoreBoardListener());
				mainMenuView = new MainMenuView(gameView, width, height, new NewGameListener(), new ScoreBoardListener());
				mainMenuView.setVisible(true);


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
				String name = JOptionPane.showInputDialog(gameView,
					    "No more moves... Starting new game. Enter username to upload into database.");

				if (name == null);
				else if (name.equals("")) {
					name = null;
					Driver.insert(name, gameView.getScore());
				} else if (name != null)
					Driver.insert(name, gameView.getScore());

				gameView.newGame();
			}
		}
		
	}
	
	
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
			gameView.newGame();
			mainMenuView.dispose();
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

	class ScoreBoardListener implements ActionListener, WindowListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Showing Score Board...");
			mainMenuView.setEnabled(false);
			gameView.setEnabled(false);
			scoreBoardView.updateScoresFromDatabase();
			scoreBoardView.setVisible(true);
		}


		@Override
		public void windowOpened(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			mainMenuView.setEnabled(true);
			gameView.setEnabled(true);
			scoreBoardView.dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}
	}



}
