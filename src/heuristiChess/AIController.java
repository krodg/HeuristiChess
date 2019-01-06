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
public class AIController
{
    private boolean AIcolour; //0 for white, 1 for black
    private ChessController CC;
    
    
    
    
    public AIController(ChessController controller, boolean colour)
    {
        CC = controller;
        AIcolour = colour;
    }
    
    
    public ChessController getController()
    {
        return CC;
    }
    
    public void getMove(ChessModel M)
    {

        if (CC.getTurnNum() < 3) //opening strategy
            openingMoves();
        else //regular gameplay
        {
            //ChessModel bestState;
            StateSpace startState = new StateSpace(this, CC.getModel().getState(), 4, !(getAIcolour()));
            startState.run();
            //generateMove(startState.getBest());
            //sendMove(bestState);
        }
        
    }
    
    //preprogrammed opening strategy
    public void openingMoves()
    {
           //white opening moves
        if (AIcolour == false)
        {
            if (CC.getTurnNum() == 0)
                CC.makeMove(4, 6, 4, 4);
                //white move 0
            else 
            {
                if (CC.getTurnNum() == 1)
                CC.makeMove(3, 6, 3, 4);
                //white move 1
                else if (CC.getTurnNum() == 2)
                CC.makeMove(6, 7, 5, 5);
                //white move 2
            }
        }
           //black opening moves
            else if (AIcolour == true)
            {
            if (CC.getTurnNum() == 0)
                CC.makeMove(4, 1, 4, 2);
                //black move 0
                else
                {
                if (CC.getTurnNum() == 1)
                    CC.makeMove(3, 1, 3, 3);
                //black move 1
                else if (CC.getTurnNum() == 2)
                    CC.makeMove(5, 0, 1, 4);
                //black move 2
                }
            }
                
    }
    public void setColour(boolean x)
    {
        AIcolour = x;
    }   
    
    public boolean getAIcolour()
    {
        return AIcolour;
    }
    
 
   
    public void generateMove(ChessModel bestState)
    {
        //
        int fromX = bestState.getx1();
        int fromY = bestState.gety1();
        int toX = bestState.getx2();
        int toY = bestState.gety2();
            System.out.print("AI Sending move: (" +fromX+ ", " +fromY+ ") (" +toX+ ", " +toY+ ")\n");
        
        CC. makeMove(fromX, fromY, toX, toY);
    }
    

    
    
    //for generated move
    public void sendMove(ChessModel state)
    {
        //get coordinates from model state
        int fromX = state.getx1();
        int fromY = state.gety1();
        int toX = state.getx2();
        int toY = state.gety2();
        
        //send coordinates to controller
        CC.makeMove(fromX, fromY, toX, toY);
        
    }
    
    //for opening moves
        public void sendMove(int fromX, int fromY, int toX, int toY)
    {
        //send coordinates to main controller to update model
        CC.makeMove(fromX, fromY, toX, toY);
        
    }
        
        
        
   /*
    public void AIAvailableMoves(ChessModel state, int n, boolean max)
    {
        ArrayList<ChessModel> states = new ArrayList<>();
        int nStates = 0;
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                //select moves only for colour playing
                if (!(CC.getModel().getPiece(i, j) instanceof EmptyPiece) 
                        && (((n % 2 == 0) && (CC.getModel().getPiece(i, j).getPieceColour() == !AIcolour)) 
                            ||((n % 2 == 1 ) && (CC.getModel().getPiece(i, j).getPieceColour() == AIcolour))))
                {
                    availableMoves = CC.getModel().getPiece(i, j).availableMoves(state.getPieces()); 
                    if (availableMoves.size()!=0)
                    {
			for(int z = 0; z<availableMoves.size();z++)
			{ 
				int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
				int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
                                states.add(state.generateState(i, j, x, y));
                                nStates++;
                        }
                    }
                }
            }
        }
        int average = scoreStates(states);
        ArrayList<ChessModel> prunedStates = pruneStates(states, average, max);

        for (int i = 0; i < prunedStates.size(); i++)
        {
            if (n > 0)
            {
                if (n % 2 == 0)
                {
                    AIAvailableMoves(prunedStates.get(i), n-1, !AIcolour);
                }
                if (n % 2 == 1)
                    AIAvailableMoves(prunedStates.get(i), n-1, !AIcolour);
            }
            else if (n == 0) 
                AIAvailableMoves(prunedStates.get(i), !(AIcolour));
        }
            int tempindex = 0;
            int bestScore = state.getScore();
        for (int i = 0; i < prunedStates.size(); i++)
        {
           if ((AIcolour) && (states.get(i).getScore() > bestScore) 
                       || (!AIcolour) && (states.get(i).getScore() < bestScore))
           {
                bestScore = states.get(i).getScore();
                tempindex = i;
           }
        }
        state.setScore(bestScore);
        
        //if first function call, send best state to sendMove
        if (n == 4)
            sendMove(states.get(tempindex));
            
        
        
    }
    //generates final set of moves in state space
        public void AIAvailableMoves(ChessModel state, boolean max)
    {
        ArrayList<ChessModel> states = new ArrayList<ChessModel>();
        int nStates = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (!(CC.model.getPiece(i, j) instanceof EmptyPiece))
                {
                    availableMoves = CC.model.getPiece(i, j).availableMoves(state.getPieces()); 
                    if (availableMoves.size()!=0)
                    {
			for(int z = 0; z<availableMoves.size();z++)
			{ 
				int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
				int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
                                states.add(state.generateState(i, j, x, y));
                                nStates++;
                        }
                    }
                }
            }
        }

        int average = scoreStates(states);
        ArrayList<ChessModel> prunedStates = pruneStates(states, average, max);
        
        int bestScore = state.getScore();
        for (int i = 0; i < prunedStates.size(); i++)
        {
           if ((AIcolour) && (states.get(i).getScore() > bestScore) 
                       || (!AIcolour) && (states.get(i).getScore() < bestScore))
           {
                bestScore = states.get(i).getScore();
           }
        }
        state.setScore(bestScore);
        
    }
            
    */
    
}
