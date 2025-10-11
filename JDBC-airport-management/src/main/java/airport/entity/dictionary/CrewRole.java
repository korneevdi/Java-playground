package airport.entity.dictionary;

public class CrewRole {

    private int id;
    private String name;

    public CrewRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return """
                CrewRole
                    ID: %s,
                    name: %s
                """.formatted(id, name);
    }
}
