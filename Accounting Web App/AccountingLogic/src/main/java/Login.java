import java.sql.*;

public class Login {
  // Database connection URL
  /*private String dbUrl;

  // Constructor to initialize the database connection URL
  public Login(String url) {
      dbUrl = url;
  }

  // Method for logging in if username and password are correct
  public String handleLogin (String username, String password) {
    try (Connection connection = DriverManager.getConnection(dbUrl)) {
      String query = "SELECT * FROM users WHERE username = ? AND password = ?";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        //preparedStatement.setString(1, inputUsername);
        //preparedStatement.setString(2, inputPassword);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          if (resultSet.next()) {
            return "dashboard"; // If user is found, move on to dashboard screen
          }
        }
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return "login"; // If login fails, return to login page
  }
  // Takes user to Forgot Password page when the respective button is selected
  public String showForgotPasswordPage () {
    return "forgot-password";
  }
  // Takes user to Create User page when the respective button is selected
  public String createUserPage () {
    return "create-user";
  }
  // Returns to login page after a new user is created
  public String createUser (String username, String password) {
    // Check if the username already exists
    try (Connection connection = DriverManager.getConnection(dbUrl)) {
      String checkQuery = "SELECT * FROM users WHERE username = ?";
      try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
        checkStatement.setString(1, username);
        try (ResultSet resultSet = checkStatement.executeQuery()) {
          if (resultSet.next()) {
            return "create-user"; // If the username is taken, return to create user page
          }
        }
      }

      // Add the new user to the database if it doesn't already exist
      String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
      try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
        insertStatement.setString(1, username);
        insertStatement.setString(2, password);
        insertStatement.executeUpdate();
        return "login";
      }

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return "error";
  }
  
  // Method to handle Forgot Password functionality
  public String handleForgotPassword(String userId, String email, String securityAnswer) {
    ForgotPassword forgotPassword = new ForgotPassword(dbUrl);
    return forgotPassword.verifyIdentity(userId, email, securityAnswer);
  }

  // Method to handle password reset
  public String resetPassword(String userId, String newPassword) {
    ForgotPassword forgotPassword = new ForgotPassword(dbUrl);
    return forgotPassword.resetPassword(userId, newPassword);
  }*/
}
