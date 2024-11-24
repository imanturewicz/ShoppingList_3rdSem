public class Grocery extends Product {
    private char nutriScore = 'X';

    public Grocery(String name, double price, int quantity, char nutriScore) {
        super(name, price, quantity);
        this.nutriScore = nutriScore;
    }
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
