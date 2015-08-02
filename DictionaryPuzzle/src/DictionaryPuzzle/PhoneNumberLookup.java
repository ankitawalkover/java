package DictionaryPuzzle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PhoneNumberLookup {

	public static void main(String[] arg) throws IOException{
		
		/*
		 * Make a 1800 Dictionary and search for word for the given phone number
		 */
		if(arg.length == 2 && !arg[0].equals("-d")) {
			fail("Please specify a dictionary file.");
		}

		if(arg.length == 3 && !arg[0].equals("-d") && !arg[1].equals("-d")) {
			fail("Please specify a dictionary file.");
		}

		/*
		 * Handle different arguments passed
		 * dictionary file will be passed with -d option
		 * If inputs are passed in file, it will be stored in data
		 */
		File dictionary = null;
		File data = null;
		
		if(arg.length == 2) {
			dictionary = new File(arg[1]);
		} else if(arg.length == 3) {
			if(arg[0].equals("-d")) {
				dictionary = new File(arg[1]);
				data = new File(arg[2]);
			} else {
				dictionary = new File(arg[2]);
				data = new File(arg[0]);
			}
		}
		
		/*
		 * Default Dictionary
		 */
		if(dictionary == null) {
			dictionary = new File("data/dict.txt");
		}
		
		
			
		/*
		 * Data is the inputs read from file
		 * If no file passed, then will read form STDIN
		 */
		if(data != null) {
			try (InputStream in = new FileInputStream(data)) {
				process(dictionary,in);
			}
		} else {
			process(dictionary,System.in);
		}
		
		
	}
	
	/*
	 * Process the input based on the dictionary specified
	 * Makes a new dictionary using constructor of T1800Dictionary
	 * To get response respondQuery function is called
	 */
	public static void process(File dictionary, InputStream in) throws IOException {

		T1800Dictionary phoneDictionary = new T1800Dictionary(dictionary);
		String response = null;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while((line = br.readLine()) != null) {
				response = phoneDictionary.respondQuery(line);
				System.out.println(response);
			}
		}


	}
	
	/*
	 * To handle fail cases
	 */
	private static void fail(String error) {
		System.err.println(error);
		System.exit(1);
	}
	
}
