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
public class ChessObserver implements Observer{
   private ChessSubject S;
   
   private int x1, y1, x2, y2;
   
   private ChessView view;
    
    
    public ChessObserver(ChessSubject CS, ChessView v)
    {
        S = CS;
        view = v;
    }

    public ChessView getView()
    {
        return view;
    }
    
    public void update(int fromX, int fromY, int toX, int toY)
    {
           //update observed variables
           x1 = fromX;
           y1 = fromY;
           x2 = toX;
           y2 = toY;
           //push update to be displayed to view
           pushToView();
        
    }
    public void pushToView()
    {
        //getView().update(x1, y1, x2, y2);
    }
    
    
}
