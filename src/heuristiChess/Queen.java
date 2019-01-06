package heuristiChess;

import java.util.ArrayList;

public class Queen extends ChessPiece implements ChessPieceMovement{


	public Queen(boolean isBlack)
	{
		super(3, 7, isBlack); 
		setValue(90);
		if(this.getPieceColour()) 
			setCoord(3,0); 
		else 
			setCoord(3,7);
		
		//System.out.println(getCoord().toString() + " is "+ isBlack);
		
		setOnBoard(true); 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();
		
		// Down
		y0 = getY()+1; 
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			y0++;
		}
		
		// Up
		y0 = getY()-1; 
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			y0--;
		}
		y0 = getY();
		
		// Right
		x0 = getX()+1; 
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0++;
		}
		
		// Left
		x0 = getX()-1; 
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0--;
		}
		
		// Up Left
		x0 = getX()-1;
		y0 = getY()-1; 
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0--;
			y0--;
		}

		// Down Right
		x0 = getX()+1;
		y0 = getY()+1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0++;
			y0++;
		}
		
		// Down left
		x0 = getX()-1;
		y0 = getY()+1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0--;
			y0++;
		}
		// Down Right
		x0 = getX()+1;
		y0 = getY()-1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0++;
			y0--;
		} 
		return coords;
	}
	
}
