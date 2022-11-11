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

    public static void main(String[] args) {

        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8","9", "10", "Jack", "Queen", "King"};

        String name = userPrompt();
        int[] deck = createDeck();

         // Display the first four cards
         for (int i = 0; i < 4; i++) {
            String suit = suits[deck[i] / 13];
            String rank = ranks[deck[i] % 13];
            System.out.println("Card number " + deck[i] + ": " + rank + " of " + suit);
        }

        
    }
}
          
