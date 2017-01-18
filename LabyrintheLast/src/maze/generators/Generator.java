package maze.generators;

import maze.Cell;
import maze.Maze;

public class Generator {
	
	
	Maze maze;
	int startingRow;
	int startingCol;
	int[][] mazeTab;
	int nCols;
	int nRows;
	
	public Generator(int r,int c,int nRows,int nCols) {

		this.startingCol = c;
		this.startingRow = r;
		this.nCols = nCols;
		this.nRows = nRows;
		this.mazeTab =  new int[nRows][nCols];
		
	}
	public Generator(int r,int c) {

		this.startingCol = c;
		this.startingRow = r;

	}
	
	public int[][] generateMaze(int i, int j, int[][] maze2) {
		return maze2;
		// TODO Auto-generated method stub
		
	}
	public int[][] generateMaze(int r, int c) {
		return mazeTab;
    
    }
	public int[][] getMaze() {
		return mazeTab;
	}
	public void setMaze(int[][] maze) {
		this.mazeTab = maze;
	}
	  //controle que le point passé en parametre est dans les limites du labyrinthe
    public boolean withinBounds(int r, int c) {
        return c >= 0 && c < nCols && r >= 0 && r < nRows;
    }
    //controle que le point passé en parametre est dans les limites du labyrinthe
    public boolean withinBounds(Cell cell) {
        return cell.getCol() >= 0 && cell.getCol() < nCols && cell.getRow() >= 0 && cell.getRow() < nRows;
    }
}
