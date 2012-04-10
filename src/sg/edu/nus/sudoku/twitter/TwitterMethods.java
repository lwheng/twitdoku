package sg.edu.nus.sudoku.twitter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import org.w3c.dom.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.lang.StringBuffer;
import java.util.ArrayList;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class TwitterMethods {
	
	final private static String NO_CONNECTION_MESSAGE = "No Connection";

	private String twitterhost = "www.twitter.com";
	private String twitteruser = "yipeng@gmail.com";
	private String twitterpwd = "yipeng";

	public String getTwitterpwd() {
		return twitterpwd;
	}

	public void setTwitterpwd(String twitterpwd) {
		this.twitterpwd = twitterpwd;
	}

	public String getTwitteruser() {
		return twitteruser;
	}

	public void setTwitteruser(String twitteruser) {
		this.twitteruser = twitteruser;
	}
	
	public static void main(String[] args) {
		TwitterMethods client = new TwitterMethods();
		client.setTwitteruser(JOptionPane.showInputDialog("Username:"));
		client.setTwitterpwd(JOptionPane.showInputDialog("Password:"));
		client.postShareGame("TwitterMethods");
//		/*
		// enter your credentials to make it work.
//		client.setTwitteruser("yipeng@gmail.com");
//		client.setTwitterpwd("yipeng");
//		String time = client.getTime();
//		String board_nums = client.getBoardNums();
//		String result = client.postScore_2Twitter(time, board_nums);
//		System.out.println(result);

		/* define your search term below
		String result2 = client.getSearchResults("Twitdoku");
		System.out.println("Result2 " + result2);
		if (result2 == null){
			return;
		}
		boolean parseCheck = client.parseXML_2LocalFile(result2);
		if (parseCheck==false){
			return;
		}
		client.parseXML_Title();
//		*/
	}


	/**
	 * Executes an Authenticated HTTP method (Get or Post)
	 * @param method - the get or post to execute
	 * @throws NullPointerException
	 * @throws HttpException
	 * @throws IOException
	 */
	private void executeAuthenticatedMethod(HttpMethodBase method) throws NullPointerException, HttpException, IOException {
		if ((twitteruser == null)||(twitterpwd == null)) {
			throw new RuntimeException("User and/or password has not been initialized!");
		}		
		HttpClient client = new HttpClient();
		Credentials credentials = new UsernamePasswordCredentials(this.getTwitteruser(), this.getTwitterpwd());
		client.getState().setCredentials(new AuthScope(twitterhost, 80, AuthScope.ANY_REALM), credentials);
		HostConfiguration host = client.getHostConfiguration();
		host.setHost(new URI("http://"+twitterhost, true));
		client.executeMethod(host, method);		
	}

	//	/**
	//	 * Gets the 20 most recent statuses by friends (equivalent to /home on the web)
	//	 * @return a String representing the XML response.
	//	 */
	//	private String getFriendsTimeline() {
	//		String message = "";		
	//		String url = "/statuses/friends_timeline.xml";
	//		GetMethod get = new GetMethod(url);
	//	    try {
	//	    	executeAuthenticatedMethod(get);
	//			message = message + get.getResponseBodyAsString();
	//		} catch (URIException e) {
	//			message = e.getMessage();			
	//		} catch (NullPointerException e) {
	//			message = e.getMessage();
	//		} catch (IOException e) {
	//			message = e.getMessage();
	//		} finally {
	//			get.releaseConnection();
	//		}
	//		return message;
	//	}

	private String getSearchResults(String searchString) {
		
		// this method retrieves search terms in ATOM/XML format
		final String lang = "en";
		final String rpp = "15";
		final String show_user = "false";

		String url = "http://search.twitter.com/search.atom?q=" + searchString + 
		"&rpp=" + rpp +
		"&lang=" + lang +
		"&show_user=" + show_user;

		String message = "";		

		GetMethod get = new GetMethod(url);
		try {
			executeAuthenticatedMethod(get);
			message = message + get.getResponseBodyAsString();
		} catch (URIException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			get.releaseConnection();
		}
		return message;
	}

	/**
	 * Update your Twitter Status
	 * @param state
	 * @return
	 */
	private String postScore_2Twitter(String score, String boardnums) {
		
		// this method posts on the user's twitter account 
		
		String url = "/statuses/update.xml";
		String message ="";
		if (score!=null){ 
			message = "took " + score + " to solve this Twitdoku (" + boardnums +")";
		}else  message = "solved this Twitdoku (" + boardnums +")";

		PostMethod post = new PostMethod(url);
		NameValuePair params[] = new NameValuePair[1];
		params[0] = new NameValuePair("status", message);
		post.setRequestBody(params);		
		try {
			executeAuthenticatedMethod(post);
			message = "Status:"+post.getStatusText();
		} catch (URIException e) {
				
		} catch (NullPointerException e) {

		} catch (IOException e) {
	
		} finally {
			post.releaseConnection();
		}

		return message;
	}
	
	public String postShareGame(String boardnums) {
		
		// this method posts on the user's twitter account 
		
		String url = "/statuses/update.xml";
		String message ="";
		message =  "is trying to solve this Sudoku! #Twitdoku " + boardnums;

		PostMethod post = new PostMethod(url);
		NameValuePair params[] = new NameValuePair[1];
		params[0] = new NameValuePair("status", message);
		post.setRequestBody(params);		
		try {
			executeAuthenticatedMethod(post);
			message = "Status:"+post.getStatusText();
		} catch (URIException e) {
				
		} catch (NullPointerException e) {

		} catch (HttpException e) {
			
		} catch (IOException e) {
			message = "Status:"+NO_CONNECTION_MESSAGE;
		} finally {
			post.releaseConnection();
		}
		return message;
	}

	private boolean parseXML_2LocalFile(String message){
		
		// this method saves the retrieved XML data into a file 
		// saving to file will be helpful to display data even when the user has no twitter account / net connection
		
		try {
			OutputStream fout = new FileOutputStream("Twitdoku.xml");
			OutputStream bout= new BufferedOutputStream(fout);
			OutputStreamWriter out = new OutputStreamWriter(bout);
			out.write(message);
			out.flush();  
			out.close();
		}catch (Throwable t) {
			t.printStackTrace ();
			return false;
		}
		return true;
	}

	public boolean parseXML_Title(){
		
		// This method retreives the XML from file and lists only the Title Elements
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File("Twitdoku.xml"));
			NodeList listOfTitleElements = doc.getDocumentElement().getElementsByTagName("title");
			// Ignore first title term in list returning <Search Term>-Twitter Search
			for(int i=1; i<listOfTitleElements.getLength() ; i++){
				// getFirstChild works because there is only 1 value
				Node elemOfList = listOfTitleElements.item(i).getFirstChild();
				System.out.println(elemOfList.getNodeValue());
//				ArrayList<String> entries = null;
//				entries.add(elemOfList.getNodeValue());
			}

		}catch (Throwable t) {
			t.printStackTrace ();
			return false;
		}
		return true;
	}
	

	public ArrayList<String> getXMLTitle(){
	ArrayList<String> entries = new ArrayList<String>();;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File("Twitdoku.xml"));
			NodeList listOfTitleElements = doc.getDocumentElement().getElementsByTagName("title");
			for(int i=1; i<listOfTitleElements.getLength() ; i++){
				Node elemOfList = listOfTitleElements.item(i).getFirstChild();
				entries.add(elemOfList.getNodeValue());
			}
		}catch (Throwable t) {
			t.printStackTrace ();
		}
		return entries;
	}
	
	public String[][] getChallengeResults(){
		String[][] challengeList = new String[10][2];
			try {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse (new File("Twitdoku.xml"));
				NodeList listOfTitleElements = doc.getDocumentElement().getElementsByTagName("title");
				for(int i=1; i<listOfTitleElements.getLength() ; i++){
					Node elemOfList = listOfTitleElements.item(i).getFirstChild();
					String temp = elemOfList.getNodeValue(); 
					String[] part = temp.split("#");
					challengeList[i-1][0] = part[0];
					challengeList[i-1][1] = part[1].substring(9);
					System.out.println(part[1].substring(9)); 
				}
			}catch (Throwable t) {
				t.printStackTrace ();
			}
			return challengeList;
		}


	private String getBoardNums() {
		// TODO Auto-generated method stub
		
		// this method takes in the Sudoku board numbers and cuts it up after every 9 characters. 
		// this fixes a display bug in the Twitter web interface which cannot handle no whitespace. 
		
		String board_nums = "000000010400000000020000000000050407008000300001090000300400200050100000000806000";
		StringBuffer test = new StringBuffer(board_nums);
		for(int i=8;i<board_nums.length()+9;i+=10){
			test.insert(i+1, " ");
		}
		board_nums = test.toString().trim();		
		return board_nums;
	}

	private String getTime() {
		// TODO Auto-generated method stub
		// this method takes in the total time taken by the user and outputs it into a String		
		
		return null;
	}
	private String compressString(String sudokuString){
		int size = sudokuString.length();
		StringBuffer sb = new StringBuffer();
		char previous = sudokuString.charAt(0);
		for(int i=1;i<size;i++){
			int count = 1;
			while ( i < sudokuString.length() && previous == sudokuString.charAt(i)){
				
				count++;
				i++;
			}
			if(count == 1) sb.append(previous);
			else if(count > 4){
				sb.append('/')
					.append(previous)
					.append(':')
					.append(count);
			} else {
				for(int j=0;j<count;j++) sb.append(previous);
			}
			if(i<sudokuString.length())previous = sudokuString.charAt(i);
		}
		return sb.toString();
	}

	public void getMainListing() {
		String result2 = getSearchResults("twitdoku%2Bcompleted%20a%20sudoku", 10);
		if (result2 == null){
			return;
		}
		boolean parseCheck = parseXML_2LocalFile(result2);
		if (parseCheck==false){
			return;
		}
		parseXML_Title();
	}
	
	private String getSearchResults(String searchString, int rpp) {
		
		// this method retrieves search terms in ATOM/XML format
		final String lang = "en";
		final String show_user = "false";

		String url = "http://search.twitter.com/search.atom?q=" + searchString + 
		"&rpp=" + rpp +
		"&lang=" + lang +
		"&show_user=" + show_user;

		String message = "";		
		System.out.println(url);
		GetMethod get = new GetMethod(url);
		try {
			executeAuthenticatedMethod(get);
			message = message + get.getResponseBodyAsString();
		} catch (URIException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			get.releaseConnection();
		}
		return message;
	}
}
