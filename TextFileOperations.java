import java.util.*;
import java.io.*;

public class TextFile{
	private Scanner reader;
	private String filename;
	private int numberOfLines;
	private ArrayList<String> contentAsArray;

	public TextFile(String filename){
		filename = filename;
		contentAsArray = placeContentIntoArray();
		numberOfLines = contentAsArray.size();
	}
	/* 
	 * Get the content of a text file and place it in an array.
	 */
	private ArrayList<String> placeContentIntoArray(){
		contentAsArray = new ArrayList<String>();
		try {
			reader = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filename);
		}
		while (reader.hasNext()){
			contentAsArray.add(reader.nextLine());
		}
		return contentAsArray;
	}

	public String getRandomLine(){
		return contentAsArray.get((int)(Math.random() * numberOfLines));
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

