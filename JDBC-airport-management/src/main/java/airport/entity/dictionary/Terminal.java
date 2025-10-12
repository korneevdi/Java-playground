package airport.entity.dictionary;

import java.util.Objects;

public class Terminal {

    private int id;
    private String number;

    public Terminal(int id, String number) {
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
                Terminal
                    ID: %s,
                    number: %s
                """.formatted(id, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        return Objects.equals(number, terminal.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
