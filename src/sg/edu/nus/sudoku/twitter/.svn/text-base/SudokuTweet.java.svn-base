package sg.edu.nus.sudoku.twitter;


import java.io.Serializable;

import javax.swing.Icon;



public class SudokuTweet implements Serializable{
	
	private String author;
	private String id;
	private String message;
	private Icon profileIcon;
	private String sudokuString;
	private boolean mentionsUser;
	private boolean solvable;
	

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @return the profileIcon
	 */
	public Icon getProfileIcon() {
		return profileIcon;
	}
	/**
	 * @return the sudokuString
	 */
	public String getSudokuString() {
		return sudokuString;
	}
	/**
	 * @param profileIcon the profileIcon to set
	 */
	public void setProfileIcon(Icon profileIcon) {
		this.profileIcon = profileIcon;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @param sudokuString the sudokuString to set
	 */
	void setSudokuString(String sudokuString) {
		this.sudokuString = sudokuString;
	}
	@Override
	public String toString() {
		return new StringBuffer()
			.append(author).append('\n')
			.append(message).append('\n').toString();
	}
	/**
	 * @return the mentionsUser
	 */
	public boolean isMentionsUser() {
		return mentionsUser;
	}
	/**
	 * @param mentionsUser the mentionsUser to set
	 */
	public void setMentionsUser(boolean mentionsUser) {
		this.mentionsUser = mentionsUser;
	}
	/**
	 * @return the solvable
	 */
	public boolean isSolvable() {
		return solvable;
	}
	/**
	 * @param solvable the solvable to set
	 */
	public void setSolvable(boolean solvable) {
		this.solvable = solvable;
	}
	
}
