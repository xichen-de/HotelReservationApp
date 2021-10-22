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
