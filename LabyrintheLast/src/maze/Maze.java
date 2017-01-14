package maze;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.*;
import javax.swing.*;

import maze.solvers.AbstractSolver;
import maze.solvers.OriginalSolver;
 
public class Maze extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Dir {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
    	//vecteur (0,-1) pour Nord 
    	//vecteur (0,1) pour Sud
    	//vecteur (1,0) pour Est
    	//vecteur (-1,0) pour Ouest
    	//l'origine du repère est donc en haut à gauche
    	/*(0,0)--------------x------------------>
    		|
    		|
    		|
    		|
    		|
    		|
    		y
    		|
    		|
    		|
    		|
    		|
    		v
    	*/
        private final int bit;
        private final int dx;
        private final int dy;
        Dir opposite;
 
        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }
 
        Dir(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }

		public int getBit() {
			return bit;
		}

		public int getDy() {
			return dy;
		}

		public int getDx() {
			return dx;
		}
    };
    AbstractSolver solver;
    private final int nCols;
    private final int nRows;
    private final int cellSize = 25;
    private final int margin;
    private final int[][] maze;
    private LinkedList<Integer> solution;
    Cell start;
    Cell end;
 
    public Maze(int nRows,int nCols) {
    	
    	this.nCols = nCols;
        this.nRows = nRows;
        setPreferredSize(new Dimension(nCols*27, nRows*27));
        setBackground(Color.white);
        this.margin = 27;

        maze = new int[nRows][nCols];
        setSolution(new LinkedList<>());
        generateMaze(0, 0);
 
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new Thread(() -> {
                   solver.solve(0);
                }).start();
            }
        });
    }
    
    public void setSolver (OriginalSolver solver)
    {
    	this.solver = solver;
    }
    public void setStart (Cell start)
    {
    	this.start = start;
    }
    public void setEnd (Cell end)
    {
    	this.end = end;
    }
    public int getNCols() {
    	return nCols;
    }
    public int getNRows() {
    	return nRows;
    }
    void generateMaze(int r, int c) {
        Dir[] dirs = Dir.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Dir dir : dirs) {
            int nc = c + dir.getDx();
            int nr = r + dir.getDy();
            if (withinBounds(nr, nc) && getMaze()[nr][nc] == 0) {
                getMaze()[r][c] |= dir.getBit();
                getMaze()[nr][nc] |= dir.opposite.getBit();
                generateMaze(nr, nc);
            }
        }
    }
    
    //controle que le point passé en parametre est dans les limites du labyrinthe
    boolean withinBounds(int r, int c) {
        return c >= 0 && c < nCols && r >= 0 && r < nRows;
    }
    
    public Dir[] getDirValues()
    {
    	return Dir.values();
    }
    
   
    
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
 
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.black);
 
        // draw maze
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
 
                int x = margin + c * cellSize;
                int y = margin + r * cellSize;
 
                //a et b deux int. (a & b)renvoi 0 SI a diff de b SINON renvoi a
                
                if ((getMaze()[r][c] & 1) == 0) // N
                    g.drawLine(x, y, x + cellSize, y);
 
                if ((getMaze()[r][c] & 2) == 0) // S
                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
 
                if ((getMaze()[r][c] & 4) == 0) // E
                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
 
                if ((getMaze()[r][c] & 8) == 0) // W
                    g.drawLine(x, y, x, y + cellSize);
            }
        }
 
        // draw pathfinding animation
        //rien est dessiné avant le clique parce que solution est vide
        int offset = margin + cellSize / 2;
 
        Path2D path = new Path2D.Float();
        path.moveTo(offset, offset);
 
        for (int pos : getSolution()) {
            int x = pos % nCols * cellSize + offset;
            int y = pos / nCols * cellSize + offset;
            path.lineTo(x, y);
        }
 
        g.setColor(Color.orange);
        g.draw(path);
 
        g.setColor(Color.blue);
        g.fillOval(offset - 5, offset - 5, 10, 10);
 
        g.setColor(Color.green);
        int x = offset + (nCols - 1) * cellSize;
        int y = offset + (nRows - 1) * cellSize;
        g.fillOval(x - 5, y - 5, 10, 10);
 
    }
 

 
   
 

 
    public void animate() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException ignored) {
        }
        repaint();
    }

	public int[][] getMaze() {
		return maze;
	}

	public LinkedList<Integer> getSolution() {
		return solution;
	}

	public void setSolution(LinkedList<Integer> solution) {
		this.solution = solution;
	}
 
  
}