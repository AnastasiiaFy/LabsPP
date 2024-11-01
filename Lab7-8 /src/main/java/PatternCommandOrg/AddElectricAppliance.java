package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class AddElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public AddElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to add appliances was called.");

        System.out.println("\n\tEnter the information about the electrical appliance: ");

        try{
            System.out.print("Enter the name: ");
            String name = scanner.nextLine();
            System.out.print("Enter the brand: ");
            String brand = scanner.nextLine();
            System.out.print("Enter the model: ");
            String model = scanner.nextLine();
            System.out.print("Enter the power: ");
            int power = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the purchase date: ");
            String purchaseDate = scanner.nextLine();
            System.out.print("Enter the waranty end date: ");
            String warantyEndDate = scanner.nextLine();
            System.out.print("Enter the price: ");
            int price = scanner.nextInt();
            scanner.nextLine();

            appliances.add(new ElectricalAppliance(name, brand,
                    model, power, purchaseDate, warantyEndDate, price, false));

            System.out.println("Appliance added.");
            fileLogger.info("A new electrical appliance " + name + "was added to the list");
        }catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            errorLogger.error(ERROR_MARKER, "Enter a invalid number: " + e.getMessage(), e);
            scanner.nextLine();
        }
    }
}


