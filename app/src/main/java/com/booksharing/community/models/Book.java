package com.booksharing.community.models;

/**
 * Book Model Class
 * Represents a book entity in the Firebase Realtime Database
 * 
 * @author RANA MUHAMMAD AWAIS
 * @email rana.16241.ac@iqra.edu.pk
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private String imageUrl;

    /**
     * Default constructor required for Firebase
     */
    public Book() {
        // Default constructor required for calls to DataSnapshot.getValue(Book.class)
    }

    /**
     * Constructor with all parameters
     * 
     * @param id Unique book identifier
     * @param title Book title
     * @param author Book author name
     * @param description Book description
     * @param imageUrl URL of book cover image
     */
    public Book(String id, String title, String author, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
