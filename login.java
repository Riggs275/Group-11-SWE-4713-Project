public class Login {
  // Method for logging in if username and password are correct
  public String handleLogin (String username, String password) {
    if ("user".equals(username) && "password".equals(password)) {
            return "dashboard";
        }
        return "login";
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
  public String createUser () {
    return "login";
  }
}
