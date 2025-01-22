import java.util.Scanner;

public class FlowerShopMenu1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) { // Display the menu
            DisplayShopMenu();
            int option = GetUserChoice(scanner); // Get user choice
            if (option == 1) { // Process user choice
                OrderBouquetAndGetPrice();
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
        }  catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }
    public static void OrderBouquetAndGetPrice() {// Function to order a bouquet and get the price
        System.out.println("A bouquet has been ordered.");
    }
    public static void DisplayStatistics() {// Function to display statistics
        System.out.println("Summary statistics provided.");
    }
}