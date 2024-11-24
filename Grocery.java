public class Grocery extends Product {
    private char nutriscore = 'X';

    public Grocery(String name, double cena, int ilosc, char nutri) {
        super(name, cena, ilosc);
        nutriscore = nutri;
    } public Grocery() {}

    public char getNutriscore() {
        return nutriscore;
    }

    @Override
    public void show() {
        super.show();
        System.out.println(getNutriscore());
    }
}
