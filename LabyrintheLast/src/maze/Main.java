package maze;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import maze.solvers.OriginalSolver;

public class Main {
	
	private static Cell start;
	private static Cell end;
	private static int nRows=30;
	private static int nCols=50;


    
	public static Maze maze = new Maze(nRows,nCols);
	
	
	
	  public static void main(String[] args) {
		  
			
			maze.setStart(start);
			maze.setEnd(end);
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

