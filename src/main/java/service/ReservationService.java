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
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static final Map<String, IRoom> roomMap = new HashMap<>();
    private static final Map<IRoom, Set<Reservation>> roomListMap = new HashMap<>();

    private static ReservationService instance;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }


    public Collection<IRoom> getAllRooms() {
        return roomMap.values();
    }

    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
        roomListMap.put(room, new HashSet<>());
    }

    public IRoom getARoom(String roomId) {
        return roomMap.get(roomId);
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        roomListMap.computeIfAbsent(room, k -> new HashSet<>()).add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        ArrayList<IRoom> availableRoom = new ArrayList<>();
        for (IRoom room : roomMap.values()
        ) {
            if (roomListMap.get(room).isEmpty()) availableRoom.add(room);
            else {
                boolean isAvailable = true;
                for (Reservation r : roomListMap.get(room)) {
                    boolean checkInDateNotAvailable = !checkInDate.before(r.getCheckInDate()) &&
                            (!checkInDate.after((r.getCheckOutDate())));
                    boolean checkOutDateNotAvailable = !checkOutDate.before(r.getCheckInDate()) &&
                            (!checkInDate.after((r.getCheckOutDate())));
                    if (checkInDateNotAvailable || checkOutDateNotAvailable) {
                        isAvailable = false;
                        break;
                    }
                }
                if (isAvailable) availableRoom.add(room);
            }
        }
        return availableRoom;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> cr = new ArrayList<>();
        for (Set<Reservation> sr : roomListMap.values()
        ) {
            for (Reservation r : sr
            ) {
                if (r.getCustomer().equals(customer)) {
                    cr.add(r);
                }
            }
        }
        return cr;
    }

    public void printAllReservation() {
        for (Collection<Reservation> cr : roomListMap.values()
        ) {
            for (Reservation r : cr
            ) {
                System.out.println(r);
            }
        }
    }
}
