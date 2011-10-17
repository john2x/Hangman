import java.util.*;

public class TextTwist {
	private String questionsFilename;
	private String question;
	private int totalTriesAllowed;
	private int numberOfQuestionsAnswered;
	private int numberOfTries;
	private int numberOfQuestions;
	private int score;
	private String triedLetters;
	private String validChars;
	private ArrayList<String> questions;
	private ArrayList<String> possibleAnswers;
	private ArrayList<String> answers;
	private ArrayList<Integer> questionNumbers;
	private TextFile textFile;

	public TextTwist(String questionsFilename){
		loadQuestions(questionsFilename);

		score = 0;
		numberOfQuestionsAnswered = 0;
		numberOfQuestions = textFile.getNumberOfLines();
		questionNumbers = new ArrayList<Integer>();
		for (int i = 0; i < numberOfQuestions; i++){
			questionNumbers.add(i);
		}
		answers = new ArrayList<String>();
		// shuffle the question numbers so they will be random
		Collections.shuffle(questionNumbers);
		validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		reset();
	}

	public void run(){
		Scanner kb = new Scanner(System.in);
		reset();
		prepareNextQuestion();
		while (!isGameOver()){

			System.out.println("============================================");
			System.out.println("0 to quit");
			System.out.printf("Score: %d\n", score);
			printQuestion();

			System.out.print("\nGuess a word: ");

			String word = kb.nextLine();

			if (word.equals("0")) {
				return;
			}
			switch (tryWord(word)) {
				case 0: System.out.println("Word already tried."); break;
				case 1: System.out.println("Correct!"); 
						answers.add(word); 
						break;
				case -1: System.out.println("Incorrect :("); break;
				default: break;
			}

			if (isAnswerCorrect()) {
				System.out.println("\nCongratulations! You answered that correctly. ");
				reset();
				if (!isGameOver())
					prepareNextQuestion();
				else
					break;
			}
		}
		System.out.println("You answered all questions correctly. ");
		System.out.printf("Score: %d\n", score);
	}

	private void reset(){
		answers = new ArrayList<String>();
	}

	public void loadQuestions(String questionsFilename){
		this.questionsFilename = questionsFilename;
		this.textFile = new TextFile(this.questionsFilename);
		this.questions = textFile.getContentAsArray();
	}

	public void prepareNextQuestion(){
		prepareQuestion(questionNumbers.get(numberOfQuestionsAnswered));
	}

	public void prepareQuestion(int questionNumber){
		String question = textFile.getLine(questionNumber);
		Scanner questionScanner = new Scanner(question);
		questionScanner.useDelimiter(";");
		this.question = questionScanner.next();
		possibleAnswers = new ArrayList<String>();
		while (questionScanner.hasNext()){
			possibleAnswers.add(questionScanner.next());	
		}
	}
	/*
	 * Prints the question while hiding the characters which have not been guessed yet
	 */
	public void printQuestion(){
		System.out.print(jumbleString(question));
		System.out.println();
	}

	/*
	 * Try a word
	 */
	public int tryWord(String str){
		if (answers.contains(str)){
			return 0;
		}
		if (possibleAnswers.contains(str)){
			if (isLongestWord(str)){
				score += 10;
			} else {
				if (str.length() == 3){
					score += 2;
				} else if (str.length() == 4){
					score += 4;
				} else if (str.length() > 5){
					score += 6;
				}
			}

			return 1;
		}
		return -1;
	}

	/*
	 * Check if the answer is already correct
	 */
	public boolean isAnswerCorrect(){
		if (answers.size() == possibleAnswers.size()){
			numberOfQuestionsAnswered += 1;
			return true;
		}
		return false;
	}

	/*
	 * Check if it is game over
	 */
	public boolean isGameOver(){
		if (numberOfQuestionsAnswered == numberOfQuestions)
			return true;
		return false;
	}

	/*
	 * Function to check if a letter is in a String
	 */
	public boolean isLetterInString(char letter, String str){
		if (letter >= 97 && letter <= 122){
			letter -= 32;
		}
		for (char c : str.toUpperCase().toCharArray()){
			if (c == letter){
				return true;
			}
		}
		return false;
	}

	public boolean isLongestWord(String str){
		ArrayList<Integer> wordLengths = new ArrayList<Integer>();
		for (String word : possibleAnswers){
			wordLengths.add(word.length());
		}
		if (str.length() == Collections.max(wordLengths))
			return true;
		return false;
	}

	public String jumbleString(String str){
		ArrayList<Character> temp = new ArrayList<Character>();
		for (char c : str.toCharArray()){
			temp.add(c);
		}
		Collections.shuffle(temp);
		String tempStr = "";
		for (char cc : temp) {
			tempStr += cc;
		}
		return tempStr;
	}

	public String getQuestion(){
		return question;
	}
}

