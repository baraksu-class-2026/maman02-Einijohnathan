
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelRoomDanielBeharblipTester {

    @Test
    public void getRoomNum1() {
        HotelRoom room = new HotelRoom(345, 22);
        assertEquals(345, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum2() {
        HotelRoom room = new HotelRoom(721, 22);
        assertEquals(721, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum3() {
        HotelRoom room = new HotelRoom(-8, 22);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum4() {
        HotelRoom room = new HotelRoom(1004, 22);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum5() {
        HotelRoom room = new HotelRoom(100, 22);
        room.setRoomNum(900);
        assertEquals(900, room.getRoomNum());
    }
    
    @Test
    public void getNumBeds1() {
        HotelRoom room = new HotelRoom(222, 3);
        assertEquals(3, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds2() {
        HotelRoom room = new HotelRoom(222, 4);
        assertEquals(4, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds3() {
        HotelRoom room = new HotelRoom(222, 1);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds4() {
        HotelRoom room = new HotelRoom(222, 5);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds5() {
        HotelRoom room = new HotelRoom(222, 201);
        room.setNumBeds(-10);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getGuest1() {
        HotelRoom room = new HotelRoom(222, 2);
        room.checkIn("Thing");
        assertEquals("Thing", room.getGuest());
    }

    @Test
    public void getGuest2() {
        HotelRoom room = new HotelRoom(222, 2);
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void getGuest3() {
        HotelRoom room = new HotelRoom(222, 2);
        room.checkIn("Eyal");
        assertEquals("Eyal", room.getGuest());
    }

    @Test
    public void getGuest4() {
        HotelRoom room = new HotelRoom(222, 2);
        room.checkIn("Eyal");
        room.checkOut();
        assertEquals("", room.getGuest());
    }

    @Test
    public void getGuest5() {
        HotelRoom room = new HotelRoom(222, 2);
        assertEquals("", room.getGuest());
    }

     @Test
    public void isOccupied1() {
        HotelRoom room = new HotelRoom(222, 2);
        assertFalse(room.isOccupied());
    }

    @Test
    public void isOccupied2() {
        HotelRoom room = new HotelRoom(222, 2);
        room.checkIn("Daniel");
        assertTrue(room.isOccupied());
    }

    @Test
    public void isOccupied3() {
        HotelRoom room = new HotelRoom(222, 3);
        assertFalse(room.isOccupied());
    }

    @Test
    public void isOccupied4() {
        HotelRoom room = new HotelRoom(222, 3);
        room.checkIn("HamoreBarack");
        assertTrue(room.isOccupied());
    }

    @Test
    public void isOccupied5() {
        HotelRoom room = new HotelRoom(222, 1);
        room.checkIn("Tzofia");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void equals1() {
        HotelRoom room1 = new HotelRoom(222, 2);
        HotelRoom room2 = new HotelRoom(222, 2);
        assertTrue(room1.equals(room2));
    }

    @Test
    public void equals2() {
        HotelRoom room1 = new HotelRoom(222, 2);
        HotelRoom room2 = new HotelRoom(222, 3);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals3() {
        HotelRoom room1 = new HotelRoom(222, 2);
        HotelRoom room2 = new HotelRoom(221, 2);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals4() {
        HotelRoom room1 = new HotelRoom(222, 4);
        HotelRoom room2 = new HotelRoom(222, 4);
        assertTrue(room1.equals(room2));
    }

    @Test
    public void equals5() {
        HotelRoom room1 = new HotelRoom(223, 1);
        HotelRoom room2 = new HotelRoom(222, 1);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void before1() {
        HotelRoom room1 = new HotelRoom(222, 2);
        HotelRoom room2 = new HotelRoom(223, 2);
        assertTrue(room1.before(room2));
    }

    @Test
    public void before2() {
        HotelRoom room1 = new HotelRoom(223, 2);
        HotelRoom room2 = new HotelRoom(222, 3);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before3() {
        HotelRoom room1 = new HotelRoom(222, 3);
        HotelRoom room2 = new HotelRoom(222, 2);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before4() {
        HotelRoom room1 = new HotelRoom(442, 1);
        HotelRoom room2 = new HotelRoom(224, 1);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before5() {
        HotelRoom room1 = new HotelRoom(222, 2);
        HotelRoom room2 = new HotelRoom(222, 2);
        assertFalse(room1.before(room2));
    }

    @Test
    public void after1() {
        HotelRoom room1 = new HotelRoom(322, 2);
        HotelRoom room2 = new HotelRoom(222, 2);
        assertTrue(room1.after(room2));
    }

    @Test
    public void after2() {
        HotelRoom room1 = new HotelRoom(222, 3);
        HotelRoom room2 = new HotelRoom(222, 2);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after3() {
        HotelRoom room1 = new HotelRoom(233, 2);
        HotelRoom room2 = new HotelRoom(332, 2);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after4() {
        HotelRoom room1 = new HotelRoom(222, 1);
        HotelRoom room2 = new HotelRoom(222, 1);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after5() {
        HotelRoom room1 = new HotelRoom(333, 2);
        HotelRoom room2 = new HotelRoom(333, 2);
        assertFalse(room1.after(room2));
    }

    @Test
    public void checkIn1() {
        HotelRoom room = new HotelRoom(222, 2);
        assertTrue(room.checkIn("Daniel"));
    }

    @Test
    public void checkIn2() {
        HotelRoom room = new HotelRoom(222, 2);
        room.checkIn("Daniel");
        assertFalse(room.checkIn("Tzofia"));
    }

    @Test
    public void checkIn3() {
        HotelRoom room = new HotelRoom(222, 2);
        assertTrue(room.checkIn(""));
    }

    @Test
    public void checkIn4() {
        HotelRoom room = new HotelRoom(222, 2);
        assertTrue(room.checkIn("HappyFace"));
    }

    @Test
    public void checkIn5() {
        HotelRoom room = new HotelRoom(222, 2);
        assertTrue(room.checkIn("Daniel"));
    }

    @Test
    public void checkOut1() {
        HotelRoom room = new HotelRoom(111, 1);
        room.checkIn("Daniel");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut2() {
        HotelRoom room = new HotelRoom(222, 2);
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut3() {
        HotelRoom room = new HotelRoom(333, 3);
        room.checkIn("Daniel");
        room.checkOut();
        assertEquals("", room.getGuest());
    }

    @Test
    public void checkOut4() {
        HotelRoom room = new HotelRoom(444, 4);
        room.checkIn("Daniel");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut5() {
        HotelRoom room = new HotelRoom(555, 5);
        assertFalse(room.isOccupied());
    }
}