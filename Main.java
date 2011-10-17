import java.util.*;
public class Main
{
    static Scanner kb = new Scanner (System.in);
	static Hangman hangman;
	static TextTwist texttwist;
    public static void main(String[] args){
		texttwist = new TextTwist("texttwist.txt");
		texttwist.run();
		//hangman = new Hangman("hangman.txt", 4);
		//hangman.run();
	}

}

