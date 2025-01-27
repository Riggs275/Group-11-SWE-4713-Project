import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class UserUnitTests {

    @Test
    void setUsername_RandomDate_ReturnsSuccess() {

        // Arrange
        Date testDate = new Date(2023, 5, 1);
        final String firstName = "John";
        final String lastName = "Doe";
        final String expectedUsername = "jdoe0623"; // 06 to represent June

        User testUser = new User();
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setUsername(testDate);

        // Act
        String actualUsername = testUser.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void setPassword_ValidPassword_ReturnsSuccess() {

        // Arrange
        final String expected = "Password changed successfully!";
        final String testPassword = "P@ssw0rd";
        User testUser = new User();

        // Act
        String actual = testUser.setPassword(testPassword);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setPassword_InvalidLength_ReturnsError() {

        // Arrange
        final String expected = "Password length is too short!";
        final String testPassword = "test";
        User testUser = new User();

        // Act
        String actual = testUser.setPassword(testPassword);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setPassword_NoStartingLetter_ReturnsError() {

        // Arrange
        final String expected = "Password must start with a letter!";
        final String testPassword = "1234test!";
        User testUser = new User();

        // Act
        String actual = testUser.setPassword(testPassword);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setPassword_NoNumber_ReturnsError() {

        // Arrange
        final String expected = "Password must contain a number!";
        final String testPassword = "test!!!!";
        User testUser = new User();

        // Act
        String actual = testUser.setPassword(testPassword);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setPassword_NoSpecialCharacter_ReturnsError() {

        // Arrange
        final String expected = "Password must contain a special character!";
        final String testPassword = "test1234";
        User testUser = new User();

        // Act
        String actual = testUser.setPassword(testPassword);

        // Assert
        assertEquals(expected, actual);
    }
}
