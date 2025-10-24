package com.application;

import com.application.model.User;
import com.application.service.UserService;
import com.application.util.JpaUtil; // Import JpaUtil for factory cleanup

import java.util.Scanner;

public class App {
    // Note: service must be static or passed to main, as App is instantiated in switch
    public static final UserService service = new UserService(); 

    public static void main(String[] args) {
        // Create Scanner once in main, and use try-with-resources to close ONLY at app exit
        try (Scanner input = new Scanner(System.in)) { 
            
            // Initialize JpaUtil here (optional, but ensures factory is created early)
            // Accessing any static method in JpaUtil will ensure the factory is initialized.
            JpaUtil.getEntityManager(); 

            boolean running = true;
            while (running) {
                System.out.println("\n=== User Management Menu ===");
                System.out.println("1. Create new user");
                System.out.println("2. List users");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");

                int option = input.hasNextInt() ? input.nextInt() : -1;
                input.nextLine(); // consume newline
                
                switch (option) {
                    case 1:
                        // Pass the existing Scanner instance to the register method
                        new App().register(input); 
                        break;
                    case 2:
                        service.findAll(); // Assumes your service has listAllUsers
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("A fatal application error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure the EntityManagerFactory is closed when the application shuts down
            JpaUtil.closeFactory(); 
        }
    }

    // Accept the Scanner as a parameter
    public void register(Scanner input) { 
        // NO Scanner creation or closing here!
        try {
            User user = new User();
            int confirm = 0;
            do {
                System.out.print("Enter user's name: ");
                String name = input.nextLine();
                user.setName(name);

                System.out.print("Enter '" + name + "'s email: ");
                String email = input.nextLine();
                user.setEmail(email);

                System.out.println("\nConfirm information:");
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.print("Is this correct? (1) YES  (2) NO: ");
                
                // Read confirmation without consuming the newline here, if nextInt is used 
                // However, reading 1 or 2 as an entire line is safer.
                confirm = input.hasNextInt() ? input.nextInt() : 2; 
                input.nextLine(); // consume newline
            } while (confirm != 1);

            // Assuming your service has a method called 'create'
            service.create(user.getName(), user.getEmail());
            System.out.println("User created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }
}