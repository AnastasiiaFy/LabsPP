package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class FindByParameters implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public FindByParameters(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to find appliances by range of parameters was called.");

        System.out.println("\n1. Find by name.");
        System.out.println("2. Find by power.");
        System.out.println("3. Find by price.");

        System.out.print("Enter the search variant: ");
        int variant = scanner.nextInt();
        scanner.nextLine();
        int min = 0;
        int max = 0;

        switch (variant) {
            case 1:
                System.out.print("Enter the name of the appliance: ");
                String searchName = scanner.nextLine().toLowerCase();

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.getName().toLowerCase().equals(searchName)) {
                        System.out.println(appliance);
                    }
                }
                fileLogger.info("The electrical appliances were found by name");
                break;
            case 2:
                try {
                    System.out.print("Enter the min power: ");
                    min = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the max power: ");
                    max = scanner.nextInt();
                    scanner.nextLine();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    errorLogger.error(ERROR_MARKER, "Enter an invalid number: " + e.getMessage(), e);
                }

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.getPower() >= min && appliance.getPower() <= max) {
                        System.out.println(appliance);
                    }
                }
                fileLogger.info("The electrical appliances were found by range of power");
                break;
            case 3:
                try{
                    System.out.print("Enter the min price: ");
                    min = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the max price: ");
                    max = scanner.nextInt();
                    scanner.nextLine();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    errorLogger.error(ERROR_MARKER, "Enter an invalid number: " + e.getMessage(), e);
                }

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.getPrice() >= min && appliance.getPrice() <= max) {
                        System.out.println(appliance);
                    }
                }
                fileLogger.info("The electrical appliances were found by range of price");
                break;
            default:
                System.out.println("Invalid input.");
                fileLogger.warn("User selected an invalid variant.");
                break;
        }
    }
}
