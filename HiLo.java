import java.util.Scanner;
import java.util.Random;

public class HiLo {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    // Main Method
    public static void main(String[] args) {
        playingGame();
    } // End Of Main


    /* Playing Game Method
     * This is responsible for looping until the user wants to stop playing the game. It calls startGame,
     * which starts a new Game.
     */
    static void playingGame() {
        // Starting off with playing being true
        Boolean playing = true;

        // Defining Name So We Can Use It Later
        String name = userPrompt();

        while (playing) {
            // Start A Game
            startGame(name);

        

        // Create a Scanner
        Scanner reader = new Scanner(System.in);

        // Defining a goodAnswer boolean 
        Boolean goodAnswer = false;

        // While a good answer hasn't been given yet
        while (!goodAnswer) {
            // Prompt the user if they'd like to continue
            System.out.println("Would you like to continue? Y/N?");

            // Get the value
            char contPlaying = reader.next().charAt(0);

            // If it is a variation of Y
            if (contPlaying == 'Y' || contPlaying == 'y' ) {
                // Good Answer is true. Don't change playing so the while loop loops
                goodAnswer = true;
            }

            // If it is a varation of N
            else if (contPlaying == 'N' || contPlaying == +'n' ) {
                System.out.println("Thank you for playing");
                // Good answer is true. Change playing so the while loop breaks
                goodAnswer = true;
                playing = false;
            }

            // If it isn't Y or N, prompt for a good answer
            else {
                System.out.println("Please enter an acceptible value Y or N");
            }
            } // end of goodAnswer while
        } // end of playing while
    } // end of playingGame method



    /*  User Prompt Method
        This is for getting the user's name
        It prompts for and returns the value of name */
    static String userPrompt(){
        System.out.print("Hello, What is your name: ");
        Scanner name = new Scanner(System.in);
        String userName = name.nextLine();
        System.out.println("Welcome to HighLow " + userName);

        return userName;
    }





    /*  Start Game Method
        This runs for each "GAME that is started"
        Each game has 52 turns (one for each card) so it loops 52 times
        In the parameters, a shuffeled 'deck' of cards is given, as well as the user's name
        After 52 turns, display whether or not the user won */

    static void startGame(String name) {
        // Get a new deck for the new game
        Card[] deck = createDeck();

        // Define user score as 0
        int userScore = 0;

        // Loop 52 times
        for (var i=0; i < 51; i++) {
            // If the user gets that turn correct, it'll return 1. If not, it'll return 0
            userScore += startTurn(deck[i+1], deck[i]);

            Scanner reader = new Scanner(System.in);
            System.out.print("");

            // Create a buffer that requires an input (the input value doesn't matter);
            // this buffer allows the user to read their score
            String buffer = reader.nextLine();

            // Clean the console so it's empty
            cleanConsole();
        }

        // Decide the winner, passing in the final score and name
        decideWin(userScore, name);
    }





    /* Create Deck Method
     * This runs only once, in the main. This is responsible for creating and suffling an array
     * of Card class Objects. There are certain attributes to a Card. Value (numberic), Value (display), and Suite.
     * 
     * At the top, we have these three arrays allocated for so we can reference them when applying a value to the cards.
     */
    static Card[] createDeck() {
        // The attributes of the cards
        String[] suites = {"Heart","Spade","Diamond","Club"};
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        int[] valuesInt = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        // Predefining the cards array
        Card[] cards = new Card[52];

        // Preparing an array for the "Shuffeled" cards
        Card[] shuffeledCards = new Card[52];

        // This creates an array that matches the shuffeled array. This allows us to check it as a switch.
        // If we generate a random number of 2, it'll try to put the next card in two. This allows us to see if we've
        // already put a card in the 2 slot.
        Boolean[] cardsTaken = new Boolean[52];

        // Define all of the cardsTaken as false (so they will be 'empty')
        for (var i=0; i < cardsTaken.length; i++) {
            cardsTaken[i] = false;
        }

        // Increment for each card. So we know exactly where in the cards array to put the card.
        int increment = 0;

        // Note: THIS CARDS ARRAY IS UNSHUFFELED AND ORGANIZED
        // Example: cards{"A of Heart", "2 of Heart", "3 of Heart"}
        // I loops for each suite
        for (var i=0; i < suites.length; i++) {
            // J loops for every value, applying one of every value in each suite
            for (var j=0; j < values.length; j++) {
                // Create a new card with this certain data. 
                cards[increment] = new Card(suites[i], values[j], valuesInt[j]);
                // increase increment
                increment++;
            }
        }

        // Define this so we can use random numbers
        Random rand = new Random();
  
        // For ever card in the cards array
        for (var i=0; i < cards.length; i++) {
            // Define a "good answer" as false.
            boolean goodAnswer = false;

            // While we don't have a good answer ( while we haven't found an empty space for the card )
            // In the shuffeled cards array
            while (!goodAnswer) {
                // Generate the random number
                int cardPos = rand.nextInt(52);

                // If the card isn't taken (the position in the cardsTaken array is false)
                if (!cardsTaken[cardPos]) {

                    // Make the position taken (make the cardsTaken array position true)
                    cardsTaken[cardPos] = true;

                    // Reset good answer (breaking the lop)
                    goodAnswer = true;

                    // Place the card in the shuffeled cards array at the position.
                    shuffeledCards[cardPos] = cards[i];

                } // end of if statement

            } // end of while loop ( waiting for good answer )

        } // end of for each ( for each card )

        // return the shuffeledCards array
        return shuffeledCards;

    } // end of createDeck







    /* Start Turn Method
     * This is called from the startGame method. This method is called 52 times
     * Per Game (once for each card in the game)
     * It is responsible for prompting the user for H / L / S
     * And then checking if it was true
     * If the user was correct for this turn, return 1 and if they were false, return 0
     */
    static int startTurn(Card nextCard, Card currentCard) {
        // Preparing a Scanner
        Scanner reader = new Scanner(System.in);

        // Creating a good answer boolean
        Boolean goodAnswer = false;

        // Placeholder for user's guess ( was getting an error for a possibility of this being null)
        char userGuess = 'a';

        // While we don't have a good answer
        while (!goodAnswer) {

            // Print the current card so the user can read it
            currentCard.PrintCard();

            // Ask the user what the next card will be
            System.out.print("\nWhat do you think the next card will be? High (H) Low (L) Or Same (S)? : ");

            // Get the user's guess from the next Char
            userGuess = reader.next().charAt(0);

            // If the answer is H / L / S
            if (userGuess == 'H' || userGuess == 'L' || userGuess == 'S') {
                // Good answer is true and the loop is broken
                goodAnswer = true;
            }

            // If the answer isn't H / L / S, prompt the user to re-enter their guess
            else {
                System.out.println("Please enter an acceptible value: H L or S");
            }

        } // end of while loop (break on good answer)


        // If the current card ( Card On The Table) has a higher value than the next card
        if (currentCard.intValue < nextCard.intValue) {
            // If the answer was H, they were correct
            if (userGuess == 'H') {
                System.out.println(GREEN + "Correct!" + RESET);
                return 1;
            }

            // Any other answer, they were wrong
            else {
                System.out.println(RED + "Incorrect, you guessed " + userGuess + " but the correct response was H!" + RESET);
                return 0;
            }

        } // end of if

        // If the current card ( Card On The Table) has a lower value than the next card
        else if (currentCard.intValue > nextCard.intValue) {

            // If the user's guess was Lower, they were correct
            if (userGuess == 'L') {
                System.out.println(GREEN + "Correct!" + RESET);
                return 1;
            }

            // If they had any other answer, they were wrong
            else {
                System.out.println(RED + "Incorrect, you guessed " + userGuess + " but the correct response was L!" + RESET);
                return 0;
            }
        } // end of else if

        // If the current card ( Card On The Table) has the same value than the next card
        else {
            // If the user's guess was Same, they were correct
            if (userGuess == 'S') {
                System.out.println(GREEN + "Correct!" + RESET);
                return 1;
            }

            // Any other guess, they were wrong
            else {
                System.out.println(RED +  "Incorrect, you guessed " + userGuess + " but the correct response was S!"  + RESET);
                return 0;
            }
        } // end of else


    } // end of startTurn method

    /* Decide Win Method
        This method's purpose is to take the score and the name, and check 
        to see if they've gotten enough points to win or if they haven't
    */
    static void decideWin(int score, String name) {
        if (score > (52 / 2)) {
            System.out.println(name + " won! Congrats! They got " + score + " cards correct!");
        }
        else {
            System.out.println(name + " lost. Sorry, but you only got " + score + " cards correct.");
        }
    }

    static void cleanConsole() {
         // Cleaning the screen
         System.out.print("\033[H\033[2J");  
         System.out.flush(); 
    }

} // end of public class HiLo
          
