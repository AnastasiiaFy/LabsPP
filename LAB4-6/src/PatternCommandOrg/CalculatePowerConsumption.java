package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

class CalculatePowerConsumption implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public CalculatePowerConsumption(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int sum = 0;
        for (ElectricalAppliance appliance : appliances) {
            if (appliance.isPluggedIn()) {
                sum += appliance.getPower();
            }
        }
        System.out.println("Сalculated power consumption - " + sum);
    }
}
