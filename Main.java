public class Main {
    public static void main(String[] args) {
        Product japko = new Product("Jablko", 3.40);
        Product telewizor = new Product();
        Product kobieta = new Product("sarna", 2.5);
        Grocery grusza = new Grocery("gruszencja", 7.33, 'B');

        japko.show();
        telewizor.show();
        kobieta.show();
        grusza.show();
    }
}
