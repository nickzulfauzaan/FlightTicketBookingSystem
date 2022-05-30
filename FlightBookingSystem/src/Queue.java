import java.util.Arrays;

public class Queue {
    private int headPointer;
    private int tailPointer;
    private int maxSize;
    private String passengerID[];
    String flightID;

    public Queue(int setMaxSize, String id) {
        this.headPointer = 0;
        this.tailPointer = 0;
        this.maxSize = setMaxSize;
        this.passengerID = new String[maxSize];
        this.flightID = id;
    }

    public boolean isEmpty() {
        return tailPointer == 0;
    }

    public boolean isFull() {
        return tailPointer >= maxSize;
    }

    public void enqueue(String queuedElement) {
        if(isEmpty()) {
            passengerID[headPointer] = queuedElement;
            tailPointer++;
        }
        else if(isFull()) {
            System.out.println("[FAILURE] Attempt to queue an element '"+ queuedElement +"' into Object '"+ this.getClass() +"' has failed due to reaching the maximum limit of '" + this.maxSize +"'.");
        }
        else {
            passengerID[tailPointer] = queuedElement;
            tailPointer++;
        }
    }

    public void enqueueMany(String queuedMultipleElements) {
        String[] temporaryElementArray = (String[])queuedMultipleElements.split(", ");

        for (int i = 0; i < temporaryElementArray.length; i++) {
            this.enqueue(temporaryElementArray[i]);
        }
    }

    public String dequeue() {
        if(isEmpty()) {
            return null;
        }
        else {
            String temporaryElement = passengerID[headPointer];

            for (int i = 0; i < tailPointer; i++) {
                passengerID[i] = passengerID[i + 1];
            }
            passengerID[tailPointer] = null;
            tailPointer--;

            return temporaryElement;
        }
    }

    public void dequeueAll() {
        if(!isEmpty()) {
            for(int i = tailPointer - 1; i >= 0; i--) {
                dequeue();
            }
        }
    }

    public String peek() {
        if(passengerID[headPointer] == null) {
            return null;
        }
        else {
            return passengerID[headPointer];
        }
    }

    public String display() {
        if(isEmpty()) {
            return null;
        }
        else {
            return Arrays.toString(passengerID);
        }
    }

    public String getFlightID() {
        return flightID;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "flightID='" + flightID + '\'' +
                "passengerID='" + Arrays.toString(passengerID) + '\'' +
                '}';
    }
}
