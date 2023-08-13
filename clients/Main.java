package clients;
import clients.backDoor.BackDoorController;
import clients.backDoor.BackDoorModel;
import clients.backDoor.BackDoorView;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import clients.collection.CollectController;
import clients.collection.CollectModel;
import clients.collection.CollectView;
import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
import clients.shopDisplay.DisplayController;
import clients.shopDisplay.DisplayModel;
import clients.shopDisplay.DisplayView;
import clients.warehousePick.PickController;
import clients.warehousePick.PickModel;
import clients.warehousePick.PickView;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * Starts all the clients  as a single application.
 * Good for testing the system using a single application but no use of RMI.
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */
class Main
{
  // Change to false to reduce the number of duplicated clients

  private final static boolean many = false;        // Many clients? (Or minimal clients)
  private static JButton button;
  private JButton button1;
  private JButton button2;
  private JButton button3;
  private JButton button4;
  private JButton button5;

  public static void main (String args[])
  {
      startPlayMusic(); // Add this line to start playing music

      MiddleFactory mlf = new LocalMiddleFactory();
	  JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(new Point(10, 50));		    
		frame.setSize(new Dimension(300, 300));
		frame.setTitle("Client Main Window");


		frame.setVisible(true);

      JButton button = new JButton("Customer");
		button.setBounds(17, 25+60*0, 120, 40);
		button.setBackground(Color.red);
		frame.getContentPane().add(button);
		button.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        new Main().startCustomerGUI_MVC( mlf );


            }
		});


		JButton button1 = new JButton("Cashier");
		button1.setBounds(150, 25+60*0, 120, 40);
		button1.setBackground(Color.red);
		frame.getContentPane().add(button1);
		button1.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		      	new Main().startCashierGUI_MVC( mlf );
            }
		});
		JButton button2 = new JButton("BackDoor");
		button2.setBounds(17, 25+60*1, 120, 40);
		button2.setBackground(Color.red);
		frame.getContentPane().add(button2);
		button2.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		      	new Main().startBackDoorGUI_MVC( mlf );
            }
		});
		JButton button3 = new JButton("Pickup");
		button3.setBounds(150, 25+60*1, 120, 40);
		button3.setBackground(Color.red);
		frame.getContentPane().add(button3);
		button3.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		      	new Main().startPickGUI_MVC( mlf );
            }
		});
		JButton button4 = new JButton("Display");
		button4.setBounds(17, 25+60*2, 120, 40);
		button4.setBackground(Color.RED);
		frame.getContentPane().add(button4);
		button4.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		      	new Main().startDisplayGUI_MVC( mlf );
            }
		});
		JButton button5 = new JButton("Collection");
		button5.setBounds(150, 25+60*2, 120, 40);
		button5.setBackground(Color.red);
		frame.getContentPane().add(button5);
		button5.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		      	new Main().startCollectionGUI_MVC( mlf );
            }
		});
  }
  

  /**
   * Starts test system (Non distributed)
   */
  public void begin()
  {
    //DEBUG.set(true); /* Lots of debug info */
    MiddleFactory mlf = new LocalMiddleFactory(); // Direct access
 
    startCustomerGUI_MVC( mlf );
    if ( many ) 
     startCustomerGUI_MVC( mlf );
    startCashierGUI_MVC( mlf );
    startCashierGUI_MVC( mlf );
    startBackDoorGUI_MVC( mlf );
    if ( many ) 
      startPickGUI_MVC( mlf );
    startPickGUI_MVC( mlf );
    startDisplayGUI_MVC( mlf );
    if ( many ) 
      startDisplayGUI_MVC( mlf );
    startCollectionGUI_MVC( mlf );
  }
  
  public static void startCustomerGUI_MVC(MiddleFactory mlf)
  {
    JFrame  window = new JFrame();
    window.setTitle( "Customer Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    CustomerModel model      = new CustomerModel(mlf);
    CustomerView view        = new CustomerView( window, mlf, pos.width, pos.height );
    CustomerController cont  = new CustomerController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // start Screen
  }

    private static void startPlayMusic() {


        try {
            File musicPath = new File("images/music.wav"); // Sets filepath for ambience
            if (musicPath.exists()) {                                                      // Checks if filepath is valid
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);   // Creates input stream
                Clip Ambience = AudioSystem.getClip();
                Ambience.loop(Clip.LOOP_CONTINUOUSLY);    // sets clip to loop forever
                Ambience.open(audioInput);                // opens clip
                Ambience.start();                         // starts clip
            }
        }
        catch (Exception e) {
            System.out.println("Ambience failed to load"); // displays error message if and errors occur in method
        }
    }

  /**
   * start the cashier client
   * @param mlf A factory to create objects to access the stock list
   */
  public void startCashierGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();
    window.setTitle( "Cashier Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    CashierModel model      = new CashierModel(mlf);
    CashierView view        = new CashierView( window, mlf, pos.width, pos.height );
    CashierController cont  = new CashierController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
    model.askForUpdate();            // Initial display
  }

  public void startBackDoorGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "BackDoor Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    BackDoorModel model      = new BackDoorModel(mlf);
    BackDoorView view        = new BackDoorView( window, mlf, pos.width, pos.height );
    BackDoorController cont  = new BackDoorController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }
  

  public void startPickGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Pick Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    PickModel model      = new PickModel(mlf);
    PickView view        = new PickView( window, mlf, pos.width, pos.height );
    PickController cont  = new PickController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }
  
  public void startDisplayGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Display Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    DisplayModel model      = new DisplayModel(mlf);
    DisplayView view        = new DisplayView( window, mlf, pos.width, pos.height );
    DisplayController cont  = new DisplayController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }


  public void startCollectionGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Collect Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    CollectModel model      = new CollectModel(mlf);
    CollectView view        = new CollectView( window, mlf, pos.width, pos.height );
    CollectController cont  = new CollectController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }

public JButton getButton() {
	return button;
}

public void setButton(JButton button) {
	this.button = button;
}


public JButton getButton1() {
	return button1;
}


public void setButton1(JButton button1) {
	this.button1 = button1;
}


public JButton getButton2() {
	return button2;
}


public void setButton2(JButton button2) {
	this.button2 = button2;
}


public JButton getButton3() {
	return button3;
}


public void setButton3(JButton button3) {
	this.button3 = button3;
}


public JButton getButton4() {
	return button4;
}


public void setButton4(JButton button4) {
	this.button4 = button4;
}


public JButton getButton5() {
	return button5;
}


public void setButton5(JButton button5) {
	this.button5 = button5;
}

}
