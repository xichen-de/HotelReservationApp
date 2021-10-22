package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Room implements IRoom {
    private final String roomNumber;
    private final double price;
    private final RoomType roomType;


    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room number='" + roomNumber + '\'' +
                ", price=" + price +
                ", room type=" + roomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Double.compare(room.price, price) == 0 &&
                Objects.equals(roomNumber, room.roomNumber) &&
                roomType == room.roomType;
    }


    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, roomType);
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        BigDecimal priceBigDecimal = new BigDecimal(String.valueOf(price));
        BigDecimal freePrice = new BigDecimal("0.0");
        return priceBigDecimal.compareTo(freePrice) == 0;
    }
}
