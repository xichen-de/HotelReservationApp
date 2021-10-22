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
