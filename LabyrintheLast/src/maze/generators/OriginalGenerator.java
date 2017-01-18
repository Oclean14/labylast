package maze.generators;

import java.util.Arrays;
import java.util.Collections;

import maze.Maze.Dir;

public class OriginalGenerator extends Generator{
	



	public OriginalGenerator(int r, int c, int nRows, int nCols) {
		super(r, c, nRows, nCols);
		// TODO Auto-generated constructor stub
	}



	@Override
	  public int[][] generateMaze(int r, int c) {
			
		//  System.out.println("J'entre dans generateMaze, r = "+r+" et c = "+c);
	        Dir[] dirs = Dir.values();
	        Collections.shuffle(Arrays.asList(dirs));
	        for (Dir dir : dirs) {
	            int nc = c + dir.getDx();
	           // System.out.println("nc = c + dir.getDx()");
	            //System.out.println("nc =" +(c + dir.getDx()));
	            

	            int nr = r + dir.getDy();
	            //System.out.println("nr = r + dir.getDy()");
	           // System.out.println("nr = "+(r + dir.getDy()));
	            if (withinBounds(nr, nc) && mazeTab[nr][nc] == 0) {
	            	mazeTab[r][c] |= dir.getBit();
	               // System.out.println("mazeTabr][c] = "+ mazeTab[r][c]+" et  dir.get().getBit() ="+ dir.getBit());

	            	//System.out.println("maze.getMaze()[r][c] |= dir.getBit() renvoi "+(mazeTab[r][c] |= dir.getBit()));
	
	            	mazeTab[nr][nc] |= dir.getOpposite().getBit();
	                //System.out.println("mazeTab[nr][nc] = "+ mazeTab[nr][nc]+" et  dir.getOpposite().getBit() ="+ dir.getOpposite().getBit());
	               // System.out.println("mazeTab[nr][nc] |= dir.getOpposite().getBit() renvoi "+(mazeTab[nr][nc] |= dir.getOpposite().getBit()));
		           // System.out.println("");
	                generateMaze(nr, nc);
	            }
	            
	        }
	        //System.out.println();
	        //System.out.println();
	       // System.out.println();
			return mazeTab;

	        
	    }

}
