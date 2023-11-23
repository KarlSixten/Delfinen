package ui;

import domain.Controller;
import domain.Member;
import domain.Membership;

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
    public void editMember(){
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
        if (selectedMember != null){
            System.out.println("""
                        What do you want to edit?
                        1. Fullname:
                        2. Email:
                        3. Tlf.Number:
                        4. Address:
                        5. Active/Passive:
                        6. Senior/Junior:
                        7. Competitive/non competitive:
                        8. Is member a coach
                        """);
            int switchChoice = scanner.nextInt();
            switch (switchChoice){
                case 1->{
                    System.out.println("Enter the new Fullname");
                    scanner.nextLine();
                    String newName = scanner.nextLine();
                    selectedMember.setFullName(newName);
                    System.out.println("Fullname is now updated to: " + newName);
                    controller.loadData();
                }
                case 2->{
                    System.out.println("Enter the new Email");
                    scanner.nextLine();
                    String newEmail = scanner.nextLine();
                    selectedMember.setEmail(newEmail);
                    System.out.println("Email is now updated to: " + newEmail);
                    controller.loadData();
                }
                case 3->{
                    System.out.println("Enter the new Tlf.Number");
                    scanner.nextInt();
                    int newNumber = scanner.nextInt();
                    selectedMember.setPhoneNumber(newNumber);
                    System.out.println("Tlf.number is now updated to: " + newNumber);
                    controller.loadData();
                }
                case 4 -> {
                    System.out.println("Enter the new Address");
                    scanner.nextLine();
                    String newAddress = scanner.nextLine();
                    selectedMember.setAddress(newAddress);
                    System.out.println("Address is now updated to: " + newAddress);
                    controller.loadData();
                }
                case 5 ->{
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("is member as active? [y/n]: ");
                        String input = scanner.next().trim().toLowerCase();

                        if (input.equals("y")) {
                            selectedMember.getMembership().setActive(true);
                            validInput = true;
                        } else if (input.equals("n")) {
                            selectedMember.getMembership().setActive(false);
                            validInput = true;
                        } else {
                            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                        }
                    }
                    System.out.println("Member status is now updated: ");
                    controller.loadData();
                }case 6 ->{
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("is member a senior? [y/n]: ");
                        String input = scanner.next().trim().toLowerCase();
                        if (input.equals("y")) {
                            selectedMember.getMembership().setSenior(true);
                            validInput = true;
                        } else if (input.equals("n")) {
                            selectedMember.getMembership().setSenior(false);
                            validInput = true;
                        } else {
                            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                        }
                    }
                    System.out.println("Member status is now updated: ");
                    controller.loadData();
                }case 7 ->{
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("is member competitive? [y/n]: ");
                        String input = scanner.next().trim().toLowerCase();
                        if (input.equals("y")) {
                            selectedMember.getMembership().setCompetetive(true);
                            validInput = true;
                        } else if (input.equals("n")) {
                            selectedMember.getMembership().setCompetetive(false);
                            validInput = true;
                        } else {
                            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                        }
                    }
                    System.out.println("Member status is now updated: ");
                    controller.loadData();
                }case 8 ->{
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("is member a coach? [y/n]: ");
                        String input = scanner.next().trim().toLowerCase();
                        if (input.equals("y")) {
                            selectedMember.getMembership().setCoach(true);
                            validInput = true;
                        } else if (input.equals("n")) {
                            selectedMember.getMembership().setCoach(false);
                            validInput = true;
                        } else {
                            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                        }
                    }
                    System.out.println("Member status is now updated: ");
                    controller.loadData();
                }

            }



            }
        }



        }










