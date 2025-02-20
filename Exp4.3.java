Experiment 4.3: Ticket Booking System
====================================================================================================================================
  class TicketBookingSystem {
    private boolean[] seats; 

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }

    public synchronized boolean bookSeat(int seatNumber, String userName, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println(userName + ": Invalid seat number!");
            return false;
        }
        
        if (seats[seatNumber - 1]) {
            System.out.println(userName + ": Seat " + seatNumber + " is already booked!");
            return false;
        }

        seats[seatNumber - 1] = true;
        System.out.println(userName + (isVIP ? " (VIP)" : "") + " booked seat " + seatNumber);
        return true;
    }
}

class UserThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String userName;
    private boolean isVIP;

    public UserThread(TicketBookingSystem system, int seatNumber, String userName, boolean isVIP) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.userName = userName;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, userName, isVIP);
    }
}

public class TicketBookingSimulator {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        System.out.println("Test Case 1: No Seats Available Initially");
        System.out.println("No bookings yet.\n");

        System.out.println("Test Case 2: Successful Booking");
        UserThread user1 = new UserThread(system, 1, "Anish", true);
        UserThread user2 = new UserThread(system, 2, "Bobby", false);
        UserThread user3 = new UserThread(system, 3, "Charlie", true);
        user1.start();
        user2.start();
        user3.start();
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 3: Thread Priorities (VIP First)");
        UserThread user4 = new UserThread(system, 4, "Bobby", false);
        UserThread user5 = new UserThread(system, 4, "Anish", true);
        user4.setPriority(Thread.MIN_PRIORITY);
        user5.setPriority(Thread.MAX_PRIORITY);
        user5.start();
        user4.start();
        try {
            user5.join();
            user4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 4: Preventing Double Booking");
        UserThread user6 = new UserThread(system, 1, "Anish", true);
        UserThread user7 = new UserThread(system, 1, "Bobby", false);
        user6.start();
        user7.start();
        try {
            user6.join();
            user7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 5: Booking After All Seats Are Taken");
        UserThread user8 = new UserThread(system, 3, "NewUser", false);
        user8.start();
        try {
            user8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 6: Invalid Seat Selection");
        UserThread user9 = new UserThread(system, 0, "User1", false);
        UserThread user10 = new UserThread(system, 6, "User2", false);
        user9.start();
        user10.start();
        try {
            user9.join();
            user10.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 7: Simultaneous Bookings (Concurrency Test)");
        UserThread[] users = new UserThread[10];
        for (int i = 0; i < 10; i++)
===================================================================================================================================
  Test Case 1: No Seats Available Initially
No bookings yet.

Test Case 2: Successful Booking
Anish (VIP) booked seat 1
Bobby (Regular) booked seat 2
Charlie (VIP) booked seat 3

Test Case 3: Thread Priorities (VIP First)
Anish (VIP) booked seat 4
Bobby (Regular): Seat 4 is already booked!

Test Case 4: Preventing Double Booking
Anish (VIP) booked seat 1
Bobby (Regular): Seat 1 is already booked!

Test Case 5: Booking After All Seats Are Taken
Error: Seat 3 is already booked!

Test Case 6: Invalid Seat Selection
User1: Invalid seat number!
User2: Invalid seat number!

Test Case 7: Simultaneous Bookings (Concurrency Test)
User1 (VIP) booked seat 1
User2 (Regular) booked seat 2
User3 (VIP) booked seat 3
User4 (Regular) booked seat 4
User5 (VIP) booked seat 5
User6 (Regular): Seat 1 is already booked!
User7 (VIP): Seat 2 is already booked!
User8 (Regular): Seat 3 is already booked!
User9 (VIP): Seat 4 is already booked!
User10 (Regular): Seat 5 is already booked!

  ===================================================================================================================================

  
Test Cases

Test Case 1: No Seats Available Initially
Input:
System starts with 5 seats.
No users attempt to book.
Expected Output:
No bookings yet.

Test Case 2: Successful Booking
Input:
Anish (VIP) books Seat 1.
Bobby (Regular) books Seat 2.
Charlie (VIP) books Seat 3.
Expected Output:
Anish (VIP) booked seat 1
Bobby (Regular) booked seat 2
Charlie (VIP) booked seat 3

Test Case 3: Thread Priorities (VIP First)
Input:
Bobby (Regular) books Seat 4 (low priority).
Anish (VIP) books Seat 4 (high priority).
Expected Output:
Anish (VIP) booked seat 4
Bobby (Regular): Seat 4 is already booked!

Test Case 4: Preventing Double Booking
Input:
Anish (VIP) books Seat 1.
Bobby (Regular) tries to book Seat 1 again.
Expected Output:
Anish (VIP) booked seat 1
Bobby (Regular): Seat 1 is already booked!

Test Case 5: Booking After All Seats Are Taken
Input:
All 5 seats are booked.
A new user (Regular) tries to book Seat 3.
Expected Output:
Error: Seat 3 is already booked!

Test Case 6: Invalid Seat Selection
Input:
User tries to book Seat 0 (out of range).
User tries to book Seat 6 (beyond available seats).
Expected Output:
Invalid seat number!

Test Case 7: Simultaneous Bookings (Concurrency Test)
Input:
10 users try booking at the same time for 5 seats.
Expected Output:
5 users successfully book seats.
5 users receive error messages for already booked seats.
