package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

class DeleteElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public DeleteElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to delete appliances was called.");
        ShowListOfElectricAppliances showCommand = new ShowListOfElectricAppliances(appliances, scanner);
        MenuManager manager2 = new MenuManager();

        manager2.runCommand(showCommand, scanner);
        while (true) {
            System.out.print("\n\nEnter index of appliance to delete: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index--;

            if (index >= 0 && index < appliances.size()) {
                appliances.remove(index);
                System.out.println("Appliance deleted.");
                fileLogger.info("Electrical appliance was removed from the list");
                break;
            } else {
                System.out.println("Invalid index. Please try again.");
                fileLogger.warn("User selected an invalid index to delete appliance.");
            }
        }
    }
}
