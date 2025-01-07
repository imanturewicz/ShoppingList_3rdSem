public class Book extends Product {
    private String author = "Yo Mama";

    public Book(String name, double price, int quantity, String author) {        //constructors
        super(name, price, quantity);
        this.author = author;
    }
    public Book() {}

    public String getAuthor() {        //getter
        return author;
    }

    @Override
    public void show() {        //method outputting the attributes of the product
        super.show();
        System.out.println(getAuthor());
    }
}
