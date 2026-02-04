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
        if (roomNum < MIN_NUM_ROOM || roomNum > MAX_NUM_ROOM){
            _roomNum = DEF_NUM_ROOM;
        }
        else{
            _roomNum = roomNum;
        }
        if (numBeds < MIN_NUM_BEDS || numBeds > MAX_NUM_BEDS){
            _numBeds = DEF_NUM_BEDS;
        }
        else{
            _numBeds = numBeds;
        }
        _occupied = DEF_OCCUPIED;
        _guest = DEF_GUEST;
    }
    public int getRoomNum(){
        return _roomNum;
    }
    public int getNumBeds(){
        return _numBeds;
    }
    public boolean isOccupied(){
        return _occupied;
    }
    public String getGuest(){
        return _guest;
    }
    public void setRoomNum(int roomNum){
        if (roomNum < MIN_NUM_ROOM || roomNum > MAX_NUM_ROOM){
            _roomNum = DEF_NUM_ROOM;
        }
        else{
            _roomNum = roomNum;
        }
    }
    public void setNumBeds(int numBeds){
        if (numBeds < MIN_NUM_BEDS || numBeds > MAX_NUM_BEDS){
            _numBeds = DEF_NUM_BEDS;
        }
        else{
            _numBeds = numBeds;
        }
    }
    private boolean roomNumOk(int roomNum){
        return(roomNum >= MIN_NUM_ROOM && roomNum <= MAX_NUM_ROOM);
            
    }
    private boolean numBedsOk(int numbed){
        return(numbed >= MIN_NUM_BEDS && numbed <= MAX_NUM_BEDS);
    }
    public String toString(){
        if (_occupied == false)
            return "Room "+ _roomNum + ", " + _numBeds + " beds: Available";
        return "Room "+ _roomNum + ", " + _numBeds + " beds: Occupied by Dana Levi";
    }
    public boolean equals(HotelRoom other){
        if (other == null){
            return false;
        }
        if (this._roomNum == other._roomNum && this._numBeds == other._numBeds)
            return true;
        return false;
    }
    public boolean before(HotelRoom other){
        if (other == null){
            return false;
        }
        if (this._roomNum < other._roomNum)
            return true;
        return false;
    }
    public boolean after(HotelRoom other){
            return (other.before(this));
    }
    public boolean checkIn(String guest){
        if(_occupied == false){
            _occupied = true;
            _guest = guest;
            return true;
        }
        return false;
    }
    public void checkOut(){
        _occupied = false;
        _guest = DEF_GUEST;
    }
}
