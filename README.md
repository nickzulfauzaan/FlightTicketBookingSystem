# FlightTicketBookingSystem
Implementation of a simple simulation ticket booking system with a queue data structure for a waiting list in Java.

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
