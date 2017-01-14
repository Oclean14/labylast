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
		 if (pos == maze.getEndCell().getCol() +  maze.getNCols() * ( maze.getEndCell()).getRow())//vérifie que l'arrivée et le départ ne sont pas confondu. Il faudra pouvoir mettre le départ
	            return true;
		 
		 System.out.println("x de la fin = "+maze.getEndCell().getCol());
		 System.out.println("y de la fin = "+maze.getEndCell().getRow());
		 System.out.println("y * x = "+maze.getEndCell().getRow()*maze.getEndCell().getCol());
		 System.out.println("pos = "+pos);

	 
	        int currentCol= pos %  maze.getNCols();//récupère le numéro de colonne
	        int currentRaw= pos /  maze.getNCols();//récupère le numéro de ligne
	    	//System.out.println("Je suis dans solve je vais dire les values des Dir successivement : ");
	        for (Dir dir : Dir.values()) {
	        
	        	
	            int nextCol =currentCol+ dir.getDx();
	            int nextRow = currentRaw+ dir.getDy();
	            System.out.println("nextCol =currentCol+ dir.getDx()");
	            System.out.println(nextCol +"="+currentCol+" + "+ dir.getDx());
	            System.out.println("nextRow = currentRaw+ dir.getDy();");
	            System.out.println(nextRow +"="+ currentRaw+" + "+ dir.getDy());
	            System.out.println("c = "+currentCol+" et currentRaw= "+currentRaw);
	            System.out.println();
	            System.out.println();
	            System.out.println();
	            System.out.println();


	            try {
					Thread.sleep(1L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (withinBounds(nextRow, nextCol,maze) && ( maze.getMaze()[currentRaw][currentCol] & dir.getBit()) != 0
	                    && ( maze.getMaze()[nextRow][nextCol] & 16) == 0) {
	 
	                int newPos = nextRow *  maze.getNCols() + nextCol;
	 
	                maze.getSolution().add(newPos);
	                maze.getMaze()[nextRow][nextCol] |= 16;
	 
	                maze.animate();
	 
	                if (solve(newPos))
	                    return true;
	 
	                maze.animate();
	 
	                maze.getSolution().removeLast();
	                maze.getMaze()[nextRow][nextCol] &= ~16;
		            System.out.println("maze.getMaze()[nextRow][nextCol] &= ~16 renvoi "+ (maze.getMaze()[nextRow][nextCol] &= ~16) );

	            }
	        }
	 
	        return false;
    }
    
    //controle que le point passé en parametre est dans les limites du labyrinthe
    boolean withinBounds(int r, int c,Maze maze) {
      
    	if(c>= 0 && c< maze.getNCols() && r>= 0 && r< maze.getNRows())
    		return true;
    	else {
    	try {
   					Thread.sleep(1L);
   				} catch (InterruptedException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
        
        return false;
    	}
    }
    public void setMaze(Maze maze)
    {
    	this.maze = maze;
    }
    
}
