// import java.io.*;
import java.util.*;
// import java.math.*;
// import java.text.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.print("How many items? ");
        int howmany = scanner.nextInt();
        
        String category, name, author;
        double price;
        char nutriscore;

        for(int i = 1; i <= howmany; i++) {
            System.out.print("Podaj produkt: ");
            category = scanner.next();
            name = scanner.next();
            price = scanner.nextDouble();
            if(category.equals("Grocery")) {
                nutriscore = scanner.next().charAt(0);
                products.add(new Grocery(name, price, nutriscore));
            }
            if(category.equals("Book")) {
                author = scanner.next();
                products.add(new Book(name, price, author));
            }
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println();
            products.get(i).show();
        }

        scanner.close();

        /*Product kobieta = new Product("sarna", 2.5);
        Grocery gruszka = new Grocery("gruszencja", 7.33, 'B');
        Book lolo = new Book();
        Book brief = new Book("Brief history of time", 20, "Stephen Hawking");

        kobieta.show();
        System.out.println();
        gruszka.show();
        System.out.println();
        lolo.show();
        System.out.println();
        brief.show();*/
    }
}
