package PatternCommandOrg;

import Appliance.ElectricalAppliance;

import java.util.ArrayList;
import java.util.Scanner;

class AddElectricAppliance implements Command {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;

    public AddElectricAppliance(ArrayList<ElectricalAppliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n\tEnter the information about the electrical appliance: ");

        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter the model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the power: ");
        int power = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the purchase date: ");
        String purchaseDate = scanner.nextLine();
        System.out.print("Enter the waranty end date: ");
        String warantyEndDate = scanner.nextLine();
        System.out.print("Enter the price: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        appliances.add(new ElectricalAppliance(name, brand,
                model, power, purchaseDate, warantyEndDate, price));
        System.out.println("Appliance added.");
    }
}


