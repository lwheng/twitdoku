package sg.edu.nus.sudoku.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


import sg.edu.nus.sudoku.controller.MainController;
import sg.edu.nus.sudoku.twitter.SudokuTweet;

/**
 * 
 * This panel displays the original welcome text for the app.
 * This could be expanded to include live feeds from Twitter.
 * @author Your Name here
 *
 */
public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//private TwitterMethods twitlib = new TwitterMethods();
	private JList jlistChallenge;
	private DefaultListModel listModel;
	private List<SudokuTweet> tweets;
	private SudokuTweet loadingTweet;
	private SudokuTweet dcTweet;
	
	private Runnable updateTwitterbox = new Runnable(){
		public void run() {
			listModel.clear();
			for(SudokuTweet tweet: tweets) {
				if(tweet.isSolvable()) listModel.addElement(tweet);
			}
		}
	};
	
	private Runnable showDC = new Runnable() {
		@Override
		public void run() {
			if(dcTweet == null) {
				dcTweet = new SudokuTweet();
				dcTweet.setProfileIcon(Resources.NO_LOGIN_ICON);
				dcTweet.setAuthor("No connection");
				dcTweet.setMessage("Could not connect to twitter.com.\nHere are the previously retrieved tweets.");
				dcTweet.setMentionsUser(true);
				dcTweet.setSolvable(true);
			}
			listModel.insertElementAt(dcTweet, 0);
		}
	};
	
	WelcomePanel(final MainController controller) {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		JLabel lblTitle = new JLabel("Twitdoku+ v0.2", SwingConstants.CENTER);
		lblTitle.setFont(Resources.CELL_FONT);
		lblTitle.setIcon(Resources.NEWGAME_ICON);
		JLabel lblMessage = new JLabel("Click on the options on the right to start.");

		JPanel pnlTitle = new JPanel(); 
		pnlTitle.add(lblTitle);
		pnlTitle.add(lblMessage);
		pnlTitle.setPreferredSize(new Dimension(200, 120));
		JPanel pnlTwitterPull = new JPanel(new BorderLayout()); 


		JScrollPane listPane = new JScrollPane(getJlistChallenge(controller));
		listPane.setBorder(null);
		JPanel listPanel = new JPanel();
		BorderLayout listLayout = new BorderLayout();
		listPanel.setLayout(listLayout);
		listPanel.setBorder(BorderFactory.createTitledBorder("Twitter feed: Double click to challenge!"));
		listPanel.add(listPane);
		pnlTwitterPull.add(listPanel, BorderLayout.CENTER);
		this.add(pnlTitle, BorderLayout.PAGE_START);
		this.add(pnlTwitterPull, BorderLayout.CENTER);
	}

	public void showLoading() {
		if(loadingTweet == null) {
			loadingTweet = new SudokuTweet();
			loadingTweet.setProfileIcon(Resources.SHAREGAME_ICON);
			loadingTweet.setAuthor("Contacting Twitter");
			loadingTweet.setMessage("Retrieving Twitdoku tweets. Please wait...");
			loadingTweet.setSolvable(true);
			
		}
		listModel.clear();
		listModel.insertElementAt(loadingTweet, 0);
	}

	public void showNoConnection() {
		SwingUtilities.invokeLater(showDC);

	}

	private JList getJlistChallenge(final MainController controller) {
		if(jlistChallenge == null) {
			listModel = new DefaultListModel();
			jlistChallenge = new JList(listModel);
			jlistChallenge.setVisibleRowCount(10);
			jlistChallenge.setBorder(null);
			jlistChallenge.setLayoutOrientation(JList.VERTICAL);
			jlistChallenge.setCellRenderer(new TweetRenderer());
			jlistChallenge.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2) {
						SudokuTweet  t = (SudokuTweet) jlistChallenge.getSelectedValue();
						if (t.getSudokuString() != null) {
							controller.loadGameFromTweet(t);
						}
						
					}
				}
			});
		}
		return jlistChallenge;
	}

	public void updateFeed(final List<SudokuTweet> tweets) {
		this.tweets = tweets;
		SwingUtilities.invokeLater(updateTwitterbox);
	}


	private static class TweetRenderer implements ListCellRenderer {
		private Hashtable<SudokuTweet,JTextPane> cache;
		private Border emptyBorder;
		private Dimension size;
		private AttributeSet att;
		public TweetRenderer() {
			cache =  new Hashtable<SudokuTweet, JTextPane>();
			emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 5);
			size = new Dimension(200,120);
			att = StyleContext
			.getDefaultStyleContext()
			.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.FontSize,18);
		}
		public Component getListCellRendererComponent(JList list, Object value,	int index, boolean isSelected, boolean cellHasFocus) {
			SudokuTweet t = (SudokuTweet) value;
			JTextPane p;
			if((p=cache.get(t)) == null) p= createNewJTextPane(t);
			if(t.isMentionsUser()) {
				p.setForeground(Color.RED);
			}
			if(!isSelected) {
				p.setBorder(emptyBorder);
			} else {
				p.getBorder().getBorderInsets(p).left = 10;
			}
			return p;
		}


		JTextPane createNewJTextPane(SudokuTweet t) {
			JTextPane p = new JTextPane();
			p.setPreferredSize(size);
			Document d = p.getDocument();

			JLabel l = new JLabel(t.getProfileIcon());
			l.setFont(Resources.STANDARD_FONT_UNDERLINE);
			l.setText(t.getAuthor());
			p.setEditable(false);
			try {
				p.insertComponent(l);
				d.insertString(d.getLength(), "\n", att);
				d.insertString(d.getLength(), t.getMessage(), att);
			} catch (BadLocationException e1) {
			}

			return p;
		}

	}

}
