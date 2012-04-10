package sg.edu.nus.sudoku.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Resources {
	final static String RESOURCE_DIRECTORY = "resources/";
	private static Font DEFAULT_FONT = null;
	static {
		loadFont();

	}
	final static Font STANDARD_FONT = DEFAULT_FONT;
	final static Font TRIVIA_FONT = DEFAULT_FONT.deriveFont(Font.BOLD, 15);
	final static Font CELL_FONT = DEFAULT_FONT.deriveFont(Font.BOLD, 40);
	final static Font STANDARD_FONT_UNDERLINE = DEFAULT_FONT.deriveFont(Font.ITALIC);
	
	
	
	final public static Icon HALLOFFAME_ICON = 	loadIcon("halloffame.png");
	final public static Icon NEWGAME_ICON = 	loadIcon("newgame.png");
	final public static Icon BLANKMAP_ICON = 	loadIcon("blankmap.png");
	final public static Icon SAVEGAME_ICON = 	loadIcon("savegame.png");
	final public static Icon SHAREGAME_ICON = 	loadIcon("sharegame.png");
	final public static Icon LOADGAME_ICON = 	loadIcon("loadgame.png");
	final public static Icon SETTINGS_ICON = 	loadIcon("settings.png");
	final public static Icon EXITGAME_ICON = 	loadIcon("exitgame.png");
	final public static Icon SOLVER_ICON = 		loadIcon("solver.png");
	final public static Icon CLEARBOARD_ICON = 	loadIcon("clearboard.png");
	final public static Icon RETURNTOMENU_ICON = loadIcon("returntomenu.png");
	final public static Icon STOPWATCH_ICON = 	loadIcon("stopwatch.png");
	final public static Icon VICTORY_ICON = loadIcon("victory.png");
	final public static Icon TOOLTIP_ICON = loadIcon("tooltip.png");
	final public static Icon SPLASH_IMAGE = loadIcon("splash.png");
	final public static Icon NO_LOGIN_ICON = loadIcon("nologin.png");
	final public static Icon LOGIN_ICON = loadIcon("login.gif");
	final public static Icon TWITTER_LOGO_ICON = loadIcon("twitterlogo.png");
	final public static Icon REFRESH_ICON = loadIcon("refresh.png");
	
	final public static ArrayList<String> TRIVIA_LINES = loadFile("trivia.txt");

	
	final public static Color NON_EDITABLE_CELL_COLOR = new Color(2, 150, 2);
	
	public static void loadResources(){
		setDefaultFont();
	}
	
	private static ArrayList<String> loadFile(String filename) {
		// TODO Auto-generated method stub
		ArrayList<String> trivia = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						Resources.class.getResourceAsStream(RESOURCE_DIRECTORY+filename)
						)
				);
		String line;
		try {
			while((line = reader.readLine()) != null) {
				trivia.add(line);
			}
			
		} catch (IOException e) {e.printStackTrace();}
		return trivia;
	}

	private static Icon loadIcon(String filename){
		ImageIcon icon = new ImageIcon(Resources.class.getResource(RESOURCE_DIRECTORY+filename));
		//System.out.println(icon);
		return icon;
	}
	
	
	private static void setDefaultFont(){
		Enumeration<?> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, DEFAULT_FONT);
			}
		}
	}
	private static void loadFont(){
		try {
			InputStream fontstream = Resources.class.getResourceAsStream(RESOURCE_DIRECTORY+"aescrawl.ttf");
			DEFAULT_FONT = Font.createFont(Font.TRUETYPE_FONT, fontstream)
			.deriveFont(Font.PLAIN, 20);
			// UIManager.setLookAndFeel("napkin.NapkinLookAndFeel");
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
