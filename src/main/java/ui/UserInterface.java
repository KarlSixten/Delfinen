package ui;

import domain.Controller;
import domain.Member;

import java.time.*;
import java.util.ArrayList;

import java.util.Scanner;

public class UserInterface {
    private boolean uiIsRunning = true;
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller;
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
        System.out.println("Please search for the member you would like to delete:");
        String search = scanner.nextLine();
        ArrayList<Member> foundMembers = controller.findMembers(search);

        if (foundMembers.isEmpty()) {
            System.out.println("No members have been found");
            return;
        }

        System.out.println("Select a member to delete");
        int index = 1;
        for (Member member : foundMembers) {
            System.out.println(index + ". " + controller.getMemberName(member));
            index++;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > foundMembers.size()) {
            System.out.println("Invalid selection. Please select a valid member");
            return;
        }

        Member selectedMember = foundMembers.get(choice - 1);
        System.out.println(selectedMember);
        System.out.println("Enter 'yes' to confirm");

        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
                controller.deleteMember(choice);
                System.out.println("Member deleted");
                controller.saveData();

        }
        else System.out.println("Cancelled trying to delete a member");
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
        String fullName = createName();
        LocalDate birthDate = createBirthdate();
        String email = createEmail();
        int phoneNumber = createPhoneNumber();
        String address = createAddress();
        String gender = createGender();
        boolean isActive = true;
        boolean isSenior = checkIfSenior(birthDate);
        boolean isCompetitive = checkIfCompetitive();
        boolean isCoach = checkIfCoach();

        controller.createNewUser(fullName, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach);
        controller.saveData();
    }

    private String createName() {
        System.out.println("Indtast fulde navn:");
        String fullName = scanner.nextLine();
        while (!nameIsValid(fullName)) {
            System.out.println("Ugyldigt navn! Prøv igen:");
            fullName = scanner.nextLine();
        }
        return fullName;
    }

    private LocalDate createBirthdate() {
        int birthYear;
        int birthMonth;
        int birthDay;

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
        while (!(birthDay >= 1 && birthDay <= YearMonth.of(birthYear, birthMonth).lengthOfMonth())) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            birthDay = takeIntUserInput();
        }
        return LocalDate.of(birthYear, birthMonth, birthDay);
    }

    private String createEmail() {
        System.out.println("Indtast e-mail:");
        String email = scanner.nextLine();
        while (!emailIsValid(email)) {
            System.out.println("Ugyldig email! Prøv igen:");
            email = scanner.nextLine();
        }
        return email;
    }

    private int createPhoneNumber() {
        System.out.println("Indtast tlf. nr.:");
        int phoneNumber = takeIntUserInput();
        while (!(phoneNumber >= 10000000 && phoneNumber <= 99999999)) {
            System.out.println("Ugyldigt telefonnummer! Prøv igen:");
            phoneNumber = takeIntUserInput();
        }
        return phoneNumber;
    }

    private String createAddress() {
        System.out.println("Indtast addresse:");
        return scanner.nextLine();
    }

    private String createGender() {
        System.out.println("""
                Vælg køn:
                1. Kvinde
                2. Mand""");

        int userSelection = takeIntUserInput();
        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }
        if (userSelection == 1) {
            return "Woman";
        } else {
            return "Male";
        }
    }

    private boolean checkIfSenior(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    private boolean nameIsValid(String stringToTest) {
        return stringToTest.matches("^[^0-9]+\\s[^0-9]+$");
    }

    private boolean emailIsValid(String stringToTest) {
        return stringToTest.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean checkIfCompetitive() {
        System.out.println("""
                Er medlemmet konkurrencesvømmer?
                1. Ja
                2. Nej""");

        int userSelection = takeIntUserInput();
        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }
        return userSelection == 1;
    }

    private boolean checkIfCoach() {
        System.out.println("""
                Er medlemmet træner?
                1. Ja
                2. Nej""");

        int userSelection = takeIntUserInput();
        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }
        return userSelection == 1;
    }

    private boolean checkIfActive() {
        System.out.println("""
                Er medlemmet aktivt?
                1. Ja
                2. Nej""");
        int userSelection = takeIntUserInput();
        while (userSelection !=1 && userSelection !=2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }
        return userSelection == 1;
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
        int choice = takeIntUserInput();
        Member selectedMember = controller.getMemberFromIndex(choice, foundMembers);
        System.out.println("This is your selected member:");
        System.out.println(selectedMember);
        if (selectedMember != null){
            System.out.println("""
                        What do you want to edit?
                        1. Full name:
                        2. Email:
                        3. Tlf.Number:
                        4. Address:
                        5. Active/Passive:
                        6. Competitive/non competitive:
                        7. Is member a coach
                        """);
            switch (takeIntUserInput()){
                case 1->{
                    selectedMember.setFullName(createName());
                    System.out.println("Fulde navn er blevet opdateret til: " + selectedMember.getFullName());
                }
                case 2->{
                    selectedMember.setEmail(createEmail());
                    System.out.println("Email is now updated to: " + selectedMember.getEmail());
                }
                case 3->{
                    selectedMember.setPhoneNumber(createPhoneNumber());
                    System.out.println("Tlf.number is now updated to: " + selectedMember.getPhoneNumber());
                }
                case 4 -> {
                    selectedMember.setAddress(createAddress());
                    System.out.println("Address is now updated to: " + selectedMember.getAddress());
                }
                case 5 ->{
                    selectedMember.getMembership().setActive(checkIfActive());
                    System.out.println("Medlemsstatus opdateret.");
                }
                case 6 ->{
                    selectedMember.getMembership().setCompetetive(checkIfCompetitive());
                    System.out.println("Medlemsstatus opdateret.");
                }
                case 7 ->{
                    selectedMember.getMembership().setCoach(checkIfCoach());
                    System.out.println("Medlemsstatus opdateret.");
                }
                default -> System.out.println("Ugyldigt valg!");
            }
            controller.saveData();
        }
    }


}