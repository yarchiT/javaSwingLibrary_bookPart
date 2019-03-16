package dto;

public class BookDto {

    private String name;
    private String author;
    private String type;
    private int amountOfPages;
    private int editionNumber;
    private int amountOfBooks;

    public BookDto(){

    }
    public BookDto(String name, String author, String type, int amountOfPages, int editionNumber, int amountOfBooks) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.amountOfPages = amountOfPages;
        this.editionNumber = editionNumber;
        this.amountOfBooks = amountOfBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmountOfPages() {
        return amountOfPages;
    }

    public void setAmountOfPages(int amountOfPages) {
        this.amountOfPages = amountOfPages;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public int getAmountOfBooks() {
        return amountOfBooks;
    }

    public void setAmountOfBooks(int amountOfBooks) {
        this.amountOfBooks = amountOfBooks;
    }
}
