package maze;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import maze.solvers.OriginalSolver;

public class Main {
	
	private static int nRows=15;
	private static int nCols=15;
	public static Maze maze = new Maze(nRows,nCols);
	private static Cell start = new Cell(0,4,maze);
	private static Cell end = new Cell(5,10,maze);
	


    
	
	
	
	
	  public static void main(String[] args) {
		  
			
			maze.setStartCell(start);
			maze.setEndCell(end);
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

