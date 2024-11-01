package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddElectricApplianceTest {

    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;
    private AddElectricAppliance addElectricApplianceCommand;

    @BeforeEach
    public void setUp() {
        appliances = new ArrayList<>();
        scanner = mock(Scanner.class);
        addElectricApplianceCommand = new AddElectricAppliance(appliances, scanner);
    }

    @Test
    public void AddsElectricalAppliance() {
        when(scanner.nextLine()).thenReturn("Праска", "PHILIPS", "DST5040",
                " ", "23-11-19", "23-11-21", " ");
        when(scanner.nextInt()).thenReturn(2600, 2300);

        addElectricApplianceCommand.execute();

        assertEquals(1, appliances.size());

        ElectricalAppliance addedAppliance = appliances.get(0);
        assertEquals("Праска", addedAppliance.getName());
        assertEquals("PHILIPS", addedAppliance.getBrand());
        assertEquals("DST5040", addedAppliance.getModel());
        assertEquals(2600, addedAppliance.getPower());
        assertEquals("23-11-19", addedAppliance.getPurchaseDate());
        assertEquals("23-11-21", addedAppliance.getWarantyEndDate());
        assertEquals(2300, addedAppliance.getPrice());

        verify(scanner, times(7)).nextLine();
        verify(scanner, times(2)).nextInt();
    }
}
