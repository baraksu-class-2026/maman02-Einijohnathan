import java.util.Scanner;

public class Hotel {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        // יצירת חדרים (מניח שקיים בנאי המקבל מספר חדר ומספר מיטות)
        HotelRoom room1 = new HotelRoom(307, 4);
        HotelRoom room2 = new HotelRoom(205, 3);
        room2.checkIn("Guest Test");
        HotelRoom room3 = new HotelRoom(402, 2);

        System.out.println("Hotel Menu:");
        System.out.println("1 - Display rooms by room number (ascending)");
        System.out.println("2 - Check-in to a room");
        System.out.println("3 - Check-out from a room");
        System.out.println("4 - Find available room by requested beds");
        System.out.print("Enter your choice: ");

        int choice = reader.nextInt();
        switch (choice) {
            case 1:
                displaySorted(room1, room2, room3);
                break;
            case 2:
                System.out.print("Enter room number: ");
                int roomNumIn = reader.nextInt();
                System.out.print("Enter guest name: ");
                String guestName = reader.next();
                checkIn(guestName, roomNumIn, room1, room2, room3);
                break;
            case 3:
                System.out.print("Enter room number: ");
                int roomNumOut = reader.nextInt();
                checkOut(roomNumOut, room1, room2, room3);
                break;
            case 4:
                System.out.print("Enter requested number of beds (2-4): ");
                int bedNum = reader.nextInt();
                findAvailableByBeds(bedNum, room1, room2, room3);
                break;
            default:
                System.out.println("Error: Invalid menu choice");
                break;
        }
    }

    public static void displaySorted(HotelRoom a, HotelRoom b, HotelRoom c) {
        HotelRoom first, second, third;

        // לוגיקת המיון שלך (מניח שקיימת מתודה before שמשווה מספרי חדרים)
        if (a.before(b) && a.before(c)) {
            first = a;
            if (b.before(c)) { second = b; third = c; } 
            else { second = c; third = b; }
        } else if (b.before(a) && b.before(c)) {
            first = b;
            if (a.before(c)) { second = a; third = c; } 
            else { second = c; third = a; }
        } else {
            first = c;
            if (a.before(b)) { second = a; third = b; } 
            else { second = b; third = a; }
        }
        System.out.println("Sorted Rooms: \n1. " + first + "\n2. " + second + "\n3. " + third);
    }

    public static void checkIn(String guestName, int roomNum, HotelRoom a, HotelRoom b, HotelRoom c) {
        HotelRoom chosenRoom = findRoomByNumber(roomNum, a, b, c);
        if (chosenRoom != null && !chosenRoom.isOccupied()) {
            chosenRoom.checkIn(guestName);
            System.out.println("Check-in successful!");
            System.out.println("Room details: " + chosenRoom);
            // תיקון: הדפסת השם הדינמי שהוזן
            System.out.println("Occupied by: " + guestName);
        } else {
            printRoomNotAvailable();
        }
    }

    public static HotelRoom findRoomByNumber(int roomNum, HotelRoom a, HotelRoom b, HotelRoom c) {
        if (roomNum == a.getRoomNum()) return a;
        if (roomNum == b.getRoomNum()) return b;
        if (roomNum == c.getRoomNum()) return c;
        return null;
    }

    public static void checkOut(int roomNum, HotelRoom a, HotelRoom b, HotelRoom c) {
        HotelRoom chosenRoom = findRoomByNumber(roomNum, a, b, c);
        if (chosenRoom != null) {
            chosenRoom.checkOut();
            System.out.println("Check-out completed for room: " + roomNum);
        } else {
            printRoomNotAvailable();
        }
    }

    public static void findAvailableByBeds(int beds, HotelRoom a, HotelRoom b, HotelRoom c) {
        if (availableBeds(a, beds)) System.out.println("Found: " + a);
        else if (availableBeds(b, beds)) System.out.println("Found: " + b);
        else if (availableBeds(c, beds)) System.out.println("Found: " + c);
        else System.out.println("No available room with " + beds + " beds.");
    }

    private static boolean availableBeds(HotelRoom room, int beds) {
        return !room.isOccupied() && room.getNumBeds() == beds;
    }

    private static void printRoomNotAvailable() { 
        System.out.println("Error: Room not available or not found");
    }
}
