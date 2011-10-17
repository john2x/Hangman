import java.util.*;
public class Main
{
    static Scanner kb = new Scanner (System.in);
	static Hangman hangman;
	static TextTwist texttwist;
    public static void main(String[] args){
		char choice = ' ';

		while (choice != '0'){
			System.out.println("\nCHOOSE A GAME");
			System.out.println("---------------------");
			System.out.println("1. Hangman ");
			System.out.println("2. Text Twist ");
			System.out.println("0. Exit ");
			System.out.println("---------------------");
			System.out.print("Enter your choice: ");
			choice = kb.next().charAt(0);
			switch (choice){
				case '1': 
					hangman = new Hangman("hangman.txt", 4);
					hangman.run();
					break;
				case '2':
					texttwist = new TextTwist("texttwist.txt");
					texttwist.run();
					break;
				case '0':
					System.out.println("Thank you for playing.");
					break;
				default:
					System.out.println("Invalid choice. ");
			}
		}
	}

}

