/**
 * Product subclass for grocery items. Has additional nutriScore attribute.
 */
public class Grocery extends Product {
    private char nutriScore = 'X'; //default nutriScore value

    public Grocery(String name, double price, int quantity, char nutriScore) {        //constructors
        super(name, price, quantity);
        this.nutriScore = nutriScore;
    }

    /**
     * Default constructor for test purposes - creates a grocery product with default values.
     */
    public Grocery() {}

    public char getNutriScore() {
        return nutriScore;
    }

    @Override
    public void show() {
        super.show();
        System.out.println(getNutriScore());
    }
}
