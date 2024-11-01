package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

class FindByParameters implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public FindByParameters(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n1. Find by name.");
        System.out.println("2. Find by power.");
        System.out.println("3. Find by price.");

        System.out.print("Enter the search variant: ");
        int variant = scanner.nextInt();
        scanner.nextLine();
        int min = 0;
        int max = 0;

        switch (variant) {
            case 1:
                System.out.print("Enter the name of the appliance: ");
                String searchName = scanner.nextLine().toLowerCase();

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.getName().toLowerCase().equals(searchName)) {
                        System.out.println(appliance);
                    }
                }
                break;
            case 2:
                System.out.print("Enter the min power: ");
                min = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the max power: ");
                max = scanner.nextInt();
                scanner.nextLine();

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.getPower() >= min && appliance.getPower() <= max) {
                        System.out.println(appliance);
                    }
                }
                break;
            case 3:
                System.out.print("Enter the min price: ");
                min = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the max price: ");
                max = scanner.nextInt();
                scanner.nextLine();

                for (ElectricalAppliance appliance : appliances) {
                    if (appliance.getPrice() >= min && appliance.getPrice() <= max) {
                        System.out.println(appliance);
                    }
                }
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
}
