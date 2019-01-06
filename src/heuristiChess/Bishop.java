package heuristiChess;

import java.util.ArrayList;

public class Bishop extends ChessPiece implements ChessPieceMovement {

	public Bishop(int count, boolean isBlack)
	{
		super(2, 7, isBlack); 
		setValue(30);
		if(isBlack) 
			setCoord( 2 + (count * 3), 0 ); 
		else 
			setCoord( 2 + (count * 3), 7 ); 
		
		setOnBoard(true); 
	}	

	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{ 
		ArrayList<Coord> coords= new ArrayList<Coord>(); 
		
		// Down Right
		int x0 = getX()+1;
		int y0 = getY()+1;
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
		
		// Down Left 
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
		
		// Up Right
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
		return coords;
	}
}
