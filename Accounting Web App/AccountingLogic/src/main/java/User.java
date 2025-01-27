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
        /* PREQ-20: A username should be made of the first name initial,
         the full last name, and a four digit (two-digit month and
         two-digit year) of when the account is created (e.g., jdoe0125). */

        Username = (FirstName.substring(0, 1).toLowerCase() +
                    LastName.toLowerCase() +
                    String.format("%02d", (creationDate.getMonth()) + 1) +  // getMonth range is 0 - 11
                   (creationDate.getYear() % 1000)
        );
    }

    // Setters for password (No getters for security reasons)
    public String setPassword(String ProposedPassword) {
        /* PREQ-10 & 11: Passwords must be a minimum of 8 characters,
         must start with a letter, must have a letter, a number and
         a special character, if this requirement is not satisfied,
         display an appropriate error message.

         Password used in the past cannot be used when password is reset */

        if(ProposedPassword.length() < 8) {
            ErrorMessage = "Password length is too short!";
            IsError = true;
        }
        else if((ProposedPassword.toUpperCase().charAt(0) < 65) || (ProposedPassword.toUpperCase().charAt(0) > 90)) {
            ErrorMessage = "Password must start with a letter!";
            IsError = true;
        }
        else if(!CheckForNumbers(ProposedPassword)) {
            ErrorMessage = "Password must contain a number!";
            IsError = true;
        }
        else if(!CheckForSpecialCharacters(ProposedPassword)) {
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

    // Supplementary Methods
    private boolean CheckForNumbers(String Password) {

        boolean Result = false;

        String[] Numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        // This list was not explicitly defined in the instructions
        // As such this list is subject to change

        for(int i = 0; i < Password.length(); i++) {
            for (String number : Numbers) {
                if (Password.charAt(i) == number.charAt(0)) {
                    Result = true;
                    break;
                }
            }
        }
        return Result;
    }

    private boolean CheckForSpecialCharacters(String Password) {

        boolean Result = false;

        String[] AllowedSpecialCharacters = {"!", "@", "#", "$", "%", "^", "&", "*", "\"", ":"};
        // This list was not explicitly defined in the instructions
        // As such this list is subject to change

        for(int i = 0; i < Password.length(); i++) {
            for (String allowedSpecialCharacter : AllowedSpecialCharacters) {
                if (Password.charAt(i) == allowedSpecialCharacter.charAt(0)) {
                    Result = true;
                    break;
                }
            }
        }
        return Result;
    }

}
