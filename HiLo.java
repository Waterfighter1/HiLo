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
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8","9", "10", "Jack", "Queen", "King"};

        String theSuit = suits[cardNumber / 13];
        String theRank = ranks[cardNumber % 13];

        System.out.println("You're current card is a" + theRank + " of " + theSuit);
        System.out.println("What do you think the next card will be? High (H) Low (L) Or Same (S)?");
    }

    public static void main(String[] args) {

        String name = userPrompt();
        int[] deck = createDeck();

        startGame(deck);

    }
}
          
