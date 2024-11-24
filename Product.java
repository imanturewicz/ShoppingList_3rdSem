public class Product {
    private String name = "XXX";
    private double price = 2137;
    private int quantity = 0;

    public Product(String n, double p, int q) {
        name = n;
        price = p;
        quantity = q;
    } public Product() {}

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void show() {
        System.out.println(getName());
        System.out.println(getPrice());
        System.out.println(getQuantity());
    }
}