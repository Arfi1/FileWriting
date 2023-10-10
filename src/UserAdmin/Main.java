package UserAdmin;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
        final String filename = "users.text";
        private ArrayList<User> users;
        private User user;

        Main() {
            users = new ArrayList<User>();
            readFile();
        }

        void viewUserList() {
            System.out.println("\nUSER LIST:");
            for (int i = 0; i < users.size(); i ++)
                System.out.println("#" + i + ":" + users.get(i));
        }

        void createNewUser() {
            int id;
            String name;
            String password;
            User user;
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nCREATE NEW USER\n");

            System.out.println("id: ");
            id = scanner.nextInt();
            scanner.nextLine(); // scanner bug

            System.out.println("Name: ");
            name = scanner.nextLine();

            System.out.println("password: ");
            password = scanner.nextLine();

            user = new User(id, name, password);
            users.add(user);
            saveFile();
            System.out.println("\nUser added.");
        }

        void deleteUser() {
            int userNumber;
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nDELETE USER: ");
            System.out.print("Enter # of user to delete: ");

            userNumber = scanner.nextInt();

            if (userNumber >= 0 && userNumber < users.size()) {
                users.remove(userNumber);
                saveFile();
                System.out.println("\nUser deleted.");
            } else {
                System.out.println("Invalid user number.");
            }
        }

    private void saveFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.write(Integer.toString(user.getID()));
                writer.newLine();
                writer.write(user.getName());
                writer.newLine();
                writer.write(user.getPasword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int id = Integer.parseInt(line);
                String name = reader.readLine();
                String password = reader.readLine();
                User user = new User(id, name, password);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty user list.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;

        Menu menu = new Menu("MENU:", "Please choose an option: ",
                new String[]{"1. View user list", "2. Create new user", "3. Delete user", "9. Quit"});

        while (running) {
            menu.printMenu();
            int choice = menu.readChoice();

            switch (choice) {
                case 1:
                    viewUserList();
                    break;
                case 2:
                    createNewUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    }



