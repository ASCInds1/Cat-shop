package clients.backDoor;

import middle.MiddleFactory;
import middle.StockReadWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class BackDoorView implements Observer
{
  private static final String RESTOCK  = "Add";
  private static final String CLEAR    = "Clear";
  private static final String QUERY    = "Query";
 
  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final JLabel      theAction  = new JLabel();
  private final JTextField  theInput   = new JTextField();
  private final JTextField  theInputNo = new JTextField();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtClear = new JButton( CLEAR );
  private final JButton     theBtRStock = new JButton( RESTOCK );
  private final JButton     theBtQuery = new JButton( QUERY );
  
  private StockReadWriter theStock     = null;
  private BackDoorController cont= null;

  private static final Color BUTTON_COLOR = new Color(0, 102, 204); // Dark Blue
  private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
  private static final Color MESSAGE_AREA_COLOR = new Color(34, 49, 63); // Dark Blue-Grey
  private static final Color MESSAGE_TEXT_COLOR = Color.WHITE;
  private static final Color INPUT_AREA_COLOR = new Color(149, 165, 166); // Light Grey

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  public BackDoorView(  RootPaneContainer rpc, MiddleFactory mf, int x, int y )
  {
    try                                             // 
    {      
      theStock = mf.makeStockReadWriter();          // Database access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );
    
    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

    theBtQuery.setBounds( 16, 25+60*0, 80, 40 );    // Buy button 
    theBtQuery.addActionListener(                   // Call back code
      e -> cont.doQuery( theInput.getText() ) );
    cp.add( theBtQuery );                           //  Add to canvas

    theBtRStock.setBounds( 16, 25+60*1, 80, 40 );   // Check Button
    theBtRStock.addActionListener(                  // Call back code
      e -> cont.doRStock( theInput.getText(),
                          theInputNo.getText() ) );
    cp.add( theBtRStock );                          //  Add to canvas

    theBtClear.setBounds( 16, 25+60*2, 80, 40 );    // Buy button 
    theBtClear.addActionListener(                   // Call back code
      e -> cont.doClear() );
    cp.add( theBtClear );                           //  Add to canvas

 
    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank
    cp.add( theAction );                            //  Add to canvas

    theInput.setBounds( 110, 50, 120, 40 );         // Input Area
    theInput.setText("");                           // Blank
    cp.add( theInput );                             //  Add to canvas
    
    theInputNo.setBounds( 260, 50, 120, 40 );       // Input Area
    theInputNo.setText("0");                        // 0
    cp.add( theInputNo );                           //  Add to canvas

    theSP.setBounds( 110, 100, 270, 160 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font  
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    theInput.requestFocus();                        // Focus is here

    theBtQuery.setBackground(Color.BLUE);
    theBtRStock.setBackground(Color.GREEN);
    theBtClear.setBackground(Color.RED);

    // Setting text color for buttons
    theBtQuery.setForeground(Color.WHITE);
    theBtRStock.setForeground(Color.WHITE);
    theBtClear.setForeground(Color.WHITE);

    // Setting background color for message area and input area
    theAction.setOpaque(true);
    theAction.setBackground(Color.DARK_GRAY);
    theAction.setForeground(Color.WHITE);

    theInput.setBackground(Color.LIGHT_GRAY);
    theInputNo.setBackground(Color.LIGHT_GRAY);

    // Setting background color for scrolling pane and text area
    theSP.setBackground(Color.WHITE);
    theOutput.setBackground(Color.WHITE);

    // ... (remaining code)

    initializeComponents(rpc, mf, x, y);
    configureUIStyling(); // Apply the cool UI styling



  }

  private void initializeComponents(RootPaneContainer rpc, MiddleFactory mf, int x, int y) {
  }

  private void configureUIStyling() {
    theBtQuery.setBackground(BUTTON_COLOR);
    theBtRStock.setBackground(BUTTON_COLOR);
    theBtClear.setBackground(BUTTON_COLOR);

    theBtQuery.setForeground(BUTTON_TEXT_COLOR);
    theBtRStock.setForeground(BUTTON_TEXT_COLOR);
    theBtClear.setForeground(BUTTON_TEXT_COLOR);

    theAction.setOpaque(true);
    theAction.setBackground(MESSAGE_AREA_COLOR);
    theAction.setForeground(MESSAGE_TEXT_COLOR);

    theInput.setBackground(INPUT_AREA_COLOR);
    theInputNo.setBackground(INPUT_AREA_COLOR);

    // ... (other color and styling configurations)
  }


  public void setController( BackDoorController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
    BackDoorModel model  = (BackDoorModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
    
    theOutput.setText( model.getBasket().getDetails() );
    theInput.requestFocus();
  }

}