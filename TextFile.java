import java.util.*;
import java.io.*;

public class TextFile{
	private Scanner reader;
	private String filename;
	private int numberOfLines;
	private ArrayList<String> contentAsArray;

	public TextFile(String filename){
		this.filename = filename;
		contentAsArray = placeContentIntoArray();
		numberOfLines = contentAsArray.size();
	}
	/* 
	 * Get the content of a text file and place it in an array.
	 */
	private ArrayList<String> placeContentIntoArray(){
		ArrayList<String> contents = new ArrayList<String>();
		try {
			reader = new Scanner(new File(this.filename));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filename);
			return null;
		}
		while (reader.hasNext()){
			contents.add(reader.nextLine());
		}
		return contents;
	}

	public String getRandomLine(){
		return contentAsArray.get((int)(Math.random() * numberOfLines));
	}

	public String getLine(int lineNumber){
		return contentAsArray.get(lineNumber);
	}

	public int getNumberOfLines(){
		return numberOfLines;
	}

	public ArrayList<String> getContentAsArray(){
		return contentAsArray;
	}

	public void printAll(){
		for (String line : contentAsArray){
			System.out.println(line);
		}
	}
}

