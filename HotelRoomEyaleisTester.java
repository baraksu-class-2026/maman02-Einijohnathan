
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelRoomEyaleisTester {

    @Test
    public void getRoomNum1() {
        HotelRoom room = new HotelRoom(201, 302);
        assertEquals(201, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum2() {
        HotelRoom room = new HotelRoom(992, 302);
        assertEquals(992, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum3() {
        HotelRoom room = new HotelRoom(-1, 302);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum4() {
        HotelRoom room = new HotelRoom(1002, 302);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum5() {
        HotelRoom room = new HotelRoom(203, 302);
        room.setRoomNum(200);
        assertEquals(200, room.getRoomNum());
    }
    
    @Test
    public void getNumBeds1() {
        HotelRoom room = new HotelRoom(102, 3);
        assertEquals(3, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds2() {
        HotelRoom room = new HotelRoom(221, 4);
        assertEquals(4, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds3() {
        HotelRoom room = new HotelRoom(222, 1);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds4() {
        HotelRoom room = new HotelRoom(212, 5);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds5() {
        HotelRoom room = new HotelRoom(203, 201);
        room.setNumBeds(-10);
        assertEquals(2, room.getNumBeds());
    }
    
     @Test
    public void isOccupied1() {
        HotelRoom room = new HotelRoom(101, 2);
        assertFalse(room.isOccupied());
    }

    @Test
    public void isOccupied2() {
        HotelRoom room = new HotelRoom(101, 2);
        room.checkIn("Eyal");
        assertTrue(room.isOccupied());
    }

    @Test
    public void isOccupied3() {
        HotelRoom room = new HotelRoom(202, 3);
        assertFalse(room.isOccupied());
    }

    @Test
    public void isOccupied4() {
        HotelRoom room = new HotelRoom(202, 3);
        room.checkIn("Eyal");
        assertTrue(room.isOccupied());
    }

    @Test
    public void isOccupied5() {
        HotelRoom room = new HotelRoom(303, 1);
        room.checkIn("Eyal");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void getGuest1() {
        HotelRoom room = new HotelRoom(101, 2);
        assertEquals("", room.getGuest());
    }

    @Test
    public void getGuest2() {
        HotelRoom room = new HotelRoom(101, 2);
        room.checkIn("Eyal");
        assertEquals("Eyal", room.getGuest());
    }

    @Test
    public void getGuest3() {
        HotelRoom room = new HotelRoom(202, 3);
        room.checkIn("Eyal");
        assertEquals("Eyal", room.getGuest());
    }

    @Test
    public void getGuest4() {
        HotelRoom room = new HotelRoom(303, 1);
        room.checkIn("Eyal");
        room.checkOut();
        assertEquals("", room.getGuest());
    }

    @Test
    public void getGuest5() {
        HotelRoom room = new HotelRoom(404, 4);
        assertEquals("", room.getGuest());
    }

    @Test
    public void equals1() {
        HotelRoom room1 = new HotelRoom(101, 2);
        HotelRoom room2 = new HotelRoom(101, 2);
        assertTrue(room1.equals(room2));
    }

    @Test
    public void equals2() {
        HotelRoom room1 = new HotelRoom(101, 2);
        HotelRoom room2 = new HotelRoom(101, 3);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals3() {
        HotelRoom room1 = new HotelRoom(201, 2);
        HotelRoom room2 = new HotelRoom(202, 2);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals4() {
        HotelRoom room1 = new HotelRoom(555, 4);
        HotelRoom room2 = new HotelRoom(555, 4);
        assertTrue(room1.equals(room2));
    }

    @Test
    public void equals5() {
        HotelRoom room1 = new HotelRoom(999, 1);
        HotelRoom room2 = new HotelRoom(998, 1);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void before1() {
        HotelRoom room1 = new HotelRoom(101, 2);
        HotelRoom room2 = new HotelRoom(201, 2);
        assertTrue(room1.before(room2));
    }

    @Test
    public void before2() {
        HotelRoom room1 = new HotelRoom(102, 2);
        HotelRoom room2 = new HotelRoom(101, 3);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before3() {
        HotelRoom room1 = new HotelRoom(201, 3);
        HotelRoom room2 = new HotelRoom(201, 2);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before4() {
        HotelRoom room1 = new HotelRoom(300, 1);
        HotelRoom room2 = new HotelRoom(200, 1);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before5() {
        HotelRoom room1 = new HotelRoom(150, 2);
        HotelRoom room2 = new HotelRoom(150, 2);
        assertFalse(room1.before(room2));
    }

    @Test
    public void after1() {
        HotelRoom room1 = new HotelRoom(201, 2);
        HotelRoom room2 = new HotelRoom(101, 2);
        assertTrue(room1.after(room2));
    }

    @Test
    public void after2() {
        HotelRoom room1 = new HotelRoom(101, 3);
        HotelRoom room2 = new HotelRoom(101, 2);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after3() {
        HotelRoom room1 = new HotelRoom(101, 2);
        HotelRoom room2 = new HotelRoom(201, 2);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after4() {
        HotelRoom room1 = new HotelRoom(400, 1);
        HotelRoom room2 = new HotelRoom(400, 1);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after5() {
        HotelRoom room1 = new HotelRoom(500, 4);
        HotelRoom room2 = new HotelRoom(500, 5);
        assertFalse(room1.after(room2));
    }

    @Test
    public void checkIn1() {
        HotelRoom room = new HotelRoom(101, 2);
        assertTrue(room.checkIn("Eyal"));
    }

    @Test
    public void checkIn2() {
        HotelRoom room = new HotelRoom(101, 2);
        room.checkIn("Eyal");
        assertFalse(room.checkIn("Eitan"));
    }

    @Test
    public void checkIn3() {
        HotelRoom room = new HotelRoom(101, 2);
        assertTrue(room.checkIn(""));
    }

    @Test
    public void checkIn4() {
        HotelRoom room = new HotelRoom(101, 2);
        assertTrue(room.checkIn("-"));
    }

    @Test
    public void checkIn5() {
        HotelRoom room = new HotelRoom(303, 3);
        assertTrue(room.checkIn("Eyal"));
    }

    @Test
    public void checkOut1() {
        HotelRoom room = new HotelRoom(101, 2);
        room.checkIn("Eyal");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut2() {
        HotelRoom room = new HotelRoom(202, 2);
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut3() {
        HotelRoom room = new HotelRoom(303, 1);
        room.checkIn("Eyal");
        room.checkOut();
        assertEquals("", room.getGuest());
    }

    @Test
    public void checkOut4() {
        HotelRoom room = new HotelRoom(404, 4);
        room.checkIn("Eyal");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut5() {
        HotelRoom room = new HotelRoom(505, 2);
        assertFalse(room.isOccupied());
    }
}

