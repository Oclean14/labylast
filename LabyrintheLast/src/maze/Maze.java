package maze;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import maze.generators.OriginalGenerator;
import maze.solvers.OriginalSolver;
import maze.solvers.Solver;
 
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
        private Dir opposite;
 
        // use the static initializer to resolve forward references
        static {
            N.setOpposite(S);
            S.setOpposite(N);
            E.setOpposite(W);
            W.setOpposite(E);
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

		public Dir getOpposite() {
			return opposite;
		}

		public void setOpposite(Dir opposite) {
			this.opposite = opposite;
		}
    };
    private Solver solver;
    
    private final int nCols;
    private final int nRows;
    private final int cellSize = 25;
    private final int margin=27;
    private int[][] maze;
    private final int offset = margin + cellSize / 2;
    private LinkedList<Integer> solution=new LinkedList<>();
    Cell start;
    Cell end;
 
    public Maze(int nRows,int nCols, int[] startCoord, int[] endCoord) {
    	
    	this.nCols = nCols;
        this.nRows = nRows;
       // generator.setMaze(this);
       
        
        setPreferredSize(new Dimension(nCols*cellSize+2*margin, nRows*cellSize+2*margin));
        setBackground(Color.white);
        
        start = new Cell(startCoord[0],startCoord[1],this);
        end = new Cell(endCoord[0],endCoord[1],this);
        Cell mouse = start;
        maze = new int[nRows][nCols];
        setSolution(new LinkedList<>());
       //solution.add(mouse.getRow() * nCols +mouse.getCol());
        OriginalGenerator generator = new OriginalGenerator(0,0,nCols,nRows);
        
        maze = generator.generateMaze(0, 0);//(0,0) coordonné départ de la création 
		   System.out.println("maze après génération ");

        for(int x = 0;x<nCols;x++)
	       {
	    	   for(int y = 0;y<nRows;y++)
	    	   {
	    		   System.out.print(getMaze()[x][y] + " ");
	    	   }
	    	   System.out.println();
	       }
		
        //generateMaze(0,0);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new Thread(() -> {
                	System.out.println("Au départ mouse est à cette pos là (avant 1 er solve): "+ (mouse.getRow() * nCols +mouse.getCol()));
                   solver.solve(mouse.getRow() * nCols +mouse.getCol());
                }).start();
            }
        });
    }
    /*public void generateMaze(int r, int c) {
    	  System.out.println("J'entre dans generateMaze, r = "+r+" et c = "+c);
	        Dir[] dirs = Dir.values();
	        Collections.shuffle(Arrays.asList(dirs));
	        for (Dir dir : dirs) {
	            int nc = c + dir.getDx();
	            System.out.println("nc = c + dir.getDx()");
	            System.out.println("nc =" +(c + dir.getDx()));
	            

	            int nr = r + dir.getDy();
	            System.out.println("nr = r + dir.getDy()");
	            System.out.println("nr = "+(r + dir.getDy()));
	            if (withinBounds(nr, nc) && getMaze()[nr][nc] == 0) {
	            	getMaze()[r][c] |= dir.getBit();
	                System.out.println("maze.getMaze()[r][c] = "+ getMaze()[r][c]+" et  dir.get().getBit() ="+ dir.getBit());

	            	System.out.println("maze.getMaze()[r][c] |= dir.getBit() renvoi "+(getMaze()[r][c] |= dir.getBit()));
	
	                getMaze()[nr][nc] |= dir.getOpposite().getBit();
	                System.out.println("maze.getMaze()[nr][nc] = "+ getMaze()[nr][nc]+" et  dir.getOpposite().getBit() ="+ dir.getOpposite().getBit());
	                System.out.println("maze.getMaze()[nr][nc] |= dir.getOpposite().getBit() renvoi "+(getMaze()[nr][nc] |= dir.getOpposite().getBit()));
		            System.out.println("");
	                generateMaze(nr, nc);
	            }
	            
	        }
	        System.out.println();
	        System.out.println();
	        System.out.println();

	        
    }*/
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
   /* void generateMaze(int r, int c) {
        Dir[] dirs = Dir.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Dir dir : dirs) {
            int nc = c + dir.getDx();
            int nr = r + dir.getDy();
            if (withinBounds(nr, nc) && getMaze()[nr][nc] == 0) {
                getMaze()[r][c] |= dir.getBit();
                getMaze()[nr][nc] |= dir.getOpposite().getBit();
                generateMaze(nr, nc);
            }
        }
    }*/
    
    //controle que le point passé en parametre est dans les limites du labyrinthe
    public boolean withinBounds(int r, int c) {
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
 
        g.setStroke(new BasicStroke(5));//pour changer la taille des barrieres
        
        g.setColor(Color.black);//pour changer la couleur des barrieres
 
        // draw maze
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
 
                int x = margin + c * cellSize;
                int y = margin + r * cellSize;
 
                //a et b deux int. (a & b)renvoi 0 SI a diff de b SINON renvoi a
                //test chaque cellule pour savoir quelle barriere dessiner autour avec le codage des directions (voir compréhension code section new)
                //le dessin du contour semble implicite
                if ((getMaze()[r][c] & 1) == 0) { // N
                	System.out.println("Je suis en ("+c+","+r+") Je dessine la ligne du haut ");
                	g.drawLine(x, y, x + cellSize, y);
                }
 
                if ((getMaze()[r][c] & 2) == 0) { // S
                	System.out.println("Je suis en ("+c+","+r+") et Je dessine la ligne du bas");
                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
 
            }
               
                if ((getMaze()[r][c] & 4) == 0) {// E
                	System.out.println("Je suis en ("+c+","+r+") et Je dessine la ligne de droite ");
                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
 
            }
                if ((getMaze()[r][c] & 8) == 0) { // W
                	System.out.println("Je suis en ("+c+","+r+") Je dessine la ligne de gauche ");
                    g.drawLine(x, y, x, y + cellSize);
            }
            }
        }
 
        // draw pathfinding animation
        //rien est dessiné avant le clique parce que solution est vide
        
 
        Path2D path = new Path2D.Float();
        path.moveTo(offset+start.getCol()*cellSize, offset+start.getRow()*cellSize);
        System.out.println("Position successive dans solution (lors du dessin dans maze : ");
       
        for (int pos : getSolution()) {
            int x = pos % nCols * cellSize + offset;
            int y = pos / nCols * cellSize + offset;
            System.out.println("X = "+((x-offset)/cellSize)+" Y = "+((y-offset)/cellSize));
            path.lineTo(x, y);
        }
 
        //dessine le serpent
        g.setColor(Color.orange);
        g.draw(path);
 
        //dessine le point de départ
        g.setColor(Color.blue);
        g.fillOval(offset - 5+cellSize*getStartCell().getCol(), offset - 5 + cellSize*getStartCell().getRow(), 10, 10);
 
        //dessine le point d'arrivée
        g.setColor(Color.green);
        int x = offset + (getEndCell().getCol() ) * cellSize;
        int y = offset + (getEndCell().getRow() ) * cellSize;
        g.fillOval(x - 5, y - 5, 10, 10);
 
    }
 

 
   

	public Cell getEndCell() {
		return end;
	}

	public Cell getStartCell() {
		return start;
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
		System.out.println("getPosition est appelé");
		return solution;
	}

	public void setSolution(LinkedList<Integer> solution) {
		System.out.println("setPosition est appelé");

		this.solution = solution;
	}

	
  
}