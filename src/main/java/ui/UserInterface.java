package ui;

import domain.Controller;
import domain.Member;
import domain.Membership;
import domain.MembershipType;

import java.time.Year;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class UserInterface {
    private boolean uiIsRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private Controller controller;
    String userRole = "";

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    public void startProgram() {
        System.out.println("Velkommen til Delfinens database!\n");
        while (userRole.isEmpty()) {
            selectUserRole();
        }
        while (uiIsRunning) {
            showMainMenu();
        }
    }

    private void selectUserRole() {
        System.out.println("""
                Vælg din rolle i Delfinen:
                1. Formand
                2. Kasser.
                3. Træner""");
        switch (takeIntUserInput()) {
            case 1 -> userRole = "Chairman";
            case 2 -> userRole = "Accountant";
            case 3 -> userRole = "Coach";
            default -> System.out.println("Ugyldigt valg! Prøv igen:");
        }
    }

    private void showMainMenu() {
        switch (userRole) {
            case "Chairman" -> chairmanSelection();
            case "Accountant" -> accountantSelection();
            case "Coach" -> coachSelection();
        }
    }

    private void chairmanSelection() {
        System.out.println("""
                Vælg den funktion du vil tilgå:
                1. Opret nyt medlem.
                2. Vis alle medlemmer.
                3. Find et specifikt medlem.
                4. Slet et medlem.
                5. Rediger et medlem.
                6. Gem data.
                8. Skift rolle.
                9. Afslut program.
                """);
        switch (takeIntUserInput()) {
            case 1 -> createMember();
            case 2 -> showAllMembers();
            case 3 -> findMembers();
            case 4 -> deleteMember();
            case 5 -> editMember();
            case 6 -> controller.saveData();
            case 8 -> selectUserRole();
            case 9 -> exitProgram();
            default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
        }
    }

    private void accountantSelection() {
        System.out.println("""
                    Vælg den funktion du vil tilgå:
                    1. Kasser menuer
                    2.
                    3.
                    4.
                    8. Skift rolle.
                    9. Afslut program.
                    """);
        switch (takeIntUserInput()) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            case 4 -> {}
            case 8 -> selectUserRole();
            case 9 -> exitProgram();
            default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
        }
    }

    private void coachSelection() {
        System.out.println("""
                    Vælg den funktion du vil tilgå:
                    1. Træner menuer
                    2.
                    3.
                    4.
                    8. Skift rolle.
                    9. Afslut program.
                    """);
        switch (takeIntUserInput()) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            case 4 -> {}
            case 8 -> selectUserRole();
            case 9 -> exitProgram();
            default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
        }
    }

    private int takeIntUserInput() {
        String input = scanner.nextLine();
        int inputInt;

        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input! Prøv igen:");
            inputInt = takeIntUserInput();
        }
        return inputInt;
    }


    private void deleteMember() {
        System.out.println("Søg venligst på det medlem du gerne vil slette:");
        String search = scanner.nextLine();
        ArrayList<Member> foundMembers = controller.findMembers(search);

        if (foundMembers.isEmpty()) {
            System.out.println("Ingen medlemmer fundet");
            return;
        }

        System.out.println("Vælg et medlem du gerne vil slette");
        int index = 1;
        for (Member member : foundMembers) {
            System.out.println(index + ". " + controller.getMemberName(member));
            index++;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > foundMembers.size()) {
            System.out.println("Ugyldigt valg, prøv igen din tåbe");
            return;
        }

        Member selectedMember = foundMembers.get(choice - 1);
        System.out.println(selectedMember);
        System.out.println("Skriv ja");

        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("ja")) {
                controller.deleteMember(selectedMember);
                System.out.println("Medlem slettet");
                controller.saveData();

        }
        else System.out.println("Kunne ikke slette medlemmet.");
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
        System.out.println("Indtast fulde navn:");
        String fullName = scanner.nextLine();

        LocalDate birthDate = createBirthdate();

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

        while (userSelection != 1 && userSelection != 2) {
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

        while (userSelection != 1 && userSelection != 2) {
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

        while (userSelection != 1 && userSelection != 2) {
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

        while (userSelection != 1 && userSelection != 2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }

        if (userSelection == 1) {
            isCoach = true;
        }

        MembershipType membershipType = controller.createNewUser(fullName, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach);
        if (membershipType == MembershipType.COMPETITIVE) {
            System.out.println("Du har lavet en konkurrencesvømmer og skal derfor vælge en coach og et hold");
            System.out.println("Her er en liste over coaches du kan vælge:");
            System.out.println(controller.listOfCoaches());
            System.out.println("Vælg en træner ved at skrive deres tal til venstre");
            int index = scanner.nextInt();
            controller.setCoachToMember(controller.findfirstMember(fullName, address),controller.getIndexInListOfCoaches(index));
            System.out.println("Du har tilføjet en træner");
        } else if (membershipType == MembershipType.COACH) {
            System.out.println("Du har lavet en træner");
        }

        controller.saveData();
    }

    private LocalDate createBirthdate() {
        int birthYear = 0000;
        int birthMonth = 0;
        int birthDay = 0;

        System.out.println("Indtast fødselsår:");
        birthYear = takeIntUserInput();
        while (!(birthYear >= 1920 && birthYear <= Year.now().getValue())) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            birthYear = takeIntUserInput();
        }

        System.out.println("Indtast fødselsmåned:");
        birthMonth = takeIntUserInput();
        while (!(birthMonth >= 1 && birthMonth <= 12)) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            birthMonth = takeIntUserInput();
        }

        System.out.println("Indtast fødselsdag:");
        birthDay = takeIntUserInput();
        while (!(birthDay >= 1 && birthDay <= 31)) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            birthDay = takeIntUserInput();
        }

        return LocalDate.of(birthYear, birthMonth, birthDay);
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
                    controller.saveData();
                }
                case 2->{
                    System.out.println("Enter the new Email");
                    scanner.nextLine();
                    String newEmail = scanner.nextLine();
                    selectedMember.setEmail(newEmail);
                    System.out.println("Email is now updated to: " + newEmail);
                    controller.saveData();
                }
                case 3->{
                    System.out.println("Enter the new Tlf.Number");
                    scanner.nextInt();
                    int newNumber = scanner.nextInt();
                    selectedMember.setPhoneNumber(newNumber);
                    System.out.println("Tlf.number is now updated to: " + newNumber);
                    controller.saveData();
                }
                case 4 -> {
                    System.out.println("Enter the new Address");
                    scanner.nextLine();
                    String newAddress = scanner.nextLine();
                    selectedMember.setAddress(newAddress);
                    System.out.println("Address is now updated to: " + newAddress);
                    controller.saveData();
                }
                case 5 ->{
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("is member active? [y/n]: ");
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
                    controller.saveData();
                }
                case 6 ->{
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
                    controller.saveData();
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
                    controller.saveData();
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
                    controller.saveData();
                }

            }



            }
        }



        }










