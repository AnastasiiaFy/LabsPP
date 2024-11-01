package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public static void main(String[] args) {
        ArrayList<ElectricalAppliance> appliances = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        MenuManager manager = new MenuManager();
        Home home = new Home(appliances, scanner, manager);

        int choice = 0;
        printMenu();

        do{
            System.out.print("\nEnter an option - ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> home.downloadFromFile();
                    case 2 -> home.showList();
                    case 3 -> home.addAppliance();
                    case 4 -> home.deleteAppliance();
                    case 5 -> home.turnOnAppliance();
                    case 6 -> home.turnOffAppliance();
                    case 7 -> home.sort();
                    case 8 -> home.find();
                    case 9 -> home.calculateConsumption();
                    case 10 -> home.calculatePrice();
                    case 11 -> {
                        home.writeInFile();
                        System.out.println("Changes successfully written to file");
                        System.out.println("Exit from the program.");
                        fileLogger.info("The program completed");
                        scanner.close();
                    }
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        fileLogger.warn("User selected an invalid option.");
                    }
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                errorLogger.error(ERROR_MARKER, "Enter an invalid number: " + e.getMessage(), e);
                scanner.nextLine();
            }
        }while (choice != 11);
    }

    private static void printMenu() {
        System.out.println("\n\t\t\t\t----- Menu -----");
        System.out.println("\t\t1. Download electrical appliances from file.");
        System.out.println("\t\t2. Show a list of all electrical appliances.");
        System.out.println("\t\t3. Add new electrical appliance.");
        System.out.println("\t\t4. Delete electrical appliance.");
        System.out.println("\t\t5. Turn on electrical appliance.");
        System.out.println("\t\t6. Turn off electrical appliance.");
        System.out.println("\t\t7. Sort electrical appliance by power.");
        System.out.println("\t\t8. Find electrical appliance by parameter.");
        System.out.println("\t\t9. Calculate the power consumption of turned on electrical appliances.");
        System.out.println("\t\t10. Calculate the price of electrical appliances' work.");
        System.out.println("\t\t11. Exit.");

        fileLogger.info("Menu was shown");
    }
}
