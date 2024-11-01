package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindByParametersTest {
    private ArrayList<ElectricalAppliance> appliances;
    private FindByParameters findByParameters;
    private Scanner scanner;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Праска", "PHILIPS",
                "DST5040", 2600, "23-11-19", "23-11-21",
                2300, true));
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG",
                "RB33L3200SA", 700, "12-01-20", "12-01-23",
                22500, false));
        appliances.add(new ElectricalAppliance("Телевізор", "LG",
                "55UR78006LK", 500, "03-04-23", "03-04-26",
                20500, true));

        scanner = mock(Scanner.class);
        findByParameters = new FindByParameters(appliances, scanner);
    }

    @Test
    public void CorrectFindByName() {
        when(scanner.nextInt()).thenReturn(1);
        when(scanner.nextLine()).thenReturn("","холодильник");

        findByParameters.execute();

        String expectedOutput =
                "1. Find by name." +
                "\n2. Find by power." +
                "\n3. Find by price." +
                "\nEnter the search variant: " +
                "Enter the name of the appliance:" +
                " Name:  Холодильник" +
                "\n   Brand: SAMSUNG" +
                "\n   Model: RB33L3200SA" +
                "\n   Power: 700" +
                "\n   Date of purchase: 12-01-20" +
                "\n   End date of waranty: 12-01-23" +
                "\n   Price: 22500" +
                "\n   IsPluggedIn: false";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void CorrectFindByPowerRange() {
        when(scanner.nextInt()).thenReturn(2, 600, 3000);
        when(scanner.nextLine()).thenReturn("","", "");

        findByParameters.execute();


        assertTrue(outContent.toString().contains(" Name:  Праска"));
        assertTrue(outContent.toString().contains("Name:  Холодильник"));
        assertFalse(outContent.toString().contains("Name:  Телевізор"));
    }

    @Test
    public void CorrectFindByPowerRangeOnEnds() {
        when(scanner.nextInt()).thenReturn(2, 500, 700);
        when(scanner.nextLine()).thenReturn("","", "");

        findByParameters.execute();

        assertTrue(outContent.toString().contains("Name:  Холодильник"));
        assertTrue(outContent.toString().contains("Name:  Телевізор"));
        assertFalse(outContent.toString().contains(" Name:  Праска"));
    }

    @Test
    public void CorrectFindByPriceRange() {
        when(scanner.nextInt()).thenReturn(3, 1000, 3000);
        when(scanner.nextLine()).thenReturn("","", "");

        findByParameters.execute();

        assertTrue(outContent.toString().contains(" Name:  Праска"));
        assertFalse(outContent.toString().contains("Name:  Холодильник"));
        assertFalse(outContent.toString().contains("Name:  Телевізор"));
    }

    @Test
    public void CorrectFindByPriceRangeOnEnds() {
        when(scanner.nextInt()).thenReturn(3, 20500, 22500);
        when(scanner.nextLine()).thenReturn("","", "");

        findByParameters.execute();

        assertTrue(outContent.toString().contains("Name:  Холодильник"));
        assertTrue(outContent.toString().contains("Name:  Телевізор"));
        assertFalse(outContent.toString().contains(" Name:  Праска"));
    }

    @Test
    public void NegativeInvalidVariant() {
        when(scanner.nextInt()).thenReturn(-1);
        when(scanner.nextLine()).thenReturn("");

        findByParameters.execute();

        String expectedOutput =
                "1. Find by name." +
                "\n2. Find by power." +
                "\n3. Find by price." +
                "\nEnter the search variant: " +
                "Invalid input.";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void Overlarge() {
        when(scanner.nextInt()).thenReturn(6);
        when(scanner.nextLine()).thenReturn("");

        findByParameters.execute();

        String expectedOutput =
                "1. Find by name." +
                        "\n2. Find by power." +
                        "\n3. Find by price." +
                        "\nEnter the search variant: " +
                        "Invalid input.";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}




/*String expectedOutput =
                "1. Find by name." +
                "\n2. Find by power." +
                "\n3. Find by price." +
                "\nEnter the search variant: " +
                "Enter the min power: " +
                "Enter the max power: " +
                "Name:  Праска" +
                "\n   Brand: PHILIPS" +
                "\n   Model: DST5040" +
                "\n   Power: 2600" +
                "\n   Date of purchase: 23-11-19" +
                "\n   End date of waranty: 23-11-21" +
                "\n   Price: 2300" +
                "\n   IsPluggedIn: true" +
                "\n\nName:  Холодильник" +
                "\n   Brand: SAMSUNG" +
                "\n   Model: RB33L3200SA" +
                "\n   Power: 700" +
                "\n   Date of purchase: 12-01-20" +
                "\n   End date of waranty: 12-01-23" +
                "\n   Price: 22500" +
                "\n   IsPluggedIn: false";

        assertEquals(expectedOutput, outContent.toString().trim());*/