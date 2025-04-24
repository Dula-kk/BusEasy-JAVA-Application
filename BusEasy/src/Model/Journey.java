package Model;

import java.sql.Timestamp;

public class Journey {
    private int journeyId;
    private String route;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private String status;
    private int conductorId;
    private double fee;
    private Timestamp createdAt;

    // Getters and Setters
    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }


    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public Timestamp getCreatedAT() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getConductorId() {
        return conductorId;
    }

    public void setConductorId(int conductorId) {
        this.conductorId = conductorId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}