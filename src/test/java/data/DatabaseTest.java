package data;

import domain.Member;
import domain.Membership;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
Database database;
Member member1;
Member member2;
    @BeforeEach
    void setUp() throws IOException {
database = new Database();
    }

    @AfterEach
    void tearDown() {
        database = null;
    }

    @Test
    void deleteMember() {
        int expectedSize = database.getMembersArrayList().size();
        member1 = new Member("Aleksander", "Alek0617", LocalDate.of(1993,10,7), "aleks@gmail.com", 42755293, "Kanalvej 15","male", new Membership(true,true,true,true));
        database.getMembersArrayList().add(member1);
        database.getMembersArrayList().remove(member1);

        int actualSize = database.getMembersArrayList().size();

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

        //
        assertEquals(expectedName,actualName);
    }
}