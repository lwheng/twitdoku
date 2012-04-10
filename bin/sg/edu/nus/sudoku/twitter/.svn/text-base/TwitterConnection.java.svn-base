package sg.edu.nus.sudoku.twitter;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sg.edu.nus.sudoku.model.SudokuBoard;
import sg.edu.nus.sudoku.model.SudokuSolver;
import sg.edu.nus.sudoku.util.Compressor;


/**
 * The TwitterConnection objects provides the api for connection to the Twitter API ({@link http://apiwiki.twitter.com/})
 * 
 * This class is dependent on the commons-httpclient ({@link http://hc.apache.org/}) provided by
 * the Apache Software Foundation. Its dependencies include commons-codec and commons-logging.
 * @author Yipeng, Shawn , Low Wee and Breton
 */
public class TwitterConnection{
	/**
	 * The maximum number of characters permitted by Twitter per Tweet.
	 */
	final public static int MAX_TWITTER_LENGTH = 140;
	final private String TWITTER_HOST = "www.twitter.com";
	final private String UPDATE_LOCATION = "/statuses/update.xml";
	final private AuthScope twitterAuthScope = AuthScope.ANY;//new AuthScope(TWITTER_HOST, 80, AuthScope.ANY_REALM);
	final private String lang = "en";
	final private String rpp = "15";
	final private String show_user = "false";
	final private String KEYWORD = "twitdoku";
	final private Pattern twitdokuPattern = Pattern.compile("(.*)\\s\\#[Tt]witdoku\\s(.*)");
	final private Pattern tweetPattern = Pattern.compile("(\\w+):\\s(.*)");
	final private Pattern idPattern = Pattern.compile("(.*):(\\d+)");
	private Matcher twitdokuMatcher;
	private Matcher tweetMatcher;
	private Matcher idMatcher;
	private boolean connected;
	private String twitterName;
	private HttpClient client;
	private Credentials credentials;
	private DocumentBuilder builder;
	private String latestId;
	private LinkedList<SudokuTweet> cachedTweets;
	private SudokuBoard sb;

	private static TwitterConnection instance;

	/**
	 * Returns the TwitterConnection object for the first time.
	 * @return The TwitterConnection object.
	 */
	public static TwitterConnection getInstance(){
		if(instance==null) {
			instance = new TwitterConnection();
		}
		return instance;
	}

	/**
	 * Instantiates a new TwitterConnection object for the first time with the given savedTweets.
	 * @param savedTweets
	 * @return The TwitterConnection object.
	 */
	public static TwitterConnection getInstance(List<SudokuTweet> savedTweets){
		if(instance==null) {
			instance = new TwitterConnection(savedTweets);
		}
		return instance;
	}

	private TwitterConnection() {
		init();
	}

	private TwitterConnection(List<SudokuTweet> savedTweets) {
		this.cachedTweets = (LinkedList<SudokuTweet>)savedTweets;
		this.latestId = cachedTweets.peek().getId();
		init();
	}

	/**
	 * Authenticates using the Twitter API with the given <tt>username</tt> and <tt>password</tt>.
	 * @param username The same username used at the Twitter login page can be used.
	 * @param password The same password used at the Twitter login page can be used.
	 * @return true if successfully authenticated. false if the wrong credentials are supplied.
	 * @throws IOException Thrown when there is a connection error.
	 */
	public synchronized boolean authenticate(String username, String password) throws HttpException, IOException {

		if ((username == null)||(password == null)) {
			throw new RuntimeException("User and/or password has not been initialized!");
		}		
		credentials = new UsernamePasswordCredentials(username, password);
		client.getState().setCredentials(twitterAuthScope, credentials);
		try {
			if(checkAuthentication()) {
				connected = true;
				return true;
			}
			else {
				clearAuthentication();
				return false;
			}
		} catch (HttpException e) {
			clearAuthentication();
			throw e;
		} catch (IOException e) {
			clearAuthentication();
			throw e;
		}
	}

	
	private boolean checkAuthentication() throws HttpException, IOException {
		GetMethod get = new GetMethod("http://www.twitter.com/account/verify_credentials.xml");
		get.setDoAuthentication(true);
		client.executeMethod(get);
		try {
			if(get.getStatusCode()==200) {
				Document doc = builder.parse(get.getResponseBodyAsStream());
				twitterName = getNodeWithTagName(0, "screen_name",doc.getDocumentElement())
				.getFirstChild()
				.getNodeValue();
				return true;
			}
		} catch (SAXException e) {}
		return false;
	}
	
	private void clearAuthentication() {
		client.getState().clearCredentials();
		connected = false;
		this.twitterName = null;

		credentials = null;
	}

	private String extractId(String idString) {
		if(idMatcher == null) idMatcher = idPattern.matcher(idString);
		else idMatcher.reset(idString);
		if(idMatcher.matches()) {
			return idMatcher.group(2);
		} else {
			return null;
		}

	}

	private void fillAuthorMessageSudoku(String msgBody,SudokuTweet t) {
		if(tweetMatcher==null) tweetMatcher = tweetPattern.matcher(msgBody);
		else tweetMatcher.reset(msgBody);
		if(tweetMatcher.matches()) {
			t.setAuthor(tweetMatcher.group(1));
			String msgText = tweetMatcher.group(2);
			if(twitdokuMatcher==null) twitdokuMatcher = twitdokuPattern.matcher(msgText);
			else twitdokuMatcher.reset(msgText);
			if(twitdokuMatcher.matches()){
				t.setMessage(twitdokuMatcher.group(1));
				t.setSudokuString(Compressor.stringDecompressor(twitdokuMatcher.group(2)));
			} else {
				t.setMessage(msgText);
			}
		} 
	}

	/**
	 * Retrieves and processes Tweets from Twitter and returns a {@link LinkedList} of {@link SudokuTweet}s with
	 * the latest tweet being the first element in the list.
	 * 
	 * If the user is logged in, this method returns the tweets the user is mentioned in to the top of the list
	 * sorted by time posted.
	 * @return
	 */
	public synchronized  List<SudokuTweet> getMainListing() throws HttpException, IOException {
		LinkedList<SudokuTweet> resultList = getSearchResults("#"+KEYWORD);
		LinkedList<SudokuTweet> mainListing = processForMainListing(resultList);

		return mainListing;
	}
	
	/**
	 * Processes the cached Tweets <b>previously</b> retrieved
	 * from Twitter
	 * @return {@link LinkedList} of {@link SudokuTweet}s
	 */
	public synchronized List<SudokuTweet> getCachedMainListing() {
		LinkedList<SudokuTweet> resultList = cachedTweets;
		LinkedList<SudokuTweet> mainListing = processForMainListing(resultList);
		return mainListing;
	}

	/**
	 * Returns the cached Tweets <b>previously</b> retrieved
	 * from Twitter
	 * @return {@link LinkedList} of {@link SudokuTweet}s
	 */
	public List<SudokuTweet> getCachedTweets() {
		return cachedTweets;
	}



	private Node getNodeWithTagName(int index,String name, Node parentNode) {
		NodeList list = parentNode.getChildNodes();
		int count = 0;
		for(int i=0;i<list.getLength();i++) {
			Node n = list.item(i);
			if(n.getNodeName().equalsIgnoreCase(name)) {
				if(index==count
						&& n.getNodeName().equals(name)) {
					return list.item(i);
				}
				count++;
			}
		}
		return null;
	}
	/**
	 * This method retrieves search results via the Twitter API.
	 * @param searchString Text to search for
	 * @return {@link LinkedList} of {@link SudokuTweet}s that were retrieved.
	 * 			 The resulting {@link SudokuTweet}s return null when <tt>getSudokuString</tt>
	 * 			 if no puzzle string was included with the tweet.
	 * @throws HttpException Thrown when a HttpException is caught.
	 * @throws IOException  Thrown when client is not connected to the internet or if twitter.com is unreachable.
	 */
	public synchronized LinkedList<SudokuTweet> getSearchResults(String searchString) throws  HttpException, IOException {
		GetMethod get = null;
		LinkedList<SudokuTweet> resultList;
		if(cachedTweets==null) 	 resultList= new LinkedList<SudokuTweet>();
		else resultList = cachedTweets;
		try {
			StringBuffer sb = new StringBuffer()
			.append("http://search.twitter.com/search.atom?q=")
			.append(URIUtil.encodePath(searchString)) 
			.append("&rpp=").append(rpp)
			.append("&lang=").append(lang)
			.append("&show_user=").append(show_user);

			if(latestId != null) sb.append("&since_id=").append(latestId);

			String url = sb.toString();
			System.out.println(url);
			get  = new GetMethod(url);
			client.executeMethod(get);

			Document doc = builder.parse(get.getResponseBodyAsStream());
			NodeList listEntry = doc.getDocumentElement().getElementsByTagName("entry");

			for(int i=listEntry.getLength()-1; i>=0 ; i--){
				Node n = listEntry.item(i);
				Node idNode = getNodeWithTagName(0, "id", n);
				Node imageNode = getNodeWithTagName(1,"link", n);
				Node msgNode = getNodeWithTagName(0,"title", n);

				String msgBody = msgNode.getFirstChild().getNodeValue().replace('\n', ' ');
				String imageURL = imageNode.getAttributes().getNamedItem("href").getTextContent();
				String id = extractId(idNode.getFirstChild().getNodeValue());
				if(i==0) latestId = id;

				SudokuTweet t = new SudokuTweet();
				t.setId(id);
				t.setProfileIcon(new ImageIcon(new URL(imageURL)));
				fillAuthorMessageSudoku(msgBody, t);

				resultList.push(t);
			}
		} catch (HttpException e) {

			throw e;
		} catch (IOException e) {

			throw e;
		} catch (SAXException e) {
		}	
		finally {
			if(get!=null) get.releaseConnection();
		}
		cachedTweets = resultList;
		return cachedTweets;
	}

	private void init() {
		this.sb = new SudokuBoard();
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
		}
		client = new HttpClient();
		try {
			client.getHostConfiguration().setHost(new URI("http://"+ TWITTER_HOST, true));
		} catch (URIException e) {

		}
	}


	/**
	 * Returns true if the {@link #authenticate(String, String)} method was successful.
	 * Does not check if client is connected to the Internet.
	 * @return true if authenticated.
	 */
	public boolean isAuthenticated() {
		return connected;
	}


	private synchronized LinkedList<SudokuTweet> processForMainListing(
			LinkedList<SudokuTweet> resultList) {

		LinkedList<SudokuTweet> mainListing = new LinkedList<SudokuTweet>();
		if(resultList!= null) { 
			ListIterator<SudokuTweet> itr = resultList.listIterator();

			while(itr.hasNext()) {
				SudokuTweet t = itr.next();
				if(t.getSudokuString() == null) continue;
				sb.clear();
				sb.deserializeBoard(t.getSudokuString());
				if(t.isSolvable() || SudokuSolver.hasUniqueSolution(sb)) {
					t.setSolvable(true);
				}
				else t.setSolvable(false);
			}

			while(itr.hasPrevious()) {
				SudokuTweet t = itr.previous();
				if(!t.isSolvable()) continue;
				if(t.getMessage().contains("@"+twitterName)) {
					t.setMentionsUser(true);
					mainListing.push(t);
					System.out.println(t.getMessage());
				}
			}

			while(itr.hasNext()) {
				SudokuTweet t = itr.next();	  
				if(!t.isSolvable()) continue;
				if(!t.getMessage().contains("@"+twitterName)) {
					t.setMentionsUser(false);
					mainListing.add(t);
				}
			}
		}
		return mainListing;
	}

	/**
	 * Shares a sudoku game in the format understood by other Twitdoku clients:
	 * "[message] #twitdoku [puzzle-string]"
	 * @param message Message to include with the tweet.
	 * @param sudokuString 81-character puzzle string
	 * @throws HttpException Thrown when a HttpException is caught.
	 * @throws IOException  Thrown when client is not connected to the internet or if twitter.com is unreachable.
	 */
	public synchronized void shareSudoku(String message,String sudokuString) throws HttpException, IOException{
		String status = new StringBuffer()
		.append(message)
		.append(" #").append(KEYWORD).append(' ')
		.append(sudokuString).toString();
		updateStatus(status);
	}

	/**
	 * Shares a message on Twitter.
	 * @param text Message content
	 * @throws HttpException Thrown when a HttpException is caught.
	 * @throws IOException  Thrown when client is not connected to the internet or if twitter.com is unreachable.
	 */
	public synchronized void updateStatus(String text) throws HttpException,IOException {
		if(text== null) return;
		NameValuePair paramsPostUpdate[] = {new NameValuePair("status",text)};
		PostMethod postUpdateMethod = new PostMethod(UPDATE_LOCATION);
		postUpdateMethod.setRequestBody(paramsPostUpdate);

		try { // 0
			//	executeAuthenticatedMethod(postUpdateMethod);
			System.out.println(text);
			System.out.println(text.length());
			client.executeMethod(postUpdateMethod);

			if(postUpdateMethod.getStatusCode() !=200) throw new HttpException(postUpdateMethod.getStatusLine().toString());
		} catch (HttpException e) { // 1 
			if(postUpdateMethod.getStatusCode()==401) clearAuthentication(); 
			throw e;
		} catch (IOException e) { // 2
			throw e;
		} finally {
			postUpdateMethod.releaseConnection();
		}
	}



}
