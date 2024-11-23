import java.io.*;
import java.util.*;
// import java.math.*;
// import java.text.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        String category, name, author;
        double price;
        char nutriscore;

        System.out.print("Parse the shopping list from memory? [Y/n] ");
        char parse = scanner.next().charAt(0);
        String[] parts;

        if(parse == 'Y') {
            System.out.print("Provide the input file name (with extension): ");
            String inputFilePath = scanner.next();

            try(BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while((line = reader.readLine()) != null) {
                    // System.out.println("Reading line: " + line);
                    if(line.endsWith(".")) {
                        parts = line.split("\\s+");
                        category = parts[1];
                        name = parts[2];
                        price = Double.parseDouble(parts[3].replace("$", ""));
                        if (category.equals("Grocery")) {
                            nutriscore = parts[5].charAt(0);
                            products.add(new Grocery(name, price, nutriscore));
                        } else if (category.equals("Book")) {
                            author = parts[5].replace(".", "");
                            products.add(new Book(name, price, author));
                        }
                    }
                }
            } catch(IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }

        System.out.print("Manually add to the shopping list? [Y/n] ");
        char manual = scanner.next().charAt(0);

        if(manual == 'Y') {
            System.out.print("How many items? ");
            int howmany = scanner.nextInt();

            for(int i = 1; i <= howmany; i++) {
                System.out.print("\nPodaj "+i+" produkt:\nCategory (Grocery/Book): ");
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
        }

        // for (int i = 0; i < products.size(); i++) {
        //     System.out.println();                                    //wyświetlanie zawartości listy w konsoli
        //     products.get(i).show();
        // }

        Grocery grocery;
        Book book;
        String outputFilePath = "output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for(int i = 0; i < products.size(); i++) {
                writer.write(i+1+": ");
                if(products.get(i) instanceof Grocery) {
                    grocery = (Grocery) products.get(i);
                    writer.write("Grocery "+grocery.getName()+" "
                    +grocery.getPrice()+"$ nutriscore: "
                    +grocery.getNutriscore()+".");
                }
                else if(products.get(i) instanceof Book) {
                    book = (Book) products.get(i);
                    writer.write("Book "+book.getName()+" "
                    +book.getPrice()+"$ Author: "
                    +book.getAuthor()+".");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }
}
