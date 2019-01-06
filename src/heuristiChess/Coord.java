package heuristiChess;

import javax.swing.JButton;

public class Coord 
{
	private int x;
	private int y;
	
	public Coord(int x1, int y1)
	{
		setX(x1);
		setY(y1);
	}
	
	public Coord(JButton jb ) { 
		if(jb.getName() != null) {
			
			if(jb.getName().length()>1)
			{
				setX(Character.getNumericValue(jb.getName().charAt(0)));
				setY(Character.getNumericValue(jb.getName().charAt(1)));
			} 
			else
			{
				setY(Character.getNumericValue(jb.getName().charAt(0)));
			} 
		}
		
	}
	
	public void setX(int x1)
	{
		x=x1;
	}
	
	public int getX()
	{
		return x; 
	}
	
	public void setY(int y1)
	{
		y=y1;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String toString()
	{
		return (getX()+""+getY());
	}

    @Override
    public boolean equals(Object obj) 
	{
        if (!(obj instanceof Coord))
        	return false;
        
        Coord compCoord = (Coord) obj;
        
		if(compCoord.getX() == x && compCoord.getY() == y)
			return true;
		
		return false;
	}
}
