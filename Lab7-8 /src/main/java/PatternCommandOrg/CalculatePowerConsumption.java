package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

class CalculatePowerConsumption implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public CalculatePowerConsumption(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to calculate power consumption was called.");
        int sum = 0;
        for (ElectricalAppliance appliance : appliances) {
            if (appliance.isPluggedIn()) {
                sum += appliance.getPower();
            }
        }
        System.out.println("Сalculated power consumption - " + sum);
        fileLogger.info("The power consumption was calculated");
    }
}
