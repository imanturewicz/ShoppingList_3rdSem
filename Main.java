import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Product> products = new PriorityQueue<>((a, b) ->
                Double.compare(a.getPrice()*a.getQuantity(), b.getPrice()*b.getQuantity()));
                //TODO IntelliJ podpowiada, że może być replaced with Comperator.comparingDouble

        System.out.print("Parse the shopping list from memory? [Y/n] ");
        boolean parse = 'Y' == scanner.next().charAt(0);
        if(parse) {
            readProductFromFile(scanner, products);
        }

        System.out.print("Manually add to the shopping list? [Y/n] ");
        boolean manual = 'Y' == scanner.next().charAt(0);
        if(manual) {
            addProductsManually(scanner,products);
        }

        scanner.close();

        String outputFileName = "output.txt";
        writeProductToFile(products,outputFileName);

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
                    parts = line.split("\\s+");
                    category = parts[1];
                    name = parts[2];
                    price = Double.parseDouble(parts[3].replace("$", ""));

                    quantParsing = parts[6].chars().filter(Character::isDigit)
                            .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();

                    quantity = Integer.parseInt(quantParsing);
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
        Product targetProduct = new Product();
        targetProduct.show();

        System.out.print("How many different items? ");
        howMany = scanner.nextInt();

        for(int i = 1; i <= howMany; i++) {
            System.out.print("\nAre you duplicating an item? [Y/n] ");
            isDuplicate = 'Y' == scanner.next().charAt(0);

            if(isDuplicate) {
                System.out.print("\nWhich item? ");
                targetname = scanner.next();
                System.out.print("How many "+targetname+"s are you adding? ");
                numDup = scanner.nextInt();
                for (Product item : products) {
                    if (item.getName().equals(targetname)) {
                        targetProduct = item;
                    }
                }
                products.remove(targetProduct);
                targetProduct.setQuantity(targetProduct.getQuantity() + numDup);
                products.add(targetProduct);
            } else {
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
