
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;

public class HotelArielMeyer1Tester {

    // Static fields copied from Hotel class
    public static final int ROOM_A_NUM = 307;
    public static final int ROOM_A_BEDS = 4;

    public static final int ROOM_B_NUM = 205;
    public static final int ROOM_B_BEDS = 3;

    public static final int ROOM_C_NUM = 402;
    public static final int ROOM_C_BEDS = 2;

    public static final int MIN_BEDS = 2;
    public static final int MAX_BEDS = 4;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private HotelRoom roomA;
    private HotelRoom roomB;
    private HotelRoom roomC;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        // Create standard test rooms
        roomA = new HotelRoom(ROOM_A_NUM, ROOM_A_BEDS);
        roomB = new HotelRoom(ROOM_B_NUM, ROOM_B_BEDS);
        roomC = new HotelRoom(ROOM_C_NUM, ROOM_C_BEDS);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    // Test displaySorted - all 6 permutations
    @Test
    public void testDisplaySortedABC() {
        HotelRoom a = new HotelRoom(100, 2);
        HotelRoom b = new HotelRoom(200, 3);
        HotelRoom c = new HotelRoom(300, 4);
        Hotel.displaySorted(a, b, c);
        String output = outContent.toString();
        assertTrue(output.indexOf("Room 100") < output.indexOf("Room 200"));
        assertTrue(output.indexOf("Room 200") < output.indexOf("Room 300"));
    }

    @Test
    public void testDisplaySortedACB() {
        HotelRoom a = new HotelRoom(100, 2);
        HotelRoom b = new HotelRoom(300, 3);
        HotelRoom c = new HotelRoom(200, 4);
        Hotel.displaySorted(a, b, c);
        String output = outContent.toString();
        assertTrue(output.indexOf("Room 100") < output.indexOf("Room 200"));
        assertTrue(output.indexOf("Room 200") < output.indexOf("Room 300"));
    }

    @Test
    public void testDisplaySortedBAC() {
        HotelRoom a = new HotelRoom(200, 2);
        HotelRoom b = new HotelRoom(100, 3);
        HotelRoom c = new HotelRoom(300, 4);
        Hotel.displaySorted(a, b, c);
        String output = outContent.toString();
        assertTrue(output.indexOf("Room 100") < output.indexOf("Room 200"));
        assertTrue(output.indexOf("Room 200") < output.indexOf("Room 300"));
    }

    @Test
    public void testDisplaySortedBCA() {
        HotelRoom a = new HotelRoom(300, 2);
        HotelRoom b = new HotelRoom(100, 3);
        HotelRoom c = new HotelRoom(200, 4);
        Hotel.displaySorted(a, b, c);
        String output = outContent.toString();
        assertTrue(output.indexOf("Room 100") < output.indexOf("Room 200"));
        assertTrue(output.indexOf("Room 200") < output.indexOf("Room 300"));
    }

    @Test
    public void testDisplaySortedCAB() {
        HotelRoom a = new HotelRoom(200, 2);
        HotelRoom b = new HotelRoom(300, 3);
        HotelRoom c = new HotelRoom(100, 4);
        Hotel.displaySorted(a, b, c);
        String output = outContent.toString();
        assertTrue(output.indexOf("Room 100") < output.indexOf("Room 200"));
        assertTrue(output.indexOf("Room 200") < output.indexOf("Room 300"));
    }

    @Test
    public void testDisplaySortedCBA() {
        HotelRoom a = new HotelRoom(300, 2);
        HotelRoom b = new HotelRoom(200, 3);
        HotelRoom c = new HotelRoom(100, 4);
        Hotel.displaySorted(a, b, c);
        String output = outContent.toString();
        assertTrue(output.indexOf("Room 100") < output.indexOf("Room 200"));
        assertTrue(output.indexOf("Room 200") < output.indexOf("Room 300"));
    }

    // Test checkIn - successful check-in
    @Test
    public void testCheckInSuccess() {
        Hotel.checkIn("John Doe", ROOM_A_NUM, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Room " + ROOM_A_NUM));
        assertTrue(output.contains("Occupied by John Doe"));
        assertTrue(roomA.isOccupied());
        assertEquals("John Doe", roomA.getGuest());
    }

    // Test checkIn - room already occupied
    @Test
    public void testCheckInRoomOccupied() {
        roomA.checkIn("First Guest");
        outContent.reset();
        Hotel.checkIn("Second Guest", ROOM_A_NUM, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Error: Room not available or not found"));
        assertEquals("First Guest", roomA.getGuest());
    }

    // Test checkIn - room not found
    @Test
    public void testCheckInRoomNotFound() {
        Hotel.checkIn("Guest", 999, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Error: Room not available or not found"));
    }

    // Test checkOut - successful checkout
    @Test
    public void testCheckOutSuccess() {
        roomB.checkIn("Test Guest");
        outContent.reset();
        Hotel.checkOut(ROOM_B_NUM, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Room " + ROOM_B_NUM));
        assertTrue(output.contains("Available"));
        assertFalse(roomB.isOccupied());
    }

    // Test checkOut - room not found
    @Test
    public void testCheckOutRoomNotFound() {
        Hotel.checkOut(999, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Error: Room not available or not found"));
    }

    // Test findAvailableByBeds - found room A
    @Test
    public void testFindAvailableByBedsRoomA() {
        Hotel.findAvailableByBeds(ROOM_A_BEDS, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Room " + ROOM_A_NUM));
        assertTrue(output.contains(ROOM_A_BEDS + " Beds"));
    }

    // Test findAvailableByBeds - found room B
    @Test
    public void testFindAvailableByBedsRoomB() {
        Hotel.findAvailableByBeds(ROOM_B_BEDS, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Room " + ROOM_B_NUM));
        assertTrue(output.contains(ROOM_B_BEDS + " Beds"));
    }

    // Test findAvailableByBeds - found room C
    @Test
    public void testFindAvailableByBedsRoomC() {
        Hotel.findAvailableByBeds(ROOM_C_BEDS, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Room " + ROOM_C_NUM));
        assertTrue(output.contains(ROOM_C_BEDS + " Beds"));
    }

    // Test findAvailableByBeds - not found (room occupied)
    @Test
    public void testFindAvailableByBedsNotFoundOccupied() {
        roomA.checkIn("Guest");
        roomB.checkIn("Guest");
        roomC.checkIn("Guest");
        Hotel.findAvailableByBeds(ROOM_A_BEDS, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("No available room with the requested number of beds"));
    }

    // Test findAvailableByBeds - not found (no matching beds)
    @Test
    public void testFindAvailableByBedsNotFoundNoMatch() {
        Hotel.findAvailableByBeds(1, roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("No available room with the requested number of beds"));
    }

    // Test findAvailableByBeds - invalid beds (negative)
    @Test
    public void testFindAvailableByBedsInvalidNegative() {
        Hotel.findAvailableByBeds(-1, roomA, roomB, roomC);
        String output = outContent.toString();
        assertEquals("", output.trim());
    }

    // Test findAvailableByBeds - invalid beds (zero)
    @Test
    public void testFindAvailableByBedsInvalidZero() {
        Hotel.findAvailableByBeds(0, roomA, roomB, roomC);
        String output = outContent.toString();
        assertEquals("", output.trim());
    }

    // Test display
    @Test
    public void testDisplay() {
        Hotel.display(roomA, roomB, roomC);
        String output = outContent.toString();
        assertTrue(output.contains("Room " + ROOM_A_NUM));
        assertTrue(output.contains("Room " + ROOM_B_NUM));
        assertTrue(output.contains("Room " + ROOM_C_NUM));
    }

    // Test findRoomByNumber - find room A
    @Test
    public void testFindRoomByNumberRoomA() {
        HotelRoom found = Hotel.findRoomByNumber(ROOM_A_NUM, roomA, roomB, roomC);
        assertNotNull(found);
        assertEquals(ROOM_A_NUM, found.getRoomNum());
    }

    // Test findRoomByNumber - find room B
    @Test
    public void testFindRoomByNumberRoomB() {
        HotelRoom found = Hotel.findRoomByNumber(ROOM_B_NUM, roomA, roomB, roomC);
        assertNotNull(found);
        assertEquals(ROOM_B_NUM, found.getRoomNum());
    }

    // Test findRoomByNumber - find room C
    @Test
    public void testFindRoomByNumberRoomC() {
        HotelRoom found = Hotel.findRoomByNumber(ROOM_C_NUM, roomA, roomB, roomC);
        assertNotNull(found);
        assertEquals(ROOM_C_NUM, found.getRoomNum());
    }

    // Test findRoomByNumber - room not found
    @Test
    public void testFindRoomByNumberNotFound() {
        HotelRoom found = Hotel.findRoomByNumber(999, roomA, roomB, roomC);
        assertNull(found);
    }

    // ========== Main Method Tests ==========
    // Test main method - option 1 (Display sorted)
    @Test
    public void testMainOption1DisplaySorted() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Hotel Rooms:"));
        assertTrue(output.contains("Hotel Menu:"));
        assertTrue(output.contains("1 - Display rooms by room number (ascending)"));
        assertTrue(output.contains("Enter your choice:"));

        // Find all occurrences of Room numbers and verify the last set is in sorted order
        // (after the "Enter your choice" menu selection)
        int lastIndexChoice = output.lastIndexOf("Enter your choice:");
        String afterChoice = output.substring(lastIndexChoice);

        // Verify that the sorted display appears after the menu choice
        assertTrue(afterChoice.contains("Room"));
    }

    // Test main method - option 2 (Check-in) - successful
    @Test
    public void testMainOption2CheckInSuccess() {
        String input = "2\n307\nJane Doe\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter room number:"));
        assertTrue(output.contains("Enter guest name:"));
        assertTrue(output.contains("Room 307"));
        assertTrue(output.contains("Occupied by Jane Doe"));
    }

    // Test main method - option 2 (Check-in) - room occupied
    @Test
    public void testMainOption2CheckInRoomOccupied() {
        String input = "2\n205\nAttempted Guest\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter room number:"));
        assertTrue(output.contains("Enter guest name:"));
        assertTrue(output.contains("Error: Room not available or not found"));
    }

    // Test main method - option 2 (Check-in) - room not found
    @Test
    public void testMainOption2CheckInRoomNotFound() {
        String input = "2\n999\nGuest Name\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter room number:"));
        assertTrue(output.contains("Enter guest name:"));
        assertTrue(output.contains("Error: Room not available or not found"));
    }

    // Test main method - option 3 (Check-out) - successful
    @Test
    public void testMainOption3CheckOutSuccess() {
        String input = "3\n205\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter room number:"));
        assertTrue(output.contains("Room 205"));
        assertTrue(output.contains("Available"));
    }

    // Test main method - option 3 (Check-out) - room not found
    @Test
    public void testMainOption3CheckOutRoomNotFound() {
        String input = "3\n999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter room number:"));
        assertTrue(output.contains("Error: Room not available or not found"));
    }

    // Test main method - option 4 (Find by beds) - found room with 4 beds
    @Test
    public void testMainOption4FindByBeds4() {
        String input = "4\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter requested number of beds (2-4):"));
        assertTrue(output.contains("Room 307"));
        assertTrue(output.contains("4 Beds"));
    }

    // Test main method - option 4 (Find by beds) - found room with 3 beds (occupied)
    @Test
    public void testMainOption4FindByBeds3() {
        String input = "4\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter requested number of beds (2-4):"));
        assertTrue(output.contains("No available room with the requested number of beds"));
    }

    // Test main method - option 4 (Find by beds) - found room with 2 beds
    @Test
    public void testMainOption4FindByBeds2() {
        String input = "4\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter requested number of beds (2-4):"));
        assertTrue(output.contains("Room 402"));
        assertTrue(output.contains("2 Beds"));
    }

    // Test main method - option 4 (Find by beds) - no match
    @Test
    public void testMainOption4FindByBedsNoMatch() {
        String input = "4\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Enter requested number of beds (2-4):"));
        assertTrue(output.contains("No available room with the requested number of beds"));
    }

    // Test main method - invalid option
    @Test
    public void testMainInvalidOption() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Hotel Menu:"));
        assertTrue(output.contains("Enter your choice:"));
        assertTrue(output.contains("Error: Invalid menu choice"));
    }

    // Test main method - invalid option (zero)
    @Test
    public void testMainInvalidOptionZero() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Error: Invalid menu choice"));
    }

    // Test main method - invalid option (negative)
    @Test
    public void testMainInvalidOptionNegative() {
        String input = "-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Hotel.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Error: Invalid menu choice"));
    }

}
