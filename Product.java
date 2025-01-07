public class Product {        //superclass Product
    private String name = "XXX";
    private double price = -2137;
    private int quantity = 0;

    public Product(String name, double price, int quantity) {        //constructors
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product() {}

    public String getName() {        //getters
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {        ///setters
        this.quantity = quantity;
    }

    public void show() {        //method outputting the attributes of the product
        System.out.println(getName());
        System.out.println(getPrice());
        System.out.println(getQuantity());
    }
}
