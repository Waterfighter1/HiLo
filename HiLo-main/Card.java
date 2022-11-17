public class Card {
    public String Value;
    public String Suite;
    public int intValue;
    public boolean empty = false;


    public Card(String suite, String value, int iValue) {
        this.Value = value;
        this.Suite = suite;
        this.intValue = iValue;
    }

    public Card() {
        this.Value = "None";
        this.Suite = "None";
    }


    public void PrintCard() {
        System.out.println("There is a " + Value + " of " + Suite + "s on the table");
    }
}
