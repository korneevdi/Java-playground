package validator;

public class User {
    @NotEmpty(message = "User name cannot be empty")
    private String name;

    public User(String name) {
        this.name = name;
    }
}
