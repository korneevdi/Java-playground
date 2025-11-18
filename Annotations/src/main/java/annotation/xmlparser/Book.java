package annotation.xmlparser;

public class Book {

    private String name;
    private String author;

    @XmlElement(name = "book")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
