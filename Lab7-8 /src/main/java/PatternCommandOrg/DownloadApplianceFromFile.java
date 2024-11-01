package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class DownloadApplianceFromFile implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public DownloadApplianceFromFile(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        fileLogger.info("The command to download appliances from file was called.");

        Scanner fileScanner = null;
        try {
            File file = new File("/Users/anastasiiafylypiv/Documents/ППelectrical_appliances.txt");

            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 8) {
                    try {
                        String name = parts[0];
                        String brand = parts[1];
                        String model = parts[2];
                        int power = Integer.parseInt(parts[3]);
                        String purchaseDate = parts[4];
                        String warrantyEndDate = parts[5];
                        int price = Integer.parseInt(parts[6]);
                        boolean isPluggedIn = Boolean.parseBoolean(parts[7]);

                        ElectricalAppliance appliance = new ElectricalAppliance(
                                name, brand, model, power, purchaseDate, warrantyEndDate, price, isPluggedIn);
                        appliances.add(appliance);

                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing appliance data.");
                        errorLogger.error(ERROR_MARKER, "Error parsing appliance data from file."
                                + e.getMessage(), e);
                    }
                } else {
                    System.out.println("Incorrect record format.");
                }
            }
            fileLogger.info("Electrical appliances loaded from file.");
            System.out.println("Electrical appliances downloaded from file successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
            errorLogger.error(ERROR_MARKER, "The file for download appliances was not found."
                    + e.getMessage(), e);
        } finally {
            assert fileScanner != null;
            fileScanner.close();
        }
    }
}
