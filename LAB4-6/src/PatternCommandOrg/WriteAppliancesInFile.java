package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


class WriteAppliancesInFile implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public WriteAppliancesInFile(ArrayList<Appliance.ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try (FileWriter writer = new FileWriter("electrical_appliances.txt", false)) {
            for (Appliance.ElectricalAppliance appliance : appliances) {
                writer.write(appliance.getName() + "," + appliance.getBrand() + "," + appliance.getModel() + "," +
                        appliance.getPower() + "," + appliance.getPurchaseDate() + "," +
                        appliance.getWarantyEndDate() + "," + appliance.getPrice() + "," + appliance.isPluggedIn() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error opening file for writing");
        }
    }
}
