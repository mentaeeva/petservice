package com.example.assignment2;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Pet> petList = new ArrayList<>();
    private static List<Adopter> adopterList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:postgresql://localhost:5432/pet_adoption";
        String user = "postgres";
        String password = "1111";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Успешное подключение к PostgreSQL!");

            while (true) {
                System.out.println("\nPet Adoption System Menu");
                System.out.println("1. Insert pets");
                System.out.println("2. Insert adopters");
                System.out.println("3. Update adopters");
                System.out.println("4. Delete pets");
                System.out.println("5. Select pets");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Очистка некорректного ввода
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера после числа

                switch (choice) {
                    case 1:
                        DataBase.insertDatatoPet(connection, scanner, petList);
                        break;
                    case 2:
                        DataBase.insertDatatoAdopter(connection, scanner, adopterList);
                        break;
                    case 3:
                        DataBase.updateAdopter(connection, scanner, adopterList);
                        break;
                    case 4:
                        DataBase.deletePet(connection, scanner, petList);
                        break;
                    case 5:
                        DataBase.selectPet(connection, scanner);
                        break;
                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения или выполнения операции: " + e.getMessage());
        }
    }
}


