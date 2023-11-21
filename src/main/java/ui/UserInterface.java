package ui;

import domain.Controller;
import java.util.Scanner;

public class UserInterface {
    private boolean uiIsRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    public void startProgram() {
        System.out.println("Velkommen til Delfinens database!\n");
        while (uiIsRunning) {
            showMainMenu();
            switch (takeIntUserInput()) {
                case 1 -> createMember();
                case 2 -> showAllMembers();
                case 3 -> findMember();
                case 4 -> deleteMember();
                case 5 -> editMember();
                case 9 -> exitProgram();
                default -> System.out.println("Invalid selection! Try agan:\n");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("""
                Select the function you'd like to use:
                1. Create new member.
                2. Show all members.
                3. Find a specific member.
                4. Delete a member.
                5. Edit a member.
                """);
    }

    private int takeIntUserInput() {
        String input = scanner.nextLine();
        int inputInt;

        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Try again:");
            inputInt = takeIntUserInput();
        }
        return inputInt;
    }

    private void editMember() {

    }

    private void deleteMember() {

    }

    private void findMember() {

    }

    private void showAllMembers() {

    }

    private void createMember() {

    }

    private void exitProgram() {
        System.out.println("Exiting...");
        uiIsRunning = false;
    }
}
