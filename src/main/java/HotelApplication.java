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

import ui.AdminMenu;
import ui.MainMenu;

public class HotelApplication {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();
        while (true) {
            String userInput = mainMenu.showMainMenuAndGetInput();
            switch (userInput) {
                case "1":
                    mainMenu.findAndReserveRoom();
                    break;
                case "2":
                    mainMenu.showMyReservation();
                    break;
                case "3":
                    try {
                        mainMenu.createAnAccount();
                    } catch (IllegalArgumentException exception) {
                        System.out.println("First name and last name cannot be blank. " +
                                "E-mail have to be name@domain.com. " +
                                "Please try again.");
                        mainMenu.createAnAccount();
                    }
                    break;
                case "4":
                    mainMenu.showAdminMenu(adminMenu);
                    break;
                case "5":
                    mainMenu.exitProgram();
                default:
                    System.out.println("Number should be between 1 to 5.");
                    break;
            }
        }

    }
}
