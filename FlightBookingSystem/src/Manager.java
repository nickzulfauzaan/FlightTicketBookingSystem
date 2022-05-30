import java.util.Scanner;

/*
==Objects==
1. Flight
2. Passenger
3. Queue
4. Ticket

==Object Requirements==
1. A Ticket has 1 Flight
2. A Ticket has 1 Passenger
3. A Flight has 1 Waiting Queue and vice versa
4. A Passenger has 0 to Many Tickets
5. A Flight has 0 to Many Tickets

==Basic Psuedocode==
1. Generate a set amount of flights with random day and month within the year 2022
2. Ask user for use case

--Flight Searcher--
1. Ask user for lower and upper range day and month
* Ensure the validity of lower and upper range day and month e.g. 28 Feb / 29 Feb X
2. Convert desired date range into dayIndex (1 Jan = 1, 2 Jan = 2, 3 Jan = 3,...31 Dec = 365)
3. List flights within date range according to dayIndex

--Passenger Registration--
1. Request Name, Age, ID Number and Mobile Number
2. Create "Passenger" object, pass details to constructor
3. Increase passenger count by 1 (initial at 0)

--Ticket Booking--
1. List out all registered passengers
2. Request the booking passenger
3. Request the flight ID
4. Create "Ticket" object, pass details to constructor
5. If flight vacant seats more than 0, reduce 1 vacant seat and confirm ticket
   if flight vacant seats is 0, enqueue passenger id into flight waiting queue and put ticket status as 'waiting'

--Ticket Editor--
1. List out all tickets
2. Request which ticket
3. List out all editing option
4. Request which option
5. Edit "Passenger" object within "Ticket" object based on details passed

--Ticket Canceller--
1. List out all 'confirmed' and 'waiting' tickets
2. Request which ticket
3. if ticket is confirmed, and flight waitingQueue has tickets; ticket changes status to cancelled and dequeue and confirm 1 ticket from the waitingQueue
   if ticket is waiting, and flight waitingQueue has tickets; status change and dequeue ticket from waitingQueue
   if ticket is confirmed, and flight waitingQueue is empty; status change and vacant Seat of flight + 1
   if ticket is waiting, and flight waitingQueue is empty somehow; status change (Note: this is not achievable theoretically!)
 */
public class Manager
{
    public static void displayOptionsMain(){
        System.out.println("\n================== Flight Ticket Booking System ==================");
        System.out.println("----- UI Options (Enter the corresponding number to proceed) -----");
        System.out.println("[1] Search for flights");
        System.out.println("[2] Register passenger");
        System.out.println("[3] Book a ticket");
        System.out.println("[4] Edit ticket information");
        System.out.println("[5] View ticket status");
        System.out.println("[6] Cancel a ticket");
        System.out.println("[99] Terminate program");
        System.out.print("\nYour input: ");
    }

    public static void main(String[] args) {
        Scanner userInput1 = new Scanner(System.in);
        Scanner userInput2 = new Scanner(System.in);

        int passengerCount = 0;
        int ticketCount = 0;
        int flightAmount = 5;

        Passenger[] passenger = new Passenger[9999];
        Ticket[] ticket = new Ticket[9999];
        Flight[] flight = new Flight[flightAmount];
        Queue[] waitingQueue = new Queue[flightAmount];

        for(int i = 0; i < flightAmount; i++) {
            flight[i] = new Flight();
            waitingQueue[i] = new Queue(999,flight[i].getId()); //setMaxSize originally 9999, now 5 for debugging
        }

        boolean terminated = false;        
        while (!terminated) {
            //Debugger
            /*
            System.out.println("\n===Display every object and values for debugging purposes===");
            System.out.println("--Passenger--(Counter: "+passengerCount+")");
            for(int i = 0; i < passengerCount; i++) {
                System.out.println("["+i+"] "+passenger[i].toString());
            }
            System.out.println("--Flight--(Counter: "+flightAmount+")");
            for(int i = 0; i < flightAmount; i++) {
                System.out.println("["+i+"] "+flight[i].toString());
            }
            System.out.println("--Waiting Queue--(Counter: "+flightAmount+")");
            for(int i = 0; i < flightAmount; i++) {
                System.out.println("["+i+"] "+waitingQueue[i].toString());
            }
            System.out.println("--Ticket--(Counter: "+ticketCount+")");
            for(int i = 0; i < ticketCount; i++) {
                System.out.println("["+i+"] "+ticket[i].toString());
            }
            */

            displayOptionsMain();

            String input = userInput1.nextLine();
            switch(input){
                //Flight Searcher
                case "1":{
                    System.out.println("\n<<<<<  Flight Searcher  >>>>>");

                    int desiredLRMonth;
                    do {
                        System.out.print("Input Lower Limit Month Range (1 - 12): ");
                        desiredLRMonth = userInput2.nextInt();
                        if (desiredLRMonth < 1 || desiredLRMonth > 12) System.out.println("[ERROR] Please enter value within 1 to 12");
                    } while (desiredLRMonth < 1 || desiredLRMonth > 12);

                    int desiredLRDay;
                    boolean invalid1 = true;
                    do
                    {
                        if (desiredLRMonth == 4 || desiredLRMonth == 6 || desiredLRMonth == 9 || desiredLRMonth == 11)
                        {
                            System.out.print("Input Lower Limit Day Range (1 - 30): ");
                            desiredLRDay = userInput2.nextInt();
                            if (desiredLRDay < 1 || desiredLRDay > 30) System.out.println("[ERROR] Please enter value within 1 to 30");
                            else invalid1 = false;
                        }
                        else if (desiredLRMonth == 2)
                        {
                            System.out.print("Input Lower Limit Day Range (1 - 28): ");
                            desiredLRDay = userInput2.nextInt();
                            if (desiredLRDay < 1 || desiredLRDay > 28) System.out.println("[ERROR] Please enter value within 1 to 28");
                            else invalid1 = false;
                        }
                        else
                        {
                            System.out.print("Input Lower Limit Day Range (1 - 31): ");
                            desiredLRDay = userInput2.nextInt();
                            if (desiredLRDay < 1 || desiredLRDay > 31) System.out.println("[ERROR] Please enter value within 1 to 31");
                            else invalid1 = false;
                        }
                    } while (invalid1);

                    int desiredURMonth;
                    do {
                        System.out.print("Input Upper Limit Month Range ("+desiredLRMonth+" - 12): ");
                        desiredURMonth = userInput2.nextInt();
                        if (desiredURMonth < desiredLRMonth || desiredURMonth > 12) System.out.println("[ERROR] Please enter value within "+desiredLRMonth+" to 12");
                    } while (desiredURMonth < desiredLRMonth || desiredURMonth > 12);

                    int desiredURDay;
                    boolean invalid2 = true;
                    do
                    {
                        if (desiredURMonth == 4 || desiredURMonth == 6 || desiredURMonth == 9 || desiredURMonth == 11)
                        {
                            System.out.print("Input Upper Limit Day Range (1 - 30): ");
                            desiredURDay = userInput2.nextInt();
                            if (desiredURDay < 1 || desiredURDay > 30) System.out.println("[ERROR] Please enter value within 1 to 30");
                            else invalid2 = false;
                        }
                        else if (desiredURMonth == 2)
                        {
                            System.out.print("Input Upper Limit Day Range (1 - 28): ");
                            desiredURDay = userInput2.nextInt();
                            if (desiredURDay < 1 || desiredURDay > 28) System.out.println("[ERROR] Please enter value within 1 to 28");
                            else invalid2 = false;
                        }
                        else
                        {
                            System.out.print("Input Upper Limit Day Range (1 - 31): ");
                            desiredURDay = userInput2.nextInt();
                            if (desiredURDay < 1 || desiredURDay > 31) System.out.println("[ERROR] Please enter value within 1 to 31");
                            else invalid2 = false;
                        }
                    } while(invalid2);

                    System.out.print("\nSearching for flights within "+desiredLRDay+"/"+desiredLRMonth+"/2022 to "+desiredURDay+"/"+desiredURMonth+"/2022...\n\n");

                    int dayIndexLower = 0;
                    switch(desiredLRMonth) {
                        case 1:
                            dayIndexLower = desiredLRDay;
                            break;

                        case 2:
                            dayIndexLower = desiredLRDay + 31;
                            break;

                        case 3:
                            dayIndexLower = desiredLRDay + 59;
                            break;

                        case 4:
                            dayIndexLower = desiredLRDay + 90;
                            break;

                        case 5:
                            dayIndexLower = desiredLRDay + 120;
                            break;

                        case 6:
                            dayIndexLower = desiredLRDay + 151;
                            break;

                        case 7:
                            dayIndexLower = desiredLRDay + 181;
                            break;

                        case 8:
                            dayIndexLower = desiredLRDay + 212;
                            break;

                        case 9:
                            dayIndexLower = desiredLRDay + 243;
                            break;

                        case 10:
                            dayIndexLower = desiredLRDay + 273;
                            break;

                        case 11:
                            dayIndexLower = desiredLRDay + 304;
                            break;

                        case 12:
                            dayIndexLower = desiredLRDay + 334;
                            break;
                    }

                    int dayIndexUpper = 0;
                    switch(desiredURMonth) {
                        case 1:
                            dayIndexUpper = desiredURDay;
                            break;

                        case 2:
                            dayIndexUpper = desiredURDay + 31;
                            break;

                        case 3:
                            dayIndexUpper = desiredURDay + 59;
                            break;

                        case 4:
                            dayIndexUpper = desiredURDay + 90;
                            break;

                        case 5:
                            dayIndexUpper = desiredURDay + 120;
                            break;

                        case 6:
                            dayIndexUpper = desiredURDay + 151;
                            break;

                        case 7:
                            dayIndexUpper = desiredURDay + 181;
                            break;

                        case 8:
                            dayIndexUpper = desiredURDay + 212;
                            break;

                        case 9:
                            dayIndexUpper = desiredURDay + 243;
                            break;

                        case 10:
                            dayIndexUpper = desiredURDay + 273;
                            break;

                        case 11:
                            dayIndexUpper = desiredURDay + 304;
                            break;

                        case 12:
                            dayIndexUpper = desiredURDay + 334;
                            break;
                    }

                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("Flight ID\t|\tOrigin\t|\tDestination\t|\tDate\t\t|\tVacant Seats\t|\t");
                    System.out.println("-----------------------------------------------------------------------------");

                    for(int i = 0; i < flightAmount; i++) {
                        int flightDayIndex = 0;
                        switch(flight[i].getMonth()) {
                            case 1:
                                flightDayIndex = flight[i].getDay();
                                break;

                            case 2:
                                flightDayIndex = flight[i].getDay() + 31;
                                break;

                            case 3:
                                flightDayIndex = flight[i].getDay() + 59;
                                break;

                            case 4:
                                flightDayIndex = flight[i].getDay() + 90;
                                break;

                            case 5:
                                flightDayIndex = flight[i].getDay() + 120;
                                break;

                            case 6:
                                flightDayIndex = flight[i].getDay() + 151;
                                break;

                            case 7:
                                flightDayIndex = flight[i].getDay() + 181;
                                break;

                            case 8:
                                flightDayIndex = flight[i].getDay() + 212;
                                break;

                            case 9:
                                flightDayIndex = flight[i].getDay() + 243;
                                break;

                            case 10:
                                flightDayIndex = flight[i].getDay() + 273;
                                break;

                            case 11:
                                flightDayIndex = flight[i].getDay() + 304;
                                break;

                            case 12:
                                flightDayIndex = flight[i].getDay() + 334;
                                break;
                        }

                        if(flightDayIndex >= dayIndexLower && flightDayIndex <= dayIndexUpper) {
                            System.out.println(flight[i].getId()+"\t\t"+flight[i].getOrigin()+"\t\t\t"+flight[i].getDestination()+"\t\t\t\t"+flight[i].getDay()+"/"+flight[i].getMonth()+"/2022\t\t"+flight[i].getVacantSeats());
                        }
                    }
                }
                break;

                //Passenger Registration
                case "2": {
                    System.out.println("\n<<<<<  Passenger Registration  >>>>>");

                    userInput2.nextLine();
                    System.out.print("Input passenger's full name: ");
                    String passengerName = userInput2.nextLine();

                    System.out.print("Input passenger's identification number: ");
                    String passengerIC = userInput2.nextLine();

                    System.out.print("Input passenger's mobile number: ");
                    String passengerMobileNum = userInput2.nextLine();

                    System.out.print("Input passenger's age: ");
                    int passengerAge = userInput2.nextInt();

                    passenger[passengerCount] = new Passenger(passengerName, passengerAge, passengerIC, passengerMobileNum);
                    System.out.println("\nPassenger with details:-" +
                            "\nName: "+passenger[passengerCount].getName()+"" +
                            "\nAge: "+passenger[passengerCount].getAge()+"" +
                            "\nIC Number: "+passenger[passengerCount].getIdentificationNum()+"" +
                            "\nMobile Number: "+passenger[passengerCount].getMobileNum()+"\n" +
                            "Assigned System ID: "+passenger[passengerCount].getId()+"" +
                            "\nHas been appended into the system");
                    passengerCount++;
                }
                break;

                //Ticket Booking
                case "3":{
                    System.out.println("\n<<<<<  Ticket Booking  >>>>>");
                    System.out.println("----- Passenger List (enter the booking passenger) -----");

                    for(int i = 0; i < passengerCount; i++)
                    {
                        System.out.println("["+i+"] Name:"+passenger[i].getName().substring(0, Math.min(6, passenger[i].getName().length()))+"\t\t\tID: "+passenger[i].getId());
                    }

                    System.out.print("\nYour input: ");
                    int passengerIndex = userInput2.nextInt();
                    System.out.print("Flight ID: ");
                    userInput2.nextLine();
                    String desiredFlightID = userInput2.nextLine();

                    boolean notFound = true;
                    for(int i = 0; i < flightAmount; i++) {
                        if(flight[i].getId().equals(desiredFlightID)) {
                            if(flight[i].getVacantSeats() > 0) {
                                ticket[ticketCount] = new Ticket(flight[i].getId(), passenger[passengerIndex].getId(), "confirmed");
                                System.out.println("Ticket ID "+ticket[ticketCount].getId()+" for Passenger ID "+passenger[passengerIndex].getId()+" for Flight ID "+flight[i].getId()+" with status 'Confirmed' has been appended into the system.");
                                ticketCount++;
                                flight[i].setVacantSeats(flight[i].getVacantSeats() - 1);
                            }
                            else {
                                ticket[ticketCount] = new Ticket(flight[i].getId(), passenger[passengerIndex].getId(), "waiting");
                                waitingQueue[i].enqueue(passenger[passengerIndex].getId());
                                System.out.println("Ticket ID "+ticket[ticketCount].getId()+" for Passenger ID "+passenger[passengerIndex].getId()+" for Flight ID "+flight[i].getId()+" with status 'Waiting' has been appended into the system.");
                                System.out.println("Ticket ID "+ticket[ticketCount].getId()+" is enqueued into the flight's waiting queue");
                                ticketCount++;
                            }
                            notFound = false;
                        }
                        else if(i == flightAmount-1 && notFound) System.out.println("[ERROR] Booking failed due to Flight ID is irretrievable");
                    }
                }
                break;

                //Ticket Editor
                case "4":{
                    if(ticketCount == 0) {
                        System.out.println("[ERROR] There are no tickets booked!");
                        break;
                    }
                    else {
                        System.out.println("\n<<<<<  Ticket Editor  >>>>>");
                        System.out.println("----- Ticket List (enter the editing ticket) -----");

                        for(int i = 0; i < ticketCount; i++) {
                            System.out.println("["+i+"] ID: "+ticket[i].getId()+"\tFlight ID: "+ticket[i].getFlightID()+"\t\tPassenger ID: "+ticket[i].getPassengerID()+"\t\tStatus: "+ticket[i].getStatus());
                        }

                        System.out.print("\nYour input: ");

                        int ticketIndex = userInput2.nextInt();
                        int passengerIndex = 0;

                        //get the passenger index
                        for(int i = 0; i < passengerCount; i++){
                            if(passenger[i].getId().equals(ticket[ticketIndex].getPassengerID())){
                                passengerIndex = i;
                            }
                        }

                        //exeption just in case the passenger index cant be found

                        System.out.println("----- Edit Options (Enter the corresponding option to proceed) -----");
                        System.out.println("[1] Edit Passenger Name");
                        System.out.println("[2] Edit Passenger Age");
                        System.out.println("[3] Edit Passenger Identification Number");
                        System.out.println("[3] Edit Passenger Mobile Number");

                        System.out.print("\nYour input: ");
                        int editIndex = userInput2.nextInt();

                        userInput2.nextLine(); //idk it doesnt work without this
                        switch(editIndex) {
                            case 1:
                                System.out.print("\nPassenger's new name: ");
                                String newName = userInput2.nextLine();
                                passenger[passengerIndex].setName(newName);
                                break;

                            case 2:
                                System.out.print("\nPassenger's new Age: ");
                                int newAge = userInput2.nextInt();
                                passenger[passengerIndex].setAge(newAge);
                                break;

                            case 3:
                                System.out.print("\nPassenger's new Identification Number: ");
                                String newIDNum = userInput2.nextLine();
                                passenger[passengerIndex].setIdentificationNum(newIDNum);
                                break;

                            case 4:
                                System.out.print("\nPassenger's new Mobile Number: ");
                                String newMobileNum = userInput2.nextLine();
                                passenger[passengerIndex].setMobileNum(newMobileNum);
                                break;
                        }

                        System.out.println("\nPassenger with details:-" +
                                "\nName: "+passenger[passengerIndex].getName()+"" +
                                "\nAge: "+passenger[passengerIndex].getAge()+"" +
                                "\nIC Number: "+passenger[passengerIndex].getIdentificationNum()+"" +
                                "\nMobile Number: "+passenger[passengerIndex].getMobileNum()+"\n" +
                                "Assigned System ID: "+passenger[passengerIndex].getId()+"" +
                                "\nHas been updated");
                    }
                }
                break;

                //Ticket List Viewer
                case "5":{
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Ticket ID\t|\tFlight ID\t|\tPassenger ID\t|\tStatus\t|\t");
                    System.out.println("-------------------------------------------------------------");

                    for(int i = 0; i < ticketCount; i++) {
                            System.out.println(ticket[i].getId()+"\t\t"+ticket[i].getFlightID()+"\t\t"+ticket[i].getPassengerID()+"\t\t\t"+ticket[i].getStatus());
                    }
                }
                break;

                //Ticket Cancelling
                case "6":{
                    System.out.println("\n<<<<<  Ticket Canceller  >>>>>");
                    System.out.println("----- Ticket List (enter the cancelling ticket) -----");

                    for(int i = 0; i < ticketCount; i++)
                    {
                        if(ticket[i].getStatus().equals("cancelled")) System.out.println("[X] Cancelled");
                        else System.out.println("["+i+"] ID: "+ticket[i].getId()+"\tFlight ID: "+ticket[i].getFlightID()+"\t\tPassenger ID: "+ticket[i].getPassengerID()+"\t\tStatus: "+ticket[i].getStatus());
                    }

                    System.out.print("\nYour input: ");
                    int ticketIndex = userInput2.nextInt();

                    /*
                    if ticket is confirmed, and flight waitingQueue has tickets; ticket changes status to cancelled and dequeue and confirm 1 ticket the waitingQueue
                    if ticket is waiting, and flight waitingQueue has tickets; status change and dequeue ticket from waitingQueue
                    if ticket is confirmed, and flight waitingQueue is empty; status change and vacant Seat of flight + 1
                    if ticket is waiting, and flight waitingQueue is empty somehow; status change (Note: this is not achievable theoretically!)
                    */

                    for(int i = 0; i < flightAmount; i++) {
                        if(waitingQueue[i].getFlightID().equals(ticket[ticketIndex].getFlightID())){//if waiting Queue has the same Flight ID as the ticket's Flight ID
                            if(!waitingQueue[i].isEmpty()){ //if the waitingQueue is filled (not empty)
                                if(ticket[ticketIndex].getStatus().equals("confirmed")){ //if ticket is confirmed
                                    ticket[ticketIndex].setStatus("cancelled"); //set ticket status to cancelled
                                    for(int j = 0; j < ticketCount; j++) {
                                        if(ticket[j].getPassengerID().equals(waitingQueue[i].peek())){// if ticket has the same Passengger ID as peeked Passenger ID from waitingQueue
                                            waitingQueue[i].dequeue();  //dequeue ticket from waitingQueue
                                            ticket[j].setStatus("confirmed"); //confirm dequeued ticket
                                            break; //to avoid the next in line in waitingQueue to get 'confirmed' somehow
                                        }
                                    }
                                }
                                else if(ticket[ticketIndex].getStatus().equals("waiting")){ //if ticket is waiting
                                    ticket[ticketIndex].setStatus("cancelled"); //set ticket status to cancelled
                                    waitingQueue[i].dequeue();  //dequeue ticket from waitingQueue
                                }
                            }
                            else if(waitingQueue[i].isEmpty()){ // if waitingQueue is empty
                                if(ticket[ticketIndex].getStatus().equals("confirmed")){ //if ticket is confirmed
                                    ticket[ticketIndex].setStatus("cancelled"); //set ticket status to cancelled
                                    flight[i].setVacantSeats(flight[i].getVacantSeats() + 1); //increase flight's vacantSeats by 1
                                }
                                else if(ticket[ticketIndex].getStatus().equals("waiting")){ //if ticket is waiting
                                    ticket[ticketIndex].setStatus("cancelled"); //set ticket status to cancelled
                                }
                            }
                        }
                    }
                }
                break;

                //Terminate Loop
                case "99": {
                    terminated = true;
                    System.out.println("\n[ALERT] Program has been terminated! Thank you for using DS Airlines Flight Ticketing System\n");
                }
                break;

                //Return Error
                default: {
                    System.out.println("\n[ERROR] You have entered an invalid option!\n");
                }
                break;
            }
        }
    }
}
