package maze.solvers;

import maze.*;
import maze.Maze.Dir;


public class OriginalSolver extends AbstractSolver{
	
	private Maze maze;
	
	public OriginalSolver(Maze maze) 
	{
		this.maze = maze;
	}
	@Override
	public
    boolean solve(int pos) {
		 if (pos == maze.getNCols() *  maze.getNRows() - 1)
	            return true;
	 
	        int c = pos %  maze.getNCols();
	        int r = pos /  maze.getNCols();
	 
	        for (Dir dir : Dir.values()) {
	            int nc = c + dir.getDx();
	            int nr = r + dir.getDy();
	            if (withinBounds(nr, nc,maze) && ( maze.getMaze()[r][c] & dir.getBit()) != 0
	                    && ( maze.getMaze()[nr][nc] & 16) == 0) {
	 
	                int newPos = nr *  maze.getNCols() + nc;
	 
	                maze.getSolution().add(newPos);
	                maze.getMaze()[nr][nc] |= 16;
	 
	                maze.animate();
	 
	                if (solve(newPos))
	                    return true;
	 
	                maze.animate();
	 
	                maze.getSolution().removeLast();
	                maze.getMaze()[nr][nc] &= ~16;
	            }
	        }
	 
	        return false;
    }
    
    //controle que le point passé en parametre est dans les limites du labyrinthe
    boolean withinBounds(int r, int c,Maze maze) {
        return c >= 0 && c < maze.getNCols() && r >= 0 && r < maze.getNRows();
    }
    public void setMaze(Maze maze)
    {
    	this.maze = maze;
    }
    
}
