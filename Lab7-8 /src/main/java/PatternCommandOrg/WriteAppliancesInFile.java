package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


class WriteAppliancesInFile implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public WriteAppliancesInFile(ArrayList<Appliance.ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to write appliances in file was called.");
        try (FileWriter writer = new FileWriter(
                "/Users/anastasiiafylypiv/Documents/ППelectrical_appliances.txt", false)) {
            for (Appliance.ElectricalAppliance appliance : appliances) {
                writer.write(appliance.getName() + "," + appliance.getBrand() + "," + appliance.getModel() + "," +
                        appliance.getPower() + "," + appliance.getPurchaseDate() + "," +
                        appliance.getWarantyEndDate() + "," + appliance.getPrice() + "," +
                        appliance.isPluggedIn() + "\n");
            }
            fileLogger.info("Appliances was written to file.");
        } catch (IOException e) {
            System.out.println("Error opening file for writing");
            errorLogger.error(ERROR_MARKER, "Error opening file" + e.getMessage(), e);
        }
    }
}
