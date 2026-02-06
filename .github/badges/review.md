## AI Code Review

❌ Duplicate Code (-10 points)
Methods with duplication: HotelRoom(int,int) and setRoomNum
```java
private int validRoomNum(int roomNum) { return (roomNum < MIN_NUM_ROOM || roomNum > MAX_NUM_ROOM) ? DEF_NUM_ROOM : roomNum; }
```
Recommended adjusted grade: 90%
