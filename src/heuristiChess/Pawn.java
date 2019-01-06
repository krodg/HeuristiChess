package heuristiChess;

import java.util.ArrayList;

public class Pawn extends ChessPiece implements ChessPieceMovement  { 
	
	public Pawn(int count, boolean isBlack)
	{
		super(count, 6, isBlack); 
		setValue(10);
		if(isBlack) 
			setCoord(count,1); 
		else 
			setCoord(count,6); 
		
		setOnBoard(true); 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>(); 
		if(getPieceColour()) 
			y0 = getY()+1; 
		else
			y0 = getY()-1;
		
		// Move up one
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0))  
				coords.add(new Coord(x0,y0));
		
			if(x0<7)
			{
				// Attack up right
				if(enemyHere(pieces[x0+1][y0]))
				{	
					coords.add(new Coord(x0+1,y0));
				}
			}
			if(x0>1)
			{
				// Attack up left
				if(enemyHere(pieces[x0-1][y0]))
				{
					coords.add(new Coord(x0-1,y0));
				}
			}
			
		}
		// Double Up
		if(!getHasMoved())
		{
			x0 = getX();
			y0 = getY();
			if(getPieceColour()) {
				if(!outOfRange(x0, y0+2))
					coords.add(new Coord(x0,y0+2));
			}
			else {
				if(!outOfRange(x0, y0-2))
					coords.add(new Coord(x0,y0-2));
			}
		} 
		 
		return coords;
	}
}
