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

package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static Scanner inputScanner;
    private static HotelResource hotelResource;

    public MainMenu() {
        hotelResource = new HotelResource();
        inputScanner = new Scanner(System.in);
    }


    public void exitProgram() {
        System.exit(0);
    }

    public void showAdminMenu(AdminMenu adminMenu) {
        adminMenu.adminMenu();
    }

    public void createAnAccount() {
        System.out.println("Input first name");
        String firstName = inputScanner.nextLine();
        System.out.println("Input last name");
        String lastName = inputScanner.nextLine();
        System.out.println("Input E-mail");
        String email = inputScanner.nextLine();
        hotelResource.createACustomer(email, firstName, lastName);

    }

    public void showMyReservation() {
        System.out.println("Input E-mail");
        String email = inputScanner.nextLine();
        for (Reservation r : hotelResource.getCustomerReservations(email)
        ) {
            System.out.println(r);
        }
    }

    public String showMainMenuAndGetInput() {
        printMainMenu();
        System.out.println("Please select an item:");
        return inputScanner.nextLine();
    }

    public void printMainMenu() {
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
    }

    public void findAndReserveRoom() {
        System.out.println("Input check in date (dd/MM/yyyy):");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date checkInDate;
        try {
            checkInDate = sdf.parse(inputScanner.nextLine());
        } catch (ParseException e) {
            System.out.println("The date does not fit the format!");
            return;
        }
        System.out.println("Input check out date (dd/MM/yyyy):");
        Date checkOutDate;
        try {
            checkOutDate = sdf.parse(inputScanner.nextLine());
        } catch (ParseException e) {
            System.out.println("The date does not fit the format!");
            return;
        }
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if (!availableRooms.isEmpty()) {
            System.out.println("There are rooms available:");
            bookARoom(checkInDate, checkOutDate, availableRooms);
        } else {
            System.out.println("No rooms available. Search for recommended rooms...");
            Date newCheckInDate = sevenDaysAfterDate(checkInDate);
            Date newCheckOutDate = sevenDaysAfterDate(checkOutDate);
            Collection<IRoom> newAvailableRooms = hotelResource.findARoom(newCheckInDate, newCheckOutDate);
            if (!newAvailableRooms.isEmpty()) {
                bookARoom(newCheckInDate, newCheckOutDate, newAvailableRooms);
            } else {
                System.out.println("There is no room available. Sorry.");
            }
        }
    }

    private void bookARoom(Date checkInDate, Date checkOutDate, Collection<IRoom> availableRooms) {
        System.out.println(availableRooms);
        System.out.println("Check in date: " + checkInDate + " , check out date: " + checkOutDate);
        System.out.println("Which available room do you want to reserve? Please input room number.");
        String choice = inputScanner.nextLine();
        if (!validateRoomNumber(availableRooms, choice)) {
            System.out.println("Invalid room number.");
            return;
        }
        System.out.println("Please input your E-mail:");
        String customerEmail = inputScanner.nextLine();
        if (!validateUser(customerEmail)) {
            System.out.println("You have to register an account first.");
            return;
        }
        hotelResource.bookARoom(customerEmail, hotelResource.getRoom(choice), checkInDate, checkOutDate);
        System.out.println("Reservation successful.");
    }

    private boolean validateRoomNumber(Collection<IRoom> availableRooms, String choice) {
        for (IRoom r : availableRooms
        ) {
            if (choice.equals(r.getRoomNumber())) return true;
        }
        return false;
    }

    private boolean validateUser(String userEmail) {
        return hotelResource.getCustomer(userEmail) != null;
    }

    private Date sevenDaysAfterDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }
}
