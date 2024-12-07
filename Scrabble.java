/*
 * RUNI version of the Scrabble game.
 */
public class Scrabble {

	// Note 1: "Class variables", like the five class-level variables declared below,
	// are global variables that can be accessed by any function in the class. It is
	// customary to name class variables using capital letters and underline characters.
	// Note 2: If a variable is declared "final", it is treated as a constant value
	// which is initialized once and cannot be changed later.

	// Dictionary file for this Scrabble game
	static final String WORDS_FILE = "dictionary.txt";

	// The "Scrabble value" of each letter in the English alphabet.
	// 'a' is worth 1 point, 'b' is worth 3 points, ..., z is worth 10 points.
	static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
												  1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

	// Number of random letters dealt at each round of this Scrabble game
	static int HAND_SIZE = 10;

	// Maximum number of possible words in this Scrabble game
	static int MAX_NUMBER_OF_WORDS = 100000;

    // The dictionary array (will contain the words from the dictionary file)
	static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];

	// Actual number of words in the dictionary (set by the init function, below)
	static int NUM_OF_WORDS;

	// Populates the DICTIONARY array with the lowercase version of all the words read
	// from the WORDS_FILE, and sets NUM_OF_WORDS to the number of words read from the file.
	public static void init() {
		// Declares the variable in to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the given file. Used for reading words from the file.  
		In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
		while (!in.isEmpty()) {
			// Reads the next "token" from the file. A token is defined as a string of 
			// non-whitespace characters. Whitespace is either space characters, or  
			// end-of-line characters.
			DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
		}
        System.out.println(NUM_OF_WORDS + " words loaded.");
	}

	// Checks if the given word is in the dictionary.
	public static boolean isWordInDictionary(String word) {
		//// Replace the following statement with your code
		return false;
	}
	
	// Returns the Scrabble score of the given word.
	// If the length of the word equals the length of the hand, adds 50 points to the score.
	// If the word includes the sequence "runi", adds 1000 points to the game.
	public static int wordScore(String word) {
		int score = 0;
		char[] englishLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',  
								 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		boolean runiIn = true;                              //cheaking if runi is in 
		String runi = "runi";
		for(int i = 0; i < runi.length(); i ++){
			if(MyString.countChar(word, runi.charAt(i)) == 0){
				runiIn = false;
				break;
			}
		}

		boolean firstTry = false;
		if (HAND_SIZE == word.length()){  //his first try and used all letters
			firstTry = true;
		}

		for(int i = 0; i < word.length(); i ++){
			char wordChar = word.charAt(i);
			int charPlace = 27;                                //just because i have to
			for(int j = 0; j < englishLetters.length; j ++){
				if(englishLetters[j] == wordChar){
					charPlace = j;
					break;
				}
			}
			score += SCRABBLE_LETTER_VALUES[charPlace];
		}

		score *= word.length();
		if (runiIn){
			score += 1000;
		}
		if(firstTry){
			score += 50;
		}

		return score;
	}

	// Creates a random hand of length (HAND_SIZE - 2) and then inserts
	// into it, at random indexes, the letters 'a' and 'e'
	// (these two vowels make it easier for the user to construct words)
	public static String createHand() {                  
		String randomWord = MyString.randomStringOfLetters(HAND_SIZE - 2);
		randomWord = MyString.insertRandomly('e', randomWord);
		randomWord = MyString.insertRandomly('a', randomWord);
		return randomWord;
	}
	
    // Runs a single hand in a Scrabble game. Each time the user enters a valid word:
    // 1. The letters in the word are removed from the hand, which becomes smaller.
    // 2. The user gets the Scrabble points of the entered word.
    // 3. The user is prompted to enter another word, or '.' to end the hand. 
	public static void playHand(String hand) {
		//int n = hand.length();
		int score = 0;
		boolean wantToPlay = true;
		// Declares the variable in to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the keyboard. Used for reading the user's inputs.   
		In in = new In();

		while (hand.length() > 0 && wantToPlay) {
			//// Replace the following break statement with code
			//// that completes the hand playing loop
			System.out.println("Current Hand: " + MyString.spacedString(hand));
			System.out.println("Enter a word, or '.' to finish playing this hand:");
			// Reads the next "token" from the keyboard. A token is defined as a string of 
			// non-whitespace characters. Whitespace is either space characters, or  
			// end-of-line characters.
			String input = in.readString();
			if (input.equals(".")){                             ////want to play?
				wantToPlay = false;
			}

			boolean IsfirstTime = true, isSub = false;
			while(wantToPlay && !isSub){                               //getting a sub-word
				if(!IsfirstTime){
					System.out.println("Current Hand: " + MyString.spacedString(hand));
					System.out.println("Enter a word, or '.' to finish playing this hand:");
					input = in.readString();
				} else {
					IsfirstTime = false;
				}

				if (input.equals(".")){                      //want to play?
					wantToPlay = false;
					break;
				}

				if(MyString.subsetOf(input, hand)){
					isSub = true;
				} else {
					System.out.println("Invalid word. Try again.");         //not a sub-word...
				}
			}

			//cheaking in the DICTIONARY
			boolean inDICTIONARY = false;
			for(int i = 0; i < DICTIONARY.length; i ++){
				if(input.equals(DICTIONARY[i])){
					inDICTIONARY = true;
					break;
				}
			}
			
			if(inDICTIONARY){                                              //not in the DICTIONARY 
				System.out.println("No such word in the dictionary. Try again.");
			} else if (wantToPlay){
				int newScore = wordScore(input);                            //initial score
				
				score += newScore;                                //end of session with sub-word
				System.out.println(input + " earned " + newScore + " points. Score: "
								   + score + " points");
				hand = MyString.remove(hand, input);
			}	
		}
		if (hand.length() == 0) {
	        System.out.println("Ran out of letters. Total score: " + score + " points");
		} else {
			System.out.println("End of hand. Total score: " + score + " points");
		}
	}

	// Plays a Scrabble game. Prompts the user to enter 'n' for playing a new hand, or 'e'
	// to end the game. If the user enters any other input, writes an error message.
	public static void playGame() {
		// Initializes the dictionary
    	init();
		// The variable in is set to represent the stream of characters 
		// coming from the keyboard. Used for getting the user's inputs.  
		In in = new In();
		boolean inputIsE = false;

		while(!inputIsE) {
			System.out.println("Enter n to deal a new hand, or e to end the game:");
			// Gets the user's input, which is all the characters entered by 
			// the user until the user enter the ENTER character.
			//// Replace the following break statement with code
			//// that completes the game playing loop
			
			String input = in.readString();
			boolean firstInput = true, inputIsN = false;
			while(!inputIsN && !inputIsE){
				if(!firstInput){
					input = in.readString();
				} else {
					firstInput = false;
				}

				if(input.equals("e")){
					inputIsE = true;
				} else if(input.equals("n")){
					inputIsN = true;
				}
			}

			if(inputIsN){
				playHand(createHand());
			}

		}
	}

	public static void main(String[] args) {
		//System.out.println("A 1 random Strig word: " + createHand());
		// System.out.println("A 2 random Strig word: " + createHand());
		// System.out.println("A 3 random Strig word: " + createHand());
		// System.out.println("dog (15): " + wordScore("dog"));
		// System.out.println("cat (15): " + wordScore("cat"));
		// System.out.println("quiz (88): " + wordScore("quiz"));
		//playHand(createHand());
		
		////testBuildingTheDictionary();  
		////testScrabbleScore();  
		playGame();
	}

	public static void testBuildingTheDictionary() {
		init();
		// Prints a few words
		for (int i = 0; i < 5; i++) {
			System.out.println(DICTIONARY[i]);
		}
		System.out.println(isWordInDictionary("mango"));
	}
	
	public static void testScrabbleScore() {
		System.out.println(wordScore("bee"));	
		System.out.println(wordScore("babe"));
		System.out.println(wordScore("friendship"));
		System.out.println(wordScore("running"));
	}
	
	public static void testCreateHands() {
		System.out.println(createHand());
		System.out.println(createHand());
		System.out.println(createHand());
	}
	public static void testPlayHands() {
		init();
		//playHand("ocostrza");
		//playHand("arbffip");
		//playHand("aretiin");
	}
}
