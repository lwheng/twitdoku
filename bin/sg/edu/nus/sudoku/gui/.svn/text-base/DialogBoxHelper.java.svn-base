package sg.edu.nus.sudoku.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sg.edu.nus.sudoku.persistence.Persistence;

public class DialogBoxHelper {
	final private static String[] OK_CANCEL = {"OK","Cancel"};

	private static JPanel saveGameDialogue;
	private static JTextField saveGameDialogueNameField;	
	final private static String[] OVERWRITE_CHOOSENEWNAME = {"Overwrite","Choose New Name"};
	final private static String[] YES_NO_OOPS = {"Yes","No", "Oops!"};
	final private static String[] YES_NO = {"Yes","No"};
	private static JPanel loadGameDialogue;
	private static JList  loadGameDialogueGameList;
	private static DefaultListModel loadGameListModel;
	private static JPanel changeUserPanel;
	private static JLabel lblCUUsername;
	private static JLabel lblCUPassword;
	private static JTextField txtCUUsername;
	private static JPasswordField txtCUPassword;
	private static JLabel lblTwitterTitle;
	private static JPanel stringCreationDialogue;
	private static JTextField stringCreationDialogueNameField;	

	private static JPanel inputMessagePanel;
	private static JTextArea txtMessage;
	private static JLabel lblCharCount;
	private static int charCount;


	public static String showSaveGameDialogue(Component parentComponent){
		int option = JOptionPane.showOptionDialog(
				parentComponent,
				getSaveGameDialogue(),
				"Save game",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				OK_CANCEL,
				OK_CANCEL[0]);
		if(option == 1) return null;
		String s = saveGameDialogueNameField.getText();
		saveGameDialogueNameField.setText("");
		return s;
	}
	public static boolean showConfirmOverwriteDialogue(Component parentComponent){
		int option = JOptionPane.showOptionDialog(
				parentComponent,
				"Game name exists. \n Overwrite?",
				"Save game",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				OVERWRITE_CHOOSENEWNAME,
				OVERWRITE_CHOOSENEWNAME[0]);
		return option == 0;
	}

	public static int showSaveQueryDialogue(Component parentComponent){
		int option = JOptionPane.showOptionDialog(
				parentComponent,
				"Do you want to save?",
				"Exit Game!",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				YES_NO_OOPS,
				YES_NO_OOPS[0]);
		return option;
	}

	public static int showClearQueryDialogue(Component parentComponent){
		int option = JOptionPane.showOptionDialog(
				parentComponent,
				"Do you want to clear all user input?",
				"Clear Board!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				YES_NO,
				YES_NO[0]);
		return option;
	}

	public static String showLoadGameDialogue(Component parentComponent){
		int option = JOptionPane.showOptionDialog(
				parentComponent,
				getLoadGameDialogue(),
				"Load game",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				Resources.NEWGAME_ICON,
				OK_CANCEL,
				OK_CANCEL[0]);
		if(option == 1) return null;
		String s = loadGameDialogueGameList.getSelectedIndex()!=-1?(String)loadGameDialogueGameList.getSelectedValue():null;
		return s;
	}

	public static String showEnterNameDialogue(Component parentComponent) {
		String name = (String)JOptionPane.showInputDialog(
				parentComponent,
				"Enter your name:",
				"High Score",
				JOptionPane.PLAIN_MESSAGE,
				Resources.HALLOFFAME_ICON,
				null,
		"Sudoku Player");
		if(name.equals("")) return "Sudoku Player";

		return name;
	}

	public static void showMessageDialogue(Component parentComponent, String message, String title, int typeOfMessage) {
		JOptionPane.showMessageDialog(parentComponent, message, title, typeOfMessage);
	}

	public static void showNoUniqueSolutionDialogue(Component parentComponent){
		JOptionPane.showMessageDialog(parentComponent, 
		"No unique solution found!\n Please try again.");

	}

	public static void updateSaveGame(String name){
		getLoadGameDialogue();
		if(!loadGameListModel.contains(name)) 
			loadGameListModel.addElement(name);
	}
	private static JPanel getLoadGameDialogue(){
		if(loadGameDialogue == null) {
			loadGameDialogue = new JPanel(new BorderLayout());
			loadGameDialogue.add(new JLabel("Load game..."),BorderLayout.NORTH);
			loadGameListModel = new DefaultListModel();
			String[] names = Persistence.getInstance().getSavedGames();
			for(int i=0;i<names.length;i++){
				loadGameListModel.addElement(names[i]);
			}
			loadGameDialogueGameList = new JList(loadGameListModel);
			JScrollPane scrollpane = new JScrollPane(loadGameDialogueGameList);
			loadGameDialogue.add(scrollpane,BorderLayout.CENTER);
		}
		return loadGameDialogue;
	}
	private static JPanel getSaveGameDialogue(){
		if(saveGameDialogue == null) {
			saveGameDialogue = new JPanel(new GridLayout(2, 1));
			saveGameDialogue.add(new JLabel("Save as..."));
			saveGameDialogueNameField = new JTextField(20);
			saveGameDialogueNameField.setName("nameField");
			saveGameDialogueNameField.setBorder(BorderFactory.createTitledBorder(""));
			saveGameDialogue.add(saveGameDialogueNameField);
		}
		return saveGameDialogue;
	}

	private static JPanel getStringCreationDialogue(){
		if(stringCreationDialogue == null) {
			stringCreationDialogue = new JPanel(new GridLayout(2, 1));
			JTextArea jLabelReplacement = new JTextArea("Create a game from a string of 81 numbers! \nUse 0s to represent blank cells and read from left to right.");
			jLabelReplacement.setEditable(false);
			jLabelReplacement.setBorder(null);
			stringCreationDialogue.add(jLabelReplacement);
			stringCreationDialogueNameField = new JTextField(20);
			stringCreationDialogueNameField.setName("nameField");
			stringCreationDialogueNameField.setBorder(BorderFactory.createTitledBorder(""));
			stringCreationDialogue.add(stringCreationDialogueNameField);
		}
		return stringCreationDialogue;
	}

	public static String[] loginToTwitter(Component parentComponent, String title, int typeOfMessage) {
		changeUserPanel = getChangeUserPanel();
		int input = JOptionPane.OK_OPTION;
		txtCUPassword.setText("");
		txtCUUsername.setText("");
		while( (txtCUPassword.getPassword().length == 0 ||
				txtCUUsername.getText().equals("")) &&
				input == JOptionPane.OK_OPTION
		)  {
			input = JOptionPane.showOptionDialog(
					parentComponent,
					changeUserPanel,
					title,
					JOptionPane.OK_CANCEL_OPTION,
					typeOfMessage,
					null, null,
					0);
		}
		if (input == JOptionPane.OK_OPTION) return new String[] {txtCUUsername.getText(),new String(txtCUPassword.getPassword())};
		else return null;
	}

	private static JPanel getChangeUserPanel() {
		if(changeUserPanel == null) {
			changeUserPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c  = new GridBagConstraints();

			c.anchor = GridBagConstraints.CENTER;
			c.weightx = 0;
			c.weighty = 0;
			c.fill = GridBagConstraints.HORIZONTAL;

			lblTwitterTitle = new JLabel("Log onto Twitter and share your game!");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth=3;
			changeUserPanel.add(lblTwitterTitle,c);

			lblCUUsername = new JLabel("Username: ");
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth=1;
			changeUserPanel.add(lblCUUsername,c);

			lblCUPassword = new JLabel("Password: ");
			c.gridx = 0;
			c.gridy = 2;
			changeUserPanel.add(lblCUPassword,c);

			c.insets.right = 0;

			txtCUUsername = new JTextField(20);
			c.gridx = 1;
			c.gridy = 1;
			c.gridwidth = 2;
			c.ipadx = 230;
			changeUserPanel.add(txtCUUsername,c);

			txtCUPassword = new JPasswordField(20);
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 2;
			c.ipadx = 230;
			changeUserPanel.add(txtCUPassword,c);
		}

		return changeUserPanel;
	}

	public static String showStringCreationDialog(Component parentComponent){
		int option = JOptionPane.showOptionDialog(
				parentComponent,
				getStringCreationDialogue(),
				"Create your own Sudoku",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				OK_CANCEL,
				OK_CANCEL[0]);
		if(option == 1){
			stringCreationDialogueNameField.setText("");
			return null;
		}

		String s = stringCreationDialogueNameField.getText();
		if (s.length()!=81){showStringInvalidDialog(parentComponent);s=null;} 
		stringCreationDialogueNameField.setText("");
		return s;
	}
	private static void showStringInvalidDialog(Component parentComponent) {
		JOptionPane.showMessageDialog(parentComponent, "Input string must have a length of 81 characters.");
	}

	public static JPanel getInputMessagePanel() {
		if(inputMessagePanel == null) {
			inputMessagePanel = new JPanel(new BorderLayout());
			lblCharCount = new JLabel();
			lblCharCount.setForeground(Color.GRAY);
			lblCharCount.setHorizontalAlignment(SwingConstants.RIGHT);
			txtMessage = new JTextArea(2,25);
			txtMessage.setFont(Resources.TRIVIA_FONT);
			txtMessage.setLineWrap(true);
			txtMessage.setWrapStyleWord(true);
			txtMessage.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					updateCharLeft();
				}
			});
			txtMessage.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					updateCharLeft();
				}
			});
			JScrollPane sp = new JScrollPane(txtMessage);
			inputMessagePanel.add(lblCharCount,BorderLayout.NORTH);
			inputMessagePanel.add(sp,BorderLayout.CENTER);
		}
		return inputMessagePanel;
	}
	private static void updateCharLeft() {
		int charLeft = charCount - txtMessage.getText().length();
		if(charLeft < 10) lblCharCount.setForeground(Color.RED);
		else if (charLeft < 20) lblCharCount.setForeground(Color.BLACK);
		else lblCharCount.setForeground(Color.GRAY);
		lblCharCount.setText(Integer.toString(charLeft));
	}

	public static synchronized String showWriteMessageDialog(Component parentComponent,int charLimit,String defaultMessage) {
		charCount = charLimit;
		String input = "";
		int option = 0;
		inputMessagePanel = getInputMessagePanel();
		int charLeft = charCount - txtMessage.getText().length();
		lblCharCount.setText(Integer.toString(charLeft));
		txtMessage.setText(defaultMessage);
		while((input.equals("") && option != JOptionPane.CANCEL_OPTION) || input.length() > charLimit) {
			option = JOptionPane.showOptionDialog(
					parentComponent,
					inputMessagePanel,
					"Tweet message",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					Resources.SHAREGAME_ICON,
					null,
					0
			);

			input = txtMessage.getText();

			if (input.length() > charLimit) {
				int opt2 = JOptionPane.showOptionDialog(parentComponent, 
						"The message is too long and will be truncated\nContinue?", 
						"Too long for Twitter",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null, null, 0);
				if(opt2==JOptionPane.YES_OPTION) {
					input = input.substring(0,charLimit - 1);
				}

			}
		}
		if(option == JOptionPane.CANCEL_OPTION) {
			return null;
		} else {
			return input;
		}

	}



}
