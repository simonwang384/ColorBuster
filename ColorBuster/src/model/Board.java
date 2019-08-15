
package model;

import java.util.ArrayList;
import java.util.HashSet;

import model.Tile;

public class Board {
	

	private Tile[][] tiles;
	private int cols;
	private int rows;
	private int matchSize;
	private HashSet<Tile> matches;
	
	public Board(int rows, int cols, int matchSize) {
		this.rows = rows;
		this.cols = cols;
		this.matchSize = matchSize;
		this.matches = new HashSet<>();
		tiles = new Tile[rows][cols];
		initializeBoard();
	
	}
	
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.matchSize = 3;
		this.matches = new HashSet<>();
		tiles = new Tile[rows][cols];
		initializeBoard();
	
	}
	
	public void initializeBoard() {
		do {
			for (int row=0;row<rows;row++)
				for (int col=0;col<cols;col++) 
					tiles[row][col] = new Tile(row, col);
			if (!isMoveAvailable()) 
				System.out.println("**********No moves -- reinitializing board");
		} while(!isMoveAvailable());
	}
	
	public boolean hasTileAt(int row, int col) {
		if (tiles[row][col] != null)
			return true;
		else
			return false;
	}
	public Tile tileAt(int row, int col) {
		return tiles[row][col];
	}
	
	public void removeTileAt(int row, int col) {
		tiles[row][col] = null;
	}
	
	public void removeMatchingTiles(HashSet<Tile> matches) {
		for (Tile t: matches) {
			removeTileAt(t.getRow(), t.getCol());
		}
	}
	
	private void collapseColumn(int colToColapse) {
		ArrayList<Tile> newCol = new ArrayList<Tile>();
		
		for (int row=0; row<rows; row++) {
			if (hasTileAt(row, colToColapse)) {
				newCol.add(tileAt(row, colToColapse));
			}
		}
		
       
		int tilesToAdd = rows - newCol.size();
		System.out.println("Adding " + tilesToAdd + " new Tiles");
		
		for (int i=1;i<=tilesToAdd;i++) {
			newCol.add(new Tile(newCol.size() + i, colToColapse));
		}
		
		if (newCol.size() > 0) {
			for (int row=0; row<newCol.size(); row++) {
				tiles[row][colToColapse] = newCol.get(row);
				tiles[row][colToColapse].setRow(row);
				tiles[row][colToColapse].setCol(colToColapse);
			}

		}
	}
		
		
	
	
	public void collapseColumns() {
		for (int col=0; col<cols; col++) {
			collapseColumn(col);
		}
	}
	
	public boolean isNorthMatch (int row, int col,  int color) {
		if (row >= 0 && row <= rows-2 && tiles[row+1][col] != null && tiles[row+1][col].getColor() == color)
			return true;
		else
			return false;
	}
	
	public boolean isSouthMatch (int row, int col, int color) {
		if (row >=1  && row <= rows-1 && tiles[row-1][col] != null && tiles[row-1][col].getColor() == color)
			return true;
		else
			return false;
	}
	
	public boolean isEastMatch(int row, int col, int color) {
		if (col >=0 && col <= cols-2 && tiles[row][col+1] != null && tiles[row][col+1].getColor() == color)
			return true;
		else
			return false;
	}
	
	public boolean isWestMatch(int row, int col, int color) {
		if (col >=1 && col <= cols-1 &&  tiles[row][col-1] != null && tiles[row][col-1].getColor() == color)
			return true;
		else
			return false;
	}
	
	
	public void locateNeighbors(int row, int col, int color, HashSet<Tile> matches) {
		
		Tile b = tiles[row][col];
		if (matches.contains(b)) {
			return;
		} else {
			matches.add(b);
		}
		
		
		if (isNorthMatch(row,col,color)) {
			locateNeighbors(row+1,col,color, matches);
		}
		
		

		if (isSouthMatch(row,col,color)) {
			locateNeighbors(row-1,col,color, matches);
		}
		
		
		
		if (isEastMatch(row,col,color)) {
			locateNeighbors(row,col+1,color, matches);
		}
		
		
		
		if (isWestMatch(row,col,color)) {
			locateNeighbors(row,col-1,color, matches);
		}
	}
	
	public String toString() {
		String s = "";
		for (int row=rows-1;row>=0;row--) {
			for (int col=0;col<cols;col++)  {
				if (tiles[row][col] != null)
					s += tiles[row][col].toString();
				else
					s += "[-----]";
			}
			s += "\n";
		}
		return s;
	}
	
	public boolean isMoveAvailable() {
		for (int col=0; col<cols;col++) {
			for (int row=0; row< rows; row++) {
				int color = tiles[row][col].getColor();
				HashSet<Tile> matches = new HashSet<Tile>();
				locateNeighbors(row, col, color, matches);
				if (matches.size() >= matchSize) {
					return true;
				}
			}
		}
		return false;
	}

	public void createSuperTile(int row, int col, int color) {
		tiles[row][col] = new SuperTile(row,col,color);

	}
	
	public boolean updateBoard(int row, int col, int color) {
		matches.clear();
		locateNeighbors(row,col,color,matches);
		if(getMatchesSize() >= getMatchSize()) {
			if (containsSuperTile()) {
				System.out.println("Contains Super Tile");
				System.out.println("Destroying all matching tiles on the board.");
				destroyAllMatchingTilesOnBoard(color);
				return true;
			} else {
				boolean canCreateSuper = false;
				if (matches.size() > getMatchSize())
					canCreateSuper = true;

				removeMatchingTiles(matches);
				collapseColumns();

				if (canCreateSuper)
					createSuperTile(row, col, color);
				return true;
			}
		} else
			return false;
	}

	public void destroyAllMatchingTilesOnBoard(int color) {
		addMatchingColorTiles(color);
		removeMatchingTiles(matches);
		collapseColumns();
	}

	public boolean containsSuperTile() {
		for(Tile o: matches) {
			if (o instanceof SuperTile)
				return true;
		}
		return false;
	}

	public void addMatchingColorTiles(int color) {
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (color == tiles[i][j].getColor())
					matches.add(tiles[i][j]);
			}
		}
	}

	public int getMatchesSize() {
		return matches.size();
	}
	
	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getMatchSize() {
		return matchSize;
	}

	public void setMatchSize(int matchSize) {
		this.matchSize = matchSize;
	}
	
}
