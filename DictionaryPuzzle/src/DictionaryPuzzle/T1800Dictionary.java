/**
 * 
 */
package DictionaryPuzzle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @author ankita
 *
 */
public class T1800Dictionary extends StandardDictionary{

	public static HashMap<String, ArrayList<String>> dictionary = new LinkedHashMap<String, ArrayList<String>>();
	
	/*
	 * Make dictionary with file
	 */
	public T1800Dictionary(File file){
		super(file);
	}
	
	/*
	 * Make dictionary with input stream
	 */
	public T1800Dictionary(InputStream in){
		super(in);
	}
	 
	
	/**
	 * @param query
	 * @return
	 * @throws Exception
	 * returns the wordQueue corresponding to the given Sequence
	 */
	private static ArrayList<String> getWordQueue(String query) throws Exception {
		if (!dictionary.containsKey(query)) {
			ArrayList<String> response = new ArrayList<String>();
			String replacedString = new String();
			Set<String> Keys = dictionary.keySet();
			for (String eachKey : Keys) {
			
				/*
				 * If substring is matched in dictionary key
				 * replace part of string with the matched one and again find for for the rest
				 * Add - at the end to separate words
				 */
				if (query.contains(eachKey)) {
					for (String dictionaryKey : dictionary.get(eachKey)) {
						replacedString = query.replaceFirst(eachKey, dictionaryKey+"-");
						response.add(replacedString);
					}
				}
			}
	
			//return null response in case queue is empty
			if(response.isEmpty())
				return null;
			
			return response;
		}
		
		//when exact string is matched
		return dictionary.get(query);
	}

	
	/**
	 * @param query
	 * @return
	 * @throws Exception
	 *             converts the digit sequence input by the user into words and
	 *             returns it
	 */
	public static ArrayList<String> getResponse(String query, ArrayList<String> processedRecords) {
		/*
		 * Array to check which records are processed
		 * Processed records will not be processed again
		 */
		processedRecords.add(query);
		
		/*
		 * Call Word queue to get all the word options of input number
		 */
		ArrayList<String> WordQueue = null;
		try {
			WordQueue = getWordQueue(query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		ArrayList<String> response = new ArrayList<String>();
		/*
		 * if no key found return string itself
		 * Otherwise find the matching word for each substring of string by using recursion
		 */
		if(WordQueue == null){
			response.add(query);
		}else{
			for(int i = 0; i < WordQueue.size(); i++) {  
					if(WordQueue.get(i) == query || processedRecords.contains(WordQueue.get(i))){
						response.add(WordQueue.get(i)); 
					}
					else{
						response.addAll(getResponse(WordQueue.get(i),processedRecords));
		
					}
				}
		}
		return response;
	}
	
	/**
	 * @param queries
	 * @return response for the query
	 *  this method is only used when this code is run.
	 *  If result string contains any word which should be excluded then that word will not be added in response
	 */
	public String respondQuery(String query) {
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<String> processedRecords = new ArrayList<String>();
		StringBuilder finalResult = new StringBuilder();
		String[] excludeArray = new String[] {"22","23","24","25","26","27","28","29",
												"32","33","34","35","36","37","38","39",
												"42","43","44","45","46","47","48","49",
												"52","53","54","55","56","57","58","59",
												"62","63","64","65","66","67","68","69",
												"72","73","74","75","76","77","78","79",
												"82","83","84","85","86","87","88","89",
												"92","93","94","95","96","97","98","99"};
		
		/*
		 * Get all the possible matching words for the given string
		 * Format the output as per requirement
		 */
		response = getResponse(query,processedRecords);

		//Converting ArrayList to HashSet to remove duplicates
		HashSet<String> listToSet = new HashSet<String>(response);
		      
		//Creating Arraylist without duplicate values
		ArrayList<String> listWithoutDuplicates = new ArrayList<String>(listToSet);

		//Formatting of output
		for (String eachQuery : listWithoutDuplicates) {
			if(StringUtils.indexOfAny(eachQuery, excludeArray) == -1){
				eachQuery = eachQuery.replaceAll("[^A-Za-z0-9]", "-");
				eachQuery = eachQuery.toUpperCase().replaceAll("-+", "-");
				eachQuery = StringUtils.stripEnd(eachQuery," ");
				eachQuery = StringUtils.stripEnd(eachQuery,"-");
				finalResult.append(eachQuery);
				finalResult.append(" ");
			}
		}

		//In case if no result found
		if(finalResult.toString().isEmpty())
			return "No result Found";
		
		return StringUtils.stripEnd(finalResult.toString()," ");
	} 
	
	/**
	 * @param alphabet
	 * @return digit mapped to the corresponding character
	 */
	public static char getDigit(char alphabet) {
		if (alphabet >= '0' && alphabet <= '9') {
			return alphabet;
		}
		switch (alphabet) {
		case 'a':
		case 'b':
		case 'c':
			return '2';
		case 'd':
		case 'e':
		case 'f':
			return '3';
		case 'g':
		case 'h':
		case 'i':
			return '4';
		case 'j':
		case 'k':
		case 'l':
			return '5';
		case 'm':
		case 'n':
		case 'o':
			return '6';
		case 'p':
		case 'q':
		case 'r':
		case 's':
			return '7';
		case 't':
		case 'u':
		case 'v':
			return '8';
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return '9';
		default:
			return '1';
		}
	}
	
	/** 
	 * @param word
	 * @returns an equivalent digit sequence to the word
	 */
	public static String generateSeq(String word) {
		StringBuilder sequence = new StringBuilder();
		for (char c : word.toCharArray()) 
			sequence.append(getDigit(c));
		return sequence.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see DictionaryPuzzle.StandardDictionary#makeDictionary(java.io.File)
	 */
	public void makeDictionary(File file) {
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			makeDictionary(in);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Load each usable word from a dictionary file into a dictionary.
	 *
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public void makeDictionary(InputStream in) {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String sequence;
			while ((strLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(strLine);
				while(st.hasMoreTokens()) {
					String word = st.nextToken();
					sequence = generateSeq(word.toLowerCase());
					insert(sequence, word.toLowerCase());
				}			
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	/**
	 * @param sequence
	 * @param word
	 *            inserts the sequence into the dictionary if it is not there
	 *            already
	 */
	public static void insert(String sequence, String word) {
		if (dictionary.containsKey(sequence)) {

			ArrayList<String> wordQueue = dictionary.get(sequence);
			if (wordQueue.contains(new String(word))) {
				///do nothing
			} else {
				String ne = new String(word);
				wordQueue.add(ne);
			}
		} else {
			ArrayList<String> wordQueue = new ArrayList<String>();
			wordQueue.add(new String(word));
			dictionary.put(sequence, (ArrayList<String>) wordQueue);
		}
	}
}
