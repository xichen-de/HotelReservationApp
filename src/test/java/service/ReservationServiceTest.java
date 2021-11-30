/*
 * MIT License
 *
 * Copyright (c) 2021 Xi Chen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
    final ReservationService reservationService = ReservationService.getInstance();
    final Customer customer = new Customer("a", "b", "abc@gmail.com");
    final Date checkInDate = new Date();
    final Date checkOutDate = new Date();

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