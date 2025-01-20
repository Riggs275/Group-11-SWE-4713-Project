public class User {
    private Long id; // Unique id number for every user
    private String username; // Username for user
    private String password; // Password for user

    // Getters and Setters for id
    public Long getId() {
        return id;
    }
    public void setId(Long inputId) {
        inputId = id;
    }

    // Getters and Setters for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String inputUsername) {
        inputUsername = username;
    }

    // Getters and Setters for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String inputPassword) {
        inputPassword = password;
    }
}
