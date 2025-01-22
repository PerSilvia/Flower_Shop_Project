import java.util.Scanner;

public class FlowerShopMenu2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) { // Display the menu
            DisplayMenu();
            int option = GetUserChoice(scanner); // Get user choice

            if (option == 1) { // Process user choice
                OrderBouquetAndGetPrice(scanner);
            } else if (option == 2) {
                DisplayStatistics();
            } else if (option == 3) {
                running = false;
                System.out.println("Exiting the program.");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void DisplayMenu() { // Function to display the menu
        System.out.println("Flower shop menu:");
        System.out.println("1. Order a bouquet and get the price.");
        System.out.println("2. Display statistics.");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");
    }

    public static int GetUserChoice(Scanner scanner) { // Function to get user choice
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    public static void OrderBouquetAndGetPrice(Scanner scanner) { // Function to order a bouquet and get the price
        try {
            System.out.println("Enter flower type (Rose, Lily, Carnations, Daffodil, Gerbera, Chrysanthemum, Assorted):");
            String flowerInput = scanner.nextLine().toUpperCase();
            Flower flower = Flower.valueOf(flowerInput);

            System.out.println("Enter color (White, Red, Pink, Yellow, Blue, Assorted):");
            String colorInput = scanner.nextLine().toUpperCase();
            Color color = Color.valueOf(colorInput);

            System.out.println("Enter bouquet size (Small, Medium, Large):");
            String sizeInput = scanner.nextLine().toUpperCase();
            Size size = Size.valueOf(sizeInput);

            // Calculate price based on flower, color, and size
            double price = calculatePrice(flower, color, size);

            // Record the sale
            recordSale(flower, color, size, price);
            System.out.printf("A bouquet of %s size, %s flower, %s color has been ordered at £%.2f.\n", size, flower, color, price);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please make sure you enter valid values.");
        }
    }

    public static void DisplayStatistics() { // Function to display statistics
        displayStatistics();
    }

    // Enum types with markup values
    enum Flower {
        ROSE(1.2), LILY(1.3), CARNATIONS(1.0), DAFFODIL(1.0),
        GERBERA(1.1), CHRYSANTHEMUM(1.1), ASSORTED(0.8);

        private final double markup;

        Flower(double markup) {
            this.markup = markup;
        }

        public double getMarkup() {
            return markup;
        }
    }

    enum Color {
        WHITE(1.3), RED(1.2), PINK(1.1), YELLOW(1.1),
        BLUE(1.2), ASSORTED(1.0);

        private final double markup;

        Color(double markup) {
            this.markup = markup;
        }

        public double getMarkup() {
            return markup;
        }
    }

    enum Size {
        SMALL(5.5), MEDIUM(7.5), LARGE(9.5);

        private final double markup;

        Size(double markup) {
            this.markup = markup;
        }

        public double getMarkup() {
            return markup;
        }
    }

    // Static arrays and variables for statistics
    private static int[] flowerFreq = new int[Flower.values().length];
    private static int[] colorFreq = new int[Color.values().length];
    private static int[] sizeFreq = new int[Size.values().length];
    private static int totalCount = 0;
    private static double totalPrice = 0;
    private static double minPrice = Double.MAX_VALUE;
    private static double maxPrice = Double.MIN_VALUE;

    // Method to calculate price based on flower, color, and size
    private static double calculatePrice(Flower flower, Color color, Size size) {
        double flowerMarkup = flower.getMarkup();
        double colorMarkup = color.getMarkup();
        double sizeMarkup = size.getMarkup();
        return (flowerMarkup + colorMarkup) * sizeMarkup;
    }

    // Method to record a sale
    private static void recordSale(Flower flower, Color color, Size size, double price) {
        flowerFreq[flower.ordinal()]++;
        colorFreq[color.ordinal()]++;
        sizeFreq[size.ordinal()]++;
        totalCount++;
        totalPrice += price;
        if (price < minPrice) minPrice = price;
        if (price > maxPrice) maxPrice = price;
    }

    // Method to display statistics
    private static void displayStatistics() {
        double averagePrice = totalCount > 0 ? totalPrice / totalCount : 0;
        double rangeOfPrice = totalCount > 0 ? maxPrice - minPrice : 0;
        System.out.println("\nStatistics for bouquets:");
        System.out.printf("Minimum price: £%.2f\n", minPrice);
        System.out.printf("Maximum price: £%.2f\n", maxPrice);
        System.out.printf("Range of price: £%.2f\n", rangeOfPrice);
        System.out.printf("Total number of bouquets ordered: %d\n", totalCount);
        System.out.printf("Total price of all the bouquets: £%.2f\n", totalPrice);
        System.out.printf("Average price of bouquet: £%.2f\n", averagePrice);
        System.out.println("\nFrequency Counts:");
        
        System.out.println("Flower Frequency count:");
        for (Flower flower : Flower.values()) {
            System.out.printf("%s: %d\n", flower, flowerFreq[flower.ordinal()]);
        }
        
        System.out.println("Color Frequency count:");
        for (Color color : Color.values()) {
            System.out.printf("%s: %d\n", color, colorFreq[color.ordinal()]);
        }
        
        System.out.println("Size Frequency count:");
        for (Size size : Size.values()) {
            System.out.printf("%s: %d\n", size, sizeFreq[size.ordinal()]);
        }
    }
}