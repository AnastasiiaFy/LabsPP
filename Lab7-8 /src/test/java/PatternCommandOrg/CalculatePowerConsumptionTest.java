package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalculatePowerConsumptionTest {

    private ArrayList<ElectricalAppliance> appliances;
    private CalculatePowerConsumption calculatePowerConsumption;
    private Scanner scanner;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        appliances = new ArrayList<>();

        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scanner = new Scanner(System.in);

        ElectricalAppliance appliance1 = mock(ElectricalAppliance.class);
        ElectricalAppliance appliance2 = mock(ElectricalAppliance.class);
        ElectricalAppliance appliance3 = mock(ElectricalAppliance.class);

        when(appliance1.isPluggedIn()).thenReturn(true);
        when(appliance1.getPower()).thenReturn(100);

        when(appliance2.isPluggedIn()).thenReturn(false);
        when(appliance2.getPower()).thenReturn(200);

        when(appliance3.isPluggedIn()).thenReturn(true);
        when(appliance3.getPower()).thenReturn(150);

        appliances.add(appliance1);
        appliances.add(appliance2);
        appliances.add(appliance3);

        calculatePowerConsumption = new CalculatePowerConsumption(appliances, scanner);
    }

    @Test
    public void CalculatesCorrectPowerConsumption() {
        calculatePowerConsumption.execute();

        assertEquals("Сalculated power consumption - 250", outContent.toString().trim());
    }
}
