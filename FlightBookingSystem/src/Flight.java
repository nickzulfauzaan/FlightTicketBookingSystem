import java.util.Random;
import java.util.UUID;

public class Flight {
    int vacantSeats;
    boolean isFull = false;

    int day;
    int month;
    int year;

    String id;

    String origin;
    String destination;

    Random randVal = new Random();

    Flight() {
        origin = "KUL";
        destination = "SIN";

        month = randVal.nextInt(12) + 1;
        if (month == 2) {
            day = randVal.nextInt(27) + 1;  
        } 
        else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = randVal.nextInt(31) + 1;
        } 
        else {
            day = randVal.nextInt(30) + 1;
        } 
        year = 2022;

        vacantSeats = 3;
        isFull = false;

        id = UUID.randomUUID().toString().split("-")[0];
    }

    public int getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(int vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    public boolean isFull() {
        if(vacantSeats == 0) isFull = true;
        else isFull = false;
        return isFull;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "vacantSeats=" + vacantSeats +
                ", isFull=" + isFull +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", id='" + id + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
