package ui;

import domain.Controller;

import java.time.LocalDate;
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
                default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("""
                Vælg den funktion du vil tilgå:
                1. Opret nyt medlem.
                2. Vis alle medlemmer.
                3. Find et specifikt medlem.
                4. Slet et medlem.
                5. Rediger et medlem.
                """);
    }

    private int takeIntUserInput() {
        String input = scanner.nextLine();
        int inputInt;

        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ulgyldigt input! Prøv igen:");
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
        System.out.println(controller.getAllMemberNames());
    }

    private void createMember() {
        System.out.println("Indtast fulde navn:");
        String fullName = scanner.nextLine();

        //HER KAN MAN KUN VÆRE FØDT I OKTOBER ELLER EFTER
        System.out.println("Indtast fødselsdato (ååååmmdd):");
        String birthdateString = scanner.nextLine();
        LocalDate birthDate = LocalDate.of(Integer.parseInt(birthdateString.substring(0, 3)), Integer.parseInt(birthdateString.substring(4, 5)), Integer.parseInt(birthdateString.substring(6, 7)));

        System.out.println("Indtast e-mail:");
        String email = scanner.nextLine();

        System.out.println("Indtast tlf. nr.:");
        int phoneNumber = takeIntUserInput();

        System.out.println("Indtast addresse:");
        String address = scanner.nextLine();

        System.out.println("""
                Vælg køn:
                1. Kvinde
                2. Mand""");
        String gender = "Male";
        int userSelection = takeIntUserInput();

        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }

        if (userSelection == 1) {
            gender = "Woman";
        }

        //This has been set to true since it doesn't make sense to create an inactive user
        boolean isActive = true;

        System.out.println("""
                Er medlemmet senior?
                1. Ja
                2. Nej
                """);
        userSelection = takeIntUserInput();
        boolean isSenior = false;

        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }

        if (userSelection == 1) {
            isSenior = true;
        }

        System.out.println("""
                Er medlemmet konkurrencesvømmer?
                1. Ja
                2. Nej
                """);
        userSelection = takeIntUserInput();
        boolean isCompetitive = false;

        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }

        if (userSelection == 1) {
            isCompetitive = true;
        }

        System.out.println("""
                Er medlemmet træner?
                1. Ja
                2. Nej
                """);
        userSelection = takeIntUserInput();
        boolean isCoach = false;

        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }

        if (userSelection == 1) {
            isCoach = true;
        }

        controller.createNewUser(fullName, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach);
    }

    private void exitProgram() {
        System.out.println("Exiting...");
        uiIsRunning = false;
    }
}
