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
public class Player {
    boolean colour; //0 for white, 1 for black
    
    
    
    public void setColour(boolean x)
    {
        colour = x;
    }
    public boolean getPlayerColour()
    {
        return colour;
    }
}
