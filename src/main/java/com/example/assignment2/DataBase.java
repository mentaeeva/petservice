package com.example.assignment2;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DataBase {
    public static void insertDatatoPet(Connection connection, Scanner scanner, List<Pet> petList) {
        String insertQuery = "INSERT INTO pet (name, type, age) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            System.out.println("How many pets will be added?");
            int petCount = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < petCount; i++) {
                System.out.println("Enter name of pet " + (i + 1) + ":");
                String namePet = scanner.nextLine();
                System.out.println("Enter type of pet " + (i + 1) + ":");
                String typePet = scanner.nextLine();
                System.out.println("Enter age of pet " + (i + 1) + ":");
                int agePet = scanner.nextInt();
                scanner.nextLine();

                insertStatement.setString(1, namePet);
                insertStatement.setString(2, typePet);
                insertStatement.setInt(3, agePet);
                insertStatement.executeUpdate();

                // Добавляем в список
                petList.add(new Pet(namePet, typePet, agePet));
            }
        } catch (SQLException s) {
            System.err.println("Database error: " + s.getMessage());
        }
    }

    public static void insertDatatoAdopter(Connection connection, Scanner scanner, List<Adopter> adopterList) {
        String insertQuery = "INSERT INTO adopter (name, age) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            System.out.println("How many adopters will be added?");
            int adopterCount = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < adopterCount; i++) {
                System.out.println("Enter adopter's name " + (i + 1) + ":");
                String nameAdopter = scanner.nextLine();
                System.out.println("Enter adopter's age " + (i + 1) + ":");
                int ageAdopter = scanner.nextInt();
                scanner.nextLine();

                insertStatement.setString(1, nameAdopter);
                insertStatement.setInt(2, ageAdopter);
                insertStatement.executeUpdate();

                // Добавляем в список
                adopterList.add(new Adopter(nameAdopter, ageAdopter));
            }
        } catch (SQLException s) {
            System.err.println("Database error: " + s.getMessage());
        }
    }

    public static void updateAdopter(Connection connection, Scanner scanner, List<Adopter> adopterList) {
        String updateQuery = "UPDATE adopter SET age = ? WHERE name = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            System.out.println("Which adopter will be updated?");
            String name = scanner.nextLine();
            System.out.println("Set new age:");
            int age = scanner.nextInt();
            scanner.nextLine();

            updateStatement.setInt(1, age);
            updateStatement.setString(2, name);
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                // Обновляем в списке
                for (Adopter adopter : adopterList) {
                    if (adopter.getName().equalsIgnoreCase(name)) {
                        adopter.setAge(age);
                        break;
                    }
                }
                System.out.println("Adopter updated successfully.");
            } else {
                System.out.println("Adopter not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating adopter: " + e.getMessage());
        }
    }

    public static void deletePet(Connection connection, Scanner scanner, List<Pet> petList) {
        String deleteQuery = "DELETE FROM pet WHERE name = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            System.out.println("Which pet will be deleted?");
            String name = scanner.nextLine();

            deleteStatement.setString(1, name);
            int rowsDeleted = deleteStatement.executeUpdate();

            if (rowsDeleted > 0) {
                // Удаляем из списка
                petList.removeIf(pet -> pet.getName().equalsIgnoreCase(name));
                System.out.println("Pet deleted successfully.");
            } else {
                System.out.println("Pet not found.");
            }
        } catch (SQLException s) {
            System.err.println("Error deleting pet: " + s.getMessage());
        }
    }

    public static void selectPet(Connection connection, Scanner scanner) {
        String selectQuery = "SELECT * FROM pet WHERE type = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            System.out.println("Which type do you prefer?");
            String typePet = scanner.nextLine();
            selectStatement.setString(1, typePet);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                boolean hasResults = false;
                while (resultSet.next()) {
                    hasResults = true;
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Type: " + resultSet.getString("type"));
                }
                if (!hasResults) {
                    System.out.println("No pet found.");
                }
            }
        } catch (SQLException s) {
            System.err.println("Error fetching pet: " + s.getMessage());
        }
    }
}




