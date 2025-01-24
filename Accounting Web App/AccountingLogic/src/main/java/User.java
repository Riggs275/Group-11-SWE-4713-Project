import java.util.Date;

public class User {

    // Attributes
    private String FirstName;
    private String LastName;
    private String Username;
    private String Password;

    public String ErrorMessage;
    private boolean IsError;



    // Constructor
    public User() {
        FirstName = "";
        LastName = "";
        Username = "";
        Password = "";
    }



    // Methods

    // Getters and Setters for names
    public String getFirstName() {
        return FirstName;
    }

    public String setFirstName(String FN) {
        FirstName = FN;
        return "First name set successfully!";
    }

    public String getLastName() {
        return LastName;
    }

    public String setLastName(String LN) {
        LastName = LN;
        return "Last name set successfully!";
    }

    // Getters and Setters for username
    public String getUsername() {
        return Username;
    }

    public void setUsername(Date creationDate) {
        /* PREQ-20: A username should be made of the first name initial,
         the full last name, and a four digit (two-digit month and
         two-digit year) of when the account is created (e.g., jdoe0125). */

        Username = (FirstName.substring(0, 1).toLowerCase() +
                    LastName.toLowerCase() +
                    String.format("%02d", (creationDate.getMonth() + 1)) +
                    (creationDate.getYear() - Math.floor(creationDate.getYear()))
        );
    }

    // Setters for password (No getters for security reasons)
    public String setPassword(String ProposedPassword) {
        /* PREQ-10 & 11: Passwords must be a minimum of 8 characters,
         must start with a letter, must have a letter, a number and
         a special character, if this requirement is not satisfied,
         display an appropriate error message.

         Password used in the past cannot be used when password is reset */

        String[] AllowedSpecialCharacters = {"!", "@", "#", "$", "%", "^", "&", "*", "\"", ":"};
        // This list was not explicitly defined in the instructions
        // As such this list can change

        String[] Letters = {"A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String[] Numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        if(ProposedPassword.length() > 8) {
            ErrorMessage = "Password length is too short!";
            IsError = true;
        }
        else if(!Letters.toString().contains(ProposedPassword.substring(0, 1).toUpperCase())) {
            ErrorMessage = "Password must start with a letter!";
            IsError = true;
        }
        else if(!ProposedPassword.contains(Numbers.toString())) {
            ErrorMessage = "Password must contain a number!";
            IsError = true;
        }
        else if(!ProposedPassword.contains(AllowedSpecialCharacters.toString())) {
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

}
