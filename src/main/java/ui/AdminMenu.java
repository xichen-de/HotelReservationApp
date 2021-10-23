/*
 * PROJECT LICENSE
 *
 * This project was submitted by Xi Chen as part of the Nanodegree At Udacity.
 *
 * As part of Udacity Honor code, your submissions must be your own work, hence
 * submitting this project as yours will cause you to break the Udacity Honor Code
 * and the suspension of your account.
 *
 * Me, the author of the project, allow you to check the code as a reference, but if
 * you submit it, it's your own responsibility if you get expelled.
 *
 * Copyright (c) 2021 Xi Chen
 *
 * Besides the above notice, the following license applies and this license notice
 * must be included in all works derived from this project.
 *
 * MIT License
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ui;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static Scanner inputScanner;
    private static AdminResource adminResource;

    public AdminMenu() {
        adminResource = new AdminResource();
        inputScanner = new Scanner(System.in);
    }

    public void printAdminMenu() {
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
    }


    public void addARoom() {
        List<IRoom> rooms = new ArrayList<>();
        do {
            System.out.println("Please enter room number:");
            String roomNumber = inputScanner.nextLine();
            System.out.println("Please enter price:");
            double price = Double.parseDouble(inputScanner.nextLine());
            System.out.println("Please enter room type: SINGLE, DOUBLE");
            RoomType roomType = RoomType.valueOf(inputScanner.nextLine());
            rooms.add(new Room(roomNumber, price, roomType));
            System.out.println("Finished? y/n");
        } while (!inputScanner.nextLine().equals("y"));
        adminResource.addRoom(rooms);
    }


    public void showAllReservations() {
        adminResource.displayAllReservations();
    }

    public void showAllRooms() {
        for (IRoom room : adminResource.getAllRooms()) {
            System.out.println(room);
        }
    }

    public void showAllCustomers() {
        for (Customer c : adminResource.getAllCustomers()) {
            System.out.println(c);
        }
    }

    public void adminMenu() {
        outerLoop:
        while (true) {
            printAdminMenu();
            String userInput = inputScanner.nextLine();
            switch (userInput) {
                case "1":
                    showAllCustomers();
                    break;
                case "2":
                    showAllRooms();
                    break;
                case "3":
                    showAllReservations();
                    break;
                case "4":
                    addARoom();
                    break;
                case "5":
                    break outerLoop;
                default:
                    System.out.println("Number should be between 1 to 5.");
                    break;
            }
        }
    }
}
