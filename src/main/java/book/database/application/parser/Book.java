package book.database.application.parser;

import java.util.HashSet;
import java.util.Set;

public class Book {
    private String id;
    private String title;
    private String summary;
    private String author;
    private String category;
    private final Set<Link> imageLinks = new HashSet<>();
    private final Set<Link> downloadLinks = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getSummary() {
        return summary;
    }

    public void setDownloadLinks(Set<Link> downloadLinks) {
        this.downloadLinks.addAll(downloadLinks);
    }

    public Set<Link> getDownloadLinks() {
        return downloadLinks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setImageLinks(Set<Link> imageLinks) {
        this.imageLinks.addAll(imageLinks);
    }

    public Set<Link> getImageLinks() {
        return imageLinks;
    }
}
