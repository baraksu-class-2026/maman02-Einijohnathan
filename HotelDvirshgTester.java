import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelDvirshgTester {

    // ===== HotelRoom constructor =====
    @Test
    public void test01_constructor_validValues() {
        HotelRoom r = new HotelRoom(101, 2);
        assertEquals(101, r.getRoomNum());
        assertEquals(2, r.getNumBeds());
        assertFalse(r.isOccupied());
    }

    @Test
    public void test02_constructor_invalidRoom() {
        HotelRoom r = new HotelRoom(50, 2);
        assertEquals(HotelRoom.DEFAULT_ROOM_NUM, r.getRoomNum());
    }

    @Test
    public void test03_constructor_invalidBeds() {
        HotelRoom r = new HotelRoom(200, 1);
        assertEquals(HotelRoom.DEFAULT_NUM_BEDS, r.getNumBeds());
    }

    // ===== checkIn =====
    @Test
    public void test04_checkIn_success() {
        HotelRoom r = new HotelRoom(101, 2);
        assertTrue(r.checkIn("Dan"));
        assertTrue(r.isOccupied());
        assertEquals("Dan", r.getGuest());
    }

    @Test
    public void test05_checkIn_failOccupied() {
        HotelRoom r = new HotelRoom(101, 2);
        r.checkIn("Dan");
        assertFalse(r.checkIn("Avi"));
    }

    // ===== checkOut =====
    @Test
    public void test06_checkOut_afterCheckIn() {
        HotelRoom r = new HotelRoom(101, 2);
        r.checkIn("Dan");
        r.checkOut();
        assertFalse(r.isOccupied());
        assertEquals("", r.getGuest());
    }

    // ===== before / equals =====
    @Test
    public void test07_before_true() {
        HotelRoom r1 = new HotelRoom(101, 2);
        HotelRoom r2 = new HotelRoom(150, 2);
        assertTrue(r1.before(r2));
    }

    @Test
    public void test08_equals_true() {
        HotelRoom r1 = new HotelRoom(101, 2);
        HotelRoom r2 = new HotelRoom(101, 2);
        assertTrue(r1.equals(r2));
    }

    // ===== Hotel methods =====
    @Test
    public void test09_findRoomByNumber_found() {
        HotelRoom r1 = new HotelRoom(101, 2);
        HotelRoom r2 = new HotelRoom(150, 3);
        HotelRoom r3 = new HotelRoom(200, 4);

        HotelRoom result = Hotel.findRoomByNumber(150, r1, r2, r3);
        assertEquals(r2, result);
    }

    @Test
    public void test10_findRoomByNumber_notFound() {
        HotelRoom r1 = new HotelRoom(101, 2);
        HotelRoom r2 = new HotelRoom(150, 3);
        HotelRoom r3 = new HotelRoom(200, 4);

        HotelRoom result = Hotel.findRoomByNumber(999, r1, r2, r3);
        assertNull(result);
    }

    @Test
    public void test11_checkIn_success() {
        HotelRoom r1 = new HotelRoom(101, 2);
        HotelRoom r2 = new HotelRoom(150, 3);
        HotelRoom r3 = new HotelRoom(200, 4);

        Hotel.checkIn("Dana", 150, r1, r2, r3);
        assertTrue(r2.isOccupied());
        assertEquals("Dana", r2.getGuest());
    }

    @Test
    public void test12_checkOut_success() {
        HotelRoom r1 = new HotelRoom(101, 2);
        HotelRoom r2 = new HotelRoom(150, 3);
        HotelRoom r3 = new HotelRoom(200, 4);

        r2.checkIn("Dana");
        Hotel.checkOut(150, r1, r2, r3);
        assertFalse(r2.isOccupied());
    }
}

