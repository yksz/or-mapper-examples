package entity;

public class Book {
    public enum Category {
        UNKNOWN,
        NOVEL,
        MAGAZINE,
    }

    private Integer id;
    private String title;
    private String author;
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book [id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", author=");
        builder.append(author);
        builder.append(", category=");
        builder.append(category);
        builder.append("]");
        return builder.toString();
    }

}
