
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelRoomYotamOphirTester {
    
    public static final int DEFAULT_ROOM_NUM = 999;
    public static final int DEFAULT_NUM_BEDS = 2;
    
    public static final int MIN_ROOM_NUM = 99;
    public static final int MAX_ROOM_NUM = 1000;
    
    public static final int MIN_NUM_BEDS = 2;
    public static final int MAX_NUM_BEDS = 4;
    
    //toString

    @Test
    public void testToString() {
        HotelRoom room = new HotelRoom(307, 4);
        assertEquals("Room 307, 4 Beds: Available", room.toString());
    }
    
    @Test
    public void testToStringInvalidBeds() {
        HotelRoom room = new HotelRoom(307, 6);
        assertEquals("Room 307, 2 Beds: Available", room.toString());
    }
    
    @Test
    public void testToStringInvalidRoomNum01() {
        HotelRoom room = new HotelRoom(53, 4);
        assertEquals("Room 999, 4 Beds: Available", room.toString());
    }
    
    @Test
    public void testToStringInvalidRoomNum02() {
        HotelRoom room = new HotelRoom(8063, 4);
        assertEquals("Room 999, 4 Beds: Available", room.toString());
    }
    
    @Test
    public void testToStringAfterCheckIn() {
        HotelRoom room = new HotelRoom(307, 4);
        room.checkIn("Yotam");
        assertEquals("Room 307, 4 Beds: Occupied by Yotam", room.toString());
    }
    
    //get
    
    @Test
    public void testConstructorValidValues() {
        HotelRoom room = new HotelRoom(468, 2);
        assertEquals(468, room.getRoomNum());
        assertEquals(2, room.getNumBeds());
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void testConstructorInvalidBeds01() {
        HotelRoom room = new HotelRoom(468, 6);
        assertEquals(468, room.getRoomNum());
        assertEquals(2, room.getNumBeds());
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void testConstructorInvalidBeds02() {
        HotelRoom room = new HotelRoom(468, 1);
        assertEquals(468, room.getRoomNum());
        assertEquals(2, room.getNumBeds());
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void testConstructorInvalidRoomNum01() {
        HotelRoom room = new HotelRoom(50, 3);
        assertEquals(999, room.getRoomNum());
        assertEquals(3, room.getNumBeds());
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void testConstructorInvalidRoomNum02() {
        HotelRoom room = new HotelRoom(9045, 3);
        assertEquals(999, room.getRoomNum());
        assertEquals(3, room.getNumBeds());
        assertFalse(room.isOccupied());
    }
    
    //set
    
    @Test
    public void testSetRoomNum() {
        HotelRoom room = new HotelRoom(854, 3);
        room.setRoomNum(647);
        assertEquals(647, room.getRoomNum());
    }
    
    @Test
    public void testSetInvalidRoomNum() {
        HotelRoom room = new HotelRoom(854, 3);
        room.setRoomNum(67);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void testSetNumBeds() {
        HotelRoom room = new HotelRoom(854, 3);
        room.setNumBeds(4);
        assertEquals(4, room.getNumBeds());
    }
    
    @Test
    public void testSetInvalidNumBeds01() {
        HotelRoom room = new HotelRoom(854, 3);
        room.setNumBeds(67);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void testSetInvalidNumBeds02() {
        HotelRoom room = new HotelRoom(854, 3);
        room.setNumBeds(1);
        assertEquals(2, room.getNumBeds());
    }
    
    // checkIn
    
    @Test
    public void testCheckIn() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Yoav");
        assertTrue(room.isOccupied());
        assertEquals("Yoav", room.getGuest());
    }
    
    @Test
    public void testCheckIn02() {
        HotelRoom room = new HotelRoom(365, 3);
        assertTrue(room.checkIn("Banani"));
    }
    
    @Test
    public void testCheckInToOccupiedRoom() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Yoav");
        room.checkIn("Banana");
        assertTrue(room.isOccupied());
        assertEquals("Yoav", room.getGuest());
    }
    
    @Test
    public void testCheckInToOccupiedRoom02() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Yoav");
        assertTrue(room.isOccupied());
        assertFalse(room.checkIn("Banana"));
    }
    
    @Test
    public void testCheckInToOccupiedRoom03() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Matan");
        assertTrue(room.isOccupied());
        assertFalse(room.checkIn("Banana"));
    }
    
    //checkOut
    
    @Test
    public void testCheckOut01() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkOut();
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void testCheckOut02() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkOut();
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void testCheckOut03() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Yoav");
        room.checkOut();
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void testCheckOut04() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Yotam");
        room.checkOut();
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void testCheckOut05() {
        HotelRoom room = new HotelRoom(365, 3);
        room.checkIn("Yotam");
        room.checkOut();
        room.checkIn("Yoav");
        room.checkOut();
        assertEquals("", room.getGuest());
    }
    
    
}

