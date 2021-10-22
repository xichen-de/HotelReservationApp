package service;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationServiceTest {
    ReservationService reservationService = ReservationService.getInstance();
    Customer customer = new Customer("a", "b", "abc@gmail.com");
    Date checkInDate = new Date();
    Date checkOutDate = new Date();

    @BeforeEach
    void setUp() {
        reservationService.addRoom(new Room("1", 1.0, RoomType.DOUBLE));
        reservationService.addRoom(new Room("2", 2.0, RoomType.SINGLE));
        reservationService.addRoom(new Room("3", 3.0, RoomType.DOUBLE));
    }

    @Test
    void getARoom() {
        IRoom room = reservationService.getARoom("1");
        assertEquals(room, (new Room("1", 1.0, RoomType.DOUBLE)));
    }

    @Test
    void reserveARoom() {
        reservationService.reserveARoom(customer,
                reservationService.getARoom("1"),
                checkInDate,
                checkOutDate);
        int numReservation = reservationService.getCustomersReservation(customer).size();
        assertEquals(1, numReservation);
    }

    @Test
    void findRooms() {
        reserveARoom();
        int numAvailableRoom = reservationService.findRooms(checkInDate, checkOutDate).size();
        assertEquals(2, numAvailableRoom);
    }
}