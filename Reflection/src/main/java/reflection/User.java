package reflection;

import java.time.LocalDate;

public class User extends Person implements Comparable {

    private String login;
    private LocalDate lastLoginDate;
    private boolean isActive;
    private String password = "secret password";

    public User(String name, int age) {
        super(name, age);
    }

    @Deprecated()
    private void purchase() {
        System.out.println("Bought something");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
