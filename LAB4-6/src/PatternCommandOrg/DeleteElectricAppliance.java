package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

class DeleteElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public DeleteElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        ShowListOfElectricAppliances showCommand = new ShowListOfElectricAppliances(appliances, scanner);
        MenuManager manager2 = new MenuManager();

        manager2.runCommand(showCommand, scanner);
        while (true) {
            System.out.println("\n\nEnter index of appliance to delete:");
            int index = scanner.nextInt();
            scanner.nextLine();
            index--;

            if (index >= 0 && index < appliances.size()) {
                appliances.remove(index);
                System.out.println("Appliance deleted.");
                break;
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        }
    }
}
