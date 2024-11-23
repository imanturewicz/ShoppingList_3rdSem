public class Book extends Product {
    private String author = "Jo-Mama";

    public Book(String nazwa, double cena, String autor) {
        super(nazwa, cena);
        author = autor;
    } public Book() {}

    public String getAuthor() {
        return author;
    }

    @Override
    public void show() {
        super.show();
        System.out.println(getAuthor());
    }
}
