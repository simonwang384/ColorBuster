
package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Board;
import model.Tile;
import view.TileView;


public class BoardView extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private Board b;
	private int rows;
	private int cols;
	private int tileSize;
	private ActionListener listener;
	private int spacer;

	
	private TileView [][] tileGrid;
	
	
	public BoardView(int rows, int cols, ActionListener lis, int matchSize) {
	    spacer = 6;
		listener = lis;
		Dimension s = new Dimension(500,600);
		System.out.println("BoardView: " + s);
		this.rows = rows;
		this.cols = cols;	
			
		int totalSpace = (cols+1) * spacer;
		System.out.println("totalspace : " + totalSpace);
		
		tileSize = (s.width-totalSpace) / cols;
		System.out.println("TileSize : " + tileSize);
		
		
		
		
		setLayout(null);
		tileGrid = new TileView[rows][cols];
		
		b = new Board(rows,cols,matchSize);

		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				TileView tv = new TileView(b.tileAt(row, col));
				add(tv);
				tileGrid[row][col] = tv;
			
				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows-1)-row) + spacer),
							             tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows-1)-row) + spacer));
				
				tv.addListener(listener);

				
			}
		}
	}
	
	
	
	public BoardView(int rows, int cols, ActionListener lis) {
	    spacer = 6;
		listener = lis;
		Dimension s = new Dimension(500,600);
		System.out.println("BoardView: " + s);
		this.rows = rows;
		this.cols = cols;	
			
		int totalSpace = (cols+1) * spacer;
		System.out.println("totalspace : " + totalSpace);
		
		tileSize = (s.width-totalSpace) / cols;
		System.out.println("TileSize : " + tileSize);
		
		
		
		
		setLayout(null);
		tileGrid = new TileView[rows][cols];
		
		b = new Board(rows,cols);
		
		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				TileView tv = new TileView(b.tileAt(row, col));
				add(tv);
				tileGrid[row][col] = tv;
			
				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows-1)-row) + spacer),
							             tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows-1)-row) + spacer));
				
				tv.addListener(listener);

				
			}
		}
		
	}
	

	
	public void updateBoardViewFromBoard() {
		System.out.println("In updateBoardViewFromBoard...");
		TileView tv;
		removeAll();
		tileGrid = new TileView[rows][cols];
		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				if (b.hasTileAt(row,col)) {
					 tv = new TileView(b.tileAt(row, col));
				}
				else  {
					tv = new TileView(row,col);
					
				}
				add(tv);
				tileGrid[row][col] = tv;
			
			
				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows-1)-row) + spacer),
							             tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows-1)-row) + spacer));
				
				tv.addListener(listener);

				
			}
		}
		
		
	}

	
	public int processTouchedTile(TileView tv) {

		
		int score = 0;
		if(b.updateBoard(tv.getRow(), tv.getCol(), tv.getColor())) {
			score = b.getMatchesSize() * 100;
		} else
			JOptionPane.showMessageDialog(this, "Not enough matches");
	
		
		updateBoardViewFromBoard();
		System.out.println(b);
		return score; 
	}
	
	
	public String toString() {
		return b.toString();
		
	}
	

	public boolean isMoveAvailable() {
		return b.isMoveAvailable();
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}
	
	
	public Board getB() {
		return b;
	}

	public void setB(Board b) {
		this.b = b;
	}
	
}
