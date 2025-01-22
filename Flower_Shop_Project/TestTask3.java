import java.util.Scanner;

public class TestTask3 {

    enum Size { // Enum variables
        Small, Medium, Large
    }
    
    enum Flower {
        Rose, Lily, Carnations, Daffodil, Gerbera, Chrysanthemum, Assorted
    }
    
    enum Color {
        White, Red, Pink, Yellow, Blue, Assorted
    }

    private static int[] sizeFreq = new int[Size.values().length]; // Arrays to store frequency counts
    private static int[] flowerFreq = new int[Flower.values().length];
    private static int[] colorFreq = new int[Color.values().length]; 
    private static int totalCount = 0; // Variables for statistics
    private static double totalPrice = 0;
    private static double minPrice = Double.MAX_VALUE;
    private static double maxPrice = Double.MIN_VALUE;
    private static Scanner scanner = new Scanner(System.in);

    private static void recordSale(Size size, Flower flower, Color color, double price) { // Method to record a sale
        sizeFreq[size.ordinal()]++; // Update frequency counts
        flowerFreq[flower.ordinal()]++;
        colorFreq[color.ordinal()]++;
        totalCount++; // Update statistics
        totalPrice += price;
        if (price < minPrice) minPrice = price;
        if (price > maxPrice) maxPrice = price;
    }

    private static void displayStatistics() { // Method to display statistics
        double averagePrice = totalCount > 0 ? totalPrice / totalCount : 0;
        double rangeOfPrice = maxPrice - minPrice;
        System.out.println("\nStatistics for bouquets:");
        System.out.printf("Minimum price: £%.2f\n", minPrice);
        System.out.printf("Maximum price: £%.2f\n", maxPrice);
        System.out.printf("Range of price: £%.2f\n", rangeOfPrice);
        System.out.printf("Total number of bouquets ordered: %d\n", totalCount);
        System.out.printf("Total price of all the bouquets: £%.2f\n", totalPrice);
        System.out.printf("Average price of bouquet: £%.2f\n", averagePrice);
        System.out.println("\nFrequency Counts:");         // Frequency Counts
        System.out.println("Size Freq count:");
        for (Size size: Size.values()) {
            System.out.printf("%s: %d\n", size, sizeFreq[size.ordinal()]);
        }
        System.out.println("Flower Freq count:");
        for (Flower flower: Flower.values()) {
            System.out.printf("%s: %d\n", flower, flowerFreq[flower.ordinal()]);
        }
        System.out.println("Color Freq count:");
        for (Color color: Color.values()) {
            System.out.printf("%s: %d\n", color, colorFreq[color.ordinal()]);
        }
    }

    private static void addSale() {
        System.out.print("Enter flower type (Rose, Lily, Carnations, Daffodil, Gerbera, Chrysanthemum, Assorted): ");
        String flowerType = scanner.nextLine();
        System.out.print("Enter flower colour (White, Red, Pink, Yellow, Blue, Assorted): ");
        String colour = scanner.nextLine();
        System.out.print("Enter bouquet size (Small, Medium, Large): ");
        String size = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try {
            Size sizeEnum = Size.valueOf(size);
            Flower flowerEnum = Flower.valueOf(flowerType);
            Color colorEnum = Color.valueOf(colour);
            recordSale(sizeEnum, flowerEnum, colorEnum, price);
            System.out.printf("Sale recorded. Final price: £%.2f. Returning to main menu.\n", price);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please check your entries and try again.");
        }
    }

    public static void main(String[] args) { // Main method with menu
        // Adding initial sample data
        recordSale(Size.Medium, Flower.Rose, Color.Pink, 17.25);
        recordSale(Size.Large, Flower.Assorted, Color.Assorted, 17.1);
        recordSale(Size.Small, Flower.Gerbera, Color.Yellow, 12.1);
        recordSale(Size.Medium, Flower.Lily, Color.White, 19.5);
        recordSale(Size.Large, Flower.Rose, Color.Red, 22.8);
        recordSale(Size.Large, Flower.Chrysanthemum, Color.White, 22.8);
        recordSale(Size.Small, Flower.Daffodil, Color.Blue, 12.1);
        recordSale(Size.Medium, Flower.Assorted, Color.Assorted, 13.5);
        recordSale(Size.Small, Flower.Carnations, Color.Pink, 11.55);
        recordSale(Size.Large, Flower.Rose, Color.Red, 22.8);

        while (true) {
            System.out.println("\nFlowerShopMenu:");
            System.out.println("2. Display statistics");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                addSale(); // Record a new sale
            } else if (choice == 2) {
                displayStatistics(); // Display current statistics
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}