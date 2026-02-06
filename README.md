## Grade

[![Grade](https://img.shields.io/badge/Grade-90%25-brightgreen)](https://github.com/baraksu-class-2026/maman02-Einijohnathan/actions/runs/21748008110) *(Original: 100%, Penalties: -10)*

## AI Code Review

❌ Duplicate Code (-10 points)
Methods with duplication: HotelRoom(int,int) and setRoomNum
```java
private int validRoomNum(int roomNum) { return (roomNum < MIN_NUM_ROOM || roomNum > MAX_NUM_ROOM) ? DEF_NUM_ROOM : roomNum; }
```
Recommended adjusted grade: 90%
