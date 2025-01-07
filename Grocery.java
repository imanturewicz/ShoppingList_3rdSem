public class Grocery extends Product {
    private char nutriScore = 'X';

    public Grocery(String name, double price, int quantity, char nutriScore) {        //constructors
        super(name, price, quantity);
        this.nutriScore = nutriScore;
    }
    public Grocery() {}

    public char getNutriScore() {        //getter
        return nutriScore;
    }

    @Override
    public void show() {        //method outputting the attributes of the product
        super.show();
        System.out.println(getNutriScore());
    }
}
