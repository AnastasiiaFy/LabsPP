package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.util.ArrayList;
import java.util.Scanner;

public class Home {
    private final ArrayList<ElectricalAppliance> appliances;
    private final Scanner scanner;
    private final MenuManager manager;

    public Home(ArrayList<ElectricalAppliance> appliances, Scanner scanner, MenuManager manager) {
        this.appliances = appliances;
        this.scanner = scanner;
        this.manager = manager;
    }

    public void downloadFromFile() {
        manager.runCommand(new DownloadApplianceFromFile(appliances, scanner), scanner);
    }

    public void showList() {
        manager.runCommand(new ShowListOfElectricAppliances(appliances, scanner), scanner);
    }

    public void addAppliance() {
        manager.runCommand(new AddElectricAppliance(appliances, scanner), scanner);
    }

    public void deleteAppliance() {
        manager.runCommand(new DeleteElectricAppliance(appliances, scanner), scanner);
    }

    public void turnOnAppliance() {
        manager.runCommand(new TurnOnElectricAppliance(appliances, scanner), scanner);
    }

    public void turnOffAppliance() {
        manager.runCommand(new TurnOffElectricAppliance(appliances, scanner), scanner);
    }

    public void sort() {
        manager.runCommand(new SortByPower(appliances, scanner), scanner);
    }

    public void find() {
        manager.runCommand(new FindByParameters(appliances, scanner), scanner);
    }

    public void calculateConsumption() {
        manager.runCommand(new CalculatePowerConsumption(appliances, scanner), scanner);
    }

    public void calculatePrice() {
        manager.runCommand(new CalculatePriceOfWork(appliances, scanner), scanner);
    }

    public void writeInFile() {
        manager.runCommand(new WriteAppliancesInFile(appliances, scanner), scanner);
    }
}
