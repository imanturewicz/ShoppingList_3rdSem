/**
 * Book class is a subclass of Product class. It has an additional author attribute.
 */
public class Book extends Product {
    private String author = "Yo Mama"; //default author :)

    public Book(String name, double price, int quantity, String author) {
        super(name, price, quantity);
        this.author = author;
    }

    /**
     * Default constructor for test purposes - creates a book product with default values.
     */
    public Book() {}

    public String getAuthor() {        //getter
        return author;
    }

    @Override
    public void show() {
        super.show();
        System.out.println(getAuthor());
    }
}
