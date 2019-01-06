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
public class ChessSubject{
    private ChessObserver observer;
    private ChessView view;
    //subject focuses on the coordinates of the last valid move update to the model
    private int x1, y1, x2, y2;
    
    
    
    public ChessSubject(ChessView v)
    {
        register();
        view = v;
    }
    
     public void register() {

        observer = new ChessObserver(this, view);
    }
     
     public void update(int fromX, int fromY, int toX, int toY)
     {
      x1 = fromX;
      y1 = fromY;
      x2 = toX;
      y2 = toY;
      push();
     }
    
    public void push()
    {
        //for all observers, push subject coordinates
        observer.update(x1,y1,x2,y2);
    }
    
    
    
}
