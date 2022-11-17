import java.util.Scanner;



import java.util.Random;

public class HiLo {

    public static int score = 0;

    static char gameRules(){
        System.out.println("Welcome to the High/Low Game!\n");
        System.out.println(" Here are the Rules: ");
        System.out.println("The Program will give you a card ");
        System.out.println(" You have to guess between High(H), Low(L) or Same(S)");
        System.out.println("Getting H or L correct will earn 1 point");
        System.out.println("While getting S correct will earn 2 points");
        System.out.println("The program continue until you earn 30 points or until the deck is gone");
        System.out.println("Would you like to play?");
        System.out.println("Choose Yes(Y) or No(N)");

        char userReply = 'a';
        Scanner input = new Scanner(System.in);
       userReply = input.next().charAt(0);

       if ( userReply == 'Y'){
        
        String name = userPrompt();
        Card[] deck = createDeck();
        startGame(deck);
    }
    else if ( userReply == 'N'){
System.out.println("Have a Nice day!");
   System.exit(0);
    }
    else{
    System.out.println("Please Enter an Appropriate Response");
    gameRules();
}   
return userReply;
    }

    

    static String userPrompt(){
        
        System.out.print("What is your name: ");
        Scanner inputs = new Scanner(System.in);
        String userName = inputs.nextLine();
        System.out.println("Now, Let's start guessing " + userName +"!");

        return userName;
    }



    static Card[] createDeck() {
        String[] suites = {"Heart","Spade","Diamond","Club"};
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        int[] valuesInt = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        Card[] cards = new Card[52];
        Card[] shuffeledCards = new Card[52];
        Boolean[] cardsTaken = new Boolean[52];

        for (var i=0; i < cardsTaken.length; i++) {
            cardsTaken[i] = false;
        }

        int increment = 0;

        for (var i=0; i < suites.length; i++) {
            for (var j=0; j < values.length; j++) {
                cards[increment] = new Card(suites[i], values[j], valuesInt[j]);
                increment++;
            }
        }

        Random rand = new Random();
  
        // Generate random integers in range 0 to 999

        for (var i=0; i < cards.length; i++) {
            boolean goodAnswer = false;
            while (!goodAnswer) {
                int rand_int1 = rand.nextInt(52);

                if (!cardsTaken[rand_int1]) {
                    cardsTaken[rand_int1] = true;
                    goodAnswer = true;
                    shuffeledCards[rand_int1] = cards[i];
                }
            }
        }



        return shuffeledCards;
    }

    static void startGame(Card[] deck) {
        for (var i=0; i < 52; i++) {
            startTurn(deck[i+1], deck[i]);
        }
        
    }

    static void startTurn(Card nextCard, Card currentCard) {

        Scanner reader = new Scanner(System.in);

        Boolean goodAnswer = false;

        char userGuess = 'a';

        while (!goodAnswer) {

            currentCard.PrintCard();

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
        if (currentCard.intValue < nextCard.intValue) {
            if (userGuess == 'H') {
                System.out.println("Correct!");
               
               score++;
               System.out.println(score);
            }
            else {
                System.out.println("Incorrect, you guessed " + userGuess + " but the correct response was H!");
               
            }
        }
        // If the current card is less than the next
        else if (currentCard.intValue > nextCard.intValue) {
            if (userGuess == 'L') {
                System.out.println("Correct!");
                score++;
                System.out.println(score);
                
            }
            else {
                System.out.println("Incorrect, you guessed " + userGuess + " but the correct response was L!");
              
            }
        }
        // If the current card is equal to the next
        else {
            if (userGuess == 'S') {
                System.out.println("Correct!");
                score= score + 2 ;
                System.out.println(score);
            }
            else {
                System.out.println("Incorrect, you guessed " + userGuess + " but the correct response was S!");
            }
        }
    }











    public static void main(String[] args) {


        gameRules();


        String name = userPrompt();
        Card[] deck = createDeck();




        startGame(deck);

    }
}
          
