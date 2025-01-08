/**
 * The Product superclass represents a product in a store.
 */
public class Product {
    //product attributes with sample default values
    private String name = "XXX";
    private double price = -2137;
    private int quantity = 0;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Default constructor for test purposes - creates a product with default values.
     */
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

    /**
     * Outputs the attributes of the product.
     */
    public void show() {
        System.out.println(getName());
        System.out.println(getPrice());
        System.out.println(getQuantity());
    }
}
