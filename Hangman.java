import java.util.*;

public class Hangman {
	private String questionsFilename;
	private String question;
	private String category;
	private int numberOfTries;
	private int numberOfQuestions;
	private int[] questionsAlreadyAnswered;
	private String triedLetters;
	private ArrayList<String> questions;
	private TextFile textFile;

	public Hangman(String questionsFilename){
		loadQuestions(questionsFilename);
		prepareRandomQuestion();
		numberOfTries = 0;
		numberOfQuestions = textFile.getNumberOfLines();
		questionsAlreadyAnswered = new int[numberOfQuestions];
		triedLetters = new String();
	}

	public void loadQuestions(String questionsFilename){
		this.questionsFilename = questionsFilename;
		this.textFile = new TextFile(this.questionsFilename);
		this.questions = textFile.getContentAsArray();
	}

	public void prepareRandomQuestion(){
		String questionAndCategory = textFile.getRandomLine();
		int separator = questionAndCategory.indexOf(";");
		question = questionAndCategory.substring(0, separator);
		category = questionAndCategory.substring(separator + 1, questionAndCategory.length());
	}

	/*
	 * Prints the question while hiding the characters which have not been guessed yet
	 */
	public void printQuestion(){
		for (char letter : question.toCharArray()){
			if (letterInString(letter, triedLetters)){
				System.out.print(letter);
			}else{
				System.out.print('X');
			}
		}
		System.out.println();
	}

	/*
	 * Try a letter
	 */
	public int tryLetter(char letter){
		if (letterInString(letter, triedLetters)) {
			return 0; // letter has already been tried
		}
		if (letterInString(letter, question)){
			triedLetters += letter;
			return 1; // letter is correct
		}
		numberOfTries += 1;
		return -1; // letter is incorrect
	}

	/*
	 * Check if the answer is already correct
	 */
	public boolean checkCorrectAnswer(){
		int correct = 0;
		for (char letter : question.toCharArray()){;
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
}
