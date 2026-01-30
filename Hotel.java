
public class HotelRoom
{
    private int _roomNum;
    private int _numBeds;
    private boolean _occupied;
    private String _guest;
    
    static final int MIN_NUM_ROOM = 100;
    static final int MAX_NUM_ROOM = 999;
    static final int DEF_NUM_ROOM = 999;
    static final int DEF_NUM_BEDS = 2;
    static final int MIN_NUM_BEDS = 2;
    static final int MAX_NUM_BEDS = 4;
    static final boolean DEF_OCCUPIED = false;
    static final String DEF_GUEST = "";
    
    public HotelRoom(){
    _roomNum = DEF_NUM_ROOM;
    _numBeds = DEF_NUM_BEDS;
    _occupied = DEF_OCCUPIED;
    _guest = DEF_GUEST;
    }
    public HotelRoom(int roomNum, int numBeds){
        if (roomNUM < MIN_NUM_ROOM && roomNUM > MAX_NUM_ROOM){
            _roomNum = DEF_NUM_ROOM;
        }
        else{
            _roomNum = roomNum;
        }
        if (numBeds < MIN_NUM_BEDS && numBeds > MAX_NUM_BEDS){
            _numBeds = DEF_NUM_BEDS;
        }
        else{
            _numBeds = numBeds;
        }
        
    }
    
}