package maze;

public class Cell {

	public Cell CELL_LEFT;
	public Cell CELL_TOP;
	public Cell CELL_RIGHT;
	public Cell CELL_BOTTOM;
	// The cell is visited or not
	private boolean isVisited = false;
	private int col;
	private int row;
	private int indicator;//position codé 
	Maze maze;
	public Cell(int col, int row ,Maze maze){
		this.setCol(col);
		this.setRow(row);
		this.maze = maze;
		

	}
	public Cell(int col, int row){
		this.setCol(col);
		this.setRow(row);
		
		

	}
	public Cell() {}
	
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
	
	public void setEdges() {
		this.CELL_LEFT =  new Cell(col-1,row);
		this.CELL_TOP =  new Cell(col,row-1);
		this.CELL_RIGHT =  new Cell(col+1,row);
		this.CELL_BOTTOM =  new Cell(col,row+1);
	}
	public void setIndicator(int indic)
	{	
		this.indicator = indic;
		
	}
	public int getIndicator()
	{
		return indicator;
	}
	public int[] getCellCoord()
	{
		 int[] tab = {col,row};
		return tab;
	}
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
