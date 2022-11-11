import java.util.Scanner;

public class HiLo {

    static String userPrompt(){
        System.out.print("Hello, What is your name: ");
        Scanner name = new Scanner(System.in);
        String userName = name.nextLine();
        System.out.println("Welcome to HighLow " + userName);

        return userName;
    }

    static int[] createDeck() {
        int[] deck = new int[52];
                      
        // Initialize cards
        for (int i = 0; i < deck.length; i++)
        deck[i] = i;
                 
        // Shuffle the cards
        for (int i = 0; i < deck.length; i++) {
            // Generate an index randomly
            int index = (int)(Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index]; 
            deck[index] = temp;
        }
             
        return deck;
    }

    static void startGame(int[] deck) {
        for (var i=0; i < 52; i++) {
            startTurn(deck[i]);
        }
    }

    static void startTurn(int cardNumber) {

        Scanner reader = new Scanner(System.in);

        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8","9", "10", "Jack", "Queen", "King"};
        int[] intRanks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        String theSuit = suits[cardNumber / 13];
        String theRank = ranks[cardNumber % 13];

        System.out.println("You're current card is a " + theRank + " of " + theSuit);

        Boolean goodAnswer = false;

        char userGuess = 'a';
        
        while (!goodAnswer) {
            System.out.print("\nWhat do you think the next card will be? High (H) Low (L) Or Same (S)? : ");
            userGuess = reader.next().charAt(0);

            if (userGuess == 'H' || userGuess == 'L' || userGuess == 'S') {
                goodAnswer = true;
            }
            else {
                System.out.println("Please enter an acceptible value: H L or S");
            }
        }
        // If the current card is greater than the next
        if (intRanks[cardNumber % 13] < intRanks[(cardNumber + 1) % 13]) {
            if (userGuess == 'H') {
                System.out.println("Correct!");
            }
            else {
                System.out.println("Incorrect, you guessed " + userGuess + " but the correct response was H!");
            }
        }
        // If the current card is less than the next
        else if (intRanks[cardNumber % 13] > intRanks[(cardNumber + 1) % 13]) {
            if (userGuess == 'L') {
                System.out.println("Correct!");
            }
            else {
                System.out.println("Incorrect, you guessed " + userGuess + " but the correct response was L!");
            }
        }
        // If the current card is equal to the next
        else {
            if (userGuess == 'S') {
                System.out.println("Correct!");
            }
            else {
                System.out.println("Incorrect, you guessed " + userGuess + " but the correct response was S!");
            }
        }
    }

    public static void main(String[] args) {

        String name = userPrompt();
        int[] deck = createDeck();

        startGame(deck);

    }
}
          
