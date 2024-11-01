package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class SortByPower implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public SortByPower(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        ArrayList<ElectricalAppliance> sortedAppliances = new ArrayList<>(appliances);

        sortedAppliances.sort(Comparator.comparingInt(ElectricalAppliance::getPower));

        System.out.println("\n\t---- Electrical appliances sorted by power ----");
        for (ElectricalAppliance appliance : sortedAppliances) {
            System.out.println(appliance);
        }
    }
}
