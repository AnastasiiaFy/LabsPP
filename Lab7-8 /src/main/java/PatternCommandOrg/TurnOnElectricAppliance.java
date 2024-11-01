package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

class TurnOnElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public TurnOnElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to turn on appliance was called.");
        showTurnedOffAppliances();

        while (true) {
            System.out.print("\n\nEnter index of appliance to turn on: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index--;

            if (index >= 0 && index < appliances.size()) {
                appliances.get(index).turnOn();

                System.out.println("Appliance turned on.");
                fileLogger.info("The " + appliances.get(index).getName() + " was turned on");
                break;
            } else {
                System.out.println("Invalid index. Please try again.");
                fileLogger.warn("User selected an invalid index to turn on appliance.");
            }
        }
    }

    public void showTurnedOffAppliances() {
        for (int i = 0; i < appliances.size(); i++) {
            if (!appliances.get(i).isPluggedIn()) {
                System.out.print("\n" + (i + 1) + ": " + appliances.get(i));
            }
        }
        fileLogger.info("List of turned off appliances was shown");
    }
}
