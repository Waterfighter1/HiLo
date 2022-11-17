public class Card {
    // Var Bank 
    public String Value;
    public String Suite;
    public int intValue;
    public boolean empty = false;

    // Argument Constructor ( The one called in the createDeck Method)
    public Card(String suite, String value, int iValue) {
        this.Value = value;
        this.Suite = suite;
        this.intValue = iValue;
    }

    // No-Arg constructor
    public Card() {
        this.Value = "None";
        this.Suite = "None";
    }

    // Print the values of the cards
    public void PrintCard() {
        System.out.println("There is a " + Value + " of " + Suite + "s on the table");
    }
}
