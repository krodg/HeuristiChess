/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristiChess;


/**
 *
 * @author krodg
 */
public class ChessController {
    private AIController ai;
    private ChessView view;
    private ChessModel model;
    
    private boolean playerColour;
            
    private boolean turnColour;
    
    private String WHITE_TURN_MESSAGE = "White's Turn!";
    private String BLACK_TURN_MESSAGE = "Black's Turn!";
    private String WHITE_WIN_MESSAGE = "White wins!";
    private String BLACK_WIN_MESSAGE = "Black wins!";
    private String gameMessage;
    
    private int turnNumber;
    
    private Coord fromCoord, enteredCoord;  
    boolean game;
    
   public ChessController()
   {
       initialize();
   }
    public void initialize()
    {
       
       initializeView();
       this.getView().inputPlayerColour();
       
       initializeModel();
       
       initializeAI();
       
       game = true;  
       turnColour = false;
       turnNumber = 0;
       //if player is black (1) , get AI move first for white (0)
       if (playerColour)
       {
           ai.getMove(model);
       }
       
   }
   
  
   
    public ChessView getView()
    {
        return view;
    }
    public ChessModel getModel()
    {
        return model;
    }
    public void initializeView()
    {
        view = new ChessView(this);
    }
     
    public void initializeModel()
    {
     
        model = new ChessModel(this);
        
    }
    
    public void initializeAI()
    {
    
        ai = new AIController(this, !(getPlayerColour()));
        
        
    }
    
    public boolean getPlayerColour()
    {
        return playerColour;
    }
    public void setPlayerColour(boolean colour)
    {
        playerColour = colour;
    }
    

           //input from player entered coords
    public void makeMove()
    {	    
      
       
        model.setPiece(model.getPiece(fromCoord.getX(), fromCoord.getY()), enteredCoord.getX(), enteredCoord.getY());
        //update model subject
      
        //model.updateSubject(fromCoord.getX(), fromCoord.getY(), enteredCoord.getX(), enteredCoord.getY());
            
        //toggle turn

        toggleTurn();
	
    }
    
    //input from AI
    public void makeMove(int x1, int y1, int x2, int y2)
    {
                
        //if king, is being captured
         if(model.getPiece(x2, y2) instanceof King 
                    && (model.getPiece(x2, y2).getPieceColour()
                        != model.getPiece(x1, y1).getPieceColour()))
        {
            model.setPiece(model.getPiece(x1, y1), x2, y2);
            view.update(x1,y1,x2,y2);
            CheckMate();
        }
         //update model
        model.setPiece(model.getPiece(x1, y1), x2, y2);
        //update view
        view.update(x1,y1,x2,y2); //temporary fix to update view

        
        toggleTurn();
        
        
    }
           
         

    public int getTurnNum()
    {
        return turnNumber;
    }
       
    public void toggleTurn()
    {
        if (turnColour == true)
            turnNumber++;
        
        turnColour = !turnColour;
        
        //if AI's move
          if (ai.getAIcolour() == turnColour)
          {
            ai.getMove(model);
          }
            

    }

    
    public void Check()
    {
        //if a piece can attack king
            //display to view colour of king in check
                //view check king-incheck
        
                  //if next move can't save king
                        //CheckMate();
    }
    
    
    //Checkmate
    public void CheckMate()
    {
        //if king is within move of opposite colour piece
        view.displayWinner(turnColour);
        game = false;
    }

    
    
    //called after player acknowledges message on view
    public void close()
    {
        System.exit(0);
    }

}
