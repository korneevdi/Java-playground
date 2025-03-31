package validator;

public class Main {
    public static void main(String[] args) {

        try{
            User user1 = new User("");
            User user2 = new User("Alex");
            Validator.validate(user1);
            Validator.validate(user2);
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
}
