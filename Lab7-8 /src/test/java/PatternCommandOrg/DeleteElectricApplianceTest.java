package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DeleteElectricApplianceTest {

    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;
    private DeleteElectricAppliance deleteElectricAppliance;

    @BeforeEach
    public void setUp() {
        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Праска", "PHILIPS", "DST5040", 700,
                "23-11-19", "23-11-21", 22500, false));
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG", "RB33L3200SA", 700,
                "12-01-20", "12-01-23", 22500, false));

        scanner = mock(Scanner.class);
        deleteElectricAppliance = new DeleteElectricAppliance(appliances, scanner);
    }

    @Test
    public void DeletesApplianceWithValidIndex() {
        when(scanner.nextInt()).thenReturn(2);
        when(scanner.nextLine()).thenReturn("");

        deleteElectricAppliance.execute();

        assertEquals(1, appliances.size());
        assertTrue(appliances.stream().noneMatch(appliance -> appliance.getName().equals("Microwave")));
    }

    @Test
    public void NoDeletesApplianceWithInvalidIndex() {

        when(scanner.nextInt()).thenReturn(5,1);
        when(scanner.nextLine()).thenReturn("");

        //Перевіряємо чи при неправильному індексі не видалився жоден прилад
        assertEquals(2, appliances.size());

        deleteElectricAppliance.execute();

        assertEquals(1, appliances.size());

    }
}
