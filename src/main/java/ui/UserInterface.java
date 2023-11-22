package ui;

import domain.Controller;
import domain.Member;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.Month;
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
                case 3 -> findMembers();
                case 4 -> deleteMember();
                case 5 -> editMember();
                case 6 -> controller.loadData();
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

    private void findMembers() {
        System.out.println("Search by Name, user-ID or phone number");
        String search = scanner.nextLine();
        ArrayList<Member> foundMembers = controller.findMembers(search);
        int index = 1;
        for (Member member: foundMembers) {
            System.out.println(index + ". " + controller.getMemberName(member));
            index += 1;
        }
        System.out.println("This is your search result. Please choose a member by their number");
        int choice = scanner.nextInt();
        Member selectedMember = controller.getMemberFromIndex(choice, foundMembers);
        System.out.println("This is your selected member:");
        System.out.println(selectedMember);
    }

    private void showAllMembers() {
        System.out.println(controller.getAllMemberNames());
    }

    private void createMember() {
        //Test
        controller.createNewUser("Aleksander Gregersen",
                LocalDate.of(1993, Month.OCTOBER,7),
                "agregersen0@gmail.com",
                42755293,
                "Mågevej 51, 2tv",
                "Male",
                true,
                true,
                true,
                true);

    }

    private void exitProgram() {
        System.out.println("Exiting...");
        uiIsRunning = false;
    }
}
