import java.util.*;

public class Hangman {
	private String questionsFilename;
	private String question;
	private String category;
	private String clue;
	private int totalTriesAllowed;
	private int numberOfQuestionsAnswered;
	private int numberOfTries;
	private int numberOfQuestions;
	private int answeredQuestion;
	private String triedLetters;
	private String validChars;
	private ArrayList<String> questions;
	private ArrayList<Integer> questionNumbers;
	private TextFile textFile;

	public Hangman(String questionsFilename, int totalTriesAllowed){
		loadQuestions(questionsFilename);

		numberOfQuestionsAnswered = 0;
		numberOfQuestions = textFile.getNumberOfLines();
		answeredQuestion = 0;
		questionNumbers = new ArrayList<Integer>();
		for (int i = 0; i < numberOfQuestions; i++){
			questionNumbers.add(i);
		}
		// shuffle the question numbers so they will be random
		Collections.shuffle(questionNumbers);
		validChars = "abcdefghijklmnopqrstuvwxyz";
		this.totalTriesAllowed = totalTriesAllowed;
		reset();
	}

	public void run(){
		Scanner kb = new Scanner(System.in);
		prepareNextQuestion();
		while (!isGameOver()){
			System.out.println("============================================");
			System.out.printf("Category: %s\n", category);
			System.out.printf("Clue: %s\n", clue);
			System.out.printf("Tries left: %d\n", totalTriesAllowed - numberOfTries);
			printQuestion();
			System.out.print("Guess a letter: ");
			char letter = kb.nextLine().charAt(0);
			if (isLetterInString(letter, validChars)){
				// Try the letter if its correct/incorrect
				switch (tryLetter(letter)) {
					case 0: System.out.println("Letter already tried."); break;
					case 1: System.out.println("Correct!"); break;
					case -1: System.out.println("Incorrect :("); break;
					default: break;
				}
			} else {
				System.out.println("Invalid character. ");
			}
			if (isAnswerCorrect()) {
				System.out.println("Congratulations! You answered that correctly. ");
				System.out.printf("The answer is: %s\n", question);
				answeredQuestion += 1;
				reset();
				prepareNextQuestion();
			}
			if (isGameOver()) {
				System.out.println("Game Over.");
				System.out.printf("The answer is: %s\n", question);
			}
		}
	}

	private void reset(){
		numberOfTries = 0;
		triedLetters = new String();
	}

	public void loadQuestions(String questionsFilename){
		this.questionsFilename = questionsFilename;
		this.textFile = new TextFile(this.questionsFilename);
		this.questions = textFile.getContentAsArray();
	}

	public void prepareNextQuestion(){
		prepareQuestion(questionNumbers.get(answeredQuestion));
	}

	public void prepareQuestion(int questionNumber){
		String questionCategoryClue = textFile.getLine(questionNumber);
		int separator = questionCategoryClue.indexOf(";");
		int separator2 = questionCategoryClue.lastIndexOf(";");
		question = questionCategoryClue.substring(0, separator);
		category = questionCategoryClue.substring(separator + 1, separator2);
		clue = questionCategoryClue.substring(separator2 + 1, questionCategoryClue.length());
	}
	/*
	 * Prints the question while hiding the characters which have not been guessed yet
	 */
	public void printQuestion(){
		for (char letter : question.toCharArray()){
			if (isLetterInString(letter, triedLetters)){
				System.out.print(letter);
			}else{
				System.out.print('x');
			}
		}
		System.out.println();
	}

	/*
	 * Try a letter
	 */
	public int tryLetter(char letter){
		if (numberOfTries < totalTriesAllowed){
			if (isLetterInString(letter, triedLetters)) {
				return 0; // letter has already been tried
			}
			if (isLetterInString(letter, question)){
				triedLetters += letter;
				return 1; // letter is correct
			}
			numberOfTries += 1;
		}
		return -1; // letter is incorrect
	}

	/*
	 * Check if the answer is already correct
	 */
	public boolean isAnswerCorrect(){
		int correct = 0;
		for (char letter : question.toCharArray()){
			if (isLetterInString(letter, triedLetters)){
				correct += 1;
			}
		}
		if (correct == question.length()){
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Check if it is game over
	 */
	public boolean isGameOver(){
		if (numberOfTries >= totalTriesAllowed)
			return true; // lose
		else
			return false; // continue game
	}

	/*
	 * Function to check if a letter is in a String
	 */
	public boolean isLetterInString(char letter, String str){
		for (char c : str.toCharArray()){
			if (c - 65 == letter - 65){
				return true;
			}
		}
		return false;
	}

	public String getQuestion(){
		return question;
	}
	public String getCategory(){
		return category;
	}
	public String getClue(){
		return clue;
	}
}
