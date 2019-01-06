//This application is the work of Austin Philp - https://github.com/austinphilp/JChess

package heuristiChess;

import java.awt.BorderLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class Chess extends JFrame
{
	
        private ChessController CC;
        private JToolBar tools;
                
    
	public Chess(String title)
	{
		super(title);
		initialize();
	}
	public void initialize()
	{
                CC = new ChessController();
		
		//Create and populate the toolbar
	    //tools = new JToolBar(); 

        //tools.setFloatable(false);
        //gui.add(tools, BorderLayout.PAGE_START);
	     
        //JButton newButton = createResetButton();
       // JButton saveButton = createSaveButton();
       // JButton loadButton = createLoadButton();
	      
		//tools.add(newButton);
		//tools.addSeparator();
		//tools.add(saveButton);
		//tools.add(loadButton);
		//tools.addSeparator();
		//tools.add(board.turnMessage);
		
		
		
	}
	private static final long serialVersionUID = -5185475584729272657L;
/*
	//Create the button that calls the ChessBoards saveGame() function
	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");  

	    saveButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      { 
	        board.saveGame(); 
	        repaint();
	      }
	    });
	    return saveButton;
	}

	//Create the button that calls the ChessBoards reset() function
	private JButton createResetButton() {
		JButton newButton = new JButton("New");  
	    
	    newButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      { 
	        board.reset(); 
	        repaint();
	      }
	    });
	    return newButton;
	}
	
	//Create the button that calls the ChessBoards loadGame() function
	private JButton createLoadButton() {
		JButton loadButton = new JButton("Load"); 

	    loadButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      { 
	        board.loadGame(); 
	        repaint();
	      }
	    });
	    return loadButton;
	}
   */
	
	public static void main(String args[] ) 
	{
		
		Runnable r = new Runnable() {

            @Override
            public void run() {
            	Chess chess = new Chess("Chess");
        		chess.setDefaultCloseOperation(EXIT_ON_CLOSE); 
            }
        };
        SwingUtilities.invokeLater(r);
	}
//=============================================================================================================================
}
