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

class SortByPowerTest {
    private ArrayList<ElectricalAppliance> appliances;
    private SortByPower sortByPower;
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
        sortByPower = new SortByPower(appliances, scanner);
    }

    @Test
    public void CorrectSortByPower() {
        sortByPower.execute();

        String expectedOutput =
                "---- Electrical appliances sorted by power ----" +
                "\nName:  Телевізор" +
                "\n   Brand: LG" +
                "\n   Model: 55UR78006LK" +
                "\n   Power: 500" +
                "\n   Date of purchase: 03-04-23" +
                "\n   End date of waranty: 03-04-26" +
                "\n   Price: 20500" +
                "\n   IsPluggedIn: true" +
                "\n\nName:  Холодильник" +
                "\n   Brand: SAMSUNG" +
                "\n   Model: RB33L3200SA" +
                "\n   Power: 700" +
                "\n   Date of purchase: 12-01-20" +
                "\n   End date of waranty: 12-01-23" +
                "\n   Price: 22500" +
                "\n   IsPluggedIn: false" +
                "\n\nName:  Праска" +
                "\n   Brand: PHILIPS" +
                "\n   Model: DST5040" +
                "\n   Power: 2600" +
                "\n   Date of purchase: 23-11-19" +
                "\n   End date of waranty: 23-11-21" +
                "\n   Price: 2300" +
                "\n   IsPluggedIn: true";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}