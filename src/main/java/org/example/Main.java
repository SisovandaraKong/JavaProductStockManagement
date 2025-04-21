package org.example;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static String[][] stock;
    static String[] history = new String[100];
    static int historyCount = 0;

    public static void main(String[] args) {
        int option;

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
            scanner.nextLine();

            switch (option) {
                case 1 -> setUpStock();
                case 2 -> viewStock();
                case 3 -> insertProduct();
                case 4 -> updateProduct();
                case 5 -> deleteProduct();
                case 6 -> viewHistory();
                case 7 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid option.");
            }

        } while (option != 7);
    }

    static void setUpStock() {
        System.out.print("Enter number of stock: ");
        int stockCount = scanner.nextInt();
        stock = new String[stockCount][];

        for (int i = 0; i < stockCount; i++) {
            System.out.print("Enter number of catalogues for stock [" + (i + 1) + "]: ");
            int catalogueCount = scanner.nextInt();
            stock[i] = new String[catalogueCount];

            for (int j = 0; j < catalogueCount; j++) {
                stock[i][j] = "EMPTY";
            }
        }

        System.out.println("----- SET UP STOCK SUCCEEDED -----");
        viewStock();
    }

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
            System.out.println();
        }
    }


    static void insertProduct() {
        if (stock == null) {
            System.out.println("Set up stock first.");
            return;
        }

        System.out.print("Enter stock number: ");
        int stockNum = scanner.nextInt();
        scanner.nextLine();

        if (stockNum < 1 || stockNum > stock.length) {
            System.out.println("Invalid stock number.");
            return;
        }

        int stockIndex = stockNum - 1;

        System.out.print("Enter catalogue number: ");
        int catalogueNum = scanner.nextInt();
        scanner.nextLine();

        if (catalogueNum < 1 || catalogueNum > stock[stockIndex].length) {
            System.out.println("Invalid catalogue number.");
            return;
        }

        int catalogueIndex = catalogueNum - 1;

        if (!stock[stockIndex][catalogueIndex].equals("EMPTY")) {
            System.out.println("This catalogue is already occupied with '" + stock[stockIndex][catalogueIndex] + "'.");
            return;
        }

        System.out.print("Enter product name to insert: ");
        String productName = scanner.nextLine();
        stock[stockIndex][catalogueIndex] = productName;

        if (historyCount < history.length) {
            history[historyCount] = "Inserted '" + productName + "' into Stock " + stockNum + " Catalogue " + catalogueNum;
            historyCount++;
        }

        System.out.println("Product inserted.");
    }

    static void updateProduct() {
        if (stock == null) {
            System.out.println("Set up stock first.");
            return;
        }

        System.out.print("Enter product name to update: ");
        String oldName = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < stock.length; i++) {
            for (int j = 0; j < stock[i].length; j++) {
                if (stock[i][j].equalsIgnoreCase(oldName)) {
                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();
                    stock[i][j] = newName;

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

    static void deleteProduct() {
        if (stock == null) {
            System.out.println("Set up stock first.");
            return;
        }

        System.out.print("Enter product name to delete: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < stock.length; i++) {
            for (int j = 0; j < stock[i].length; j++) {
                if (stock[i][j].equalsIgnoreCase(name)) {
                    stock[i][j] = "EMPTY";

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
