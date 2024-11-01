package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

class ShowListOfElectricAppliances implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public ShowListOfElectricAppliances(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to show the list of appliances was called.");
        System.out.println("\n\t---- List of electrical appliances ----");
        for (int i = 0; i < appliances.size(); i++) {
            System.out.print("\n" + (i + 1) + ": " + appliances.get(i));
        }
        fileLogger.info("List of all electrical appliances was shown");
    }
}
