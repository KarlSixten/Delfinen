package ui;

import domain.*;

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
                2. Kassér.
                3. Træner
                9. Afslut program.""");
        switch (takeIntUserInput()) {
            case 1 -> userRole = "Chairman";
            case 2 -> userRole = "Accountant";
            case 3 -> userRole = "Coach";
            case 9 -> exitProgram();
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
                8. Skift rolle.
                9. Afslut program.
                """);
        switch (takeIntUserInput()) {
            case 1 -> createMember();
            case 2 -> showAllMembers();
            case 3 -> findMembers();
            case 4 -> deleteMember();
            case 5 -> editMember();
            case 8 -> selectUserRole();
            case 9 -> exitProgram();
            default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
        }
    }

    private void accountantSelection() {
        System.out.println("""
                Vælg den funktion du vil tilgå:
                1. Beregn samlet årlig indkomst
                2. Beregn årlig indkomst for enkelt person
                3. Se medlemmer i restance.
                4. Ændr betalingsstatus for et medlem
                8. Skift rolle.
                9. Afslut program.
                """);
        switch (takeIntUserInput()) {
            case 1 -> getTotalSubscriptionIncome();
            case 2 -> getSubscriptionPriceSingleUser();
            case 3 -> seeMembersInArrears();
            case 4 -> changeHasPaid();
            case 8 -> selectUserRole();
            case 9 -> exitProgram();
            default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
        }
    }

    private void coachSelection() {
        System.out.println("""
                Vælg den funktion du vil tilgå:
                1. Register performance
                2. Sorter svømmere
                3. Se top 5 svømmere
                4. Se en specifik svømmers resultater inden for disciplin.
                8. Skift rolle.
                9. Afslut program.
                """);
        switch (takeIntUserInput()) {
            case 1 -> registerPerformance();
            case 2 -> sortPerformance();
            case 3 -> top5Swimmers();
            case 4 -> getSpecificMembersPerformanceInDisclipin();
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

    private int takeIntUserInput(int minimumValue, int maximumValue) {
        String input = scanner.nextLine();
        int inputInt;

        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input! Prøv igen:");
            inputInt = takeIntUserInput(minimumValue, maximumValue);
        }

        while (!(inputInt >= minimumValue && inputInt <= maximumValue)) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            inputInt = takeIntUserInput(minimumValue, maximumValue);
        }

        return inputInt;
    }

    private double takeDoubleUserInput() {
        String input = scanner.nextLine();
        double inputDouble;

        try {
            inputDouble = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input! Prøv igen:");
            inputDouble = takeDoubleUserInput();
        }
        return inputDouble;
    }


    private void deleteMember() {
        Member selectedMember = findMember();
        System.out.println("Skriv ja");

        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("ja")) {
            controller.deleteMember(selectedMember);
            System.out.println("Medlem slettet");
            controller.saveData();

        } else System.out.println("Kunne ikke slette medlemmet.");
    }


    private void findMembers() {
        System.out.println("Søg efter navn, bruger ID eller telefonnummer");
        String search = scanner.nextLine();
        ArrayList<Member> foundMembers = controller.findMembers(search);
        int index = 1;
        for (Member member : foundMembers) {
            System.out.println(index + ". " + controller.getMemberName(member));
            index += 1;
        }
        System.out.println("Dette er dit søgeresultat. Vælg venligst et medlem:");
        int choice = takeIntUserInput();
        scanner.nextLine();
        Member selectedMember = controller.getMemberFromIndex(choice, foundMembers);
        System.out.println("Dette er dit valgte medlem:");
        System.out.println(selectedMember);
    }

    private void showAllMembers() {
        printAllNames();
        System.out.println("""
                Skal der sorteres i medlemerne Vælg den funktion du vil tilgå:
                        1. Sortering med en primær
                        2. Sortering med primær og sekundær""");
        int userChoice = takeIntUserInput(1, 2);
int choice = 0;
int choice2 = 0;
        if (userChoice == 1) {
            sortLines();
            choice = takeIntUserInput(1, 11);
            controller.sortMember(choice);
        } else if (userChoice == 2) {
            sortLines();
            choice = takeIntUserInput(1, 11);
            sortLines();
            choice2 = takeIntUserInput(1, 11);
            controller.sortMemberPrimaryandSecundary(choice, choice2);
        }
        System.out.println("Liste sorteret!");
        System.out.println(controller.getAllMemberNames(choice, choice2));


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

        MembershipType membershipType = controller.createNewUser(fullName, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach);
        switch (membershipType){
            case COMPETITIVE -> {
                System.out.println("Du har lavet en konkurrencesvømmer og skal derfor vælge en coach og et hold");
                System.out.println("Her er en liste over coaches du kan vælge:");
                System.out.println(controller.listOfCoaches());
                System.out.println("Vælg en træner ved at skrive deres tal til venstre");
                int index = takeIntUserInput();
                controller.setCoachToMember(controller.findfirstMember(fullName, address), controller.getIndexInListOfCoaches(index));
                System.out.println("Du har tilføjet en træner");
            }
            case COACH -> System.out.println("Du har oprettet en træner");
            case MEMBER -> System.out.println("Du har oprettet et medlem");
            case FAILED_CREATE -> System.out.println("""
                    Der er sket en fejl.
                    Fornavn og efternavn skal have mindst to bogstaver i sig.
                    Det kan være du har prøvet at oprette en træner der også er konkurrencesvømmer. Det kan man ikke!
                    prøv igen:
                    """);
            default -> {}
        }
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
        System.out.println("Indtast medlemmets fødselsdato.");
        return createLocalDate();
    }

    private LocalDate createLocalDate() {
        int year;
        int month;
        int day;

        System.out.println("Indtast årstal:");
        year = takeIntUserInput(1920, Year.now().getValue());

        System.out.println("Indtast måned:");
        month = takeIntUserInput(1, 12);

        System.out.println("Indtast dag:");
        day = takeIntUserInput(1, YearMonth.of(year, month).lengthOfMonth());
        return LocalDate.of(year, month, day);
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
        return takeIntUserInput(10000000, 99999999);
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
        if (takeIntUserInput(1, 2) == 1) {
            return "Kvinde";
        } else {
            return "Mand";
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
        return takeIntUserInput(1, 2) == 1;
    }

    private boolean checkIfCoach() {
        System.out.println("""
                Er medlemmet træner?
                1. Ja
                2. Nej""");
        return takeIntUserInput(1, 2) == 1;
    }

    private boolean checkIfActive() {
        System.out.println("""
                Er medlemmet aktivt?
                1. Ja
                2. Nej""");
        return takeIntUserInput(1, 2) == 1;
    }

    private void exitProgram() {
        System.out.println("Afslutter...");
        uiIsRunning = false;
    }


    public void editMember() {
        Member selectedMember = findMember();
        if (selectedMember != null) {
            System.out.println("""
                    Hvad vil du gerne redigere?
                    1. Fulde navn.
                    2. Email.
                    3. Telefonnummer.
                    4. Addresse.
                    5. Aktiv/passiv.
                    6. Konkurrencesvømmer/ikke konkurrencesvømmer.
                    7. Træner/ikke træner.
                    """);
            switch (takeIntUserInput()) {
                case 1 -> {
                    selectedMember.setFullName(createName());
                    System.out.println("Fulde navn er blevet opdateret til: " + selectedMember.getFullName());
                }
                case 2 -> {
                    selectedMember.setEmail(createEmail());
                    System.out.println("Email er blevet opdateret til: " + selectedMember.getEmail());
                }
                case 3 -> {
                    selectedMember.setPhoneNumber(createPhoneNumber());
                    System.out.println("Telefonnummer er blevet opdateret til: " + selectedMember.getPhoneNumber());
                }
                case 4 -> {
                    selectedMember.setAddress(createAddress());
                    System.out.println("Addresse er blevet opdateret til: " + selectedMember.getAddress());
                }
                case 5 -> {
                    selectedMember.getMembership().setActive(checkIfActive());
                    System.out.println("Medlemsstatus opdateret.");
                }
                case 6 -> {
                    selectedMember.getMembership().setCompetetive(checkIfCompetitive());
                    System.out.println("Medlemsstatus opdateret.");
                }
                case 7 -> {
                    selectedMember.getMembership().setCoach(checkIfCoach());
                    System.out.println("Medlemsstatus opdateret.");
                }
                default -> System.out.println("Ugyldigt valg!");
            }
            controller.saveData();
        }
    }

    private void sortLines() {
        System.out.println("""
                Hvilken attribut vil du gerne sortere efter?
                        1. Fulde navn.
                        2. Bruger ID.
                        3. Fødselsdato.
                        4. Email:
                        5. Telefonnummer.
                        6. Adresse.
                        7. Køn.
                        8. Aktiv status.
                        9. Senior status.
                        10. Konkurrencesvømmer status.
                        11. Træner status.
                """);
    }
//TODO: Refaktorering
    private void registerPerformance() {
        Member selectedMember = null;
        do {
            System.out.println("Navnet på svømmeren du vil registrere en tid til");
            String fullName = scanner.nextLine();
            System.out.println(controller.listOfCompetetionsSwimmersByName(fullName));
            System.out.println("Skriv tallet på den konkurrencesvømmer du vil vælge");
            int index = takeIntUserInput();
            try {

                selectedMember = controller.getCompetetionSwimmerInListByIndex(index, fullName);
            } catch (IndexOutOfBoundsException e){
                System.out.println("Der blev ikke fundet et medlem");
            }
            if (selectedMember == null) {
                System.out.println("Prøv igen. Medlem ikke fundet");
            }
        } while(selectedMember == null);
        SwimDiscipline chosenDiscipline = getswimDiscipline();
        System.out.println("Hvad er tiden i sekunder med 2 decimaler");
        double performanceTime = takeDoubleUserInput();
        System.out.println("""
                Er tiden lavet i konkurrence?
                1. Ja
                2. Nej""");
        boolean timeMadeInCompetition = takeIntUserInput(1, 2) == 1;

        System.out.println("Indtast dato for hvornår tiden er sat:");
        LocalDate dateForPerformance = createLocalDate();
        controller.registerPerformance(selectedMember, String.valueOf(chosenDiscipline),performanceTime,timeMadeInCompetition,dateForPerformance);
        controller.savePerformance();
    }
    private SwimDiscipline getswimDiscipline (){
        System.out.println("""
                Hvilken disciplin er tiden sat i?
                1. Butterfly.
                2. Crawl.
                3. Rygcrawl.
                4. Bryst.
                """);
        int choice;
        choice = takeIntUserInput(1, 4);
        switch (choice) {
            case 1 -> {return SwimDiscipline.BUTTERFLY;}
            case 2 -> {return SwimDiscipline.CRAWL;}
            case 3 -> {return SwimDiscipline.RYGCRAWL;}
            case 4 -> {return SwimDiscipline.BRYST;}
        }
        return getswimDiscipline();
    }

    private void getSpecificMembersPerformanceInDisclipin(){

        Member selectedMember = null;
        do {
            System.out.println("Navnet på svømmeren du vil registrere en tid til");
            String fullName = scanner.nextLine();
            System.out.println(controller.listOfCompetetionsSwimmersByName(fullName));
            if (!controller.listOfCompetetionsSwimmersByName(fullName).isEmpty()) {
                System.out.println("Skriv tallet på den konkurrencesvømmer du vil vælge");
                int index = takeIntUserInput();
                try {
                    selectedMember = controller.getCompetetionSwimmerInListByIndex(index, fullName);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Der blev ikke fundet et medlem");
                }
                if (selectedMember == null) {
                    System.out.println("Medlem ikke fundet. Prøv igen");
                }
            } else System.out.println("Der blev ikke fundet et medlem. Prøv igen");
        } while(selectedMember == null);
        SwimDiscipline chosenDiscipline = getswimDiscipline();
        if (!controller.getOneSwimmersPerformances(selectedMember, chosenDiscipline).isEmpty()) {
            System.out.println(controller.getOneSwimmersPerformances(selectedMember, chosenDiscipline));
        } else System.out.println("Det her medlem har ingen resultater i den valgte disciplin.");
    }
    private void sortPerformance(){
        int choice = genderChoice();
        int choice2 = categoryChoice();
        System.out.println(controller.sortPerformance(choice,choice2));
    }
    private void top5Swimmers(){
        int choice = genderChoice();
        int choice2 = categoryChoice();
        System.out.println(controller.getTop5Swimmers(choice, choice2));
    }
    private int genderChoice(){
        System.out.println("""
                Hvilket køn vil du sortere efter
                1. Mand.
                2. Kvinde.
                """);
        int choice = takeIntUserInput(1, 2);
        return choice;
    }

    private int categoryChoice(){
        System.out.println("""
                Hvilken kategori vil du se resultater for
                1. Butterfly.
                2. Crawl.
                3. Rygcrawl.
                4. Bryst.
                """);
        int choice = takeIntUserInput(1, 4);
        return choice;
    }

    private void getTotalSubscriptionIncome() {
        System.out.println("Den totale årlige indkomst fra kontingent er: " + controller.getTotalSubscriptionIncome() + ",- kr.");
        controller.saveAccountantList();
    }

    private void getSubscriptionPriceSingleUser() {
        Member selectedMember = findMember();
        System.out.println("Den årlige abonnementspris for dette medlem er: " + selectedMember.calculateSubscriptionPrice() + ",- kr.");
    }

    private Member findMember(){
        System.out.println("Søg på navn, user-ID eller telefonnummer");
        Member selectedMember;
        ArrayList<Member> foundMembers = new ArrayList<>();
        while(foundMembers.isEmpty()) {
            String search = scanner.nextLine();
            foundMembers = controller.findMembers(search);
            if (!foundMembers.isEmpty()) {
                int index = 1;
                for (Member member : foundMembers) {
                    System.out.println(index + ". " + controller.getMemberName(member));
                    index += 1;
                }
            } else {
                System.out.println("Din søgning gav ingen resultater. Prøv igen:");
            }
        }
        System.out.println("Dette er dit søgeresultat. Vælg venligst et medlem:");
        int choice = takeIntUserInput();

        selectedMember = controller.getMemberFromIndex(choice, foundMembers);
        System.out.println("Det her er dit valgte medlem:");
        System.out.println(selectedMember);
        return selectedMember;
    }

    private void changeHasPaid(){
      Member selectedMember = findMember();
        System.out.println("""
                Har vedkommende betalt?
                1. Ja
                2. Nej""");
        controller.setHasPaidForMember(selectedMember, takeIntUserInput(1, 2) == 1);
        System.out.println("Medlemmets betalingsstatus er nu opdateret");
        controller.saveAccountantList();
    }

    private void seeMembersInArrears(){
        System.out.println(controller.arrearsList());
    }
    private void printAllNames(){
        System.out.println(controller.printAllNames());
    }
}