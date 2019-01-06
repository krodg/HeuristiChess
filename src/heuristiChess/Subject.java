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
public interface Subject {
    public void register(Observer o, ChessView v);
    public void unregister(Observer o);
    public void notifyObserver();

}
