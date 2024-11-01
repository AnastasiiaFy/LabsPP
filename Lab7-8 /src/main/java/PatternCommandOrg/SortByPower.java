package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class SortByPower implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public SortByPower(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to sort appliances by power was called.");
        ArrayList<ElectricalAppliance> sortedAppliances = new ArrayList<>(appliances);

        sortedAppliances.sort(Comparator.comparingInt(ElectricalAppliance::getPower));

        System.out.println("\n\t---- Electrical appliances sorted by power ----");
        for (ElectricalAppliance appliance : sortedAppliances) {
            System.out.println(appliance);
        }
        fileLogger.info("List of all electrical appliances sorted by power was shown");
    }
}
