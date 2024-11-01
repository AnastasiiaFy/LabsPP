package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.ArrayList;
import java.util.Scanner;

class CalculatePriceOfWork implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public CalculatePriceOfWork(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to calculate price of work appliances was called.");

        int powerSum = 0;
        float kWPrice = 0;
        int hours = 0;
        float workPrice = 0;
        String inputKW;

        System.out.println("\n1.Calculate price of work of turned on electrical appliances.");
        System.out.println("2.Calculate price of selected electrical appliances.");

        System.out.print("Enter the variant: ");
        int variant = scanner.nextInt();
        scanner.nextLine();

        switch (variant) {
            case 1:
                System.out.print("Enter the price per kilowatt(kW) of energy: ");
                inputKW = scanner.nextLine().replace(',', '.');
                kWPrice = Float.parseFloat(inputKW);

                System.out.print("Enter number of hours of work: ");
                hours = scanner.nextInt();
                scanner.nextLine();

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.isPluggedIn()) {
                        powerSum += appliance.getPower();
                    }
                }

                workPrice = (float) (powerSum * hours) / 1000 * kWPrice;
                System.out.println("Price of work of turned on electrical appliances - " + workPrice);
                fileLogger.info("The price of work turned on appliances was calculated");
                break;
            case 2:
                ShowListOfElectricAppliances showCommand = new ShowListOfElectricAppliances(appliances, scanner);
                MenuManager manager3 = new MenuManager();
                manager3.runCommand(showCommand, scanner);

                System.out.print("\n\nEnter the indexes of appliances to calculate power for (separated by spaces): ");
                String inputIndexes = scanner.nextLine();
                String[] indicesString = inputIndexes.split(" ");

                for (String indexStr : indicesString) {
                    try {
                        int index = Integer.parseInt(indexStr);

                        if (index >= 1 && index <= appliances.size()) {
                            powerSum += appliances.get(index - 1).getPower();
                        } else {
                            System.out.println("Index " + index + " is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input: '" + indexStr + "' is not a valid number.");
                        errorLogger.error(ERROR_MARKER, "Enter an invalid number: " + e.getMessage(), e);
                        break;
                    }
                }

                System.out.print("Enter the price per kilowatt(kW) of energy: ");
                inputKW = scanner.nextLine().replace(',', '.');
                kWPrice = Float.parseFloat(inputKW);
                System.out.print("Enter number of hours of work: ");
                hours = scanner.nextInt();
                scanner.nextLine();

                workPrice = (float) (powerSum * hours) / 1000 * kWPrice;
                System.out.println("Price of work of selected electrical appliances - " + workPrice);
                fileLogger.info("The price of work selected appliances was calculated");
                break;
            default:
                System.out.println("Invalid input.");
                fileLogger.warn("User selected an invalid option.");
        }
    }
}
