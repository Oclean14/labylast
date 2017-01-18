package maze.generators;

import java.util.Arrays;
import java.util.Collections;

import maze.Cell;
import maze.Maze.Dir;

public class RandomFusionGenerator extends Generator{
	
	Cell[][] lab = new Cell[nCols][nRows];
	int[][] randTab = new int[nCols][nRows];
	
	
	
	public RandomFusionGenerator(int r, int c, int nRows, int nCols) {
		super(r, c, nRows, nCols);
		// TODO Auto-generated constructor stub
	}



	@Override
	  public int[][] generateMaze(int r, int c) {
		System.out.println(" je genere un laby dans randomfusion ");
		//génération de la grille pleine
	
		int i = 0;
	
		 for(int y = 0;y<nRows;y++)
	       {
	    	   for(int x = 0;x<nCols;x++)
	    	   {
	    		   randTab[x][y] = i;
	    		   System.out.print(randTab[x][y]+" ");
	    		   i++;
	    	   }
	    	   System.out.println();
	       }
		 adaptGroups();
		 System.out.println("Je suis dans generator et je vais afficher randTab");
		  for(int x = 0;x<nCols;x++)
	       {
	    	   for(int y = 0;y<nRows;y++)
	    	   {
	    		   System.out.print(randTab[x][y] + " ");
	    	   }
	    	   System.out.println();
	       }
	        /**Dir[] dirs = Dir.values();
	        Collections.shuffle(Arrays.asList(dirs));
	        for (Dir dir : dirs) {
	            int nc = c + dir.getDx();
	            int nr = r + dir.getDy();
	            
	            if (withinBounds(nr, nc) && mazeTab[nr][nc] == 0) {
	            	mazeTab[r][c] |= dir.getBit();
	            	mazeTab[nr][nc] |= dir.getOpposite().getBit();
	                
	                generateMaze(nr, nc);
	            }            
	        }*/
	        return randTab;        
	    }
	
	private Cell getRandomCoord()
	{
	
		int randomCol = (int)(Math.random() * (nCols));
		int randomRow = (int)(Math.random() * (nRows));
		return new Cell (randomCol,randomRow);
		
	
	}
	
	private boolean randTabIsallSame()
	{
		int reference = randTab[0][0];
		  for(int x = 0;x<nCols;x++)
	       {
	    	   for(int y = 0;y<nRows;y++)
	    	   {
	    		   if(randTab[x][y] != reference)
	    		   {
	    				System.out.println("le tableau n'est pas homogene");

	    			   return false;
	    		   }
	    		   
	    	   }
	    }
			System.out.println("le tableau est homogene");

		  return true;
		
	}
	
	private void adaptGroups()//fait tout le boutot
	{
		Cell randCell = getRandomCoord();
		Cell randNeightBoorCell = getRandomNeightboorCoords(randCell);
		
		while(!randTabIsallSame() )
		{

			
			 hasNeightboorTargetedGroup(randCell,
						randTab[randNeightBoorCell.getCol()][randNeightBoorCell.getRow()]);
				randCell = getRandomCoord();
				randNeightBoorCell = getRandomNeightboorCoords(randCell);
	
			/*System.out.println("Coordonnées de randCell : x = "+randCell.getCol()+" y = "+randCell.getRow());
			System.out.println("Coordonnées de randNeightBoorCell : x = "+randNeightBoorCell.getCol()+" y = "+randNeightBoorCell.getRow());*/
			
			 
				while(randNeightBoorCell == null)
				{
					System.out.println("Pas de voision exploitable pour randNeightboorCell");

					randCell = getRandomCoord();
					randNeightBoorCell = getRandomNeightboorCoords(randCell);
				}
			 try {
		            Thread.sleep(10000L);
		        } catch (InterruptedException ignored) {
		        }

		}
	}
	
	//private boolean isInSameGroup

	private boolean hasNeightboorTargetedGroup(Cell cell, int groupId) {//groupId c'est le nb à changer si on le trouve
	//	System.out.println("Dans hasNeightboor cell.getBottomEdge().getCol() = "+(cell.getBottomEdge().getCol()));
		int count = 0;
		if(!withinBounds(cell))
			return false;
		System.out.println("Coordonnées de la cellule choisi au hazard  dans has: x = "+cell.getCol()+" y = "+cell.getRow());
		System.out.println("groupId de la premiere cellule choisi au hazard  dans has: "+randTab[cell.getCol()][cell.getRow()] );

		System.out.println("groupId de la cellule voisine choisi = "+groupId);;

		  
				
		cell.setEdges();
		/*System.out.println("Dans hasNeightboor cell.getBottomEdge().getCol() = "+(cell.getBottomEdge().getCol()));
		System.out.println("Dans hasNeightboor cell.getBottomEdge().getRow() = "+(cell.getBottomEdge().getRow()));

		System.out.println("Dans hasNeightboor cell.getTopEdge().getCol() = "+(cell.getTopEdge().getCol()));
		System.out.println("Dans hasNeightboor cell.getTopEdge().getRow() = "+(cell.getTopEdge().getRow()));

		System.out.println("Dans hasNeightboor cell.getLeftEdge().getCol() = "+(cell.getLeftEdge().getCol()));
		System.out.println("Dans hasNeightboor cell.getLeftEdge().getRow() = "+(cell.getLeftEdge().getRow()));

		System.out.println("Dans hasNeightboor cell.getRightEdge().getCol() = "+(cell.getRightEdge().getCol()));
		System.out.println("Dans hasNeightboor cell.getRightEdge().getRow() = "+(cell.getRightEdge().getRow()));*/
		

		 for(int y = 0;y<nRows;y++)
	       {
	    	   for(int x = 0;x<nCols;x++)
	    	   {
	    		   System.out.print(randTab[x][y] + "   ");
	    	   }
	    	   System.out.println();
	       }
		
		while(count<4)
		{
		if(withinBounds(cell.getBottomEdge()) && count++<4 &&
				randTab[cell.getBottomEdge().getCol()][cell.getBottomEdge().getRow()] == groupId) {
			
			randTab[cell.getBottomEdge().getCol()][cell.getBottomEdge().getRow()] = randTab[cell.getCol()][cell.getRow()];
			
			return hasNeightboorTargetedGroup(cell.getBottomEdge(),groupId);
			}
			//randTab[cell.getBottomEdge().getCol()][cell.getBottomEdge().getRow()] = randTab[cell.getCol()][cell.getRow()];
		if(withinBounds(cell.getTopEdge())  && count++<4 
				&& randTab[cell.getTopEdge().getCol()][cell.getTopEdge().getRow()] == groupId) {
			
			randTab[cell.getTopEdge().getCol()][cell.getTopEdge().getRow()] = randTab[cell.getCol()][cell.getRow()];

			return hasNeightboorTargetedGroup(cell.getBottomEdge(),groupId);
			}
		if(withinBounds(cell.getRightEdge()) && count++<4 
				&& randTab[cell.getRightEdge().getCol()][cell.getRightEdge().getRow()] == groupId) {
			
			randTab[cell.getRightEdge().getCol()][cell.getRightEdge().getRow()] = randTab[cell.getCol()][cell.getRow()];

			return hasNeightboorTargetedGroup(cell.getRightEdge(),groupId);
			}
		if(withinBounds(cell.getLeftEdge()) && count++<4 
				&& randTab[cell.getLeftEdge().getCol()][cell.getLeftEdge().getRow()] == groupId) {
			
			randTab[cell.getLeftEdge().getCol()][cell.getLeftEdge().getRow()] = randTab[cell.getCol()][cell.getRow()];

			return hasNeightboorTargetedGroup(cell.getLeftEdge(),groupId);
			}
		}
		
		
		return true;
	}



	private Cell getRandomNeightboorCoords(Cell cell)
	{
	    System.out.println("Je vais chercher random un voisin valide de la cellule x = "+cell.getCol()+" et y = "+cell.getRow());

	
		int clock = 0;
		cell.setEdges();
		int random = (int)(Math.random() * 3);
		//choisi une position initiale et on tourne à partir de celle ci après avec clock tant qu'une position n'est pas convenable
		while(clock<4)
		{
			/*System.out.println(("(random+clock)%4 = "+((random+clock)%4)));
			System.out.println(("(clock) = "+(clock)));*/
			 try {
		            Thread.sleep(1000L);
		        } catch (InterruptedException ignored) {
		        }
			switch ((random+clock)%4)
	
			{
	//vérifie que la case choisi au hasard a un voisin dans le laby et est dans un groupe différente de celle ci
			  case (0):
			    if(withinBounds(cell.getBottomEdge()) &&
			    		(randTab[cell.getCol()][cell.getRow()] != randTab[cell.getBottomEdge().getCol()][cell.getBottomEdge().getRow()]
			    		)) {
					System.out.println("je renvoi la cellule du bas de coordonnées x = "+ cell.getBottomEdge().getCol()+" et y = "+cell.getBottomEdge().getRow());

			    	return cell.getBottomEdge();}
			  System.out.println("la cellule du bas est pas libre");
			    	
			   
			  case 1:
				    if(withinBounds(cell.getTopEdge()) &&
				    		(randTab[cell.getCol()][cell.getRow()] != randTab[cell.getTopEdge().getCol()][cell.getTopEdge().getRow()]
				    		)) {
						System.out.println("je renvoi la cellule du haut de coordonnées x = "+ cell.getTopEdge().getCol()+" et y = "+cell.getTopEdge().getRow());
				    	return cell.getTopEdge();}
				    System.out.println("la cellule du haut est pas libre");
				    	
			  case 2:
				    if(withinBounds(cell.getLeftEdge()) &&
				    		(randTab[cell.getCol()][cell.getRow()] != randTab[cell.getLeftEdge().getCol()][cell.getLeftEdge().getRow()]
				    		)) {
						System.out.println("je renvoi la cellule de gauche de coordonnées x = "+ cell.getLeftEdge().getCol()+" et y = "+cell.getLeftEdge().getRow());
				    	return cell.getLeftEdge();
				    	}
				    System.out.println("la cellule du gauche est pas libre");
			  case 3:
				    if(withinBounds(cell.getRightEdge()) &&
				    		(randTab[cell.getCol()][cell.getRow()] != randTab[cell.getRightEdge().getCol()][cell.getRightEdge().getRow()]
				    		)) {
						System.out.println("je renvoi la cellule de droite de coordonnées x = "+ cell.getRightEdge().getCol()+" et y = "+cell.getRightEdge().getRow());
				    	return cell.getRightEdge();
				    	}
				    System.out.println("la cellule du droite est pas libre");
	
			  default:
	
			}
			clock++;
		    System.out.println("");
		    System.out.println("");
		    System.out.println("");


		}

		return null;
	}
	
	
}
