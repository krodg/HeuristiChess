package heuristiChess;

public class EmptyPiece extends ChessPiece { 
	
	public EmptyPiece(int x, int y){
		super(x, y, false);  
		setOnBoard(true);
                setValue(0);
	}	  
	
}
