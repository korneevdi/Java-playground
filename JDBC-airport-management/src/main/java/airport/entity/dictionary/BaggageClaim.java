package airport.entity.dictionary;

import java.util.Objects;

public class BaggageClaim {

    private int id;
    private String number;

    public BaggageClaim(int id, String number) {
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
                BaggageClaim
                    ID: %s,
                    number: %s
                """.formatted(id, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaggageClaim that = (BaggageClaim) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
