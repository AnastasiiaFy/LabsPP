package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

class HomeTest {
    private Home home;
    private Scanner scanner;
    private MenuManager menuManager;
    private ArrayList<ElectricalAppliance> appliances;

    @BeforeEach
    public void setUp() {
        scanner = mock(Scanner.class);
        menuManager = mock(MenuManager.class);
        appliances = new ArrayList<>();

        home = new Home(appliances, scanner, menuManager);
    }

    @Test
    public void testDownloadFromFile() {
        home.downloadFromFile();

        verify(menuManager).runCommand(any(DownloadApplianceFromFile.class), eq(scanner));
    }

    @Test
    public void testShowList() {
        home.showList();

        verify(menuManager).runCommand(any(ShowListOfElectricAppliances.class), eq(scanner));
    }

    @Test
    public void testAddAppliance() {
        when(scanner.nextLine()).thenReturn("Праска", "PHILIPS",
                "DST5040", " ", "23-11-19", "23-11-21", " ");
        when(scanner.nextInt()).thenReturn(2600, 2300);

        home.addAppliance();

        verify(menuManager).runCommand(any(AddElectricAppliance.class), eq(scanner));
    }
    @Test
    public void testDeleteAppliance() {
        home.deleteAppliance();

        verify(menuManager).runCommand(any(DeleteElectricAppliance.class), eq(scanner));
    }

    @Test
    public void testTurnOnAppliance() {
        home.turnOnAppliance();

        verify(menuManager).runCommand(any(TurnOnElectricAppliance.class), eq(scanner));
    }

    @Test
    public void testTurnOffAppliance() {
        home.turnOffAppliance();

        verify(menuManager).runCommand(any(TurnOffElectricAppliance.class), eq(scanner));
    }

    @Test
    public void testSort() {
        home.sort();

        verify(menuManager).runCommand(any(SortByPower.class), eq(scanner));
    }

    @Test
    public void testFind() {
        home.find();

        verify(menuManager).runCommand(any(FindByParameters.class), eq(scanner));
    }

    @Test
    public void testCalculateConsumption() {
        home.calculateConsumption();

        verify(menuManager).runCommand(any(CalculatePowerConsumption.class), eq(scanner));
    }

    @Test
    public void testCalculatePrice() {
        home.calculatePrice();

        verify(menuManager).runCommand(any(CalculatePriceOfWork.class), eq(scanner));
    }

    @Test
    public void testWriteInFile() {
        home.writeInFile();

        verify(menuManager).runCommand(any(WriteAppliancesInFile.class), eq(scanner));
    }
}