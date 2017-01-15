package maze;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import maze.solvers.OriginalSolver;

public class Main {
	
	private static int nRows=40;
	private static int nCols=40;
	
	private static int[] startCoord = {0,3};//(x,y) (numCol,numRow)
	private static int[] endCoord = {4,3};
	
	public static Maze maze = new Maze(nRows,nCols,startCoord,endCoord);


	
	
	  public static void main(String[] args) {

			maze.setSolver(new OriginalSolver(maze));//changeable
						
		    for(int x = 0;x<nCols;x++)
		       {
		    	   for(int y = 0;y<nRows;y++)
		    	   {
		    		   System.out.print(maze.getMaze()[x][y] + " ");
		    	   }
		    	   System.out.println();
		       }
			
	       SwingUtilities.invokeLater(() -> {
	            JFrame f = new JFrame();
	            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            f.setTitle("Maze Generator");
	            f.setResizable(false);
	            f.add(maze, BorderLayout.CENTER);
	            f.pack();
	            f.setLocationRelativeTo(null);
	            f.setVisible(true);
	        });
	   
	    }
}

