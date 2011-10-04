import java.util.*;
public class Main
{
    static Scanner kb = new Scanner (System.in);
	static Hangman hangman;
    public static void main(String[] args){
		hangman = new Hangman("hangman.txt", 4);
		System.out.println(hangman.getQuestion());
		System.out.println(hangman.getCategory());
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('x'));
		System.out.println(hangman.tryLetter('q'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('u'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('e'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('s'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('t'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('i'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('o'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('n'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('h'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('r'));
		hangman.printQuestion();
		System.out.println(hangman.tryLetter('w'));
		hangman.printQuestion();
		if (hangman.isGameWin()) System.out.println("Correct!");
	}

}

