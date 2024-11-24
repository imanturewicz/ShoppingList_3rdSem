public class Grocery extends Product {
    private char nutriscore = 'X';

    public Grocery(String nazwa, double cena, int ilosc, char nutri) {
        super(nazwa, cena, ilosc);
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
