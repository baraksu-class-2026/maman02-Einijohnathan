import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelRoomI5am5coolTester {
    @Test
    public void equals1() {
        HotelRoom r1 = new HotelRoom(546, 2);
        HotelRoom r2 = new HotelRoom(546, 2);
        assertTrue(r1.equals(r2));
    }
    
    @Test
    public void equals2() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(654, 4);
        assertFalse(r1.equals(r2));
    }
    
    @Test
    public void equals3() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(652, 3);
        assertFalse(r1.equals(r2));
    }
    
    @Test
    public void equals4() {
        HotelRoom r1 = new HotelRoom(652, 3);
        HotelRoom r2 = null;
        assertFalse(r1.equals(r2));
    }
    
    @Test
    public void equals5() {
        HotelRoom r1 = new HotelRoom(1009, 1);
        HotelRoom r2 = new HotelRoom(9, 10);
        assertTrue(r1.equals(r2));
    }
    
    @Test
    public void before1() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(652, 3);
        assertFalse(r1.before(r2));
    }
    
    @Test
    public void before2() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(652, 3);
        assertTrue(r2.before(r1));
    }
    
    @Test
    public void before3() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(651, 3);
        assertFalse(r1.before(r2));
    }
    
    @Test
    public void before4() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(654, 3);
        assertFalse(r2.before(r1));
    }
    
    @Test
    public void before5() {
        HotelRoom r1 = new HotelRoom(6545, 32);
        HotelRoom r2 = r1;
        assertFalse(r1.before(r2));
    }
    
    @Test
    public void after1() {
        HotelRoom r1 = new HotelRoom(6545, 32);
        HotelRoom r2 = r1;
        assertFalse(r1.after(r2));
    }
    
    @Test
    public void after2() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(652, 3);
        assertTrue(r1.after(r2));
    }
    
    @Test
    public void after3() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(654, 3);
        assertFalse(r1.after(r2));
    }
    
    @Test
    public void after4() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(651, 3);
        assertTrue(r1.after(r2));
    }
    
    @Test
    public void aftre5() {
        HotelRoom r1 = new HotelRoom(654, 3);
        HotelRoom r2 = new HotelRoom(652, 3);
        assertFalse(r2.after(r1));
    }
}