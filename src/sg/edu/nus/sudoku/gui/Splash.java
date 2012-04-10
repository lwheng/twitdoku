package sg.edu.nus.sudoku.gui;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import sg.edu.nus.sudoku.controller.MainController;

public class Splash extends JWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -140566881472063463L;
	private JPanel splashPanel;
	private JLabel splashLabel, twitterLabel;
	private JButton btnLogin, btnCarryOn, btnQuit;

	private MainController controller;

	public Splash(MainController controller) {
		this.controller = controller;
		Container c = getContentPane();
		c.add(getSplashPanel());
		pack();

		Dimension screenSize =
			Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = splashLabel.getPreferredSize();
		setLocation(screenSize.width/2 - (labelSize.width/2),
				screenSize.height/2 - (labelSize.height/2) - 100);

		setVisible(true);
		screenSize = null;
		labelSize = null;
		setAlwaysOnTop(false);
	}

	private JPanel getSplashPanel() {
		splashPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridwidth = 2;
		splashPanel.add(getSplashLabel(), c);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		splashPanel.add(getBtnLogin(), c);

		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		splashPanel.add(getBtnCarryOn(), c);

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		splashPanel.add(getTwitterLabel(), c);

		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		splashPanel.add(getBtnQuit(), c);

		return splashPanel;
	}

	private JLabel getSplashLabel() {
		if(splashLabel == null) {
			splashLabel = new JLabel(Resources.SPLASH_IMAGE);
		}
		return splashLabel;
	}

	private JLabel getTwitterLabel() {
		if(twitterLabel == null) {
			twitterLabel = new JLabel("powered by : ", Resources.TWITTER_LOGO_ICON, JLabel.CENTER);
			twitterLabel.setVerticalTextPosition(SwingConstants.TOP);
			twitterLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			twitterLabel.setFont(Resources.TRIVIA_FONT);
			twitterLabel.setHorizontalAlignment(SwingConstants.LEFT);
			twitterLabel.setVerticalAlignment(SwingConstants.CENTER);
			twitterLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
		}
		return twitterLabel;
	}

	private JButton getBtnLogin() {
		if(btnLogin == null) {
			btnLogin = new JButton("Play With Login");
			btnLogin.setIcon(Resources.LOGIN_ICON);
			btnLogin.setHorizontalTextPosition(SwingConstants.RIGHT);
			btnLogin.setHorizontalAlignment(SwingConstants.CENTER);
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					authenticate();
				}
			});
		}
		return btnLogin;
	}

	private JButton getBtnCarryOn() {
		if(btnCarryOn == null) {
			btnCarryOn = new JButton("Play Without Login");
			btnCarryOn.setIcon(Resources.NO_LOGIN_ICON);
			btnCarryOn.setHorizontalTextPosition(SwingConstants.RIGHT);
			btnCarryOn.setHorizontalAlignment(SwingConstants.CENTER);
			btnCarryOn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.displayGui();
					dispose();
				}
			});
		}
		return btnCarryOn;
	}

	private JButton getBtnQuit() {
		if(btnQuit == null) {
			btnQuit = new JButton("");
			btnQuit.setIcon(Resources.EXITGAME_ICON);
			btnQuit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
			btnQuit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return btnQuit;
	}

	private void authenticate() {
		boolean loginSuccess = controller.showLoginUntilAuthenticated(this);
		if (loginSuccess) {
			controller.displayGui();
			dispose();
		}
	}

}