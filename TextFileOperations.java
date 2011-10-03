import java.util.*;
import java.io.*;

public class TextFileOperations {
	/* 
	 * Get the content of a text file and place it in an array.
	 */
	public static ArrayList<String> placeContentIntoArray(String filename){
		
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

	public static String getRandomLine(String filename){
		ArrayList<String> lines = placeContentIntoArray(filename);
		return lines.get((int)(Math.random() * lines.size()));
	}

	public static void printAll(String filename){
		ArrayList<String> lines = placeContentIntoArray(filename);
		for (String line : lines){
			System.out.println(line);
		}
	}
}

