package org.example;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // 2D array to store stock and product names
    static String[][] stock;

    // Array to keep track of history actions
    static String[] history = new String[100];
    static int historyCount = 0;

    public static void main(String[] args) {
        int option;

        // Main menu loop
        do {
            System.out.println("\n--- Product Stock Management ---");
            System.out.println("1. Set Up Stock with Catalogue");
            System.out.println("2. View Product in Stock");
            System.out.println("3. Insert Product to Stock Catalogue");
            System.out.println("4. Update Product in Stock Catalogue by Product Name");
            System.out.println("5. Delete Product in Stock Catalogue by Name");
            System.out.println("6. View Insertion History in Stock Catalogue");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Clear newline from buffer

            // Menu options
            if (option == 1) {
                setUpStock();
            } else if (option == 2) {
                viewStock();
            } else if (option == 3) {
                insertProduct();
            } else if (option == 4) {
                updateProduct();
            } else if (option == 5) {
                deleteProduct();
            } else if (option == 6) {
                viewHistory();
            } else if (option == 7) {
                System.out.println("Exiting program...");
            } else {
                System.out.println("Invalid option.");
            }

        } while (option != 7); // Repeat until user chooses Exit
    }

    // 1. Set up stock and catalogue
    static void setUpStock() {
        System.out.print("Enter number of stock: ");
        int stockCount = scanner.nextInt();
        stock = new String[stockCount][]; // Initialize rows

        // Ask for number of catalogue items per stock
        for (int i = 0; i < stockCount; i++) {
            System.out.print("Enter number of catalogues for stock [" + (i + 1) + "]: ");
            int catalogueCount = scanner.nextInt();
            stock[i] = new String[catalogueCount]; // Initialize columns

            // Fill each catalogue slot with "EMPTY"
            for (int j = 0; j < catalogueCount; j++) {
                stock[i][j] = "EMPTY";
            }
        }

        System.out.println("----- SET UP STOCK SUCCEEDED -----");
        viewStock(); // Show stock after setup
    }

    // 2. View current stock and products
    static void viewStock() {
        if (stock == null) {
            System.out.println("Stock not set up yet.");
            return;
        }

        for (int i = 0; i < stock.length; i++) {
            System.out.print("Stock [" + (i + 1) + "] => ");
            for (int j = 0; j < stock[i].length; j++) {
                System.out.print("[ " + (j + 1) + " - " + stock[i][j] + " ] ");
            }
            System.out.println(); // Newline after each stock row
        }
    }

    // 3. Insert a product into the first empty catalogue slot
    static void insertProduct() {
        if (stock == null) {
            System.out.println("Set up stock first.");
            return;
        }

        System.out.print("Enter stock number: ");
        int stockNum = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        // Validate input
        if (stockNum < 1 || stockNum > stock.length) {
            System.out.println("Invalid stock number.");
            return;
        }

        int stockIndex = stockNum - 1;

        // Loop to find first empty slot
        for (int i = 0; i < stock[stockIndex].length; i++) {
            if (stock[stockIndex][i].equals("EMPTY")) {
                System.out.print("Enter product name to insert: ");
                String productName = scanner.nextLine();
                stock[stockIndex][i] = productName;

                // Record in history
                if (historyCount < history.length) {
                    history[historyCount] = "Inserted '" + productName + "' into Stock " + stockNum + " Catalogue " + (i + 1);
                    historyCount++;
                }

                System.out.println("Product inserted.");
                return;
            }
        }

        // If no empty slot found
        System.out.println("No empty slot in this stock.");
    }

    // 4. Update product name by searching existing name
    static void updateProduct() {
        if (stock == null) {
            System.out.println("Set up stock first.");
            return;
        }

        System.out.print("Enter product name to update: ");
        String oldName = scanner.nextLine();
        boolean found = false;

        // Loop through stock and update name if found
        for (int i = 0; i < stock.length; i++) {
            for (int j = 0; j < stock[i].length; j++) {
                if (stock[i][j].equalsIgnoreCase(oldName)) {
                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();
                    stock[i][j] = newName;

                    // Record in history
                    if (historyCount < history.length) {
                        history[historyCount] = "Updated '" + oldName + "' to '" + newName + "' in Stock " + (i + 1) + " Catalogue " + (j + 1);
                        historyCount++;
                    }

                    System.out.println("Product updated.");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    // 5. Delete a product from stock by name
    static void deleteProduct() {
        if (stock == null) {
            System.out.println("Set up stock first.");
            return;
        }

        System.out.print("Enter product name to delete: ");
        String name = scanner.nextLine();
        boolean found = false;

        // Search and delete by setting back to "EMPTY"
        for (int i = 0; i < stock.length; i++) {
            for (int j = 0; j < stock[i].length; j++) {
                if (stock[i][j].equalsIgnoreCase(name)) {
                    stock[i][j] = "EMPTY";

                    // Record in history
                    if (historyCount < history.length) {
                        history[historyCount] = "Deleted '" + name + "' from Stock " + (i + 1) + " Catalogue " + (j + 1);
                        historyCount++;
                    }

                    System.out.println("Product deleted.");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    // 6. Display all recorded history
    static void viewHistory() {
        if (historyCount == 0) {
            System.out.println("No history yet.");
        } else {
            System.out.println("--- Insertion History ---");
            for (int i = 0; i < historyCount; i++) {
                System.out.println((i + 1) + ". " + history[i]);
            }
        }
    }
}
