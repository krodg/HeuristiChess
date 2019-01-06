package heuristiChess;

import java.util.ArrayList;

public class Rook extends ChessPiece implements ChessPieceMovement
{

	public Rook(int count, boolean isBlack)
	{
		super(count*7, 7, isBlack); 
		setValue(50);
		if(isBlack) 
			setCoord(count*7,0); 
		else 
			setCoord(count*7,7);
		
		//System.out.println(getCoord().toString() + " is "+ isBlack);
		
		setOnBoard(true); 
	}	

	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();

		// Up
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

		// Down
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
		return coords;
	}
	
}
