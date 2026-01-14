
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelStudJnitTester {

    private HotelRoom roomA;
    private HotelRoom roomB;
    private HotelRoom roomC;

    @BeforeEach
    public void setUp() {
        // Initialize fresh room objects before each test
        roomA = new HotelRoom(307, 4);
        roomB = new HotelRoom(205, 3);
        roomC = new HotelRoom(402, 2);
    }

    @Test
    public void testDisplay() {
        // Test that display method works without throwing exceptions
        // Since display prints to console, we're mainly testing it doesn't crash
        assertDoesNotThrow(() -> Hotel.display(roomA, roomB, roomC));
    }

    @Test
    public void testDisplaySorted() {
        // Test that displaySorted method works without throwing exceptions
        // Since displaySorted prints to console, we're mainly testing it doesn't crash
        assertDoesNotThrow(() -> Hotel.displaySorted(roomA, roomB, roomC));
    }

    @Test
    public void testFindRoomByNumber() {
        // Test finding room 205
        HotelRoom found = Hotel.findRoomByNumber(205, roomA, roomB, roomC);
        assertNotNull(found, "Room 205 should be found");
        assertEquals("Room 205, 3 Beds: Available", found.toString(),
                "Room 205 should have 3 beds and be available");
    }

    @Test
    public void testFindRoomByNumberNotFound() {
        // Test finding a room that doesn't exist
        HotelRoom notFound = Hotel.findRoomByNumber(999, roomA, roomB, roomC);
        assertNull(notFound, "Room 999 should not be found");
    }

    @Test
    public void testCheckIn() {
        // Test checking in a guest
        Hotel.checkIn("Dana", 205, roomA, roomB, roomC);

        HotelRoom room205 = Hotel.findRoomByNumber(205, roomA, roomB, roomC);
        assertNotNull(room205, "Room 205 should exist");
        assertTrue(room205.toString().contains("Occupied by Dana"),
                "Room 205 should be occupied by Dana");
    }

    @Test
    public void testCheckOut() {
        // First check in a guest
        Hotel.checkIn("Dana", 205, roomA, roomB, roomC);

        // Then check them out
        Hotel.checkOut(205, roomA, roomB, roomC);

        HotelRoom room205 = Hotel.findRoomByNumber(205, roomA, roomB, roomC);
        assertNotNull(room205, "Room 205 should exist");
        assertTrue(room205.toString().contains("Available"),
                "Room 205 should be available after checkout");
    }

    @Test
    public void testFindAvailableByBeds() {
        // Test finding an available room with 2 beds
        // Since findAvailableByBeds prints to console, we're mainly testing it doesn't crash
        assertDoesNotThrow(() -> Hotel.findAvailableByBeds(2, roomA, roomB, roomC),
                "Should find and print a room with 2 beds");
    }

    @Test
    public void testFindAvailableByBedsWhenOccupied() {
        // Occupy the 2-bed room
        Hotel.checkIn("Guest", 402, roomA, roomB, roomC);

        // Try to find available room with 2 beds (should print nothing or "not found" message)
        assertDoesNotThrow(() -> Hotel.findAvailableByBeds(2, roomA, roomB, roomC),
                "Should not crash when no room with 2 beds is available");
    }

    @Test
    public void testFindAvailableByBedsNotExists() {
        // Test finding a room with number of beds that doesn't exist
        assertDoesNotThrow(() -> Hotel.findAvailableByBeds(10, roomA, roomB, roomC),
                "Should not crash when room with 10 beds doesn't exist");
    }

    @Test
    public void testMultipleCheckIns() {
        // Test multiple check-ins
        Hotel.checkIn("Dana", 205, roomA, roomB, roomC);
        Hotel.checkIn("John", 307, roomA, roomB, roomC);
        Hotel.checkIn("Sarah", 402, roomA, roomB, roomC);

        HotelRoom room205 = Hotel.findRoomByNumber(205, roomA, roomB, roomC);
        HotelRoom room307 = Hotel.findRoomByNumber(307, roomA, roomB, roomC);
        HotelRoom room402 = Hotel.findRoomByNumber(402, roomA, roomB, roomC);

        assertTrue(room205.toString().contains("Occupied by Dana"));
        assertTrue(room307.toString().contains("Occupied by John"));
        assertTrue(room402.toString().contains("Occupied by Sarah"));
    }

    @Test
    public void testCheckInToOccupiedRoom() {
        // Check in a guest
        Hotel.checkIn("Dana", 205, roomA, roomB, roomC);

        // Try to check in another guest to the same room
        Hotel.checkIn("John", 205, roomA, roomB, roomC);

        // The room should still be occupied by Dana (assuming the method doesn't overwrite)
        HotelRoom room205 = Hotel.findRoomByNumber(205, roomA, roomB, roomC);
        assertTrue(room205.toString().contains("Occupied"),
                "Room should remain occupied");
    }
}
