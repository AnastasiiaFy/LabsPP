package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ArrayList<ElectricalAppliance> appliances = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        MenuManager manager = new MenuManager();
        Home home = new Home(appliances, scanner, manager);

        int choice = 0;

        System.out.println("\n\t\t\t\t----- Menu -----");
        System.out.println("\t\t1. Download electrical appliances from file.");
        System.out.println("\t\t2. Show a list of all electrical appliances.");
        System.out.println("\t\t3. Add new electrical appliance.");
        System.out.println("\t\t4. Delete electrical appliance.");
        System.out.println("\t\t5. Turn on electrical appliance.");
        System.out.println("\t\t6. Turn off electrical appliance.");
        System.out.println("\t\t7. Sort electrical appliance by power.");
        System.out.println("\t\t8. Find electrical appliance by parameter.");
        System.out.println("\t\t9. Calculate the power consumption of turned on electrical appliances.");
        System.out.println("\t\t10. Calculate the price of electrical appliances' work.");
        System.out.println("\t\t11. Exit.");

        do{
            System.out.print("\nEnter an option - ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> home.downloadFromFile();
                case 2 -> home.showList();
                case 3 -> home.addAppliance();
                case 4 -> home.deleteAppliance();
                case 5 -> home.turnOnAppliance();
                case 6 -> home.turnOffAppliance();
                case 7 -> home.sort();
                case 8 -> home.find();
                case 9 -> home.calculateConsumption();
                case 10 -> home.calculatePrice();
                case 11 -> {
                    home.writeInFile();
                    System.out.println("Changes successfully written to file");
                    System.out.println("Exit from the program.");
                    scanner.close();
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }while (choice != 11);
    }
}
