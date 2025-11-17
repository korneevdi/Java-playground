package annotation.validation;

public class User {

    private String name;
    private int age;
    private String login;
    @ValidateLength(min = 18)
    private String password;

    public User(String name, int age, String login, String password) {
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
    }
}
