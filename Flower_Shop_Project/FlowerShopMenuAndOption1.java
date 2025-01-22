import java.util.Scanner;

public class FlowerShopMenuAndOption1 {
    // Arrays to store order details
    private static String[] flowerTypes = new String[100];
    private static String[] colours = new String[100];
    private static String[] sizes = new String[100];
    private static double[] prices = new double[100];

    private static int orderCount = 0; // To keep track of the number of orders

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) { // Display the menu
            DisplayShopMenu();
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

    public static void DisplayShopMenu() { // Function to display the menu
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
        System.out.print("Enter flower type (Rose, Lily, Carnations, Daffodil, Gerbera, Chrysanthemum, Assorted): ");
        String flowerType = scanner.nextLine();
        System.out.print("Enter flower colour (White, Red, Pink, Yellow, Blue, Assorted): ");
        String colour = scanner.nextLine();
        System.out.print("Enter bouquet size (Small, Medium, Large): ");
        String size = scanner.nextLine();

        if (!isValidInput(flowerType, colour, size)) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        double price = (getFlowerPrice(flowerType) + getColourPrice(colour)) * getSizeMarkup(size);
        System.out.println("The price is: £" + price);

        // Store order details in the arrays
        flowerTypes[orderCount] = flowerType;
        colours[orderCount] = colour;
        sizes[orderCount] = size;
        prices[orderCount] = price;
        orderCount++;

        System.out.println("Order has been stored.");
    }

    public static void DisplayStatistics() { // Function to display statistics
        System.out.println("Summary statistics provided.");
    }

    public static boolean isValidInput(String flowerType, String colour, String size) {
        String[] validFlowerTypes = {"Rose", "Lily", "Carnations", "Daffodil", "Gerbera", "Chrysanthemum", "Assorted"};
        String[] validColourTypes = {"White", "Red", "Pink", "Yellow", "Blue", "Assorted"};
        String[] validSizeTypes = {"Small", "Medium", "Large"};
        return contains(validFlowerTypes, flowerType) && contains(validColourTypes, colour) && contains(validSizeTypes, size);
    }

    private static boolean contains(String[] array, String value) {
        for (String item: array) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static double getFlowerPrice(String flowerType) {
        switch (flowerType) {
            case "Rose": return 1.2;
            case "Lily": return 1.3;
            case "Carnations": return 1.0;
            case "Daffodil": return 1.0;
            case "Gerbera": return 1.1;
            case "Chrysanthemum": return 1.1;
            case "Assorted": return 0.8;
            default: return 0;
        }
    }

    public static double getColourPrice(String colour) {
        switch (colour) {
            case "White": return 1.3;
            case "Red": return 1.2;
            case "Pink": return 1.1;
            case "Yellow": return 1.1;
            case "Blue": return 1.2;
            case "Assorted": return 1.0;
            default: return 0;
        }
    }

    public static double getSizeMarkup(String size) {
        switch (size) {
            case "Small": return 5.5;
            case "Medium": return 7.5;
            case "Large": return 9.5;
            default: return 0;
        }
    }
}