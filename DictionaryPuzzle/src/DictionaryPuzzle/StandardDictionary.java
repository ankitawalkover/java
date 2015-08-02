/**
 * 
 */
package DictionaryPuzzle;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Queue;
import java.io.File;
import java.io.InputStream;

/**
 * @author ankita
 *
 */
public abstract class StandardDictionary {

	/**
	 * dictionary is the hash table having the digit seq mapped to words the
	 * digit sequence is the key and the word tree is the value
	 */
	public static HashMap<String, Queue<String>> dictionary = new LinkedHashMap<String, Queue<String>>();
	
	/**
	 * dictionary is constructed.
	 */
	public StandardDictionary(File file) {
		makeDictionary(file);
	}
	
	public StandardDictionary(InputStream in) {
		makeDictionary(in);
	}
	
	public abstract void makeDictionary(File file);
	
	public abstract void makeDictionary(InputStream in);
}
