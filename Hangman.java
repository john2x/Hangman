import java.util.*;

public class Hangman {
	private String questionsFilename;
	private String question;
	private String category;
	private String clue;
	private int totalTriesAllowed;
	private int numberOfTries;
	private int numberOfQuestions;
	private int[] questionsAlreadyAnswered;
	private String triedLetters;
	private ArrayList<String> questions;
	private TextFile textFile;

	public Hangman(String questionsFilename, int totalTriesAllowed){
		loadQuestions(questionsFilename);
		prepareRandomQuestion();
		numberOfTries = 0;
		numberOfQuestions = textFile.getNumberOfLines();
		questionsAlreadyAnswered = new int[numberOfQuestions];
		triedLetters = new String();
		this.totalTriesAllowed = totalTriesAllowed;
	}

	public void loadQuestions(String questionsFilename){
		this.questionsFilename = questionsFilename;
		this.textFile = new TextFile(this.questionsFilename);
		this.questions = textFile.getContentAsArray();
	}

	public void prepareRandomQuestion(){
		String questionCategoryClue = textFile.getRandomLine();
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
			if (letterInString(letter, triedLetters)){
				System.out.print(letter);
			}else{
				System.out.print('*');
			}
		}
		System.out.println();
	}

	/*
	 * Try a letter
	 */
	public int tryLetter(char letter){
		if (numberOfTries < totalTriesAllowed){
			if (letterInString(letter, triedLetters)) {
				return 0; // letter has already been tried
			}
			if (letterInString(letter, question)){
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
	public boolean isGameWin(){
		int correct = 0;
		for (char letter : question.toCharArray()){
			if (letterInString(letter, triedLetters)){
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
		if (numberOfTries >= totalTriesAllowed && !isGameWin() )
			return true;
		else
			return false;
	}

	/*
	 * Function to check if a letter is in a String
	 */
	public boolean letterInString(char letter, String str){
		for (char c : str.toCharArray()){
			if (c == letter){
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
