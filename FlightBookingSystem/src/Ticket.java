import java.util.UUID;

public class Ticket {
    String flightID;
    String passengerID;
    String id;
    String status;

    public Ticket(String flightID, String passengerID, String status) {
    
        this.flightID = flightID;
        this.passengerID = passengerID;
        this.status = status;

        this.id = UUID.randomUUID().toString().split("-")[0];
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "flightID='" + flightID + '\'' +
                ", passengerID='" + passengerID + '\'' +
                ", id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
