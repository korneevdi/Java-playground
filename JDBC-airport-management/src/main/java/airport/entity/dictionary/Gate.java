package airport.entity.dictionary;

import java.util.Objects;

public class Gate {

    private int id;
    private String number;

    public Gate(int id, String number) {
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
                Gate
                    ID: %s,
                    number: %s
                """.formatted(id, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gate gate = (Gate) o;
        return Objects.equals(number, gate.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
