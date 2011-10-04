import java.util.*;
public class Main
{
    static Scanner kb = new Scanner (System.in);
	static Hangman hangman;
    public static void main(String[] args){
		hangman = new Hangman("hangman.txt", 4);
		hangman.run();
	}

}

