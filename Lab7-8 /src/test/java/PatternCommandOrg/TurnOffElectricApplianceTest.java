package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TurnOffElectricApplianceTest {
    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;
    private TurnOffElectricAppliance turnOffElectricAppliance;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Праска", "PHILIPS",
                "DST5040", 2600, "23-11-19",
                "23-11-21", 2300, false));
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG",
                "RB33L3200SA", 700, "12-01-20",
                "12-01-23", 22500, true));

        scanner = mock(Scanner.class);
    }

    @Test
    public void showOnlyTurnedOffAppliances() {
        turnOffElectricAppliance = new TurnOffElectricAppliance(appliances, scanner);
        turnOffElectricAppliance.showTurnedOnAppliances();

        String expectedOutput =
                "2: Name:  Холодильник" +
                        "\n   Brand: SAMSUNG" +
                        "\n   Model: RB33L3200SA" +
                        "\n   Power: 700" +
                        "\n   Date of purchase: 12-01-20" +
                        "\n   End date of waranty: 12-01-23" +
                        "\n   Price: 22500" +
                        "\n   IsPluggedIn: true";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void TurnOffAppliance() {
        when(scanner.nextInt()).thenReturn(2);
        when(scanner.nextLine()).thenReturn("");

        assertTrue(appliances.get(1).isPluggedIn());

        turnOffElectricAppliance = new TurnOffElectricAppliance(appliances, scanner);
        turnOffElectricAppliance.execute();

        assertFalse(appliances.get(1).isPluggedIn());
    }

    @Test
    public void NoTurnOffWithOverlargeIndex() {
        when(scanner.nextInt()).thenReturn(5, 2);
        when(scanner.nextLine()).thenReturn("");

        assertTrue(appliances.get(1).isPluggedIn());

        turnOffElectricAppliance = new TurnOffElectricAppliance(appliances, scanner);
        turnOffElectricAppliance.execute();

        assertFalse(appliances.get(1).isPluggedIn());
    }

    @Test
    public void NoTurnOffWithNegativeIndex() {
        when(scanner.nextInt()).thenReturn(-1, 2);
        when(scanner.nextLine()).thenReturn("");

        assertTrue(appliances.get(1).isPluggedIn());

        turnOffElectricAppliance = new TurnOffElectricAppliance(appliances, scanner);
        turnOffElectricAppliance.execute();

        assertFalse(appliances.get(1).isPluggedIn());
    }
    @Test
    public void NoTurnOffWithZeroIndex() {
        when(scanner.nextInt()).thenReturn(0, 2);
        when(scanner.nextLine()).thenReturn("");

        assertTrue(appliances.get(1).isPluggedIn());

        turnOffElectricAppliance = new TurnOffElectricAppliance(appliances, scanner);
        turnOffElectricAppliance.execute();

        assertFalse(appliances.get(1).isPluggedIn());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}