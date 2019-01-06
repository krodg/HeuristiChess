/*
    Currently set to random move generation 
    from first set of branches
    To change to threaded state space search, 
    uncomment the last section of the run method,
    and remove the call to randomMove
 */
package heuristiChess;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.*;
/**
 *
 * @author krodg
 */
public class StateSpace extends Thread{
    private AIController AIC;

    private ChessModel currentState;
    private int sd;  //search depth
    private boolean max;
    
    public StateSpace(AIController AI, ChessModel state, int n, boolean maximizing)
    {
        
        AIC = AI;
        currentState = state;
        sd = n;
        max = maximizing;
    }
    
      
        public ArrayList<ChessModel> AIAvailableMoves(ChessModel state, int n, boolean max)
    {
        
        ArrayList<ChessModel> states = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {   //select moves only for current colour
                if (!(state.getPiece(i, j) instanceof EmptyPiece)) 
                {   
                    if(((n % 2) == 0 && state.getPiece(i, j).getPieceColour() == AIC.getAIcolour()) 
                            || ((n % 2) == 1  && state.getPiece(i, j).getPieceColour() == !AIC.getAIcolour()))
                    {
                        ArrayList<Coord> availableMoves = new ArrayList<>();
                         availableMoves = state.getPiece(i, j).availableMoves(state.getPieces()); 
                        if (!(availableMoves.isEmpty()))
                        {
                            for(int z = 0; z<availableMoves.size();z++)
                            { 
                                int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
                                int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
                                
                                //error check for valid coords
                                Coord tCoord = new Coord(x,y);
                                if ((!(state.getPiece(i, j) instanceof EmptyPiece)) //if not attempting to move emptypiece
                                      && (state.getPiece(i, j).availableMoves(state.getPieces()).contains(tCoord)))
                                {
                                    //valid coord, generate state
                                    ChessModel tState = state.generateState(i, j, x, y);
                                    states.add(tState);
                                    
                                    //System.out.print("State generated\n");
                                }
                                else
                                { 
                                    //System.out.print("Illegal move detected.\n");                                
                                }
                            }
                        }
                    }
                }
            }
        }
        
        scoreStates(states);
        System.out.print(states.size()+" states\n");
        ArrayList<ChessModel> prunedStates;
        prunedStates = sortList(pruneStates(states, state.getScore(), max),max);
        
        if (prunedStates.size() > 0)
        {
        //generate threads for generating state spaces from pruned statespace
            for (int i = 0; i < prunedStates.size(); i++)
            {
                //if generating leaf nodes
                if (n == 0) 
                {//non threaded procedure
                    AIAvailableMoves(prunedStates.get(i), !max);
                }
                 else //n > 1
                    {   //generate threaded statespace
                        printState(prunedStates.get(i));
                        StateSpace ss = new StateSpace(AIC, prunedStates.get(i), (sd - 1), !max);
                        threads.add(new Thread(ss));
                    
                    }
            }
            //run threads
            for (int k = 0; k < threads.size(); k++)
            {
                System.out.print("Running thread " + (k) + "    ");
                System.out.print("depth = " + n + "\n");
                threads.get(k).run();
            }
        
            try {
                join();
            } catch (InterruptedException ex) {
                Logger.getLogger(StateSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
            int bestScore = states.get(0).getScore();
        
            for (int i = 0; i < prunedStates.size(); i++)
            {
                if ((max && (states.get(i).getScore() >= bestScore)) 
                       || (!max && (states.get(i).getScore() <= bestScore)))
                {   
                    bestScore = states.get(i).getScore();
                }
            }
            state.setScore(bestScore);
                    System.out.print("parent node given score: " + bestScore + "\n");
        }
       
        //if root state, and statespace has been searched
        
        

        return prunedStates;
    }
    //generates final set of moves in state space
        public void AIAvailableMoves(ChessModel state, boolean max)
    {
        ArrayList<Coord> availableMoves = new ArrayList<>();
        ArrayList<ChessModel> states = new ArrayList<ChessModel>();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((!(state.getPiece(i, j) instanceof EmptyPiece)) && (state.getPiece(i, j).getPieceColour() == AIC.getAIcolour()))
                {
                    availableMoves = state.getPiece(i, j).availableMoves(state.getPieces()); 
                    if (!availableMoves.isEmpty())
                    {
			for(int z = 0; z<availableMoves.size();z++)
			{ 
                            int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
                            int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
                                        //verify  is legal move - not empty, contained in availableMoves, and the correct colour
                            Coord tCoord = new Coord(x,y);
                            if ((!(state.getPiece(i, j) instanceof EmptyPiece)) //if not attempting to move emptypiece
                                    && ((state.getPiece(i, j).availableMoves(state.getPieces()).contains(tCoord)) 
                                        && (state.getPiece(i, j).getPieceColour() == AIC.getAIcolour())))
                                    {
                                       ChessModel tState = state.generateState(i,j,x,y);
                                    states.add(tState);
                                    //System.out.print("Leaf state generated\n");
                                    }
                            else
                            {
                                //System.out.print("Illegal move detected.\n");                                
                            }
                        }
                    }
                }
            }
        }
        System.out.print(states.size()+" states\n");
        //int r = (int)(Math.random() * states.size());
       // System.out.print("Sending state " + r + "\n");
        //AIC.generateMove(states.get(r));
        
        
        scoreStates(states);
        ArrayList<ChessModel> prunedStates = new ArrayList<>();
        prunedStates = sortList(pruneStates(states, state.getScore(), max), max);
        
        if (!prunedStates.isEmpty())
            {
            int bestScore = prunedStates.get(0).getScore();
            //int tempindex = 0;
            for (int i = 0; i < prunedStates.size(); i++)
            {
                if ((max && (prunedStates.get(i).getScore() >= bestScore)) 
                       || ((!max) && (prunedStates.get(i).getScore() <= bestScore)))
                {
                    bestScore = prunedStates.get(i).getScore();
                    //System.out.print("Bestscore set: " + bestScore + "\n");
                    //tempindex = i;
                }
            }

            state.setScore(bestScore);
            System.out.print("parent node given score: " + bestScore + "\n");
        }
    }

    public ArrayList<ChessModel> pruneStates(ArrayList<ChessModel> states, int s, boolean max)
    {
        ArrayList<ChessModel> prunedList = new ArrayList<>();
        for (int i = 0; i < states.size(); i++)
        {
            if ((max) && (states.get(i).getScore() >= (s)) 
                       || ((!max) && (states.get(i).getScore() <= (s))))
            {
                prunedList.add(states.get(i)); 
            }
        }
        //if (prunedList.size() == 0)
        //{
         //   prunedList.add(states.get((int)Math.random() * prunedList.size()));
        //}
        
        System.out.print("State space pruned\n");
        System.out.print(prunedList.size() + " states remain\n");
        return prunedList;
    }
    //sorts list by board score
    public ArrayList<ChessModel> sortList(ArrayList<ChessModel> states, boolean max)
    {   
        ArrayList<ChessModel> sortedList = new ArrayList<ChessModel>();
        
        int z = states.size();
        
        if (4 < z)
        {
            z = 4;
        }
        
        //for 4 or less states       
        for (int i = 0; i < z; i++)
        {
            int bestScore = states.get(0).getScore();
            int tempindex = 0;
           for (int j = 1; j < states.size(); j++)
           {
               if ((max && (states.get(j).getScore() > bestScore))//if player black - maximize score 
                       || ((!max) && (states.get(j).getScore() < bestScore)))
                       {
                           bestScore = states.get(j).getScore();
                           tempindex = j;
                       }
               
           }
           sortedList.add(states.get(tempindex).getModel());
        }
        return sortedList;
    }
   
    public void scoreStates(ArrayList<ChessModel> states)
    {
        for (int i = 0; i < states.size(); i++)
        {
            states.get(i).scoreState();
            //System.out.print(states.get(i).getScore() + " board score\n");
        }
    }
    
    //selects best scored child state of root, error checks to make sure it is a legal available move for a piece
    public void selectBest(ArrayList<ChessModel> states)
    {
        int bestScore = states.get(0).getScore();
        int tempindex = 0;
        for (int i = 0; i < states.size(); i++)
        {
            if ((!AIC.getAIcolour() && (states.get(i).getScore() >= bestScore))//if player white - maximize score 
                       || ((AIC.getAIcolour()) && (states.get(i).getScore() <= bestScore))) //player black, minimize score
           {
                bestScore = states.get(i).getScore();
                tempindex = i;
           }
        }
        
        //set bestState
        ChessModel bestState = new ChessModel(AIC.getController());
        bestState = states.get(tempindex);
        System.out.print("Best state selected: index = " + tempindex + "\n");
        int fromx = states.get(tempindex).getx1();
        int fromy = states.get(tempindex).gety1();
        int tox = states.get(tempindex).getx2();
        int toy = states.get(tempindex).gety2();
        bestState.setx1(fromx);
        bestState.sety1(fromy);
        bestState.setx2(tox);
        bestState.sety2(toy);
        
        
        System.out.print("x1: " + fromx + "\ny1: " + fromy);
        System.out.print("\nx2: " + tox + "\ny2: " + toy + "\n");
        //send state to AI controller to get coordinates
        AIC.generateMove(bestState);

    }
    
    public void printState(ChessModel state)
    {
        int fromx = state.getx1();
        int fromy = state.gety1();
        int tox = state.getx2();
        int toy = state.gety2();
        
        
        System.out.print("( " + fromx + ", " + fromy + " ) ");
        System.out.print("( " + tox + ", " + toy + " )\n");
    }
    
    public void randomMove(ChessModel state)
    {
        
        ArrayList<ChessModel> states = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {   //select moves only for current colour
                if ((!(state.getPiece(i, j) instanceof EmptyPiece))
                    && (state.getPiece(i, j).getPieceColour() == AIC.getAIcolour()))
                    {
                        //ArrayList<Coord> availableMoves = new ArrayList<>();
                        ArrayList<Coord> availableMoves = state.getPiece(i, j).availableMoves(state.getPieces()); 
                        if (!(availableMoves.isEmpty()))
                        {
                            for(int z = 0; z<availableMoves.size();z++)
                            { 
                                int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
                                int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
                                
                                //error check for valid coords
                                Coord tCoord = new Coord(x,y);
                                if ((!(state.getPiece(i, j) instanceof EmptyPiece)) //if not attempting to move emptypiece
                                      && (state.getPiece(i, j).availableMoves(state.getPieces()).contains(tCoord)))
                                {
                                    //valid coord, generate state
                                    ChessModel tState = state.generateState(i, j, x, y);
                                    states.add(tState);
                                    
                                    //System.out.print("State generated\n");
                                }
                                else
                                { 
                                    //System.out.print("Illegal move detected.\n");                                
                                }
                            }
                        }
                    }
                }
        }
        int r = (int)(Math.random() * (states.size() - 1));
        AIC.generateMove(states.get(r));
    }
   
    
    @Override
    public void run() {
        //generate random move from first branches
        randomMove(currentState);

      /* - enable for statespace search
        //ArrayList<ChessModel> prunedStates = new ArrayList<>();
        prunedStates = AIAvailableMoves(currentState, sd, max); //To change body of generated methods, choose Tools | Templates.
        
        //if root state    
        if (sd == 4)
        {
            //send best state to AI controller
            //System.out.print("Best state: "+tempindex+" \n");
            selectBest(prunedStates);
        }
        */
    }   
}
