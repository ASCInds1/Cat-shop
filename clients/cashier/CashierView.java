package clients.cashier;

import catalogue.Basket;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockReadWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


/**
 * View of the model
 * @author  M A Smith (c) June 2014  
 */
public class CashierView implements Observer
{
  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private static final String CHECK  = "Check";
  private static final String BUY    = "Buy";
  private static final String BOUGHT = "Bought";

  private static final String REMOVE = "Remove";


  private final JLabel      theAction  = new JLabel();
  private final JTextField  theInput   = new JTextField();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtCheck = new JButton( CHECK );
  private final JButton     theBtBuy   = new JButton( BUY );
  private final JButton     theBtBought= new JButton( BOUGHT );

  private final JButton     theBtRemove= new JButton( REMOVE );


  private StockReadWriter theStock     = null;
  private OrderProcessing theOrder     = null;
  private CashierController cont       = null;

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-coordinate of position of window on screen
   * @param y     y-coordinate of position of window on screen
   */

  public CashierView(  RootPaneContainer rpc,  MiddleFactory mf, int x, int y  )
  {
    try {
      theStock = mf.makeStockReadWriter();
      theOrder = mf.makeOrderProcessing();
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
    Border thickBlackBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);



    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );

    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is


    theBtCheck.setBorder(thickBlackBorder);
    theBtBuy.setBorder(thickBlackBorder);
    theBtBought.setBorder(thickBlackBorder);
    theAction.setBorder(thickBlackBorder);
    theInput.setBorder(thickBlackBorder);
    theSP.setBorder(thickBlackBorder);


    theBtCheck.setBounds( 16, 25+60*0, 80, 40 );    // Check Button
    theBtCheck.setBackground(Color.BLACK); // Set background color to black
    theBtCheck.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add black border
    theBtCheck.setForeground(Color.WHITE); // Set text color to white
    theBtCheck.addActionListener(                   // Call back code
      e -> cont.doCheck( theInput.getText() ) );
    cp.add( theBtCheck );                           //  Add to canvas

    theBtBuy.setBounds( 16, 25+60*1, 80, 40 );      // Buy button
    theBtBuy.setBackground(Color.BLACK); // Set background color to black
    theBtBuy.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add black border
    theBtBuy.setForeground(Color.WHITE); // Set text color to white
    theBtBuy.addActionListener(                     // Call back code
      e -> cont.doBuy() );
    cp.add( theBtBuy );                             //  Add to canvas

    theBtBought.setBounds(16, 25 + 60 * 3, 80, 40);  // bought button
    theBtBought.setBackground(Color.BLACK); // Set background color to black
    theBtBought.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add black border
    theBtBought.setForeground(Color.WHITE); // Set text color to white
    theBtBought.addActionListener(e -> cont.doBought());
    cp.add(theBtBought);


    theBtRemove.setBounds( 16, 25+60*2, 80, 40 );   // Remove Button
    theBtRemove.setBackground(Color.BLACK); // Set background color to black
    theBtRemove.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add black border
    theBtRemove.setForeground(Color.WHITE); // Set text color to white
    theBtRemove.addActionListener(                  // Call back code
            e -> cont.doRemove() );
    cp.add( theBtRemove );

    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setBackground(Color.BLACK); // Set background color to black
    theAction.setForeground(Color.WHITE); // Set text color to white
    theAction.setOpaque(true); // Ensure the background color is visible
    cp.add(theAction); //  Add to canvas

    theInput.setBounds( 110, 50, 270, 40 );         // Input Area
    theInput.setText("");                           // Blank
    cp.add( theInput );                             //  Add to canvas

    theSP.setBounds( 110, 100, 270, 160 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    theInput.requestFocus();                        // Focus is here


  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( CashierController c )
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
    CashierModel model  = (CashierModel) modelC;
    String      message = (String) arg;
    theAction.setText( message );
    Basket basket = model.getBasket();
    if ( basket == null )
      theOutput.setText( "Customers order" );
    else
      theOutput.setText( basket.getDetails() );
    
    theInput.requestFocus();               // Focus is here
  }

}
