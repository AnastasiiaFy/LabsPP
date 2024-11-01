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

class TurnOnElectricApplianceTest {
    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;
    private TurnOnElectricAppliance turnOnElectricAppliance;
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
    public void showOnlyTurnedOnAppliances() {
        turnOnElectricAppliance = new TurnOnElectricAppliance(appliances, scanner);
        turnOnElectricAppliance.showTurnedOffAppliances();

        String expectedOutput =
                "1: Name:  Праска" +
                        "\n   Brand: PHILIPS" +
                        "\n   Model: DST5040" +
                        "\n   Power: 2600" +
                        "\n   Date of purchase: 23-11-19" +
                        "\n   End date of waranty: 23-11-21" +
                        "\n   Price: 2300" +
                        "\n   IsPluggedIn: false";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void TurnOnAppliance() {
        when(scanner.nextInt()).thenReturn(1);
        when(scanner.nextLine()).thenReturn("");

        assertFalse(appliances.get(0).isPluggedIn());

        turnOnElectricAppliance = new TurnOnElectricAppliance(appliances, scanner);
        turnOnElectricAppliance.execute();

        assertTrue(appliances.get(0).isPluggedIn());
    }

    @Test
    public void NoTurnOnWithOverlargeIndex() {
        when(scanner.nextInt()).thenReturn(10, 1);
        when(scanner.nextLine()).thenReturn("", "");

        assertFalse(appliances.get(0).isPluggedIn());

        turnOnElectricAppliance = new TurnOnElectricAppliance(appliances, scanner);
        turnOnElectricAppliance.execute();

        assertTrue(outContent.toString().contains("Invalid index. Please try again."));

        assertTrue(appliances.get(0).isPluggedIn());
    }

    @Test
    public void NoTurnOnWithNegativeIndex() {
        when(scanner.nextInt()).thenReturn(-1, 1);
        when(scanner.nextLine()).thenReturn("");

        assertFalse(appliances.get(0).isPluggedIn());

        TurnOnElectricAppliance command = new TurnOnElectricAppliance(appliances, scanner);
        command.execute();

        assertTrue(outContent.toString().contains("Invalid index. Please try again."));

        assertTrue(appliances.get(0).isPluggedIn());
    }

    @Test
    public void NoTurnOnWithZeroIndex() {
        when(scanner.nextInt()).thenReturn(0, 1);
        when(scanner.nextLine()).thenReturn("");

        assertFalse(appliances.get(0).isPluggedIn());

        TurnOnElectricAppliance command = new TurnOnElectricAppliance(appliances, scanner);
        command.execute();

        assertTrue(outContent.toString().contains("Invalid index. Please try again."));

        assertTrue(appliances.get(0).isPluggedIn());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}