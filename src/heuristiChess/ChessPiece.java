package heuristiChess;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessPiece implements Cloneable{ 
	private Coord coord;
	private boolean colour;
	private boolean onBoard;
	private boolean hasMoved;
        private boolean isNull;
        private int value; //determined by piece type and colour
        private int mobilityValue;

	public ChessPiece(int x, int y, boolean colour)
	{
		setColour(colour);
		coord = new Coord(0,0);
		hasMoved = false;
		setCoord(x,y);
                setValue(0);
	}
        
        public ChessPiece(int x, int y)
	{
		//setColour(getPieceColour());
		coord = new Coord(0,0);
		
		setCoord(x,y); 
	}
        
        public ChessPiece clonePiece(ChessPiece P)
        {
            try {
                ChessPiece copy = new ChessPiece(P.getX(), P.getY(), P.getPieceColour());
                copy = (ChessPiece)P.clone();
                            return copy;
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ChessPiece.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        public ChessPiece getPiece()
        {
            return this;
        }
	
	public void setX(int x1)
	{
		coord.setX(x1);
	}
	
	public int getX()
	{
		return coord.getX();
	}
	
	public void setY(int y1)
	{
		coord.setY(y1);
	}
	
	public int getY()
	{
		return coord.getY();
	}
	
	public void setCoord(int x1 ,int y1)
	{
		setX(x1);
		setY(y1);
	}

	public void setCoord(Coord enteredCoord) 
	{

		setX(enteredCoord.getX());
		setY(enteredCoord.getY());
		
	}
	public Coord getCoord()
	{
		return coord;
	}
	
	public void setColour(boolean c)
	{
		colour = c;
	}
	   
	public boolean getPieceColour()
	{
		return colour;
	}
	
	public void move(char x1, int y1)
	{
                
		coord=new Coord(x1,y1);
         
	}
	
	public void offBoard()
	{
		onBoard = false;
	}
	public boolean getOnBoard()
	{
		return onBoard;
	}
        
        public void setOnBoard(boolean x)
        {
            onBoard = x;
        }
        
        public boolean getHasMoved()
	{
		return hasMoved;
	}
        public void setHasMoved(boolean x)
	{
		hasMoved = x;
	}
        
        public int getValue()
        {
            return value;
        }
        public void setValue(int x)
        {   if (getPieceColour() == false) //white piece - positive value
            value = x;
        else //black piece - negative value
                value = -(x);
        }
	
	public String toString()
	{
		return "position is "+getX()+getY();
	}
	
	public boolean outOfRange(int x0, int y0)
	{
		boolean range = false;
		if(x0<0||x0>7)
		{
			//System.out.println("x out of bounds");
			range = true;
		}
		else if(y0<0||y0>7)
		{
			//System.out.println("y out of bounds");
			range = true;
		}		
		return range;
	}		
	
	// return true if the piece given is an enemy piece

	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ChessPiece))
			return false;
		
		ChessPiece piece = (ChessPiece) obj; 
		
		if(coord.getX() == piece.getX() && coord.getY() == piece.getY())
			return true;
		
		return false;	
	}
	public boolean enemyHere(ChessPiece piece)
	{
		if( piece == null || piece instanceof EmptyPiece )
			return false;
		
		boolean isEnemy;
		
		if( (getPieceColour() != piece.getPieceColour()) )
			isEnemy = true;
		else
			isEnemy = false;
		
		return isEnemy;

	}
	// return true if the coordinate given contains a piece that is not empty
	public boolean unitHere(ChessPiece pieces[][],int x0,int y0)
	{
		//System.out.println(x0+", "+y0);
		boolean unit = true;
		if(pieces[x0][y0] instanceof EmptyPiece)
			unit = false;
			//System.out.println("moveadded: "+x0+", "+y0);
		return unit;	
	}
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{ 
		ArrayList<Coord> coords= new ArrayList<Coord>(); 
		return coords;
	}
}
