package heuristiChess;

import java.util.ArrayList;

public class King extends ChessPiece implements ChessPieceMovement{



	public King(boolean isBlack)
	{
		super(4, 0, isBlack); 
		setValue(900);
		if(isBlack) 
			setCoord(4,0); 
		else 
			setCoord(4,7); 
		
		setOnBoard(true); 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY()+1;
		ArrayList<Coord> coords= new ArrayList<Coord>();
		
		// Down
		y0 = getY()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up
		y0 = getY()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}		
		
		// Right
		x0 = getX()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}			
		
		// Left
		x0 = getX()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down Right
		x0 = getX()+1;
		y0 = getY()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down Left
		x0 = getX()-1;
		y0 = getY()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up Right
		x0 = getX()+1;
		y0 = getY()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		//Up Left
		x0 = getX()-1;
		y0 = getY()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
                
                //castling - added by Kellen Rodgers
                //white king
                //if king and castle-side rook hasnt moved,
                //and knight + bishop squares are empty -
                //then add castling coordinate
                if((!getPieceColour()) && !(getHasMoved()) 
                    && (!pieces[7][7].getHasMoved()) && (pieces[7][5] instanceof EmptyPiece)
                       && (pieces[7][6] instanceof EmptyPiece))
                {
                    coords.add(new Coord(0, 1));
                }
                if((!getPieceColour()) && !(getHasMoved())
                    && (!pieces[7][0].getHasMoved()) && (pieces[7][1] instanceof EmptyPiece)
                       && (pieces[7][2] instanceof EmptyPiece) && (pieces[7][3] instanceof EmptyPiece))
                {
                    //display white right castle
                    coords.add(new Coord(6, 0));
                }
                //black king
                if((getPieceColour()) && !(getHasMoved()) 
                    && (!pieces[0][7].getHasMoved()) && (pieces[0][5] instanceof EmptyPiece)
                       && (pieces[0][6] instanceof EmptyPiece))
                {
                coords.add(new Coord(0,6));
                }
                if(getPieceColour() && !(getHasMoved()) 
                && (!pieces[0][0].getHasMoved()) && (pieces[0][1] instanceof EmptyPiece)
                       && (pieces[0][2] instanceof EmptyPiece) && (pieces[0][3] instanceof EmptyPiece))
                {
                    coords.add(new Coord(0,1));
                }
           
		return coords;
            
	}
	
}
