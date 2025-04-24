package Model;

import java.sql.Timestamp;

public class Feedback {
    private int feedbackId;
    private int reservationId; 
    private String message;    
    private Timestamp createdAt;

    public Feedback(int feedbackId, int reservationId, String message, Timestamp createdAt) {
        this.feedbackId = feedbackId;
        this.reservationId = reservationId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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