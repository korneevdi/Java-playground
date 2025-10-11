package airport.entity.dictionary;

public class Runway {

    private int id;
    private String number;

    public Runway(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return """
                Runway
                    ID: %s,
                    name: %s
                """.formatted(id, number);
    }
}
