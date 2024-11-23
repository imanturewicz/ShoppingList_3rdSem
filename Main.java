import java.io.*;
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
            System.out.print("\nPodaj "+i+" produkt:\nCategory: ");
            category = scanner.next();
            System.out.print("Name: ");
            name = scanner.next();
            System.out.print("Price: ");
            price = scanner.nextDouble();
            if(category.equals("Grocery")) {
                System.out.print("Nutriscore: ");
                nutriscore = scanner.next().charAt(0);
                products.add(new Grocery(name, price, nutriscore));
            }
            else if(category.equals("Book")) {
                System.out.print("Author: ");
                author = scanner.next();
                products.add(new Book(name, price, author));
            }
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println();
            products.get(i).show();
        }

        Grocery grocery;
        Book book;
        String filePath = "output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for(int i = 0; i < products.size(); i++) {
                writer.write(i+1+":\n");
                if(products.get(i) instanceof Grocery) {
                    grocery = (Grocery) products.get(i);
                    writer.write(grocery.getName()+" "
                    +grocery.getPrice()+"$ nutriscore:"
                    +grocery.getNutriscore());
                }
                else if(products.get(i) instanceof Book) {
                    book = (Book) products.get(i);
                    writer.write(book.getName()+" "
                    +book.getPrice()+"$ Author:"
                    +book.getAuthor());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }
}
