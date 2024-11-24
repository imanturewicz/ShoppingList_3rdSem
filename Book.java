public class Book extends Product {
    private String author = "Jo-Mama";

    public Book(String nazwa, double cena, int ilosc, String autor) {
        super(nazwa, cena, ilosc);
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
