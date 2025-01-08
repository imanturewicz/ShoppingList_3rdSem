import java.io.*;
import java.util.*;

/**
 * The Main class is the entry point of the program.
 * It allows the user to create a shopping list made of Products and its subclasses based on manual input or by parsing the file.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //declaration of the queue for the products
        PriorityQueue<Product> products = new PriorityQueue<>((a, b) ->
                Double.compare(a.getPrice()*a.getQuantity(), b.getPrice()*b.getQuantity()));

        //optional uploading an existing shopping list
        System.out.print("Parse the shopping list from internal file? [Y/n] ");
        boolean parse = 'Y' == scanner.next().charAt(0);
        if(parse) {
            readProductFromFile(scanner, products);
        }

        //optional adding to the shopping list from the terminal
        System.out.print("Manually add to the shopping list? [Y/n] ");
        boolean manual = 'Y' == scanner.next().charAt(0);
        if(manual) {
            addProductsManually(scanner,products);
        }

        scanner.close();

        String outputFileName = "output.txt";
        writeProductToFile(products,outputFileName); //outputting shopping list
    }


    private static void readProductFromFile(Scanner scanner, PriorityQueue<Product> products){
        String[] parts;
        String category, name, author, quantParsing;
        double price;
        int quantity;
        char nutriScore;

        System.out.print("Provide the input file name (with extension): ");
        String inputFilePath = scanner.next();

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                if(line.endsWith(".")) {
                    //splitting the uploaded list's line into several properties
                    parts = line.split("\\s+");
                    category = parts[1];
                    name = parts[2];
                    price = Double.parseDouble(parts[3].replace("$", ""));

                    quantParsing = parts[6].chars().filter(Character::isDigit)
                            .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString(); //helper for obtaining the "quantity"
                    quantity = Integer.parseInt(quantParsing);
                    //adding the parsed object to the queue (list)
                    if (category.equals("Grocery")) {
                        nutriScore = parts[5].charAt(0);
                        products.add(new Grocery(name, price, quantity, nutriScore));
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

    private static void addProductsManually(Scanner scanner, PriorityQueue<Product> products){
        String category, name, author, targetname;
        double price;
        char nutriScore;
        boolean isDuplicate;
        int quantity, howMany, numDup;

        System.out.print("How many different items? ");
        howMany = scanner.nextInt();

        Product targetProduct = new Product();
        for(int i = 1; i <= howMany; i++) {
            System.out.print("\nAre you duplicating an item? [Y/n] ");
            isDuplicate = 'Y' == scanner.next().charAt(0);

            //this option comes useful when the user wants to add the products that already exist on the list
            if(isDuplicate) {
                System.out.print("\nWhich item? ");
                targetname = scanner.next();
                System.out.print("How many "+targetname+"s are you adding? ");
                numDup = scanner.nextInt();
                //searching for the given product in the list
                for (Product item : products) {
                    if (item.getName().equals(targetname)) {
                        targetProduct = item;
                    }
                }
                products.remove(targetProduct);
                targetProduct.setQuantity(targetProduct.getQuantity() + numDup);
                products.add(targetProduct); //adding the product with increased quantity
            } else {
                System.out.print("\nPodaj "+i+" produkt:\nCategory (Grocery/Book): ");
                category = scanner.next(); //inputting the properties of the product
                System.out.print("Name: ");
                name = scanner.next();
                System.out.print("Quantity: ");
                quantity = scanner.nextInt();
                System.out.print("Price: ");
                price = scanner.nextDouble();
                if(category.equals("Grocery")) {
                    System.out.print("Nutriscore: ");
                    nutriScore = scanner.next().charAt(0);
                    products.add(new Grocery(name, price, quantity, nutriScore));
                }
                else if(category.equals("Book")) {
                    System.out.print("Author: ");
                    author = scanner.next();
                    products.add(new Book(name, price, quantity, author));
                }
            }
        }
    }


    private static void writeProductToFile(PriorityQueue<Product> products, String outputFileName){
        int numberOfProducts = products.size();
        Grocery grocery;
        Book book;
        //saving the shopping list into the provided file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for(int i = 0; i < numberOfProducts; i++) {
                writer.write(i+1+": ");
                if(products.peek() instanceof Grocery) {
                    grocery = (Grocery) products.poll();
                    writer.write("Grocery "+grocery.getName()+" "
                            +grocery.getPrice()+"$ nutriscore: "
                            +grocery.getNutriScore()+" x"
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

    }
}
