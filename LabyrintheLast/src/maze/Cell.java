package maze;

import java.awt.Color;
import java.nio.charset.MalformedInputException;


public class Cell {
	
	private boolean[] edges = new boolean[4];
	private boolean[] exploitability = new boolean[4];
	public static Cell CELL_LEFT;
	public static Cell CELL_TOP;
	public static Cell CELL_RIGHT;
	public static Cell CELL_BOTTOM;
	// The cell is visited or not
	private boolean isVisited = false;
	private int col;
	private int row;
	Maze maze;
	
	public Cell( int row, int col,Maze maze){
		this.setCol(col);
		this.setRow(row);
		/*this.CELL_LEFT =  new Cell(row,col-1,maze);
		this.CELL_TOP =  new Cell(row+1,col,maze);
		this.CELL_RIGHT =  new Cell(row,col+1,maze);
		this.CELL_BOTTOM =  new Cell(row-1,col,maze);*/

	}
	
	/*public Cell(Cell cell){
		this.edges[CELL_LEFT] = getLeftEdge();
		this.edges[CELL_TOP] = getTopEdge();
		this.edges[CELL_RIGHT] = getRightEdge();
		this.edges[CELL_BOTTOM] = getBottomEdge();
		this.exploitability[CELL_LEFT] = getLeftExploitability();
		this.exploitability[CELL_RIGHT] = getRightExploitability();
		this.exploitability[CELL_TOP] = getTopExploitability();
		this.exploitability[CELL_BOTTOM] = getBottomExploitability();
	}*/
	
	 //controle que le point passé en parametre est dans les limites du labyrinthe
    boolean withinBounds(Cell cell){
        return cell.getCol() >= 0 && cell.getCol() < maze.getNCols() && cell.getRow() >= 0 && cell.getRow() < maze.getNRows();
    }
    
    public boolean isTheStart(Maze maze) {
    	
    		return ((this.getCol() == maze.getStartCell().getCol()) && (this.getRow() == maze.getStartCell().getRow()));
    }
    
    public boolean isTheExit(Maze maze) {
    	
		return ((this.getCol() == maze.getEndCell().getCol()) && (this.getRow() == maze.getEndCell().getRow()));
}
    
    public Cell isExploitable(Cell cell)//renvoi la cellule correspondante à la direction exploitable si possible, null sinon
    {
    	if(withinBounds(cell))
    		return cell;
		return cell;
    }
	public Cell getLeftEdge(){
		return CELL_LEFT;
	}
	
	public Cell getTopEdge(){
		return CELL_TOP;
	}
	
	public Cell getRightEdge(){
		return CELL_RIGHT;
	}
	
	public Cell getBottomEdge(){
		return CELL_BOTTOM;
	}
	
	public boolean isAvailable(){
		return this.exploitability[CELL_LEFT];
	}
	
	public void setLeftExploitability(boolean  exploitability){
		this.exploitability[CELL_LEFT] = exploitability;
	}
	
	public void setRightExploitability(boolean  exploitability){
		this.exploitability[CELL_RIGHT] = exploitability;
	}
	
	public void setTopExploitability(boolean  exploitability){
		this.exploitability[CELL_TOP] = exploitability;
	}
	
	public void setBottomExploitability(boolean  exploitability){
		this.exploitability[CELL_BOTTOM] = exploitability;
	}
	
	public boolean getRightExploitability(){
		return this.exploitability[CELL_RIGHT];
	}
	
	public boolean getTopExploitability(){
		return this.exploitability[CELL_TOP];
	}
	
	public boolean getBottomExploitability(){
		return this.exploitability[CELL_BOTTOM];
	}
	
	public void openWall(final int side){
		
		System.out.println("Open " + side + " edge" );
		this.edges[side] = false;
	}
	
	public void setVisited(final boolean isVisited){
		this.isVisited = isVisited;
	}
	
	public boolean getVisited(){
		return this.isVisited;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	
}
