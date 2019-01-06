package heuristiChess;

import java.util.ArrayList;

public class Knight extends ChessPiece implements ChessPieceMovement 
{

	public Knight(int count, boolean isBlack)
	{
		super(count*7, 7, isBlack); 
		setValue(30);
		if(isBlack) 
			setCoord(1+count*5,0); 
		else 
			setCoord(1+count*5,7);
		
		//System.out.println(getCoord().toString() + " is "+ isBlack);
		
		setOnBoard(true); 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();
		
		// Down 1 Right 2
		x0 = getX()+2;
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up 1 Right 2
		x0 = getX()+2;
		y0 = getY()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down 1 left 2
		x0 = getX()-2;
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (pieces[x0][y0] instanceof EmptyPiece) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		};
		
		// Up 1 Left 2
		x0 = getX()-2;
		y0 = getY()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down 2 Right 1
		x0 = getX()+1;
		y0 = getY()+2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up 2 Right 1
		x0 = getX()+1;
		y0 = getY()-2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down 2 Right 1
		x0 = getX()-1;
		y0 = getY()+2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up 2 Left 1
		x0 = getX()-1;
		y0 = getY()-2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		return coords;
	}
	
}
