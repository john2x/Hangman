import java.util.*;
public class Main
{
    static Scanner kb = new Scanner (System.in);
    public static void main(String[] args){
		ArrayList<String> questions = TextFileOperations.placeContentIntoArray("hangman.txt");
		for (String question : questions){
			System.out.println(question);
		}
	}
}

