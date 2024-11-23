public class Product {
    private String name = "XXX";
    private double price = 2137;

    public Product(String n, double p) {
        name = n;
        price = p;
    }
    public Product() {}

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public void show() {
        System.out.println(getName());
        System.out.println(getPrice());
    }
}