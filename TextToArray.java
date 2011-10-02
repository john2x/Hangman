import java.util.*;
import java.io.*;

public class TextToArray {
	/* 
	 * Get the content of a text file and place it in an array.
	 */
	public static ArrayList<String> getContent(String filename){
		
		Scanner reader;
		try {
			reader = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filename);
			return null;
		}
		ArrayList<String> ar = new ArrayList<String>();
		while (reader.hasNext()){
			ar.add(reader.nextLine());
		}
		return ar;
	}
}

