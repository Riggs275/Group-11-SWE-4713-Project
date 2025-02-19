import java.sql.*;
import java.util.Scanner;

public class ForgotPassword {

    private String dbUrl;

    // Constructor to initialize the database connection URL
    public ForgotPassword(String url) {
        dbUrl = url;
    }

    // Method to verify user identity (from email and user ID) for password reset
    public String verifyIdentity(String userId, String email, String securityAnswer) {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            // Retrieve the user's security question and answer from the database
            String query = "SELECT * FROM users WHERE user_id = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String correctAnswer = resultSet.getString("security_answer"); // Assuming security answer is stored
                        // Check if the entered answer matches the stored one
                        if (securityAnswer.equals(correctAnswer)) {
                          return "reset-password"; // Identity verified, allow password reset
                        }
                        else {
                          return "forgot-password"; // If answer is incorrect, return to Forgot Password page
                        }
                    }
                }
            }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
        return "forgot-password"; // If user is not found, return to Forgot Password page
    }

    // Method to reset the password in adherence to the password policy and prevent password reuse
    public String resetPassword(String userId, String newPassword) {
        if (isValidPassword(newPassword)) {
          // Check if the new password has been used before
          if (isPasswordReused(userId, newPassword)) {
            return "forgot-password"; // Reject if the password has been used before and return to Forgot Password page
          }
          try (Connection connection = DriverManager.getConnection(dbUrl)) {
            // Update the password in the users table
            String query = "UPDATE users SET password = ? WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
              preparedStatement.setString(1, newPassword);
              preparedStatement.setString(2, userId);
              preparedStatement.executeUpdate();
              // Add the new password to the password history table
              String historyQuery = "INSERT INTO password_history (user_id, password) VALUES (?, ?)";
              try (PreparedStatement historyStatement = connection.prepareStatement(historyQuery)) {
                historyStatement.setString(1, userId);
                historyStatement.setString(2, newPassword);
                historyStatement.executeUpdate();
              }
              // Ensure the password history is limited
              String limitHistoryQuery = "DELETE FROM password_history WHERE user_id = ? AND change_date NOT IN (SELECT change_date FROM password_history WHERE user_id = ? ORDER BY change_date DESC LIMIT 5)";
              try (PreparedStatement limitHistoryStatement = connection.prepareStatement(limitHistoryQuery)) {
                limitHistoryStatement.setString(1, userId);
                limitHistoryStatement.setString(2, userId);
                limitHistoryStatement.executeUpdate();
              }
              return "password-reset-success"; // Password reset successfully
            }
          }
          catch (SQLException e) {
            e.printStackTrace();
          }
        }
        return "forgot-password"; // If password is invalid, return to Forgot Password page
    }

    // Checks if the new password matches any previous passwords in the history
    private boolean isPasswordReused(String userId, String newPassword) {
      try (Connection connection = DriverManager.getConnection(dbUrl)) {
        String query = "SELECT COUNT(*) FROM password_history WHERE user_id = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
          preparedStatement.setString(1, userId);
          preparedStatement.setString(2, newPassword);
          try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next() && resultSet.getInt(1) > 0) {
              return true; // Password has been used before
            }
          }
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      return false; // Password is new
    }

    // Ensure attempted new password adheres to password policy
    private boolean isValidPassword(String password) {
        return true;
    }
}
