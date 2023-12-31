package data;

import domain.Member;
import domain.Membership;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
Database database;
Member member1;
Member member2;
Member member3;
    @BeforeEach
    void setUp() throws IOException {
database = new Database("Test.csv");
    }

    @AfterEach
    void tearDown() {
        database = null;
    }
    @Test
    void addMember(){
        //Arrange
        member1 = new Member("Aleksander", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        member2 = new Member("Jonas", "Jona0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        member3 = new Member("Karl", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        database.getMembersArrayList().addAll(List.of(member1,member2,member3));
        int expectedSize = database.getMembersArrayList().size();

        //Act
        database.createNewUser("Karl", LocalDate.of(1997,10,1), "Karl@karlmail.dk", 19181919, "Karlvej 15", "male",true,true,true,true);

        //Assert
        assertEquals(expectedSize, database.getMembersArrayList().size());

    }
    @Test
    void deleteMember() {
        //Arrange
        int expectedSize = database.getMembersArrayList().size();
        member1 = new Member("Aleksander", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        member2 = new Member("Aleksander", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        member3 = new Member("Aleksander", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        database.getMembersArrayList().addAll(List.of(member1,member2,member3));
        //Act
        database.deleteMember(member1);
        int actualSize = database.getMembersArrayList().size() -2;
        //Assert
        assertEquals(expectedSize,actualSize);
    }
    @Test
    void findMember(){
        member1 = new Member("Aleksander", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        database.getMembersArrayList().add(member1);
        //Arrange
        String expectedName = "Aleksander";

        //Act
        String actualName = member1.getFullName();

        //Assert
        assertEquals(expectedName,actualName);
    }

    @Test
    void calculateTotalSubscriptions() {
        int actualResult = database.getTotalSubscriptionIncome();
        int expectedResult = 15500;

        assertEquals(actualResult, expectedResult);
    }
}