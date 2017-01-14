package maze;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import maze.solvers.OriginalSolver;

public class Main {
	
	private static int nRows=15;
	private static int nCols=15;
	
	private static int[] startCoord = {1,1};
	private static int[] endCoord = {5,10};
	
	public static Maze maze = new Maze(nRows,nCols,startCoord,endCoord);

	
	


    
	
	
	
	
	  public static void main(String[] args) {

			maze.setSolver(new OriginalSolver(maze));//changeable

			
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

