package PatternCommandOrg;

import Appliance.ElectricalAppliance;

import java.util.ArrayList;
import java.util.Scanner;

class ShowListOfElectricAppliances implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public ShowListOfElectricAppliances(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n\t---- List of electrical appliances ----");
        for (int i = 0; i < appliances.size(); i++) {
            System.out.print("\n" + (i + 1) + ": " + appliances.get(i));
        }
    }
}
