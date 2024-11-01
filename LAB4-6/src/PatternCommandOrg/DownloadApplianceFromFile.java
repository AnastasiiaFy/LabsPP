package PatternCommandOrg;

import Appliance.ElectricalAppliance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class DownloadApplianceFromFile implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public DownloadApplianceFromFile(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Scanner fileScanner = null;
        try {
            File file = new File("electrical_appliances.txt");

            if (!file.exists()) {
                System.out.println("The file was not found.");
                return;
            }

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
                    }
                } else {
                    System.out.println("Incorrect record format.");
                }
            }
            System.out.println("Electrical appliances downloaded from file successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }
}
