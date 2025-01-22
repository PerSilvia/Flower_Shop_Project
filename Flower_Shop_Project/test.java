import java.util.Scanner;

public class FlowerShopMenu2 {

    // Enum types
    enum Size {
        SMALL, MEDIUM, LARGE
    }

    enum Flower {
        ROSE, LILY, CARNATIONS, DAFFODIL, GERBERA, CHRYSANTHEMUM, ASSORTED
    }

    enum Color {
        WHITE, RED, PINK, YELLOW, BLUE, ASSORTED
    }

    // Static arrays and variables for statistics
    private static int[] sizeFreq = new int[Size.values().length];
    private static int[] flowerFreq = new int[Flower.values().length];
    private static int[] colorFreq = new int[Color.values().length];
    private static int totalCount = 0;
    private static double totalPrice = 0;
    private static Double minPrice = null;
    private static Double maxPrice = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) { // Display the menu
            DisplayMenu();
            int option = GetUserChoice(scanner); // Get user choice

            if (option == 1) { // Process user choice
                OrderBouquetAndGetPrice(scanner);
            } else if (option == 2) {
                DisplayStatistics(scanner);
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
            System.out.println("Enter bouquet size (SMALL, MEDIUM, LARGE):");
            String sizeInput = scanner.nextLine().toUpperCase();
            Size size = Size.valueOf(sizeInput);

            System.out.println("Enter flower type (ROSE, LILY, CARNATIONS, DAFFODIL, GERBERA, CHRYSANTHEMUM, ASSORTED):");
            String flowerInput = scanner.nextLine().toUpperCase();
            Flower flower = Flower.valueOf(flowerInput);

            System.out.println("Enter color (WHITE, RED, PINK, YELLOW, BLUE, ASSORTED):");
            String colorInput = scanner.nextLine().toUpperCase();
            Color color = Color.valueOf(colorInput);

            // Calculate price based on size, flower, and color
            double price = calculatePrice(size, flower, color);

            // Record the sale
            recordSale(size, flower, color, price);
            System.out.printf("A bouquet of %s size, %s flower, %s color has been ordered at £%.2f.\n", size, flower, color, price);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please make sure you enter valid values.");
        }
    }

    public static void DisplayStatistics(Scanner scanner) { // Function to display statistics
        displayStatistics();

        System.out.println("\nWould you like to return to the main menu or exit?");
        System.out.println("1. Return to main menu");
        System.out.println("2. Exit");
        int choice = GetUserChoice(scanner);

        if (choice == 2) {
            System.out.println("Exiting the program.");
            System.exit(0); // Exit the program
        }
    }

    // Method to calculate price based on size, flower, and color
    private static double calculatePrice(Size size, Flower flower, Color color) {
        double sizeMarkUp = 0;
        double flowerMarkUp = 0;
        double colorMarkUp = 0;

        // Adjust base price based on size
        switch (size) {
            case SMALL:
                sizeMarkUp = 5.5;
                break;
            case MEDIUM:
                sizeMarkUp = 7.5;
                break;
            case LARGE:
                sizeMarkUp = 9.5;
                break;
        }

        // Adjust price based on flower type
        switch (flower) {
            case ROSE:
                flowerMarkUp = 1.2;
                break;
            case LILY:
                flowerMarkUp = 1.3;
                break;
            case CARNATIONS:
                flowerMarkUp = 1.0;
                break;
            case DAFFODIL:
                flowerMarkUp = 1.0;
                break;
            case GERBERA:
                flowerMarkUp = 1.1;
                break;
            case CHRYSANTHEMUM:
                flowerMarkUp = 1.1;
                break;
            case ASSORTED:
                flowerMarkUp = 0.8;
                break;
        }

        // Adjust price based on color
        switch (color) {
            case WHITE:
                colorMarkUp = 1.3;
                break;
            case RED:
                colorMarkUp = 1.2;
                break;
            case PINK:
                colorMarkUp = 1.1;
                break;
            case YELLOW:
                colorMarkUp = 1.1;
                break;
            case BLUE:
                colorMarkUp = 1.2;
                break;
            case ASSORTED:
                colorMarkUp = 1.0;
                break;
        }

        return (flowerMarkUp + colorMarkUp) * sizeMarkUp;
    }

    // Method to record a sale
    private static void recordSale(Size size, Flower flower, Color color, double price) {
        sizeFreq[size.ordinal()]++;
        flowerFreq[flower.ordinal()]++;
        colorFreq[color.ordinal()]++;
        totalCount++;
        totalPrice += price;
        if (minPrice == null || price < minPrice) minPrice = price;
        if (maxPrice == null || price > maxPrice) maxPrice = price;
    }

    // Method to display statistics
    private static void displayStatistics() {
        double averagePrice = totalCount > 0 ? totalPrice / totalCount : 0;
        double rangeOfPrice = (minPrice != null && maxPrice != null) ? maxPrice - minPrice : 0;
        System.out.println("\nStatistics for bouquets:");
        System.out.printf("Minimum price: £%.2f\n", minPrice != null ? minPrice : 0);
        System.out.printf("Maximum price: £%.2f\n", maxPrice != null ? maxPrice : 0);
        System.out.printf("Range of price: £%.2f\n", rangeOfPrice);
        System.out.printf("Total number of bouquets ordered: %d\n", totalCount);
        System.out.printf("Total price of all the bouquets: £%.2f\n", totalPrice);
        System.out.printf("Average price of bouquet: £%.2f\n", averagePrice);
        System.out.println("\nFrequency Counts:");
        System.out.println("Size Freq count:");
        for (Size size : Size.values()) {
            System.out.printf("%s: %d\n", size, sizeFreq[size.ordinal()]);
        }
        System.out.println("Flower Freq count:");
        for (Flower flower : Flower.values()) {
            System.out.printf("%s: %d\n", flower, flowerFreq[flower.ordinal()]);
        }
        System.out.println("Color Freq count:");
        for (Color color : Color.values()) {
            System.out.printf("%s: %d\n", color, colorFreq[color.ordinal()]);
        }
    }
}
