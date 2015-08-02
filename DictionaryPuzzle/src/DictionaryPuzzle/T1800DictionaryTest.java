package DictionaryPuzzle;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Test phone number matcher.
 */
public class T1800DictionaryTest extends TestCase {

	@Test
	public void testExampleFromRequirementsDocument(){
		T1800Dictionary phoneDictionary = new T1800Dictionary(new ByteArrayInputStream("Bat\nCat\nFat\ncall\nme".getBytes()));
		String results = phoneDictionary.respondQuery("2255.63\r\n");
		assertEquals("CALL-ME", results);
	}
	
	@Test
	public void testAllowSingleDigit(){
		T1800Dictionary phoneDictionary = new T1800Dictionary(new ByteArrayInputStream("Bat\nCat\nFat\ncall\nme".getBytes()));
	
		// Allows single digit at start of number
		String resultString = phoneDictionary.respondQuery("9-228-228\r\n");
		String[] results=resultString.split(" ");
		assertEquals(4, results.length);
	
	}
	
	@Test
	public void testDisallowMoreThanOneDigit(){
		T1800Dictionary phoneDictionary = new T1800Dictionary(new ByteArrayInputStream("Bat\nCat\nFat\ncall\nme".getBytes()));
	
		// Allows single digit at start of number
		String resultString = phoneDictionary.respondQuery("99-228-228\r\n");
		assertEquals("No result Found",resultString);
	
	}

	
}
