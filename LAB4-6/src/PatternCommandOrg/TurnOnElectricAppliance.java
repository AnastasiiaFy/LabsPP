package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

class TurnOnElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public TurnOnElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        for (int i = 0; i < appliances.size(); i++) {
            if (!appliances.get(i).isPluggedIn()) {
                System.out.print("\n" + (i + 1) + ": " + appliances.get(i));
            }
        }

        while (true) {
            System.out.print("\n\nEnter index of appliance to turn on: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index--;

            if (index >= 0 && index < appliances.size()) {
                appliances.get(index).turnOn();
                System.out.println("Appliance turned on.");
                break;
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        }
    }
}
