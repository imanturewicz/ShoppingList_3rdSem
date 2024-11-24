import java.io.*;
import java.util.*;
// import java.math.*;
// import java.text.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // List<Product> products = new ArrayList<>();
        PriorityQueue<Product> products = new PriorityQueue<>((a, b) -> 
        Double.compare(a.getPrice()*a.getQuantity(), b.getPrice()*b.getQuantity()));
        String category, name, author, quantparsing;
        double price;
        char nutriscore;
        int quantity;

        System.out.print("Parse the shopping list from memory? [Y/n] ");
        char parse = scanner.next().charAt(0);
        String[] parts;

        if(parse == 'Y') {
            System.out.print("Provide the input file name (with extension): ");
            String inputFilePath = scanner.next();

            try(BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while((line = reader.readLine()) != null) {
                    if(line.endsWith(".")) {
                        parts = line.split("\\s+");
                        category = parts[1];
                        name = parts[2];
                        price = Double.parseDouble(parts[3].replace("$", ""));

                        quantparsing = parts[6].chars().filter(Character::isDigit)
                        .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();

                        quantity = Integer.parseInt(quantparsing);
                        if (category.equals("Grocery")) {
                            nutriscore = parts[5].charAt(0);
                            products.add(new Grocery(name, price, quantity, nutriscore));
                        } else if (category.equals("Book")) {
                            author = parts[5].replace(".", "");
                            products.add(new Book(name, price, quantity, author));
                        }
                    }
                }
            } catch(IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }

        System.out.print("Manually add to the shopping list? [Y/n] ");
        char manual = scanner.next().charAt(0);

        int n;

        if(manual == 'Y') {
            System.out.print("How many different items? ");
            int howmany = scanner.nextInt(), numdup;
            char czyduplicate;
            String targetname;
            Product targetproduct = new Product();
            
            for(int i = 1; i <= howmany; i++) {
                System.out.print("\nAre you duplicating an item? [Y/n] ");
                czyduplicate = scanner.next().charAt(0);

                if(czyduplicate == 'Y') {
                    System.out.print("\nWhich item? ");
                    targetname = scanner.next();
                    System.out.print("How many "+targetname+"s are you adding? ");
                    numdup = scanner.nextInt();
                    
                    n = products.size();
                    for (Product item : products) {
                        if (item.getName().equals(targetname)) {
                            targetproduct = item;
                        }
                    }
                    products.remove(targetproduct);
                    targetproduct.setQuantity(targetproduct.getQuantity() + numdup);
                    products.add(targetproduct);
                } else if(czyduplicate == 'n') {
                    System.out.print("\nPodaj "+i+" produkt:\nCategory (Grocery/Book): ");
                    category = scanner.next();
                    System.out.print("Name: ");
                    name = scanner.next();
                    System.out.print("Quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Price: ");
                    price = scanner.nextDouble();
                    if(category.equals("Grocery")) {
                        System.out.print("Nutriscore: ");
                        nutriscore = scanner.next().charAt(0);
                        products.add(new Grocery(name, price, quantity, nutriscore));
                    }
                    else if(category.equals("Book")) {
                        System.out.print("Author: ");
                        author = scanner.next();
                        products.add(new Book(name, price, quantity, author));
                    }
                }
            }
        }

        n = products.size();
        Grocery grocery;
        Book book;
        String outputFilePath = "output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for(int i = 0; i < n; i++) {
                writer.write(i+1+": ");
                if(products.peek() instanceof Grocery) {
                    grocery = (Grocery) products.poll();
                    writer.write("Grocery "+grocery.getName()+" "
                    +grocery.getPrice()+"$ nutriscore: "
                    +grocery.getNutriscore()+" x"
                    +grocery.getQuantity()+".");
                }
                else if(products.peek() instanceof Book) {
                    book = (Book) products.poll();
                    writer.write("Book "+book.getName()+" "
                    +book.getPrice()+"$ Author: "
                    +book.getAuthor()+" x"
                    +book.getQuantity()+".");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }
}
