import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.time.LocalDate;

public class User {

   /* // Attributes
    private String FirstName;
    private String LastName;
    private String Username;
    private String Password;

    private String dbURL;

    public String ErrorMessage;
    private boolean IsError;



    // Constructor
    public User() {
        FirstName = "";
        LastName = "";
        Username = "";
        Password = "";
    }

    public User (String fn, String LN, String Pass, String Url) {
        String currentDate = LocalDate.now().toString();

        FirstName = fn;
        LastName = LN;
        Username = setUsername(currentDate);
        Password = setPassword(Pass);
        dbURL = Url;
    }


    // Methods

    // Getters and Setters for names
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FN) {
        FirstName = FN;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LN) {
        LastName = LN;
    }

    // Getters and Setters for username
    public String getUsername() {
        return Username;
    }

    public void setUsername(Date creationDate) {
        //PREQ-20: A username should be made of the first name initial, the full last name, and a four digit (two-digit month and two-digit year) of when the account is created (e.g., jdoe0125). 

        Username = (FirstName.substring(0, 1).toLowerCase() +
                    LastName.toLowerCase() +
                    String.format("%02d", (creationDate.getMonth()) + 1) +  // getMonth range is 0 - 11
                   (creationDate.getYear() % 1000)
        );
    }

    // Setters for password (No getters for security reasons)
    public String setPassword(String ProposedPassword) {
        //PREQ-10 & 11: Passwords must be a minimum of 8 characters, must start with a letter, must have a letter, a number and a special character, if this requirement is not satisfied, display an appropriate error message. Password used in the past cannot be used when password is reset 

        if(ProposedPassword.length() < 8) {
            ErrorMessage = "Password length is too short!";
            IsError = true;
        }
        else if(ProposedPassword.matches(".*[a-zA-Z].*")) {
            ErrorMessage = "Password must start with a letter!";
            IsError = true;
        }
        else if(!ProposedPassword.matches(".*[0-9].*")) {
            ErrorMessage = "Password must contain a number!";
            IsError = true;
        }
        else if(!ProposedPassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            ErrorMessage = "Password must contain a special character!";
            IsError = true;
        }


        if(IsError) {
            return ErrorMessage;
        }

        Password = ProposedPassword;
        return "Password changed successfully!";
    }

    public String ResetPassword() {
        Password = "";
        return "Password has been reset successfully!";
    }

    public void SendToDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = null;

        try {
            userJson = objectMapper.writeValueAsString(User);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO user_data (user_json) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(dbURL, Username, Password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userJson);
            int rowsAffected = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public string RetrieveFromDatabase() {
        String SQL = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(dbURL, Username, Password)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    String userJson = rs.getString("user_json");

                    ObjectMapper objectMapper = new ObjectMapper();
                    User retrievedUser = objectMapper.readValue(userJson, User.class);
                }
            }
        }
        catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
            return "User was not found.";
        }

        return "Retrival Successful.";
    }*/
        
}
