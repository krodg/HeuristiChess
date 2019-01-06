/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristiChess;

import java.util.*;

/**
 *
 * @author krodg
 */
public class ChessModel implements Cloneable{
        private ChessPiece [][] pieces;
        private ChessController CC;
        final private ChessSubject subject;
        
        
        //coordinates of previous move, for use with the AI
        //and observer design patter
        private int x1, y1, x2, y2;
        private int score;

        public ChessModel(ChessModel M)
        {
            subject = null;
            pieces = M.getPieces().clone();
        }
       
       public ChessModel(ChessController C)
       {
           CC = C;
            initialize();
            subject = new ChessSubject(CC.getView()); //view is only observer
            score = 0;
       }

       public void initialize()
       {
           setPieces();
       }
       public void setx1(int a)
       {
           x1 = a;
       }
       public int getx1()
       {
           return x1;
       }
       public void sety1(int a)
       {
           y1 = a;
       }
       public int gety1()
       {
           return y1;
       }
       public void setx2(int a)
       {
           x2 = a;
       }
       public int getx2()
       {
           return x2;
       }
       public void sety2(int a)
       {
           y2 = a;
       }
       public int gety2()
       {
           return y2;
       }
       public int getScore()
       {
           return score;
       }
       public void setScore(int s)
       {
           score = s;
       }

       public void copyModel(ChessModel M) throws CloneNotSupportedException
       {
           for (int i = 0; i < 8; i++)
               for (int j = 0; j < 8; j++)
                   pieces[i][j] = M.getPiece(i,j);
                   
       }
       
        public ChessModel getModel()
        {
             return this;
        }
        public ChessPiece[][] getPieces()
        {
            return pieces;
        }
        
        //initialization of piece and link to board
        public void setPiece(ChessPiece piece)
        {
             pieces[piece.getX()][piece.getY()] = piece;
        }
        
        //movement
   	public void setPiece(ChessPiece piece, int x, int y)
        {
                //check for piece in position of new move
                //if occupied, void piece
                if (!(pieces[x][y] instanceof EmptyPiece))
                {
                    //pieces[x][y].setOnBoard(false);
                    pieces[x][y] = null;
                }
                
                
                
                int fromX = piece.getX();
                int fromY = piece.getY();
                                      
                x1 = fromX;
                y1 = fromY;
                x2 = x;
                y2 = y;

		pieces[x][y] = piece.clonePiece(piece);
                pieces[x][y].setCoord(x,y);
                piece.setHasMoved(true);
                pieces[fromX][fromY] = null;
                pieces[fromX][fromY] = new EmptyPiece(fromX, fromY);
                //updateSubject();
	} 
 
        
        // Create the Chesspieces and populate the pieces array
       	public void setPieces()
	{
		pieces = new ChessPiece [8][8];
		
		for(int i = 0; i<8;i++)
			for(int c = 2; c<6;c++)
				pieces[i][c] = new EmptyPiece( i, c );
		
		pieces[4][0] = new EmptyPiece(4,0);
		pieces[4][7] = new EmptyPiece(4,7);
		
		setPiece( new Queen( true ));
		setPiece( new Queen( false ));

		setPiece( new King( true ));
		setPiece( new King( false ));

		setPiece( new Bishop( 0, true ));
		setPiece( new Bishop( 1, true ));
		setPiece( new Bishop( 0, false ));
		setPiece( new Bishop( 1, false ));

		setPiece( new Knight( 0, true ) );
		setPiece( new Knight( 1, true ) );
		setPiece( new Knight( 0, false ) );
		setPiece( new Knight( 1, false ) );

		setPiece( new Rook( 0, true ) );
		setPiece( new Rook( 1, true ) );
		setPiece( new Rook( 0, false ) );
		setPiece( new Rook( 1, false ) );

		for(int i = 0; i<8;i++)
			setPiece(new Pawn( i, true ));

		for(int i = 0; i<8;i++)
			setPiece(new Pawn( i, false ));
                
                     
                
                    
	}
       public ChessPiece getPiece(ChessPiece p)
       {
           return p;
       }

        
        public ChessPiece getPiece(int x, int y) //get piece by coordinate
        {
            return pieces[x][y];
        }
        public void getState(ChessModel state)
        {
            //sets this to a copy of current game state
            this.pieces = state.pieces;
        }
        
            //scores state based on layout of pieces
    public void scoreState()
    {
        int boardScore = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                boardScore = boardScore + pieces[i][j].getValue();
            }
        }
        score = boardScore;
    }
 
      
    public void updateSubject()
    {
       //update subject variables
        subject.update(x1, y1, x2, y2);

    }
    
 
       //used by AI to get a copy of current board state
    public ChessModel getState()
    {
        ChessModel state = new ChessModel(CC);
        for (int i = 0; i < 8; i++)   
        {  
            for (int j = 0; j < 8; j++){
                state.setPiece(this.pieces[i][j]);}
        }
        return state;
    }
       
//AI use - generates new state based on move provided
    public ChessModel generateState(int x1, int y1, int x2, int y2)
    {
        ChessModel state = new ChessModel(this);
        state.setPiece(state.getPiece(x1, y1), x2, y2);
        state.setx1(x1);
        state.sety1(y1);
        state.setx2(x2);
        state.sety2(y2);
        return state;   
    }
               
       
}
