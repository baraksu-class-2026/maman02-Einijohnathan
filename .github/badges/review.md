## AI Code Review

❌ Duplicate Code (-10 points)
Duplicate in methods: checkIn and checkOut
```java
private static void printRoomNotAvailable() { System.out.println("Error: Room not available or not found"); }
```
Nice work — a small helper will make this cleaner.
Recommended adjusted grade: 90%
