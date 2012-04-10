/**
 * 
 */
package sg.edu.nus.sudoku.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sourceforge.napkinlaf.NapkinLookAndFeel;

import sg.edu.nus.sudoku.controller.MainController;


/**
 * The main GUI class that draws the window, and loads all components,
 * e.g. JButtons, JTextFields, and the custom Cell component. This is where
 * all the LookAndFeel and Fonts should be set. By convention, a JFrame
 * usually contains a <tt>contentPane</tt> which is usually retrieved via
 * <tt>frame.getContentPane()</tt>. Subsequently, any sub JPanels added
 * should be instantiated and its properties set in separate methods. This
 * goes for ANY components generated. This way, anyone reading the code simply
 * has to go to the getComponentVariableName in order to view its properties.
 * To make the code neater, you can create a new class that <tt>extends 
 * JPanel</tt>. This way, the <tt>MainFrame</tt> class will not be cluttered
 * up with excessive methods. 
 * @author Your Name Here
 *
 */
public class GuiFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	final private static int FRAME_HEIGHT = 800;
	final private static int FRAME_WIDTH = 600;
	final private static String FRAME_NAME = "Twitdoku v0.2";
	static {
		try {
			
			//NapkinLookAndFeel napkinLaf = new NapkinLookAndFeel();
			UIManager.setLookAndFeel(new NapkinLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private MainController controller;
	private MainPanel mainPanel;
	private SidebarPanel sidebarPanel;


	public GuiFrame(final MainController controller) {
		this.controller = controller;
		this.setContentPane(createContentPane());
		this.setTitle(FRAME_NAME);
		this.setSize(FRAME_HEIGHT,FRAME_WIDTH);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.exitProgram();
			}
		});
		
		//added to make GuiFrame appear in the center of screen.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width/2 - screenSize.width/3, screenSize.height/2 - screenSize.height/5*2);
	}
	
	private JPanel createContentPane(){
		BorderLayout layout = new BorderLayout();
		JPanel pane = new JPanel(layout);
		layout.setHgap(10);
		pane.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 5));
		//pane.add(new JLabel(" "),BorderLayout.NORTH);
		this.mainPanel = new MainPanel(this.controller);
		this.sidebarPanel = new SidebarPanel(this.controller);
		pane.add(this.mainPanel, BorderLayout.CENTER);
		pane.add(this.sidebarPanel, BorderLayout.EAST);
		return pane;
	}
	/**
	 * Returns the mainPanel attribute of this object.
	 * Used by the MainController to retrieve this frame's sub-panels
	 * @return the mainPanel
	 */
	public MainPanel getMainPanel() {
		return mainPanel;
	}


	/**
	 * Returns the sidebarPanel of this object.
	 * Used by the MainController to retrieve this frame's sub-panels.
	 * @return the sidebarPanel
	 */
	public SidebarPanel getSidebarPanel() {
		return sidebarPanel;
	}


}
