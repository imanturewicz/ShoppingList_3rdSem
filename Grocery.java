public class Grocery extends Product {
    private char nutriscore;

    public Grocery(String nazwa, double cena, char nutri) {
        super(nazwa, cena);
        nutriscore = nutri;
    }

    public char getNutriscore() {
        return nutriscore;
    }

    @Override
    public void show() {
        super.show();
        System.out.println(getNutriscore());
    }
}
