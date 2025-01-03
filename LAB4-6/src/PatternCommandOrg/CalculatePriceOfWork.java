package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

class CalculatePriceOfWork implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public CalculatePriceOfWork(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int powerSum = 0;
        float kWPrice = 0;
        int hours = 0;
        float workPrice = 0;
        String inputkW;

        System.out.println("\n1.Calculate price of work of turned on electrical appliances.");
        System.out.println("2.Calculate price of selected electrical appliances.");

        System.out.print("Enter the variant: ");
        int variant = scanner.nextInt();
        scanner.nextLine();

        switch (variant) {
            case 1:
                System.out.print("Enter the price per kilowatt(kW) of energy: ");
                inputkW = scanner.nextLine().replace(',', '.');
                kWPrice = Float.parseFloat(inputkW);

                System.out.print("Enter number of hours of work: ");
                hours = scanner.nextInt();
                scanner.nextLine();

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.isPluggedIn()) {
                        powerSum += appliance.getPower();
                    }
                }

                workPrice = (float) (powerSum * hours) / 1000 * kWPrice;
                System.out.println("Price of work of turned on electrical appliances - " + workPrice);
                break;
            case 2:
                ShowListOfElectricAppliances showCommand = new ShowListOfElectricAppliances(appliances, scanner);
                MenuManager manager3 = new MenuManager();
                manager3.runCommand(showCommand, scanner);

                System.out.print("\n\nEnter the indexes of appliances to calculate power for (separated by spaces): ");
                String inputIndexes = scanner.nextLine();
                String[] indicesString = inputIndexes.split(" ");

                for (String indexStr : indicesString) {
                    try {
                        int index = Integer.parseInt(indexStr);

                        if (index >= 1 && index <= appliances.size()) {
                            powerSum += appliances.get(index - 1).getPower();
                        } else {
                            System.out.println("Index " + index + " is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input: '" + indexStr + "' is not a valid number.");
                        break;
                    }
                }

                System.out.print("Enter the price per kilowatt(kW) of energy: ");
                inputkW = scanner.nextLine().replace(',', '.');
                kWPrice = Float.parseFloat(inputkW);
                System.out.print("Enter number of hours of work: ");
                hours = scanner.nextInt();
                scanner.nextLine();

                workPrice = (float) (powerSum * hours) / 1000 * kWPrice;
                System.out.println("Price of work of selected electrical appliances - " + workPrice);
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}
