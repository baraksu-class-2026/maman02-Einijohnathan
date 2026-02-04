import java.util.Scanner;
public class Hotel{
    public static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) {
    int roomNum,bedNum;
    final int DEF_NUM_BEDS = 2;
    final int MIN_NUM_BEDS = 2;
    final int MAX_NUM_BEDS = 4;
    HotelRoom room1=new HotelRoom(307,4);
    HotelRoom room2=new HotelRoom(205,3);
    room2.checkIn("Guest Test");
    HotelRoom room3=new HotelRoom(402,2);
    displaySorted(room1,room2,room3);
    System.out.println("Hotel Menu :");
    System.out.println("1 - Display rooms by room number (ascending)");
    System.out.println("2 - Check-in to a room");
    System.out.println("3 - Check-out from a room");
    System.out.println("4 - Find available room by requested beds");
    System.out.println("Enter your choice :");
    int choice = reader.nextInt();
    switch(choice){
        case 1:displaySorted(room1,room2,room3);
        break;
        case 2:System.out.println("Enter your room number,Enter your name");
               String guestName = reader.next();
               roomNum = reader.nextInt();
              checkIn(guestName,roomNum,room1,room2,room3);
        break;
        case 3:System.out.println("Enter your room number");
               roomNum = reader.nextInt();
              checkOut(roomNum,room1,room2,room3);
        break;
        case 4:System.out.println("Enter number of beds 2-4");
               bedNum = reader.nextInt();
               //if (bedNum < MIN_NUM_BEDS && bedNum >MAX_NUM_BEDS)
               //bedNum = MAX_NUM_BEDS;
               findAvailableByBeds(bedNum,room1,room2,room3);
        break;
        default:System.out.println("Error: Invalid menu choice");
        break;
      
    
    
    
    
    }
    }
public static void displaySorted(HotelRoom a, HotelRoom b, HotelRoom c){
    HotelRoom first= new HotelRoom();
    HotelRoom second= new HotelRoom();
    HotelRoom third= new HotelRoom();
    if (a.before(b) && a.before(c)){
        first = a;
        if (b.before(c)){
        second = b;
        third = c;
        }
        else{
        second = c;
        third = b;  
        }
    }
    else if (b.before(a) && b.before(c)){
        first = b;
        if (a.before(c)){
        second = a;
        third = c;
        }
        else{
        second = c;
        third = a;  
        }
    }
    else{
        first = c;
        if(a.before(b)){
        second = a;
        third = b; 
            }
        else
        {
            second = b;
            third = a; 
        }
    }
    System.out.println("the first room: "+first+ " the second room: "+second+ " the third room: "+third);
}
public static void checkIn(String guestName, int roomNum, HotelRoom a,HotelRoom b, HotelRoom c){
    HotelRoom choosenRoom = new HotelRoom();
    choosenRoom = findRoomByNumber(roomNum,a,b,c); 
    if (choosenRoom != null && (!choosenRoom.isOccupied())){
        choosenRoom.checkIn(guestName);
        System.out.println(choosenRoom);
    }
    else
    System.out.println("Error: Room not available or not found");
}
public static HotelRoom findRoomByNumber(int roomNum, HotelRoom a,
    HotelRoom b, HotelRoom c){
        
        if (roomNum == a.getRoomNum()){
            return a;
        }
        else if (roomNum == b.getRoomNum()){
            return b;
        }
        else if (roomNum == c.getRoomNum()){
            return c;
        }
        else
        return null;
}
public static void checkOut(int roomNum, HotelRoom a, HotelRoom b, HotelRoom c){
    HotelRoom choosenRoom = new HotelRoom();
    choosenRoom = findRoomByNumber(roomNum,a,b,c); 
    if (choosenRoom != null){
        choosenRoom.checkOut();
        System.out.println(choosenRoom);
    }
    else{
        System.out.println("Error: Room not available or not found");
    }
}
public static void findAvailableByBeds(int beds, HotelRoom a, HotelRoom b,HotelRoom c){
    if (AvailbaleBeds(a,beds))
    System.out.println(a);
    else if (AvailbaleBeds(b,beds))
    System.out.println(b);
    else if (AvailbaleBeds(c,beds))
    System.out.println(c);
    else
    System.out.println("No available room with the requested number of beds");
}
private static boolean AvailbaleBeds(HotelRoom room,int beds){
    return !room.isOccupied() && room.getNumBeds() == beds;    
}
public static void display (HotelRoom a, HotelRoom b, HotelRoom c){
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
}
}