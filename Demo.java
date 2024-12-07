/*
 * A simple game that runs as follows. In each turn, the user is prompted to provide an
 * integer, say n. The program then displays n words, drawn randomly from a given dictionary.
 */
public class Demo {

	// Note 1: "Class variables", like the five class-level variables declared below,
	// are global variables that can be accessed by any function in the class. It is
	// customary to name class variables using capital letters and underline characters.
	// Note 2: If a variable is declared "final", it is treated as a constant value
	// which is initialized once and cannot be changed later.

	// Dictionary file for this Scrabble game
	static final String WORDS_FILE = "tinyDictionary.txt";

	// Maximum number of possible words in this Scrabble game
	static int MAX_NUMBER_OF_WORDS = 100000;

    // The dictionary array (will contain the words from the dictionary file)
	static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];

	// Actual number of words in the dictionary (set by the init function, below)
	static int NUM_OF_WORDS;

	// Populates the DICTIONARY array with the lowercase version of all the words read from the file.
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
	
	// Draws and returns a random word from the dictionary.
	public static String getRandomWord() {
		int randomIndex = (int) (NUM_OF_WORDS * Math.random());
		return DICTIONARY[randomIndex];
	}

	// Initializes the dictionary, and plays the game.
	// Assumes (naively) that the user's input is always either 'e',
	// or a sequence of digit characters.	
	public static void playGame() {
		// Initializes the dictionary
    	init();
    	// Declares the variable in to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the keyboard. Used for reading the user's inputs. 
		In in = new In();          ///////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11

		// Plays the game
		while(true) {
			System.out.println("Enter the number of random words you want to see, or e to end the game:");
			// Gets the user's input, which is all the characters entered by 
			// the user until the user enters the ENTER character.
			String input = in.readString();
			if (input.equals("e")) {
				break;
			} else {
				// Here we assume, naively, that the user entered an input consisting only of digits
				int n = Integer.parseInt(input);
				for (int i = 0; i < n; i++) {
					System.out.println(getRandomWord());
				}
			}
		}
	}

	public static void main(String[] args) {		
		playGame();
	}
}