package maze.solvers;

import maze.Cell;
import maze.Maze;
import maze.Maze.Dir;

public class PledgeSolver extends Solver{
	private Maze maze;
		// TODO Auto-generated constructor stubprivate Maze maze;
		
		public PledgeSolver(Maze maze) 
		{
			this.maze = maze;
		}
		@Override
		public
	    boolean solve(int pos) {
			 if (pos == (maze.getEndCell().getCol() + maze.getNCols()*  maze.getEndCell().getRow()))
		            return true;
			 System.out.println("direct pos = "+pos);
			

			/* System.out.println("x de la fin = " + maze.getEndCell().getCol());
			 System.out.println("y de la fin = "+ maze.getEndCell().getRow());
			 System.out.println("y * x = "+maze.getEndCell().getRow()*maze.getEndCell().getCol());*/
		        int c = pos %  maze.getNCols();	
		        int r = pos /  maze.getNCols();
		        System.out.println("dans solve C (x)= "+c+" R (y)= "+r+" et pos = "+pos);
		   	 /*System.out.println("x actuel = " + c);*/
			 System.out.println("je suis dans solve, je vais afficher la liste solution");
		        for (Dir dir : Dir.values()) {
		            int nc = c + dir.getDx();
		            int nr = r + dir.getDy();
		            
				   	 System.out.println("direction choisi : "+dir.name()+" donc la prochaine est : nc = "+nc+" et nr = "+nr);
				   	 try {
				            Thread.sleep(10000L);
				        } catch (InterruptedException ignored) {
				        }
			           /* for (int posa : maze.getSolution()) {
			                int x = posa % maze.getNCols();
			                int y = posa / maze.getNCols();
			                System.out.println("dans solve, d'après pos les solutions sont , X = "+x+" Y = "+y+" et posa = "+posa);
			                System.out.println("maze.getMaze()[r][c] = "+ maze.getMaze()[r][c]);
			                System.out.println();
			                System.out.println();
	
			            }*/
		            if (withinBounds(nr, nc,maze) && ( maze.getMaze()[r][c] & dir.getBit()) != 0
		                    && ( maze.getMaze()[nr][nc] ==16)) //& 16) == 0)
		            {
		 
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
	    public void forwardAndKeepWall(Cell cell) {
	    	
	    	int counter = 0;
	    	while(!cell.isTheExit(maze))
	    	{
	    		if(counter == 0)
	    			
	    	}
	    
	    	
	    }
	    
	    public boolean haveWallInDirection(Cell cell,String direction)
	    {
			switch (direction)
			
			{
			  case ("N"):
			  case ("E"):
			  case ("S"):
			  case ("O"):
			
	
			  default:
	
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


