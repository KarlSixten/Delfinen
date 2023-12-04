package ui;

import domain.*;

import java.time.*;
import java.util.ArrayList;

import java.util.InputMismatchException;
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
                1. Beregn årlig indkomst
                2.
                3.
                4.
                8. Skift rolle.
                9. Afslut program.
                """);
        switch (takeIntUserInput()) {
            case 1 -> getTotalSubscriptionIncome();
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 8 -> selectUserRole();
            case 9 -> exitProgram();
            default -> System.out.println("Ugyldigt valg! Prøv igen:\n");
        }
    }

    private void coachSelection() {
        System.out.println("""
                Vælg den funktion du vil tilgå:
                1. Register performance
                2. Se performance
                3. Sorter svømmere
                4. Se top 5 svømmere
                5. Se en specifik svømmers resultater inden for disciplin.
                8. Skift rolle.
                9. Afslut program.
                """);
        controller.loadPerformances();
        switch (takeIntUserInput()) {
            case 1 -> {
                registerPerformance();
            }
            case 2 -> {
            }
            case 3 -> { sortPerformance();
            }
            case 4 -> {top5Swimmers();
            }
            case 5 -> getSpecificMembersPerformanceInDisclipin();
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

        int choice = takeIntUserInput();
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

        } else System.out.println("Kunne ikke slette medlemmet.");
    }


    private void findMembers() {
        System.out.println("Search by Name, user-ID or phone number");
        String search = scanner.nextLine();
        ArrayList<Member> foundMembers = controller.findMembers(search);
        int index = 1;
        for (Member member : foundMembers) {
            System.out.println(index + ". " + controller.getMemberName(member));
            index += 1;
        }
        System.out.println("This is your search result. Please choose a member by their number");
        int choice = takeIntUserInput();
        scanner.nextLine();
        Member selectedMember = controller.getMemberFromIndex(choice, foundMembers);
        System.out.println("This is your selected member:");
        System.out.println(selectedMember);
    }

    private void showAllMembers() {
        System.out.println("Skal der sorteres i medlemerne" +
                " Vælg den funktion du vil tilgå:\n" +
                "        1. Sortering med en primær\n" +
                "        2. Sortering med primær og sekundær");
        System.out.println(controller.getAllMemberNames());
        int userchoice = takeIntUserInput();
        scanner.nextLine();
        if (userchoice == 1) {
            sortLines();
            int choice = takeIntUserInput();
            scanner.nextLine();
            controller.sortMember(choice);
            System.out.println("List sorted");
            controller.saveData();
        } else if (userchoice == 2) {
            sortLines();
            int choice = takeIntUserInput();
            sortLines();
            int choice2 = takeIntUserInput();
            scanner.nextLine();
            controller.sortMemberPrimaryandSecundary(choice, choice2);
            System.out.println("List sorted");
            controller.saveData();

        }

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
        System.out.println("Indtast fødselsdato:");
        return createLocalDate();
    }

    private LocalDate createLocalDate() {
        int year;
        int month;
        int day;

        System.out.println("Indtast årstal:");
        year = takeIntUserInput();
        while (!(year >= 1920 && year <= Year.now().getValue())) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            year = takeIntUserInput();
        }

        System.out.println("Indtast måned:");
        month = takeIntUserInput();
        while (!(month >= 1 && month <= 12)) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            month = takeIntUserInput();
        }

        System.out.println("Indtast dag:");
        day = takeIntUserInput();
        while (!(day >= 1 && day <= YearMonth.of(year, month).lengthOfMonth())) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            day = takeIntUserInput();
        }
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
        while (userSelection != 1 && userSelection != 2) {
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
        while (userSelection != 1 && userSelection != 2) {
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
        while (userSelection != 1 && userSelection != 2) {
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
        while (userSelection != 1 && userSelection != 2) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            userSelection = takeIntUserInput();
        }
        return userSelection == 1;
    }

    private void exitProgram() {
        System.out.println("Exiting...");
        uiIsRunning = false;
    }

    public void editMember() {
        System.out.println("Search by Name, user-ID or phone number");
        String search = scanner.nextLine();
        ArrayList<Member> foundMembers = controller.findMembers(search);
        int index = 1;
        for (Member member : foundMembers) {
            System.out.println(index + ". " + controller.getMemberName(member));
            index += 1;
        }
        System.out.println("This is your search result. Please choose a member by their number");
        int choice = takeIntUserInput();
        Member selectedMember = controller.getMemberFromIndex(choice, foundMembers);
        System.out.println("This is your selected member:");
        System.out.println(selectedMember);
        if (selectedMember != null) {
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
            switch (takeIntUserInput()) {
                case 1 -> {
                    selectedMember.setFullName(createName());
                    System.out.println("Fulde navn er blevet opdateret til: " + selectedMember.getFullName());
                }
                case 2 -> {
                    selectedMember.setEmail(createEmail());
                    System.out.println("Email is now updated to: " + selectedMember.getEmail());
                }
                case 3 -> {
                    selectedMember.setPhoneNumber(createPhoneNumber());
                    System.out.println("Tlf.number is now updated to: " + selectedMember.getPhoneNumber());
                }
                case 4 -> {
                    selectedMember.setAddress(createAddress());
                    System.out.println("Address is now updated to: " + selectedMember.getAddress());
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
                What primary attribute do you want to sort the database by?
                        1. Fullname:
                        2. UserID:
                        3. BirthDate:
                        4. Email:
                        5. PhoneNumber:
                        6. Adress:
                        7. Gender:
                        8. Is member active:
                        9. Is member a senior
                        10. Is member competetive
                        11. Coach                         
                """);
    }

    private void registerPerformance() {
        Member selectedMember = null;
        do {
            System.out.println("Navnet på svømmeren du vil registrere en tid til");
            String fullName = scanner.nextLine();
            System.out.println(controller.listOfCompetetionsSwimmersByName(fullName));
            System.out.println("Skriv tallet på den competitionssvømmer du vil vælge");
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
        scanner.nextLine();
        System.out.println("Hvad er tiden i sekunder med 2 decimaler");
        double performanceTime = 0;
        while(true) {
            try {
                performanceTime = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Du skal skrive et tal");
                scanner.nextLine();
            }
        }
        System.out.println("Er tiden lavet i konkurrence (ja/nej)");
        boolean timeMadeInCompetition = false;
        String input = "";
        do {
            input = scanner.nextLine().toLowerCase();
                if (input.equals("ja")) {
                    timeMadeInCompetition = true;
                } else if (input.equals("nej")) {
                    timeMadeInCompetition = false;
                } else {
                System.out.println("Ugyldigt valg, vælg ja eller nej");
            }
        } while (!input.equals("ja") && !input.equals("nej"));
        System.out.println("Indtast dato for hvornår tiden er sat:");
        LocalDate dateForPerformance = createLocalDate();
        controller.registerPerformance(selectedMember, String.valueOf(chosenDiscipline),performanceTime,timeMadeInCompetition,dateForPerformance);
        controller.savePerformance();
    }
    private SwimDiscipline getswimDiscipline (){
        System.out.println("Hvilken disciplin er tiden sat i \n" +
                "1. Butterfly \n" +
                "2. Crawl \n" +
                "3. Rygcrawl \n" +
                "4. Bryst \n");
        int choice = 0;
        choice = takeIntUserInput();
        while(!(choice >= 1 && choice <= 4)) {
            System.out.println("Ugyldigt valg! Prøv igen:");
            choice = takeIntUserInput();
            }
        switch (choice) {
            case 1:
                return SwimDiscipline.BUTTERFLY;

            case 2:
                return SwimDiscipline.CRAWL;

            case 3:
                return SwimDiscipline.RYGCRAWL;

            case 4:
                return SwimDiscipline.BRYST;
        }
        return getswimDiscipline();
    }

    public void getSpecificMembersPerformanceInDisclipin(){

        Member selectedMember = null;
        do {
            System.out.println("Navnet på svømmeren du vil registrere en tid til");
            String fullName = scanner.nextLine();
            System.out.println(controller.listOfCompetetionsSwimmersByName(fullName));
            System.out.println("Skriv tallet på den competitionssvømmer du vil vælge");
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
        System.out.println(controller.getOneSwimmersPerformances(selectedMember, chosenDiscipline));
    }
    private void sortPerformance(){
        System.out.println("""
                Hvilket køn vil du sortere efter
                1. Mand
                2. Kvinde
                """);
        int choice = takeIntUserInput();
        while (true){

            if (choice == 1 || choice == 2){
                break;
            }
            else {
                System.out.println("Prøv igen. Skriv 1 eller 2");
                choice = takeIntUserInput();
            }
        }
        System.out.println("""
                Hvilken kategori vil du se resultater for
                1. Butterfly
                2. Crawl
                3. Rygcrawl
                4. Bryst
                """);
        int choice2 = takeIntUserInput();
        while (true){

            if (choice2 == 1 || choice2 == 2 || choice2 == 3 || choice2 == 4){
                break;
            }
            else {
                System.out.println("Prøv igen. Skriv 1,2,3 eller 4");
                choice2 = takeIntUserInput();
            }
        }
        System.out.println(controller.sortPerformance(choice,choice2));
    }

    private void top5Swimmers(){
        System.out.println("""
                Hvilket køn vil du sortere efter
                1. Mand
                2. Kvinde
                """);
        int choice = takeIntUserInput();
        while (true){

            if (choice == 1 || choice == 2){
                break;
            }
            else {
                System.out.println("Prøv igen. Skriv 1 eller 2");
                choice = takeIntUserInput();
            }
        }
        System.out.println("""
                Hvilken kategori vil du se resultater for
                1. Butterfly
                2. Crawl
                3. Rygcrawl
                4. Bryst
                """);
        int choice2 = takeIntUserInput();
        while (true){

            if (choice2 == 1 || choice2 == 2 || choice2 == 3 || choice2 == 4){
                break;
            }
            else {
                System.out.println("Prøv igen. Skriv 1,2,3 eller 4");
                choice2 = takeIntUserInput();
            }
        }
        System.out.println(controller.getTop5Swimmers(choice, choice2));
    }

    private void getTotalSubscriptionIncome() {
        System.out.println("Den totale årlige indkomst fra kontingent er: " + controller.getTotalSubscriptionIncome() + ",- kr.");
    }
}










