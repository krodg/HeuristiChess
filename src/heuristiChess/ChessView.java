/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristiChess;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.GridLayout; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author krodg
 */
public class ChessView extends JPanel{
    
    private static final long serialVersionUID = 1L;
	
	private ImageIcon blankIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	
	
        private ImageIcon B_ROOK_ICON 	= new ImageIcon("blackrook.png");
	private ImageIcon W_ROOK_ICON 	= new ImageIcon("whiterook.png");
	private ImageIcon B_KNIGHT_ICON = new ImageIcon("blackknight.png");
	private ImageIcon W_KNIGHT_ICON = new ImageIcon("whiteknight.png");
	private ImageIcon B_BISHOP_ICON = new ImageIcon("blackbishop.png");
	private ImageIcon W_BISHOP_ICON = new ImageIcon("whitebishop.png");
	private ImageIcon B_KING_ICON 	= new ImageIcon("blackking.png");
	private ImageIcon W_KING_ICON 	= new ImageIcon("whiteking.png");
	private ImageIcon B_QUEEN_ICON 	= new ImageIcon("blackqueen.png");
	private ImageIcon W_QUEEN_ICON 	= new ImageIcon("whitequeen.png");
	private ImageIcon B_PAWN_ICON 	= new ImageIcon("blackpawn.png");
	private ImageIcon W_PAWN_ICON 	= new ImageIcon("whitepawn.png"); 

        private String WHITE_TURN_MESSAGE = "White's Turn!";
        private String BLACK_TURN_MESSAGE = "Brown's Turn!";
        //private JLabel turnMessage = new JLabel(WHITE_TURN_MESSAGE); 

        private Color TILE_COLOR_DARK 	= Color.getHSBColor(0.10f, 0.9f, 0.6f);
        private Color TILE_COLOR_LIGHT 	= Color.getHSBColor(0.10f, 0.2f, 0.99f); 
        private Color TILE_COLOR_HOVER 	= Color.GRAY;
        //private Color TILE_INCHECK      = Color.RED; did not get sufficient time to 
                                                      //develop check logic condition logic
    
        private Color AVAILABLE_MOVE_COLOR_DARK  = new Color(200, 200, 0); 
        private Color AVAILABLE_MOVE_COLOR_LIGHT = new Color(255, 255, 155);
     
	private ArrayList<Coord> availableMoves;  
	private Coord fromCoord, enteredCoord;  
	private boolean pressed = false;
        
        private JLabel gameMessage = new JLabel(WHITE_TURN_MESSAGE); 
        
        private JFrame gui = new JFrame("HeuristiChess");
        final JPanel board = new JPanel(new BorderLayout(3, 3)); 
        
        private ChessController controller;
        
        
        private boolean playerColour;
    
        private JButton [][] buttons 	= new JButton [8][8];
        
        public ChessView(ChessController CC)
	{
            controller = CC;
            initialize();   
            
        }
            public void initialize()
        {
            board.setLayout(new GridLayout(0,8));
            
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.add(board);
        
            //tools.add(gameMessage); - no toolbar
            //  super(new GridLayout(0,8));	
            // Create the buttons and add the appropriate icons
            setButtons();
            // Add handlers for dealing with mouse actions for playing the game
            setMouseActions();
    	// Add the JButtons to the ChessBoard Panel;
            for(int i = 0; i<8;i++)
			for(int c = 0; c<8;c++)
			{ 
				board.add(buttons[c][i]); 
			}
                //setIcons(); - called in setbuttons
            
                gui.validate();
		gui.setLocationByPlatform(true);
                gui.pack();
		gui.setMinimumSize(getSize());
		gui.setVisible(true);
                
                
	} 
        
        
        //update state based on last move - swap icons
        //incoming from observer
        public void update(int x1, int y1, int x2, int y2)
        {
            buttons[x2][y2].setIcon(blankIcon);
            buttons[x2][y2].setIcon(buttons[x1][y1].getIcon());   
            buttons[x1][y1].setIcon(blankIcon);
            board.repaint();
        }
	// add mouse action handlers for when a mouse presses on, leaves, enteres, and releases on a button
	private void setMouseActions()
	{
		for(int i = 0; i<8;i++)
        	for(int c = 0; c<8; c++)
        	{
        		buttons[i][c].addMouseListener(new MouseAdapter()
        		{ 
        			public void mousePressed(MouseEvent evt)
        			{  
        				// If the the user is not already in the middle of a click, record the coordinates of the click
        				if(!pressed)
        				{
        					fromCoord = new Coord((JButton) evt.getSource()); 
							enteredCoord = fromCoord; 
							// if it is the turn of the piece that was clicked on 
        					if(controller.getModel().getPiece(fromCoord.getX(),fromCoord.getY()).getPieceColour() == playerColour)// || ((!pieces[fromCoord.getX()][fromCoord.getY()].getPieceColour()) && (!blackTurn))) 
        					{
        						//it the piece isn't empty
								if( !(controller.getModel().getPiece(fromCoord.getX(),fromCoord.getY()) instanceof EmptyPiece) )
								{
		        					showAvailableMoves();
									pressed = true;
	        					}
        					}
						}    					
        			}
        			// if the mouse is pressed, record it, and change the color of the tile
        			public void mouseEntered(MouseEvent evt)
        			{  
        				if(pressed)
        				{    
                                            enteredCoord = new Coord((JButton) evt.getSource());   
                                  
							
                                            buttons[enteredCoord.getX()][enteredCoord.getY()].setBackground(TILE_COLOR_HOVER); 
          
    					} 
        			}
        			// 
        			public void mouseReleased(MouseEvent e)
        			{
        				if(pressed)
        				{  
        					//if the button we're on is empty or occupied by enemy
	        				if((controller.getModel().getPiece(enteredCoord.getX(),enteredCoord.getY()) instanceof EmptyPiece) 
                                                        || ((controller.getModel().getPiece(enteredCoord.getX(),enteredCoord.getY()).getPieceColour()) 
                                                        != (controller.getModel().getPiece(fromCoord.getX(), fromCoord.getY()).getPieceColour())))
	        					// if this is a legal move make it
	        					if(availableMoves.contains(enteredCoord))  
	        						controller.makeMove(fromCoord.getX(), fromCoord.getY(), enteredCoord.getX(), enteredCoord.getY());   
	        					//otherwise reset the color of the hovered tile
	        					else
	        						chooseColor(enteredCoord, false); 
	        				// empty out the available moves list and change the colors back
	    					while(!availableMoves.isEmpty())
	    					{
	        					int x = Character.getNumericValue(availableMoves.get(0).toString().charAt(0));
								int y = Character.getNumericValue(availableMoves.get(0).toString().charAt(1)); 
								chooseColor( x, y, false );
								availableMoves.remove(0);
							}     
	        			} 		 
        				pressed = false; 
        			}
        			//====================================
        			public void mouseExited(MouseEvent evt)
        			{
        				//if you're making the tiles gray, make sure to set them back to their original color
    					if(pressed)
    					{  
							chooseColor(enteredCoord, availableMoves.contains(enteredCoord));  
						}
					}
				});
        	}
	} 
        
        public void showAvailableMoves()
	{
		availableMoves = new ArrayList<>();
                availableMoves = controller.getModel().getPiece(fromCoord.getX(), fromCoord.getY()).availableMoves(controller.getModel().getPieces()); 
		if (!availableMoves.isEmpty())
		{
			for(int z = 0; z<availableMoves.size();z++)
			{ 
				int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
				int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
				chooseColor(x, y, true); 
				board.repaint();
			}
		}
	}
        /*
        public void setObserver(ChessObserver obs)
        {
            observer = obs;
        }
	*/
    	public void setButtons()
	{
		for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
            {
            	int name = i*10+j;
            	buttons[i][j] = new JButton();
            	buttons[i][j].setOpaque(true);
            	buttons[i][j].setBorderPainted(false);
            	buttons[i][j].setName(Integer.toString(name));
            }
		setTileColors();
		setIcons();
	}
    
    	// iterates through the whole board and sets the tile color for each square
	public void setTileColors()
	{
		
            for(int i = 0; i < 8; i++) 
            {  
                for(int j = 0; j < 8; j++)
                {  
                    chooseColor(i,j,false);
                }
		
            }
	}
        
        public void inputPlayerColour()
        {
            JPanel colourSelect = new JPanel(new GridLayout(0,1));
            String[] items = {"White", "Black"};
            JComboBox<String> colour = new JComboBox<>(items);
            
            colourSelect.add(colour);
            
            int result = JOptionPane.showConfirmDialog(null, colourSelect, "Select player colour",
             JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (colour.getSelectedItem() == "White")
                {
                    playerColour = false;
                    controller.setPlayerColour(false);
                }
                else if (colour.getSelectedItem() == "Black")
                {
                    playerColour = true;
                    controller.setPlayerColour(true);
                }
            }
            
            
            
        }
        
    public ChessController getController()
    {
        return controller;
    }
        
        public void chooseColor(Coord coord, boolean availMove)
	{
		int x = coord.getX();
		int y = coord.getY();

    	buttons[x][y].setOpaque(true);
    	buttons[x][y].setBorderPainted(false);
    	
		if((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1))
		{   
			if(availMove) 
				buttons[x][y].setBackground(AVAILABLE_MOVE_COLOR_LIGHT);
			else
				buttons[x][y].setBackground(TILE_COLOR_LIGHT);
		}
		else
		{   
			if(availMove) 
				buttons[x][y].setBackground(AVAILABLE_MOVE_COLOR_DARK);
			else 
				buttons[x][y].setBackground(TILE_COLOR_DARK);
		}
        }
        
        	//overloaded method that takes x and y instead of a Coord
	public void chooseColor(int x, int y, boolean availMove) {
		chooseColor(new Coord(x,y), availMove);
	}
    
    	public void setIcons() //initialize board
	{
		
		buttons[0][0].setIcon(B_ROOK_ICON);
		buttons[1][0].setIcon(B_KNIGHT_ICON);
		buttons[2][0].setIcon(B_BISHOP_ICON);
		buttons[3][0].setIcon(B_QUEEN_ICON);
		buttons[4][0].setIcon(B_KING_ICON);
		buttons[5][0].setIcon(B_BISHOP_ICON);
		buttons[6][0].setIcon(B_KNIGHT_ICON);
		buttons[7][0].setIcon(B_ROOK_ICON);
		
		for(int i = 0; i < 8; ++i) {
			buttons[i][1].setIcon(B_PAWN_ICON);
		}

		for(int i = 0; i < 8; ++i) {
			buttons[i][6].setIcon(W_PAWN_ICON);
		}
		 
        buttons[0][7].setIcon(W_ROOK_ICON);
        buttons[1][7].setIcon(W_KNIGHT_ICON);
        buttons[2][7].setIcon(W_BISHOP_ICON);
        buttons[3][7].setIcon(W_QUEEN_ICON);
        buttons[4][7].setIcon(W_KING_ICON);

        buttons[5][7].setIcon(W_BISHOP_ICON);
        buttons[6][7].setIcon(W_KNIGHT_ICON);
        buttons[7][7].setIcon(W_ROOK_ICON);
        
	}
/*
        public void displayCheck(int x, int y)
        {
            
            buttons[x][y].setBackground(TILE_INCHECK);
        }
    insufficient time to implement logic
*/
        public void displayWinner(boolean x)
        {
            JPanel winner = new JPanel(new GridLayout(0,1));
            int result = 1;
            if (x == false)
            {
                result = JOptionPane.showConfirmDialog(null, winner, "White wins",
                    JOptionPane.PLAIN_MESSAGE);
            
            }
            else if(x == true)
            {
                result = JOptionPane.showConfirmDialog(null, winner, "Black wins",
                    JOptionPane.PLAIN_MESSAGE);
               
            }
            
            if (result == JOptionPane.OK_OPTION)
                    {
                        controller.close();
                    }
        }
        

}
