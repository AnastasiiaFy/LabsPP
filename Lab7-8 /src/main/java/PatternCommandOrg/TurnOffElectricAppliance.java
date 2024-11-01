package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

class TurnOffElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public TurnOffElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to turn off appliance was called.");
        showTurnedOnAppliances();

        while (true) {
            System.out.print("\n\nEnter index of appliance to turn off: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index--;

            if (index >= 0 && index < appliances.size()) {
                appliances.get(index).turnOff();

                System.out.println("Appliance turned off.");
                fileLogger.info("The " + appliances.get(index).getName() + " was turned off");
                break;
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        }
    }

    public void showTurnedOnAppliances() {
        for (int i = 0; i < appliances.size(); i++) {
            if (appliances.get(i).isPluggedIn()) {
                System.out.print("\n" + (i + 1) + ": " + appliances.get(i));
            }
        }
        fileLogger.info("List of turned on appliances was shown");
    }
}
