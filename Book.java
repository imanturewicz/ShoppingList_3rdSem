public class Book extends Product {
    private String author = "Jo-Mama";

    public Book(String name, double price, int quantity, String author) {
        super(name, price, quantity);
        this.author = author;
    }
    public Book() {}

    public String getAuthor() {
        return author;
    }

    @Override
    public void show() {
        super.show();
        System.out.println(getAuthor());
    }
}
