package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

class TurnOffElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public TurnOffElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        for (int i = 0; i < appliances.size(); i++) {
            if (appliances.get(i).isPluggedIn()) {
                System.out.print("\n" + (i + 1) + ": " + appliances.get(i));
            }
        }

        while (true) {
            System.out.print("\n\nEnter index of appliance to turn off: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index--;

            if (index >= 0 && index < appliances.size()) {
                appliances.get(index).turnOff();
                System.out.println("Appliance turned off.");
                break;
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        }
    }
}
