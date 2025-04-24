package Model;

import java.sql.Timestamp;

public class ContactUs {
    private String contactId;
    private String name;
    private String email;
    private String message;
    private Timestamp createdAt;

    public ContactUs(String contactId,String name,String email, String message,Timestamp createdAt) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}