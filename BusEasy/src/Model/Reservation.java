package Model;

import java.sql.Timestamp;

public class Reservation {
    private int reservationId;
    private int userId;
    private int journeyId;
    private String seatNumber;
    private String paymentStatus;
    private Timestamp createdAt;

    // Constructor
    public Reservation(int reservationId, int userId, int journeyId, String seatNumber, String paymentStatus, Timestamp createdAt) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.journeyId = journeyId;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}