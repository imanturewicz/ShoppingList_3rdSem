public class Product {
    private String name = "XXX";
    private double price = 2137;
    private int quantity = 0;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {}

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void show() {
        System.out.println(getName());
        System.out.println(getPrice());
        System.out.println(getQuantity());
    }
}