package Model;

/**
 * Represents a Refund entity.
 */
public class Refund {
    private int refundId;
    private int reservationId;
    private String status;

    public static final String STATUS_REQUESTED = "requested";
    public static final String STATUS_APPROVED = "approved";
    public static final String STATUS_REJECTED = "rejected";

    public Refund(int refundId, int reservationId, String status) {
        this.refundId = refundId;
        this.reservationId = reservationId;
        this.status = status;
    }

    // Getters and Setters
    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}